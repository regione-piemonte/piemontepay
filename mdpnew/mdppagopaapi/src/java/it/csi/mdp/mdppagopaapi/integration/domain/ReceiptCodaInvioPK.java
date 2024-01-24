/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the receipt_coda_invio database table.
 * 
 */
@Embeddable
public class ReceiptCodaInvioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String iuv;

	@Column(name="transaction_id")
	private String transactionId;

	public ReceiptCodaInvioPK() {
	}
	public String getIuv() {
		return this.iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public String getTransactionId() {
		return this.transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReceiptCodaInvioPK)) {
			return false;
		}
		ReceiptCodaInvioPK castOther = (ReceiptCodaInvioPK)other;
		return 
			this.iuv.equals(castOther.iuv)
			&& this.transactionId.equals(castOther.transactionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.iuv.hashCode();
		hash = hash * prime + this.transactionId.hashCode();
		
		return hash;
	}
}
