/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoSintesi;
import it.csi.epay.epaymodric.business.epaymodric.bo.TipoAcqusizioneDTO;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDTipoAcquisizione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoDettaglio;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoSintesi;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;
import it.csi.epay.epaymodric.util.wsdl.flussorendicontazione.TrasmettiFlussoRendicontazioneExtRequestType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author vsgro
 */
public class FlussiUtility {

    final static Logger logger = LogManager.getLogger ( FlussiUtility.class );

    public static FlussoSintesi getFlusso ( CblTFlussoSintesi flussoSintesiEntity, boolean withDettagli ) {
        FlussoSintesi flussoSintesi = null;
        if ( flussoSintesiEntity == null ) {
            logger.warn ( "FlussiUtility.getFlusso: oggetto null" );
        } else {
            flussoSintesi = new FlussoSintesi ();
            flussoSintesi.setId ( flussoSintesiEntity.getId () );
            flussoSintesi.setImportoQuotaAggregazione ( flussoSintesiEntity.getImportoQuotaAggregazione () );
            flussoSintesi.setNumeroVersamentoQuotaAggregazione ( flussoSintesiEntity.getNumeroVersQuotaAggregazione () );
            flussoSintesi.setDatiSpecificiDiRiscossione ( flussoSintesiEntity.getDatiSpecificiDiRiscossione () );
            flussoSintesi.setCodiceVersamento ( flussoSintesiEntity.getCodiceVersamento () );
            flussoSintesi.setProvvisorioAnno ( flussoSintesiEntity.getProvvisorioAnno () );
            flussoSintesi.setProvvisorioNumero ( flussoSintesiEntity.getProvvisorioNro () );
            flussoSintesi.setAccertamentoAnno(flussoSintesiEntity.getAccertamentoAnno());
            flussoSintesi.setAccertamentoNumero(flussoSintesiEntity.getAccertamentoNumero());
            flussoSintesi.setDescrizioneCodiceVersamento(flussoSintesiEntity.getDescrizioneCodiceVersamento());

            if ( flussoSintesiEntity.getCblTFlussoOrigine () != null ) {
                flussoSintesi.setFlussoOrigine ( getFlussoOrigine ( flussoSintesiEntity.getCblTFlussoOrigine () ) );
            }
            if ( flussoSintesiEntity.getCblTEnte () != null ) {
                flussoSintesi.setIstitutoRicevente ( EnteUtility.getEnte ( flussoSintesiEntity.getCblTEnte () ) );
            }

            if ( withDettagli
                            && flussoSintesiEntity.getCblTFlussoDettaglios ()!=null &&  !flussoSintesiEntity.getCblTFlussoDettaglios ().isEmpty ()) {
                ArrayList<FlussoDettaglio> listaPagamenti = new ArrayList<> ();
                for ( int i = 0; i < flussoSintesiEntity.getCblTFlussoDettaglios ().size (); i++ ) {
                    listaPagamenti.add ( getFlussoDettaglio ( flussoSintesiEntity.getCblTFlussoDettaglios ().get ( i ), false ) );
                }
                flussoSintesi.setListaPagamenti ( listaPagamenti );
            }

            if ( flussoSintesiEntity.getCblRStatoFlussoErrore () != null ) {
                flussoSintesi.setStatoFlussoErrore (
                    StatoFlussoErroreUtility.getStatoFlussoErrore ( flussoSintesiEntity.getCblRStatoFlussoErrore () ) );
            }

            flussoSintesi.setDataPagamentoCalcolata ( flussoSintesiEntity.getDataPagamentoCalcolata () );

            flussoSintesi.mapBaseFields ( flussoSintesiEntity );
        }
        return flussoSintesi;
    }

    public static FlussoSintesi getFlusso ( CblTFlussoSintesi flussoSintesiEntity, boolean withDettagli, String identificativoFlusso ) {
        FlussoSintesi flusso = getFlusso(flussoSintesiEntity, withDettagli);
        flusso.setIdentificativoFlusso(identificativoFlusso);
        return flusso;
    }

