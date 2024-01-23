/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.CodiciEsitoPagamentoDao;
import it.csi.mdp.core.business.dto.CodiciEsitoPagamento;
import it.csi.mdp.core.business.exceptions.CodiciEsitoPagamentoDaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class CodiciEsitoPagamentoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<CodiciEsitoPagamento>, CodiciEsitoPagamentoDao
{
	protected NamedParameterJdbcTemplate jdbcTemplate;

	protected DataSource dataSource;

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	@Transactional
	public List<CodiciEsitoPagamento> getCodiciEsitoPagamentoAll() throws CodiciEsitoPagamentoDaoException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		return jdbcTemplate.query("SELECT * FROM codici_esito_pagamento ORDER BY id_esito_pagamento ", params, this);
	}

	public CodiciEsitoPagamento mapRow(ResultSet rs, int index) throws SQLException {
		CodiciEsitoPagamento gde = new CodiciEsitoPagamento();
		gde.setIdEsitoPagamento(rs.getInt("id_esito_pagamento"));
		gde.setDescrizione(rs.getString("descrizione"));
		return gde;
	}
	
}





