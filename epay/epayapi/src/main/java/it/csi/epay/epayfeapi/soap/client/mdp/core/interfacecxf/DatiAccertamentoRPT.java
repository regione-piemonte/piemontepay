/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DatiAccertamentoRPT complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="DatiAccertamentoRPT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="annoAccertamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *         &lt;element name="numeroAccertamento" type="{<a href="http://www.w3.org/2001/XMLSchema">...</a>}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "DatiAccertamentoRPT", propOrder = {
				"annoAccertamento",
				"numeroAccertamento"
} )
public class DatiAccertamentoRPT {

	protected Integer annoAccertamento;

	protected Integer numeroAccertamento;

	/**
	 * Recupera il valore della proprieta' annoAccertamento.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getAnnoAccertamento () {
		return annoAccertamento;
	}

	/**
	 * Imposta il valore della proprieta' annoAccertamento.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setAnnoAccertamento ( Integer value ) {
		this.annoAccertamento = value;
	}

	/**
	 * Recupera il valore della proprieta' numeroAccertamento.
	 *
	 * @return possible object is
	 * {@link Integer }
	 */
	public Integer getNumeroAccertamento () {
		return numeroAccertamento;
	}

	/**
	 * Imposta il valore della proprieta' numeroAccertamento.
	 *
	 * @param value allowed object is
	 *              {@link Integer }
	 */
	public void setNumeroAccertamento ( Integer value ) {
		this.numeroAccertamento = value;
	}

}
