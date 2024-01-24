/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;


public class ChiamataEsternaNonValida implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1123923913032199802L;

    private Long idChiamata;

    private String codiceChiamante;

    private String digest;

    private String iuv;

    private String codiceFiscale;

    private String identificativoPagamento;

    private Date timestampChiamata;

    private String descrizioneErrore;

    private String remoteHost;

    @Generated ( "SparkTools" )
    private ChiamataEsternaNonValida ( Builder builder ) {
        this.idChiamata = builder.idChiamata;
        this.codiceChiamante = builder.codiceChiamante;
        this.digest = builder.digest;
        this.iuv = builder.iuv;
        this.codiceFiscale = builder.codiceFiscale;
        this.identificativoPagamento = builder.identificativoPagamento;
        this.timestampChiamata = builder.timestampChiamata;
        this.descrizioneErrore = builder.descrizioneErrore;
        this.remoteHost = builder.remoteHost;
    }

    public Long getIdChiamata () {
        return idChiamata;
    }

    public void setIdChiamata ( Long idChiamata ) {
        this.idChiamata = idChiamata;
    }

    public String getCodiceChiamante () {
        return codiceChiamante;
    }

    public void setCodiceChiamante ( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }

    public String getDigest () {
        return digest;
    }

    public void setDigest ( String digest ) {
        this.digest = digest;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public String getCodiceFiscale () {
        return codiceFiscale;
    }

    public void setCodiceFiscale ( String codiceFiscale ) {
        this.codiceFiscale = codiceFiscale;
    }

    public Date getTimestampChiamata () {
        return timestampChiamata;
    }

    public void setTimestampChiamata ( Date timestampChiamata ) {
        this.timestampChiamata = timestampChiamata;
    }

    public String getRemoteHost () {
        return remoteHost;
    }

    public void setRemoteHost ( String remoteHost ) {
        this.remoteHost = remoteHost;
    }

    public String getDescrizioneErrore () {
        return descrizioneErrore;
    }

    public void setDescrizioneErrore ( String descrizioneErrore ) {
        this.descrizioneErrore = descrizioneErrore;
    }

    public String getIdentificativoPagamento () {
        return identificativoPagamento;
    }

    public void setIdentificativoPagamento ( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }

    /**
     * Creates builder to build {@link ChiamataEsternaNonValida}.
     * @return created builder
     */
    @Generated ( "SparkTools" )
    public static Builder builder () {
        return new Builder ();
    }

    /**
     * Builder to build {@link ChiamataEsternaNonValida}.
     */
    @Generated ( "SparkTools" )
    public static final class Builder {

        private Long idChiamata;

        private String codiceChiamante;

        private String digest;

        private String iuv;

        private String codiceFiscale;

        private String identificativoPagamento;

        private Date timestampChiamata;

        private String descrizioneErrore;

        private String remoteHost;

        private Builder () {
        }

        public Builder withIdChiamata ( Long idChiamata ) {
            this.idChiamata = idChiamata;
            return this;
        }

        public Builder withCodiceChiamante ( String codiceChiamante ) {
            this.codiceChiamante = codiceChiamante;
            return this;
        }

        public Builder withDigest ( String digest ) {
            this.digest = digest;
            return this;
        }

        public Builder withIuv ( String iuv ) {
            this.iuv = iuv;
            return this;
        }

        public Builder withCodiceFiscale ( String codiceFiscale ) {
            this.codiceFiscale = codiceFiscale;
            return this;
        }

        public Builder withIdentificativoPagamento ( String identificativoPagamento ) {
            this.identificativoPagamento = identificativoPagamento;
            return this;
        }

        public Builder withTimestampChiamata ( Date timestampChiamata ) {
            this.timestampChiamata = timestampChiamata;
            return this;
        }

        public Builder withDescrizioneErrore ( String descrizioneErrore ) {
            this.descrizioneErrore = descrizioneErrore;
            return this;
        }

        public Builder withRemoteHost ( String remoteHost ) {
            this.remoteHost = remoteHost;
            return this;
        }

        public ChiamataEsternaNonValida build () {
            return new ChiamataEsternaNonValida ( this );
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        return "ChiamataEsternaNonValida [idChiamata=" + idChiamata + ", codiceChiamante=" + codiceChiamante + ", iuv=" + iuv + ", codiceFiscale="
            + codiceFiscale + ", identificativoPagamento=" + identificativoPagamento + ", timestampChiamata=" + timestampChiamata + ", descrizioneErrore="
            + descrizioneErrore + ", remoteHost=" + remoteHost + "]";
    }

}
