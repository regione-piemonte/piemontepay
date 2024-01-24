/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;


@SuppressWarnings ( "unused" )
public class EnteDTO implements Serializable {

	private static final long serialVersionUID = -4624271577330296227L;

	private Long idEnte;

	private byte[] logo;

	private String nome;

	private String codiceFiscale;

	private BigDecimal codiceGs1Gln;

	private String orari;

	private Boolean flagInvioPagamenti;

	private String codiceInterbancario;

	private Boolean flagRiconciliazioneVersamenti;

	private byte[] passphrase;

	public Long getIdEnte () {
		return idEnte;
	}

	public void setIdEnte ( Long idEnte ) {
		this.idEnte = idEnte;
	}

	public byte[] getLogo () {
		return logo;
	}

	public void setLogo ( byte[] logo ) {
		this.logo = logo;
	}

	public String getNome () {
		return nome;
	}

	public void setNome ( String nome ) {
		this.nome = nome;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public BigDecimal getCodiceGs1Gln () {
		return codiceGs1Gln;
	}

	public void setCodiceGs1Gln ( BigDecimal codiceGs1Gln ) {
		this.codiceGs1Gln = codiceGs1Gln;
	}

	public String getOrari () {
		return orari;
	}

	public void setOrari ( String orari ) {
		this.orari = orari;
	}

	public Boolean getFlagInvioPagamenti () {
		return flagInvioPagamenti;
	}

	public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
		this.flagInvioPagamenti = flagInvioPagamenti;
	}

	public String getCodiceInterbancario () {
		return codiceInterbancario;
	}

	public void setCodiceInterbancario ( String codiceInterbancario ) {
		this.codiceInterbancario = codiceInterbancario;
	}

	public Boolean getFlagRiconciliazioneVersamenti () {
		return flagRiconciliazioneVersamenti;
	}

	public void setFlagRiconciliazioneVersamenti ( Boolean flagRiconciliazioneVersamenti ) {
		this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
	}

	public byte[] getPassphrase () {
		return passphrase;
	}

	public void setPassphrase ( byte[] passphrase ) {
		this.passphrase = passphrase;
	}
}
