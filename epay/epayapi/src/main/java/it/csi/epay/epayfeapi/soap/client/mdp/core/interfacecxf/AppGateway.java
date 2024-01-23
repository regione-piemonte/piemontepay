/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per appGateway complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="appGateway"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="applicationId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="gatewayDescription" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="gatewayId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="gatewayServiceName" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="merchantId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="paymentmodeDescription" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="paymentmodeId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="sogliaMax" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}double"/&gt;
 *         &lt;element name="sogliaMin" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}double"/&gt;
 *         &lt;element name="tipoCommissione" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="valoreComm" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}double"/&gt;
 *         &lt;element name="valoreMax" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}double"/&gt;
 *         &lt;element name="valoreMin" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "appGateway", propOrder = {
				"applicationId",
				"gatewayDescription",
				"gatewayId",
				"gatewayServiceName",
				"merchantId",
				"paymentmodeDescription",
				"paymentmodeId",
				"sogliaMax",
				"sogliaMin",
				"tipoCommissione",
				"valoreComm",
				"valoreMax",
				"valoreMin"
} )
@XmlSeeAlso ( {
				AppGatewayInformativa.class
} )
@SuppressWarnings ( "unused" )
public class AppGateway {

	protected String applicationId;

	protected String gatewayDescription;

	protected String gatewayId;

	protected String gatewayServiceName;

	protected String merchantId;

	protected String paymentmodeDescription;

	protected String paymentmodeId;

	protected double sogliaMax;

	protected double sogliaMin;

	protected String tipoCommissione;

	protected double valoreComm;

	protected double valoreMax;

	protected double valoreMin;

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
	 * Recupera il valore della proprieta' gatewayDescription.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getGatewayDescription () {
		return gatewayDescription;
	}

	/**
	 * Imposta il valore della proprieta' gatewayDescription.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setGatewayDescription ( String value ) {
		this.gatewayDescription = value;
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
	 * Recupera il valore della proprieta' gatewayServiceName.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getGatewayServiceName () {
		return gatewayServiceName;
	}

	/**
	 * Imposta il valore della proprieta' gatewayServiceName.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setGatewayServiceName ( String value ) {
		this.gatewayServiceName = value;
	}

	/**
	 * Recupera il valore della proprieta' merchantId.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getMerchantId () {
		return merchantId;
	}

	/**
	 * Imposta il valore della proprieta' merchantId.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setMerchantId ( String value ) {
		this.merchantId = value;
	}

	/**
	 * Recupera il valore della proprieta' paymentmodeDescription.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getPaymentmodeDescription () {
		return paymentmodeDescription;
	}

	/**
	 * Imposta il valore della proprieta' paymentmodeDescription.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setPaymentmodeDescription ( String value ) {
		this.paymentmodeDescription = value;
	}

	/**
	 * Recupera il valore della proprieta' paymentmodeId.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getPaymentmodeId () {
		return paymentmodeId;
	}

	/**
	 * Imposta il valore della proprieta' paymentmodeId.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setPaymentmodeId ( String value ) {
		this.paymentmodeId = value;
	}

	/**
	 * Recupera il valore della proprieta' sogliaMax.
	 */
	public double getSogliaMax () {
		return sogliaMax;
	}

	/**
	 * Imposta il valore della proprieta' sogliaMax.
	 */
	public void setSogliaMax ( double value ) {
		this.sogliaMax = value;
	}

	/**
	 * Recupera il valore della proprieta' sogliaMin.
	 */
	public double getSogliaMin () {
		return sogliaMin;
	}

	/**
	 * Imposta il valore della proprieta' sogliaMin.
	 */
	public void setSogliaMin ( double value ) {
		this.sogliaMin = value;
	}

	/**
	 * Recupera il valore della proprieta' tipoCommissione.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTipoCommissione () {
		return tipoCommissione;
	}

	/**
	 * Imposta il valore della proprieta' tipoCommissione.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTipoCommissione ( String value ) {
		this.tipoCommissione = value;
	}

	/**
	 * Recupera il valore della proprieta' valoreComm.
	 */
	public double getValoreComm () {
		return valoreComm;
	}

	/**
	 * Imposta il valore della proprieta' valoreComm.
	 */
	public void setValoreComm ( double value ) {
		this.valoreComm = value;
	}

	/**
	 * Recupera il valore della proprieta' valoreMax.
	 */
	public double getValoreMax () {
		return valoreMax;
	}

	/**
	 * Imposta il valore della proprieta' valoreMax.
	 */
	public void setValoreMax ( double value ) {
		this.valoreMax = value;
	}

	/**
	 * Recupera il valore della proprieta' valoreMin.
	 */
	public double getValoreMin () {
		return valoreMin;
	}

	/**
	 * Imposta il valore della proprieta' valoreMin.
	 */
	public void setValoreMin ( double value ) {
		this.valoreMin = value;
	}

}
