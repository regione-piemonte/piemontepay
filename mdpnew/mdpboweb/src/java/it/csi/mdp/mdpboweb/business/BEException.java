/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.business;

/**
 * Eccezione rilanciata dallo strato spring nel caso in cui la condizione di 
 * errore non sia gestita attraverso un outcome_code e corrispondenti messaggi.
 * Serve per gestire situazioni non recuperabili o impreviste
 */
public class BEException extends Exception {
	public BEException(String msg, Exception nested) {
		super(msg, nested);
	}
}
