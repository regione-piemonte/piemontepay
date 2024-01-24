/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the config database table.
 */
@Entity
@Table ( name = "config" )
@SuppressWarnings ( "unused" )
public class Config implements Serializable {

	private static final long serialVersionUID = -7852755172747611140L;

	@Id
	@SequenceGenerator ( name = "CONFIG_KEY_GENERATOR", sequenceName = "CONFIG_SEQ", allocationSize = 1 )
	@GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "CONFIG_KEY_GENERATOR" )
	private String key;

	private String descrizione;

	private String value;

	public Config () {
		//  constructor is empty
	}

	public Config ( String value ) {
		this.value = value;
	}

	public String getKey () {
		return this.key;
	}

	public void setKey ( String key ) {
		this.key = key;
	}

	public String getDescrizione () {
		return this.descrizione;
	}

	public void setDescrizione ( String descrizione ) {
		this.descrizione = descrizione;
	}

	public String getValue () {
		return this.value;
	}

	public void setValue ( String value ) {
		this.value = value;
	}

}
