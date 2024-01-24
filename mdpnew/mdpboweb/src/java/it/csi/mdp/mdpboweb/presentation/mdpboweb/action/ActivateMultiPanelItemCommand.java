/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

/// ActivateMultiPanelItemCommand
public class ActivateMultiPanelItemCommand implements ICommand {

	// il serial version uid e' fisso in quanto la classe in oggetto e' serializzabile
	// solo per poter essere inserita in sessione web e non viene scambiata con altre
	// componenti.
	private static final long serialVersionUID = 1L;

	String contentPanelName;
	String containerName;
	String itemName;

	/**
	 * @param contentPanelName il nome del content panel
	 * @param containerName il nome del multi-panel contenitore
	 * @param itemName il nome dell'item da attivare (se null => non mostrare
	 *  nessun pannello
	 */
	public ActivateMultiPanelItemCommand(String contentPanelName,
			String containerName, String itemName) {
		assert contentPanelName != null && contentPanelName.length() > 0;
		assert containerName != null && containerName.length() > 0;
		// item name puo essere nullo => deselect

		this.contentPanelName = contentPanelName;
		this.containerName = containerName;
		this.itemName = itemName;
	}
	public String doCommand(BaseAction strutsAction)
			throws CommandExecutionException {
		if (itemName != null) {
			strutsAction.getSession().put(
					contentPanelName + "_" + containerName
							+ "_selectedMultiPanel",
					contentPanelName + "_" + containerName + "_" + itemName);
		} else {
			strutsAction.getSession().remove(
					contentPanelName + "_" + containerName
							+ "_selectedMultiPanel");
		}
		return null;
	}
};
