/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;


public class MdpTEnte implements Serializable {

    private static final long serialVersionUID = 1L;

    String ente_id;

    String partita_iva;

    String descrizione;

    String attivo;

    String codice_segregazione;

    Boolean flag_invio_flusso_base;

    Boolean flag_invio_flusso_esteso;

    Integer progressivo_application_id;

    boolean flag_gestione_ppay;

    public String getEnte_id () {
        return ente_id;
    }

    public void setEnte_id ( String ente_id ) {
        this.ente_id = ente_id;
    }

    public String getPartita_iva () {
        return partita_iva;
    }

    public void setPartita_iva ( String partita_iva ) {
        this.partita_iva = partita_iva;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getAttivo () {
        return attivo;
    }

    public void setAttivo ( String attivo ) {
        this.attivo = attivo;
    }

    public String getCodice_segregazione () {
        return codice_segregazione;
    }

    public void setCodice_segregazione ( String codice_segregazione ) {
        this.codice_segregazione = codice_segregazione;
    }

    public Boolean getFlag_invio_flusso_base () {
        return flag_invio_flusso_base;
    }

    public void setFlag_invio_flusso_base ( Boolean flag_invio_flusso_base ) {
        this.flag_invio_flusso_base = flag_invio_flusso_base;
    }

    public Boolean getFlag_invio_flusso_esteso () {
        return flag_invio_flusso_esteso;
    }

    public void setFlag_invio_flusso_esteso ( Boolean flag_invio_flusso_esteso ) {
        this.flag_invio_flusso_esteso = flag_invio_flusso_esteso;
    }

    public Integer getProgressivo_application_id () {
        return progressivo_application_id;
    }

    public void setProgressivo_application_id ( Integer progressivo_application_id ) {
        this.progressivo_application_id = progressivo_application_id;
    }

    public boolean isFlag_gestione_ppay () {
        return flag_gestione_ppay;
    }

    public void setFlag_gestione_ppay ( boolean flag_gestione_ppay ) {
        this.flag_gestione_ppay = flag_gestione_ppay;
    }

}
