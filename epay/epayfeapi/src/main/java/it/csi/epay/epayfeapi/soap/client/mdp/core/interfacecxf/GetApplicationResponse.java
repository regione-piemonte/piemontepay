/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getApplicationResponse complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="getApplicationResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}application" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "getApplicationResponse", propOrder = {
				"_return"
} )
public class GetApplicationResponse {

	@XmlElement ( name = "return" )
	protected Application _return;

	/**
	 * Recupera il valore della proprieta' return.
	 *
	 * @return possible object is
	 * {@link Application }
	 */
	public Application getReturn () {
		return _return;
	}

	/**
	 * Imposta il valore della proprieta' return.
	 *
	 * @param value allowed object is
	 *              {@link Application }
	 */
	public void setReturn ( Application value ) {
		this._return = value;
	}

}
