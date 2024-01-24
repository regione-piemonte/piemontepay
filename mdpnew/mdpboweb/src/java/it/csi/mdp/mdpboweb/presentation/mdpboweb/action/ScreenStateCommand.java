/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.util.Map;
import java.util.HashMap;

public class ScreenStateCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String _containerName = null;
	String _stateName = null;
	String[] _widgetsOn = null;
	String[] _widgetsOff = null;
	String[] _widgetsShown = null;
	String[] _widgetsHidden = null;

	public ScreenStateCommand(String containerName, String stateName,
			String widgetsOn[], String widgetsOff[], String widgetsShown[],
			String widgetsHidden[]) {
		_containerName = containerName;
		_stateName = stateName;
		_widgetsOn = widgetsOn;
		_widgetsOff = widgetsOff;
		_widgetsShown = widgetsShown;
		_widgetsHidden = widgetsHidden;
	}

	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		OnOffCommand turnOn = new OnOffCommand(_containerName, _widgetsOn, true);
		OnOffCommand turnOff = new OnOffCommand(_containerName, _widgetsOff,
				false);
		VisibilityCommand show = new VisibilityCommand(_containerName,
				_widgetsShown, true);
		VisibilityCommand hide = new VisibilityCommand(_containerName,
				_widgetsHidden, false);
		turnOn.doCommand(strutsAction);
		turnOff.doCommand(strutsAction);
		show.doCommand(strutsAction);
		hide.doCommand(strutsAction);
		if (_stateName != null) {
			strutsAction.getSession().put(_containerName + "_currentState",
					_stateName);
		}
		return null;
	}
}