    public static CblTFlussoSintesi getFlusso ( FlussoSintesi flussoSintesi, boolean withDettagli ) {
        CblTFlussoSintesi flussoSintesiEntity = null;
        if ( flussoSintesi == null ) {
            logger.warn ( "FlussiUtility.getFlussoSintesi: oggetto null" );
        } else {
            flussoSintesiEntity = new CblTFlussoSintesi ();
            flussoSintesiEntity.setId ( flussoSintesi.getId () );
            flussoSintesiEntity.setImportoQuotaAggregazione ( flussoSintesi.getImportoQuotaAggregazione () );
            flussoSintesiEntity.setNumeroVersQuotaAggregazione ( flussoSintesi.getNumeroVersamentoQuotaAggregazione () );
            flussoSintesiEntity.setDatiSpecificiDiRiscossione ( flussoSintesi.getDatiSpecificiDiRiscossione () );
            flussoSintesiEntity.setCodiceVersamento ( flussoSintesi.getCodiceVersamento () );
            flussoSintesiEntity.setProvvisorioAnno ( flussoSintesi.getProvvisorioAnno () );
            flussoSintesiEntity.setProvvisorioNro ( flussoSintesi.getProvvisorioNumero () );
            flussoSintesiEntity.setAccertamentoAnno(flussoSintesi.getAccertamentoAnno());
            flussoSintesiEntity.setAccertamentoNumero(flussoSintesi.getAccertamentoNumero());
            flussoSintesiEntity.setDescrizioneCodiceVersamento(flussoSintesi.getDescrizioneCodiceVersamento());

            if ( flussoSintesi.getFlussoOrigine () != null ) {
                flussoSintesiEntity.setCblTFlussoOrigine ( getFlussoOrigine ( flussoSintesi.getFlussoOrigine () ) );
            }
            if ( flussoSintesi.getIstitutoRicevente () != null ) {
                flussoSintesiEntity.setCblTEnte ( EnteUtility.getEnte ( flussoSintesi.getIstitutoRicevente () ) );
            }
            if ( withDettagli &&
                            flussoSintesi.getListaPagamenti ()!=null &&  !flussoSintesi.getListaPagamenti ().isEmpty ()) {
                List<CblTFlussoDettaglio> listaPagamenti = new LinkedList<> ();
                for ( int i = 0; i < flussoSintesi.getListaPagamenti ().size (); i++ ) {
                    listaPagamenti.add ( getFlussoDettaglio ( ( flussoSintesi.getListaPagamenti ().get ( i ) ), false ) );
                }
                flussoSintesiEntity.setCblTFlussoDettaglios ( listaPagamenti );
            }
            if ( flussoSintesi.getStatoFlussoErrore () != null ) {
                flussoSintesiEntity.setCblRStatoFlussoErrore (
                    StatoFlussoErroreUtility.getStatoFlussoErrore ( flussoSintesi.getStatoFlussoErrore () ) );
            }

            flussoSintesiEntity.setDataPagamentoCalcolata ( new java.sql.Date ( flussoSintesi.getDataPagamentoCalcolata ().getTime () ) );

            flussoSintesiEntity.mapBaseFields ( flussoSintesi );
        }
        return flussoSintesiEntity;
    }

    public static FlussoDettaglio getFlussoDettaglio ( CblTFlussoDettaglio flussoDettaglioEntity, boolean withSintesi ) {
        FlussoDettaglio flussoDettaglio = new FlussoDettaglio ();
        flussoDettaglio.setId ( flussoDettaglioEntity.getId () );
        flussoDettaglio.setCodiceVersamento ( flussoDettaglioEntity.getCodiceVersamento () );
        flussoDettaglio.setIdentificativoUnicoVersamento ( flussoDettaglioEntity.getIdentificativoUnicoVersamento () );
        flussoDettaglio.setIdentificativoUnicoRiscossione ( flussoDettaglioEntity.getIdentificativoUnicoRiscossione () );
        flussoDettaglio.setDatiSpecificiDiRiscossione ( flussoDettaglioEntity.getDatiSpecificiDiRiscossione () );
        flussoDettaglio.setImportoSingoloVersamento ( flussoDettaglioEntity.getImportoSingoloVersamento () );
        flussoDettaglio.setIndiceSingoloVersamento ( flussoDettaglioEntity.getIndiceSingoloVersamento () );
        flussoDettaglio.setDataPagamento ( flussoDettaglioEntity.getDataPagamento () );
        flussoDettaglio.setEsitoPagamento ( flussoDettaglioEntity.getEsitoPagamento () );
        flussoDettaglio.setCausale ( flussoDettaglioEntity.getDescrizioneCausaleVersamento () );
        flussoDettaglio.setIdTransaction ( flussoDettaglioEntity.getTransactionid () );
        flussoDettaglio.setStatoInvioFruitore ( flussoDettaglioEntity.getStatoInvioFruitore () );
        flussoDettaglio.setAnagraficaPagatore ( flussoDettaglioEntity.getAnagraficaPagatore () );
        flussoDettaglio.setCodiceFiscalePagatore ( flussoDettaglioEntity.getCodicefiscalePagatore () );
        if (withSintesi && flussoDettaglioEntity.getCblTFlussoSintesi () != null ) {
            flussoDettaglio.setIdFlussoSintesi ( flussoDettaglioEntity.getCblTFlussoSintesi ().getId () );
            flussoDettaglio.setFlussoSintesi ( getFlusso ( flussoDettaglioEntity.getCblTFlussoSintesi () , false) );
        }

        flussoDettaglio.mapBaseFields ( flussoDettaglioEntity );
        return flussoDettaglio;
    }

