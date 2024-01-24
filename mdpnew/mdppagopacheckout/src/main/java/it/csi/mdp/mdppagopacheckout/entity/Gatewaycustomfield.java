/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the gatewaycustomfields database table.
 */
@Entity
@Table ( name = "gatewaycustomfields" )
@NamedQuery ( name = "Gatewaycustomfield.findAll", query = "SELECT g FROM Gatewaycustomfield g" )
@SuppressWarnings ( "unused" )
public class Gatewaycustomfield implements Serializable {

	private static final long serialVersionUID = -1624297587139350931L;

	@Id
	@SequenceGenerator ( name = "GATEWAYCUSTOMFIELDS_KEYID_GENERATOR", sequenceName = "GATEWAYCUSTOMFIELDS_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "GATEWAYCUSTOMFIELDS_KEYID_GENERATOR" )
	private Integer keyid;

	private String fielddescription;

	private String fieldname;

	private String fieldvalue;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "gateway_id" )
	private Gateway gateway;

	public Gatewaycustomfield () {
	}

	public Integer getKeyid () {
		return this.keyid;
	}

	public void setKeyid ( Integer keyid ) {
		this.keyid = keyid;
	}

	public String getFielddescription () {
		return this.fielddescription;
	}

	public void setFielddescription ( String fielddescription ) {
		this.fielddescription = fielddescription;
	}

	public String getFieldname () {
		return this.fieldname;
	}

	public void setFieldname ( String fieldname ) {
		this.fieldname = fieldname;
	}

	public String getFieldvalue () {
		return this.fieldvalue;
	}

	public void setFieldvalue ( String fieldvalue ) {
		this.fieldvalue = fieldvalue;
	}

	public Gateway getGateway () {
		return this.gateway;
	}

	public void setGateway ( Gateway gateway ) {
		this.gateway = gateway;
	}

}
