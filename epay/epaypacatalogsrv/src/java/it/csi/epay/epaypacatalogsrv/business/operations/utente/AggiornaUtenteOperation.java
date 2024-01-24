/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.utente;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.repository.AssociazioneUtenteEnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.UtenteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Operation ( consumes = AggiornaUtenteInput.class, produces = AggiornaUtenteOutput.class )
@Component
public class AggiornaUtenteOperation implements OperationHandler<AggiornaUtenteInput, AggiornaUtenteOutput> {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private StatoAggiornamentoRepository statoAggiornamentoRepository;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private AssociazioneUtenteEnteRepository associazioneUtenteEnteRepository;

    @Override
    public void preValidateInput ( AggiornaUtenteInput input, OperationDispatchingContext<AggiornaUtenteInput, AggiornaUtenteOutput> context ) {
        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( AggiornaUtenteInput input, OperationDispatchingContext<AggiornaUtenteInput, AggiornaUtenteOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.MODIFICA_UTENTE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );

        SecurityUtils.assertAmministratoreEnteCorrente ();

        Utente current = utenteRepository.findOne ( input.getId () );
        if ( current == null ) {
            throw new NotFoundException ();
        }
    }

    @Override
    public void validateInput ( AggiornaUtenteInput input, OperationDispatchingContext<AggiornaUtenteInput, AggiornaUtenteOutput> context ) {

        // non puo' modificare utente corrente
        if ( input.getId ().equals ( SecurityUtils.getPrincipal ().getUtente ().getId () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.MODIFICA_UTENTE_CORRENTE_NON_CONSENTITA );
        }

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
    public AggiornaUtenteOutput execute ( AggiornaUtenteInput input, OperationDispatchingContext<AggiornaUtenteInput, AggiornaUtenteOutput> context ) {

        Utente current = utenteRepository.findOne ( input.getId () );

        //EPAY-80
        AssociazioneUtenteEnte assCurrent = null;
        if ( null != current.getAssociazioneUtenteEnte () ) {
            for ( AssociazioneUtenteEnte aue: current.getAssociazioneUtenteEnte () ) {
                if ( current.getId ().equals ( aue.getId ().getIdUtente () )
                                && SecurityUtils.getCurrentIdEnte ().equals ( new Long ( aue.getId ().getIdEnte () ) ) ) {
                    assCurrent = aue;
                    break;
                }
            }
        }

        AssociazioneUtenteEnte assPrecedente = EntityUtils.shallowCopy ( assCurrent );

        //EPAY-80
        //Utente precedente = EntityUtils.shallowCopy ( current );

        //EPAY-80
        //map ( current, input );

        // EPAY-80
        if ( null != assCurrent && null != assCurrent.getId () ) {
            assCurrent.setDataInizioValidita ( input.getDataInizioValidita () );
            assCurrent.setDataFineValidita ( input.getDataFineValidita () );
            assCurrent.setEmail ( input.getEmail () );
            assCurrent.setDataModifica ( Timestamp.valueOf ( LocalDateTime.now () ) );

            PrincipalVO principal = SecurityUtils.getPrincipal ();
            if ( principal != null ) {
                assCurrent.setUtenteModifica ( principal.getUtente ().getCodiceFiscale () );
            } else {
                assCurrent.setUtenteModifica ( Constants.CODICE_UTENTE_SISTEMA );
            }
        }

        //EPAY-80
        //        current.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        //        current.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );
        //
        //        utenteRepository.save ( current );
        associazioneUtenteEnteRepository.saveAndFlush ( assCurrent );

        EsitoPropagazioneDTO risultatoPropagazione = propagazioneService.propagaUtente ( current, AzioneDaPropagare.MODIFICA );

        if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        //EPAY-80
        //        current.setStatoAggiornamento ( statoAggiornamentoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        //        current.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );
        //        if ( verificaSeInviareMailDisattivazione ( current, precedente ) ) {
        //            inviaMailDisattivazione ( current );
        //        } else if ( verificaSeInviareMailRiattivazione ( current, precedente ) ) {
        //            inviaMailRiattivazione ( current );
        //        }        
        if ( verificaSeInviareMailDisattivazioneAssociazione ( assCurrent, assPrecedente ) ) {
            inviaMailDisattivazione ( assCurrent );
        } else if ( verificaSeInviareMailRiattivazioneAssociazione ( assCurrent, assPrecedente ) ) {
            inviaMailRiattivazione ( assCurrent );
        }

        AggiornaUtenteOutput output = AggiornaUtenteOutput.ok ( AggiornaUtenteOutput.class );
        output.setCodiceRisultatoSincronizzazione ( current.getStatoAggiornamento ().getCodice () );
        output.setDescrizioneRisultatoSincronizzazione ( current.getDescrizioneErroreAggiornamento () );
        return output;
    }

    private boolean verificaSeInviareMailDisattivazione ( Utente current, Utente precedente ) {
        if ( StringUtils.isEmpty ( current.getEmail () ) ) {
            return false;
        }

        if ( precedente.getDataFineValidita () == null && current.getDataFineValidita () != null ) {
            return true;
        }

        if ( precedente.getDataFineValidita () != null && current.getDataFineValidita () != null
                        && !current.getDataFineValidita ().equals ( precedente.getDataFineValidita () ) ) {
            return true;
        }

        return false;
    }

    private boolean verificaSeInviareMailRiattivazione ( Utente current, Utente precedente ) {
        if ( StringUtils.isEmpty ( current.getEmail () ) ) {
            return false;
        }

        if ( precedente.getDataFineValidita () != null && current.getDataFineValidita () == null ) {
            // l'utente e' stato riattivato
            return true;
        }

        return false;
    }

    private void inviaMailDisattivazione ( AssociazioneUtenteEnte entity ) {
        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "data_fine_validita", new SimpleDateFormat ( "dd/MM/yyyy" ).format ( entity.getDataFineValidita () ) );

        invioMailService.inviaMail ( EmailEnum.DISATTIVAZIONE_UTENTE,
            entity.getEmail (),
            SecurityUtils.getCurrentIdEnte (),
            parametri );
    }

