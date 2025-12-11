/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getErroriNodo complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="getErroriNodo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applicationId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="transactionId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "getErroriNodo", propOrder = {
				"applicationId",
				"transactionId"
} )
@SuppressWarnings ( "unused" )
public class GetErroriNodo {

	protected String applicationId;

	protected String transactionId;

	/**
	 * Recupera il valore della proprieta' applicationId.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getApplicationId () {
		return applicationId;
	}

	/**
	 * Imposta il valore della proprieta' applicationId.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setApplicationId ( String value ) {
		this.applicationId = value;
	}

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
