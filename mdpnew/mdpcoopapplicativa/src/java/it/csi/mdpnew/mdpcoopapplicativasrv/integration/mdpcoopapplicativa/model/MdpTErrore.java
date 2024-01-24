/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.integration.mdpcoopapplicativa.model;

import java.io.Serializable;


public class MdpTErrore implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codiceErrore;

    private String descrizioneErrore;

    private Boolean flagMail;

    private Boolean flagRielaborazione;

    private String tipologia;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodiceErrore () {
        return codiceErrore;
    }

    public void setCodiceErrore ( String codiceErrore ) {
        this.codiceErrore = codiceErrore;
    }

    public String getDescrizioneErrore () {
        return descrizioneErrore;
    }

    public void setDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }

    public Boolean getFlagMail () {
        return flagMail;
    }

    public void setFlagMail ( Boolean flagMail ) {
        this.flagMail = flagMail;
    }

    public Boolean getFlagRielaborazione () {
        return flagRielaborazione;
    }

    public void setFlagRielaborazione ( Boolean flagRielaborazione ) {
        this.flagRielaborazione = flagRielaborazione;
    }

    public String getTipologia () {
        return tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

}
