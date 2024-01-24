/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.*;
import it.csi.epay.epaymodric.business.epaymodric.utils.ConversionUtils;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStoricoFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStoricoFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStoricoFlussoSintesi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 *
 * @author vsgro
 * use case 2.2.13
 */
public class FlussiStoricoUtilityWS {

    final static Logger logger = LogManager.getLogger ( FlussiStoricoUtilityWS.class );

    public static DTOStoricoFlussoOrigine getDTOStoricoFlussoOrigine ( CblTStoricoFlussoOrigine cblTStoricoFlussoOrigine ) {
        DTOStoricoFlussoOrigine origineWs = null;
        if (cblTStoricoFlussoOrigine==null) {
            logger.warn ( "FlussiStoricoUtilityWS.DTOStoricoFlussoOrigine from entity: oggetto null" );
        } else {
            origineWs = new DTOStoricoFlussoOrigine ();
            origineWs.setTipoAcqusizione ( FlussiUtility.getTipoAcqusizioneDTO ( cblTStoricoFlussoOrigine.getCblDTipoAcquisizione () ) );
            origineWs.setId ( ConversionUtils.convertNumberToString (cblTStoricoFlussoOrigine.getId ( )) );
            origineWs.setIdentificativoFlusso ( cblTStoricoFlussoOrigine.getIdentificativoFlusso () );
            origineWs.setIdentificativoIstitutoRicevente ( cblTStoricoFlussoOrigine.getIdentificativoIstitutoRicevente () );
            origineWs.setIdentificativoPsp ( cblTStoricoFlussoOrigine.getIdentificativoPsp () );
            origineWs.setImportoTotalePagamenti ( ConversionUtils.convertNumberToString (cblTStoricoFlussoOrigine.getImportoTotalePagamenti () ) );
            origineWs.setNumeroTotalePagamenti ( ConversionUtils.convertNumberToString (cblTStoricoFlussoOrigine.getNumeroTotalePagamenti () ) );
            origineWs.setDataoraFlusso ( ConversionUtils.convertTimeStampToString (cblTStoricoFlussoOrigine.getDataoraFlusso () ) );
            origineWs.setContatoreTentativi ( ConversionUtils.convertNumberToString (cblTStoricoFlussoOrigine.getContatoreTentativi () ) );
            origineWs.setIdElaborazione ( ConversionUtils.convertNumberToString ( cblTStoricoFlussoOrigine.getIdElaborazione ()  ) );
            origineWs.setIdIstitutoRicevente ( ConversionUtils.convertNumberToString ( cblTStoricoFlussoOrigine.getIdIstitutoRicevente ()  ) );
            origineWs.setIdStatoFlusso ( ConversionUtils.convertNumberToString ( cblTStoricoFlussoOrigine.getIdStatoFlusso ()  ) );
            origineWs.setXmlFlusso ( cblTStoricoFlussoOrigine.getXmlFlusso () );
            origineWs.setNumeroTotalePagamentiIntermediati ( cblTStoricoFlussoOrigine.getNumeroTotalePagamentiIntermediati () );
            origineWs.setImportoTotalePagamentiIntermediati ( cblTStoricoFlussoOrigine.getImportoTotalePagamentiIntermediati () );

            if ( cblTStoricoFlussoOrigine.getDataRegolamento () != null ) {
                origineWs.setDataRegolamento ( new Date ( cblTStoricoFlussoOrigine.getDataRegolamento ().getTime () ) );
            }

            origineWs.mapBaseFields ( cblTStoricoFlussoOrigine );
        }
        return origineWs;
    }

