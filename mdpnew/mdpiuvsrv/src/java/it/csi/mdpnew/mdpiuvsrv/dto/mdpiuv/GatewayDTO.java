/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpiuvsrv.dto.mdpiuv;

import java.sql.Timestamp;

public class GatewayDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String gatewayid;
	private String gatewaydescription;
	private String gatewayprovider;
	private Timestamp validodal;
	private Timestamp validoal;
	private String gatewayservicename;
	private boolean flagnodo;

	public String getGatewayid() {
		return gatewayid;
	}

	public void setGatewayid(String gatewayid) {
		this.gatewayid = gatewayid;
	}

	public String getGatewaydescription() {
		return gatewaydescription;
	}

	public void setGatewaydescription(String gatewaydescription) {
		this.gatewaydescription = gatewaydescription;
	}

	public String getGatewayprovider() {
		return gatewayprovider;
	}

	public void setGatewayprovider(String gatewayprovider) {
		this.gatewayprovider = gatewayprovider;
	}

	public Timestamp getValidodal() {
		return (Timestamp) validodal.clone();
	}

	public void setValidodal(Timestamp validodal) {
		if(validodal != null){
			this.validodal = (Timestamp) validodal.clone();	
		}
		else{
			this.validodal = null;
		}
	}

	public Timestamp getValidoal() {
		return (Timestamp) validoal.clone();
	}

	public void setValidoal(Timestamp validoal) {
		if(validoal!=null){
			this.validoal = (Timestamp) validoal.clone();
		}
		else{
			this.validoal = null;
		}
	}

	public String getGatewayservicename() {
		return gatewayservicename;
	}

	public void setGatewayservicename(String gatewayservicename) {
		this.gatewayservicename = gatewayservicename;
	}

	public boolean isFlagnodo() {
		return flagnodo;
	}

	public void setFlagnodo(boolean flagnodo) {
		this.flagnodo = flagnodo;
	}

}
