/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import java.util.ArrayList;
import java.util.Date;

import org.apache.cxf.common.util.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDErrore;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoFlusso;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.utils.ConversionUtils;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoDettaglio;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOFlussoSintesi;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoFlusso;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;


/**
 *
 * @author vsgro
 */
public class FlussiUtilityWS {

    final static Logger logger = LogManager.getLogger ( FlussiUtilityWS.class );

    public static DTOFlussoOrigine getDTOFlussoOrigine ( FlussoOrigine flussoOrigine ) {
        DTOFlussoOrigine origineWs = null;
        if ( flussoOrigine == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOFlussoOrigine: oggetto null" );
        } else {
            origineWs = new DTOFlussoOrigine ();
            origineWs.setId ( ConversionUtils.convertNumberToString ( flussoOrigine.getId () ) );
            origineWs.setIdentificativoFlusso ( flussoOrigine.getIdentificativoFlusso () );
            origineWs.setIdentificativoIstitutoRicevente ( flussoOrigine.getIdentificativoIstitutoRicevente () );
            origineWs.setIdentificativoPsp ( flussoOrigine.getIdentificativoPsp () );
            origineWs.setImportoTotalePagamenti ( flussoOrigine.getImportoTotalePagamenti () );
            origineWs.setNumeroTotalePagamenti ( flussoOrigine.getNumeroTotalePagamenti () );
            origineWs.setIbanRiversamentoFlusso ( flussoOrigine.getIbanRiversamentoFlusso () );
            origineWs.setDataOraFlusso ( flussoOrigine.getDataOraFlusso () );
            origineWs.setDataInserimento ( flussoOrigine.getDataInserimento () );
            origineWs.setContatoreTentativi ( flussoOrigine.getContatoreTentativi () );
            if ( flussoOrigine.getStatoFlusso () != null ) {
                origineWs.setStatoFlusso ( getDTOStatoFlusso ( flussoOrigine.getStatoFlusso () ) );
            }
        }
        return origineWs;
    }

    public static DTOFlussoSintesi getDTOFlussoSintesi ( FlussoSintesi flussoSintesi ) {
        DTOFlussoSintesi sintesiWs = null;
        if ( flussoSintesi == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOFlussoSintesi: oggetto null" );
        } else {
            sintesiWs = new DTOFlussoSintesi ();
            sintesiWs.setId ( ConversionUtils.convertNumberToString ( flussoSintesi.getId () ) );
            sintesiWs.setIdentificativoFlusso ( flussoSintesi.getIdentificativoFlusso () );
            sintesiWs.setCodiceVersamento ( flussoSintesi.getCodiceVersamento () );
            sintesiWs.setDatiSpecificiDiRiscossione ( flussoSintesi.getDatiSpecificiDiRiscossione () );
            sintesiWs.setImportoQuotaAggregazione ( ConversionUtils.convertNumberToString ( flussoSintesi.getImportoQuotaAggregazione () ) );
            sintesiWs.setNumeroVersamentoQuotaAggregazione ( ConversionUtils.convertNumberToString ( flussoSintesi.getNumeroVersamentoQuotaAggregazione () ) );
            sintesiWs.setProvvisorioAnno ( ConversionUtils.convertNumberToString ( flussoSintesi.getProvvisorioAnno () ) );
            sintesiWs.setProvvisorioNumero ( ConversionUtils.convertNumberToString ( flussoSintesi.getProvvisorioNumero () ) );

            ArrayList<FlussoDettaglio> listaVersamenti = flussoSintesi.getListaPagamenti ();
            if ( listaVersamenti != null ) {
                for ( int i = 0; i < listaVersamenti.size (); i++ ) {
                    DTOFlussoDettaglio flussoDettaglio = getDTOFlussoDettaglio ( listaVersamenti.get ( i ) );
                    if ( flussoDettaglio != null ) {
                        sintesiWs.getListaVersamenti ().add ( flussoDettaglio );
                    }
                }
            }
            sintesiWs.setFlussoOrigine ( getDTOFlussoOrigine ( flussoSintesi.getFlussoOrigine () ) );
        }
        return sintesiWs;
    }

