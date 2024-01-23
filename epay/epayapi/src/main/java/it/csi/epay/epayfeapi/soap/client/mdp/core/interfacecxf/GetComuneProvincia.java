/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getComuneProvincia complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="getComuneProvincia"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "getComuneProvincia", propOrder = {
				"arg0"
} )
@SuppressWarnings ( "unused" )
public class GetComuneProvincia {

	protected String arg0;

	/**
	 * Recupera il valore della proprieta' arg0.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getArg0 () {
		return arg0;
	}

	/**
	 * Imposta il valore della proprieta' arg0.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setArg0 ( String value ) {
		this.arg0 = value;
	}

}
