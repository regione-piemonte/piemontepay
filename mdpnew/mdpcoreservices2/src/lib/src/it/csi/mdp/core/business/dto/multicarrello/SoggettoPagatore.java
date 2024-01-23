/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto.multicarrello;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoggettoPagatore", propOrder = {
    "identificativoUnivocoPagatore",
    "tipoIdentificativoUnivocoPagatore",
    "anagraficaPagatore",
    "indirizzoPagatore",
    "civicoPagatore",
    "capPagatore",
    "localitaPagatore",
    "provinciaPagatore",
    "nazionePagatore",
    "eMailPagatore"
})
public class SoggettoPagatore implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2062442889176962637L;
	@XmlElement(required = true)
    protected String identificativoUnivocoPagatore;
    @XmlElement
    protected String tipoIdentificativoUnivocoPagatore;
    @XmlElement(required = true)
    protected String anagraficaPagatore;
    protected String indirizzoPagatore;
    protected String civicoPagatore;
    protected String capPagatore;
    protected String localitaPagatore;
    protected String provinciaPagatore;
    protected String nazionePagatore;
    @XmlElement(name = "e-mailPagatore")
    protected String eMailPagatore;
    
    
	public String getIdentificativoUnivocoPagatore() {
		return identificativoUnivocoPagatore;
	}
	public void setIdentificativoUnivocoPagatore(
			String identificativoUnivocoPagatore) {
		this.identificativoUnivocoPagatore = identificativoUnivocoPagatore;
	}
	public String getTipoIdentificativoUnivocoPagatore() {
		return tipoIdentificativoUnivocoPagatore;
	}
	public void setTipoIdentificativoUnivocoPagatore(
			String tipoIdentificativoUnivocoPagatore) {
		this.tipoIdentificativoUnivocoPagatore = tipoIdentificativoUnivocoPagatore;
	}
	public String getAnagraficaPagatore() {
		return anagraficaPagatore;
	}
	public void setAnagraficaPagatore(String anagraficaPagatore) {
		this.anagraficaPagatore = anagraficaPagatore;
	}
	public String getIndirizzoPagatore() {
		return indirizzoPagatore;
	}
	public void setIndirizzoPagatore(String indirizzoPagatore) {
		this.indirizzoPagatore = indirizzoPagatore;
	}
	public String getCivicoPagatore() {
		return civicoPagatore;
	}
	public void setCivicoPagatore(String civicoPagatore) {
		this.civicoPagatore = civicoPagatore;
	}
	public String getCapPagatore() {
		return capPagatore;
	}
	public void setCapPagatore(String capPagatore) {
		this.capPagatore = capPagatore;
	}
	public String getLocalitaPagatore() {
		return localitaPagatore;
	}
	public void setLocalitaPagatore(String localitaPagatore) {
		this.localitaPagatore = localitaPagatore;
	}
	public String getProvinciaPagatore() {
		return provinciaPagatore;
	}
	public void setProvinciaPagatore(String provinciaPagatore) {
		this.provinciaPagatore = provinciaPagatore;
	}
	public String getNazionePagatore() {
		return nazionePagatore;
	}
	public void setNazionePagatore(String nazionePagatore) {
		this.nazionePagatore = nazionePagatore;
	}
	public String geteMailPagatore() {
		return eMailPagatore;
	}
	public void seteMailPagatore(String eMailPagatore) {
		this.eMailPagatore = eMailPagatore;
	}

    
    
}
