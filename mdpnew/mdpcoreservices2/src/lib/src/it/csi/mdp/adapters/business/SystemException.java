/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.adapters.business;

public class SystemException extends Exception{

	private static final long serialVersionUID = -6874983009957706818L;
	
	public SystemException(String message) {
		super(message);
	}

}
