/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop;

public class PersistenceRemoveException extends PersistenceException {
	private static final long serialVersionUID = 1L;

	public PersistenceRemoveException(String message) {
		super(message);
	}

}
