/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "IuvComplex", namespace="http://mdpnew.csi.it/mdpiuv/")
@XmlAccessorType(XmlAccessType.FIELD)
public class IuvComplex {
	
	private String iuv;
	private byte[] iuvComplex;
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public byte[] getIuvComplex() {
		return iuvComplex;
	}
	public void setIuvComplex(byte[] iuvComplex) {
		this.iuvComplex = iuvComplex;
	}
}
