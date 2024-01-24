/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdp.mdppagopaapi.dto.common;

import javax.annotation.Generated;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 *
 */

public class ErrorMessageDTO {

    private String codice;

    private Integer status;

    private String messaggio;

    @JsonIgnore
    private Exception errore;

    private String dettagli;

    @Generated ( "SparkTools" )
    private ErrorMessageDTO ( Builder builder ) {
        codice = builder.codice;
        status = builder.status;
        messaggio = builder.messaggio;
        errore = builder.errore;
        dettagli = builder.dettagli;
    }

    public String getDettagli () {
        return dettagli;
    }

    public void setDettagli ( String dettagli ) {
        this.dettagli = dettagli;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus ( Integer status ) {
        this.status = status;
    }

    public String getMessaggio () {
        return messaggio;
    }

    public void setMessaggio ( String messaggio ) {
        this.messaggio = messaggio;
    }

    public Exception getErrore () {
        return errore;
    }

    public void setErrore ( Exception errore ) {
        this.errore = errore;
    }

    /**
     * Creates builder to build {@link ErrorMessageDTO}.
     *
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link ErrorMessageDTO}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private String codice;

        private Integer status;

        private String messaggio;

        private Exception errore;

        private String dettagli;

        private Builder () {
        }

        public Builder withCodice ( String codice ) {
            this.codice = codice;
            return this;
        }

        public Builder withStatus ( Integer status ) {
            this.status = status;
            return this;
        }

        public Builder withMessaggio ( String messaggio ) {
            this.messaggio = messaggio;
            return this;
        }

        public Builder withErrore ( Exception errore ) {
            this.errore = errore;
            return this;
        }

        public Builder withDettagli ( String dettagli ) {
            this.dettagli = dettagli;
            return this;
        }

        public ErrorMessageDTO build () {
            return new ErrorMessageDTO ( this );
        }
    }

}
