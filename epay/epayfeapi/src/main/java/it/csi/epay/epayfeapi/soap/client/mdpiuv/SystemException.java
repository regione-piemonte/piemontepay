/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpiuv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SystemException complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="SystemException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nestedExcClassName" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="stackTraceMessage" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="nestedExcMsg" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "SystemException", propOrder = {
				"nestedExcClassName",
				"stackTraceMessage",
				"nestedExcMsg"
} )
@SuppressWarnings ( "unused" )
public class SystemException {

	@XmlElement ( required = true, nillable = true )
	protected String nestedExcClassName;

	@XmlElement ( required = true, nillable = true )
	protected String stackTraceMessage;

	@XmlElement ( required = true, nillable = true )
	protected String nestedExcMsg;

	/**
	 * Recupera il valore della proprieta nestedExcClassName.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getNestedExcClassName () {
		return nestedExcClassName;
	}

	/**
	 * Imposta il valore della proprieta nestedExcClassName.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setNestedExcClassName ( String value ) {
		this.nestedExcClassName = value;
	}

	/**
	 * Recupera il valore della proprieta stackTraceMessage.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getStackTraceMessage () {
		return stackTraceMessage;
	}

	/**
	 * Imposta il valore della proprieta stackTraceMessage.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setStackTraceMessage ( String value ) {
		this.stackTraceMessage = value;
	}

	/**
	 * Recupera il valore della proprieta nestedExcMsg.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getNestedExcMsg () {
		return nestedExcMsg;
	}

	/**
	 * Imposta il valore della proprieta nestedExcMsg.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setNestedExcMsg ( String value ) {
		this.nestedExcMsg = value;
	}

}
