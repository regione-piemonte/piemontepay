/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.dto.epaymdpservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ParametriVerificaDatiPagamento", namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParametriVerificaDatiPagamento {
	
	public String iuv;
	public String timestamp; //Formato ddmmyyyy-hh:mm:ss:ms
	public String mac;
	
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
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

}
