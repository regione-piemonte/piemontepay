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
 * <p>Classe Java per RPTData complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="RPTData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="autenticazioneSoggetto" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="idStazioneRichiedente" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string" minOccurs="0"/&gt;
 *         &lt;element name="soggettoVersante" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}SoggettoVersante" minOccurs="0"/&gt;
 *         &lt;element name="soggettoPagatore" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}SoggettoPagatore"/&gt;
 *         &lt;element name="datiVersamento" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}DatiVersamentoRPT"/&gt;
 *         &lt;element name="applicationId" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}string"/&gt;
 *         &lt;element name="datiAccertamento" type="{<a href="http://interfacecxf.core.mdp.csi.it/">...</a>}DatiAccertamentoRPT" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "RPTData", propOrder = {
				"autenticazioneSoggetto",
				"idStazioneRichiedente",
				"soggettoVersante",
				"soggettoPagatore",
				"datiVersamento",
				"applicationId",
				"datiAccertamento"
} )
@SuppressWarnings ( "unused" )
public class RPTData {

	@XmlElement ( required = true )
	protected String autenticazioneSoggetto;

	protected String idStazioneRichiedente;

	protected SoggettoVersante soggettoVersante;

	@XmlElement ( required = true )
	protected SoggettoPagatore soggettoPagatore;

	@XmlElement ( required = true )
	protected DatiVersamentoRPT datiVersamento;

	@XmlElement ( required = true )
	protected String applicationId;

	protected DatiAccertamentoRPT datiAccertamento;

	/**
	 * Recupera il valore della proprieta' autenticazioneSoggetto.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getAutenticazioneSoggetto () {
		return autenticazioneSoggetto;
	}

	/**
	 * Imposta il valore della proprieta' autenticazioneSoggetto.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setAutenticazioneSoggetto ( String value ) {
		this.autenticazioneSoggetto = value;
	}

	/**
	 * Recupera il valore della proprieta' idStazioneRichiedente.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getIdStazioneRichiedente () {
		return idStazioneRichiedente;
	}

	/**
	 * Imposta il valore della proprieta' idStazioneRichiedente.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setIdStazioneRichiedente ( String value ) {
		this.idStazioneRichiedente = value;
	}

	/**
	 * Recupera il valore della proprieta' soggettoVersante.
	 *
	 * @return possible object is
	 * {@link SoggettoVersante }
	 */
	public SoggettoVersante getSoggettoVersante () {
		return soggettoVersante;
	}

	/**
	 * Imposta il valore della proprieta' soggettoVersante.
	 *
	 * @param value allowed object is
	 *              {@link SoggettoVersante }
	 */
	public void setSoggettoVersante ( SoggettoVersante value ) {
		this.soggettoVersante = value;
	}

	/**
	 * Recupera il valore della proprieta' soggettoPagatore.
	 *
	 * @return possible object is
	 * {@link SoggettoPagatore }
	 */
	public SoggettoPagatore getSoggettoPagatore () {
		return soggettoPagatore;
	}

	/**
	 * Imposta il valore della proprieta' soggettoPagatore.
	 *
	 * @param value allowed object is
	 *              {@link SoggettoPagatore }
	 */
	public void setSoggettoPagatore ( SoggettoPagatore value ) {
		this.soggettoPagatore = value;
	}

	/**
	 * Recupera il valore della proprieta' datiVersamento.
	 *
	 * @return possible object is
	 * {@link DatiVersamentoRPT }
	 */
	public DatiVersamentoRPT getDatiVersamento () {
		return datiVersamento;
	}

	/**
	 * Imposta il valore della proprieta' datiVersamento.
	 *
	 * @param value allowed object is
	 *              {@link DatiVersamentoRPT }
	 */
	public void setDatiVersamento ( DatiVersamentoRPT value ) {
		this.datiVersamento = value;
	}

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

	@Override public String toString () {
		return "RPTData{" +
						"autenticazioneSoggetto='" + autenticazioneSoggetto + '\'' +
						", idStazioneRichiedente='" + idStazioneRichiedente + '\'' +
						", soggettoVersante=" + soggettoVersante +
						", soggettoPagatore=" + soggettoPagatore +
						", datiVersamento=" + datiVersamento +
						", applicationId='" + applicationId + '\'' +
						", datiAccertamento=" + datiAccertamento +
						'}';
	}
}
