/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;
public class MultiPanelStructure implements java.io.Serializable {
	private String selectedPanelKey;
	private String selectedPanelValue;
	public String getSelectedPanelKey() {
		return selectedPanelKey;
	}
	public void setSelectedPanelKey(String selectedPanelKey) {
		this.selectedPanelKey = selectedPanelKey;
	}
	public String getSelectedPanelValue() {
		return selectedPanelValue;
	}
	public void setSelectedPanelValue(String selectedPanelValue) {
		this.selectedPanelValue = selectedPanelValue;
	}
	public MultiPanelStructure() {
	}
	public MultiPanelStructure(String selectedPanelKey,
			String selectedPanelValue) {
		this.selectedPanelKey = selectedPanelKey;
		this.selectedPanelValue = selectedPanelValue;
	}
}
