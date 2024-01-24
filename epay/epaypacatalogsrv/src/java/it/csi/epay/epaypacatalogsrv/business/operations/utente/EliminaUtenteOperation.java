/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.utente;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.utente.EliminaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.EliminaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteTematicaRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = EliminaUtenteInput.class, produces = EliminaUtenteOutput.class )
@Component
public class EliminaUtenteOperation implements OperationHandler<EliminaUtenteInput, EliminaUtenteOutput> {

    @Autowired
    private UtenteRepository repository;

    @Autowired
    private AssociazioneUtenteCduRepository associazioneUtenteCduRepository;

    @Autowired
    private AssociazioneUtenteTematicaRepository associazioneUtenteTematicaRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

    @Override
    public void preValidateInput ( EliminaUtenteInput input, OperationDispatchingContext<EliminaUtenteInput, EliminaUtenteOutput> context ) {

        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( EliminaUtenteInput input, OperationDispatchingContext<EliminaUtenteInput, EliminaUtenteOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.ELIMINA_UTENTE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );

        SecurityUtils.assertAmministratoreEnteCorrente ();
    }

    @Override
    @Transactional
    public void validateInput ( EliminaUtenteInput input, OperationDispatchingContext<EliminaUtenteInput, EliminaUtenteOutput> context ) {

        Utente current = repository.findOne ( input.getId () );
        if ( current == null ) {
            throw new NotFoundException ();
        }

        // non puo eliminare utente corrente
        if ( input.getId ().equals ( SecurityUtils.getPrincipal ().getUtente ().getId () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.MODIFICA_UTENTE_CORRENTE_NON_CONSENTITA );
        }

    }

    @Override
    @Transactional
    public EliminaUtenteOutput execute ( EliminaUtenteInput input, OperationDispatchingContext<EliminaUtenteInput, EliminaUtenteOutput> context ) {

        Utente current = repository.findOne ( input.getId () );
        //EPAY-80
        String emailUsrDel = null;
        AssociazioneUtenteEntePK rUtenteEnte = new AssociazioneUtenteEntePK ();
        rUtenteEnte.setIdEnte ( SecurityUtils.getCurrentIdEnte ().intValue () );
        rUtenteEnte.setIdUtente ( current.getId () );
        AssociazioneUtenteEnte assUE = associazioneUtenteEnteRepository.findOne ( rUtenteEnte );
        if ( null != assUE )
            emailUsrDel = assUE.getEmail ();

        try {
            deleteUtente ( current );
        } catch ( DataIntegrityViolationException e ) {
            throw new BadRequestException ( Constants.MESSAGES.UTENTE_NON_ELIMINABILE );
        }

        EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaUtente ( current, AzioneDaPropagare.ELIMINAZIONE );

        if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        current.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        current.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );

        if ( StringUtils.isNotBlank ( emailUsrDel ) ) {
            inviaMail ( emailUsrDel );
        }

        return EliminaUtenteOutput.ok ();
    }

    //EPAY-80
    private void inviaMail ( String email ) {

        invioMailService.inviaMail ( EmailEnum.ELIMINAZIONE_UTENTE,
            email,
            SecurityUtils.getCurrentIdEnte (),
            null );
    }

    private void deleteUtente ( Utente cv ) {

        associazioneUtenteCduRepository.deleteByIdUtente ( cv.getId () );
        associazioneUtenteCodiceVersamentoRepository.deleteByIdUtente ( cv.getId () );
        associazioneUtenteTematicaRepository.deleteByIdUtente ( cv.getId () );

        //EPAY-80 
        //        if ( cv.getEnti () != null ) {
        //            if ( cv.getEnti ().size () == 1 && cv.getEnti ().get ( 0 ).getId ().equals ( SecurityUtils.getCurrentIdEnte () ) ) {
        //                cv.getEnti ().clear ();
        //            } else if ( cv.getEnti ().size () > 0 ) {
        //                throw new BadRequestException ( Constants.MESSAGES.UTENTE_NON_ELIMINABILE );
        //            }
        //        }

        repository.save ( cv );

        //EPAY-80
        //repository.delete ( cv );
        AssociazioneUtenteEntePK rUtenteEnte = new AssociazioneUtenteEntePK ();
        rUtenteEnte.setIdEnte ( SecurityUtils.getCurrentIdEnte ().intValue () );
        rUtenteEnte.setIdUtente ( cv.getId () );
        associazioneUtenteEnteRepository.delete ( rUtenteEnte );

        repository.flush ();
    }

}
