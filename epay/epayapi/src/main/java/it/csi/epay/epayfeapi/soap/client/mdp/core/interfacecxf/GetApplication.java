/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getApplication complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="getApplication"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="appId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "getApplication", propOrder = {
				"appId"
} )
@SuppressWarnings ( "unused" )
public class GetApplication {

	protected String appId;

	/**
	 * Recupera il valore della proprieta' appId.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getAppId () {
		return appId;
	}

	/**
	 * Imposta il valore della proprieta' appId.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setAppId ( String value ) {
		this.appId = value;
	}

}
