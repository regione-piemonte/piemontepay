/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import it.csi.epay.epaypacatalogsrv.dto.enums.AzioneDaPropagare;
import it.csi.epay.epaypacatalogsrv.dto.enums.EsitoPropagazione;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.EliminaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.EliminaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.exception.Wso2BroadcastingFailedException;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.repository.StatoAggiornamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.StoricoRiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = EliminaRiferimentoContabileInput.class, produces = EliminaRiferimentoContabileOutput.class )
@Component
public class EliminaRiferimentoContabileOperation implements OperationHandler<EliminaRiferimentoContabileInput, EliminaRiferimentoContabileOutput> {

    @Autowired
    private RiferimentoContabileRepository repository;

    @Autowired
    private StatoAggiornamentoRepository statoRepository;

    @Autowired
    private RiferimentoContabileService riferimentoContabileService;

    @Autowired
    private InvioMailService invioMailService;

    @Autowired
    private PropagazioneService propagazioneService;

    @Autowired
    private StoricoRiferimentoContabileRepository storicoRepository;

    @Override
    public void preValidateInput ( EliminaRiferimentoContabileInput input,
        OperationDispatchingContext<EliminaRiferimentoContabileInput, EliminaRiferimentoContabileOutput> context ) {

        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    public void authorize ( EliminaRiferimentoContabileInput input,
        OperationDispatchingContext<EliminaRiferimentoContabileInput, EliminaRiferimentoContabileOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.ELIMINA_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public void validateInput ( EliminaRiferimentoContabileInput input,
        OperationDispatchingContext<EliminaRiferimentoContabileInput, EliminaRiferimentoContabileOutput> context ) {

        RiferimentoContabile current = repository.findOne ( input.getId () );
        if ( current == null ) {
            throw new NotFoundException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NOT_FOUND );
        }


    }

    @Override
    @Transactional
    public EliminaRiferimentoContabileOutput execute ( EliminaRiferimentoContabileInput input,
        OperationDispatchingContext<EliminaRiferimentoContabileInput, EliminaRiferimentoContabileOutput> context ) {

        RiferimentoContabile corrente = repository.findOne ( input.getId () );

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( corrente.getCodiceVersamento () );

        if ( EntityUtils.isTrue ( corrente.getFlagAnnullato () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );
        }

        if ( !EntityUtils.isRiferimentoInVita ( corrente ) ) {
            throw new BadRequestException ( Constants.MESSAGES.RIFERIMENTO_CONTABILE_NON_IN_VITA );
        }

        // lazy loading
        corrente.getCodiceVersamento ().getEnte ().getDenominazione ();

        List<StoricoRiferimentoContabile> listaStorico = riferimentoContabileService.getStoricoPosizioniPrecedenti ( corrente );

        EsitoPropagazioneDTO risultatoPropagazioneParziale = null;

        if ( listaStorico.size () > 0 ) {
            StoricoRiferimentoContabile ultimaVoceStorico = listaStorico.get ( 0 );

            // rigenero la voce del riferimento a partire dallo storico
            RiferimentoContabile voceDaRipristinare = riferimentoContabileService.generaVoceDaStorico ( ultimaVoceStorico );
            EntityUtils.populateEditFields ( voceDaRipristinare );
            voceDaRipristinare.setDataFineValidita ( null );
            voceDaRipristinare.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
            voceDaRipristinare.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );
            voceDaRipristinare = repository.save ( voceDaRipristinare );

            // aggiorno gli storici delle modifiche per la voce che sto eliminando
            for ( StoricoRiferimentoContabile storicoModifica: ultimaVoceStorico.getStorico () ) {
                if ( !EntityUtils.isTrue ( storicoModifica.getFlagPosizionePrecedente () ) ) {
                    storicoModifica.setStoricoRiferimentoContabile ( null );
                    storicoModifica.setRiferimentoContabile ( voceDaRipristinare );
                    storicoRepository.save ( storicoModifica );
                }
            }

            // elimino corrispondente voce nello storico
            storicoRepository.delete ( ultimaVoceStorico );

            // propago la modifica
            risultatoPropagazioneParziale = propagazioneService.propagaRiferimentoContabile ( voceDaRipristinare, AzioneDaPropagare.MODIFICA );

            if ( risultatoPropagazioneParziale.getEsito () == EsitoPropagazione.KO ) {
                throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                    risultatoPropagazioneParziale.getMessaggio () );
            }

            voceDaRipristinare.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazioneParziale.getEsito ().name () ) );
            voceDaRipristinare.setDescrizioneErroreAggiornamento ( risultatoPropagazioneParziale.getMessaggio () );
            voceDaRipristinare = repository.save ( voceDaRipristinare );

            // se ci sono piu' voci nello storico, devo correggere i riferimenti delle altre
            // al nuovo riferimento contabile appena ripristinato

            if ( listaStorico.size () > 1 ) {
                for ( int i = 1; i < listaStorico.size (); i++ ) {
                    StoricoRiferimentoContabile daCorreggere = listaStorico.get ( i );
                    daCorreggere.setRiferimentoContabile ( voceDaRipristinare );
                    daCorreggere.setStoricoRiferimentoContabile ( null );
                    storicoRepository.save ( daCorreggere );
                }
            }
        }

        EntityUtils.populateEditFields ( corrente );
        corrente.setStatoAggiornamento ( statoRepository.findOneByCodice ( StatoAggiornamentoRepository.CODICE_DEFAULT ) );
        corrente.setDescrizioneErroreAggiornamento ( "In attesa di propagazione" );
        corrente.setFlagAnnullato ( true );
        repository.save ( corrente );

        EsitoPropagazioneDTO risultatoPropagazione;

        if ( risultatoPropagazioneParziale != null ) {
            risultatoPropagazione = propagazioneService.propagaRiferimentoContabile (
                corrente,
                AzioneDaPropagare.ELIMINAZIONE,
                risultatoPropagazioneParziale.getIdTransazione (),
                true );
        } else {
            risultatoPropagazione = propagazioneService.propagaRiferimentoContabile ( corrente, AzioneDaPropagare.ELIMINAZIONE );
        }

        if ( risultatoPropagazione.getEsito () == EsitoPropagazione.KO ) {
            throw new Wso2BroadcastingFailedException ( Constants.MESSAGES.WSO2_BROADCASTING_FAILED,
                risultatoPropagazione.getMessaggio () );
        }

        corrente.setStatoAggiornamento ( statoRepository.findOneByCodice ( risultatoPropagazione.getEsito ().name () ) );
        corrente.setDescrizioneErroreAggiornamento ( risultatoPropagazione.getMessaggio () );
        repository.save ( corrente );
        repository.flush ();

        // Inserito controllo sullo stato per evitare spam alla casella di posta del servizio.
        if ( risultatoPropagazione.getEsito () != EsitoPropagazione.OK ) {
            inviaMail ( corrente );
        }
        
        return EliminaRiferimentoContabileOutput.ok ();
    }

    private void inviaMail ( RiferimentoContabile entity ) {
        Map<String, String> parametri = new HashMap<> ();

        parametri.put ( "stato_aggiornamento", entity.getDescrizioneEsitoAggiornamento () );

        parametri.put ( "azione_oggetto", "Cancellazione" );
        parametri.put ( "azione", "cancellato" );
        parametri.put ( "descrizione_ente", entity.getCodiceVersamento ().getEnte ().getDenominazione () );
        parametri.put ( "descrizione_codice_versamento", entity.getCodiceVersamento ().getCodice () + " - " + entity.getCodiceVersamento ().getDescrizione () );
        parametri.put ( "data_inizio_validita", new SimpleDateFormat ( "dd/MM/yyyy" ).format ( entity.getDataInizioValidita () ) );

        invioMailService.inviaMail ( EmailEnum.RIFERIMENTO_CONTABILE,
            InvioMailServiceImpl.DEFAULT_DESTINATARIO,
            entity.getCodiceVersamento ().getEnte ().getId (),
            parametri );
    }

}
