/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.model;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class CheckoutPagoPAPaymentNoticeInput implements Serializable {

	private static final long serialVersionUID = 1063628316567465802L;

	private String noticeNumber;

	private String fiscalCode;

	private Integer amount;

	private String companyName;

	private String description;

	private Boolean allCCP;

	private CheckoutPagoPAPaymentNoticeInput ( Builder builder ) {
		setNoticeNumber ( builder.noticeNumber );
		setFiscalCode ( builder.fiscalCode );
		setAmount ( builder.amount );
		setCompanyName ( builder.companyName );
		setDescription ( builder.description );
		setAllCCP ( builder.allCCP );
	}

	public String getNoticeNumber () {
		return noticeNumber;
	}

	public void setNoticeNumber ( String noticeNumber ) {
		this.noticeNumber = noticeNumber;
	}

	public String getFiscalCode () {
		return fiscalCode;
	}

	public void setFiscalCode ( String fiscalCode ) {
		this.fiscalCode = fiscalCode;
	}

	public Integer getAmount () {
		return amount;
	}

	public void setAmount ( Integer amount ) {
		this.amount = amount;
	}

	public String getCompanyName () {
		return companyName;
	}

	public void setCompanyName ( String companyName ) {
		this.companyName = companyName;
	}

	public String getDescription () {
		return description;
	}

	public void setDescription ( String description ) {
		this.description = description;
	}

	public Boolean getAllCCP () {
		return allCCP;
	}

	public void setAllCCP ( Boolean allCCP ) {
		this.allCCP = allCCP;
	}

	@Override public String toString () {
		return "CheckoutPagoPAPaymentNoticeInput{" +
						"noticeNumber='" + noticeNumber + '\'' +
						", fiscalCode='" + fiscalCode + '\'' +
						", amount=" + amount +
						", companyName='" + companyName + '\'' +
						", description='" + description + '\'' +
						", allCCP=" + allCCP +
						'}';
	}

	public static final class Builder {

		private String noticeNumber;

		private String fiscalCode;

		private Integer amount;

		private String companyName;

		private String description;

		private Boolean allCCP;

		public Builder () {
		}

		public Builder noticeNumber ( String val ) {
			noticeNumber = val;
			return this;
		}

		public Builder fiscalCode ( String val ) {
			fiscalCode = val;
			return this;
		}

		public Builder amount ( Integer val ) {
			amount = val;
			return this;
		}

		public Builder companyName ( String val ) {
			companyName = val;
			return this;
		}

		public Builder description ( String val ) {
			description = val;
			return this;
		}

		public Builder allCCP ( Boolean val ) {
			allCCP = val;
			return this;
		}

		public CheckoutPagoPAPaymentNoticeInput build () {
			return new CheckoutPagoPAPaymentNoticeInput ( this );
		}
	}
}
