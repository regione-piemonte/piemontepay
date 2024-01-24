/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;


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


}
