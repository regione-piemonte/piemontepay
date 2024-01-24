/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


public class OpzioniAssociazioneUtenteTematicaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OpzioneAssociazioneUtenteTematicaTematicaVO> tematiche;

    public List<OpzioneAssociazioneUtenteTematicaTematicaVO> getTematiche() {
	    Collections.sort ( tematiche );
	    return tematiche;
    }

    public void setTematiche ( List<OpzioneAssociazioneUtenteTematicaTematicaVO> tematiche ) {
        this.tematiche = tematiche;
    }

}
