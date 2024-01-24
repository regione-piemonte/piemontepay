/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.gestioneente;

public class RisultatoRicercaEnteVO extends EnteVO {

	private static final long serialVersionUID = 1L;
	
	private boolean modificaConsentita;

	public RisultatoRicercaEnteVO() {
		super();
	}

	public boolean isModificaConsentita() {
		return modificaConsentita;
	}

	public void setModificaConsentita(boolean modificaConsentita) {
		this.modificaConsentita = modificaConsentita;
	}

}
