/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// JUMP_ACTION
public class JumpCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String name;
	String _currentPageName = null;
	boolean _pushCurrent;
	public JumpCommand(String panelName, String currentPageName,
			boolean pushCurrent) {
		name = panelName;
		_pushCurrent = pushCurrent;
		_currentPageName = currentPageName;
	}
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		if (_pushCurrent) {
			//TODO capire se e come implementare lo stack delle action
			//_pageStack.add(_currentPageName);
		}
		// clear page scoped appdata
		strutsAction.clearPageScopedAppData(name);
		return "GO_TO_" + name;
	}
};
