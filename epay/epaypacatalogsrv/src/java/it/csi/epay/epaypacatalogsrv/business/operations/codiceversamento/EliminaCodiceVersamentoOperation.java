/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import it.csi.epay.epaypacatalogsrv.business.service.RiferimentoContabileService;
import it.csi.epay.epaypacatalogsrv.business.service.impl.InvioMailServiceImpl;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneEnteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = EliminaCodiceVersamentoInput.class, produces = EliminaCodiceVersamentoOutput.class )
@Component
public class EliminaCodiceVersamentoOperation implements OperationHandler<EliminaCodiceVersamentoInput, EliminaCodiceVersamentoOutput> {

    @Autowired
    private CodiceVersamentoRepository repository;

    @Autowired
    private AssociazioneEnteCodiceVersamentoRepository associazioneEnteCodiceVersamentoRepository;

    @Autowired
    private AssociazioneUtenteCodiceVersamentoRepository associazioneUtenteCodiceVersamentoRepository;

    @Autowired
    private RiferimentoContabileService riferimentoContabileService;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Override
    public void preValidateInput ( EliminaCodiceVersamentoInput input,
        OperationDispatchingContext<EliminaCodiceVersamentoInput, EliminaCodiceVersamentoOutput> context ) {

        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( EliminaCodiceVersamentoInput input,
        OperationDispatchingContext<EliminaCodiceVersamentoInput, EliminaCodiceVersamentoOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.ELIMINA_CODICE_VERSAMENTO );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public void validateInput ( EliminaCodiceVersamentoInput input,
        OperationDispatchingContext<EliminaCodiceVersamentoInput, EliminaCodiceVersamentoOutput> context ) {

        CodiceVersamento current = repository.findOne ( input.getId () );
        if ( current == null ) {
            throw new NotFoundException ( Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND );
        }

        if ( EntityUtils.isTrue ( current.getFlagAnnullato () ) ) {
            throw new NotFoundException ( Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND );
        }

        if ( current.getCodiceVersamentoPadre () == null && current.getCodiciVersamentoCollegati ().size () > 0 ) {
            for ( CodiceVersamento cvc: current.getCodiciVersamentoCollegati () ) {
                if ( !EntityUtils.isTrue ( cvc.getFlagAnnullato () ) ) {
                    throw new BadRequestException (
                        Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_COLLEGATI );
                }
            }
        }

        if ( associazioneEnteCodiceVersamentoRepository.findByIdCodiceVersamento ( current.getId ().intValue () ).size () > 0 ) {
            throw new BadRequestException (
                Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_ENTE );
        }

        if ( associazioneUtenteCodiceVersamentoRepository.findByIdCodiceVersamento ( current.getId ().intValue () ).size () > 0 ) {
            throw new BadRequestException (
                Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_UTENTE );
        }

        if ( riferimentoContabileService.getAttiviByIdCodiceVersamento ( current.getId () ).size () > 0 ) {
            throw new BadRequestException (
                Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE_ASSOCIATO_RIFERIMENTI_CONTABILI );
        }
    }

    @Override
    @Transactional
    public EliminaCodiceVersamentoOutput execute ( EliminaCodiceVersamentoInput input,
        OperationDispatchingContext<EliminaCodiceVersamentoInput, EliminaCodiceVersamentoOutput> context ) {

        CodiceVersamento current = repository.findOne ( input.getId () );

        // lazy load
        current.getEnte ().getDenominazione ();

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( current );

        EsitoPropagazione risEsito = EsitoPropagazione.KO;
        
        try {
            for ( CodiceVersamento cvc: current.getCodiciVersamentoCollegati () ) {
                if ( EntityUtils.isTrue ( cvc.getFlagAnnullato () ) ) {
                    repository.delete ( cvc );
                }
            }
            current.setCodiciVersamentoCollegati ( new ArrayList<> () );

            repository.deleteAssociazioniEnteCodiceVersamentoByCodiceVersamento ( current.getId () );
            current.setFlagAnnullato ( true );
            repository.save ( current );

            EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaCodiceVersamento ( current, AzioneDaPropagare.ELIMINAZIONE );
            risEsito = risultatoPropagazione.getEsito ();
            
            if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
                throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                    risultatoPropagazione.getMessaggio () );
            }

            current.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
            current.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );

            repository.save ( current );

            repository.flush ();
        } catch ( DataIntegrityViolationException e ) {
            throw new BadRequestException ( Constants.MESSAGES.CODICE_VERSAMENTO_NON_ELIMINABILE );
        }

        // Inserito controllo sullo stato per evitare spam alla casella di posta del servizio.
        if ( risEsito != EsitoPropagazione.OK ) {
            inviaMail ( current );
        }

        return EliminaCodiceVersamentoOutput.ok ();
    }

    private void inviaMail ( CodiceVersamento entity ) {
        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "stato_aggiornamento", entity.getDescrizioneEsitoAggiornamento () );

        parametri.put ( "azione_oggetto", "Cancellazione" );
        parametri.put ( "azione", "cancellato" );
        parametri.put ( "descrizione_ente", entity.getEnte ().getDenominazione () );
        parametri.put ( "descrizione_codice_versamento", entity.getCodice () + " - " + entity.getDescrizione () );

        invioMailService.inviaMail ( EmailEnum.CODICE_VERSAMENTO,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            entity.getEnte ().getId (),
            parametri );
    }
}
