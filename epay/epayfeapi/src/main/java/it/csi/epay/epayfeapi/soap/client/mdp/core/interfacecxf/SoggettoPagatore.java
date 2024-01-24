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
 * <p>Classe Java per SoggettoPagatore complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="SoggettoPagatore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identificativoUnivocoPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="tipoIdentificativoUnivocoPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="anagraficaPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="indirizzoPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="civicoPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="capPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="localitaPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="provinciaPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="nazionePagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="e-mailPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "SoggettoPagatore", propOrder = {
				"identificativoUnivocoPagatore",
				"tipoIdentificativoUnivocoPagatore",
				"anagraficaPagatore",
				"indirizzoPagatore",
				"civicoPagatore",
				"capPagatore",
				"localitaPagatore",
				"provinciaPagatore",
				"nazionePagatore",
				"eMailPagatore"
} )
@SuppressWarnings ( "unused" )
public class SoggettoPagatore {

	@XmlElement ( required = true )
	protected String identificativoUnivocoPagatore;

	protected String tipoIdentificativoUnivocoPagatore;

	@XmlElement ( required = true )
	protected String anagraficaPagatore;

	protected String indirizzoPagatore;

	protected String civicoPagatore;

	protected String capPagatore;

	protected String localitaPagatore;

	protected String provinciaPagatore;

	protected String nazionePagatore;

	@XmlElement ( name = "e-mailPagatore" )
	protected String eMailPagatore;

	/**
	 * Recupera il valore della proprieta' identificativoUnivocoPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIdentificativoUnivocoPagatore () {
		return identificativoUnivocoPagatore;
	}

	/**
	 * Imposta il valore della proprieta' identificativoUnivocoPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIdentificativoUnivocoPagatore ( String value ) {
		this.identificativoUnivocoPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' tipoIdentificativoUnivocoPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTipoIdentificativoUnivocoPagatore () {
		return tipoIdentificativoUnivocoPagatore;
	}

	/**
	 * Imposta il valore della proprieta' tipoIdentificativoUnivocoPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTipoIdentificativoUnivocoPagatore ( String value ) {
		this.tipoIdentificativoUnivocoPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' anagraficaPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getAnagraficaPagatore () {
		return anagraficaPagatore;
	}

	/**
	 * Imposta il valore della proprieta' anagraficaPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setAnagraficaPagatore ( String value ) {
		this.anagraficaPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' indirizzoPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIndirizzoPagatore () {
		return indirizzoPagatore;
	}

	/**
	 * Imposta il valore della proprieta' indirizzoPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIndirizzoPagatore ( String value ) {
		this.indirizzoPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' civicoPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCivicoPagatore () {
		return civicoPagatore;
	}

	/**
	 * Imposta il valore della proprieta' civicoPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCivicoPagatore ( String value ) {
		this.civicoPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' capPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCapPagatore () {
		return capPagatore;
	}

	/**
	 * Imposta il valore della proprieta' capPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCapPagatore ( String value ) {
		this.capPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' localitaPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getLocalitaPagatore () {
		return localitaPagatore;
	}

	/**
	 * Imposta il valore della proprieta' localitaPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setLocalitaPagatore ( String value ) {
		this.localitaPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' provinciaPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getProvinciaPagatore () {
		return provinciaPagatore;
	}

	/**
	 * Imposta il valore della proprieta' provinciaPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setProvinciaPagatore ( String value ) {
		this.provinciaPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' nazionePagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getNazionePagatore () {
		return nazionePagatore;
	}

	/**
	 * Imposta il valore della proprieta' nazionePagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setNazionePagatore ( String value ) {
		this.nazionePagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' eMailPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getEMailPagatore () {
		return eMailPagatore;
	}

	/**
	 * Imposta il valore della proprieta' eMailPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setEMailPagatore ( String value ) {
		this.eMailPagatore = value;
	}

}
