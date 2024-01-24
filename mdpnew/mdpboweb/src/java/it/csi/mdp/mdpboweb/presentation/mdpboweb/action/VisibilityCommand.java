/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.util.Map;
import java.util.HashMap;

public class VisibilityCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String _containerName = null;
	String[] _targets = null;
	boolean _show = true;

	public VisibilityCommand(String containerName, String targets[],
			boolean show) {
		_containerName = containerName;
		_targets = targets;
		_show = show;
	}

	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		if (_targets != null) {
			Map<String, Boolean> cpWidgetsStatus = (Map<String, Boolean>) strutsAction.session
					.get(_containerName);
			if (cpWidgetsStatus == null) {
				cpWidgetsStatus = new HashMap<String, Boolean>();
				strutsAction.session.put(_containerName, cpWidgetsStatus);
			}
			for (int i = 0; i < _targets.length; i++) {
				cpWidgetsStatus.put(_targets[i] + "_visible",
						Boolean.valueOf(_show));
			}
		}
		return null;
	}
}