    public static DTOFlussoDettaglio getDTOFlussoDettaglio ( FlussoDettaglio flussoDettaglio ) {
        DTOFlussoDettaglio dettaglioWs = new DTOFlussoDettaglio ();
        if ( flussoDettaglio == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOFlussoDettaglio: oggetto null" );
        } else {
            dettaglioWs.setId ( ConversionUtils.convertNumberToString ( flussoDettaglio.getId () ) );
            dettaglioWs.setIdentificativoUnicoVersamento ( flussoDettaglio.getIdentificativoUnicoVersamento () );
            dettaglioWs.setIdentificativoUnicoRiscossione ( flussoDettaglio.getIdentificativoUnicoRiscossione () );
            dettaglioWs.setCodiceVersamento ( flussoDettaglio.getCodiceVersamento () );
            dettaglioWs.setDescrizioneVersamento ( flussoDettaglio.getDescrizioneVersamento () );
            dettaglioWs.setDatiSpecificiDiRiscossione ( flussoDettaglio.getDatiSpecificiDiRiscossione () );
            dettaglioWs.setImportoSingoloVersamento ( ConversionUtils.convertNumberToString ( flussoDettaglio.getImportoSingoloVersamento () ) );
            dettaglioWs.setDataPagamento ( ConversionUtils.convertTimeStampToString ( flussoDettaglio.getDataPagamento () ) );
            dettaglioWs.setEsitoPagamento ( flussoDettaglio.getEsitoPagamento () );
            dettaglioWs.setCausale ( flussoDettaglio.getCausale () );
            dettaglioWs.setIdTransaction ( flussoDettaglio.getIdTransaction () );
            dettaglioWs.setStatoInvioFruitore ( flussoDettaglio.getStatoInvioFruitore () );
            dettaglioWs.setAnagraficaPagatore ( flussoDettaglio.getAnagraficaPagatore () );
            dettaglioWs.setCodiceFiscalePagatore ( flussoDettaglio.getCodiceFiscalePagatore () );
            dettaglioWs.setIndiceSingoloVersamento ( ConversionUtils.convertNumberToString ( flussoDettaglio.getIndiceSingoloVersamento () ) );
            dettaglioWs.setFlussoSintesi ( getDTOFlussoSintesi ( flussoDettaglio.getFlussoSintesi () ) );
        }
        return dettaglioWs;
    }

    public static DTOFlussoOrigine getDTOFlussoOrigine ( CblTFlussoOrigine cblTFlussoOrigine ) {
        DTOFlussoOrigine origineWs = null;
        if ( cblTFlussoOrigine == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOFlussoOrigine from entity: oggetto null" );
        } else {
            origineWs = new DTOFlussoOrigine ();
            origineWs.setId ( ConversionUtils.convertNumberToString ( cblTFlussoOrigine.getId () ) );
            origineWs.setIdentificativoFlusso ( cblTFlussoOrigine.getIdentificativoFlusso () );
            origineWs.setIdentificativoIstitutoRicevente ( cblTFlussoOrigine.getIdentificativoIstitutoRicevente () );
            origineWs.setIdentificativoIstitutoRiceventeDescrizione ( cblTFlussoOrigine.getCblTEnte ().getDenominazione () );
            origineWs.setIdentificativoPsp ( cblTFlussoOrigine.getCblTPsp ().getIdentificativoPsp () );
            origineWs.setIdentificativoPspDescrizione ( ( cblTFlussoOrigine.getCblTPsp ().getDenominazionePsp () ) );
            origineWs.setImportoTotalePagamenti ( cblTFlussoOrigine.getImportoTotalePagamenti () );
            origineWs.setNumeroTotalePagamenti ( cblTFlussoOrigine.getNumeroTotalePagamenti () );
            origineWs.setDataOraFlusso ( new Date ( cblTFlussoOrigine.getDataoraFlusso ().getTime () ) );
            origineWs.setDataInserimento ( new Date ( cblTFlussoOrigine.getDataInserimento ().getTime () ) );
            origineWs.setContatoreTentativi ( cblTFlussoOrigine.getContatoreTentativi () );
            if ( cblTFlussoOrigine.getCblDStatoFlusso () != null ) {
                origineWs.setStatoFlusso ( getDTOStatoFlusso ( cblTFlussoOrigine.getCblDStatoFlusso () ) );
            }
        }
        return origineWs;
    }

