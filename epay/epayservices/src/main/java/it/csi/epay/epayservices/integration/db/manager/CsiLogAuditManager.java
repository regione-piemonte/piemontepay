/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import java.sql.Timestamp;
import java.util.Date;

import it.csi.epay.epayservices.integration.db.entities.CsiLogAudit;

public class CsiLogAuditManager {

	static private Timestamp currentTimestamp() {
		return new Timestamp((new Date()).getTime());
	}
	
	public CsiLogAudit insert() {
		//TODO
		//MDPNEW_INT01_PROD_MDPBOSERVICES
		CsiLogAudit audit = new CsiLogAudit();
		audit.setDataOra(currentTimestamp());
					
		return null;
	}
}
