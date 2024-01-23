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
 * <p>Classe Java per application complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="application"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applicationname" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="cliente" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="esercemail" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="note" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="numeroverde" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="progetto" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="redirectNewmdp" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}long"/&gt;
 *         &lt;element name="referentecsi" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="validoAl" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="validoDal" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "application", propOrder = {
				"applicationname",
				"cliente",
				"esercemail",
				"id",
				"note",
				"numeroverde",
				"progetto",
				"redirectNewmdp",
				"referentecsi",
				"validoAl",
				"validoDal"
} )
@SuppressWarnings ( "unused" )
public class Application {

	protected String applicationname;

	protected String cliente;

	protected String esercemail;

	protected String id;

	protected String note;

	protected String numeroverde;

	protected String progetto;

	protected long redirectNewmdp;

	protected String referentecsi;

	@XmlSchemaType ( name = "dateTime" )
	protected XMLGregorianCalendar validoAl;

	@XmlSchemaType ( name = "dateTime" )
	protected XMLGregorianCalendar validoDal;

	/**
	 * Recupera il valore della proprieta' applicationname.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getApplicationname () {
		return applicationname;
	}

	/**
	 * Imposta il valore della proprieta' applicationname.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setApplicationname ( String value ) {
		this.applicationname = value;
	}

	/**
	 * Recupera il valore della proprieta' cliente.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCliente () {
		return cliente;
	}

	/**
	 * Imposta il valore della proprieta' cliente.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCliente ( String value ) {
		this.cliente = value;
	}

	/**
	 * Recupera il valore della proprieta' esercemail.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getEsercemail () {
		return esercemail;
	}

	/**
	 * Imposta il valore della proprieta' esercemail.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setEsercemail ( String value ) {
		this.esercemail = value;
	}

	/**
	 * Recupera il valore della proprieta' id.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getId () {
		return id;
	}

	/**
	 * Imposta il valore della proprieta' id.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setId ( String value ) {
		this.id = value;
	}

	/**
	 * Recupera il valore della proprieta' note.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getNote () {
		return note;
	}

	/**
	 * Imposta il valore della proprieta' note.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setNote ( String value ) {
		this.note = value;
	}

	/**
	 * Recupera il valore della proprieta' numeroverde.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getNumeroverde () {
		return numeroverde;
	}

	/**
	 * Imposta il valore della proprieta' numeroverde.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setNumeroverde ( String value ) {
		this.numeroverde = value;
	}

	/**
	 * Recupera il valore della proprieta' progetto.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getProgetto () {
		return progetto;
	}

	/**
	 * Imposta il valore della proprieta' progetto.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setProgetto ( String value ) {
		this.progetto = value;
	}

	/**
	 * Recupera il valore della proprieta' redirectNewmdp.
	 */
	public long getRedirectNewmdp () {
		return redirectNewmdp;
	}

	/**
	 * Imposta il valore della proprieta' redirectNewmdp.
	 */
	public void setRedirectNewmdp ( long value ) {
		this.redirectNewmdp = value;
	}

	/**
	 * Recupera il valore della proprieta' referentecsi.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getReferentecsi () {
		return referentecsi;
	}

	/**
	 * Imposta il valore della proprieta' referentecsi.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setReferentecsi ( String value ) {
		this.referentecsi = value;
	}

	/**
	 * Recupera il valore della proprieta' validoAl.
	 *
	 * @return possible object is
	 * {@link XMLGregorianCalendar }
	 */
	public XMLGregorianCalendar getValidoAl () {
		return validoAl;
	}

	/**
	 * Imposta il valore della proprieta' validoAl.
	 *
	 * @param value allowed object is
	 *              {@link XMLGregorianCalendar }
	 */
	public void setValidoAl ( XMLGregorianCalendar value ) {
		this.validoAl = value;
	}

	/**
	 * Recupera il valore della proprieta' validoDal.
	 *
	 * @return possible object is
	 * {@link XMLGregorianCalendar }
	 */
	public XMLGregorianCalendar getValidoDal () {
		return validoDal;
	}

	/**
	 * Imposta il valore della proprieta' validoDal.
	 *
	 * @param value allowed object is
	 *              {@link XMLGregorianCalendar }
	 */
	public void setValidoDal ( XMLGregorianCalendar value ) {
		this.validoDal = value;
	}

}
