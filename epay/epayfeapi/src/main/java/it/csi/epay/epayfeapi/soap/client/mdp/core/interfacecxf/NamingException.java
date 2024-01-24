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
 * <p>Classe Java per NamingException complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="NamingException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="resolvedObj" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "NamingException", propOrder = {
				"resolvedObj"
} )
@SuppressWarnings ( "unused" )
public class NamingException {

	@XmlElement ( required = true, nillable = true )
	protected Object resolvedObj;

	/**
	 * Recupera il valore della proprieta' resolvedObj.
	 *
	 * @return possible object is
	 * {@link Object }
	 */
	public Object getResolvedObj () {
		return resolvedObj;
	}

	/**
	 * Imposta il valore della proprieta' resolvedObj.
	 *
	 * @param value allowed object is
	 *              {@link Object }
	 */
	public void setResolvedObj ( Object value ) {
		this.resolvedObj = value;
	}

}
