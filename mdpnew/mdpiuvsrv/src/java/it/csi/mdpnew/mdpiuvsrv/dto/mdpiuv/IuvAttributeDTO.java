/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv;

import java.math.BigInteger;

/**
 * 
 * @author Paolo
 *
 */
public class IuvAttributeDTO extends AbstractDTO{
	

	private String idEnte;
	private String dataValidita;
	private BigInteger progressivo;
	private String dataDismissione;
	
	
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
	
}