    public static CblTStoricoFlussoOrigine getDTOStoricoFlussoOrigine ( DTOStoricoFlussoOrigine inputOrigineWs ) {
        CblTStoricoFlussoOrigine cblTStoricoFlussoOrigine = null;
        if (inputOrigineWs==null) {
            logger.warn ( "FlussiStoricoUtilityWS.DTOStoricoFlussoOrigine: oggetto null" );
        } else {
            cblTStoricoFlussoOrigine = new CblTStoricoFlussoOrigine ();
            cblTStoricoFlussoOrigine.setCblDTipoAcquisizione ( FlussiUtility.getCblDTipoAcquisizione ( inputOrigineWs.getTipoAcqusizione () ) );
            cblTStoricoFlussoOrigine.setId ( (Long) ConversionUtils.convertStringToNumber (inputOrigineWs.getId (), Long.class) );
            cblTStoricoFlussoOrigine.setIdentificativoFlusso ( inputOrigineWs.getIdentificativoFlusso () );
            cblTStoricoFlussoOrigine.setIdentificativoIstitutoRicevente ( inputOrigineWs.getIdentificativoIstitutoRicevente () );
            cblTStoricoFlussoOrigine.setIdentificativoPsp ( inputOrigineWs.getIdentificativoPsp () );
            cblTStoricoFlussoOrigine.setImportoTotalePagamenti ( (BigDecimal)
                ConversionUtils.convertStringToNumber (inputOrigineWs.getImportoTotalePagamenti (), BigDecimal.class )
                            );
            cblTStoricoFlussoOrigine.setNumeroTotalePagamenti ( (Integer)
                ConversionUtils.convertStringToNumber (inputOrigineWs.getNumeroTotalePagamenti (), Integer.class )
                            );
            cblTStoricoFlussoOrigine.setDataoraFlusso ( ConversionUtils.convertStringToTimeStamp (inputOrigineWs.getDataoraFlusso () ) );
            cblTStoricoFlussoOrigine.setContatoreTentativi ( (Integer)
                ConversionUtils.convertStringToNumber (inputOrigineWs.getContatoreTentativi (), Integer.class )
                            );
            cblTStoricoFlussoOrigine.setIdElaborazione ( (Long)
                ConversionUtils.convertStringToNumber ( inputOrigineWs.getIdElaborazione (), Long.class  )
                            );
            cblTStoricoFlussoOrigine.setIdIstitutoRicevente ( (Long)
                ConversionUtils.convertStringToNumber ( inputOrigineWs.getIdIstitutoRicevente (), Long.class  )
                            );
            cblTStoricoFlussoOrigine.setIdStatoFlusso ( (Long)
                ConversionUtils.convertStringToNumber ( inputOrigineWs.getIdStatoFlusso (), Long.class  )
                            );
            cblTStoricoFlussoOrigine.setXmlFlusso ( inputOrigineWs.getXmlFlusso () );

            cblTStoricoFlussoOrigine.setNumeroTotalePagamentiIntermediati ( inputOrigineWs.getNumeroTotalePagamentiIntermediati () );
            cblTStoricoFlussoOrigine.setImportoTotalePagamentiIntermediati ( inputOrigineWs.getImportoTotalePagamentiIntermediati () );

            cblTStoricoFlussoOrigine.mapBaseFields ( inputOrigineWs );

            if ( cblTStoricoFlussoOrigine.getDataRegolamento () != null ) {
                cblTStoricoFlussoOrigine.setDataRegolamento ( new Date ( inputOrigineWs.getDataRegolamento ().getTime () ) );
            }
        }
        return cblTStoricoFlussoOrigine;
    }

