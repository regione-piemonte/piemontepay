/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "IuvComplexResponse", namespace = "http://mdpnew.csi.it/mdpmultiiuv/")
@XmlAccessorType(XmlAccessType.FIELD)
public class IuvComplexResponse {

	private String codiceIdentificativoEnte;
	private String codiceVersamento;
	private String applicationID;
	private String timestamp;
	private String mac;
	private List<IuvComplex> iuvComplex;

	public String getCodiceIdentificativoEnte() {
		return codiceIdentificativoEnte;
	}

	public void setCodiceIdentificativoEnte(String codiceIdentificativoEnte) {
		this.codiceIdentificativoEnte = codiceIdentificativoEnte;
	}

	public String getCodiceVersamento() {
		return codiceVersamento;
	}

	public void setCodiceVersamento(String codiceVersamento) {
		this.codiceVersamento = codiceVersamento;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<IuvComplex> getIuvComplex() {
		return iuvComplex;
	}

	public void setIuvComplex(List<IuvComplex> iuvComplex) {
		this.iuvComplex = iuvComplex;
	}

}
