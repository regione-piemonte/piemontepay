/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// SHOW_DIALOG_ACTION
public class ShowDialogCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String name;
	public ShowDialogCommand(String panelName) {
		name = panelName;
	}
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		return "SHOW_" + name;
	}
};
