/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import it.csi.mdp.mdpetl.util.StringUtils;


public class ParametroNodo extends BaseDTO  {	

	private static final long serialVersionUID = -5337439484416182209L;
	
	private String portaDiDominio;
	private String identificativoDominio;
	private String identificativointermediarioPA;
	private String identificativoStazioneIntermediarioPA;
	private String passwordDominioNodoSpc;
	
	private boolean gadEnabled;
	
	
	public boolean isGadEnabled() {
		return gadEnabled;
	}
	public void setGadEnabled(boolean gadEnabled) {
		this.gadEnabled = gadEnabled;
	}
	public String getPortaDiDominio() {
		return portaDiDominio;
	}
	public void setPortaDiDominio(String portaDiDominio) {
		this.portaDiDominio = portaDiDominio;
	}
	public String getIdentificativoDominio() {
		return identificativoDominio;
	}
	public void setIdentificativoDominio(String identificativoDominio) {
		this.identificativoDominio = identificativoDominio;
	}
	public String getIdentificativointermediarioPA() {
		return identificativointermediarioPA;
	}
	public void setIdentificativointermediarioPA(
			String identificativointermediarioPA) {
		this.identificativointermediarioPA = identificativointermediarioPA;
	}
	public String getIdentificativoStazioneIntermediarioPA() {
		return identificativoStazioneIntermediarioPA;
	}
	public void setIdentificativoStazioneIntermediarioPA(
			String identificativoStazioneIntermediarioPA) {
		this.identificativoStazioneIntermediarioPA = identificativoStazioneIntermediarioPA;
	}
	public String getPasswordDominioNodoSpc() {
		return passwordDominioNodoSpc;
	}
	public void setPasswordDominioNodoSpc(String passwordDominioNodoSpc) {
		this.passwordDominioNodoSpc = passwordDominioNodoSpc;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof ParametroNodo)) return false;
		
		ParametroNodo local = (ParametroNodo) obj;
		
		return (this.getIdentificativoDominio()!=null && this.getIdentificativoDominio().equals(local.getIdentificativoDominio()) && 
				this.getIdentificativointermediarioPA()!= null && this.getIdentificativointermediarioPA().equals(local.getIdentificativointermediarioPA()) && 
				this.getIdentificativoStazioneIntermediarioPA() !=null && this.getIdentificativoStazioneIntermediarioPA().equals(local.getIdentificativoStazioneIntermediarioPA()));
	}
	
	public boolean isComplete() {
		return  !StringUtils.isEmpty(this.getIdentificativoDominio()) && 
		              !StringUtils.isEmpty(this.getIdentificativointermediarioPA()) && 
		               !StringUtils.isEmpty(this.getIdentificativoStazioneIntermediarioPA()) &&
		                 !StringUtils.isEmpty(this.getPasswordDominioNodoSpc()) &&
		                    !StringUtils.isEmpty(this.getPortaDiDominio());
	}
}
