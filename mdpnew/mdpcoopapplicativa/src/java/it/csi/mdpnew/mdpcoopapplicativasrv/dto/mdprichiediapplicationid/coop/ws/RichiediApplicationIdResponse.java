/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResponseType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applicationId" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *         &lt;element name="esito" type="{http://www.csi.it/epay/epaywso/types}String35Type"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applicationId",		
    "esito"
})
@XmlRootElement(name = "RichiediApplicationIdResponse")
public class RichiediApplicationIdResponse {
	
	@XmlElement(name = "applicationId", required = true)
    protected String applicationId;
    @XmlElement(required = true)
    protected ResponseType esito;
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public ResponseType getEsito() {
		return esito;
	}
	public void setEsito(ResponseType esito) {
		this.esito = esito;
	}


}
