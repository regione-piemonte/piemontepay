/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the applicationcustomfields_log database table.
 * 
 */
@Entity
@Table(name="applicationcustomfields_log")
@NamedQuery(name="ApplicationcustomfieldsLog.findAll", query="SELECT a FROM ApplicationcustomfieldsLog a")
public class ApplicationcustomfieldsLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="APPLICATIONCUSTOMFIELDS_LOG_SEQ_GENERATOR", sequenceName="APPLICATIONCUSTOMFIELDS_LOG_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLICATIONCUSTOMFIELDS_LOG_SEQ_GENERATOR")
	private Long seq;

	private String applicationid;

	private String fielddescription;

	private String fieldname;

	private String fieldvalue;

	@Column(name="gateway_id")
	private String gatewayId;

	private Long keyid;

	public ApplicationcustomfieldsLog() {
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getFielddescription() {
		return this.fielddescription;
	}

	public void setFielddescription(String fielddescription) {
		this.fielddescription = fielddescription;
	}

	public String getFieldname() {
		return this.fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public String getFieldvalue() {
		return this.fieldvalue;
	}

	public void setFieldvalue(String fieldvalue) {
		this.fieldvalue = fieldvalue;
	}

	public String getGatewayId() {
		return this.gatewayId;
	}

	public void setGatewayId(String gatewayId) {
		this.gatewayId = gatewayId;
	}

	public Long getKeyid() {
		return this.keyid;
	}

	public void setKeyid(Long keyid) {
		this.keyid = keyid;
	}

}
