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
 * <p>Classe Java per ChiaveValore complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="ChiaveValore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="chiave" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="valore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "ChiaveValore", propOrder = {
				"chiave",
				"valore"
} )
public class ChiaveValore {

	@XmlElement ( required = true )
	protected String chiave;

	protected String valore;

	/**
	 * Recupera il valore della proprieta' chiave.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getChiave () {
		return chiave;
	}

	/**
	 * Imposta il valore della proprieta' chiave.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setChiave ( String value ) {
		this.chiave = value;
	}

	/**
	 * Recupera il valore della proprieta' valore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getValore () {
		return valore;
	}

	/**
	 * Imposta il valore della proprieta' valore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setValore ( String value ) {
		this.valore = value;
	}

}
