/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * The primary key class for the transazione_extra_attribute database table.
 */
@Embeddable
@SuppressWarnings ( "unused" )
public class TransazioneExtraAttributePK implements Serializable {

	private static final long serialVersionUID = -5943433430113478196L;

	@Column ( name = "transaction_id", insertable = false, updatable = false )
	private String transactionId;

	@Column ( name = "extra_attribute_id" )
	private long extraAttributeId;

	private String name;

	public TransazioneExtraAttributePK () {
	}

	public String getTransactionId () {
		return this.transactionId;
	}

	public void setTransactionId ( String transactionId ) {
		this.transactionId = transactionId;
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
		if ( !( other instanceof TransazioneExtraAttributePK ) ) {
			return false;
		}
		TransazioneExtraAttributePK castOther = (TransazioneExtraAttributePK) other;
		return
						this.transactionId.equals ( castOther.transactionId )
										&& ( this.extraAttributeId == castOther.extraAttributeId )
										&& this.name.equals ( castOther.name );
	}

	public int hashCode () {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.transactionId.hashCode ();
		hash = hash * prime + ( (int) ( this.extraAttributeId ^ ( this.extraAttributeId >>> 32 ) ) );
		hash = hash * prime + this.name.hashCode ();

		return hash;
	}
}
