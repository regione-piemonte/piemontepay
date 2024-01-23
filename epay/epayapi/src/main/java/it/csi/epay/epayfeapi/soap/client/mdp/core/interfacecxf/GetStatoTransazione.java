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
 * <p>Classe Java per getStatoTransazione complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="getStatoTransazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="transaction_id" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "getStatoTransazione", propOrder = {
				"transactionId"
} )
public class GetStatoTransazione {

	@XmlElement ( name = "transaction_id" )
	protected String transactionId;

	/**
	 * Recupera il valore della proprieta' transactionId.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTransactionId () {
		return transactionId;
	}

	/**
	 * Imposta il valore della proprieta' transactionId.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTransactionId ( String value ) {
		this.transactionId = value;
	}

}
