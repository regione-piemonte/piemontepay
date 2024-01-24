/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogo;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogoTemp;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamento;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamentoTemp;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnteTemp;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.interfacews.epaymodric.consts.CostantiErrori;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.CodiceVersamentoType;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.RiferimentoContabileType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;


/**
 *
 */
public interface TempUtility {

    public static enum ModalitaAcquisizioneProvvisori {

        CAM ( 4 ),
        CON ( 1 ),
        NES ( 0 );

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

    public static CblTEnte buildEnte ( CblTEnteTemp enteTemp ) {
        CblTEnte ente = new CblTEnte ();
        ente.setCodiceFiscale ( enteTemp.getCodiceFiscale () );
        ente.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
        ente.setDenominazione ( enteTemp.getDenominazione () );
        ente.setEmailEnte ( enteTemp.getEmailEnte () );
        ente.setEntePlurintermediato ( enteTemp.getEntePlurintermediato () );
        ente.setFlagAccertamento ( enteTemp.getFlagAccertamento () );
        ente.setFlagRicezioneErrori ( enteTemp.getFlagRicezioneErrori () );
        ente.setFlagRiconciliazione ( enteTemp.getFlagRiconciliazione () );
        ente.setGiornoSchedulazione ( enteTemp.getGiornoSchedulazione () );
        ente.setIbanTesoreria ( enteTemp.getIbanTesoreria () );
        ente.setIdEnte ( enteTemp.getIdEnte () );

        ente.setModalitaAcquisizioneProvvisori ( enteTemp.getModalitaAcquisizioneProvvisori () );
        ente.setPeriodicitaSchedulazione ( enteTemp.getPeriodicitaSchedulazione () );
        //:TODO modificare dopo la logica di Fabio.
        ente.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
        return ente;
    }

    public static CblTEnteTemp buildEnteTemp ( AggiornaEnteRequest aggiornaEnteRequest ) {
        CblTEnteTemp enteTemp = new CblTEnteTemp ();
        enteTemp.setIdOperazione ( aggiornaEnteRequest.getIdOperazione () );
        enteTemp.setCodiceFiscale ( aggiornaEnteRequest.getEnte ().getCodiceFiscale () );
        enteTemp.setDataInserimento ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
        enteTemp.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
        enteTemp.setDenominazione ( aggiornaEnteRequest.getEnte ().getDenominazione () );
        enteTemp.setEmailEnte ( aggiornaEnteRequest.getEnte ().getEmail () );
        enteTemp.setEntePlurintermediato ( aggiornaEnteRequest.getEnte ().isEntePlurintermediato () );
        enteTemp.setFlagAccertamento ( aggiornaEnteRequest.getEnte ().isAccertamento () );
        enteTemp.setFlagRicezioneErrori ( aggiornaEnteRequest.getEnte ().isRicezioneErrori () );
        enteTemp.setFlagRiconciliazione ( aggiornaEnteRequest.getEnte ().isRiconciliazioneVersamenti () );
        enteTemp.setGiornoSchedulazione ( aggiornaEnteRequest.getEnte ().getGiornoSchedulazione () );
        enteTemp.setIbanTesoreria ( aggiornaEnteRequest.getEnte ().getIban () );

        enteTemp.setModalitaAcquisizioneProvvisori (
            ( contains ( ModalitaAcquisizioneProvvisori.class,
                ( null != aggiornaEnteRequest.getEnte ().getModalitaAcquisizioneProvvisori () )
                ? aggiornaEnteRequest.getEnte ().getModalitaAcquisizioneProvvisori ().name () : null ) )
            ? ModalitaAcquisizioneProvvisori
                            .valueOf ( aggiornaEnteRequest.getEnte ().getModalitaAcquisizioneProvvisori ().name () )
                            .value ()
                            : null );
        enteTemp.setPeriodicitaSchedulazione (
            ( contains ( PeriodicitaSchedulazione.class,
                null != aggiornaEnteRequest.getEnte ().getPeriodicitaSchedulazioneRiconciliazione ()
                ? aggiornaEnteRequest.getEnte ().getPeriodicitaSchedulazioneRiconciliazione ().name () : null ) )
            ? PeriodicitaSchedulazione
                            .valueOf ( aggiornaEnteRequest.getEnte ().getPeriodicitaSchedulazioneRiconciliazione ().name () ).value ()
                            : PeriodicitaSchedulazione.NES.value () );
        //:TODO modificare dopo la logica di Fabio.
        enteTemp.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        enteTemp.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
        return enteTemp;
    }

