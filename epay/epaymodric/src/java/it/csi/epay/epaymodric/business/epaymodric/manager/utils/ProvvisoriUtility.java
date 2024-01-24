/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.FlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.bo.Provvisori;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTProvvisorio;
import it.csi.epay.epaymodric.business.epaymodric.utils.DateUtils;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOProvvisorio;
import it.csi.epay.epaymodric.util.wsdl.epayriconciliazioneversamentipsp.ProvvisorioType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author vsgro
 */
public class ProvvisoriUtility {


    final static Logger logger = LogManager.getLogger(ProvvisoriUtility.class);

    public static final String DESCRIZIONE_DEFAULT = "";
    public static final String TIPO_MOVIMENTO_DEFAULT = "SOSPESO DI ENTRATA";


    public static Provvisori getProvvisori(CblTProvvisorio entity) {
        Provvisori provvisori = new Provvisori();
        if (entity==null) {
            logger.warn ( "ProvvisoriUtility.getProvvisori: oggetto null" );
        } else {
            provvisori.setId ( entity.getId () );
            provvisori.setAnnoEsercizio ( entity.getAnnoEsercizio () );
            provvisori.setAnnoProvvisorio ( entity.getAnnoProvvisorio () );
            provvisori.setCausale ( entity.getCausale () );
            provvisori.setDataFine ( entity.getDataFine () );
            provvisori.setDataMovimento ( entity.getDataMovimento () );
            provvisori.setDescrizione ( entity.getDescrizione () );
            provvisori.setIdEnte ( entity.getIdEnte () );
            provvisori.setIdentificativoFlusso ( entity.getIdentificativoFlusso () );
            provvisori.setImportoDisponibilita ( entity.getImportoDisponibilita () );
            provvisori.setImportoProvvisorio ( entity.getImportoProvvisorio () );
            provvisori.setNumeroProvvisorio ( entity.getNumeroProvvisorio () );
            provvisori.setStato ( entity.getStato () );
            provvisori.setTipoMovimento ( entity.getTipoMovimento () );
            provvisori.mapBaseFields ( entity );
        }
        return provvisori;
    }

    public static CblTProvvisorio getProvvisoriEntity(Provvisori provvisorio) {
        CblTProvvisorio cblTProvvisorio = new CblTProvvisorio();
        if (provvisorio==null) {
            logger.warn ( "ProvvisoriUtility.getProvvisori - to entity: oggetto null" );
        } else {
            cblTProvvisorio.setId ( provvisorio.getId () );
            cblTProvvisorio.setAnnoEsercizio ( provvisorio.getAnnoEsercizio () );
            cblTProvvisorio.setAnnoProvvisorio ( provvisorio.getAnnoProvvisorio () );
            cblTProvvisorio.setCausale ( provvisorio.getCausale () );
            cblTProvvisorio.setDataFine ( provvisorio.getDataFine () );
            cblTProvvisorio.setDataMovimento ( provvisorio.getDataMovimento () );
            cblTProvvisorio.setDescrizione ( provvisorio.getDescrizione () );
            cblTProvvisorio.setIdEnte ( provvisorio.getIdEnte () );
            cblTProvvisorio.setIdentificativoFlusso ( provvisorio.getIdentificativoFlusso () );
            cblTProvvisorio.setImportoDisponibilita ( provvisorio.getImportoDisponibilita () );
            cblTProvvisorio.setImportoProvvisorio ( provvisorio.getImportoProvvisorio () );
            cblTProvvisorio.setNumeroProvvisorio ( provvisorio.getNumeroProvvisorio () );
            cblTProvvisorio.setStato ( provvisorio.getStato () );
            cblTProvvisorio.setTipoMovimento ( provvisorio.getTipoMovimento () );
            cblTProvvisorio.mapBaseFields ( provvisorio );
        }
        return cblTProvvisorio;
    }

