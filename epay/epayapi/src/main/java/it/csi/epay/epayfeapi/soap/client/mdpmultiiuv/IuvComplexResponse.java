/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdpmultiiuv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per IuvComplexResponse complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="IuvComplexResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceIdentificativoEnte" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVersamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="applicationID" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="timestamp" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="mac" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="iuvComplex" type="{<a href="http://mdpnew.csi.it/mdpmultiiuv/">...</a>}IuvComplex" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "IuvComplexResponse", propOrder = {
				"codiceIdentificativoEnte",
				"codiceVersamento",
				"applicationID",
				"timestamp",
				"mac",
				"iuvComplex"
} )
@SuppressWarnings ( "unused" )
public class IuvComplexResponse {

	protected String codiceIdentificativoEnte;

	protected String codiceVersamento;

	protected String applicationID;

	protected String timestamp;

	protected String mac;

	@XmlElement ( nillable = true )
	protected List<IuvComplex> iuvComplex;

	/**
	 * Recupera il valore della proprieta' codiceIdentificativoEnte.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCodiceIdentificativoEnte () {
		return codiceIdentificativoEnte;
	}

	/**
	 * Imposta il valore della proprieta' codiceIdentificativoEnte.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCodiceIdentificativoEnte ( String value ) {
		this.codiceIdentificativoEnte = value;
	}

	/**
	 * Recupera il valore della proprieta' codiceVersamento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCodiceVersamento () {
		return codiceVersamento;
	}

	/**
	 * Imposta il valore della proprieta' codiceVersamento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCodiceVersamento ( String value ) {
		this.codiceVersamento = value;
	}

	/**
	 * Recupera il valore della proprieta' applicationID.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getApplicationID () {
		return applicationID;
	}

	/**
	 * Imposta il valore della proprieta' applicationID.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setApplicationID ( String value ) {
		this.applicationID = value;
	}

	/**
	 * Recupera il valore della proprieta' timestamp.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTimestamp () {
		return timestamp;
	}

	/**
	 * Imposta il valore della proprieta' timestamp.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTimestamp ( String value ) {
		this.timestamp = value;
	}

	/**
	 * Recupera il valore della proprieta' mac.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getMac () {
		return mac;
	}

	/**
	 * Imposta il valore della proprieta' mac.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setMac ( String value ) {
		this.mac = value;
	}

	/**
	 * Gets the value of the iuvComplex property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the iuvComplex property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getIuvComplex().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link IuvComplex }
	 */
	public List<IuvComplex> getIuvComplex () {
		if ( iuvComplex == null ) {
			iuvComplex = new ArrayList<> ();
		}
		return this.iuvComplex;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString () {
		return "IuvComplexResponse [codiceIdentificativoEnte=" + codiceIdentificativoEnte + ", codiceVersamento=" + codiceVersamento + ", applicationID="
						+ applicationID + ", timestamp=" + timestamp + ", mac=" + mac + ", iuvComplex=" + iuvComplex + "]";
	}

}
