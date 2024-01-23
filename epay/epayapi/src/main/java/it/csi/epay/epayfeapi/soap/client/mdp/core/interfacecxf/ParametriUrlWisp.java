/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per parametriUrlWisp complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="parametriUrlWisp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bolloDigitale" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceLingua" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="idPsp" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="modello2" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="stornoPagamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="terzoModelloPagamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="tipoVersamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="urlBackWisp" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="urlReturnWisp" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "parametriUrlWisp", propOrder = {
				"bolloDigitale",
				"codiceLingua",
				"idPsp",
				"modello2",
				"stornoPagamento",
				"terzoModelloPagamento",
				"tipoVersamento",
				"urlBackWisp",
				"urlReturnWisp"
} )
@SuppressWarnings ( "unused" )
public class ParametriUrlWisp {

	protected String bolloDigitale;

	protected String codiceLingua;

	protected String idPsp;

	protected String modello2;

	protected String stornoPagamento;

	protected String terzoModelloPagamento;

	protected String tipoVersamento;

	protected String urlBackWisp;

	protected String urlReturnWisp;

	/**
	 * Recupera il valore della proprieta' bolloDigitale.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getBolloDigitale () {
		return bolloDigitale;
	}

	/**
	 * Imposta il valore della proprieta' bolloDigitale.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setBolloDigitale ( String value ) {
		this.bolloDigitale = value;
	}

	/**
	 * Recupera il valore della proprieta' codiceLingua.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCodiceLingua () {
		return codiceLingua;
	}

	/**
	 * Imposta il valore della proprieta' codiceLingua.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCodiceLingua ( String value ) {
		this.codiceLingua = value;
	}

	/**
	 * Recupera il valore della proprieta' idPsp.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIdPsp () {
		return idPsp;
	}

	/**
	 * Imposta il valore della proprieta' idPsp.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIdPsp ( String value ) {
		this.idPsp = value;
	}

	/**
	 * Recupera il valore della proprieta' modello2.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getModello2 () {
		return modello2;
	}

	/**
	 * Imposta il valore della proprieta' modello2.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setModello2 ( String value ) {
		this.modello2 = value;
	}

	/**
	 * Recupera il valore della proprieta' stornoPagamento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getStornoPagamento () {
		return stornoPagamento;
	}

	/**
	 * Imposta il valore della proprieta' stornoPagamento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setStornoPagamento ( String value ) {
		this.stornoPagamento = value;
	}

	/**
	 * Recupera il valore della proprieta' terzoModelloPagamento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTerzoModelloPagamento () {
		return terzoModelloPagamento;
	}

	/**
	 * Imposta il valore della proprieta' terzoModelloPagamento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTerzoModelloPagamento ( String value ) {
		this.terzoModelloPagamento = value;
	}

	/**
	 * Recupera il valore della proprieta' tipoVersamento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getTipoVersamento () {
		return tipoVersamento;
	}

	/**
	 * Imposta il valore della proprieta' tipoVersamento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setTipoVersamento ( String value ) {
		this.tipoVersamento = value;
	}

	/**
	 * Recupera il valore della proprieta' urlBackWisp.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getUrlBackWisp () {
		return urlBackWisp;
	}

	/**
	 * Imposta il valore della proprieta' urlBackWisp.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setUrlBackWisp ( String value ) {
		this.urlBackWisp = value;
	}

	/**
	 * Recupera il valore della proprieta' urlReturnWisp.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getUrlReturnWisp () {
		return urlReturnWisp;
	}

	/**
	 * Imposta il valore della proprieta' urlReturnWisp.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setUrlReturnWisp ( String value ) {
		this.urlReturnWisp = value;
	}

}
