/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;
import java.sql.Timestamp;


public class TracciabilitaChiamataEsternaNonValida implements Serializable {

	private static final long serialVersionUID = -199584103803318739L;

	private String codiceChiamante;
	private String codiceFiscale;
	private Timestamp timestampChiamata;
	private String remoteHost;
	private String descrizioneErrore;
	private String identificativoPagamento;
	private String iuv;

	public String getCodiceChiamante () {
		return codiceChiamante;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		this.codiceChiamante = codiceChiamante;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public Timestamp getTimestampChiamata () {
		return timestampChiamata;
	}

	public void setTimestampChiamata ( Timestamp timestampChiamata ) {
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
     * @return the iuv
     */
    public String getIuv () {
        return iuv;
    }

    /**
     * @param iuv the iuv to set
     */
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }
	
}
