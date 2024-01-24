/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.FlussoRiversamentoDao;
import it.csi.mdp.core.business.dto.FlussoRiversamento;
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

public class FlussoRiversamentoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<FlussoRiversamento>, FlussoRiversamentoDao
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
	
	
	public List<FlussoRiversamento> getFlussoRiversamentoByParam(
																Integer   id,
																String    identificativopsp,
																String    identificativoflusso,
																String    versioneoggetto,
																String    identificativounivocoregolamento,
																String    identificativoistitutomittente,
																String    identificativoistitutoricevente,
																Integer   numerototalepagamenti,
																Double    importototalepagamenti,
																Date      dataoraflusso,
																Date      dataregolamentoDa,
																Date      dataregolamentoA,
																Date      datainserimento,
																Date      datamodifica,
																String    xmlflusso,
																String    denominazionemittente,
																String    denominazionericevente
																) throws DaoException {
									
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");

		
		sql.append(" SELECT ");
		sql.append(" a.id ,");
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
		sql.append(" a.datainserimento ,");
		sql.append(" a.datamodifica ,");
		sql.append(" a.xmlflusso ,");
		sql.append(" a.denominazionemittente ,");
		sql.append(" a.denominazionericevente ");
		sql.append(" FROM  flusso_riversamento a ");
		sql.append(" WHERE 1=1 ");	
		
		if(id!=null){ 
			params.addValue("id", id, java.sql.Types.INTEGER);
			sql.append(" AND a.id=:id");
		}
		
		if(StringUtil.isNotBlank(identificativopsp)){
			params.addValue("identificativopsp", identificativopsp, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativopsp=:identificativopsp");
			
		}
		
		if(StringUtil.isNotBlank(identificativoflusso)){
			params.addValue("identificativoflusso", identificativoflusso, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativoflusso=:identificativoflusso");			
		}
		
		if(StringUtil.isNotBlank(versioneoggetto)){
			params.addValue("versioneoggetto", versioneoggetto, java.sql.Types.VARCHAR);
			sql.append(" AND a.versioneoggetto=:versioneoggetto");
		}
		
		if(StringUtil.isNotBlank(identificativounivocoregolamento)){
			params.addValue("identificativounivocoregolamento", identificativounivocoregolamento, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativounivocoregolamento=:identificativounivocoregolamento");			
		}
		
		if(StringUtil.isNotBlank(identificativoistitutomittente)){
			params.addValue("identificativoistitutomittente", identificativoistitutomittente, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativoistitutomittente=:identificativoistitutomittente");			
		}

		if(StringUtil.isNotBlank(identificativoistitutoricevente)){
			params.addValue("identificativoistitutoricevente", identificativoistitutoricevente, java.sql.Types.VARCHAR);
			sql.append(" AND a.identificativoistitutoricevente=:identificativoistitutoricevente");						
		}
		
		if(numerototalepagamenti!=null){
			params.addValue("numerototalepagamenti", numerototalepagamenti, java.sql.Types.INTEGER);
			sql.append(" AND a.numerototalepagamenti=:numerototalepagamenti");
		}
		if(importototalepagamenti!=null){
			params.addValue("importototalepagamenti", importototalepagamenti, java.sql.Types.DOUBLE);
			sql.append(" AND a.importototalepagamenti=:importototalepagamenti");	
		}
		
		if( dataoraflusso!=null){
			sql.append(" AND a.dataoraflusso >= to_timestamp('" +sdf.format(dataoraflusso)+"','YYYYMMDD HH24:MI')");
//			Date lastUpdateFine = UtilDate.addDay(lastUpdate,1);
//			sql.append(" AND a.last_update between to_timestamp('" +sdf.format(lastUpdate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(lastUpdateFine)+"','YYYYMMDD HH24:MI')" );
//			params.addValue("lastUpdate", lastUpdate, java.sql.Types.DATE);
		}
		
		if( dataregolamentoDa!=null){
			sql.append(" AND a.dataregolamento >= to_timestamp('" +sdf.format(dataregolamentoDa)+"','YYYYMMDD HH24:MI')");
		}
		if( dataregolamentoA!=null){
			sql.append(" AND a.dataregolamento < to_timestamp('" +sdf.format(UtilDate.addDay(dataregolamentoA,1))+"','YYYYMMDD HH24:MI')");
		}
		
		if( datainserimento!=null){
			sql.append(" AND a.datainserimento >= to_timestamp('" +sdf.format(datainserimento)+"','YYYYMMDD HH24:MI')");
		}
		
		if( datamodifica!=null){
			sql.append(" AND a.datamodifica >= to_timestamp('" +sdf.format(datamodifica)+"','YYYYMMDD HH24:MI')");
		}
		
		if(StringUtil.isNotBlank(xmlflusso)){
			params.addValue("xmlflusso", xmlflusso, java.sql.Types.VARCHAR);
			sql.append(" AND a.xmlflusso=:xmlflusso");
			
		}
		
		if(StringUtil.isNotBlank(denominazionemittente)){
			params.addValue("denominazionemittente", denominazionemittente, java.sql.Types.VARCHAR);
			sql.append(" AND a.denominazionemittente=:denominazionemittente");
			
		}
		
		if(StringUtil.isNotBlank(denominazionericevente)){
			params.addValue("denominazionericevente", denominazionericevente, java.sql.Types.VARCHAR);
			sql.append(" AND a.denominazionericevente=:denominazionericevente");			
		}
		sql.append(" LIMIT 100 ");			
		

//		if(StringUtil.isNotBlank(descrizione)){ 
//			sql.append(" AND descrizione LIKE '%"+descrizione+"%'");
//		}

		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	
}

	public FlussoRiversamento mapRow(ResultSet rs, int index) throws SQLException {
		FlussoRiversamento fr = new FlussoRiversamento();
		

		fr.setId(rs.getInt("id"));
		fr.setIdentificativopsp(rs.getString("identificativopsp"));
		fr.setIdentificativoflusso(rs.getString("identificativoflusso"));
		fr.setVersioneoggetto(rs.getString("versioneoggetto"));
		fr.setIdentificativounivocoregolamento(rs.getString("identificativounivocoregolamento"));
		fr.setIdentificativoistitutomittente(rs.getString("identificativoistitutomittente"));
		fr.setIdentificativoistitutoricevente(rs.getString("identificativoistitutoricevente"));
		fr.setNumerototalepagamenti(rs.getInt("numerototalepagamenti"));
		fr.setImportototalepagamenti(rs.getDouble("importototalepagamenti"));
		fr.setDataoraflusso(rs.getTimestamp("dataoraflusso"));
		fr.setDataregolamento(rs.getTimestamp("dataregolamento"));
		fr.setDatainserimento(rs.getTimestamp("datainserimento"));
		fr.setDatamodifica(rs.getTimestamp("datamodifica"));
		fr.setXmlflusso(rs.getString("xmlflusso"));
		fr.setDenominazionemittente(rs.getString("denominazionemittente"));
		fr.setDenominazionericevente(rs.getString("denominazionericevente"));
		
		return fr;
	}
	
	
}
