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
 * <p>Classe Java per DatiMarcaBolloDigitale complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="DatiMarcaBolloDigitale"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tipoBollo" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="hashDocumento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="provinciaResidenza" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "DatiMarcaBolloDigitale", propOrder = {
				"tipoBollo",
				"hashDocumento",
				"provinciaResidenza"
} )
@SuppressWarnings ( "unused" )
public class DatiMarcaBolloDigitale {

	@XmlElement ( required = true )
	protected String tipoBollo;

	@XmlElement ( required = true )
	protected String hashDocumento;

	@XmlElement ( required = true )
	protected String provinciaResidenza;

	/**
	 * Recupera il valore della proprieta' tipoBollo.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTipoBollo () {
		return tipoBollo;
	}

	/**
	 * Imposta il valore della proprieta' tipoBollo.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTipoBollo ( String value ) {
		this.tipoBollo = value;
	}

	/**
	 * Recupera il valore della proprieta' hashDocumento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getHashDocumento () {
		return hashDocumento;
	}

	/**
	 * Imposta il valore della proprieta' hashDocumento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setHashDocumento ( String value ) {
		this.hashDocumento = value;
	}

	/**
	 * Recupera il valore della proprieta' provinciaResidenza.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getProvinciaResidenza () {
		return provinciaResidenza;
	}

	/**
	 * Imposta il valore della proprieta' provinciaResidenza.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setProvinciaResidenza ( String value ) {
		this.provinciaResidenza = value;
	}

}
