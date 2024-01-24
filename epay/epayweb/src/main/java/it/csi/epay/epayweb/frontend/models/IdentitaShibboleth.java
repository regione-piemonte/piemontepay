/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.frontend.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayweb.utilities.XmlUtil;

public class IdentitaShibboleth implements Serializable  {
	
	public static final String SHIB_COGNOME       = "Shib-Identita-Cognome";
	public static final String SHIB_NOME          = "Shib-Identita-Nome";
	public static final String SHIB_CODICEFISCALE = "Shib-Identita-CodiceFiscale";
	public static final String SHIB_LIVAUTH       = "Shib-Identita-LivAuth";
	public static final String SHIB_TIMESTAMP     = "Shib-Identita-TimeStamp";
	public static final String SHIB_PROVIDER      = "Shib-Identita-Provider";
	public static final String SHIB_COMMUNITY     = "Shib-community";
	public static final String SHIB_EMAIL         = "Shib-Email";
	
	private static final long serialVersionUID = 7784565291561345618L;
	
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private Integer livAuth;
	private Date timeStamp;
	private String provider;
	private String community;
	private String email;
	
	public IdentitaShibboleth() {
		super();
	}
		
	public IdentitaShibboleth(HttpServletRequest request) {
		super();
		if (request == null) {
			return;
		}
		
		this.setCognome(request.getHeader(SHIB_COGNOME));
		this.setNome(request.getHeader(SHIB_NOME));
		this.setCodiceFiscale(request.getHeader(SHIB_CODICEFISCALE));
		this.setLivAuth(request.getHeader(SHIB_LIVAUTH));
		this.setTimeStamp(request.getHeader(SHIB_TIMESTAMP));
		this.setProvider(request.getHeader(SHIB_PROVIDER));
		this.setCommunity(request.getHeader(SHIB_COMMUNITY));
		this.setEmail(request.getHeader(SHIB_EMAIL));
	}
	
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	/**
	 * @return the livAuth
	 */
	public Integer getLivAuth() {
		return livAuth;
	}
	/**
	 * @param livAuth the livAuth to set
	 */
	public void setLivAuth(String livAuth) {
		if (StringUtils.isNotBlank(livAuth)) {
			try {
				this.livAuth = Integer.valueOf(livAuth);
			} catch (NumberFormatException e) {
				this.livAuth = 0;
			}	
		} else {
			this.livAuth = 0;
		}
	}
	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		if (StringUtils.isNotBlank(timeStamp)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			try {
				this.timeStamp = formatter.parse(timeStamp);
			} catch (ParseException e) {
				this.timeStamp = null;
	        }
			this.livAuth = Integer.valueOf(livAuth);
		} else {
			this.timeStamp = null;
		}
	}
	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}
	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}
	/**
	 * @param community the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}	
	
	@Override
	public String toString() {
		return "Identita Shibboleth : " + XmlUtil.obj2Xml(this);
	}
}
