/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per partAnComune complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="partAnComune"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cap" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="DStart" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="DStop" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="descComune" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="descProvincia" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="descRegione" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="idComune" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}long"/&gt;
 *         &lt;element name="idComuneNext" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}long"/&gt;
 *         &lt;element name="idComuneNextNull" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}boolean"/&gt;
 *         &lt;element name="idComunePrev" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}long"/&gt;
 *         &lt;element name="idComunePrevNull" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}boolean"/&gt;
 *         &lt;element name="istatComune" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="istatProvincia" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="istatRegione" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="RStatus" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="siglaProv" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "partAnComune", propOrder = {
				"cap",
				"dStart",
				"dStop",
				"descComune",
				"descProvincia",
				"descRegione",
				"idComune",
				"idComuneNext",
				"idComuneNextNull",
				"idComunePrev",
				"idComunePrevNull",
				"istatComune",
				"istatProvincia",
				"istatRegione",
				"rStatus",
				"siglaProv"
} )
@SuppressWarnings ( "unused" )
public class PartAnComune {

	protected String cap;

	@XmlElement ( name = "DStart" )
	@XmlSchemaType ( name = "dateTime" )
	protected XMLGregorianCalendar dStart;

	@XmlElement ( name = "DStop" )
	@XmlSchemaType ( name = "dateTime" )
	protected XMLGregorianCalendar dStop;

	protected String descComune;

	protected String descProvincia;

	protected String descRegione;

	protected long idComune;

	protected long idComuneNext;

	protected boolean idComuneNextNull;

	protected long idComunePrev;

	protected boolean idComunePrevNull;

	protected String istatComune;

	protected String istatProvincia;

	protected String istatRegione;

	@XmlElement ( name = "RStatus" )
	protected String rStatus;

	protected String siglaProv;

	/**
	 * Recupera il valore della proprieta' cap.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCap () {
		return cap;
	}

	/**
	 * Imposta il valore della proprieta' cap.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCap ( String value ) {
		this.cap = value;
	}

	/**
	 * Recupera il valore della proprieta' dStart.
	 *
	 * @return possible object is
	 * {@link XMLGregorianCalendar }
	 */
	public XMLGregorianCalendar getDStart () {
		return dStart;
	}

	/**
	 * Imposta il valore della proprieta' dStart.
	 *
	 * @param value allowed object is
	 *              {@link XMLGregorianCalendar }
	 */
	public void setDStart ( XMLGregorianCalendar value ) {
		this.dStart = value;
	}

	/**
	 * Recupera il valore della proprieta' dStop.
	 *
	 * @return possible object is
	 * {@link XMLGregorianCalendar }
	 */
	public XMLGregorianCalendar getDStop () {
		return dStop;
	}

	/**
	 * Imposta il valore della proprieta' dStop.
	 *
	 * @param value allowed object is
	 *              {@link XMLGregorianCalendar }
	 */
	public void setDStop ( XMLGregorianCalendar value ) {
		this.dStop = value;
	}

	/**
	 * Recupera il valore della proprieta' descComune.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDescComune () {
		return descComune;
	}

	/**
	 * Imposta il valore della proprieta' descComune.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDescComune ( String value ) {
		this.descComune = value;
	}

	/**
	 * Recupera il valore della proprieta' descProvincia.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDescProvincia () {
		return descProvincia;
	}

	/**
	 * Imposta il valore della proprieta' descProvincia.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDescProvincia ( String value ) {
		this.descProvincia = value;
	}

	/**
	 * Recupera il valore della proprieta' descRegione.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDescRegione () {
		return descRegione;
	}

	/**
	 * Imposta il valore della proprieta' descRegione.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDescRegione ( String value ) {
		this.descRegione = value;
	}

	/**
	 * Recupera il valore della proprieta' idComune.
	 */
	public long getIdComune () {
		return idComune;
	}

	/**
	 * Imposta il valore della proprieta' idComune.
	 */
	public void setIdComune ( long value ) {
		this.idComune = value;
	}

	/**
	 * Recupera il valore della proprieta' idComuneNext.
	 */
	public long getIdComuneNext () {
		return idComuneNext;
	}

	/**
	 * Imposta il valore della proprieta' idComuneNext.
	 */
	public void setIdComuneNext ( long value ) {
		this.idComuneNext = value;
	}

	/**
	 * Recupera il valore della proprieta' idComuneNextNull.
	 */
	public boolean isIdComuneNextNull () {
		return idComuneNextNull;
	}

	/**
	 * Imposta il valore della proprieta' idComuneNextNull.
	 */
	public void setIdComuneNextNull ( boolean value ) {
		this.idComuneNextNull = value;
	}

	/**
	 * Recupera il valore della proprieta' idComunePrev.
	 */
	public long getIdComunePrev () {
		return idComunePrev;
	}

	/**
	 * Imposta il valore della proprieta' idComunePrev.
	 */
	public void setIdComunePrev ( long value ) {
		this.idComunePrev = value;
	}

	/**
	 * Recupera il valore della proprieta' idComunePrevNull.
	 */
	public boolean isIdComunePrevNull () {
		return idComunePrevNull;
	}

	/**
	 * Imposta il valore della proprieta' idComunePrevNull.
	 */
	public void setIdComunePrevNull ( boolean value ) {
		this.idComunePrevNull = value;
	}

	/**
	 * Recupera il valore della proprieta' istatComune.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIstatComune () {
		return istatComune;
	}

	/**
	 * Imposta il valore della proprieta' istatComune.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIstatComune ( String value ) {
		this.istatComune = value;
	}

	/**
	 * Recupera il valore della proprieta' istatProvincia.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIstatProvincia () {
		return istatProvincia;
	}

	/**
	 * Imposta il valore della proprieta' istatProvincia.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIstatProvincia ( String value ) {
		this.istatProvincia = value;
	}

	/**
	 * Recupera il valore della proprieta' istatRegione.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIstatRegione () {
		return istatRegione;
	}

	/**
	 * Imposta il valore della proprieta' istatRegione.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIstatRegione ( String value ) {
		this.istatRegione = value;
	}

	/**
	 * Recupera il valore della proprieta' rStatus.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getRStatus () {
		return rStatus;
	}

	/**
	 * Imposta il valore della proprieta' rStatus.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setRStatus ( String value ) {
		this.rStatus = value;
	}

	/**
	 * Recupera il valore della proprieta' siglaProv.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSiglaProv () {
		return siglaProv;
	}

	/**
	 * Imposta il valore della proprieta' siglaProv.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSiglaProv ( String value ) {
		this.siglaProv = value;
	}

}
