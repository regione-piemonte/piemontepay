/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv;

import java.math.BigInteger;

public class IuvAttributeDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String idEnte;
	private String dataValidita;
	private BigInteger progressivo;
	private String dataDismissione;
	private BigInteger progressivoOttico;

	public String getIdEnte() {
		return idEnte;
	}

	public void setIdEnte(String idEnte) {
		this.idEnte = idEnte;
	}

	public String getDataValidita() {
		return dataValidita;
	}

	public void setDataValidita(String dataValidita) {
		this.dataValidita = dataValidita;
	}

	public BigInteger getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(BigInteger progressivo) {
		this.progressivo = progressivo;
	}

	public String getDataDismissione() {
		return dataDismissione;
	}

	public void setDataDismissione(String dataDismissione) {
		this.dataDismissione = dataDismissione;
	}

	public BigInteger getProgressivoOttico() {
		return progressivoOttico;
	}

	public void setProgressivoOttico(BigInteger progressivoOttico) {
		this.progressivoOttico = progressivoOttico;
	}

}