    public static Provvisori getProvvisorioFromProvvisorioType ( ProvvisorioType provvisorioType, String idEnte ) {
        Provvisori provvisori = new Provvisori ();
        if ( null != provvisorioType ) {
            provvisori.setAnnoEsercizio ( null != provvisorioType.getAnnoEsercizio () ? provvisorioType.getAnnoEsercizio ().intValue () : null );
            provvisori.setAnnoProvvisorio ( null != provvisorioType.getAnnoEsercizio () ? provvisorioType.getAnnoEsercizio ().intValue () : null );
            provvisori.setCausale ( provvisorioType.getCausaleVersamento () );
            provvisori.setDataFine ( null );
            if ( null != provvisorioType.getData () ) {
                provvisori.setDataMovimento ( DateUtils.xmlGregorianCalendarToTimestamp ( provvisorioType.getData () ) );
            }
            provvisori.setDescrizione ( DESCRIZIONE_DEFAULT );
            if ( null != idEnte ) {
                provvisori.setIdEnte ( idEnte );
            }

            // CSI_PAG-306 - EPAY-165
            String causale = provvisorioType.getCausaleVersamento ();
            // EPAY-511
            final String regex1 = "/(RFB|RFS)/(\\w+)/";
            final Pattern pattern1 = Pattern.compile ( regex1 );
            final Matcher matcher1 = pattern1.matcher (causale);

            if ( matcher1.find () ) {
                provvisori.setIdentificativoFlusso ( matcher1.group ( 2 ) );
            } else {
                final String regex2 = "\\D/(URI|TXT/0/URI|TXT)/(\\S+)(.*)";
                final Pattern pattern2 = Pattern.compile ( regex2 );
                final Matcher matcher2 = pattern2.matcher (causale);

                if ( matcher2.find () ) {
                    provvisori.setIdentificativoFlusso ( matcher2.group ( 2 ) );
                }
                else {
                    logger.warn ( "ProvvisoriUtility.getProvvisori - to ProvvisorioType: identificativo flusso non trovato o rifiutato" );
                    return provvisori;
                }
            }
            provvisori.setImportoProvvisorio ( provvisorioType.getImporto () );
            provvisori.setNumeroProvvisorio ( null != provvisorioType.getNumero () ? provvisorioType.getNumero ().intValue () : null );
            if ( null != provvisorioType.getStato () ) {
                provvisori.setStato ( provvisorioType.getStato ().toString () );
            } else {
                logger.warn ( "STATO PROVVISORIO NULL" );
            }
            provvisori.setTipoMovimento ( TIPO_MOVIMENTO_DEFAULT );

        } else {
            logger.warn ( "ProvvisoriUtility.getProvvisori - to ProvvisorioType: parametro null" );
        }
        return provvisori;
    }

    public static Provvisori getProvvisorioFromDTOProvvisorio(DTOProvvisorio dtoProvvisorio) {
        Provvisori provvisori = new Provvisori();

        if (null != dtoProvvisorio) {
            provvisori.setId(dtoProvvisorio.getId());
            provvisori.setAnnoEsercizio(dtoProvvisorio.getAnnoEsercizio());
            provvisori.setAnnoProvvisorio(dtoProvvisorio.getAnnoProvvisorio());
            provvisori.setCausale(dtoProvvisorio.getCausale());
            if (null != dtoProvvisorio.getDataFine()) {
                provvisori.setDataFine(DateUtils.xmlGregorianCalendarToTimestamp(dtoProvvisorio.getDataFine()));
            }
            if (null != dtoProvvisorio.getDataMovimento()) {
                provvisori.setDataMovimento(DateUtils.xmlGregorianCalendarToTimestamp(dtoProvvisorio.getDataMovimento()));
            }
            provvisori.setDescrizione(dtoProvvisorio.getDescrizione());
            provvisori.setIdentificativoFlusso(dtoProvvisorio.getIdentificativoFlusso());
            provvisori.setImportoDisponibilita(dtoProvvisorio.getImportoDisponibilita());
            provvisori.setImportoProvvisorio(dtoProvvisorio.getImportoProvvisorio());
            provvisori.setNumeroProvvisorio(dtoProvvisorio.getNumeroProvvisorio());
            provvisori.setStato(dtoProvvisorio.getStato());
            provvisori.setTipoMovimento(dtoProvvisorio.getTipoMovimento());
        } else {
            logger.warn ( "ProvvisoriUtility.getProvvisorioFromDTOProvvisorio - to Provvisori: Parametro null" );
        }

        return provvisori;
    }

    public static List<String> creaListaCausali ( List<FlussoOrigine> flussiOrigineDaElaborare ) {
        List<String> causali = new ArrayList<> ();

        if ( null != flussiOrigineDaElaborare && flussiOrigineDaElaborare.size () > 0 ) {
            for ( FlussoOrigine flussoOrigine: flussiOrigineDaElaborare ) {
                if ( null != flussoOrigine.getIdentificativoFlusso () )
                    causali.add ( flussoOrigine.getIdentificativoFlusso () );
            }
        }

        return causali;
    }

}
