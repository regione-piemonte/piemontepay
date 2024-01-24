/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model;

public class AssociazioneVO<T> {

    private Boolean selezionata;

    private T oggetto;

    public AssociazioneVO() {
        // TODO Auto-generated constructor stub
    }

    public AssociazioneVO(T oggetto, Boolean selezionata) {
        super();
        this.selezionata = selezionata;
        this.oggetto = oggetto;
    }

    public Boolean getSelezionata() {
        return selezionata;
    }

    public void setSelezionata(Boolean selezionata) {
        this.selezionata = selezionata;
    }

    public T getOggetto() {
        return oggetto;
    }

    public void setOggetto(T oggetto) {
        this.oggetto = oggetto;
    }


}
