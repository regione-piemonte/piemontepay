/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaywsosrv.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epaywsosrv.exception.coopPec.CoopPecException;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.AggiornaEnteRequest;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.CodiceVersamentoType;
import it.csi.epay.epaywsosrv.facade.cooppec.dto.RiferimentoContabileType;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTCodiceVersamento;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEnte;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTCodiceVersamentoTemp;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTEnteTemp;


public interface TempUtility {

    public static enum ModalitaAcquisizioneProvvisori {

            CAM ( 4 ),
            CON ( 1 );

        public static ModalitaAcquisizioneProvvisori fromValue ( String v ) {
            return valueOf ( v );
        }

        private Integer modalitaAcquisizioneProv;

        /**
         * @param modalitaAcquisizioneProvvisori
         */
        private ModalitaAcquisizioneProvvisori ( Integer modalitaAcquisizioneProv ) {
            this.modalitaAcquisizioneProv = modalitaAcquisizioneProv;
        }

        public Integer value () {
            return modalitaAcquisizioneProv;
        }

    }

    public static enum PeriodicitaSchedulazione {

            NES ( 0 ),
            SGF ( 99 ),
            GIO ( 1 ),
            SET ( 2 ),
            MEN ( 3 ),
            BIM ( 4 ),
            TRI ( 5 ),
            QUA ( 6 ),
            SEM ( 7 ),
            ANN ( 8 );

        public static PeriodicitaSchedulazione fromValue ( String v ) {
            return valueOf ( v );
        }

        private Integer periodicitaSchedulazione;

        /**
         * @param periodicitaSchedulazione
         */
        private PeriodicitaSchedulazione ( Integer periodicitaSchedulazione ) {
            this.periodicitaSchedulazione = periodicitaSchedulazione;
        }

        public Integer value () {
            return periodicitaSchedulazione;
        }
    }

    static final String AZIONE_MODIFICA = "M";

    static final String AZIONE_INSERIMENTO = "I";

    static final String AZIONE_CANCELLAZIONE = "C";

    public static EPaywsoTEnte buildEnte ( EPaywsoTEnteTemp enteTemp ) {
        EPaywsoTEnte ente = new EPaywsoTEnte ();
        ente.setCodFiscaleEnte ( enteTemp.getCodFiscaleEnte () );

        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
        //ente.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
        
        ente.setDenominazione ( enteTemp.getDenominazione () );
        //ente.setEmail ( enteTemp.getEmail () );

        ente.setDtInizioValidita( new Timestamp ( new Date ().getTime () ) );
        ente.setDtFineValidita( null );
        
        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
        //ente.setEntePlurintermediato ( enteTemp.getEntePlurintermediato () );
        //ente.setFlagAccertamento ( enteTemp.getFlagAccertamento () );
        //ente.setFlagRicezioneErrori ( enteTemp.getFlagRicezioneErrori () );
        //ente.setFlagRiconciliazione ( enteTemp.getFlagRiconciliazione () );
        //ente.setGiornoSchedulazione ( enteTemp.getGiornoSchedulazione () );
        //ente.setIbanTesoreria ( enteTemp.getIbanTesoreria () );
        
        //ente.setIdEnte ( enteTemp.getIdEnte () );

        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
        //ente.setModalitaAcquisizioneProvvisori ( enteTemp.getModalitaAcquisizioneProvvisori () );
        //ente.setPeriodicitaSchedulazione ( enteTemp.getPeriodicitaSchedulazione () );
        
        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
        //TODO modificare dopo la logica di Fabio.
        //ente.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
        
        return ente;
    }

