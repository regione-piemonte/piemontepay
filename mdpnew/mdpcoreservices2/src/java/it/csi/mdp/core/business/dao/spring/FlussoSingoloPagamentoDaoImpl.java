/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.FlussoSingoloPagamentoDao;
import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.core.business.dto.FlussoSingoloPagamento;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.util.StringUtil;
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

public   class FlussoSingoloPagamentoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FlussoRiversamento>, FlussoSingoloPagamentoDao
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
	
	public List<FlussoSingoloPagamento> getFlussoSingoloPagamentoByParam(
														Integer  id,
														Integer  idFlusso,
														String   iuv,
														String   identificativounivocoriscossione,
														Double   singoloimportopagato,
														String   codiceesitosingolopagamento,
														Date     dataesitosingolopagamento,
														Date     datainserimento,
														Date     datamodifica,
														String   applicationId,
														Date     dataregolamentoDa,
														Date     dataregolamentoA) throws DaoException {
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		sql.append(" SELECT ");
		sql.append(" a.identificativopsp ,");
		sql.append(" a.identificativoflusso ,");
		sql.append(" a.versioneoggetto ,");
		sql.append(" a.identificativounivocoregolamento ,");
		sql.append(" a.identificativoistitutomittente ,");
		sql.append(" a.identificativoistitutoricevente ,");
		sql.append(" a.numerototalepagamenti ,");
		sql.append(" a.importototalepagamenti ,");
		sql.append(" a.dataoraflusso ,");
		sql.append(" a.dataregolamento ,");
		//sql.append(" a.datainserimento ,");
		//sql.append(" a.datamodifica ,");
		sql.append(" a.xmlflusso ,");
		sql.append(" a.denominazionemittente ,");
		sql.append(" a.denominazionericevente ,");
		//dett
		sql.append(" b.id ,");
		sql.append(" b.id_flusso ,");
		sql.append(" b.iuv ,");
		sql.append(" b.identificativounivocoriscossione ,");
		sql.append(" b.singoloimportopagato ,");
		sql.append(" b.codiceesitosingolopagamento ,");
		sql.append(" b.dataesitosingolopagamento ,");
		sql.append(" b.datainserimento ,");
		sql.append(" b.datamodifica ,");
		sql.append(" b.application_id, ");
		sql.append(" c.applicationname ");
		sql.append(" FROM  flusso_riversamento a , ");
		sql.append(" flusso_singolo_pagamento b, ");
		sql.append(" application c ");
		sql.append(" WHERE a.id=b.id_flusso AND b.application_id = c.id");	
		 
		if( id!=null){
			params.addValue("id", id, java.sql.Types.INTEGER);
			sql.append(" AND b.id=:id");
		}

		if( idFlusso!=null){
			params.addValue("idFlusso", idFlusso, java.sql.Types.INTEGER);
			sql.append(" AND b.id_Flusso=:idFlusso");
		}

		if(StringUtil.isNotBlank(iuv)){
			params.addValue("iuv", iuv, java.sql.Types.VARCHAR);
			sql.append(" AND b.iuv=:iuv");
		}
		
		if(StringUtil.isNotBlank(identificativounivocoriscossione)){
			params.addValue("identificativounivocoriscossione", identificativounivocoriscossione, java.sql.Types.VARCHAR);
			sql.append(" AND b.identificativounivocoriscossione=:identificativounivocoriscossione");		
		}
		
		if(singoloimportopagato!=null){
			params.addValue("singoloimportopagato", singoloimportopagato, java.sql.Types.DOUBLE);
			sql.append(" AND b.singoloimportopagato=:singoloimportopagato");
		}
		
		if(StringUtil.isNotBlank(codiceesitosingolopagamento)){
			params.addValue("codiceesitosingolopagamento", codiceesitosingolopagamento, java.sql.Types.VARCHAR);
			sql.append(" AND b.codiceesitosingolopagamento=:codiceesitosingolopagamento");
		}
		
		if(dataesitosingolopagamento!=null){
			sql.append(" AND b.dataesitosingolopagamento >= to_timestamp('" +sdf.format(dataesitosingolopagamento)+"','YYYYMMDD')");
//			Date lastUpdateFine = UtilDate.addDay(lastUpdate,1);
//			sql.append(" AND a.last_update between to_timestamp('" +sdf.format(lastUpdate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(lastUpdateFine)+"','YYYYMMDD HH24:MI')" );
//			params.addValue("lastUpdate", lastUpdate, java.sql.Types.DATE);
		}
		
		if(datainserimento!=null){
			sql.append(" AND b.datainserimento >= to_timestamp('" +sdf.format(datainserimento)+"','YYYYMMDD')");
//			Date lastUpdateFine = UtilDate.addDay(lastUpdate,1);
//			sql.append(" AND a.last_update between to_timestamp('" +sdf.format(lastUpdate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(lastUpdateFine)+"','YYYYMMDD HH24:MI')" );
//			params.addValue("lastUpdate", lastUpdate, java.sql.Types.DATE);

		}
		
		if(datamodifica!=null){
			sql.append(" AND b.datamodifica >= to_timestamp('" +sdf.format(datamodifica)+"','YYYYMMDD')");
//			Date lastUpdateFine = UtilDate.addDay(lastUpdate,1);
//			sql.append(" AND a.last_update between to_timestamp('" +sdf.format(lastUpdate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(lastUpdateFine)+"','YYYYMMDD HH24:MI')" );
//			params.addValue("lastUpdate", lastUpdate, java.sql.Types.DATE);
		}
		
		if(StringUtil.isNotBlank(applicationId)){
			params.addValue("applicationId", applicationId, java.sql.Types.VARCHAR);
			sql.append(" AND b.application_id=:applicationId");
		}

		if(dataregolamentoDa!=null){
			sql.append(" AND a.dataregolamento >= to_timestamp('" +sdf.format(dataregolamentoDa)+"','YYYYMMDD')");
		}

		if(dataregolamentoA!=null){
			sql.append(" AND a.dataregolamento <= to_timestamp('" +sdf.format(dataregolamentoA)+"','YYYYMMDD')");
		}
		sql.append(" LIMIT 100 ");	
		
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	
	}
	
	public FlussoSingoloPagamento mapRow(ResultSet rs, int index) throws SQLException {
		FlussoSingoloPagamento fsp = new FlussoSingoloPagamento();
		//Testata
		fsp.setId(rs.getInt("id"));
		fsp.setIdentificativopsp(rs.getString("identificativopsp"));
		fsp.setIdentificativoflusso(rs.getString("identificativoflusso"));
		fsp.setVersioneoggetto(rs.getString("versioneoggetto"));
		fsp.setIdentificativounivocoregolamento(rs.getString("identificativounivocoregolamento"));
		fsp.setIdentificativoistitutomittente(rs.getString("identificativoistitutomittente"));
		fsp.setIdentificativoistitutoricevente(rs.getString("identificativoistitutoricevente"));
		fsp.setNumerototalepagamenti(rs.getInt("numerototalepagamenti"));
		fsp.setImportototalepagamenti(rs.getDouble("importototalepagamenti"));
		fsp.setDataoraflusso(rs.getTimestamp("dataoraflusso"));
		fsp.setDataregolamento(rs.getTimestamp("dataregolamento"));
		//fsp.setDatainserimento(rs.getTimestamp("datainserimento"));
		//fsp.setDatamodifica(rs.getTimestamp("datamodifica"));
		fsp.setXmlflusso(rs.getString("xmlflusso"));
		fsp.setDenominazionemittente(rs.getString("denominazionemittente"));
		fsp.setDenominazionericevente(rs.getString("denominazionericevente"));
		//dettaglio
		fsp.setIdFlusso(rs.getInt("id_flusso"));
		fsp.setIuv(rs.getString("iuv"));
		fsp.setIdentificativounivocoriscossione(rs.getString("identificativounivocoriscossione"));
		fsp.setSingoloimportopagato(rs.getDouble("singoloimportopagato"));
		fsp.setCodiceesitosingolopagamento(rs.getString("codiceesitosingolopagamento"));
		fsp.setDataesitosingolopagamento(rs.getTimestamp("dataesitosingolopagamento"));
		fsp.setDatainserimento(rs.getTimestamp("datainserimento"));
		fsp.setDatamodifica(rs.getTimestamp("datamodifica"));
		fsp.setApplicationId(rs.getString("application_Id"));
		fsp.setApplicationname(rs.getString("applicationname"));
		return fsp;
	}

}
