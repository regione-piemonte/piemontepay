/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

/** Classe padre di monitoriaggio lato business */
public class MonitoringBusinessBase {
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".monitoring");

}
