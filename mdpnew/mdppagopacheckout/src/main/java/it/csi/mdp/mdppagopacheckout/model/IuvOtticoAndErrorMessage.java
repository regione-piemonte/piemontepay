/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.model;

import it.csi.mdp.mdppagopacheckout.entity.IuvOttici;


@SuppressWarnings ( "unused" )
public class IuvOtticoAndErrorMessage {

	private IuvOttici iuvOttico;

	private String error;

	public IuvOttici getIuvOttico () {
		return iuvOttico;
	}

	public void setIuvOttico ( IuvOttici iuvOttico ) {
		this.iuvOttico = iuvOttico;
	}

	public String getError () {
		return error;
	}

	public void setError ( String error ) {
		this.error = error;
	}
}
