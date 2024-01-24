/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.uiutils;

import java.util.List;

/**
 * @generated
 */
public class TableArrangedData {

	//***numero totale dei record nella tabella
	private int numtot;
	//***elenco dei record della tabella
	private List<Object> elenco;
	//***elenco delle posizioni occupate dai record nella collection originale
	private List<DataPosition> positions;
	//***parametro che indica se deve essere resettato lo stato della tabella
	private boolean clearStatus;

	public int getNumtot() {
		return numtot;
	}
	public void setNumtot(int numtot) {
		this.numtot = numtot;
	}
	public List<Object> getElenco() {
		return elenco;
	}
	public void setElenco(List<Object> elenco) {
		this.elenco = elenco;
	}
	public List<DataPosition> getPositions() {
		return positions;
	}
	public void setPositions(List<DataPosition> positions) {
		this.positions = positions;
	}
	public boolean isClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(boolean clearStatus) {
		this.clearStatus = clearStatus;
	}
}
