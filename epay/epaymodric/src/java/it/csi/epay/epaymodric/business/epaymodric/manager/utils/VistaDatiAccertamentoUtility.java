/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import it.csi.epay.epaymodric.business.epaymodric.bo.VistaDatiAccertamento;
import it.csi.epay.epaymodric.business.epaymodric.model.CblVDatiAccertamento;


/**
 *
 * @author vsgro
 */
public class VistaDatiAccertamentoUtility {

    final static Logger logger = LogManager.getLogger(VistaDatiAccertamentoUtility.class);

    public static VistaDatiAccertamento getDatiAccertamento(CblVDatiAccertamento entity) {
        VistaDatiAccertamento dati = new VistaDatiAccertamento();
        if (entity==null) {
            logger.warn ( "VistaDatiAccertamentoUtility.getDatiAccertamento: oggetto null" );
        } else {
            dati.setAccertamentoAnno ( entity.getAccertamentoAnno () );
            dati.setAccertamentoNro ( entity.getAccertamentoNro () );
            dati.setAnnoEsercizio ( entity.getAnnoEsercizio () );
            dati.setCodiceVersamento ( entity.getCodiceVersamento () );
            dati.setDatiSpecificiRiscossione ( entity.getDatiSpecificiRiscossione () );
            dati.setId ( entity.getId () );
            dati.setIdEnte ( entity.getIdEnte () );
            dati.setPdc ( entity.getPdc () );
            dati.setPriorita ( entity.getPriorita () );
            dati.setCapitolo(entity.getCapitolo());
            dati.setArticolo(entity.getArticolo());
            dati.setPianoDeiConti(entity.getPianoDeiConti());
            dati.setDescrizioneCodiceVersamento(entity.getDescrizioneCodiceVersamento());
            dati.setIdListaDiCarico ( entity.getIdListaDiCarico () );
        }
        return dati;
    }

    public static CblVDatiAccertamento getDatiAccertamento(VistaDatiAccertamento dati) {
        CblVDatiAccertamento entity = new CblVDatiAccertamento();
        if (dati==null) {
            logger.warn ( "VistaDatiAccertamentoUtility.getDatiAccertamento: oggetto null" );
        } else {
            entity.setAccertamentoAnno ( dati.getAccertamentoAnno () );
            entity.setAccertamentoNro ( dati.getAccertamentoNro () );
            entity.setAnnoEsercizio ( dati.getAnnoEsercizio () );
            entity.setCodiceVersamento ( dati.getCodiceVersamento () );
            entity.setDatiSpecificiRiscossione ( dati.getDatiSpecificiRiscossione () );
            entity.setId ( dati.getId () );
            entity.setIdEnte ( dati.getIdEnte () );
            entity.setPdc ( dati.getPdc () );
            entity.setPriorita ( dati.getPriorita () );
            entity.setDescrizioneCodiceVersamento(dati.getDescrizioneCodiceVersamento());
            entity.setIdListaDiCarico ( dati.getIdListaDiCarico () );
        }
        return entity;
    }

}
