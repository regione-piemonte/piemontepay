/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.Configurazione;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstraiConfigurazioneDAO extends BaseDAO<Configurazione> {
	 LogUtil log = new LogUtil(EstraiConfigurazioneDAO.class);
	
	public  EstraiConfigurazioneDAO(String key) {
		setStatementMapper(new GenericObjectArrayStatementMapper(key));
		setResultSetExtractor(new ConfigurazioneExtractor());
	}

	@Override
	public String componiQuery() {
		return "select * from config where key = ?";
	}
}

class ConfigurazioneExtractor implements ResultSetExtractor<Configurazione> {
	
	public Configurazione extractData(ResultSet rs) throws SQLException {
		Configurazione configurazione = new Configurazione();
		if(rs.next()){
			configurazione.setKey(rs.getString("key"));
			configurazione.setValue(rs.getString("value"));
			configurazione.setDescrizione(rs.getString("descrizione"));
		}
		return configurazione;
	}
	
}

