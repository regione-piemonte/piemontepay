/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.model;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class CheckoutPagoPAReturnUrlInput implements Serializable {

	private static final long serialVersionUID = 3055981876317778668L;

	private final String returnOkUrl;

	private final String returnCancelUrl;

	private final String returnErrorUrl;

	private CheckoutPagoPAReturnUrlInput ( Builder builder ) {
		returnOkUrl = builder.returnOkUrl;
		returnCancelUrl = builder.returnCancelUrl;
		returnErrorUrl = builder.returnErrorUrl;
	}

	public String getReturnOkUrl () {
		return returnOkUrl;
	}

	public String getReturnCancelUrl () {
		return returnCancelUrl;
	}

	public String getReturnErrorUrl () {
		return returnErrorUrl;
	}

	@Override
	public String toString () {
		return "CheckoutPagoPAReturnUrlInput{" +
						"returnOkUrl='" + returnOkUrl + '\'' +
						", returnCancelUrl='" + returnCancelUrl + '\'' +
						", returnErroUrl='" + returnErrorUrl + '\'' +
						'}';
	}

	public static final class Builder {

		private String returnOkUrl;

		private String returnCancelUrl;

		private String returnErrorUrl;

		public Builder () {
		}

		public Builder returnOkUrl ( String val ) {
			returnOkUrl = val;
			return this;
		}

		public Builder returnCancelUrl ( String val ) {
			returnCancelUrl = val;
			return this;
		}

		public Builder returnErroUrl ( String val ) {
			returnErrorUrl = val;
			return this;
		}

		public CheckoutPagoPAReturnUrlInput build () {
			return new CheckoutPagoPAReturnUrlInput ( this );
		}
	}
}