    public static CblTFlussoDettaglio getFlussoDettaglio ( FlussoDettaglio flussoDettaglio, boolean withSintesi ) {
        CblTFlussoDettaglio flussoDettaglioEntity = new CblTFlussoDettaglio ();
        flussoDettaglioEntity.setId ( flussoDettaglio.getId () );
        flussoDettaglioEntity.setIdentificativoUnicoVersamento ( flussoDettaglio.getIdentificativoUnicoVersamento () );
        flussoDettaglioEntity.setIdentificativoUnicoRiscossione ( flussoDettaglio.getIdentificativoUnicoRiscossione () );
        flussoDettaglioEntity.setDatiSpecificiDiRiscossione ( flussoDettaglio.getDatiSpecificiDiRiscossione () );
        flussoDettaglioEntity.setImportoSingoloVersamento ( flussoDettaglio.getImportoSingoloVersamento () );
        flussoDettaglioEntity.setIndiceSingoloVersamento ( flussoDettaglio.getIndiceSingoloPagamento () );
        flussoDettaglioEntity.setDataPagamento ( flussoDettaglio.getDataPagamento () );
        flussoDettaglioEntity.setEsitoPagamento ( flussoDettaglio.getEsitoPagamento () );
        flussoDettaglioEntity.setDescrizioneCausaleVersamento ( flussoDettaglio.getCausale () );
        flussoDettaglioEntity.setTransactionid ( flussoDettaglio.getIdTransaction () );
        flussoDettaglioEntity.setStatoInvioFruitore ( flussoDettaglio.getStatoInvioFruitore () );
        flussoDettaglioEntity.setAnagraficaPagatore ( flussoDettaglio.getAnagraficaPagatore () );
        flussoDettaglioEntity.setCodicefiscalePagatore ( flussoDettaglio.getCodiceFiscalePagatore () );
        if ( withSintesi && flussoDettaglio.getFlussoSintesi () != null ) {
            flussoDettaglioEntity.setCblTFlussoSintesi ( getFlusso ( flussoDettaglio.getFlussoSintesi (), false ) );
        }

        flussoDettaglioEntity.mapBaseFields ( flussoDettaglio );
        return flussoDettaglioEntity;
    }

