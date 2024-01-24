/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.utente;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.ComparatoreDiElementiDiversi;
import it.csi.epay.epaypacatalogsrv.business.util.ComparatoreDiListeDiverse.DifferenzaFraListeDiverse;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaCduUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaCduUtenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotAuthorizedException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCduPK;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;
import it.csi.epay.epaypacatalogsrv.model.Cdu;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.CduRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = AggiornaCduUtenteInput.class, produces = AggiornaCduUtenteOutput.class )
@Component
public class AggiornaCduUtenteOperation implements OperationHandler<AggiornaCduUtenteInput, AggiornaCduUtenteOutput> {

    private final Logger logger = LogManager.getLogger ( this.getClass () );

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private CduRepository cduRepository;

    @Autowired
    private StatoAggiornamentoRepository statoAggiornamentoRepository;

    @Autowired
    private AssociazioneUtenteCduRepository associazioneUtenteCduRepository;

    @Autowired
    private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

    @Override
    public void preValidateInput ( AggiornaCduUtenteInput input, OperationDispatchingContext<AggiornaCduUtenteInput, AggiornaCduUtenteOutput> context ) {
        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( AggiornaCduUtenteInput input, OperationDispatchingContext<AggiornaCduUtenteInput, AggiornaCduUtenteOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.AUTORIZZA_FUNZIONE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );

        SecurityUtils.assertAmministratoreEnteCorrente ();

        Utente current = utenteRepository.findOne ( input.getId () );
        if ( current == null ) {
            throw new NotFoundException ();
        }
    }

    @Override
    public void validateInput ( AggiornaCduUtenteInput input, OperationDispatchingContext<AggiornaCduUtenteInput, AggiornaCduUtenteOutput> context ) {

        /*
         * controllare che l'utente sia abilitato all'ente specificato
         */

        List<Long> assocUtenteEnti = utenteRepository.findIdEntiAssociatiByIdUtente ( input.getId () );

        Long idEnteCorrente = SecurityUtils.getCurrentIdEnte ();

        if ( !assocUtenteEnti.contains ( idEnteCorrente ) ) {
            throw new NotAuthorizedException ( Constants.MESSAGES.UTENTE_ENTE_NOT_ASSOCIATED );
        }

        /*
         * controllare che i CDU esistano -> IMPLEMENTATO IN SEGUITO
         */
        if ( input.getCodiciCdu () != null ) {
            for ( String codice: input.getCodiciCdu () ) {
                if ( StringUtils.isBlank ( codice ) ) {
                    throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "codici cdu" );
                }
            }
        } else {
            input.setCodiciCdu ( new java.util.ArrayList<> () );
        }

        /*
         * controllare che l'utente non sia l'amministratore dell'ente
         */
        Ente ente = enteRepository.findOne ( idEnteCorrente );

        boolean isAdmin = false;
        if ( input != null && ente != null ) {
            AssociazioneUtenteEntePK pk = new AssociazioneUtenteEntePK ();
            pk.setIdEnte ( ente.getId ().intValue () );
            pk.setIdUtente ( input.getId () );
            AssociazioneUtenteEnte admin = associazioneUtenteEnteRepository.findOne ( pk );
            if ( admin != null ) isAdmin = admin.getFlagAdmin ();
        }
        
        if (isAdmin) {
            throw new NotAuthorizedException ( Constants.MESSAGES.USER_ADMIN_ENTE );
        }
    }

    @Override
    @Transactional
    public AggiornaCduUtenteOutput execute ( AggiornaCduUtenteInput input,
        OperationDispatchingContext<AggiornaCduUtenteInput, AggiornaCduUtenteOutput> context ) {

        Long idEnteCorrente = SecurityUtils.getCurrentIdEnte ();

        Utente current = utenteRepository.findOne ( input.getId () );

        List<AssociazioneUtenteCdu> associazioniCorrenti
            = associazioneUtenteCduRepository.findByIdUtenteAndIdEnte ( current.getId (), idEnteCorrente.intValue () );

        DifferenzaFraListeDiverse<AssociazioneUtenteCdu, String> differenzaTraCorrenteEdInput
            = ComparatoreDiListeDiverse.compareLists ( associazioniCorrenti, input.getCodiciCdu (), new ComparatoreDiElementiDiversi<AssociazioneUtenteCdu, String> () {
                @Override
                public boolean compara ( AssociazioneUtenteCdu corrente, String desiderato ) {
                        return corrente.getId ().getCodCdu ().equals ( desiderato );
            }
        } );

        for ( AssociazioneUtenteCdu daEliminare: differenzaTraCorrenteEdInput.getElementiSoloNellaPrima () ) {
            logger.debug ( "elimino associazione con cdu " + daEliminare.getId ().getCodCdu () );
            associazioneUtenteCduRepository.delete ( daEliminare );
        }

        for ( String daInserire: differenzaTraCorrenteEdInput.getElementiSoloNellaSeconda () ) {
            Cdu cduDesiderato = cduRepository.findOneByCodice ( daInserire );
            if ( cduDesiderato == null ) {
                throw new NotFoundException ( Constants.MESSAGES.CDU_NOT_FOUND, cduDesiderato );
            }

            logger.debug ( "inserisco associazione con cdu " + daInserire );

            AssociazioneUtenteCdu nuovo = new AssociazioneUtenteCdu ();
            AssociazioneUtenteCduPK id = new AssociazioneUtenteCduPK ();
            id.setCodCdu ( daInserire );
            id.setIdEnte ( idEnteCorrente.intValue () );
            id.setIdUtente ( current.getId () );
            nuovo.setId ( id );
            associazioneUtenteCduRepository.save ( nuovo );
        }

        // aggiorno su database esterni e salvo risultato della sincronizzazione

        current.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_KO ) );
        current.setDescrizioneErroreAggiornamento ( "Servizio di propagazione non disponibile" );
        utenteRepository.save ( current );

        // costruisco output

        AggiornaCduUtenteOutput output = AggiornaCduUtenteOutput.ok ( AggiornaCduUtenteOutput.class );
        output.setCodiceRisultatoSincronizzazione ( current.getStatoAggiornamento ().getCodice () );
        output.setDescrizioneRisultatoSincronizzazione ( current.getDescrizioneErroreAggiornamento () );
        return output;
    }

}
