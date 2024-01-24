/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.LoggingFlusso;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciLoggingFlussoDAO extends BaseDAO<LoggingFlusso> {

	public  InserisciLoggingFlussoDAO(LoggingFlusso toBeSaved) throws SerialException, SQLException {
		
		setStatementMapper(new GenericObjectArrayStatementMapper(
				toBeSaved.getIdFlusso(),
				toBeSaved.getIstitutoMittente(),
				toBeSaved.getTipoFlusso(),
				toBeSaved.getDataOraInvio(),
				toBeSaved.getErrori(),
				toBeSaved.getWarning(),
				toBeSaved.getEsito(),
				toBeSaved.getIdMessaggio ()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO logging_flusso(id_flusso, istituto_mittente, tipo_flusso, data_ora_invio, errori, warning, esito, id_messaggio)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	}

}
