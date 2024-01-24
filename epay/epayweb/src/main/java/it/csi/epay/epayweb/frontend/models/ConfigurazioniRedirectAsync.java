/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayweb.frontend.models;

import java.io.Serializable;


public class ConfigurazioniRedirectAsync extends _Model implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean email;

    private Boolean ripetiEmail;

    private Boolean verificaPrivacy;

    private Boolean verificaCaptcha;

    public Boolean getEmail () {
        return email;
    }

    public void setEmail ( Boolean email ) {
        this.email = email;
    }

    public Boolean getRipetiEmail () {
        return ripetiEmail;
    }

    public void setRipetiEmail ( Boolean ripetiEmail ) {
        this.ripetiEmail = ripetiEmail;
    }

    public Boolean getVerificaPrivacy () {
        return verificaPrivacy;
    }

    public void setVerificaPrivacy ( Boolean verificaPrivacy ) {
        this.verificaPrivacy = verificaPrivacy;
    }

    public Boolean getVerificaCaptcha () {
        return verificaCaptcha;
    }

    public void setVerificaCaptcha ( Boolean verificaCaptcha ) {
        this.verificaCaptcha = verificaCaptcha;
    }

}
