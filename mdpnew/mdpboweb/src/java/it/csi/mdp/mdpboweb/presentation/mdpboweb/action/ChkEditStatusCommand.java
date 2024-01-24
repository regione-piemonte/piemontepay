/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// CHECK_EDIT_STATUS_ACTION
public class ChkEditStatusCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	ICommand _doIfChanged;
	ICommand _doIfNotChanged;
	String _checkAggr;
	String[] _checkedData;

	public ChkEditStatusCommand(String[] checkedData, String checkAggregation,
			ICommand doIfChanged, ICommand doIfNotChanged) {
		_checkedData = checkedData;
		_checkAggr = checkAggregation;
		_doIfChanged = doIfChanged;
		_doIfNotChanged = doIfNotChanged;
	}

	/**
	 * La sequence action esegue in sequenza tutte le azioni atomiche
	 * incluse come step, mantenendo il pi&ugrave; recente risultato non nullo restituito
	 * dagli step (per determinare al termine quale debba essere la pagina successiva
	 */
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		boolean changed = checkAppdata4Changes(_checkedData, _checkAggr,
				strutsAction);
		if (changed)
			return _doIfChanged.doCommand(strutsAction);
		else
			return _doIfNotChanged.doCommand(strutsAction);
	}

	/**
	 * Verifica variazioni sugli appdata con la politica di aggregazione specificata
	 * @param checkedData
	 * @param checkAggr
	 * @param strutsAction
	 * @return
	 */
	boolean checkAppdata4Changes(String[] checkedData, String checkAggr,
			BaseAction strutsAction) {
		if (checkedData == null || checkedData.length == 0)
			return false;
		// else
		boolean ris = "OR".equals(checkAggr) ? false : ("AND".equals(checkAggr)
				? true
				: false);
		for (int i = 0; i < checkedData.length; i++) {
			String currADName = checkedData[i];
			boolean currChanged = checkAppdata4Changes(currADName, strutsAction);
			if (currChanged && "OR".equals(checkAggr))
				return true; // al primo esco con true
			else if (!currChanged && "AND".equals(checkAggr))
				return false; // al primo falso esco con falso
			else if ("XOR".equals(checkAggr)) {
				if (currChanged && ris)
					return false; // secondo match: rompe lo xor
			}
		}
		return ris;
	}

	/**
	 * verifica se il singolo appdata e' variato
	 * @param currADName
	 * @param strutsAction
	 * @return
	 */
	private boolean checkAppdata4Changes(String currADName,
			BaseAction strutsAction) {
		Object adVal = strutsAction.getSession().get(currADName);
		Object adBkp = strutsAction.getSession().get(currADName + "_bckp");
		if (adBkp != null) {
			it.csi.mdp.mdpboweb.dto.DTOUtils dtoUtils = it.csi.mdp.mdpboweb.dto.DTOUtils
					.getInstance();
			return !(dtoUtils.deepEquals(adVal, adBkp));
		} else
			return false;
	}

};
