/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// REFRESHVIEW_ACTION
public class RefreshViewCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String _currentPanelName = null;
	String[] _targetPanels = null;
	String[] _targetWidgets = null;

	public RefreshViewCommand(String currentPanelName,
			String[] targetPanelNames, String[] targetWidgetNames) {
		_currentPanelName = currentPanelName;
		_targetPanels = targetPanelNames;
		_targetWidgets = targetWidgetNames;
	}

	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		return null;
	}
};
