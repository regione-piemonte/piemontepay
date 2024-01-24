/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class CheckoutPagoPAOKOutput extends CheckoutPagoPAOutput implements Serializable {

	private static final long serialVersionUID = -7858064455219005627L;

	@JsonIgnore
	private String location;

	private CheckoutPagoPAOKOutput ( Builder builder ) {
		setLocation ( builder.location );
		setOk ( builder.ok );
	}

	public String getLocation () {
		return location;
	}

	public void setLocation ( String location ) {
		this.location = location;
	}

	@Override
	public String toString () {
		return "CheckoutPagoPAOKOutput{" +
						"location='" + location + '\'' +
						'}';
	}

	public static final class Builder {

		private String location;

		private boolean ok;

		public Builder () {
		}

		public Builder location ( String val ) {
			location = val;
			return this;
		}

		public Builder ok ( boolean val ) {
			ok = val;
			return this;
		}

		public CheckoutPagoPAOKOutput build () {
			return new CheckoutPagoPAOKOutput ( this );
		}
	}
}
