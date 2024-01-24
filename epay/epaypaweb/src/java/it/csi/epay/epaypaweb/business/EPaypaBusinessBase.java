/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

/** Classe padre di marc lato business */
public class EPaypaBusinessBase {
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".business");

	protected Timestamp getTimestampNow() {
		return new Timestamp(System.currentTimeMillis());
	}

    protected boolean isEmpty ( String raw ) {
        return raw == null || raw.trim ().equals ( "" );
    }

}
