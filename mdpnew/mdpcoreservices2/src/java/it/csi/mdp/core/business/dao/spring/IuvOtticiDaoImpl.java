/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.IuvOtticiDao;
import it.csi.mdp.core.business.dto.IuvOttici;
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

public class IuvOtticiDaoImpl extends AbstractDAO implements ParameterizedRowMapper<IuvOttici>, IuvOtticiDao
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
	public IuvOttici find(IuvOttici filtro) throws DaoException{

		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuffer sql = new StringBuffer("SELECT * FROM iuv_ottici WHERE 1 = 1 ");
		
		if(StringUtils.isNotEmpty(filtro.getIuvOttico())){
			sql.append(" AND iuv_ottico = :iuv_ottico ");
			params.addValue("iuv_ottico", filtro.getIuvOttico(), java.sql.Types.VARCHAR);
		}
		
		if(StringUtils.isNotEmpty(filtro.getIuvStandard())){
			sql.append(" AND iuv_standard = :iuv_standard");
			params.addValue("iuv_standard", filtro.getIuvStandard(), java.sql.Types.VARCHAR);
		}
		
		sql.append(" ORDER BY id ");
		LoggerUtil.info("sql --> " + sql.toString());
		List<IuvOttici> elencoTrovati = jdbcTemplate.query(sql.toString(), params, this);
		LoggerUtil.end();	
		if (elencoTrovati == null || elencoTrovati.size() == 0)  
			return null;
		else
			return elencoTrovati.get(0);
	}

	public IuvOttici mapRow(ResultSet rs, int arg1) throws SQLException {
		IuvOttici dto = new IuvOttici();
		
		dto.setId(rs.getInt("id"));
		dto.setCodiceVersamento(rs.getString("cod_versamento"));
		dto.setDataCreazione(rs.getDate("data_creazione"));
		dto.setDataRiconciliazione(rs.getDate("data_riconciliazione"));
		dto.setApplicationId(rs.getString("application_id"));
		dto.setIuvOttico(rs.getString("iuv_ottico"));
		dto.setIuvStandard(rs.getString("iuv_standard"));
		dto.setEnteId(rs.getString("ente_id"));
		
		return dto;
	}
	
}





