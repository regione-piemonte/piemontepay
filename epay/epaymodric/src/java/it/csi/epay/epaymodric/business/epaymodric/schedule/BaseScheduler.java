/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.schedule;


/**
 * @author mgiannini Interfaccia comune a tutti gli scheduler. Ogni scheduler deve essere composto da interfaccia e implementazione. L'interfaccia estendera'
 *         BaseScheduler
 */
public interface BaseScheduler {

	public void execute ();
}	
