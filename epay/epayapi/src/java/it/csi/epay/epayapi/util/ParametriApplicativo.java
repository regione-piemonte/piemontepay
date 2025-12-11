/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.util;

/**
 *
 */

public enum ParametriApplicativo {
        FILTER_CLIENT_AUTH_ENABLED ( "filter.clientauth.enabled" ),
        FILTER_CLIENT_AUTH_BYPASSED ( "filter.clientauth.bypassed" ),
        FILTER_CLIENT_AUTH_CHECK_PASSPHRASE ( "filter.clientauth.checkpassphrase" ),
        NOME_AMBIENTE ( "nome.ambiente", ExposurePolicy.EXTERNAL ),
        ;

    private String codice;

    private String valoreDefault;

    private Boolean obbligatorio;

    private ExposurePolicy policyEsposizione = ExposurePolicy.PUBLIC;

    public String getValoreDefault () {
        return valoreDefault;
    }

    public String getCodice () {
        return codice;
    }

    public Boolean getObbligatorio () {
        return obbligatorio;
    }

    public ExposurePolicy getPolicyEsposizione () {
        return policyEsposizione;
    }

    private ParametriApplicativo ( String codice ) {
        this.codice = codice;
        obbligatorio = true;
    }

    private ParametriApplicativo ( String codice, ExposurePolicy exposurePolicy ) {
        this.codice = codice;
        obbligatorio = true;
        policyEsposizione = exposurePolicy;
    }

    private ParametriApplicativo ( String codice, Boolean obbligatorio ) {
        this.codice = codice;
        this.obbligatorio = obbligatorio;
    }

    private ParametriApplicativo ( String codice, String valoreDefault ) {
        this.codice = codice;
        this.valoreDefault = valoreDefault;
        obbligatorio = true;
    }

    private ParametriApplicativo ( String codice, String valoreDefault, ExposurePolicy exposurePolicy ) {
        this.codice = codice;
        this.valoreDefault = valoreDefault;
        obbligatorio = true;
        policyEsposizione = exposurePolicy;
    }

    private ParametriApplicativo ( String codice, Boolean obbligatorio, String valoreDefault ) {
        this.codice = codice;
        this.valoreDefault = valoreDefault;
        this.obbligatorio = obbligatorio;
    }

    public enum ExposurePolicy {
            /**
             * Valore di default: parametro non riservato, disponibile solo a backend
             */
            PUBLIC,

            /**
             * Valore disponibile solo a backend e considerato sensibile. Non verra' mostrato in nessun log ne' esposto all'esterno dell'applicativo
             */
            SENSIBLE,

            /**
             * Valore disponibile ed esposto anche a frontend
             */
            EXTERNAL
    }
}
