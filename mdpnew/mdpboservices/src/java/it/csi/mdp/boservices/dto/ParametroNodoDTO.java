/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.boservices.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ParametroNodoDTO extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3280487174741112519L;
	private String portaDiDominio;
	private String identificativoDominio;
	private String identificativointermediarioPA;
	private String identificativoStazioneIntermediarioPA;
	private String passwordDominioNodoSpc;
	/**
	 * @return the portaDiDominio
	 */
	public String getPortaDiDominio() {
		return portaDiDominio;
	}
	/**
	 * @param portaDiDominio the portaDiDominio to set
	 */
	public void setPortaDiDominio(String portaDiDominio) {
		this.portaDiDominio = portaDiDominio;
	}
	/**
	 * @return the identificativoDominio
	 */
	public String getIdentificativoDominio() {
		return identificativoDominio;
	}
	/**
	 * @param identificativoDominio the identificativoDominio to set
	 */
	public void setIdentificativoDominio(String identificativoDominio) {
		this.identificativoDominio = identificativoDominio;
	}
	/**
	 * @return the identificativointermediarioPA
	 */
	public String getIdentificativointermediarioPA() {
		return identificativointermediarioPA;
	}
	/**
	 * @param identificativointermediarioPA the identificativointermediarioPA to set
	 */
	public void setIdentificativointermediarioPA(String identificativointermediarioPA) {
		this.identificativointermediarioPA = identificativointermediarioPA;
	}
	/**
	 * @return the identificativoStazioneIntermediarioPA
	 */
	public String getIdentificativoStazioneIntermediarioPA() {
		return identificativoStazioneIntermediarioPA;
	}
	/**
	 * @param identificativoStazioneIntermediarioPA the identificativoStazioneIntermediarioPA to set
	 */
	public void setIdentificativoStazioneIntermediarioPA(String identificativoStazioneIntermediarioPA) {
		this.identificativoStazioneIntermediarioPA = identificativoStazioneIntermediarioPA;
	}
	/**
	 * @return the passwordDominioNodoSpc
	 */
	public String getPasswordDominioNodoSpc() {
		return passwordDominioNodoSpc;
	}
	/**
	 * @param passwordDominioNodoSpc the passwordDominioNodoSpc to set
	 */
	public void setPasswordDominioNodoSpc(String passwordDominioNodoSpc) {
		this.passwordDominioNodoSpc = passwordDominioNodoSpc;
	}
	
	
}
