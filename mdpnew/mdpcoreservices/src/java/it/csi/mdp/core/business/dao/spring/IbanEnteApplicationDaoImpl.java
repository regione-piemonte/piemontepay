/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.IbanEnteApplicationDao;
import it.csi.mdp.core.business.dto.IbanEnteApplication;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.util.Constants;
import it.csi.mdp.core.util.LoggerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class IbanEnteApplicationDaoImpl extends AbstractDAO implements ParameterizedRowMapper<IbanEnteApplication>, IbanEnteApplicationDao
{
	protected static Logger log = Logger.getLogger(Constants.APPLICATION_CODE);
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

	public List<IbanEnteApplication> getIbanEnteApplicationByParam(Integer id,
			String applicationId,
			String idEnte,
			String tipoversamento,
			String identificativopsp,
			String iban,
			Date dataInizioValidita,
			Date dataFineValidita,
			String attivo) throws DaoException {
		LoggerUtil.begin();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		sql.append(" SELECT ");
		sql.append(" id,");
		sql.append(" application_id,");
		sql.append(" id_ente,");
		sql.append(" tipoversamento,");
		sql.append(" identificativopsp,");
		sql.append(" iban,");
		sql.append(" data_inizio_validita,");
		sql.append(" data_fine_validita,");
		sql.append(" attivo");
		sql.append(" FROM iban_ente_application ");
		sql.append(" WHERE 1=1 ");		
		if(id!=null){
			params.addValue("id", id, java.sql.Types.INTEGER);

			sql.append(" AND id =:id ");		
		}
		
		if(StringUtil.isNotBlank(identificativopsp)){ 
			params.addValue("identificativopsp", identificativopsp, java.sql.Types.VARCHAR);
			sql.append(" AND identificativopsp=:identificativopsp");
		}

		
		if(StringUtil.isNotBlank(applicationId)){ 
			params.addValue("applicationId", applicationId, java.sql.Types.VARCHAR);
			sql.append(" AND application_id=:applicationId");
		}
		
		if(StringUtil.isNotBlank(idEnte)){ 
			params.addValue("idEnte", idEnte, java.sql.Types.VARCHAR);
			sql.append(" AND id_ente=:idEnte");
		}

		sql.append(" AND tipoversamento in( :all");
		params.addValue("all", "ALL", java.sql.Types.VARCHAR);
		if(StringUtil.isNotBlank(tipoversamento)){ 
			params.addValue("tipoversamento", tipoversamento, java.sql.Types.VARCHAR);
			sql.append(",:tipoversamento");
		}
		sql.append(" )");
		
		
		if(StringUtil.isNotBlank(iban)){ 
			params.addValue("iban", iban, java.sql.Types.VARCHAR);
			sql.append(" AND iban=:iban");
		}

		if(dataInizioValidita!=null){ 
			//params.addValue("dataInizioValidita", dataInizioValidita, java.sql.Types.VARCHAR);
			sql.append(" AND date_trunc('DAY',data_inizio_validita) = to_timestamp('" +sdf.format(dataInizioValidita)+"','YYYYMMDD')" );
		}

		if(dataFineValidita!=null){ 
			//params.addValue("dataFineValidita", dataFineValidita, java.sql.Types.VARCHAR);
			sql.append(" AND date_trunc('DAY',data_fine_validita) = to_timestamp('" +sdf.format(dataFineValidita)+"','YYYYMMDD')" );
		}

		if(StringUtil.isNotBlank(attivo)){ 
			params.addValue("attivo", attivo, java.sql.Types.VARCHAR);
			sql.append(" AND attivo=:attivo");
		}
		sql.append(" ORDER BY data_inizio_validita DESC ");
		
		
		log.info("[IbanEnteApplicationDaoImpl::getIbanEnteApplicationByParam]  applicationId "+applicationId);
		log.info("[IbanEnteApplicationDaoImpl::getIbanEnteApplicationByParam]  idEnte "+idEnte);
		log.info("[IbanEnteApplicationDaoImpl::getIbanEnteApplicationByParam]  tipoversamento "+tipoversamento);
		log.info("[IbanEnteApplicationDaoImpl::getIbanEnteApplicationByParam]  identificativopsp "+identificativopsp);
		
		
		
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);

	}

	public void insertIbanEnteApplication(
										  String applicationId,
										  String idEnte,
										  String tipoversamento,
										  String identificativopsp,
										  String iban,
										  Date dataInizioValidita,
										  Date dataFineValidita,
										  String attivo) throws DaoException {
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String sql ="INSERT INTO iban_ente_application(";
				sql +=" application_id,";
				sql +=" id_ente,";
				sql +=" tipoversamento,";
				sql +=" identificativopsp,";
				sql +=" iban,";
				sql +=" data_inizio_validita,";
				sql +=" data_fine_validita,";
				sql +=" attivo";
				sql +=" )VALUES (";
				sql +=" :applicationId,";
				sql +=" :idEnte,";
				sql +=" :tipoversamento, ";
				sql +=" :identificativopsp,";
				sql +=" :iban,";
				sql +=" :dataInizioValidita,";
				sql +=" :dataFineValidita,";
				sql +=" :attivo";
				sql +=" )";
		  
				params.addValue("applicationId",applicationId,java.sql.Types.VARCHAR);
				params.addValue("idEnte",idEnte,java.sql.Types.VARCHAR);
				params.addValue("tipoversamento",tipoversamento,java.sql.Types.VARCHAR);
				params.addValue("identificativopsp",identificativopsp,java.sql.Types.VARCHAR);
				params.addValue("iban",iban,java.sql.Types.VARCHAR);
				params.addValue("dataInizioValidita",dataInizioValidita,java.sql.Types.TIMESTAMP);
				params.addValue("dataFineValidita",dataFineValidita,java.sql.Types.TIMESTAMP);
				params.addValue("attivo",attivo,java.sql.Types.VARCHAR);


			LoggerUtil.end();
			jdbcTemplate.update(sql,params);
		
	}

	public void updateIbanEnteApplication(										  
										  Integer id,
										  String applicationId,
										  String idEnte,
										  String tipoversamento,
										  String identificativopsp,
										  String iban,
										  Date dataInizioValidita,
										  Date dataFineValidita,
										  String attivo) throws DaoException {
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String sql ="UPDATE iban_ente_application SET";
		
		sql +=" application_id=:applicationId,";
		
		sql +=" id_ente=:idEnte,";
		
		sql +=" tipoversamento=:tipoversamento,";
		
		sql +=" identificativopsp=:identificativopsp,";
		
		sql +=" iban=:iban,";
		
		sql +=" data_inizio_validita=:dataInizioValidita,";
		
		sql +=" data_fine_validita=:dataFineValidita,";
		
		sql +=" attivo=:attivo";
		
		sql +=" WHERE id=:id";
  
		params.addValue("id",id,java.sql.Types.INTEGER);
		params.addValue("applicationId",applicationId,java.sql.Types.VARCHAR);
		params.addValue("idEnte",idEnte,java.sql.Types.VARCHAR);
		params.addValue("tipoversamento",tipoversamento,java.sql.Types.VARCHAR);
		params.addValue("identificativopsp",identificativopsp,java.sql.Types.VARCHAR);
		params.addValue("iban",iban,java.sql.Types.VARCHAR);
		params.addValue("dataInizioValidita",dataInizioValidita,java.sql.Types.TIMESTAMP);
		params.addValue("dataFineValidita",dataFineValidita,java.sql.Types.TIMESTAMP);
		params.addValue("attivo",attivo,java.sql.Types.VARCHAR);

		jdbcTemplate.update(sql,params);
		
	}

	public IbanEnteApplication mapRow(ResultSet rs, int arg1) throws SQLException {
		IbanEnteApplication iea = new IbanEnteApplication();
		iea.setId(rs.getInt("id"));
		iea.setApplicationId(rs.getString("application_id"));
		iea.setIdEnte(rs.getString("id_ente"));
		iea.setTipoversamento(rs.getString("tipoversamento"));
		iea.setIdentificativopsp(rs.getString("identificativopsp"));
		iea.setIban(rs.getString("iban"));
		iea.setDataInizioValidita(rs.getTimestamp("data_inizio_validita"));
		iea.setDataFineValidita(rs.getTimestamp("data_fine_validita"));
		iea.setAttivo(rs.getString("attivo"));
		return iea;
	}
}
