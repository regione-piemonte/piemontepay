/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.sql.Timestamp;

public class ChiamataEsternaSincronaNonValida {
	
	private Long idChiamata;
	
	private String codiceChiamante;
	
	private String iuv;
	
	private String causale;
	
	private String identificativoPagamento;
	
    private Timestamp timestampChiamata;

    private String remoteHost;
    
    private String descrizioneErrore;
    
	private String codiceFiscaleEnte;

	
	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

	public Long getIdChiamata() {
		return idChiamata;
	}

	public void setIdChiamata(Long idChiamata) {
		this.idChiamata = idChiamata;
	}

	public String getCodiceChiamante() {
		return codiceChiamante;
	}

	public void setCodiceChiamante(String codiceChiamante) {
		this.codiceChiamante = codiceChiamante;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getIdentificativoPagamento() {
		return identificativoPagamento;
	}

	public void setIdentificativoPagamento(String identificativoPagamento) {
		this.identificativoPagamento = identificativoPagamento;
	}

	public Timestamp getTimestampChiamata() {
		return timestampChiamata;
	}

	public void setTimestampChiamata(Timestamp timestampChiamata) {
		this.timestampChiamata = timestampChiamata;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getDescrizioneErrore() {
		return descrizioneErrore;
	}

	public void setDescrizioneErrore(String descrizioneErrore) {
		this.descrizioneErrore = descrizioneErrore;
	}

	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}
	

}
