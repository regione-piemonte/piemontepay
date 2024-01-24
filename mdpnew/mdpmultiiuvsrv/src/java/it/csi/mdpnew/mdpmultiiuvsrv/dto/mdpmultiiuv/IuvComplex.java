/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "IuvComplex", namespace = "http://mdpnew.csi.it/mdpmultiiuv/")
@XmlAccessorType(XmlAccessType.FIELD)
public class IuvComplex {

	private String auxDigit;
	private String applicationCode;
	private String iuvOttico;
	private String iuv;
//	private String codiceSegregazione;

	public String getAuxDigit() {
		return auxDigit;
	}

	public void setAuxDigit(String auxDigit) {
		this.auxDigit = auxDigit;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getIuvOttico() {
		return iuvOttico;
	}

	public void setIuvOttico(String iuvOttico) {
		this.iuvOttico = iuvOttico;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

//	public String getCodiceSegregazione() {
//		return codiceSegregazione;
//	}
//
//	public void setCodiceSegregazione(String codiceSegregazione) {
//		this.codiceSegregazione = codiceSegregazione;
//	}

}
