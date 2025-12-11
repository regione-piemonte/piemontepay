/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpmultiiuv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per IuvComplex complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="IuvComplex"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="auxDigit" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="applicationCode" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="iuvOttico" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="iuv" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "IuvComplex", propOrder = {
				"auxDigit",
				"applicationCode",
				"iuvOttico",
				"iuv"
} )
@SuppressWarnings ( "unused" )
public class IuvComplex {

	protected String auxDigit;

	protected String applicationCode;

	protected String iuvOttico;

	protected String iuv;

	/**
	 * Recupera il valore della proprieta' auxDigit.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getAuxDigit () {
		return auxDigit;
	}

	/**
	 * Imposta il valore della proprieta' auxDigit.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setAuxDigit ( String value ) {
		this.auxDigit = value;
	}

	/**
	 * Recupera il valore della proprieta' applicationCode.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getApplicationCode () {
		return applicationCode;
	}

	/**
	 * Imposta il valore della proprieta' applicationCode.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setApplicationCode ( String value ) {
		this.applicationCode = value;
	}

	/**
	 * Recupera il valore della proprieta' iuvOttico.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIuvOttico () {
		return iuvOttico;
	}

	/**
	 * Imposta il valore della proprieta' iuvOttico.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIuvOttico ( String value ) {
		this.iuvOttico = value;
	}

	/**
	 * Recupera il valore della proprieta' iuv.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIuv () {
		return iuv;
	}

	/**
	 * Imposta il valore della proprieta' iuv.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIuv ( String value ) {
		this.iuv = value;
	}

	@Override
	public String toString () {
		return "{ " +
						"auxDigit:" + auxDigit +
						", applicationCode:" + applicationCode +
						", iuv:" + iuv +
						", iuvOttico:" + iuvOttico +
						" }";
	}
}
