/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpiuv;

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
 *         &lt;element name="iuv" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="iuvComplex" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "IuvComplex", propOrder = {
				"iuv",
				"iuvComplex"
} )
@SuppressWarnings ( "unused" )
public class IuvComplex {

	protected String iuv;

	protected byte[] iuvComplex;

	/**
	 * Recupera il valore della proprieta iuv.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIuv () {
		return iuv;
	}

	/**
	 * Imposta il valore della proprieta iuv.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIuv ( String value ) {
		this.iuv = value;
	}

	/**
	 * Recupera il valore della proprieta iuvComplex.
	 *
	 * @return possible object is
	 * byte[]
	 */
	public byte[] getIuvComplex () {
		return iuvComplex;
	}

	/**
	 * Imposta il valore della proprieta iuvComplex.
	 *
	 * @param value allowed object is
	 *              byte[]
	 */
	public void setIuvComplex ( byte[] value ) {
		this.iuvComplex = value;
	}

}
