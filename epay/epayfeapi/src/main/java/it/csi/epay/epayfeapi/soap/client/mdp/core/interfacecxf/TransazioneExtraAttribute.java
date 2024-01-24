/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per transazioneExtraAttribute complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="transazioneExtraAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="extraAttributeId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}long"/&gt;
 *         &lt;element name="name" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="transactionId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="value" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "transazioneExtraAttribute", propOrder = {
				"extraAttributeId",
				"name",
				"transactionId",
				"value"
} )
@SuppressWarnings ( "unused" )
public class TransazioneExtraAttribute {

	protected long extraAttributeId;

	protected String name;

	protected String transactionId;

	protected String value;

	/**
	 * Recupera il valore della proprieta' extraAttributeId.
	 */
	public long getExtraAttributeId () {
		return extraAttributeId;
	}

	/**
	 * Imposta il valore della proprieta' extraAttributeId.
	 */
	public void setExtraAttributeId ( long value ) {
		this.extraAttributeId = value;
	}

	/**
	 * Recupera il valore della proprieta' name.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getName () {
		return name;
	}

	/**
	 * Imposta il valore della proprieta' name.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setName ( String value ) {
		this.name = value;
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

	/**
	 * Recupera il valore della proprieta' value.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getValue () {
		return value;
	}

	/**
	 * Imposta il valore della proprieta' value.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setValue ( String value ) {
		this.value = value;
	}

}
