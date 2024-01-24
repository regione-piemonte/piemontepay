/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the item_extra_attribute database table.
 * 
 */
@Embeddable
public class ItemExtraAttributePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="item_id", insertable=false, updatable=false)
	private long itemId;

	@Column(name="extra_attribute_id")
	private long extraAttributeId;

	public ItemExtraAttributePK() {
	}
	public long getItemId() {
		return this.itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public long getExtraAttributeId() {
		return this.extraAttributeId;
	}
	public void setExtraAttributeId(long extraAttributeId) {
		this.extraAttributeId = extraAttributeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemExtraAttributePK)) {
			return false;
		}
		ItemExtraAttributePK castOther = (ItemExtraAttributePK)other;
		return 
			(this.itemId == castOther.itemId)
			&& (this.extraAttributeId == castOther.extraAttributeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.itemId ^ (this.itemId >>> 32)));
		hash = hash * prime + ((int) (this.extraAttributeId ^ (this.extraAttributeId >>> 32)));
		
		return hash;
	}
}
