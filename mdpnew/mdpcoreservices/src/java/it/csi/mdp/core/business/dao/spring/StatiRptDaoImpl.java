/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.StatiRptDao;
import it.csi.mdp.core.business.dto.StatiRpt;
import it.csi.mdp.core.business.exceptions.StatiRptDaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class StatiRptDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StatiRpt>, StatiRptDao
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
	public List<StatiRpt> getStatiRptAll() throws StatiRptDaoException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		return jdbcTemplate.query("SELECT * FROM stati_rpt ORDER BY id_stati_rpt ", params, this);
	}

	public StatiRpt mapRow(ResultSet rs, int index) throws SQLException {
		StatiRpt srpt = new StatiRpt();
		srpt.setIdStatiRpt(rs.getInt("id_stati_rpt"));
		srpt.setDescrizione(rs.getString("descrizione"));
		return srpt;
	}
	
}
