/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.StatisticaApplicazioneTransazioneDao;
import it.csi.mdp.core.business.dto.StatisticaApplicazioneTransazione;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.business.util.UtilDate;
import it.csi.mdp.core.util.LoggerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class StatisticaApplicazioneTransazioneDaoImpl extends AbstractDAO implements ParameterizedRowMapper<StatisticaApplicazioneTransazione>, StatisticaApplicazioneTransazioneDao
{
	protected NamedParameterJdbcTemplate jdbcTemplate;
	
	protected DataSource dataSource;

	/**
	 * Method 'setDataSource'
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

//	public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		System.out.println(sdf.format(new Date()));
//		
//		System.out.println(new Date().toString());
//	}

	@SuppressWarnings("unchecked")
	public List<StatisticaApplicazioneTransazione> getStatisticaApplicazioneTransazione(String applicationId, Date dateDa,Date dateA) throws DaoException {
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		LoggerUtil.info("dateDa --> " + dateDa.toString());
		LoggerUtil.info("dateA --> " + dateA.toString());


		String dataInizio = sdf.format(dateDa);
		String dataFine   = sdf.format(UtilDate.addDay(dateA,1));


		sql.append(" select  ");
		sql.append(" application_id, ");
		sql.append(" cod_stato, ");
		sql.append(" count(cod_stato) numXstato ");
		sql.append(" from  ");
		sql.append(" transazione  ");
		sql.append(" WHERE ");		

		sql.append(" start_date between to_timestamp('" +dataInizio+"','YYYYMMDD') AND to_timestamp('" +dataFine+"','YYYYMMDD')" );
		
		if(StringUtil.isNotBlank(applicationId)){ 
			params.addValue("applicationId", applicationId, java.sql.Types.VARCHAR);
			sql.append(" AND application_id=:applicationId");
		}

		sql.append(" group by application_id,cod_stato ");
		sql.append(" order by application_id,cod_stato ");
		
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	}

	
	public StatisticaApplicazioneTransazione mapRow(ResultSet rs, int index) throws SQLException {
		StatisticaApplicazioneTransazione sat = new StatisticaApplicazioneTransazione();
		sat.setApplicationId(rs.getString("application_id"));
		sat.setCodStato(rs.getInt("cod_stato"));
		sat.setNumXstato(rs.getInt("numXstato"));
		return sat;
	}

}
