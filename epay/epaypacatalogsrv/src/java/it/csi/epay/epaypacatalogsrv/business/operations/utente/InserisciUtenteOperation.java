/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.utente;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.business.service.PropagazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.EsitoPropagazioneDTO;
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.utente.InserisciUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.InserisciUtenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.ManagedException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = InserisciUtenteInput.class, produces = InserisciUtenteOutput.class )
@Component
public class InserisciUtenteOperation implements OperationHandler<InserisciUtenteInput, InserisciUtenteOutput> {

    @Autowired
    private UtenteRepository repository;

    //EPAY-80
    //@Autowired
    //private EnteRepository enteRepository;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

    @Override
    public void authorize ( InserisciUtenteInput input,
        OperationDispatchingContext<InserisciUtenteInput, InserisciUtenteOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.INSERISCI_UTENTE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );

        SecurityUtils.assertAmministratoreEnteCorrente ();
    }

    @Override
    public void validateInput ( InserisciUtenteInput input,
        OperationDispatchingContext<InserisciUtenteInput, InserisciUtenteOutput> context ) {

        if ( StringUtils.isBlank ( input.getCodiceFiscale () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "codice fiscale" );
        }
        if ( StringUtils.isBlank ( input.getNome () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "nome" );
        }
        if ( StringUtils.isBlank ( input.getCognome () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "cognome" );
        }
        if ( StringUtils.isBlank ( input.getEmail () ) ) {
            // throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "email" );
            // OK, il campo e' opzionale
        } else {
            if ( !EntityUtils.isValidEmail ( input.getEmail () ) ) {
                throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "email" );
            }
        }
        //        if ( input.getDataInizioValidita () == null ) {
        //            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "data inizio validita'" );
        //        }

    }

    @Override
    @Transactional
    public InserisciUtenteOutput execute ( InserisciUtenteInput input,
        OperationDispatchingContext<InserisciUtenteInput, InserisciUtenteOutput> context ) {

        Utente entity = map ( input );

        EntityUtils.populateEditFields ( entity );

        try {
            Utente utenteTrovato = repository.findOneCompletoByCodiceFiscale ( input.getCodiceFiscale () );
            if ( null != utenteTrovato ) {
                //EPAY-80
                for ( AssociazioneUtenteEnte rUtenteEnte: utenteTrovato.getAssociazioneUtenteEnte () ) {
                    if ( SecurityUtils.getCurrentIdEnte ().equals ( rUtenteEnte.getId ().getIdEnte ().longValue () ) ) {
                        throw new ManagedException (
                            String.format ( Constants.MESSAGES.UTENTE_ESISTENTE, input.getCodiceFiscale () ) );
                    }
                }
                AssociazioneUtenteEnte ass = manageAssociazioneUtenteEnte ( input, entity, utenteTrovato.getId () );
                if ( !StringUtils.isEmpty ( ass.getEmail () ) ) {
                    inviaMail ( ass );
                }
                return InserisciUtenteOutput.ok ( utenteTrovato.getId () );
            }
        } catch ( IncorrectResultSizeDataAccessException e ) {
            throw new ManagedException (
                String.format ( Constants.MESSAGES.UTENTE_ESISTENTE, input.getCodiceFiscale () ) );
        }


        //EPAY-80
        //entity.getEnti ().add ( enteRepository.findOne ( SecurityUtils.getCurrentIdEnte () ) );

        entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        entity.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );

        repository.save ( entity );

        if ( entity.getId () == null ) {
            throw new ManagedException ();
        }

        EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaUtente ( entity, AzioneDaPropagare.INSERIMENTO );

        if (risultatoPropagazione.getEsito () == EsitoPropagazione.KO) {
            throw new Wso2BroadcastingFailedException(Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio ()
                            );
        }

        entity.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        entity.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );

        //EPAY-80
        AssociazioneUtenteEnte ass = manageAssociazioneUtenteEnte ( input, entity, entity.getId () );
        if ( null == entity.getAssociazioneUtenteEnte () )
            entity.setAssociazioneUtenteEnte ( new ArrayList<AssociazioneUtenteEnte> () );
        entity.getAssociazioneUtenteEnte ().add ( ass );

        repository.save ( entity );

        //EPAY-80
        if ( !StringUtils.isEmpty ( ass.getEmail () ) ) {
            inviaMail ( ass );
        }

        return InserisciUtenteOutput.ok ( entity.getId () );
    }

    private AssociazioneUtenteEnte manageAssociazioneUtenteEnte ( InserisciUtenteInput input, Utente entity, Long idUtente ) {
        AssociazioneUtenteEnte ass = new AssociazioneUtenteEnte ();
        ass.setDataInizioValidita ( input.getDataInizioValidita () );
        ass.setDataFineValidita ( input.getDataFineValidita () );
        ass.setEmail ( input.getEmail () );
        ass.setUtenteInserimento ( entity.getUtenteInserimento () );
        ass.setUtenteModifica ( entity.getUtenteModifica () );
        ass.setDataInserimento ( Timestamp.valueOf ( LocalDateTime.now () ) );
        ass.setDataModifica ( Timestamp.valueOf ( LocalDateTime.now () ) );
        ass.setFlagAdmin ( false );
        AssociazioneUtenteEntePK rUtenteEnte = new AssociazioneUtenteEntePK ();
        rUtenteEnte.setIdEnte ( SecurityUtils.getCurrentIdEnte ().intValue () );
        rUtenteEnte.setIdUtente ( idUtente );
        ass.setId ( rUtenteEnte );
        associazioneUtenteEnteRepository.save ( ass );
        return ass;
    }

    //EPAY-80
    private void inviaMail ( AssociazioneUtenteEnte entity ) {
        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "data_inizio_validita", new SimpleDateFormat ( "dd/MM/yyyy" ).format ( entity.getDataInizioValidita () ) );

        invioMailService.inviaMail ( EmailEnum.INSERIMENTO_UTENTE,
            entity.getEmail (),
            SecurityUtils.getCurrentIdEnte (),
            parametri );
    }

    public Utente map ( InserisciUtenteInput input ) {

        Utente output = new Utente ();

        EntityUtils.copyProperties ( output, input );

        output.setId ( null );
        //EPAY-80
        //output.setEnti ( new ArrayList<> () );
        return output;
    }

}