    static void checkDatiObbligatori ( CodiceVersamentoType codiceVersamento, String azione, String idOperazione ) throws EpaymodricException {

        if ( !StringUtils.hasText ( azione ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Azione non presente" ) );
        }
        if ( !StringUtils.hasText ( idOperazione ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Id operazione non presente" ) );
        }

        if ( !StringUtils.hasText ( codiceVersamento.getCFEnte () ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice fiscale ente non presente" ) );
        }

        if ( !StringUtils.hasText ( codiceVersamento.getCodice () ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice versamento non presente" ) );
        }
    }

    static void checkDatiObbligatori ( RiferimentoContabileType riferimentoContabile, String azione, String idOperazione ) throws EpaymodricException {

        if ( !StringUtils.hasText ( azione ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Azione non presente" ) );
        }

        if ( !StringUtils.hasText ( idOperazione ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Id operazione non presente" ) );
        }

        if ( !StringUtils.hasText ( riferimentoContabile.getCodiceFiscaleEnte () ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Codice fiscale ente non presente" ) );
        }

        if ( !StringUtils.hasText ( riferimentoContabile.getCodiceVersamento () ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
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

        if ( !StringUtils.hasText ( riferimentoContabile.getDatoSpecificoRiscossione () ) ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Dati specifici riscossione non presenti" ) );
        }

        // RDI-41 Campo non piu' obbligatorio
//        if ( !StringUtils.hasText ( riferimentoContabile.getLivelloPDC () ) ) {
//            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
//                String.format ( "Livello pdc non presente" ) );
//        }
    }

    static <E extends Enum<E>> boolean contains ( Class<E> enumClass, String value ) {
        try {
            return EnumSet.allOf ( enumClass )
                            .contains ( Enum.valueOf ( enumClass, value ) );
        } catch ( Exception e ) {
            return false;
        }
    }

    public static List<CblTCodiceVersamentoTemp> creaCodiciVersamento( CblTEnteTemp enteTemp, List<String> codiciRiferimento ) {
        List<CblTCodiceVersamentoTemp> codiceVersamentoList = new ArrayList<> ();
        for ( String codiceRiferimento: codiciRiferimento ) {
            CblTCodiceVersamentoTemp codiceVersamentoTemp = new CblTCodiceVersamentoTemp ();
            codiceVersamentoTemp.setIdOperazione ( enteTemp.getIdOperazione () );
            codiceVersamentoTemp.setIdEnte ( enteTemp.getIdEnte () );
            codiceVersamentoTemp.setAzione ( AZIONE_MODIFICA );
            codiceVersamentoTemp.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
            codiceVersamentoTemp.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
            codiceVersamentoTemp.setDataInserimento ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
            codiceVersamentoTemp.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
            codiceVersamentoTemp.setCodiceVersamento ( codiceRiferimento );
            codiceVersamentoTemp.setCodiceFiscaleEnte ( enteTemp.getCodiceFiscale () );
            codiceVersamentoTemp.setCodiceModalitaIntegrazione ( null );
            codiceVersamentoList.add ( codiceVersamentoTemp );
        }
        return codiceVersamentoList;
    }

    static CblTCatalogoTemp createCatalogoTemp ( CblTEnte enteEntity, CodiceVersamentoType codiceVersamento, String azione, String idOperazione )
                    throws EpaymodricException {
        CblTCatalogoTemp entity = new CblTCatalogoTemp ();

        checkDatiObbligatori ( codiceVersamento, azione, idOperazione );

        if ( null == enteEntity ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Ente non trovato per il codice fiscale %s", codiceVersamento.getCFEnte () ) );
        }

        entity.setCodiceFiscaleEnte ( StringUtils.hasText ( codiceVersamento.getCFEnte () ) ? codiceVersamento.getCFEnte () : enteEntity.getCodiceFiscale () );
        entity.setCodiceVersamento ( codiceVersamento.getCodice () );
        entity.setDescrizioneVersamento ( codiceVersamento.getDescrizione () );

        entity.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );
        entity.setDataModifica ( entity.getDataInserimento () );
        entity.setIdEnte ( enteEntity.getIdEnte () );
        entity.setIdOperazione ( idOperazione );

        entity.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        entity.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );

        entity.setAzione ( azione );

        return entity;
    }

    static CblTCodiceVersamentoTemp createCodiceVersamento ( CblTEnte enteEntity, CodiceVersamentoType codiceVersamento, String azione, String idOperazione )
                    throws EpaymodricException {
        CblTCodiceVersamentoTemp entity = new CblTCodiceVersamentoTemp ();

        checkDatiObbligatori ( codiceVersamento, azione, idOperazione );

        if ( null == enteEntity ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Ente non trovato per il codice fiscale %s", codiceVersamento.getCFEnte () ) );
        }

        entity.setCodiceFiscaleEnte ( codiceVersamento.getCFEnte () );
        entity.setCodiceVersamento ( codiceVersamento.getCodice () );
        entity.setDescrizioneVersamento ( codiceVersamento.getDescrizione () );

        entity.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );
        entity.setDataModifica ( entity.getDataInserimento () );
        entity.setIdEnte ( enteEntity.getIdEnte () );
        entity.setIdOperazione ( idOperazione );
        entity.setFattura ( codiceVersamento.isFattura () );

        if(codiceVersamento.getModalitaDiIntegrazione () != null) {
            entity.setCodiceModalitaIntegrazione ( codiceVersamento.getModalitaDiIntegrazione ().value () );
        }

        entity.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        entity.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );

        entity.setAzione ( azione );

        return entity;
    }

    static CblTCatalogoTemp createRiferimentoContabile ( CblTEnte enteEntity, RiferimentoContabileType riferimentoContabile, String azione,
        String idOperazione )
                    throws EpaymodricException {
        CblTCatalogoTemp entity = new CblTCatalogoTemp ();
        checkDatiObbligatori ( riferimentoContabile, azione, idOperazione );

        if ( null == enteEntity ) {
            throw new EpaymodricException ( Integer.valueOf ( CostantiErrori.ERRORE_DATI_MANCANTI ),
                String.format ( "Ente non trovato per il codice fiscale %s", riferimentoContabile.getCodiceFiscaleEnte () ) );
        }

        entity.setChiaveIntersistema ( riferimentoContabile.getChiaveIntersistema () );
        entity.setAccertamentoAnno ( riferimentoContabile.getAnnoAccertamento () );
        entity.setAccertamentoDesc ( "" );
        entity.setAccertamentoNro ( riferimentoContabile.getNumeroAccertamento () );
        entity.setAnnoEsercizio ( riferimentoContabile.getAnnoEsercizio () );

        if ( null != riferimentoContabile.getNumeroArticolo () ) {
            entity.setArticolo ( new BigDecimal ( riferimentoContabile.getNumeroArticolo () ) );
        }

        entity.setAzione ( azione );

        if ( null != riferimentoContabile.getNumeroCapitolo () ) {
            entity.setCapitolo ( riferimentoContabile.getNumeroCapitolo () );
        }

        if ( null != riferimentoContabile.getCodiceCategoria () ) {
            entity.setCategoria ( riferimentoContabile.getCodiceCategoria () );
        }

        entity.setCodiceFiscaleEnte ( StringUtils.hasText ( riferimentoContabile.getCodiceFiscaleEnte () ) ? riferimentoContabile.getCodiceFiscaleEnte ()
                        : enteEntity.getCodiceFiscale () );
        entity.setCodiceVersamento ( riferimentoContabile.getCodiceVersamento () );
        entity.setDataInizioValidita ( DateUtils.xmlGregorianCalendarToTimestamp ( riferimentoContabile.getDataInizioValidita () ) );
        entity.setDataFineValidita ( DateUtils.xmlGregorianCalendarToTimestamp ( riferimentoContabile.getDataFineValidita () ) );
        entity.setDataInserimento ( new Timestamp ( System.currentTimeMillis () ) );
        entity.setDataModifica ( entity.getDataInserimento () );
        entity.setDatiSpecificiRiscossione ( riferimentoContabile.getDatoSpecificoRiscossione () );
        entity.setIdEnte ( enteEntity.getIdEnte () );
        entity.setIdOperazione ( idOperazione );
        if ( null != riferimentoContabile.getLivelloPDC () ) {
            entity.setPianoDeiConti ( riferimentoContabile.getLivelloPDC () );
        }
        entity.setSottovoce ( riferimentoContabile.getDescrizioneDatoSpecificoRiscossione () );

        if ( null != riferimentoContabile.getCodiceTipologia () ) {
            entity.setTipologia ( riferimentoContabile.getCodiceTipologia () );
        }

        entity.setTitolo ( riferimentoContabile.getTitolo () );
        entity.setUtenteInserimento ( Costanti.DEFAULT_UTENTE_SISTEMA );
        entity.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
		entity.setCodiceTributo ( riferimentoContabile.getCodiceTributo () );

        return entity;
    }

    public static List<CblTCodiceVersamento> elaboraCodiciVersamento ( List<CblTCodiceVersamento> codiceVersamentoAttualiList,
        List<CblTCodiceVersamentoTemp> codiceVersamentoNuoviTempList ) {
        List<CblTCodiceVersamento> codiceVersamentoListcatalogsToUpdate = new ArrayList<> ();
        
        Logger logger = LogManager.getLogger (TempUtility.class);

        if ( null != codiceVersamentoAttualiList && !codiceVersamentoAttualiList.isEmpty () ) {
            //se ho codice versamento per l'ente in questione procedo con la logica di decisione della dataFineValidita'.
            //confronto e aggiornamento
            if ( null != codiceVersamentoNuoviTempList && !codiceVersamentoNuoviTempList.isEmpty () ) {
                //Se mi vengono passati codici versamnto vado a confrontarli con quelli presenti lato DB in seguito valizzo o meno la dataFineVal.
                
                for ( CblTCodiceVersamento codiceVersamento: codiceVersamentoAttualiList ) {
                    Boolean trovato = false;
                    for ( CblTCodiceVersamentoTemp codiceVersamentoNuovoTemp: codiceVersamentoNuoviTempList ) {

                        logger.debug ( "----------------------------------------------------------------------------------" );
                        logger.debug ( "Compare CV -> DB:" + codiceVersamento.getCodiceVersamento () + " VS - temp :" + codiceVersamentoNuovoTemp.getCodiceVersamento ());
                        logger.debug ( "Compare ID -> DB:" + codiceVersamento.getIdEnte ()           + " VS - temp :" + codiceVersamentoNuovoTemp.getIdEnte ());
                        
                        if ( codiceVersamento.compareTo ( codiceVersamentoNuovoTemp ) == 0 ) {
                            //Se trovo una corrispondenza nel dubbio elimino la data fine validita'.
                            trovato = true;
                            if ( null != codiceVersamento.getDataFineValidita () ) {
                                codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
                                codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                                codiceVersamento.setDataFineValidita ( null );
                                codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
                            }
                            break;
                        }
                        
                        logger.debug ( "FOUND IS " + trovato );
                        logger.debug ( "----------------------------------------------------------------------------------" );
                    }
                    if ( !trovato ) {
                        //se non trovato significa che devo chiudere il recordo presente sul DB valorizzando la data fine validita'
                        codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
                        codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                        codiceVersamento.setDataFineValidita ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                        codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
                    }
                }
            } else {
                //datafineval current a tutte
                for ( CblTCodiceVersamento codiceVersamento: codiceVersamentoAttualiList ) {
                    codiceVersamento.setDataFineValidita ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                    codiceVersamento.setUtenteModifica ( Costanti.DEFAULT_UTENTE_SISTEMA );
                    codiceVersamento.setDataModifica ( new Timestamp ( Calendar.getInstance ().getTime ().getTime () ) );
                    codiceVersamentoListcatalogsToUpdate.add ( codiceVersamento );
                }
            }
        } else {
            //Se lato DB l'ente in questione non ha codiciVersamento
            Assert.isTrue ( null == codiceVersamentoNuoviTempList || codiceVersamentoNuoviTempList.isEmpty (), "Codici Versamento non censiti." );
            //nel caso abbia ricorrenze passate in input lancio un errore in quanto non posso inserire su DB.

        }
        return codiceVersamentoListcatalogsToUpdate;
    }

    static CblTCatalogo mapCatalogo ( CblTCatalogo catalogo, CblTCatalogoTemp catalogoTemp ) {
		catalogo.setCodiceTributo ( catalogoTemp.getCodiceTributo() );
        catalogo.setCodiceVersamento ( catalogoTemp.getCodiceVersamento () );
        catalogo.setDescrizioneVersamento ( catalogoTemp.getDescrizioneVersamento () );
        catalogo.setChiaveIntersistema ( catalogoTemp.getChiaveIntersistema () );
        catalogo.setDataFineValidita ( catalogoTemp.getDataFineValidita () );
        catalogo.setDataInizioValidita ( catalogoTemp.getDataInizioValidita () );
        catalogo.setDataInserimento ( catalogoTemp.getDataInserimento () );
        catalogo.setDataModifica ( catalogoTemp.getDataModifica () );
        catalogo.setUtenteInserimento ( catalogoTemp.getUtenteInserimento () );
        catalogo.setUtenteModifica ( catalogoTemp.getUtenteModifica () );
        catalogo.setIdEnte ( catalogoTemp.getIdEnte () );
        //:TODO modificare logica
        //        if ( TIPO_ENTITA_RIFERIMENTO_CONTABILE.equalsIgnoreCase ( catalogoTemp.getTipoEntita () ) ) {
        catalogo.setAccertamentoAnno ( catalogoTemp.getAccertamentoAnno () );
        catalogo.setAccertamentoDesc ( catalogoTemp.getAccertamentoDesc () );
        catalogo.setAccertamentoNro ( catalogoTemp.getAccertamentoNro () );
        catalogo.setAnnoEsercizio ( catalogoTemp.getAnnoEsercizio () );
        catalogo.setArticolo ( catalogoTemp.getArticolo () );
        catalogo.setCapitolo ( catalogoTemp.getCapitolo () );
        catalogo.setCategoria ( catalogoTemp.getCategoria () );
        catalogo.setDatiSpecificiRiscossione ( catalogoTemp.getDatiSpecificiRiscossione () );
        catalogo.setPianoDeiConti ( catalogoTemp.getPianoDeiConti () );
        catalogo.setSottovoce ( catalogoTemp.getSottovoce () );
        catalogo.setTipologia ( catalogoTemp.getTipologia () );
        catalogo.setTitolo ( catalogoTemp.getTitolo () );
        catalogo.setCodiceFiscaleEnte ( catalogoTemp.getCodiceFiscaleEnte () );
        //        }
        return catalogo;
    }

    static CblTCodiceVersamento mapCblTCodiceVersamento ( CblTCodiceVersamento codiceVersamento, CblTCodiceVersamentoTemp codiceVersamentoTemp, boolean insert ) {

        codiceVersamento.setIdEnte ( codiceVersamentoTemp.getIdEnte () );
        codiceVersamento.setCodiceFiscaleEnte ( codiceVersamentoTemp.getCodiceFiscaleEnte () );
        codiceVersamento.setDataModifica ( codiceVersamentoTemp.getDataModifica () );
        codiceVersamento.setDescrizioneVersamento ( codiceVersamentoTemp.getDescrizioneVersamento () );
        codiceVersamento.setUtenteModifica ( codiceVersamentoTemp.getUtenteModifica () );
        codiceVersamento.setFlagAnnullato ( codiceVersamentoTemp.getFlagAnnullato () );
        codiceVersamento.setCodiceVersamento ( codiceVersamentoTemp.getCodiceVersamento () );
        codiceVersamento.setCodiceModalitaIntegrazione ( codiceVersamentoTemp.getCodiceModalitaIntegrazione () );
        codiceVersamento.setFattura ( codiceVersamentoTemp.getFattura () );

        if( insert ) {
            codiceVersamento.setUtenteInserimento ( codiceVersamentoTemp.getUtenteInserimento () );
            codiceVersamento.setDataInserimento ( codiceVersamentoTemp.getDataInserimento () );
            codiceVersamento.setDataFineValidita ( codiceVersamentoTemp.getDataFineValidita () );
            codiceVersamento.setDataInizioValidita ( codiceVersamentoTemp.getDataInizioValidita () );
        }
        
        return codiceVersamento;
    }
}