    public static DTOStoricoFlussoSintesi getDTOStoricoFlussoSintesi ( CblTStoricoFlussoSintesi cbltStoricoFlussoSintesi ) {
        DTOStoricoFlussoSintesi sintesiWs = null;
        if (cbltStoricoFlussoSintesi==null) {
            logger.warn ( "FlussiStoricoUtilityWS.getDTOStoricoFlussoSintesi from entity: oggetto null" );
        } else {
            sintesiWs = new DTOStoricoFlussoSintesi ();
            sintesiWs.setAccertamentoAnno ( cbltStoricoFlussoSintesi.getAccertamentoAnno () );
            sintesiWs.setAccertamentoNro ( cbltStoricoFlussoSintesi.getAccertamentoNro () );
            sintesiWs.setArticolo ( cbltStoricoFlussoSintesi.getArticolo () );
            sintesiWs.setCapitolo ( cbltStoricoFlussoSintesi.getCapitolo () );
            sintesiWs.setCodiceVersamento ( cbltStoricoFlussoSintesi.getCodiceVersamento () );
            sintesiWs.setDatiSpecificiRiscossione ( cbltStoricoFlussoSintesi.getDatiSpecificiRiscossione () );
            sintesiWs.setDescrizioneCodiceVersamento ( cbltStoricoFlussoSintesi.getDescrizioneCodiceVersamento () );
            sintesiWs.setIdErrore ( cbltStoricoFlussoSintesi.getIdErrore () );
            sintesiWs.setIdFlussoOrigine ( cbltStoricoFlussoSintesi.getIdFlussoOrigine () );
            sintesiWs.setIdIstitutoRicevente ( cbltStoricoFlussoSintesi.getIdIstitutoRicevente () );
            sintesiWs.setNumeroVersQuotaAggregazione ( cbltStoricoFlussoSintesi.getNumeroVersQuotaAggregazione () );
            sintesiWs.setIdentificativoFlusso ( cbltStoricoFlussoSintesi.getIdentificativoFlusso () );
            sintesiWs.setImportoQuotaAggregazione ( cbltStoricoFlussoSintesi.getImportoQuotaAggregazione () );
            sintesiWs.setId ( cbltStoricoFlussoSintesi.getId () );
            sintesiWs.setPianoDeiConti ( cbltStoricoFlussoSintesi.getPianoDeiConti () );
            sintesiWs.setProvvisorioAnno ( cbltStoricoFlussoSintesi.getProvvisorioAnno () );
            sintesiWs.setProvvisorioNro ( cbltStoricoFlussoSintesi.getProvvisorioNro () );
            sintesiWs.mapBaseFields ( cbltStoricoFlussoSintesi );

        }
        return sintesiWs;
    }

    public static CblTStoricoFlussoSintesi getDTOStoricoFlussoSintesi ( DTOStoricoFlussoSintesi inputSintesiWs ) {
        CblTStoricoFlussoSintesi cblTStoricoFlussoSintesi = null;
        if (inputSintesiWs==null) {
            logger.warn ( "FlussiStoricoUtilityWS.getDTOStoricoFlussoSintesi from entity: oggetto null" );
        } else {
            cblTStoricoFlussoSintesi = new CblTStoricoFlussoSintesi  ();

            cblTStoricoFlussoSintesi.setAccertamentoAnno ( inputSintesiWs.getAccertamentoAnno () );
            cblTStoricoFlussoSintesi.setAccertamentoNro ( inputSintesiWs.getAccertamentoNro () );
            cblTStoricoFlussoSintesi.setArticolo ( inputSintesiWs.getArticolo () );
            cblTStoricoFlussoSintesi.setCapitolo ( inputSintesiWs.getCapitolo () );
            cblTStoricoFlussoSintesi.setCodiceVersamento ( inputSintesiWs.getCodiceVersamento () );
            cblTStoricoFlussoSintesi.setDatiSpecificiRiscossione ( inputSintesiWs.getDatiSpecificiRiscossione () );
            cblTStoricoFlussoSintesi.setDescrizioneCodiceVersamento ( inputSintesiWs.getDescrizioneCodiceVersamento () );
            cblTStoricoFlussoSintesi.setIdErrore ( inputSintesiWs.getIdErrore () );
            cblTStoricoFlussoSintesi.setIdFlussoOrigine ( inputSintesiWs.getIdFlussoOrigine () );
            cblTStoricoFlussoSintesi.setIdIstitutoRicevente ( inputSintesiWs.getIdIstitutoRicevente () );
            cblTStoricoFlussoSintesi.setNumeroVersQuotaAggregazione ( inputSintesiWs.getNumeroVersQuotaAggregazione () );
            cblTStoricoFlussoSintesi.setIdentificativoFlusso ( inputSintesiWs.getIdentificativoFlusso () );
            cblTStoricoFlussoSintesi.setImportoQuotaAggregazione ( inputSintesiWs.getImportoQuotaAggregazione () );
            cblTStoricoFlussoSintesi.setId ( inputSintesiWs.getId () );
            cblTStoricoFlussoSintesi.setPianoDeiConti ( inputSintesiWs.getPianoDeiConti () );
            cblTStoricoFlussoSintesi.setProvvisorioAnno ( inputSintesiWs.getProvvisorioAnno () );
            cblTStoricoFlussoSintesi.setProvvisorioNro ( inputSintesiWs.getProvvisorioNro () );
            cblTStoricoFlussoSintesi.mapBaseFields ( inputSintesiWs );


        }
        return cblTStoricoFlussoSintesi;
    }