    public static FlussoOrigine getFlussoOrigine ( CblTFlussoOrigine cblTFlussoOrigineEntity ) {
        FlussoOrigine flussoOrigine = new FlussoOrigine ();
        flussoOrigine.setTipoAcqusizione ( getTipoAcqusizioneDTO ( cblTFlussoOrigineEntity.getCblDTipoAcquisizione () ) );
        flussoOrigine.setId ( cblTFlussoOrigineEntity.getId () );
        flussoOrigine.setContatoreTentativi ( cblTFlussoOrigineEntity.getContatoreTentativi () );
        flussoOrigine.setDataInserimento ( cblTFlussoOrigineEntity.getDataInserimento () );
        flussoOrigine.setDataOraFlusso ( cblTFlussoOrigineEntity.getDataoraFlusso () );
        flussoOrigine.setIdentificativoFlusso ( cblTFlussoOrigineEntity.getIdentificativoFlusso () );
        flussoOrigine.setIdentificativoIstitutoRicevente ( cblTFlussoOrigineEntity.getIdentificativoIstitutoRicevente () );
        if ( null != cblTFlussoOrigineEntity.getCblTEnte () ) {
            flussoOrigine.setIdentificativoIstitutoRiceventeDescrizione ( cblTFlussoOrigineEntity.getCblTEnte ().getDenominazione () );
        }
        if ( null != cblTFlussoOrigineEntity.getCblTPsp () ) {
            flussoOrigine.setIdentificativoPsp ( cblTFlussoOrigineEntity.getCblTPsp ().getIdentificativoPsp () );
            flussoOrigine.setIdentificativoPspDescrizione ( cblTFlussoOrigineEntity.getCblTPsp ().getDenominazionePsp () );
        }
        flussoOrigine.setImportoTotalePagamenti ( cblTFlussoOrigineEntity.getImportoTotalePagamenti () );
        flussoOrigine.setNumeroTotalePagamenti ( cblTFlussoOrigineEntity.getNumeroTotalePagamenti () );
        //valorizzo anche il numero e l'importo dei pagamenti non intermediati e sconosciuti
        flussoOrigine.setImportoTotalePagamentiNonIntermediati ( cblTFlussoOrigineEntity.getImportoTotalePagamentiNonIntermediati () );
        flussoOrigine.setNumeroTotalePagamentiNonIntermediati ( cblTFlussoOrigineEntity.getNumeroTotalePagamentiNonIntermediati () );
        flussoOrigine.setImportoTotalePagamentiSconosciuti ( cblTFlussoOrigineEntity.getImportoTotalePagamentiSconosciuti () );
        flussoOrigine.setNumeroTotalePagamentiSconosciuti ( cblTFlussoOrigineEntity.getNumeroTotalePagamentiSconosciuti () );
        
        flussoOrigine.setXmlFlusso ( cblTFlussoOrigineEntity.getXmlFlusso () );
        if ( cblTFlussoOrigineEntity.getCblTEnte ()!=null ) {
            flussoOrigine.setIstitutoRicevente ( EnteUtility.getEnte ( cblTFlussoOrigineEntity.getCblTEnte () ) );
        }
        if ( cblTFlussoOrigineEntity.getCblDStatoFlusso ()!=null ) {
            flussoOrigine.setStatoFlusso ( StatoFlussoUtility.getStatoFlusso ( cblTFlussoOrigineEntity.getCblDStatoFlusso () ) );
        }
        if ( cblTFlussoOrigineEntity.getCblTElaborazione ()!=null ) {
            flussoOrigine.setElaborazione ( ElaborazioneUtility.getElaborazione ( cblTFlussoOrigineEntity.getCblTElaborazione () ) );
        }
        flussoOrigine.setNumeroTotalePagamentiIntermediati ( cblTFlussoOrigineEntity.getNumeroTotalePagamentiIntermediati () );
        flussoOrigine.setImportoTotalePagamentiIntermediati ( cblTFlussoOrigineEntity.getImportoTotalePagamentiIntermediati () );

        if ( null != cblTFlussoOrigineEntity.getDataRegolamento () ) {
            flussoOrigine.setDataRegolamento ( new Date ( cblTFlussoOrigineEntity.getDataRegolamento ().getTime () ) );
        }
        flussoOrigine.setIdentificativoUnivocoRegolamento ( cblTFlussoOrigineEntity.getIdentificativoUnivocoRegolamento () );

        flussoOrigine.mapBaseFields ( cblTFlussoOrigineEntity );
        return flussoOrigine;
    }

    public static CblTFlussoOrigine getFlussoOrigine ( FlussoOrigine flussoOrigine ) {
        CblTFlussoOrigine cblTFlussoOrigineEntity = new CblTFlussoOrigine ();
        cblTFlussoOrigineEntity.setCblDTipoAcquisizione ( getCblDTipoAcquisizione ( flussoOrigine.getTipoAcqusizione () ) );
        cblTFlussoOrigineEntity.setId ( flussoOrigine.getId () );
        cblTFlussoOrigineEntity.setContatoreTentativi ( flussoOrigine.getContatoreTentativi () );
        cblTFlussoOrigineEntity.setDataInserimento ( flussoOrigine.getDataInserimento () );
        cblTFlussoOrigineEntity.setDataoraFlusso ( flussoOrigine.getDataOraFlusso () );
        cblTFlussoOrigineEntity.setIdentificativoFlusso ( flussoOrigine.getIdentificativoFlusso () );
        cblTFlussoOrigineEntity.setIdentificativoIstitutoRicevente ( flussoOrigine.getIdentificativoIstitutoRicevente () );

        //L'identificativo psp non e' piu' numerico ma un oggetto cblTPsp
        //Non mi arriva da fuori e non e' utile dove viene usata la
        //getFlussoOrigine -> lo imposto a null
        cblTFlussoOrigineEntity.setCblTPsp ( null );

        cblTFlussoOrigineEntity.setImportoTotalePagamenti ( flussoOrigine.getImportoTotalePagamenti () );
        cblTFlussoOrigineEntity.setNumeroTotalePagamenti ( flussoOrigine.getNumeroTotalePagamenti () );
        cblTFlussoOrigineEntity.setXmlFlusso ( flussoOrigine.getXmlFlusso () );

        if ( flussoOrigine.getIstitutoRicevente ()!=null ) {
            cblTFlussoOrigineEntity.setCblTEnte ( EnteUtility.getEnte ( flussoOrigine.getIstitutoRicevente () ) );
        }

        if ( flussoOrigine.getStatoFlusso ()!=null ) {
            cblTFlussoOrigineEntity.setCblDStatoFlusso ( StatoFlussoUtility.getStatoFlusso ( flussoOrigine.getStatoFlusso () ) );
        }

        if ( flussoOrigine.getElaborazione ()!=null ) {
            cblTFlussoOrigineEntity.setCblTElaborazione ( ElaborazioneUtility.getElaborazione ( flussoOrigine.getElaborazione () ) );
        }
        cblTFlussoOrigineEntity.setNumeroTotalePagamentiIntermediati ( flussoOrigine.getNumeroTotalePagamentiIntermediati () );
        cblTFlussoOrigineEntity.setImportoTotalePagamentiIntermediati ( flussoOrigine.getImportoTotalePagamentiIntermediati () );

        if ( null != flussoOrigine.getDataRegolamento () ) {
            cblTFlussoOrigineEntity.setDataRegolamento ( new java.sql.Date ( flussoOrigine.getDataRegolamento ().getTime () ) );
        }

        cblTFlussoOrigineEntity.setIdentificativoUnivocoRegolamento ( flussoOrigine.getIdentificativoUnivocoRegolamento () );

        cblTFlussoOrigineEntity.mapBaseFields ( flussoOrigine );
        return cblTFlussoOrigineEntity;
    }

