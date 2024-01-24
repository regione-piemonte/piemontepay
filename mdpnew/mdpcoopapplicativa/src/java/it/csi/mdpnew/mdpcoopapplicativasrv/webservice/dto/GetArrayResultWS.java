/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.webservice.dto;

/**
 * per ora questa classe e' inutilizzata
 * @author Paolo 
 *
 */
public class GetArrayResultWS extends BaseResultWs {
	
	
	private String[] iuvGroup;

	public String[] getIuvGroup() {
		return iuvGroup;
	}

	public void setIuvGroup(String[] iuvGroup) {
		this.iuvGroup = iuvGroup;
	}

}
