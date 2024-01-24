/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

public class RecordNumberGreaterThanThresholdException  extends Exception {

	private static final long serialVersionUID = 1L;

	public RecordNumberGreaterThanThresholdException(String message) {
		super(message);
	}
}
