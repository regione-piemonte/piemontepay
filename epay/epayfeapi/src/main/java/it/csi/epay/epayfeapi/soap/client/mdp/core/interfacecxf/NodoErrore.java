/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per nodoErrore complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="nodoErrore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applicationId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="data" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *         &lt;element name="transactionId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "nodoErrore", propOrder = {
				"applicationId",
				"data",
				"descrizione",
				"id",
				"transactionId"
} )
@SuppressWarnings ( "unused" )
public class NodoErrore {

	protected String applicationId;

	@XmlSchemaType ( name = "dateTime" )
	protected XMLGregorianCalendar data;

	protected String descrizione;

	protected Integer id;

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
	 * Recupera il valore della proprieta' data.
	 *
	 * @return possible object is
	 * {@link XMLGregorianCalendar }
	 */
	public XMLGregorianCalendar getData () {
		return data;
	}

	/**
	 * Imposta il valore della proprieta' data.
	 *
	 * @param value allowed object is
	 *              {@link XMLGregorianCalendar }
	 */
	public void setData ( XMLGregorianCalendar value ) {
		this.data = value;
	}

	/**
	 * Recupera il valore della proprieta' descrizione.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDescrizione () {
		return descrizione;
	}

	/**
	 * Imposta il valore della proprieta' descrizione.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDescrizione ( String value ) {
		this.descrizione = value;
	}

	/**
	 * Recupera il valore della proprieta' id.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getId () {
		return id;
	}

	/**
	 * Imposta il valore della proprieta' id.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setId ( Integer value ) {
		this.id = value;
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
