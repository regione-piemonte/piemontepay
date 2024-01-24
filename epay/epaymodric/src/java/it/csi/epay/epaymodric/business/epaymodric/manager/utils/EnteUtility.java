/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnte;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOEnte;
import it.csi.epay.epaymodric.interfacews.epaymodric.enums.ModalitaAcquisizioneProvvisoriEnum;


/**
 *
 * @author vsgro
 */
public class EnteUtility {

    final static Logger logger = LogManager.getLogger ( EnteUtility.class );

    public static Ente getEnte ( CblTEnte enteEntity ) {
        Ente ente = null;
        if ( enteEntity == null ) {
            logger.warn ( "EnteUtility.getEnte: oggetto null" );
        } else {
            ente = new Ente ();
            ente.setId ( enteEntity.getId () );
            ente.setIdEnte ( enteEntity.getIdEnte () );
            ente.setCodiceFiscale ( enteEntity.getCodiceFiscale () );
            ente.setDenominazione ( enteEntity.getDenominazione () );
            ente.setFlagAccertamento ( enteEntity.getFlagAccertamento () );
            ente.setFlagRiconciliazione ( enteEntity.getFlagRiconciliazione () );
            ente.setIbanTesoreria ( enteEntity.getIbanTesoreria () );
            ente.setEntePlurintermediato ( enteEntity.getEntePlurintermediato () );
            ente.setPeriodicitaSchedulazione ( enteEntity.getPeriodicitaSchedulazione () );
            if ( null != enteEntity.getGiornoSchedulazione () ) {
                ente.setGiornoSchedulazione ( enteEntity.getGiornoSchedulazione () );
            }
            for ( int i = 0; i < ModalitaAcquisizioneProvvisoriEnum.values ().length; i++ ) {
                if ( null != enteEntity.getModalitaAcquisizioneProvvisori () ) {
                    int tipoAccertamento = enteEntity.getModalitaAcquisizioneProvvisori ().intValue ();
                    if ( new Integer ( ModalitaAcquisizioneProvvisoriEnum.values () [i].getCodice () ).intValue () == tipoAccertamento ) {
                        ente.setModalitaAcquisizioneProvvisori ( ModalitaAcquisizioneProvvisoriEnum.values () [i] );
                        break;
                    }
                }
            }
            ente.setFlagRicezioneErrori ( enteEntity.getFlagRicezioneErrori () );
            ente.setEmailEnte ( enteEntity.getEmailEnte () );

            ente.mapBaseFields ( enteEntity );
        }
        return ente;
    }

    public static CblTEnte getEnte ( Ente ente ) {
        CblTEnte enteEntity = null;
        if ( ente != null ) {
            enteEntity = new CblTEnte ();
            enteEntity.setId ( ente.getId () );
            enteEntity.setIdEnte ( ente.getIdEnte () );
            enteEntity.setCodiceFiscale ( ente.getCodiceFiscale () );
            enteEntity.setDenominazione ( ente.getDenominazione () );
            enteEntity.setFlagAccertamento ( ente.isFlagAccertamento () );
            enteEntity.setFlagRiconciliazione ( ente.isFlagRiconciliazione () );
            enteEntity.setIbanTesoreria ( ente.getIbanTesoreria () );
            enteEntity.setEntePlurintermediato ( ente.isEntePlurintermediato () );
            enteEntity.setPeriodicitaSchedulazione ( ente.getPeriodicitaSchedulazione () );
            enteEntity.setGiornoSchedulazione ( ente.getGiornoSchedulazione () );
            if ( enteEntity.getModalitaAcquisizioneProvvisori () != null ) {
                enteEntity.setModalitaAcquisizioneProvvisori (
                    new Integer (
                        ente.getModalitaAcquisizioneProvvisori ().getCodice () ) );
            }
            enteEntity.setFlagRicezioneErrori ( ente.isFlagRicezioneErrori () );
            enteEntity.setEmailEnte ( ente.getEmailEnte () );

            enteEntity.mapBaseFields ( ente );
        }
        return enteEntity;
    }

    public static DTOEnte getDTOEnte ( CblTEnte enteEntity ) {
        DTOEnte ente = null;
        if ( enteEntity == null ) {
            logger.warn ( "EnteUtility.getEnte: oggetto null" );
        } else {
            ente = new DTOEnte ();
            ente.setId ( enteEntity.getId () );
            ente.setIdEnte ( enteEntity.getIdEnte () );
            ente.setCodiceFiscale ( enteEntity.getCodiceFiscale () );
            ente.setDenominazione ( enteEntity.getDenominazione () );
            ente.setFlagAccertamento ( enteEntity.getFlagAccertamento () );
            ente.setFlagRiconciliazione ( enteEntity.getFlagRiconciliazione () );
            ente.setIbanTesoreria ( enteEntity.getIbanTesoreria () );
            ente.setEntePlurintermediato ( enteEntity.getEntePlurintermediato () );
            ente.setPeriodicitaSchedulazione ( enteEntity.getPeriodicitaSchedulazione () );
            ente.setGiornoSchedulazione ( enteEntity.getGiornoSchedulazione () );
            ente.setFlagRicezioneErrori ( enteEntity.getFlagRicezioneErrori () );
            ente.setEmailEnte ( enteEntity.getEmailEnte () );
        }
        return ente;
    }
}
