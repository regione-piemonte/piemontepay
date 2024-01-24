/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opt_attr_extra_attribute database table.
 * 
 */
@Entity
@Table(name="opt_attr_extra_attribute")
@NamedQuery(name="OptAttrExtraAttribute.findAll", query="SELECT o FROM OptAttrExtraAttribute o")
public class OptAttrExtraAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OptAttrExtraAttributePK id;

	private String value;

	//bi-directional many-to-one association to OptAttr
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="opt_attr_id", insertable=false, updatable=false)
	private OptAttr optAttr;

	public OptAttrExtraAttribute() {
	}

	public OptAttrExtraAttributePK getId() {
		return this.id;
	}

	public void setId(OptAttrExtraAttributePK id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public OptAttr getOptAttr() {
		return this.optAttr;
	}

	public void setOptAttr(OptAttr optAttr) {
		this.optAttr = optAttr;
	}

}
