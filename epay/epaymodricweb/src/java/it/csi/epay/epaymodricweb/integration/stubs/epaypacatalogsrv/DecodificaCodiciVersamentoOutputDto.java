/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Classe Java per decodificaCodiciVersamentoOutputDto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="decodificaCodiciVersamentoOutputDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}decodificaOutputDto"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
* 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "decodificaCodiciVersamentoOutputDto", propOrder = {
    "tipoPagamento",
})
public class DecodificaCodiciVersamentoOutputDto extends DecodificaOutputDto {



	private String tipoPagamento;


	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}


}

