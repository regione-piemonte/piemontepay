/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class PersistenceFoundMoreThanOneResultException extends PersistenceException {
	private static final long serialVersionUID = 1L;

	private String resName;
	private String keyName;
	private String strKeyValue;
	private Long intKeyValue;

	public PersistenceFoundMoreThanOneResultException(String resName, String keyName, String strKeyValue) {
		super("Trovati piu' di un " + resName + " per " + keyName + ": " + strKeyValue);
		this.resName = resName;
		this.keyName = keyName;
		this.strKeyValue = strKeyValue;
		this.intKeyValue = null;
	}

	public PersistenceFoundMoreThanOneResultException(String resName, String keyName, Long intKeyValue) {
		super("Trovati piu' di un " + resName + " per " + keyName + ": " + intKeyValue);
		this.resName = resName;
		this.keyName = keyName;
		this.strKeyValue = null;
		this.intKeyValue = intKeyValue;
	}

	public PersistenceFoundMoreThanOneResultException(String resName, String keyName, Integer intKeyValue) {
		super("Trovati piu' di un " + resName + " per " + keyName + ": " + intKeyValue);
		this.resName = resName;
		this.keyName = keyName;
		this.strKeyValue = null;
		this.intKeyValue = intKeyValue != null ? intKeyValue.longValue() : null;
	}

	public String getResName() {
		return resName;
	}

	public String getKeyName() {
		return keyName;
	}

	public String getKeyValue() {
		return strKeyValue;
	}

	public Long getIntKeyValue() {
		return intKeyValue;
	}

}
