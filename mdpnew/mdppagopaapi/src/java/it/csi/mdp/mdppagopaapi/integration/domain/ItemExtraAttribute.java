/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_extra_attribute database table.
 * 
 */
@Entity
@Table(name="item_extra_attribute")
@NamedQuery(name="ItemExtraAttribute.findAll", query="SELECT i FROM ItemExtraAttribute i")
public class ItemExtraAttribute implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemExtraAttributePK id;

	private String name;

	private String value;

	//bi-directional many-to-one association to Item
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id", insertable=false, updatable=false)
	private Item item;

	public ItemExtraAttribute() {
	}

	public ItemExtraAttributePK getId() {
		return this.id;
	}

	public void setId(ItemExtraAttributePK id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
