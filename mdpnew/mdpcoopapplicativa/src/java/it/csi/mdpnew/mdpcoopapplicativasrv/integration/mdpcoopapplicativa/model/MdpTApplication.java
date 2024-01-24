/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


public class MdpTApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    String id;

    String applicationname;

    String referentecsi;

    String cliente;

    String progetto;

    String note;

    String esercemail;

    String numeroverde;

    Timestamp valido_dal;

    Timestamp valido_al;

    BigDecimal redirect_newmdp;

    public String getId () {
        return id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getApplicationname () {
        return applicationname;
    }

    public void setApplicationname ( String applicationname ) {
        this.applicationname = applicationname;
    }

    public String getReferentecsi () {
        return referentecsi;
    }

    public void setReferentecsi ( String referentecsi ) {
        this.referentecsi = referentecsi;
    }

    public String getCliente () {
        return cliente;
    }

    public void setCliente ( String cliente ) {
        this.cliente = cliente;
    }

    public String getProgetto () {
        return progetto;
    }

    public void setProgetto ( String progetto ) {
        this.progetto = progetto;
    }

    public String getNote () {
        return note;
    }

    public void setNote ( String note ) {
        this.note = note;
    }

    public String getEsercemail () {
        return esercemail;
    }

    public void setEsercemail ( String esercemail ) {
        this.esercemail = esercemail;
    }

    public String getNumeroverde () {
        return numeroverde;
    }

    public void setNumeroverde ( String numeroverde ) {
        this.numeroverde = numeroverde;
    }

    public Timestamp getValido_dal () {
        return valido_dal;
    }

    public void setValido_dal ( Timestamp valido_dal ) {
        this.valido_dal = valido_dal;
    }

    public Timestamp getValido_al () {
        return valido_al;
    }

    public void setValido_al ( Timestamp valido_al ) {
        this.valido_al = valido_al;
    }

    public BigDecimal getRedirect_newmdp () {
        return redirect_newmdp;
    }

    public void setRedirect_newmdp ( BigDecimal redirect_newmdp ) {
        this.redirect_newmdp = redirect_newmdp;
    }

    @Override
    public String toString () {
        return "MdpTApplication [id=" + id + ", applicationname=" + applicationname + ", referentecsi=" + referentecsi + ", cliente=" + cliente + ", progetto="
            + progetto + ", note=" + note + ", esercemail=" + esercemail + ", numeroverde=" + numeroverde + ", valido_dal=" + valido_dal + ", valido_al="
            + valido_al + ", redirect_newmdp=" + redirect_newmdp + "]";
    }

}