    public static DTOStoricoFlussoDettaglio getDTOStoricoFlussoDettaglio ( CblTStoricoFlussoDettaglio cbltStoricoFlussoDettaglio ) {
        DTOStoricoFlussoDettaglio dettaglioWs = null;
        if (cbltStoricoFlussoDettaglio==null) {
            logger.warn ( "FlussiStoricoUtilityWS.getDTOStoricoFlussoDettaglio: oggetto null" );
        } else {
            dettaglioWs = new DTOStoricoFlussoDettaglio ();
            dettaglioWs.setAnagraficaPagatore ( cbltStoricoFlussoDettaglio.getAnagraficaPagatore () );
            dettaglioWs.setCodicefiscalePagatore ( cbltStoricoFlussoDettaglio.getCodicefiscalePagatore () );
            dettaglioWs.setCodiceVersamento ( cbltStoricoFlussoDettaglio.getCodiceVersamento () );
            dettaglioWs.setDataPagamento ( cbltStoricoFlussoDettaglio.getDataPagamento () );
            dettaglioWs.setDatiSpecificiDiRiscossione ( cbltStoricoFlussoDettaglio.getDatiSpecificiDiRiscossione () );
            dettaglioWs.setDescrizioneCausaleVersamento ( cbltStoricoFlussoDettaglio.getDescrizioneCausaleVersamento () );
            dettaglioWs.setEsitoPagamento ( cbltStoricoFlussoDettaglio.getEsitoPagamento () );
            dettaglioWs.setId ( cbltStoricoFlussoDettaglio.getId () );
            dettaglioWs.setIdentificativoUnicoRiscossione ( cbltStoricoFlussoDettaglio.getIdentificativoUnicoRiscossione () );
            dettaglioWs.setIdentificativoUnicoVersamento ( cbltStoricoFlussoDettaglio.getIdentificativoUnicoVersamento () );
            dettaglioWs.setIdFlussoSintesi ( cbltStoricoFlussoDettaglio.getIdFlussoSintesi () );
            dettaglioWs.setImportoSingoloVersamento ( cbltStoricoFlussoDettaglio.getImportoSingoloVersamento () );
            dettaglioWs.setIndiceSingoloVersamento ( cbltStoricoFlussoDettaglio.getIndiceSingoloVersamento () );
            dettaglioWs.setImportoSingoloVersamento ( cbltStoricoFlussoDettaglio.getImportoSingoloVersamento () );
            dettaglioWs.setStatoInvioFruitore ( cbltStoricoFlussoDettaglio.getStatoInvioFruitore () );
            dettaglioWs.setTransactionid ( cbltStoricoFlussoDettaglio.getTransactionid () );
            dettaglioWs.mapBaseFields ( cbltStoricoFlussoDettaglio );
        }
        return dettaglioWs;
    }

