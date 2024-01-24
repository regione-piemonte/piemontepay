/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the opt_attr_extra_attribute database table.
 */
@Entity
@Table ( name = "opt_attr_extra_attribute" )
@NamedQuery ( name = "OptAttrExtraAttribute.findAll", query = "SELECT o FROM OptAttrExtraAttribute o" )
@SuppressWarnings ( "unused" )
public class OptAttrExtraAttribute implements Serializable {

	private static final long serialVersionUID = 3369856050880659856L;

	@EmbeddedId
	private OptAttrExtraAttributePK id;

	private String value;

	@ManyToOne ( fetch = FetchType.LAZY )
	@JoinColumn ( name = "opt_attr_id", insertable = false, updatable = false )
	private OptAttr optAttr;

	public OptAttrExtraAttribute () {
	}

	public OptAttrExtraAttributePK getId () {
		return this.id;
	}

	public void setId ( OptAttrExtraAttributePK id ) {
		this.id = id;
	}

	public String getValue () {
		return this.value;
	}

	public void setValue ( String value ) {
		this.value = value;
	}

	public OptAttr getOptAttr () {
		return this.optAttr;
	}

	public void setOptAttr ( OptAttr optAttr ) {
		this.optAttr = optAttr;
	}

}
