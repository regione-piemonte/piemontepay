/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class IuvOtticoFruitore implements Serializable {

	private static final long serialVersionUID = 6338255355044246513L;
	
	private IuvOttici iuvOttici;
	private ApplicationDetail applicationDetail;
	private String endpointUrlFruitore;
	private String gatewayId;
	private Applicationcustomfields[] elencoAcf;
	public IuvOttici getIuvOttici() {
		return iuvOttici;
	}
	public void setIuvOttici(IuvOttici iuvOttici) {
		this.iuvOttici = iuvOttici;
	}
	
	public String getEndpointUrlFruitore() {
		return endpointUrlFruitore;
	}
	public void setEndpointUrlFruitore(String endpointUrlFruitore) {
		this.endpointUrlFruitore = endpointUrlFruitore;
	}
	public String getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}
	public ApplicationDetail getApplicationDetail() {
		return applicationDetail;
	}
	public void setApplicationDetail(ApplicationDetail applicationDetail) {
		this.applicationDetail = applicationDetail;
	}
	public Applicationcustomfields[] getElencoAcf() {
		return elencoAcf;
	}
	public void setElencoAcf(Applicationcustomfields[] elencoAcf) {
		this.elencoAcf = elencoAcf;
	}

}
