/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.model;

import java.io.Serializable;
import java.util.List;


@SuppressWarnings ( "unused" )
public class CheckoutPagoPAInput implements Serializable {

	private static final long serialVersionUID = -7845717245095827700L;

	private final String idCart;

	private final List<CheckoutPagoPAPaymentNoticeInput> paymentNotices;

	private final CheckoutPagoPAReturnUrlInput returnUrls;

	private String emailNotice;

	private CheckoutPagoPAInput ( Builder builder ) {
		setEmailNotice ( builder.emailNotice );
		idCart = builder.idCart;
		paymentNotices = builder.paymentNotices;
		returnUrls = builder.returnUrls;
	}

	public String getEmailNotice () {
		return emailNotice;
	}

	public void setEmailNotice ( String emailNotice ) {
		this.emailNotice = emailNotice;
	}

	@Override
	public String toString () {
		return "CheckoutPagoPAInput{" +
						"emailNotice='" + emailNotice + '\'' +
						", idCart='" + idCart + '\'' +
						", paymentNotices=" + paymentNotices +
						", returnUrls=" + returnUrls +
						'}';
	}

	public String getIdCart () {
		return idCart;
	}

	public List<CheckoutPagoPAPaymentNoticeInput> getPaymentNotices () {
		return paymentNotices;
	}

	public CheckoutPagoPAReturnUrlInput getReturnUrls () {
		return returnUrls;
	}


	public static final class Builder {

		private String emailNotice;

		private String idCart;

		private List<CheckoutPagoPAPaymentNoticeInput> paymentNotices;

		private CheckoutPagoPAReturnUrlInput returnUrls;

		public Builder () {
		}

		public Builder emailNotice ( String val ) {
			emailNotice = val;
			return this;
		}

		public Builder idCart ( String val ) {
			idCart = val;
			return this;
		}

		public Builder paymentNotices ( List<CheckoutPagoPAPaymentNoticeInput> val ) {
			paymentNotices = val;
			return this;
		}

		public Builder returnUrls ( CheckoutPagoPAReturnUrlInput val ) {
			returnUrls = val;
			return this;
		}

		public CheckoutPagoPAInput build () {
			return new CheckoutPagoPAInput ( this );
		}
	}
}
