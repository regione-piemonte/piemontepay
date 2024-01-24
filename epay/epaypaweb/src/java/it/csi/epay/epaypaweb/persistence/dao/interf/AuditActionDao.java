/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.AuditAction;

public interface AuditActionDao extends EpaypaDaoBase<String, AuditAction> {
	
	/**
	 * Metodo per trovare l'auditAction in base al valore di idAction
	 * @param idAction id dell'action da trovare nel database
	 * @return un oggetto contenente delle informazioni relative a quell'idAction
	 */
	AuditAction findByIdAction(String idAction);
}
