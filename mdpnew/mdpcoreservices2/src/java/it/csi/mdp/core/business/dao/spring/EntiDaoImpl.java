/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.EntiDao;
import it.csi.mdp.core.business.dto.Enti;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.util.LoggerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class EntiDaoImpl extends AbstractDAO implements ParameterizedRowMapper<Enti>, EntiDao
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

	public void insertEnte(String enteId ,
			String partitaIva, 
			String descrizione,String attivo) throws DaoException {
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "INSERT INTO enti("+
			"  ente_id,"+
			"  partita_iva,"+
			"  descrizione,"+
			"  attivo)" +
			" VALUES " +
			" (:enteId,"+ 
			"  :partitaIva,"+
			"  :descrizione," +
			"  :attivo)";
		
		
			params.addValue("enteId",enteId,java.sql.Types.VARCHAR);
			params.addValue("partitaIva",partitaIva,java.sql.Types.VARCHAR);
			params.addValue("descrizione",descrizione,java.sql.Types.VARCHAR);
			params.addValue("attivo",attivo,java.sql.Types.VARCHAR);

			LoggerUtil.end();
			jdbcTemplate.update(mysql,params);
		
	}


	@SuppressWarnings("unchecked")
	public List<Enti> getEntiByParam(String enteId , String partitaIva, String descrizione,String attivo) throws DaoException {
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");

		
		sql.append(" SELECT ");
		sql.append(" ente_id ,");
		sql.append(" partita_iva,");
		sql.append(" descrizione, ");
		sql.append(" attivo ");
		sql.append(" FROM  enti ");
		sql.append(" WHERE  1=1 ");	

		
		if(StringUtil.isNotBlank(attivo)){ 
			params.addValue("attivo", attivo, java.sql.Types.VARCHAR);
			sql.append(" AND attivo=:attivo");
		}
		
		if(StringUtil.isNotBlank(enteId)){ 
			params.addValue("enteId", enteId, java.sql.Types.VARCHAR);
			sql.append(" AND ente_id=:enteId");
		}
		if(StringUtil.isNotBlank(partitaIva)){ 
			params.addValue("partitaIva", partitaIva, java.sql.Types.VARCHAR);
			sql.append(" AND partita_iva=:partitaIva");
		}
		if(StringUtil.isNotBlank(descrizione)){ 
			sql.append(" AND UPPER(descrizione) LIKE '%"+descrizione.toUpperCase()+"%'");
		}

		sql.append(" ORDER BY descrizione  ");

		LoggerUtil.info("String enteId      " + enteId );
		LoggerUtil.info("String partitaIva  " + partitaIva );
		LoggerUtil.info("String descrizione " + descrizione);
		LoggerUtil.info("String attivo      " + attivo);
		
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	}

	public List<Enti> findEntiAll() throws DaoException {
		return getEntiByParam(null,null,null,null);
	}
	

	
	public void updateEnte(String enteId , String partitaIva, String descrizione,String attivo) throws DaoException {
		LoggerUtil.begin();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String sqlUpdate = "UPDATE enti SET ";
		sqlUpdate = sqlUpdate + " attivo=:attivo ";
		params.addValue("attivo",attivo,java.sql.Types.VARCHAR);
		
		if(partitaIva!=null && !partitaIva.equals("NOUPDATE")){
			sqlUpdate= sqlUpdate + " ,partita_iva=:partitaIva ";
			params.addValue("partitaIva",partitaIva,java.sql.Types.VARCHAR);
		}
		
		if(descrizione!=null && !descrizione.equals("NOUPDATE")){
			sqlUpdate= sqlUpdate + " ,descrizione=:descrizione " ;
			params.addValue("descrizione",descrizione,java.sql.Types.VARCHAR);
		}
		
		sqlUpdate= sqlUpdate + "WHERE ente_id=:enteId ";
		params.addValue("enteId",enteId,java.sql.Types.VARCHAR);


		LoggerUtil.end();
		jdbcTemplate.update(sqlUpdate,params);
	}

	public void deleteEnte(String enteId) throws DaoException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sqlUpdate = "DELETE FROM enti WHERE ente_id=:enteId ";
		params.addValue("enteId",enteId,java.sql.Types.VARCHAR);
		jdbcTemplate.update(sqlUpdate,params);
	}

	public Enti mapRow(ResultSet rs, int index) throws SQLException {
		Enti rpt = new Enti();
		rpt.setEnteId(rs.getString("ente_id"));
		rpt.setPartitaIva(rs.getString("partita_iva"));
		rpt.setDescrizione(rs.getString("descrizione"));
		rpt.setAttivo(rs.getString("attivo"));
		return rpt;
	}

	public List<Enti> getEntiByApplicationId(String idApplicazione) throws DaoException {
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();

		
		sql.append(" SELECT ");
		sql.append(" e.ente_id ,");
		sql.append(" e.partita_iva,");
		sql.append(" e.descrizione, ");
		sql.append(" e.attivo ");
		sql.append(" FROM  enti e, r_application_enti r ");
		sql.append(" WHERE  e.ente_id = r.ente_id ");	

		if(StringUtil.isNotBlank(idApplicazione)){ 
			params.addValue("idApplicazione", idApplicazione, java.sql.Types.VARCHAR);
			sql.append(" AND application_id=:idApplicazione");
		}

//		if(StringUtil.isNotBlank(attivo)){ 
//			params.addValue("attivo", attivo, java.sql.Types.VARCHAR);
//			sql.append(" AND attivo=:attivo");
//		}
		

		LoggerUtil.info("String idApplicazione      " + idApplicazione);
		
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	}

	public Integer insRelEnteApplication(String applicationId, String enteId) throws DaoException {
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "INSERT INTO r_application_enti("+
			"  ente_id,"+
			"  application_id)" +
			" VALUES " +
			" (:enteId,"+ 
			"  :applicationId)";

		params.addValue("enteId",enteId,java.sql.Types.VARCHAR);
		params.addValue("applicationId",applicationId,java.sql.Types.VARCHAR);

		LoggerUtil.end();
		return jdbcTemplate.update(mysql,params);
		
	}

	public Integer delRelEnteApplication(String idApplicazione, String enteId) throws DaoException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		String sqlUpdate = "DELETE FROM r_application_enti WHERE 1=1 ";
		
		//if(StringUtil.isNotBlank(idApplicazione)){ 
			sqlUpdate += " AND application_id=:idApplicazione";
			params.addValue("idApplicazione", idApplicazione, java.sql.Types.VARCHAR);
		//}
		
		if(StringUtil.isNotBlank(enteId)){ 
			sqlUpdate +=" AND ente_id=:enteId";
			params.addValue("enteId", enteId, java.sql.Types.VARCHAR);
		}
		
		return jdbcTemplate.update(sqlUpdate,params);

	}
}
