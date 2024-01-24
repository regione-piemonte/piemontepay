/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.util.mail;

public class Attached {
	private String name;
	private byte[] data;
	private MimeType type;

	public Attached() {
	}

	public Attached(String name, byte[] data, MimeType type) {
		this.name = name;
		this.data = data;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type.getType();
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MimeType type) {
		this.type = type;
	}
}	

