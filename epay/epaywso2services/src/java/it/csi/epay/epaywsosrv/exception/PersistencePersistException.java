/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.exception;

public class PersistencePersistException extends PersistenceException {
	private static final long serialVersionUID = 1L;

	public PersistencePersistException(String message) {
		super(message);
	}

}
