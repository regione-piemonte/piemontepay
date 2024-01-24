/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// JUMP_ACTION
public class JumpExtCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String locationCode;
	public JumpExtCommand(String locationCode) {
		this.locationCode = locationCode;
	}
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		// clear page scoped appdata
		strutsAction.clearPageScopedAppData(null);
		return "GO_TO_" + locationCode;
	}
};
