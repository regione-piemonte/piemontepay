/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


/**
 * Richiesta di stampa Rt.
 */

public class GetRTChiamanteEsternoOutput extends AccessoChiamanteEsternoSincronoSplitOutput implements Serializable {

	private static final long serialVersionUID = 274014385003313666L;

	private String descrizioneStatoPagamento;

	private String iuvOriginario;

	private String iuvEffettivo;

	private byte[] ricevutaPdf;

	private byte[] rtXml;

	// e' il tipo della ricevuta
	private Ricevuta ricevuta;

	public byte[] getRicevutaPdf () {
		return ricevutaPdf;
	}

	public void setRicevutaPdf ( byte[] ricevutaPdf ) {
		this.ricevutaPdf = ricevutaPdf;
	}

	public byte[] getRtXml () {
		return rtXml;
	}

	public void setRtXml ( byte[] rtXml ) {
		this.rtXml = rtXml;
	}

	public String getDescrizioneStatoPagamento () {
		return descrizioneStatoPagamento;
	}

	public void setDescrizioneStatoPagamento ( String descrizioneStatoPagamento ) {
		this.descrizioneStatoPagamento = descrizioneStatoPagamento;
	}

	public String getIuvOriginario () {
		return iuvOriginario;
	}

	public void setIuvOriginario ( String iuvOriginario ) {
		this.iuvOriginario = iuvOriginario;
	}

	public String getIuvEffettivo () {
		return iuvEffettivo;
	}

	public void setIuvEffettivo ( String iuvEffettivo ) {
		this.iuvEffettivo = iuvEffettivo;
	}

	public Ricevuta getRicevuta () {
		return ricevuta;
	}

	public void setRicevuta ( Ricevuta ricevuta ) {
		this.ricevuta = ricevuta;
	}

	@Override
	public String toString () {

		return "GetRTChiamanteEsternoOutput ["
						+ " identificativoPagamento=" + StringUtils.trimToEmpty ( getIdentificativoPagamento () )
						+ " codiceEsito=" + StringUtils.trimToEmpty ( getCodiceEsito () )
						+ " descrizioneEsito=" + StringUtils.trimToEmpty ( getDescrizioneEsito () )
						+ " descrizioneStatoPagamento=" + StringUtils.trimToEmpty ( getDescrizioneStatoPagamento () )
						+ " iuvOriginario=" + StringUtils.trimToEmpty ( getIuvOriginario () )
						+ " iuvEffettivo=" + StringUtils.trimToEmpty ( getIuvEffettivo () )
						+ " tipoRicevuta=" + StringUtils.trimToEmpty ( ricevuta.name () )
						+ "]";
	}

}