    private void inviaMailRiattivazione ( AssociazioneUtenteEnte entity ) {
        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "data_inizio_validita", new SimpleDateFormat ( "dd/MM/yyyy" ).format ( entity.getDataInizioValidita () ) );

        invioMailService.inviaMail ( EmailEnum.RIATTIVAZIONE_UTENTE,
            entity.getEmail (),
            SecurityUtils.getCurrentIdEnte (),
            parametri );
    }

    private Utente map ( Utente current, AggiornaUtenteInput input ) {

        current.setCodiceFiscale ( input.getCodiceFiscale () );
        current.setNome ( input.getNome () );
        current.setCognome ( input.getCognome () );
        current.setEmail ( input.getEmail () );
        current.setDataInizioValidita ( input.getDataInizioValidita () );
        current.setDataFineValidita ( input.getDataFineValidita () );

        EntityUtils.populateEditFields ( current );
        return current;
    }

    private boolean verificaSeInviareMailDisattivazioneAssociazione ( AssociazioneUtenteEnte assCurrent, AssociazioneUtenteEnte assPrecedente ) {
        if ( StringUtils.isEmpty ( assCurrent.getEmail () ) ) {
            return false;
        }

        if ( assPrecedente.getDataFineValidita () == null && assCurrent.getDataFineValidita () != null ) {
            return true;
        }

        if ( assPrecedente.getDataFineValidita () != null && assCurrent.getDataFineValidita () != null
                        && !assCurrent.getDataFineValidita ().equals ( assPrecedente.getDataFineValidita () ) ) {
            return true;
        }

        return false;
    }

    private boolean verificaSeInviareMailRiattivazioneAssociazione ( AssociazioneUtenteEnte assCurrent, AssociazioneUtenteEnte assPrecedente ) {
        if ( StringUtils.isEmpty ( assCurrent.getEmail () ) ) {
            return false;
        }

        if ( assPrecedente.getDataFineValidita () != null && assCurrent.getDataFineValidita () == null ) {
            // l'utente e' stato riattivato
            return true;
        }

        return false;
    }
}
