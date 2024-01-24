/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class BusinessFoundMoreThanOneResultException extends BusinessException {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String cod;
	private String objName;
	private String keyName;

	public BusinessFoundMoreThanOneResultException(Long id, String objName, String keyName) {
		super();
		this.id = id;
		this.objName = objName;
		this.keyName = keyName;
	}

	public BusinessFoundMoreThanOneResultException(String cod, String objName, String keyName) {
		super();
		this.cod = cod;
		this.objName = objName;
		this.keyName = keyName;
	}

	public Long getId() {
		return id;
	}

	public String getCod() {
		return cod;
	}

	public String getObjName() {
		return objName;
	}
	
	public String getKeyName() {
		return keyName;
	}

}
