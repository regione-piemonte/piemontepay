/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;

public class Attached implements Serializable {
	private static final long serialVersionUID = -492398333330119382L;

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

	public enum MimeType {
		TESTO("text/plain"), PDF("application/pdf"), XML("text/xml");

		private String type;

		MimeType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}
}