    public static EPaywsoTEnteTemp buildEnteTemp ( AggiornaEnteRequest aggiornaEnteRequest ) {
        EPaywsoTEnteTemp enteTemp = new EPaywsoTEnteTemp ();
        enteTemp.setIdOperazione ( aggiornaEnteRequest.getIdOperazione () );
        enteTemp.setCodFiscaleEnte ( aggiornaEnteRequest.getEnte ().getCodiceFiscale () );
        // - Tracciatura - enteTemp.setDataInserimento ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
        // - Tracciatura - enteTemp.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
        enteTemp.setDenominazione ( aggiornaEnteRequest.getEnte ().getDenominazione () );
        
        enteTemp.setDtInizioValidita( new Timestamp ( new Date().getTime () ) );
        enteTemp.setDtFineValidita( null );
        
        //enteTemp.setEmail ( aggiornaEnteRequest.getEnte ().getEmail () );
        
        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
        //enteTemp.setEntePlurintermediato ( aggiornaEnteRequest.getEnte ().isEntePlurintermediato () );
        //enteTemp.setFlagAccertamento ( aggiornaEnteRequest.getEnte ().isAccertamento () );
        //enteTemp.setFlagRicezioneErrori ( aggiornaEnteRequest.getEnte ().isRicezioneErrori () );
        //enteTemp.setFlagRiconciliazione ( aggiornaEnteRequest.getEnte ().isRiconciliazioneVersamenti () );
        //enteTemp.setGiornoSchedulazione ( aggiornaEnteRequest.getEnte ().getGiornoSchedulazione () );
        //enteTemp.setIbanTesoreria ( aggiornaEnteRequest.getEnte ().getIban () );
        
        //        enteTemp.setModalitaAcquisizioneProvvisori (
        //            ( contains ( ModalitaAcquisizioneProvvisori.class,
        //                ( null != aggiornaEnteRequest.getEnte ().getModalitaAcquisizioneProvvisori () )
        //                                ? aggiornaEnteRequest.getEnte ().getModalitaAcquisizioneProvvisori ().name () : null ) )
        //                                                ? ModalitaAcquisizioneProvvisori
        //                                                    .valueOf ( aggiornaEnteRequest.getEnte ().getModalitaAcquisizioneProvvisori ().name () )
        //                                                    .value ()
        //                                                : null );
        //        enteTemp.setPeriodicitaSchedulazione (
        //            ( contains ( PeriodicitaSchedulazione.class,
        //                null != aggiornaEnteRequest.getEnte ().getPeriodicitaSchedulazioneRiconciliazione ()
        //                                ? aggiornaEnteRequest.getEnte ().getPeriodicitaSchedulazioneRiconciliazione ().name () : null ) )
        //                                                ? PeriodicitaSchedulazione
        //                                                    .valueOf ( aggiornaEnteRequest.getEnte ().getPeriodicitaSchedulazioneRiconciliazione ().name () ).value ()
        //                                                : PeriodicitaSchedulazione.NES.value () );
                
        //:TODO modificare dopo la logica di Fabio.
        // - Tracciatura - enteTemp.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        // - Tracciatura - enteTemp.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
        return enteTemp;
    }

