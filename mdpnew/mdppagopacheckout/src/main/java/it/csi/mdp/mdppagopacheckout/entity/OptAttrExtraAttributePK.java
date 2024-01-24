/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * The primary key class for the opt_attr_extra_attribute database table.
 */
@Embeddable
@SuppressWarnings ( "unused" )
public class OptAttrExtraAttributePK implements Serializable {

	private static final long serialVersionUID = -2800305591409470815L;

	@Column ( name = "opt_attr_id", insertable = false, updatable = false )
	private long optAttrId;

	@Column ( name = "extra_attribute_id" )
	private long extraAttributeId;

	private String name;

	public OptAttrExtraAttributePK () {
	}

	public long getOptAttrId () {
		return this.optAttrId;
	}

	public void setOptAttrId ( long optAttrId ) {
		this.optAttrId = optAttrId;
	}

	public long getExtraAttributeId () {
		return this.extraAttributeId;
	}

	public void setExtraAttributeId ( long extraAttributeId ) {
		this.extraAttributeId = extraAttributeId;
	}

	public String getName () {
		return this.name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public boolean equals ( Object other ) {
		if ( this == other ) {
			return true;
		}
		if ( !( other instanceof OptAttrExtraAttributePK ) ) {
			return false;
		}
		OptAttrExtraAttributePK castOther = (OptAttrExtraAttributePK) other;
		return
						( this.optAttrId == castOther.optAttrId )
										&& ( this.extraAttributeId == castOther.extraAttributeId )
										&& this.name.equals ( castOther.name );
	}

	public int hashCode () {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ( (int) ( this.optAttrId ^ ( this.optAttrId >>> 32 ) ) );
		hash = hash * prime + ( (int) ( this.extraAttributeId ^ ( this.extraAttributeId >>> 32 ) ) );
		hash = hash * prime + this.name.hashCode ();

		return hash;
	}
}
