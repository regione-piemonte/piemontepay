/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ModificaAssociazioniUtenteTematicaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<AssociazioneUtenteTematicaVO> associazioni = new ArrayList<> ();

    public List<AssociazioneUtenteTematicaVO> getAssociazioni () {
        return associazioni;
    }

    public void setAssociazioni ( List<AssociazioneUtenteTematicaVO> associazioni ) {
        this.associazioni = associazioni;
    }

}