    public static CblTStoricoFlussoDettaglio getDTOStoricoFlussoDettaglio ( DTOStoricoFlussoDettaglio inputDettaglioWs ) {
        CblTStoricoFlussoDettaglio cblTStoricoFlussoDettaglio = null;
        if (inputDettaglioWs==null) {
            logger.warn ( "FlussiStoricoUtilityWS.getDTOStoricoFlussoDettaglio: oggetto null" );
        } else {
            cblTStoricoFlussoDettaglio = new CblTStoricoFlussoDettaglio ();
            cblTStoricoFlussoDettaglio.setAnagraficaPagatore ( inputDettaglioWs.getAnagraficaPagatore () );
            cblTStoricoFlussoDettaglio.setCodicefiscalePagatore ( inputDettaglioWs.getCodicefiscalePagatore () );
            cblTStoricoFlussoDettaglio.setCodiceVersamento ( inputDettaglioWs.getCodiceVersamento () );
            cblTStoricoFlussoDettaglio.setDataPagamento ( inputDettaglioWs.getDataPagamento () );
            cblTStoricoFlussoDettaglio.setDatiSpecificiDiRiscossione ( inputDettaglioWs.getDatiSpecificiDiRiscossione () );
            cblTStoricoFlussoDettaglio.setDescrizioneCausaleVersamento ( inputDettaglioWs.getDescrizioneCausaleVersamento () );
            cblTStoricoFlussoDettaglio.setEsitoPagamento ( inputDettaglioWs.getEsitoPagamento () );
            cblTStoricoFlussoDettaglio.setId ( inputDettaglioWs.getId () );
            cblTStoricoFlussoDettaglio.setIdentificativoUnicoRiscossione ( inputDettaglioWs.getIdentificativoUnicoRiscossione () );
            cblTStoricoFlussoDettaglio.setIdentificativoUnicoVersamento ( inputDettaglioWs.getIdentificativoUnicoVersamento () );
            cblTStoricoFlussoDettaglio.setIdFlussoSintesi ( inputDettaglioWs.getIdFlussoSintesi () );
            cblTStoricoFlussoDettaglio.setImportoSingoloVersamento ( inputDettaglioWs.getImportoSingoloVersamento () );
            cblTStoricoFlussoDettaglio.setIndiceSingoloVersamento ( inputDettaglioWs.getIndiceSingoloVersamento () );
            cblTStoricoFlussoDettaglio.setImportoSingoloVersamento ( inputDettaglioWs.getImportoSingoloVersamento () );
            cblTStoricoFlussoDettaglio.setStatoInvioFruitore ( inputDettaglioWs.getStatoInvioFruitore () );
            cblTStoricoFlussoDettaglio.setTransactionid ( inputDettaglioWs.getTransactionid () );
            cblTStoricoFlussoDettaglio.mapBaseFields ( inputDettaglioWs );
        }
        return cblTStoricoFlussoDettaglio;
    }

    public static DTOStoricoFlussoOrigine getDTOStoricoFlussoOrigine ( FlussoOrigine flussoOrigine, String identificativoUtente ) {
        DTOStoricoFlussoOrigine origineWs = null;
        if (flussoOrigine==null) {
            logger.warn ( "FlussiStoricoUtilityWS.DTOStoricoFlussoOrigine from bo: oggetto null" );
        } else {
            origineWs = new DTOStoricoFlussoOrigine ();
            origineWs.setTipoAcqusizione ( FlussiUtility.getTipoAcqusizioneDTO ( flussoOrigine.getTipoAcqusizione () ) );
            //            origineWs.setId ( ConversionUtils.convertNumberToString (flussoOrigine.getId ( )) );
            origineWs.setIdentificativoFlusso ( flussoOrigine.getIdentificativoFlusso () );
            origineWs.setIdentificativoIstitutoRicevente ( flussoOrigine.getIdentificativoIstitutoRicevente () );
            origineWs.setIdentificativoPsp ( flussoOrigine.getIdentificativoPsp () );
            origineWs.setImportoTotalePagamenti ( ConversionUtils.convertNumberToString (flussoOrigine.getImportoTotalePagamenti () ) );
            origineWs.setNumeroTotalePagamenti ( ConversionUtils.convertNumberToString (flussoOrigine.getNumeroTotalePagamenti () ) );
            origineWs.setIbanRiversamentoFlusso ( flussoOrigine.getIbanRiversamentoFlusso () );
            origineWs.setDataoraFlusso ( ConversionUtils.convertTimeStampToString (flussoOrigine.getDataOraFlusso () ) );
            origineWs.setContatoreTentativi ( ConversionUtils.convertNumberToString (flussoOrigine.getContatoreTentativi () ) );
            if ( flussoOrigine.getElaborazione ()!=null ) {
                origineWs.setIdElaborazione ( ConversionUtils.convertNumberToString ( flussoOrigine.getElaborazione ().getId ()  ) );
            }
            if ( flussoOrigine.getIstitutoRicevente ()!=null ) {
                origineWs.setIdIstitutoRicevente ( ConversionUtils.convertNumberToString ( flussoOrigine.getIstitutoRicevente ().getId ()  ) );
            }
            if ( flussoOrigine.getStatoFlusso ()!=null ) {
                origineWs.setIdStatoFlusso ( ConversionUtils.convertNumberToString ( flussoOrigine.getStatoFlusso ().getId ()  ) );
                origineWs.setStatoFlusso ( flussoOrigine.getStatoFlusso ().getCodiceStatoFlusso () );
            }
            origineWs.setXmlFlusso ( flussoOrigine.getXmlFlusso () );

            if ( identificativoUtente != null ) {
                origineWs.setUtenteInsVar ( identificativoUtente );
            }

            origineWs.setDataInsVar ( ConversionUtils.convertTimeStampToString ( new Timestamp ( System.currentTimeMillis () ) ) );
            origineWs.setNumeroTotalePagamentiIntermediati ( flussoOrigine.getNumeroTotalePagamentiIntermediati () );
            origineWs.setImportoTotalePagamentiIntermediati ( flussoOrigine.getImportoTotalePagamentiIntermediati () );

            origineWs.setDataRegolamento ( flussoOrigine.getDataRegolamento () );
            origineWs.setIdentificativoUnivocoRegolamento ( flussoOrigine.getIdentificativoUnivocoRegolamento () );
            origineWs.setUtenteInserimento ( flussoOrigine.getUtenteInserimento () );
            origineWs.setDataInserimento ( flussoOrigine.getDataInserimento () );
            origineWs.setUtenteModifica ( flussoOrigine.getUtenteModifica () );
            origineWs.setDataModifica ( flussoOrigine.getDataModifica () );

        }
        return origineWs;
    }

