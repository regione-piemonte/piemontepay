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
@XmlType(name = "SoggettoVersante", propOrder = {
    "identificativoUnivocoVersante",
    "tipoIdentificativoUnivocoVersante",
    "anagraficaVersante",
    "indirizzoVersante",
    "civicoVersante",
    "capVersante",
    "localitaVersante",
    "provinciaVersante",
    "nazioneVersante",
    "eMailVersante"
})
public class SoggettoVersante implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 915895959245585987L;
	@XmlElement(required = true)
    protected String identificativoUnivocoVersante;
    @XmlElement
    protected String tipoIdentificativoUnivocoVersante;
    @XmlElement(required = true)
    protected String anagraficaVersante;
    protected String indirizzoVersante;
    protected String civicoVersante;
    protected String capVersante;
    protected String localitaVersante;
    protected String provinciaVersante;
    protected String nazioneVersante;
    @XmlElement(name = "e-mailVersante")
    protected String eMailVersante;
    
    
	public String getIdentificativoUnivocoVersante() {
		return identificativoUnivocoVersante;
	}
	public void setIdentificativoUnivocoVersante(
			String identificativoUnivocoVersante) {
		this.identificativoUnivocoVersante = identificativoUnivocoVersante;
	}
	public String getTipoIdentificativoUnivocoVersante() {
		return tipoIdentificativoUnivocoVersante;
	}
	public void setTipoIdentificativoUnivocoVersante(
			String tipoIdentificativoUnivocoVersante) {
		this.tipoIdentificativoUnivocoVersante = tipoIdentificativoUnivocoVersante;
	}
	public String getAnagraficaVersante() {
		return anagraficaVersante;
	}
	public void setAnagraficaVersante(String anagraficaVersante) {
		this.anagraficaVersante = anagraficaVersante;
	}
	public String getIndirizzoVersante() {
		return indirizzoVersante;
	}
	public void setIndirizzoVersante(String indirizzoVersante) {
		this.indirizzoVersante = indirizzoVersante;
	}
	public String getCivicoVersante() {
		return civicoVersante;
	}
	public void setCivicoVersante(String civicoVersante) {
		this.civicoVersante = civicoVersante;
	}
	public String getCapVersante() {
		return capVersante;
	}
	public void setCapVersante(String capVersante) {
		this.capVersante = capVersante;
	}
	public String getLocalitaVersante() {
		return localitaVersante;
	}
	public void setLocalitaVersante(String localitaVersante) {
		this.localitaVersante = localitaVersante;
	}
	public String getProvinciaVersante() {
		return provinciaVersante;
	}
	public void setProvinciaVersante(String provinciaVersante) {
		this.provinciaVersante = provinciaVersante;
	}
	public String getNazioneVersante() {
		return nazioneVersante;
	}
	public void setNazioneVersante(String nazioneVersante) {
		this.nazioneVersante = nazioneVersante;
	}
	public String geteMailVersante() {
		return eMailVersante;
	}
	public void seteMailVersante(String eMailVersante) {
		this.eMailVersante = eMailVersante;
	}


}
