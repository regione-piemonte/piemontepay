/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/// BEGIN_EDIT_ACTION
public class BeginEditCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String[] targetAppDataNames;
	public BeginEditCommand(String[] targetAppDataNames) {
		this.targetAppDataNames = targetAppDataNames;
	}

	/**
	 * Salva un backup (deep copy) degli appdata specificati
	 */
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		if (targetAppDataNames != null) {
			for (int i = 0; i < targetAppDataNames.length; i++) {
				String currADName = targetAppDataNames[i];
				try {
					storeBackup(currADName, strutsAction);
				} catch (CloneNotSupportedException cnse) {
					throw new CommandExecutionException(
							"Errore nella creazioene del backup dell'appdata ["
									+ currADName + "]:" + cnse.getMessage(),
							cnse);
				}
			}
		}
		return null;
	}

	public static void storeBackup(String adName, BaseAction strutsAction)
			throws CloneNotSupportedException {
		Object currADVal = strutsAction.getSession().get(adName);
		Object currADValClone = deepClone(currADVal);
		if (currADVal != null) {
			strutsAction.getSession().put(adName + "_bckp", currADValClone);
		}
	}

	public static Object deepClone(Object orig)
			throws CloneNotSupportedException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(orig);
			ByteArrayInputStream bais = new ByteArrayInputStream(
					baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object clone = ois.readObject();
			return clone;
		} catch (IOException e) {
			throw new CloneNotSupportedException();
		} catch (ClassNotFoundException e) {
			throw new CloneNotSupportedException();
		}
	}
};
