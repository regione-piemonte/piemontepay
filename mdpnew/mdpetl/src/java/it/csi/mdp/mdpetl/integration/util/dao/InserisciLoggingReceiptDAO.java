/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.LoggingFlusso;
import it.csi.mdp.mdpetl.dto.LoggingReceipt;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


public class InserisciLoggingReceiptDAO extends BaseDAO<LoggingFlusso> {

	public InserisciLoggingReceiptDAO ( LoggingReceipt toBeSaved ) {
		setStatementMapper ( new GenericObjectArrayStatementMapper (
						toBeSaved.getIuv (),
						toBeSaved.getIstitutoMittente (),
						toBeSaved.getDataOraInvio (),
						toBeSaved.getErrori (),
						toBeSaved.getWarning (),
						toBeSaved.getEsito ()
		) );
	}

	@Override public String componiQuery () {
		return "INSERT INTO logging_receipt (iuv, istituto_mittente, data_ora_invio, errori, warning, esito) VALUES (?, ?, ?, ?, ?, ?)";
	}
}