    public static DTOStoricoFlussoSintesi getDTOStoricoFlussoSintesi ( CblTFlussoSintesi flussoSintesi ) {
        DTOStoricoFlussoSintesi sintesiWs = null;
        if (flussoSintesi==null) {
            logger.warn ( "FlussiStoricoUtilityWS.getDTOStoricoFlussoSintesi from bo: oggetto null" );
        } else {
            sintesiWs = new DTOStoricoFlussoSintesi ();
            sintesiWs.setAccertamentoAnno ( flussoSintesi.getAccertamentoAnno () );
            sintesiWs.setAccertamentoNro ( flussoSintesi.getAccertamentoNumero () );
            sintesiWs.setArticolo ( flussoSintesi.getArticolo () );
            sintesiWs.setCapitolo ( flussoSintesi.getCapitolo () );
            sintesiWs.setCodiceVersamento ( flussoSintesi.getCodiceVersamento () );
            sintesiWs.setDatiSpecificiRiscossione ( flussoSintesi.getDatiSpecificiDiRiscossione () );
            sintesiWs.setDescrizioneCodiceVersamento ( flussoSintesi.getDescrizioneCodiceVersamento () );
            if ( null != flussoSintesi.getCblRStatoFlussoErrore () && null != flussoSintesi.getCblRStatoFlussoErrore ().getCblDErrore () ) {
                sintesiWs.setIdErrore ( flussoSintesi.getCblRStatoFlussoErrore ().getCblDErrore ().getId () );
            }
            if ( null != flussoSintesi.getCblTEnte () ) {
                sintesiWs.setIdIstitutoRicevente ( flussoSintesi.getCblTEnte ().getId () );
            }
            sintesiWs.setNumeroVersQuotaAggregazione ( flussoSintesi.getNumeroVersQuotaAggregazione () );
            if ( null != flussoSintesi.getCblTFlussoOrigine () ) {
                sintesiWs.setIdFlussoOrigine ( flussoSintesi.getCblTFlussoOrigine ().getId () );
                sintesiWs.setIdentificativoFlusso ( flussoSintesi.getCblTFlussoOrigine ().getIdentificativoFlusso () );
            }
            sintesiWs.setImportoQuotaAggregazione ( flussoSintesi.getImportoQuotaAggregazione () );
            sintesiWs.setId ( flussoSintesi.getId () );
            sintesiWs.setPianoDeiConti ( flussoSintesi.getPianoDeiConti () );
            sintesiWs.setProvvisorioAnno ( flussoSintesi.getProvvisorioAnno () );
            sintesiWs.setProvvisorioNro ( flussoSintesi.getProvvisorioNro () );

        }
        return sintesiWs;
    }

