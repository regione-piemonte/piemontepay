/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;
import it.csi.mdp.mdpboweb.dto.*;

////ExecCommand
public abstract class ExecCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String _esiti[] = null;
	ICommand _azioni[] = null;
	java.util.Hashtable _resultAzioni = new java.util.Hashtable();

	public ExecCommand(String esiti[], ICommand[] azioni) {
		_esiti = esiti;
		_azioni = azioni;
		if (_esiti != null) {
			for (int i = 0; i < _esiti.length; i++)
				_resultAzioni.put(_esiti[i], _azioni[i]);
		}
	}

	/**
	 * Esecuzione della logica di business.
	 */
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		// action --> model
		Object theModel = strutsAction.toModel();
		// esecuzione azione
		ExecResults execResults = doLogic(theModel);
		String result = execResults.getResultCode();
		Object outModel = execResults.getModel();
		// model --> action
		strutsAction.fromModel(outModel);
		// impostazione degli appData
		storeAppData(execResults, strutsAction);
		// impostazione degli eventuali messaggi
		updateMessages(execResults.getFldErrors(),
				execResults.getGlobalErrors(), execResults.getGlobalMessages(),
				strutsAction);
		// determinazione esito e azione corrispondente
		ICommand resultAction = null;
		for (int i = 0; i < _esiti.length; i++) {
			if (_esiti[i].equals(result))
				resultAction = _azioni[i];
		}
		// esecuzione azione conseguente all'esito
		if (resultAction != null) {
			return resultAction.doCommand(strutsAction);
		} else
			throw new IllegalStateException("Il result code " + result
					+ " non e' tra quelli previsti (" + _esiti + ")");
	}

	/**
	 * inserisce negli appositi campi della action struts2 gli errori/messaggi provenienti dalla
	 * business logic.
	 */
	public void updateMessages(Map<String, String> fieldErrors,
			Collection<String> globalErrors, Collection<String> globalMessages,
			ActionSupport action) {
		if (fieldErrors != null) {
			Iterator<String> fieldKey_it = fieldErrors.keySet().iterator();
			while (fieldKey_it.hasNext()) {
				String currKey = fieldKey_it.next();
				action.addFieldError(currKey, fieldErrors.get(currKey));
			}
		}
		if (globalErrors != null) {
			Iterator<String> it = globalErrors.iterator();
			while (it.hasNext()) {
				action.addActionError(it.next());
			}
		}
		if (globalMessages != null) {
			Iterator<String> it = globalMessages.iterator();
			while (it.hasNext()) {
				action.addActionMessage(it.next());
			}
		}
	}

	/**
	 * Deve essere implamentata nella sottoclasse in modo da impostare in sessione o action i valori
	 * degli app data previsti dalla exec action
	 */
	public abstract void storeAppData(ExecResults res, BaseAction strutsAction);

	// deve essere sovrascritto per eseguire la logica e deve restituire il result_code
	// corretto
	public abstract ExecResults doLogic(Object theModel)
			throws CommandExecutionException;
}
