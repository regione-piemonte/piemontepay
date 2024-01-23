/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dto;

import java.io.Serializable;

public class DettaglioFruitore implements Serializable {

	private static final long serialVersionUID = 6338255355044246513L;
	
	private Application applicazioneFruitrice;
	private ApplicationDetail applicationDetail;
	private Applicationcustomfields[] elencoAcf;
	public Application getApplicazioneFruitrice() {
		return applicazioneFruitrice;
	}
	public void setApplicazioneFruitrice(Application applicazioneFruitrice) {
		this.applicazioneFruitrice = applicazioneFruitrice;
	}
	public ApplicationDetail getApplicationDetail() {
		return applicationDetail;
	}
	public void setApplicationDetail(ApplicationDetail applicationDetail) {
		this.applicationDetail = applicationDetail;
	}
	public Applicationcustomfields[] getElencoAcf() {
		return elencoAcf;
	}
	public void setElencoAcf(Applicationcustomfields[] elencoAcf) {
		this.elencoAcf = elencoAcf;
	}
	
	
}
