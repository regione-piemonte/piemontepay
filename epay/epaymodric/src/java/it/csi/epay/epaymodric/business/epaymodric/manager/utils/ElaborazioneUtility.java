/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Elaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblDStatoElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTElaborazione;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.business.epaymodric.utils.Costanti;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputRicercaElaborazionePrecedente;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.StatoElaborazioneEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;


/**
 *
 * @author vsgro
 */
public class ElaborazioneUtility {

    final static Logger logger = LogManager.getLogger(ElaborazioneUtility.class);


    public static Elaborazione getElaborazione(CblTElaborazione elaborazioneEntity) {
        Elaborazione elaborazione = null;
        if (elaborazioneEntity==null) {
            logger.warn ( "ElaborazioneUtility.getElaborazione: oggetto null" );
        } else {
            elaborazione = new Elaborazione();
            elaborazione.setId ( elaborazioneEntity.getId () );
            elaborazione.setIdEnte ( elaborazioneEntity.getCblTEnte ().getIdEnte () );
            elaborazione.setDataElaborazione ( elaborazioneEntity.getDataElaborazione () );
            elaborazione.setDataInizio ( elaborazioneEntity.getDataInizio () );
            elaborazione.setDataFine ( elaborazioneEntity.getDataFine () );
            for ( int i=0; i<StatoElaborazioneEnum.values ().length; i++ ) {
                if ( StatoElaborazioneEnum.values () [i].getCodice ().equalsIgnoreCase ( elaborazioneEntity.getCblDStatoElaborazione ().getCodiceStato () )
                                ) {
                    elaborazione.setStatoElaborazione ( StatoElaborazioneEnum.values ()[i] );
                    break;
                }
            }
            if(elaborazioneEntity.getListaFlussi ()!=null) {
                elaborazione.setListaFlussi (new ArrayList<String> ());
                StringTokenizer stringTokenizer = new StringTokenizer (
                    elaborazioneEntity.getListaFlussi (),
                    Costanti.SEPARATORE_LISTA_FLUSSI
                                );
                while ( stringTokenizer.hasMoreElements () ) {
                    elaborazione.getListaFlussi ().add ( stringTokenizer.nextToken () );
                }
            }
            if (elaborazioneEntity.getCblDErrore () !=null) {
                elaborazione.setErrore ( ErroreUtility.getErrore(elaborazioneEntity.getCblDErrore () ) );
            }
            elaborazione.setMsgErrore ( elaborazioneEntity.getMsgErrore () );

            try {
                elaborazione.mapBaseFields ( elaborazioneEntity );
            } catch ( Exception e ) {
                logger.error ( e );
            }
        }
        return elaborazione;
    }

    public static CblTElaborazione getElaborazione(Elaborazione elaborazione) {
        CblTElaborazione elaborazioneEntity  = null;
        if (elaborazione==null) {
        } else {
            elaborazioneEntity = new CblTElaborazione();
            elaborazioneEntity.setId ( elaborazione.getId () );
            CblTEnte enteEntity = new CblTEnte ();
            enteEntity.setIdEnte ( elaborazione.getIdEnte () );
            elaborazioneEntity.setCblTEnte ( enteEntity );
            elaborazioneEntity.setDataElaborazione ( elaborazione.getDataElaborazione () );
            elaborazioneEntity.setDataInizio ( elaborazione.getDataInizio () );
            elaborazioneEntity.setDataFine ( elaborazione.getDataFine () );
            CblDStatoElaborazione statoElaborazioneEntity = new CblDStatoElaborazione ();
            statoElaborazioneEntity.setCodiceStato ( elaborazione.getStatoElaborazione ().getCodice () );
            elaborazioneEntity.setCblDStatoElaborazione ( statoElaborazioneEntity );

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
                elaborazioneEntity.setListaFlussi ( listaFlussi.toString () );
            }
            if (elaborazione.getErrore () != null) {
                elaborazioneEntity.setCblDErrore ( ErroreUtility.getErrore ( elaborazione.getErrore () ) );
            }
            elaborazioneEntity.setMsgErrore ( elaborazione.getMsgErrore () );

            elaborazioneEntity.mapBaseFields ( elaborazione );
        }
        return elaborazioneEntity;
    }
    //Nuru 2.2.18 transforma CblTElaborazione In DTOOutputRicercaElaborazione
    public static DTOOutputRicercaElaborazionePrecedente transformaCblTElaborazioneInDTOOutputRicercaElab(CblTElaborazione cblTElaborazione) {
        DTOOutputRicercaElaborazionePrecedente dtoOutputElabPrecedente = new DTOOutputRicercaElaborazionePrecedente();
        if(cblTElaborazione.getDataElaborazione()!= null)
            dtoOutputElabPrecedente.setDataElaborazione(new Date(cblTElaborazione.getDataElaborazione().getTime()));
        if(cblTElaborazione.getDataFine()!= null)
            dtoOutputElabPrecedente.setDataFine(new Date(cblTElaborazione.getDataFine().getTime()));
        if(cblTElaborazione.getDataInizio()!= null)
            dtoOutputElabPrecedente.setDataInizio(new Date(cblTElaborazione.getDataInizio().getTime()));
        dtoOutputElabPrecedente
        .setIdEnte ( String.join ( " - ", cblTElaborazione.getCblTEnte ().getIdEnte (), cblTElaborazione.getCblTEnte ().getDenominazione () ) );
        dtoOutputElabPrecedente.setUtenteIns ( cblTElaborazione.getUtenteInserimento () );
        dtoOutputElabPrecedente.setId(cblTElaborazione.getId());
        dtoOutputElabPrecedente.setListaFlussi(cblTElaborazione.getListaFlussi());
        dtoOutputElabPrecedente.setStatoElaborazione ( cblTElaborazione.getCblDStatoElaborazione ().getDescrizioneStato () );
        return dtoOutputElabPrecedente;
    }

}
