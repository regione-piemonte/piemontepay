/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.NodoErroriDao;
import it.csi.mdp.core.business.dto.NodoErrore;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.util.LoggerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class NodoErroriDaoImpl extends AbstractDAO implements ParameterizedRowMapper<NodoErrore>, NodoErroriDao
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
	public Integer insert(NodoErrore dto) throws DaoException {
		
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String sql ="INSERT INTO nodo_errori (descrizione, transaction_id, application_id, data) VALUES (:descrizione, :transaction_id, :application_id, :data)";
		
		params.addValue("descrizione",dto.getDescrizione(),java.sql.Types.VARCHAR);
		params.addValue("transaction_id",dto.getTransactionId(),java.sql.Types.VARCHAR);
		params.addValue("application_id",dto.getApplicationId(),java.sql.Types.VARCHAR);
		params.addValue("data",dto.getData(),java.sql.Types.TIMESTAMP);
			
		LoggerUtil.end();
		return jdbcTemplate.update(sql,params);
	}

	@Transactional
	public List<NodoErrore> find(NodoErrore dto) throws DaoException{

		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuffer sql = new StringBuffer("SELECT * FROM nodo_errori WHERE 1 = 1 ");
		
		if(StringUtils.isNotEmpty(dto.getApplicationId())){
			sql.append(" AND application_id = :application_id");
			params.addValue("application_id", dto.getApplicationId(), java.sql.Types.VARCHAR);
		}
		
		if(StringUtils.isNotEmpty(dto.getTransactionId())){
			sql.append(" AND transaction_id = :transaction_id");
			params.addValue("transaction_id", dto.getTransactionId(), java.sql.Types.VARCHAR);
		}
		
		sql.append(" ORDER BY id ");
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);

	}

	public NodoErrore mapRow(ResultSet rs, int arg1) throws SQLException {
		NodoErrore dto = new NodoErrore();
		
		dto.setId(rs.getInt("id"));
		dto.setApplicationId(rs.getString("application_id"));
		dto.setTransactionId(rs.getString("transaction_id"));
		dto.setDescrizione(rs.getString("descrizione"));
		dto.setData(rs.getTimestamp("data"));
		
		return dto;
	}
	
}





