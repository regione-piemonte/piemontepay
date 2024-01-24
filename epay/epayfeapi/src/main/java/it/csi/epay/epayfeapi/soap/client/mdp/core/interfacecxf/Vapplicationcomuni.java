/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per vapplicationcomuni complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="vapplicationcomuni"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applicationId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceimm" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="descComune" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="gatewayId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="merchantid" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "vapplicationcomuni", propOrder = {
				"applicationId",
				"codiceimm",
				"descComune",
				"gatewayId",
				"merchantid"
} )
@SuppressWarnings ( "unused" )
public class Vapplicationcomuni {

	protected String applicationId;

	protected String codiceimm;

	protected String descComune;

	protected String gatewayId;

	protected String merchantid;

	/**
	 * Recupera il valore della proprieta' applicationId.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getApplicationId () {
		return applicationId;
	}

	/**
	 * Imposta il valore della proprieta' applicationId.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setApplicationId ( String value ) {
		this.applicationId = value;
	}

	/**
	 * Recupera il valore della proprieta' codiceimm.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCodiceimm () {
		return codiceimm;
	}

	/**
	 * Imposta il valore della proprieta' codiceimm.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCodiceimm ( String value ) {
		this.codiceimm = value;
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
	 * Recupera il valore della proprieta' gatewayId.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getGatewayId () {
		return gatewayId;
	}

	/**
	 * Imposta il valore della proprieta' gatewayId.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setGatewayId ( String value ) {
		this.gatewayId = value;
	}

	/**
	 * Recupera il valore della proprieta' merchantid.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getMerchantid () {
		return merchantid;
	}

	/**
	 * Imposta il valore della proprieta' merchantid.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setMerchantid ( String value ) {
		this.merchantid = value;
	}

}
