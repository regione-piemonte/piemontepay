/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.LoggingFlusso;
import it.csi.mdp.mdpetl.dto.LoggingRT;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciLoggingRTDAO extends BaseDAO<LoggingFlusso> {

	public  InserisciLoggingRTDAO(LoggingRT toBeSaved) throws SerialException, SQLException {
		
		setStatementMapper(new GenericObjectArrayStatementMapper(
				toBeSaved.getIuv(),
				toBeSaved.getIstitutoMittente(),
				toBeSaved.getDataOraInvio(),
				toBeSaved.getErrori(),
				toBeSaved.getWarning(),
				toBeSaved.getEsito()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO logging_rt (iuv, istituto_mittente, data_ora_invio, errori, warning, esito) VALUES (?, ?, ?, ?, ?, ?)";
	}

}
