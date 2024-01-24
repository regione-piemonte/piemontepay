/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;

import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

public class ModificaStatoInvioFlussiDAO extends BaseDAO<FlussoRiversamento> {
	
	private FiltroFlussiFlagInvioEnum tipoFlusso;

	public  ModificaStatoInvioFlussiDAO(Integer id, Integer statoFlusso, FiltroFlussiFlagInvioEnum tipoFlusso) throws SerialException, SQLException {
		
		if (null == tipoFlusso || StringUtils.isEmpty( tipoFlusso.getNomeAttributo())) {
			throw new SQLException("Tipo flusso non presente");
		}
		
		this.tipoFlusso = tipoFlusso;
		
		setStatementMapper(new GenericObjectArrayStatementMapper(statoFlusso, id));
		
	}
	
	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		String query = String.format("update flusso_riversamento set %s = ? WHERE id = ?", tipoFlusso.getNomeAttributo());
		strSql.append( query );
		return strSql.toString();
	}
}
