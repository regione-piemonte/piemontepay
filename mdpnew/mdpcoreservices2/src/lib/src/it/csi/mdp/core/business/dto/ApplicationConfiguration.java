/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;
import java.util.List;

public class ApplicationConfiguration extends Application implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2982505267972658323L;
	private List<ApplicationGatewayConfiguration> applicationGatewayConfigurationsList = null;
	
	public ApplicationConfiguration ()
	{
		super();
	}

	public ApplicationConfiguration (Application a)
	{
		super();
		this.applicationname = a.applicationname;
		this.cliente = a.cliente;
		this.esercemail = a.esercemail;
		this.id = a.id;
		this.note = a.note;
		this.numeroverde = a.numeroverde;
		this.progetto = a.progetto;
		this.redirectNewmdp = a.redirectNewmdp;
		this.referentecsi = a.referentecsi;
		this.validoAl = a.validoAl;
		this.validoDal = a.validoDal;
		
	}

	public List<ApplicationGatewayConfiguration> getApplicationGatewayConfigurationsList()
	{
		return this.applicationGatewayConfigurationsList;
	}

	public void setApplicationGatewayConfigurationsList(List<ApplicationGatewayConfiguration> applicationGatewayConfigurationsList)
	{
		this.applicationGatewayConfigurationsList = applicationGatewayConfigurationsList;
	}

	
}
