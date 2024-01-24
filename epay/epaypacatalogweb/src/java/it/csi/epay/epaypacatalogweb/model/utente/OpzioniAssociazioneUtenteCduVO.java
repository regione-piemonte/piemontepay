/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.utente;

import java.io.Serializable;
import java.util.List;


public class OpzioniAssociazioneUtenteCduVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OpzioneAssociazioneUtenteCduCategoriaCduVO> categorie;

    public List<OpzioneAssociazioneUtenteCduCategoriaCduVO> getCategorie () {
        return categorie;
    }

    public void setCategorie ( List<OpzioneAssociazioneUtenteCduCategoriaCduVO> categorie ) {
        this.categorie = categorie;
    }

}