    public static DTOStoricoFlussoDettaglio getDTOStoricoFlussoDettaglio ( CblTFlussoDettaglio flussoDettaglio, Long idFlussoSintesi ) {
        DTOStoricoFlussoDettaglio dettaglioWs = null;
        if (flussoDettaglio==null) {
            logger.warn ( "FlussiStoricoUtilityWS.getDTOStoricoFlussoDettaglio from bo: oggetto null" );
        } else {
            dettaglioWs = new DTOStoricoFlussoDettaglio ();

            dettaglioWs.setAnagraficaPagatore ( flussoDettaglio.getAnagraficaPagatore () );
            dettaglioWs.setCodicefiscalePagatore ( flussoDettaglio.getCodicefiscalePagatore () );
            dettaglioWs.setCodiceVersamento ( flussoDettaglio.getCodiceVersamento () );
            dettaglioWs.setDataPagamento ( flussoDettaglio.getDataPagamento () );
            dettaglioWs.setDatiSpecificiDiRiscossione ( flussoDettaglio.getDatiSpecificiDiRiscossione () );
            dettaglioWs.setDescrizioneCausaleVersamento ( flussoDettaglio.getDescrizioneCausaleVersamento () );
            dettaglioWs.setEsitoPagamento ( flussoDettaglio.getEsitoPagamento () );
            dettaglioWs.setId ( flussoDettaglio.getId () );
            dettaglioWs.setIdentificativoUnicoRiscossione ( flussoDettaglio.getIdentificativoUnicoRiscossione () );
            dettaglioWs.setIdentificativoUnicoVersamento ( flussoDettaglio.getIdentificativoUnicoVersamento () );
            dettaglioWs.setIdFlussoSintesi ( idFlussoSintesi );
            dettaglioWs.setImportoSingoloVersamento ( flussoDettaglio.getImportoSingoloVersamento () );
            dettaglioWs.setIndiceSingoloVersamento ( flussoDettaglio.getIndiceSingoloVersamento () );
            dettaglioWs.setImportoSingoloVersamento ( flussoDettaglio.getImportoSingoloVersamento () );
            dettaglioWs.setStatoInvioFruitore ( flussoDettaglio.getStatoInvioFruitore () );
            dettaglioWs.setTransactionid ( flussoDettaglio.getTransactionid () );

            dettaglioWs.setUtenteInserimento ( flussoDettaglio.getUtenteInserimento () );
            dettaglioWs.setDataInserimento ( flussoDettaglio.getDataInserimento () );
            dettaglioWs.setUtenteModifica ( flussoDettaglio.getUtenteModifica () );
            dettaglioWs.setDataModifica ( flussoDettaglio.getDataModifica () );

        }
        return dettaglioWs;
    }

    public static CblTStoricoElaborazione getStoricoElaborazioneEntity ( Elaborazione elaborazione ) {
        CblTStoricoElaborazione cblTStoricoElaborazione = new CblTStoricoElaborazione();

        if (null != elaborazione) {
            cblTStoricoElaborazione = new CblTStoricoElaborazione();
            cblTStoricoElaborazione.setId ( elaborazione.getId () );
            cblTStoricoElaborazione.setIdEnte ( elaborazione.getIdEnte () );
            cblTStoricoElaborazione.setDataElaborazione ( elaborazione.getDataElaborazione () );
            cblTStoricoElaborazione.setDataInizio ( elaborazione.getDataInizio () );
            cblTStoricoElaborazione.setDataFine ( elaborazione.getDataFine () );
            cblTStoricoElaborazione.setStatoElaborazione ( elaborazione.getStatoElaborazione ().getCodice () );
            if(elaborazione.getListaFlussi ()!=null) {
                StringBuffer listaFlussi = new StringBuffer ();
                int numElem = elaborazione.getListaFlussi ().size ();
                for(int i=0; i<numElem; i++) {
                    String identificativoFlusso = elaborazione.getListaFlussi ().get ( i );
                    listaFlussi.append ( identificativoFlusso );
                    if(i<numElem-1 && numElem>1) {
                        listaFlussi.append (Costanti.SEPARATORE_LISTA_FLUSSI);
                    }
                }
                cblTStoricoElaborazione.setListaFlussi ( listaFlussi.toString () );
            }
            if (elaborazione.getErrore () != null) {
                cblTStoricoElaborazione.setCblDErrore ( ErroreUtility.getErrore ( elaborazione.getErrore () ) );
            }
            cblTStoricoElaborazione.setMsgErrore ( elaborazione.getMsgErrore () );

            cblTStoricoElaborazione.mapBaseFields ( elaborazione );
        }

        return cblTStoricoElaborazione;
    }
}
