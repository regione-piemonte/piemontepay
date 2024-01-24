/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpmultiiuvsrv.dto.mdpmultiiuv;

public class ApplicationcustomfieldsDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String keyid;
	private String applicationid;
	private String fieldname;
	private String fieldvalue;
	private String gatewayId;
	private String fielddescription;

	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getApplicationid() {
		return applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFieldvalue() {
		return fieldvalue;
	}

	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

	public String getGatewayId() {
		return gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public String getFielddescription() {
		return fielddescription;
	}

	public void setFielddescription(String fielddescription) {
		this.fielddescription = fielddescription;
	}

}
