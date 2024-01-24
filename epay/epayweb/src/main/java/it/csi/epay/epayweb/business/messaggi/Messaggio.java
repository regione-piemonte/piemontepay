/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.business.messaggi;

import java.io.Serializable;

public class Messaggio implements Serializable, Comparable<Messaggio> {
	private static final long serialVersionUID = 980989107478325191L;
	
	private LevelMessage level;
	private String testo;
	
	public Messaggio(LevelMessage level, String testo) {
		this.level = level;
		this.testo = testo;
	}
	
	
	/**
	 * @return the level
	 */
	public LevelMessage getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(LevelMessage level) {
		this.level = level;
	}
	/**
	 * @return the testo
	 */
	public String getTesto() {
		return testo;
	}
	/**
	 * @param testo the testo to set
	 */
	public void setTesto(String testo) {
		this.testo = testo;
	}

	@Override
	public int compareTo(Messaggio other) {
		return Integer.compare(this.level.ordinal(), other.level.ordinal());
	}	
}
