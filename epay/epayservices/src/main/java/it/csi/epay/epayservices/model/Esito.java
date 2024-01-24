/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import javax.annotation.Generated;
import java.io.Serializable;


/**
 *
 */

public class Esito implements Serializable {

	private static final long serialVersionUID = 5821137205605232307L;

	private String code;

	private String description;

	@Generated ( "SparkTools" )
	private Esito ( Builder builder ) {
		this.code = builder.code;
		this.description = builder.description;
	}

	/**
	 * @return the code
	 */
	public String getCode () {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode ( String code ) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription () {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription ( String description ) {
		this.description = description;
	}

	/**
	 * Creates builder to build {@link Esito}.
	 *
	 * @return created builder
	 */
	@Generated ( "SparkTools" )
	public static Builder builder () {
		return new Builder ();
	}

	/**
	 * Builder to build {@link Esito}.
	 */
	@Generated ( "SparkTools" )
	public static final class Builder {

		private String code;

		private String description;

		private Builder () {
		}

		public Builder withCode ( String code ) {
			this.code = code;
			return this;
		}

		public Builder withDescription ( String description ) {
			this.description = description;
			return this;
		}

		public Esito build () {
			return new Esito ( this );
		}
	}

}
