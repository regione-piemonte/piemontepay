/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Classe Java per DatiSingoloVersamentoRPT complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="DatiSingoloVersamentoRPT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="importoSingoloVersamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}decimal"/&gt;
 *         &lt;element name="commissioneCaricoPA" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}decimal" minOccurs="0"/&gt;
 *         &lt;element name="credenzialiPagatore" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="causaleVersamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="datiSpecificiRiscossione" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="datiMarcaBolloDigitale" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}DatiMarcaBolloDigitale" minOccurs="0"/&gt;
 *         &lt;element name="datiAccertamento" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}DatiAccertamentoRPT" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "DatiSingoloVersamentoRPT", propOrder = {
				"importoSingoloVersamento",
				"commissioneCaricoPA",
				"credenzialiPagatore",
				"causaleVersamento",
				"datiSpecificiRiscossione",
				"datiMarcaBolloDigitale",
				"datiAccertamento"
} )
@SuppressWarnings ( "unused" )
public class DatiSingoloVersamentoRPT {

	@XmlElement ( required = true )
	protected BigDecimal importoSingoloVersamento;

	protected BigDecimal commissioneCaricoPA;

	protected String credenzialiPagatore;

	@XmlElement ( required = true )
	protected String causaleVersamento;

	@XmlElement ( required = true )
	protected String datiSpecificiRiscossione;

	protected DatiMarcaBolloDigitale datiMarcaBolloDigitale;

	protected DatiAccertamentoRPT datiAccertamento;

	/**
	 * Recupera il valore della proprieta' importoSingoloVersamento.
	 *
	 * @return possible object is
	 * {@link BigDecimal }
	 */
	public BigDecimal getImportoSingoloVersamento () {
		return importoSingoloVersamento;
	}

	/**
	 * Imposta il valore della proprieta' importoSingoloVersamento.
	 *
	 * @param value allowed object is
	 *              {@link BigDecimal }
	 */
	public void setImportoSingoloVersamento ( BigDecimal value ) {
		this.importoSingoloVersamento = value;
	}

	/**
	 * Recupera il valore della proprieta' commissioneCaricoPA.
	 *
	 * @return possible object is
	 * {@link BigDecimal }
	 */
	public BigDecimal getCommissioneCaricoPA () {
		return commissioneCaricoPA;
	}

	/**
	 * Imposta il valore della proprieta' commissioneCaricoPA.
	 *
	 * @param value allowed object is
	 *              {@link BigDecimal }
	 */
	public void setCommissioneCaricoPA ( BigDecimal value ) {
		this.commissioneCaricoPA = value;
	}

	/**
	 * Recupera il valore della proprieta' credenzialiPagatore.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCredenzialiPagatore () {
		return credenzialiPagatore;
	}

	/**
	 * Imposta il valore della proprieta' credenzialiPagatore.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCredenzialiPagatore ( String value ) {
		this.credenzialiPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' causaleVersamento.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCausaleVersamento () {
		return causaleVersamento;
	}

	/**
	 * Imposta il valore della proprieta' causaleVersamento.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCausaleVersamento ( String value ) {
		this.causaleVersamento = value;
	}

	/**
	 * Recupera il valore della proprieta' datiSpecificiRiscossione.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getDatiSpecificiRiscossione () {
		return datiSpecificiRiscossione;
	}

	/**
	 * Imposta il valore della proprieta' datiSpecificiRiscossione.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setDatiSpecificiRiscossione ( String value ) {
		this.datiSpecificiRiscossione = value;
	}

	/**
	 * Recupera il valore della proprieta' datiMarcaBolloDigitale.
	 *
	 * @return possible object is
	 * {@link DatiMarcaBolloDigitale }
	 */
	public DatiMarcaBolloDigitale getDatiMarcaBolloDigitale () {
		return datiMarcaBolloDigitale;
	}

	/**
	 * Imposta il valore della proprieta' datiMarcaBolloDigitale.
	 *
	 * @param value allowed object is
	 *              {@link DatiMarcaBolloDigitale }
	 */
	public void setDatiMarcaBolloDigitale ( DatiMarcaBolloDigitale value ) {
		this.datiMarcaBolloDigitale = value;
	}

	/**
	 * Recupera il valore della proprieta' datiAccertamento.
	 *
	 * @return possible object is
	 * {@link DatiAccertamentoRPT }
	 */
	public DatiAccertamentoRPT getDatiAccertamento () {
		return datiAccertamento;
	}

	/**
	 * Imposta il valore della proprieta' datiAccertamento.
	 *
	 * @param value allowed object is
	 *              {@link DatiAccertamentoRPT }
	 */
	public void setDatiAccertamento ( DatiAccertamentoRPT value ) {
		this.datiAccertamento = value;
	}

}
