/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// END_EDIT_ACTION
public class EndEditCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String[] targetAppDataNames;
	boolean undo;

	public EndEditCommand(String[] targetAppDataNames, boolean undo) {
		this.targetAppDataNames = targetAppDataNames;
		this.undo = undo;
	}

	/**
	 * Elimina i backup (deep copy) degli appdata specificati, ripristinando il
	 * valore se undo==true; 
	 */
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		if (targetAppDataNames != null) {
			for (int i = 0; i < targetAppDataNames.length; i++) {
				String currADName = targetAppDataNames[i];
				endEditForAppdata(currADName, undo, strutsAction);
			}
		}
		return null;
	}

	public static void endEditForAppdata(String adName, boolean undo,
			BaseAction strutsAction) {
		if (undo) {
			// undo del dato
			Object currADValBkp = strutsAction.getSession().get(
					adName + "_bckp");
			if (currADValBkp != null) {
				strutsAction.getSession().put(adName, currADValBkp);
			}
		}
		// rimuove il backup 
		strutsAction.getSession().remove(adName + "_bckp");
	}

};
