/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.paymentmanager.local;

import java.io.Serializable;

public class AppGateway implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2701763102257454568L;
	private String applicationId = null;
	private String merchantId = null;
	private String gatewayId = null;
	private String gatewayDescription = null;
	//aggiunto gatewayservice name per gestire stringa jndi adapter associato
	private String gatewayServiceName = null;
	private String paymentmodeId = null;
	private String paymentmodeDescription = null;
	private double valoreComm = 0d;
	private String tipoCommissione = "";
	private double valoreMin = 0d;
	private double valoreMax = 0d;
	private double sogliaMin = 0d;
	private double sogliaMax = 0d;
	
	public String getApplicationId()
	{
		return applicationId;
	}
	public void setApplicationId(String applicationId)
	{
		this.applicationId = applicationId;
	}
	public String getMerchantId()
	{
		return merchantId;
	}
	public void setMerchantId(String merchantId)
	{
		this.merchantId = merchantId;
	}
	public String getGatewayId()
	{
		return gatewayId;
	}
	public void setGatewayId(String gatewayId)
	{
		this.gatewayId = gatewayId;
	}
	public String getGatewayDescription()
	{
		return gatewayDescription;
	}
	public void setGatewayDescription(String gatewayDescription)
	{
		this.gatewayDescription = gatewayDescription;
	}
	public String getGatewayServiceName()
	{
		return gatewayServiceName;
	}
	public void setGatewayServiceName(String gatewayServiceName)
	{
		this.gatewayServiceName = gatewayServiceName;
	}
	public String getPaymentmodeId()
	{
		return paymentmodeId;
	}
	public void setPaymentmodeId(String paymentmodeId)
	{
		this.paymentmodeId = paymentmodeId;
	}
	public String getPaymentmodeDescription()
	{
		return paymentmodeDescription;
	}
	public void setPaymentmodeDescription(String paymentmodeDescription)
	{
		this.paymentmodeDescription = paymentmodeDescription;
	}
	public double getValoreComm()
	{
		return valoreComm;
	}
	public void setValoreComm(double valoreComm)
	{
		this.valoreComm = valoreComm;
	}

	public String getTipoCommissione()
	{
		return tipoCommissione;
	}
	public void setTipoCommissione(String tipoCommissione)
	{
		this.tipoCommissione = tipoCommissione;
	}
	public double getValoreMin()
	{
		return valoreMin;
	}
	public void setValoreMin(double valoreMin)
	{
		this.valoreMin = valoreMin;
	}
	public double getValoreMax()
	{
		return valoreMax;
	}
	public void setValoreMax(double valoreMax)
	{
		this.valoreMax = valoreMax;
	}
	public double getSogliaMin()
	{
		return sogliaMin;
	}
	public void setSogliaMin(double sogliaMin)
	{
		this.sogliaMin = sogliaMin;
	}
	public double getSogliaMax()
	{
		return sogliaMax;
	}
	public void setSogliaMax(double sogliaMax)
	{
		this.sogliaMax = sogliaMax;
	}
	

}