    static void checkDatiObbligatori ( CodiceVersamentoType codiceVersamento, String azione, String idOperazione ) throws CoopPecException {

        if ( !StringUtils.isNotBlank ( azione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Azione non presente" ) );
        }
        if ( !StringUtils.isNotBlank ( idOperazione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Id operazione non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( codiceVersamento.getCFEnte () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice fiscale ente non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( codiceVersamento.getCodice () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice versamento non presente" ) );
        }
    }

    static void checkDatiObbligatori ( RiferimentoContabileType riferimentoContabile, String azione, String idOperazione ) throws CoopPecException {

        if ( !StringUtils.isNotBlank ( azione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Azione non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( idOperazione ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Id operazione non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getCodiceFiscaleEnte () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice fiscale ente non presente" ) );
        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getCodiceVersamento () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice versamento non presente" ) );
        }

        //chiedere a Piero e lollo.
        //        if ( null != riferimentoContabile.getNumeroCapitolo () ) {
        //            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
        //                String.format ( "Capitolo non presente" ) );
        //        }

        //        if ( null != riferimentoContabile.getNumeroArticolo () ) {
        //            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
        //                String.format ( "Articolo non presente" ) );
        //        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getDatoSpecificoRiscossione () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Dati specifici riscossione non presenti" ) );
        }

        if ( !StringUtils.isNotBlank ( riferimentoContabile.getLivelloPDC () ) ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Livello pdc non presente" ) );
        }
    }

    static <E extends Enum<E>> boolean contains ( Class<E> enumClass, String value ) {
        try {
            return EnumSet.allOf ( enumClass )
                .contains ( Enum.valueOf ( enumClass, value ) );
        } catch ( Exception e ) {
            return false;
        }
    }

    public static List<EPaywsoTCodiceVersamentoTemp> creaCodiciVersamento ( EPaywsoTEnteTemp enteTemp, List<String> codiciRiferimento ) {
        List<EPaywsoTCodiceVersamentoTemp> codiceVersamentoList = new ArrayList<> ();
        for ( String codiceRiferimento: codiciRiferimento ) {
            EPaywsoTCodiceVersamentoTemp codiceVersamentoTemp = new EPaywsoTCodiceVersamentoTemp ();
            
            codiceVersamentoTemp.setIdOperazione ( enteTemp.getIdOperazione () );
            //codiceVersamentoTemp.setIdEnte ( enteTemp.getIdEnte () );
            codiceVersamentoTemp.setAzione ( AZIONE_MODIFICA );
            
            codiceVersamentoTemp.setDtInizioValidita( enteTemp.getDtInizioValidita () );
            codiceVersamentoTemp.setDtFineValidita( enteTemp.getDtFineValidita() );
                        
            // - Tracciatura - codiceVersamentoTemp.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
            // - Tracciatura - codiceVersamentoTemp.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
            // - Tracciatura - codiceVersamentoTemp.setDataInserimento ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
            // - Tracciatura - codiceVersamentoTemp.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
            codiceVersamentoTemp.setCodVersamento ( codiceRiferimento );
            codiceVersamentoTemp.setCodFiscaleEnte ( enteTemp.getCodFiscaleEnte () );
            codiceVersamentoTemp.setDescrizione ( enteTemp.getDenominazione () );
            
            codiceVersamentoTemp.setCodiceModalitaIntegrazione ( null );
            
            codiceVersamentoList.add ( codiceVersamentoTemp );
        }
        return codiceVersamentoList;
    }

    static EPaywsoTCodiceVersamentoTemp createCodiceVersamento ( EPaywsoTEnte enteEntity, CodiceVersamentoType codiceVersamento, String azione, String idOperazione )
                    throws CoopPecException {
        EPaywsoTCodiceVersamentoTemp entity = new EPaywsoTCodiceVersamentoTemp ();

        checkDatiObbligatori ( codiceVersamento, azione, idOperazione );

        if ( null == enteEntity ) {
            throw new CoopPecException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Ente non trovato per il codice fiscale %s", codiceVersamento.getCFEnte () ) );
        }

        entity.setCodFiscaleEnte ( codiceVersamento.getCFEnte () );
        entity.setCodVersamento ( codiceVersamento.getCodice () );
        entity.setDescrizione ( codiceVersamento.getDescrizione () );

        entity.setDtInizioValidita( new Timestamp ( new Date().getTime () ) );
        entity.setDtFineValidita( null );
        
        // - Tracciatura - entity.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );
        // - Tracciatura - entity.setDataModifica ( entity.getDataInserimento () );
        entity.setCodFiscaleEnte ( enteEntity.getCodFiscaleEnte () );
        entity.setIdOperazione ( idOperazione );

        // - Tracciatura - entity.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        // - Tracciatura - entity.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );

        if(codiceVersamento.getModalitaDiIntegrazione () != null) {
            entity.setCodiceModalitaIntegrazione ( codiceVersamento.getModalitaDiIntegrazione ().value ());
        }
        
        entity.setAzione ( azione );

        return entity;
    }

    public static List<EPaywsoTCodiceVersamento> elaboraCodiciVersamento ( List<EPaywsoTCodiceVersamento> codiceVersamentoList,
        List<EPaywsoTCodiceVersamentoTemp> codiceVersamentoTempList ) {
        List<EPaywsoTCodiceVersamento> codiceVersamentoListcatalogsToUpdate = new ArrayList<> ();
        if ( null != codiceVersamentoList && !codiceVersamentoList.isEmpty () ) {
            //se ho codice versamento per l'ente in questione procedo con la logica di decisione della dataFineValidita'.
            //confronto e aggiornamento
            if ( null != codiceVersamentoTempList && !codiceVersamentoTempList.isEmpty () ) {
                //Se mi vengono passati codici versamnto vado a confrontarli con quelli presenti lato DB in seguito valizzo o meno la dataFineVal.
                for ( EPaywsoTCodiceVersamento codiceVersamento: codiceVersamentoList ) {
                    Boolean trovato = false;
                    for ( EPaywsoTCodiceVersamentoTemp codiceVersamentoTemp: codiceVersamentoTempList ) {
                        if ( codiceVersamentoTemp.compareTo ( codiceVersamento ) == 0 ) {
                            //Se trovo una corrispondenza nel dubbio elimino la data fine validita'
                            trovato = true;
                            //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                            //La data fine validita' non c'e' come campo per cui questo test non deve essere eseguito
                            //if ( null != codiceVersamento.getDataFineValidita () ) {
                                //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                                //codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
                                //codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                                //codiceVersamento.setDataFineValidita ( null );
                            //    codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
                            //}
                            break;
                        }
                    }
                    if ( !trovato ) {
                        //se non trovato significa che devo chiudere il recordo presente sul DB valorizzando la data fine validita'
                        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                        //codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
                        //codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                        //codiceVersamento.setDataFineValidita ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                        codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
                    }
                }
            } else {
                //datafineval current a tutte
                for ( EPaywsoTCodiceVersamento codiceVersamento: codiceVersamentoList ) {
                    //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
                    //codiceVersamento.setDataFineValidita ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                    //codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
                    //codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                    codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
                }
            }
        } else {
            //Se lato DB l'ente in questione non ha codiciVersamento
            if ( ( null == codiceVersamentoTempList || codiceVersamentoTempList.isEmpty () ) == false ) {
                throw new IllegalArgumentException ( "Codici Versamento non censiti." );
            }
            //nel caso abbia ricorrenze passate in input lancio un errore in quanto non posso inserire su DB.

        }
        return codiceVersamentoListcatalogsToUpdate;
    }

    static EPaywsoTCodiceVersamento mapCblTCodiceVersamentoSenzaIdEnte ( EPaywsoTCodiceVersamento codiceVersamento, EPaywsoTCodiceVersamentoTemp codiceVersamentoTemp ) {

        //TODO Campo non previsto nell'entity ma previsto in cooppec -> IGNORATO
        //codiceVersamento.setCodiceFiscaleEnte ( codiceVersamentoTemp.getCodiceFiscaleEnte () );
        codiceVersamento.setDtFineValidita ( codiceVersamentoTemp.getDtFineValidita () );
        codiceVersamento.setDtInizioValidita ( codiceVersamentoTemp.getDtInizioValidita () );
        //codiceVersamento.setDataInserimento ( codiceVersamentoTemp.getDataInserimento () );
        //codiceVersamento.setDataModifica ( codiceVersamentoTemp.getDataModifica () );
        //codiceVersamento.setUtenteInserimento ( codiceVersamentoTemp.getUtenteInserimento () );
        //codiceVersamento.setUtenteModifica ( codiceVersamentoTemp.getUtenteModifica () );
        //codiceVersamento.setFlagAnnullato ( codiceVersamentoTemp.getFlagAnnullato () );
        
        codiceVersamento.setDescrizione( codiceVersamentoTemp.getDescrizione () );
        codiceVersamento.setCodVersamento ( codiceVersamentoTemp.getCodVersamento () );
        
        return codiceVersamento;
    }
}
