/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.04.15 at 05:05:41 PM CEST 
//


package it.csi.mdp.core.business.dto.multicarrello;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiVersamentoRPT", propOrder = {
//    "dataEsecuzionePagamento",
    "importoTotaleDaVersare",
//    "tipoVersamento",
    "identificativoUnivocoVersamento",
//    "codiceContestoPagamento", "n/a"
    "ibanAddebito",
    "bicAddebito",
    "firmaRicevuta",
    "datiSingoloVersamento"
})
public class DatiVersamentoRPT implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3079783432899413776L;
	@XmlElement(required = true)
    protected BigDecimal importoTotaleDaVersare;
    @XmlElement(required = true)
    protected String identificativoUnivocoVersamento;
    protected String ibanAddebito;
    protected String bicAddebito;
    @XmlElement(required = true)
    protected String firmaRicevuta;
    @XmlElement(required = true)
    protected List<DatiSingoloVersamentoRPT> datiSingoloVersamento;
    
	public BigDecimal getImportoTotaleDaVersare() {
		return importoTotaleDaVersare;
	}
	public void setImportoTotaleDaVersare(BigDecimal importoTotaleDaVersare) {
		this.importoTotaleDaVersare = importoTotaleDaVersare;
	}
	public String getIdentificativoUnivocoVersamento() {
		return identificativoUnivocoVersamento;
	}
	public void setIdentificativoUnivocoVersamento(
			String identificativoUnivocoVersamento) {
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
	}
	public String getIbanAddebito() {
		return ibanAddebito;
	}
	public void setIbanAddebito(String ibanAddebito) {
		this.ibanAddebito = ibanAddebito;
	}
	public String getBicAddebito() {
		return bicAddebito;
	}
	public void setBicAddebito(String bicAddebito) {
		this.bicAddebito = bicAddebito;
	}
	public String getFirmaRicevuta() {
		return firmaRicevuta;
	}
	public void setFirmaRicevuta(String firmaRicevuta) {
		this.firmaRicevuta = firmaRicevuta;
	}
	public List<DatiSingoloVersamentoRPT> getDatiSingoloVersamento() {
		return datiSingoloVersamento;
	}
	public void setDatiSingoloVersamento(
			List<DatiSingoloVersamentoRPT> datiSingoloVersamento) {
		this.datiSingoloVersamento = datiSingoloVersamento;
	}

   
}