    public static DTOFlussoSintesi getDTOFlussoSintesi ( CblTFlussoSintesi cbltFlussoSintesi ) {
        DTOFlussoSintesi sintesiWs = null;
        if ( cbltFlussoSintesi == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOFlussoSintesi from entity: oggetto null" );
        } else {
            sintesiWs = new DTOFlussoSintesi ();
            sintesiWs.setId ( ConversionUtils.convertNumberToString ( cbltFlussoSintesi.getId () ) );
            sintesiWs.setDescrizioneVersamento ( cbltFlussoSintesi.getDescrizioneCodiceVersamento () );
            sintesiWs.setCodiceVersamento ( cbltFlussoSintesi.getCodiceVersamento () );
            sintesiWs.setDatiSpecificiDiRiscossione ( cbltFlussoSintesi.getDatiSpecificiDiRiscossione () );
            sintesiWs.setImportoQuotaAggregazione ( ConversionUtils.convertNumberToString ( cbltFlussoSintesi.getImportoQuotaAggregazione () ) );
            sintesiWs.setNumeroVersamentoQuotaAggregazione ( ConversionUtils.convertNumberToString ( cbltFlussoSintesi.getNumeroVersQuotaAggregazione () ) );
            sintesiWs.setProvvisorioAnno ( ConversionUtils.convertNumberToString ( cbltFlussoSintesi.getProvvisorioAnno () ) );
            sintesiWs.setProvvisorioNumero ( ConversionUtils.convertNumberToString ( cbltFlussoSintesi.getProvvisorioNro () ) );
            sintesiWs.setAnnoAccertamento(ConversionUtils.convertNumberToString (cbltFlussoSintesi.getAccertamentoAnno() ) );
            sintesiWs.setNumeroAccertamento(ConversionUtils.convertNumberToString (cbltFlussoSintesi.getAccertamentoNumero() ) );


        }
        return sintesiWs;
    }

    public static DTOFlussoDettaglio getDTOFlussoDettaglio ( CblTFlussoDettaglio cbltFlussoDettaglio, String descrizioneVersamento ) {
        DTOFlussoDettaglio dettaglioWs = new DTOFlussoDettaglio ();
        if ( cbltFlussoDettaglio == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOFlussoDettaglio from entity: oggetto null" );
        } else {
            dettaglioWs.setId ( ConversionUtils.convertNumberToString ( cbltFlussoDettaglio.getId () ) );
            dettaglioWs.setIdentificativoUnicoVersamento ( cbltFlussoDettaglio.getIdentificativoUnicoVersamento () );
            dettaglioWs.setIdentificativoUnicoRiscossione ( cbltFlussoDettaglio.getIdentificativoUnicoRiscossione () );
            dettaglioWs.setCodiceVersamento ( cbltFlussoDettaglio.getCodiceVersamento () );
            dettaglioWs.setDescrizioneVersamento ( descrizioneVersamento );
            dettaglioWs.setDatiSpecificiDiRiscossione ( cbltFlussoDettaglio.getDatiSpecificiDiRiscossione () );
            dettaglioWs.setImportoSingoloVersamento ( ConversionUtils.convertNumberToString ( cbltFlussoDettaglio.getImportoSingoloVersamento () ) );
            dettaglioWs.setDataPagamento ( ConversionUtils.convertTimeStampToString ( cbltFlussoDettaglio.getDataPagamento () ) );
            dettaglioWs.setEsitoPagamento ( cbltFlussoDettaglio.getEsitoPagamento () );
            dettaglioWs.setCausale ( cbltFlussoDettaglio.getDescrizioneCausaleVersamento () );
            dettaglioWs.setIdTransaction ( cbltFlussoDettaglio.getTransactionid () );
            dettaglioWs.setStatoInvioFruitore ( cbltFlussoDettaglio.getStatoInvioFruitore () );
            dettaglioWs.setAnagraficaPagatore ( cbltFlussoDettaglio.getAnagraficaPagatore () );
            dettaglioWs.setCodiceFiscalePagatore ( cbltFlussoDettaglio.getCodicefiscalePagatore () );
            dettaglioWs.setIndiceSingoloVersamento ( ConversionUtils.convertNumberToString ( cbltFlussoDettaglio.getIndiceSingoloVersamento () ) );
            dettaglioWs.setFlussoSintesi ( getDTOFlussoSintesi ( cbltFlussoDettaglio.getCblTFlussoSintesi () ) );
        }
        return dettaglioWs;
    }

