/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.GiornaleEventoDao;
import it.csi.mdp.core.business.dto.GiornaleEvento;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.exceptions.GatewaydetailDaoException;
import it.csi.mdp.core.business.exceptions.GiornaleEventoDaoException;
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
import org.springframework.transaction.annotation.Transactional;

public class GiornaleEventoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<GiornaleEvento>, GiornaleEventoDao
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
	public void insert(GiornaleEvento dto) throws GiornaleEventoDaoException {
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String sql ="INSERT INTO gde(application_id, insert_date, last_update, dataoraevento, identificativodominio, iuv, codice_contesto, id_psp, tipoversamento, componente, categoriaevento, tipoevento, sottotipoevento, identificativofruitore, identificativoerogatore, identificativostazioneintermediariopa, id_int_psp, canalepagamento, parametrispecificiinterfaccia, esito, transactionId) " +
				"VALUES (:application_id, now(), now(), :dataoraevento, :identificativodominio, :iuv, " +
				":codice_contesto, :id_psp, :tipoversamento, :componente, :categoriaevento, :tipoevento, :sottotipoevento, :identificativofruitore," +
				" :identificativoerogatore, :identificativostazioneintermediariopa, :id_int_psp, :canalepagamento, :parametrispecificiinterfaccia, :esito, :transactionId)";
				
			params.addValue("application_id",dto.getApplicationId(),java.sql.Types.VARCHAR);
			params.addValue("dataoraevento",dto.getDataoraevento(),java.sql.Types.TIMESTAMP);
			params.addValue("identificativodominio",dto.getIdentificativodominio(),java.sql.Types.VARCHAR);
			params.addValue("iuv",dto.getIuv(), java.sql.Types.VARCHAR);
			params.addValue("codice_contesto",dto.getCodiceContesto(),java.sql.Types.VARCHAR);
			params.addValue("id_psp",dto.getIdPsp(),java.sql.Types.VARCHAR);
			params.addValue("tipoversamento",dto.getTipoversamento(),java.sql.Types.VARCHAR);
			params.addValue("componente",dto.getComponente(), java.sql.Types.VARCHAR);
			params.addValue("categoriaevento",dto.getCategoriaevento(),java.sql.Types.VARCHAR);
			params.addValue("tipoevento",dto.getTipoevento(),java.sql.Types.VARCHAR);
			params.addValue("sottotipoevento",dto.getSottotipoevento(),java.sql.Types.VARCHAR);
			params.addValue("identificativofruitore",dto.getApplicationId(), java.sql.Types.VARCHAR);
			params.addValue("identificativoerogatore",dto.getIdentificativoerogatore(),java.sql.Types.VARCHAR);
			params.addValue("identificativostazioneintermediariopa",dto.getIdentificativostazioneintermediariopa(),java.sql.Types.VARCHAR);
			params.addValue("id_int_psp",dto.getIdIntPsp(), java.sql.Types.VARCHAR);
			params.addValue("canalepagamento",dto.getCanalepagamento(),java.sql.Types.VARCHAR);
			params.addValue("parametrispecificiinterfaccia",dto.getParametrispecificiinterfaccia(),java.sql.Types.VARCHAR);
			params.addValue("esito",dto.getEsito(),java.sql.Types.VARCHAR);
			params.addValue("transactionId",dto.getTransactionId(),java.sql.Types.VARCHAR);
			LoggerUtil.end();
			jdbcTemplate.update(sql,params);
	}

	@Transactional
	public List<GiornaleEvento> findAll() throws GiornaleEventoDaoException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		return jdbcTemplate.query("SELECT * FROM GDE", params, this);
	}

	@Transactional
	public List<GiornaleEvento> getGiornaleEventoByParam(String iuv,Date dataOraEvento, 
														String identificativodominio,String identificativofruitore,
														String codiceContesto) throws GiornaleEventoDaoException{

		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		sql.append(" SELECT ");
		sql.append(" id ,");
		sql.append(" insert_date ,");
		sql.append(" last_update ,");
		sql.append(" dataoraevento ,");
		sql.append(" identificativodominio ,");
		sql.append(" iuv ,");
		sql.append(" codice_contesto ,");
		sql.append(" id_psp,");
		sql.append(" tipoversamento,");
		sql.append(" componente,");
		sql.append(" categoriaevento,");
		sql.append(" tipoevento,");
		sql.append(" sottotipoevento,");
		sql.append(" identificativofruitore,");
		sql.append(" identificativoerogatore,");
		sql.append(" identificativostazioneintermediariopa,");
		sql.append(" id_int_psp,");
		sql.append(" canalepagamento,");
		sql.append(" parametrispecificiinterfaccia,");
		sql.append(" esito,");
		sql.append(" application_id");
		sql.append(" FROM GDE ");
		sql.append(" WHERE 1 = 1 ");
		
		if(iuv!=null && !iuv.trim().equals("")){
			sql.append(" AND iuv = :iuv");
			params.addValue("iuv", iuv, java.sql.Types.VARCHAR);
		}
		
		if(dataOraEvento!=null){
			sql.append(" AND date_trunc('DAY',dataoraevento) = to_timestamp('" +sdf.format(dataOraEvento)+"','YYYYMMDD')" );
		}
		
		if(identificativodominio!=null && !identificativodominio.trim().equals("")){
			sql.append(" AND identificativodominio=:identificativodominio");
			params.addValue("identificativodominio", identificativodominio, java.sql.Types.VARCHAR);
		}
		
		if(identificativofruitore!=null && !identificativofruitore.trim().equals("")){
			sql.append(" AND identificativofruitore=:identificativofruitore");
			params.addValue("identificativofruitore", identificativofruitore, java.sql.Types.VARCHAR);
		}
		
		if(codiceContesto!=null && !codiceContesto.trim().equals("")){
			sql.append(" AND codice_contesto=:codice_contesto");
			params.addValue("codice_contesto", codiceContesto, java.sql.Types.VARCHAR);
		}
		sql.append(" ORDER BY dataoraevento DESC");
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);

	}
	
	@Transactional
	public GiornaleEvento getGiornaleEventoById(Integer id) throws GiornaleEventoDaoException {
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		sql.append(" SELECT ");
		sql.append(" id ,");
		sql.append(" insert_date ,");
		sql.append(" last_update ,");
		sql.append(" dataoraevento ,");
		sql.append(" identificativodominio ,");
		sql.append(" iuv ,");
		sql.append(" codice_contesto ,");
		sql.append(" id_psp,");
		sql.append(" tipoversamento,");
		sql.append(" componente,");
		sql.append(" categoriaevento,");
		sql.append(" tipoevento,");
		sql.append(" sottotipoevento,");
		sql.append(" identificativofruitore,");
		sql.append(" identificativoerogatore,");
		sql.append(" identificativostazioneintermediariopa,");
		sql.append(" id_int_psp,");
		sql.append(" canalepagamento,");
		sql.append(" parametrispecificiinterfaccia,");
		sql.append(" esito,");
		sql.append(" application_id");
		sql.append(" FROM GDE ");
		sql.append(" WHERE id =:id ");		
		params.addValue("id", id, java.sql.Types.INTEGER);
		LoggerUtil.end();
		return (GiornaleEvento) jdbcTemplate.queryForObject(sql.toString(), params, this);
	}

	@Transactional
	public GiornaleEvento mapRow(ResultSet rs, int index) throws SQLException {
		GiornaleEvento gde = new GiornaleEvento();
		gde.setId(rs.getInt("id"));
		gde.setInsertDate(rs.getTimestamp("insert_date"));
		gde.setLastUpdate(rs.getTimestamp("last_update"));
		gde.setDataoraevento(rs.getTimestamp("dataoraevento"));
		gde.setIdentificativodominio(rs.getString("identificativodominio"));
		gde.setIuv(rs.getString("iuv"));
		gde.setCodiceContesto(rs.getString("codice_contesto"));
		gde.setIdPsp(rs.getString("id_psp"));
		gde.setTipoversamento(rs.getString("tipoversamento"));
		gde.setComponente(rs.getString("componente"));
		gde.setCategoriaevento(rs.getString("categoriaevento"));
		gde.setTipoevento(rs.getString("tipoevento"));
		gde.setSottotipoevento(rs.getString("sottotipoevento"));
		gde.setIdentificativoerogatore(rs.getString("identificativoerogatore"));
		gde.setIdentificativofruitore(rs.getString("identificativofruitore"));
		gde.setIdentificativostazioneintermediariopa(rs.getString("identificativostazioneintermediariopa"));
		gde.setIdIntPsp(rs.getString("id_int_psp"));
		gde.setCanalepagamento(rs.getString("canalepagamento"));
		gde.setParametrispecificiinterfaccia(rs.getString("parametrispecificiinterfaccia"));
		gde.setEsito(rs.getString("esito"));
		gde.setApplicationId(rs.getString("application_id"));
		return gde;
	}	
}
