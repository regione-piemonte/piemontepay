/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// NO OPERATION
public class ClearAppDataCommand implements ICommand {
	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	private String[] _attributesToBeRemovedFromSession = null;

	public ClearAppDataCommand(String[] attributesToBeRemovedFromSession) {
		this._attributesToBeRemovedFromSession = attributesToBeRemovedFromSession;
	}

	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		if (_attributesToBeRemovedFromSession != null
				&& _attributesToBeRemovedFromSession.length != 0) {
			for (int i = 0; i < _attributesToBeRemovedFromSession.length; i++) {
				strutsAction.getSession().remove(
						"appData" + _attributesToBeRemovedFromSession[i]);
			}
		}
		return null;
	}
};
