/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// SEQUENCE_ACTION
public class SequenceCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	ICommand[] _actions;
	public SequenceCommand(ICommand[] actions) {
		_actions = actions;
	}

	/**
	 * La sequence action esegue in sequenza tutte le azioni atomiche
	 * incluse come step, mantenendo il pi&ugrave; recente risultato non nullo restituito
	 * dagli step (per determinare al termine quale debba essere la pagina successiva
	 */
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		String result = null;
		if (_actions != null) {
			for (int i = 0; i < _actions.length; i++) {
				ICommand currAct = _actions[i];
				String currResult = currAct.doCommand(strutsAction);
				if (currResult != null)
					result = currResult;
			}
		}
		return result;
	}
};