    public static boolean isEmptyDTOInputWsRicercaFlussoOrigine ( DTOInputWsRicercaFlussoOrigine inputWs ) {
        boolean isEmpty = true;
        isEmpty = isEmpty
                        && StringUtils.isEmpty ( inputWs.getIdentificativoIstitutoRicevente () )
                        && StringUtils.isEmpty ( inputWs.getIdentificativoFlusso () )
                        && StringUtils.isEmpty ( inputWs.getIuv () )
                        && StringUtils.isEmpty ( inputWs.getIdCodVersamento () )
                        && StringUtils.isEmpty ( inputWs.getIdStatoFlusso () )
                        && null == ( inputWs.getDataElaborazioneDa () )
                        && null == ( inputWs.getDataElaborazioneA () )
                        && null == ( inputWs.getDataRicezioneDa () )
                        && null == ( inputWs.getDataRicezioneA () )
                        && StringUtils.isEmpty ( inputWs.getPsp () );
        return isEmpty;
    }

    public static boolean isOkParamsDataElaborazione ( DTOInputWsRicercaFlussoOrigine inputWs ) {

        return null != inputWs.getDataElaborazioneA () && null != inputWs.getDataElaborazioneDa ()
                        || ( null == inputWs.getDataElaborazioneA () && null == inputWs.getDataElaborazioneDa () );
    }

    public static DTOStatoFlusso getDTOStatoFlusso ( CblDStatoFlusso cblDStatoFlusso ) {
        DTOStatoFlusso statoFlussoWs = new DTOStatoFlusso ();
        if ( cblDStatoFlusso == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOStatoFlusso from entity: oggetto null" );
        } else {
            statoFlussoWs.setId ( ConversionUtils.convertNumberToString ( cblDStatoFlusso.getId () ) );
            statoFlussoWs.setCodiceStato ( cblDStatoFlusso.getCodiceStato () );
            statoFlussoWs.setDescrizioneStato ( cblDStatoFlusso.getDescrizioneStato () );
            statoFlussoWs.setPermettiRielaborazione ( cblDStatoFlusso.getPermettiRielaborazione () );
        }
        return statoFlussoWs;
    }

    private static DTOStatoFlusso getDTOStatoFlusso ( StatoFlusso statoFlusso ) {
        DTOStatoFlusso statoFlussoWs = new DTOStatoFlusso ();
        if ( statoFlusso == null ) {
            logger.warn ( "FlussiUtilityWS.getDTOStatoFlusso from bo: oggetto null" );
        } else {
            statoFlussoWs.setId ( ConversionUtils.convertNumberToString ( statoFlusso.getId () ) );
            statoFlussoWs.setCodiceStato ( statoFlusso.getCodiceStatoFlusso () );
            statoFlussoWs.setDescrizioneStato ( statoFlusso.getDescrizioneStatoFlusso () );
            statoFlussoWs.setPermettiRielaborazione ( statoFlusso.getPermettiRielaborazione () );
        }
        return statoFlussoWs;
    }

    public static DTOOutputWsEsito getDtoEsitoFromCblDErrore ( CblDErrore cblDErrore ) {
        DTOOutputWsEsito dtoEsito = null;
        if ( cblDErrore != null ) {
            String esito = "OK";
            if ( "FATAL".equalsIgnoreCase ( cblDErrore.getTipologia () )
                            || "ERROR".equalsIgnoreCase ( cblDErrore.getTipologia () ) ) {
                esito = "KO";
            }
            dtoEsito = new DTOOutputWsEsito ( esito, cblDErrore.getCodiceErrore (), cblDErrore.getDescrizioneErrore () );
        }
        return dtoEsito;
    }

}
