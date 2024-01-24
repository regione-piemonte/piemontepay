/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;

public class MdpTTemplate implements Serializable{

    private static final long serialVersionUID = 1L;

    String gateway_id;

    String chiave;

    String valore;

    String tabella_riferimento;

    boolean flag_attivo;

    String progetto;
    
    String descrizione;
    
    boolean campo_cifrato;

    public String getGateway_id () {
        return gateway_id;
    }

    public void setGateway_id ( String gateway_id ) {
        this.gateway_id = gateway_id;
    }

    public String getChiave () {
        return chiave;
    }

    public void setChiave ( String chiave ) {
        this.chiave = chiave;
    }

    public String getValore () {
        return valore;
    }

    public void setValore ( String valore ) {
        this.valore = valore;
    }

    public String getTabella_riferimento () {
        return tabella_riferimento;
    }

    public void setTabella_riferimento ( String tabella_riferimento ) {
        this.tabella_riferimento = tabella_riferimento;
    }

    public boolean isFlag_attivo () {
        return flag_attivo;
    }

    public void setFlag_attivo ( boolean flag_attivo ) {
        this.flag_attivo = flag_attivo;
    }

    public String getProgetto () {
        return progetto;
    }

    public void setProgetto ( String progetto ) {
        this.progetto = progetto;
    }

    
    public String getDescrizione () {
        return descrizione;
    }

    
    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    
    public boolean isCampo_cifrato () {
        return campo_cifrato;
    }

    
    public void setCampo_cifrato ( boolean campo_cifrato ) {
        this.campo_cifrato = campo_cifrato;
    }

    @Override
    public String toString () {
        return "MdpTTemplate [gateway_id=" + gateway_id + ", chiave=" + chiave + ", valore=" + valore + ", tabella_riferimento=" + tabella_riferimento
            + ", flag_attivo=" + flag_attivo + ", progetto=" + progetto + ", descrizione=" + descrizione + ", campo_cifrato=" + campo_cifrato + "]";
    }
    
}
