/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaymodric.business.epaymodric.manager.ListaDiCaricoManager;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTListaDiCarico;
import it.csi.epay.epaymodric.business.epaymodric.repository.EnteRepository;
import it.csi.epay.epaymodric.business.epaymodric.repository.ListaDiCaricoRepository;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSalvaListaDiCarico;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;

/**
 *
 */
@Service
public class ListaDiCaricoManagerImpl implements ListaDiCaricoManager {

    //Nuru
    @Autowired
    EnteRepository enteRepository;

    @Autowired
    ListaDiCaricoRepository listaDiCaricoRepository;
    // fine Nuru
    @Transactional ( rollbackFor = Exception.class )
    @Override
    public DTOOutputWsEsito salvaListaDiCarico ( DTOInputWsSalvaListaDiCarico input ) throws EpaymodricException, Exception, UnrecoverableException {
        DTOOutputWsEsito esito = new DTOOutputWsEsito ();
        CblTListaDiCarico cblTListaDiCarico = new CblTListaDiCarico ();
        String codiceFiscaleEnte = "";
        //Long id = null;
        CblTEnte cblTEnte = new CblTEnte ();
        try {
            if ( null == input ) {
                throw new EpaymodricException ( "Input non valorizzato" );
            }
            codiceFiscaleEnte = input.getCaller ().getPrincipal ().getCodiceFiscale ();
            cblTEnte = enteRepository.findByCodiceFiscale ( codiceFiscaleEnte );
            if ( null != cblTEnte ) {
                cblTListaDiCarico.setIdEnte ( cblTEnte.getIdEnte () );
            } else {
                throw new EpaymodricException ( "Errore durante il settaggio del campo idEnte" );
            }

            if ( !StringUtils.isEmpty ( input.getCodiceVersamento () ) ) {
                cblTListaDiCarico.setCodiceVersamento ( input.getCodiceVersamento () );//codice_versamento
            } else {
                throw new EpaymodricException ( "campo codiceVersamento non valorizzato" );
            }
            if ( !StringUtils.isEmpty ( input.getDatiSpecificiRiscossione () ) ) {
                cblTListaDiCarico.setDatiSpecificiRiscossione ( input.getDatiSpecificiRiscossione () );//dati_specifici_riscossione
            } else {
                throw new EpaymodricException ( "campo datiSpecificiRiscossione non valorizzato " );

            }
            if ( null != input.getAnnoEsercizio () ) {
                cblTListaDiCarico.setAnnoEsercizio ( input.getAnnoEsercizio () );//anno_esercizio
            } else {

                throw new EpaymodricException ( "campo annoEsercizio non valorizzato " );

            }
            if ( null != input.getAccertamentoAnno () ) {
                cblTListaDiCarico.setAccertamentoAnno ( input.getAccertamentoAnno () );//accertamento_anno
            } else {

                throw new EpaymodricException ( "campo accertamentoAnno non valorizzato " );

            }
            if ( null != input.getAccertamentoNro () ) {
                cblTListaDiCarico.setAccertamentoNro ( input.getAccertamentoNro () );//accertamento_nro
            } else {

                throw new EpaymodricException ( "campo accertamentoNro non valorizzato " );

            }
            if ( !StringUtils.isEmpty ( input.getDescrizioneCausale () ) ) {
                cblTListaDiCarico.setDescrizioneCausale ( input.getDescrizioneCausale () );//descrizione_causale
            } else {

                throw new EpaymodricException ( "campo descrizioneCausale non valorizzato" );

            }
            if ( null != input.getRifPosizioneDebitoria () ) {
                cblTListaDiCarico.setRifPosizioneDebitoria ( input.getRifPosizioneDebitoria () );//rif_posizione_debitoria
            } else {

                throw new EpaymodricException ( "campo rifPosizioneDebitoria non valorizzato" );

            }
            if ( null != input.getImportoVersamento () ) {
                cblTListaDiCarico.setImportoVersamento ( input.getImportoVersamento () );//importo_versamento
            } else {

                throw new EpaymodricException ( "campo importoVersamento non valorizzato" );

            }
            if ( null != input.getDataInizioValidita () ) {
                cblTListaDiCarico.setDataInizioValidita ( DateUtils.dateToTimestamp ( input.getDataInizioValidita () ) );//data_inizio_validita
            } else {

                throw new EpaymodricException ( "campo dataInizioValidita non valorizzato" );

            }
            if ( null != input.getCapitolo () ) {
                cblTListaDiCarico.setCapitolo ( input.getCapitolo ()  );//capitolo
            } else {

                throw new EpaymodricException ( "campo capitolo non valorizzato" );

            }
            if ( null != input.getArticolo () ) {
                cblTListaDiCarico.setArticolo ( BigDecimal.valueOf ( input.getArticolo () ) );//articolo
            } else {

                throw new EpaymodricException ( "campo capitolo non valorizzato articolo" );

            }
            if ( !StringUtils.isEmpty ( input.getPianoDeiConti () ) ) {
                cblTListaDiCarico.setPianoDeiConti ( input.getPianoDeiConti () );//piano_dei_conti
            } else {

                throw new EpaymodricException ( "campo pianoDeiConti non valorizzato " );

            }

            List<CblTListaDiCarico> list
            = listaDiCaricoRepository.findAllByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizioOrderByDataFineValiditaDesc (
                cblTListaDiCarico.getIdEnte (),
                cblTListaDiCarico.getCodiceVersamento (), cblTListaDiCarico.getDatiSpecificiRiscossione (), cblTListaDiCarico.getAnnoEsercizio () );

            if ( list != null && list.size () > 0 ) {

                CblTListaDiCarico recordToUpdate = list.get ( 0 );

                if ( cblTListaDiCarico.getDataInizioValidita ().before ( recordToUpdate.getDataInizioValidita () )
                                || cblTListaDiCarico.getDataInizioValidita ().equals ( recordToUpdate.getDataInizioValidita () ) ) {

                    throw new EpaymodricException ( String.format (
                                    "Impossibile inserire. La data inizio validita' del record da inserire e' precedente alla data inizio validita del record attualmente attivo"  ) );
                }


                if ( recordToUpdate.getDataFineValidita () == null ) {

                    Timestamp dataInizio = cblTListaDiCarico.getDataInizioValidita ();

                    Calendar calendar = Calendar.getInstance ();

                    calendar.setTimeInMillis ( dataInizio.getTime () );

                    calendar.roll ( Calendar.DAY_OF_MONTH, -1 );

                    recordToUpdate.setDataFineValidita ( new Timestamp ( calendar.getTimeInMillis () ) );

                    listaDiCaricoRepository.save ( recordToUpdate );
                }

                else if ( cblTListaDiCarico.getDataInizioValidita ().before ( recordToUpdate.getDataFineValidita () )
                                || cblTListaDiCarico.getDataInizioValidita ().equals ( recordToUpdate.getDataFineValidita () ) ) {
                    throw new EpaymodricException ( String.format (
                                    "Impossibile inserire. La data inizio validita' del record da inserire e' precedente alla data fine validita del record attualmente attivo" ) );

                }
            }


            cblTListaDiCarico.setAttivo ( Boolean.TRUE );
            //Salvataggio nella tabella cbl_t_lista_di_carico
            listaDiCaricoRepository.save ( cblTListaDiCarico );
            esito.setCodiceErrore ( Costanti.RESPONSE_WS_OK );
            esito.setDescrizione ( "Operazione completata correttamente" );

        } catch ( EpaymodricException e ) {
            esito.setCodiceErrore ( CostantiErrori.WS_ESITO_KO_DEFAULT );
            esito.setDescrizione ( e.getMessage () );
            return esito;
        }

        return esito;
    }

    @Override
    public void invalidaListaDiCarico ( Long id ) {
        CblTListaDiCarico listaDiCarico = listaDiCaricoRepository.findOne ( id );

        listaDiCarico.setAttivo ( Boolean.FALSE );

        listaDiCaricoRepository.save ( listaDiCarico );

    }

}