    public static List<String> getStatiElaborazione ( boolean isRiesecuzione ) {
        List<String> statiElaborazione = new LinkedList<> ();
        statiElaborazione.add ( StatoElaborazioneEnum.SCHEDULATA.getCodice () );
        if ( isRiesecuzione ) {
            statiElaborazione.add ( StatoElaborazioneEnum.FORZATA.getCodice () );
        }
        return statiElaborazione;
    }

    public static TipoAcqusizioneDTO getTipoAcqusizioneDTO ( CblDTipoAcquisizione tipoAcquisizione ) {
        TipoAcqusizioneDTO tipoAcqusizioneDTO = new TipoAcqusizioneDTO ();
        tipoAcqusizioneDTO.setId ( tipoAcquisizione.getId () );
        tipoAcqusizioneDTO.setCodice ( tipoAcquisizione.getCodice () );
        tipoAcqusizioneDTO.setDescrizione ( tipoAcquisizione.getDescrizione () );
        return tipoAcqusizioneDTO;
    }

    public static TipoAcqusizioneDTO getTipoAcqusizioneDTO ( TipoAcqusizioneDTO tipoAcquisizione ) {
        TipoAcqusizioneDTO tipoAcqusizioneDTO = new TipoAcqusizioneDTO ();
        tipoAcqusizioneDTO.setId ( tipoAcquisizione.getId () );
        tipoAcqusizioneDTO.setCodice ( tipoAcquisizione.getCodice () );
        tipoAcqusizioneDTO.setDescrizione ( tipoAcquisizione.getDescrizione () );
        return tipoAcqusizioneDTO;
    }

    public static CblDTipoAcquisizione getCblDTipoAcquisizione ( TipoAcqusizioneDTO tipoAcquisizione ) {
        CblDTipoAcquisizione cblDTipoAcquisizione = new CblDTipoAcquisizione ();
        cblDTipoAcquisizione.setId ( tipoAcquisizione.getId () );
        cblDTipoAcquisizione.setCodice ( tipoAcquisizione.getCodice () );
        cblDTipoAcquisizione.setDescrizione ( tipoAcquisizione.getDescrizione () );
        return cblDTipoAcquisizione;
    }
    public static TrasmettiFlussoRendicontazioneExtRequestType getFlussoRendicontazioneEsteso( String xml ) throws JAXBException {
        JAXBContext jaxbContextEsterno = JAXBContext.newInstance ( TrasmettiFlussoRendicontazioneExtRequestType.class );

        Unmarshaller jaxbUnmarshallerEsterno = jaxbContextEsterno.createUnmarshaller ();

        StringReader readerFlussoEsterno = new StringReader ( xml );

        TrasmettiFlussoRendicontazioneExtRequestType flussoEsterno = (TrasmettiFlussoRendicontazioneExtRequestType) jaxbUnmarshallerEsterno.unmarshal ( readerFlussoEsterno );

        return flussoEsterno;
    }
}
