/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.RTDao;
import it.csi.mdp.core.business.dto.RT;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.util.LoggerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class RTDaoImpl extends AbstractDAO implements ParameterizedRowMapper<RT>, RTDao
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

	public void insert(RT dto) throws DaoException {
		
		LoggerUtil.begin();
		LoggerUtil.dump(dto);
		
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "INSERT INTO rt("+
			"  application_id,"+
			"  transaction_id,"+
			"  insert_date,"+
			"  last_update,"+
			"  data_msg_ricevuta,"+
			"  id_msg_ricevuta,"+
			"  id_msg_richiesta,"+
			"  tipo_firma,"+
			"  iuv,"+
			"  id_esito_pagamento," +
			"  rt_data," +
			"  stato_invio_fruitore," +
			"  data_invio_fruitore," +
			"  sorgente_invio_fruitore,commissioniapplicatepsp )" +
			" VALUES " +
			" (:application_id,"+ 
			"  :transaction_id,"+
			"  :insert_date, "+
			"  now(),"+
			"  :data_msg_ricevuta, "+
			"  :id_msg_ricevuta, "+
			"  :id_msg_richiesta, "+
			"  :tipo_firma,"+
			"  :iuv ,"+
			"  :id_esito_pagamento, " +
			"  :rtData," +
			"  :stato_invio_fruitore," +
			"  :data_invio_fruitore," +
			"  :sorgente_invio_fruitore," +
			"  :commissioniApplicatePSP )";
		
            params.addValue("insert_date",dto.getInsertDate(),java.sql.Types.TIMESTAMP);
        
			params.addValue("application_id",dto.getApplicationId(),java.sql.Types.VARCHAR);
			params.addValue("transaction_id",dto.getTransactionId(),java.sql.Types.VARCHAR);
			params.addValue("data_msg_ricevuta",dto.getDataMsgRicevuta(),java.sql.Types.TIMESTAMP);
			params.addValue("id_msg_richiesta",dto.getIdMsgRichiesta(),java.sql.Types.VARCHAR);	
			params.addValue("id_msg_ricevuta",dto.getIdMsgRicevuta(),java.sql.Types.VARCHAR);	
			params.addValue("tipo_firma",dto.getTipoFirma(),java.sql.Types.VARCHAR);
			params.addValue("iuv",dto.getIuv(),java.sql.Types.VARCHAR);
			params.addValue("id_esito_pagamento",dto.getIdEsitoPagamento(),java.sql.Types.INTEGER);
			params.addValue("rtData",dto.getRtData(),java.sql.Types.BINARY);
			params.addValue("stato_invio_fruitore",dto.getStatoInvioFruitore(),java.sql.Types.VARCHAR);
			params.addValue("data_invio_fruitore",dto.getDataInvioFruitore(),java.sql.Types.TIMESTAMP);
			params.addValue("sorgente_invio_fruitore",dto.getSogenteInvioFruitore(),java.sql.Types.VARCHAR);
			params.addValue("commissioniApplicatePSP",dto.getCommissioniApplicatePSP(),java.sql.Types.DOUBLE);
			LoggerUtil.debug(mysql);
			LoggerUtil.end();
			jdbcTemplate.update(mysql,params);
	}
	
	
	
	public void insertCoda(String iuv, String applicationId, String transactionId) throws DaoException {
		
		LoggerUtil.begin();
		
		MapSqlParameterSource params = new MapSqlParameterSource();

        String mysql = "INSERT INTO rt_coda_invio (iuv, application_id, transaction_id) VALUES (:iuv, :application_id, :transaction_id)";
        
        params.addValue ( "iuv", iuv, java.sql.Types.VARCHAR );
        params.addValue ( "application_id", applicationId, java.sql.Types.VARCHAR );
        params.addValue ( "transaction_id", transactionId, java.sql.Types.VARCHAR );
			
		LoggerUtil.end();
		jdbcTemplate.update(mysql,params);
	}
	
	public void updateIdRrByIuv(String iuv, Integer idRR) {
	    LoggerUtil.begin();
	        
	    MapSqlParameterSource params = new MapSqlParameterSource();

        String mysql = "UPDATE rt set id_rr = :idRr where iuv = :iuv and id_rr is null";
        
        params.addValue ( "idRr", idRR, Types.INTEGER );
        params.addValue ( "iuv", iuv, Types.VARCHAR );        
        
	    LoggerUtil.end();
	    
	    jdbcTemplate.update(mysql,params);
	}
	

	public void update(RT dto) throws DaoException {

        LoggerUtil.begin();
        LoggerUtil.dump(dto);
        
        MapSqlParameterSource params = new MapSqlParameterSource();

        String mysql = "UPDATE rt SET " +
            "  application_id=:application_id,"+
            "  transaction_id=:transaction_id,"+
            "  last_update=now(),"+
//            "  data_msg_ricevuta=:data_msg_ricevuta,"+
            "  id_msg_richiesta=:id_msg_richiesta,"+
            "  id_msg_ricevuta=:id_msg_ricevuta,"+
            "  tipo_firma=:tipo_firma,"+
            "  iuv=:iuv,"+
            "  id_esito_pagamento=:id_esito_pagamento," +
            "  rt_data=:rt_data," +
            "  stato_invio_fruitore=:stato_invio_fruitore," +
            "  data_invio_fruitore=:data_invio_fruitore," +
            "  sorgente_invio_fruitore=:sorgente_invio_fruitore," +
            "  commissioniapplicatepsp=:commissioniApplicatePSP " +
            "  WHERE " +
            "  transaction_id=:transaction_id and id_msg_ricevuta=:id_msg_ricevuta and insert_date=:insert_date";
        
            params.addValue("transaction_id",dto.getTransactionId(),java.sql.Types.VARCHAR);
            params.addValue("id_msg_ricevuta",dto.getIdMsgRicevuta(),java.sql.Types.VARCHAR);   
            params.addValue("insert_date",dto.getInsertDate(),java.sql.Types.TIMESTAMP);
        
            params.addValue("application_id",dto.getApplicationId(),java.sql.Types.VARCHAR);
//            params.addValue("data_msg_ricevuta",dto.getDataMsgRicevuta(),java.sql.Types.TIMESTAMP);
            params.addValue("id_msg_richiesta",dto.getIdMsgRichiesta(),java.sql.Types.VARCHAR); 
            params.addValue("tipo_firma",dto.getTipoFirma(),java.sql.Types.VARCHAR);
            params.addValue("iuv",dto.getIuv(),java.sql.Types.VARCHAR);
            params.addValue("id_esito_pagamento",dto.getIdEsitoPagamento(),java.sql.Types.INTEGER);
            params.addValue("rt_data",dto.getRtData(),java.sql.Types.BINARY);
            params.addValue("stato_invio_fruitore",dto.getStatoInvioFruitore(),java.sql.Types.VARCHAR);
            params.addValue("data_invio_fruitore",dto.getDataInvioFruitore(),java.sql.Types.TIMESTAMP);
            params.addValue("sorgente_invio_fruitore",dto.getSogenteInvioFruitore(),java.sql.Types.VARCHAR);
            params.addValue("commissioniApplicatePSP",dto.getCommissioniApplicatePSP(),java.sql.Types.DOUBLE);
            
            LoggerUtil.debug(mysql);
            LoggerUtil.end();
            jdbcTemplate.update(mysql,params);
	}

	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("cast")
	public List<RT> findAll() throws DaoException {
		return getRTByParam(null,null, null,null,null,null,null, null,null,null);
	}

	public List<RT> findWhereKeyidEquals(Integer id) throws DaoException {
		return getRTByParam(id,null, null,null,null,null,null, null,null,null);
	}
	
	public List<RT> findWhereApplicationIdEquals(String applicationId) throws DaoException {
		return getRTByParam(null,applicationId, null,null,null,null,null, null,null,null);
	}
	
	public List<RT> findWhereTransactionIdEquals(String transactionId) throws DaoException {
		return getRTByParam(null,null, transactionId,null,null,null,null, null,null,null);
	}
	
	public List<RT> findWhereIUVEquals(String iuv) throws DaoException {
		return getRTByParam(null,null, null,null,null,null,null, iuv,null,null);
	}

	public List<RT> getRTByParam(Integer id ,
									String applicationId,
									String transactionId,
									Date lastUpdateDa,
									Date lastUpdateA,
									Date insertDateDa,
									Date insertDateA,
									String iuv,
									String idEsitoPagamento,
									String idMsgRichiesta
									) throws DaoException {
		LoggerUtil.begin();
		StringBuffer sql = new StringBuffer();
		MapSqlParameterSource params = new MapSqlParameterSource();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		
		sql.append(" SELECT ");
		sql.append("  a.id ,");
		sql.append("  a.application_id,");
		sql.append("  a.transaction_id,");
		sql.append("  a.insert_date,");
		sql.append("  a.last_update,");
		sql.append("  a.data_msg_ricevuta,");
		sql.append("  a.id_msg_ricevuta,");
		sql.append("  a.rt_data,");
		sql.append("  a.tipo_firma,");
		sql.append("  a.iuv,");
		sql.append("  a.id_esito_pagamento,");
		sql.append("  a.id_msg_richiesta,");
		sql.append("  (SELECT b.descrizione FROM codici_esito_pagamento b WHERE b.id_esito_pagamento = a.id_esito_pagamento) as descrizione");
		sql.append("  FROM rt a");
		sql.append("  WHERE 1=1");
		
		if(id!=null){ 
			params.addValue("id", id, java.sql.Types.INTEGER);
			sql.append(" AND a.id=:id");
		}
		if(StringUtil.isNotBlank(applicationId)){ 
			params.addValue("applicationId", applicationId, java.sql.Types.VARCHAR);
			sql.append(" AND a.application_id=:applicationId");
		}
		if(StringUtil.isNotBlank(transactionId)){
			params.addValue("transactionId", transactionId, java.sql.Types.VARCHAR);
			sql.append(" AND a.transaction_id=:transactionId");
		}
		
		
		if(lastUpdateDa!=null){ 
			//sql.append(" AND a.last_update >= to_timestamp('" +sdf.format(lastUpdateDa)+"','YYYYMMDD HH24:MI')");
			sql.append(" AND date_trunc('DAY',a.last_update) >= to_timestamp('" +sdf.format(lastUpdateDa)+"','YYYYMMDD')");
			//Date lastUpdateFine = UtilDate.addDay(lastUpdate,1);
			//sql.append(" AND a.last_update between to_timestamp('" +sdf.format(lastUpdate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(lastUpdateFine)+"','YYYYMMDD HH24:MI')" );
			//params.addValue("lastUpdate", lastUpdate, java.sql.Types.DATE);
		}		
		if(lastUpdateA!=null){ 
			//sql.append(" AND a.last_update <= to_timestamp('" +sdf.format(lastUpdateA)+"','YYYYMMDD HH24:MI')");
			sql.append(" AND date_trunc('DAY',a.last_update) <= to_timestamp('" +sdf.format(lastUpdateA)+"','YYYYMMDD')");
		}
		
		
		if(insertDateDa!=null){ 
			sql.append(" AND date_trunc('DAY',a.insert_date) >= to_timestamp('" +sdf.format(insertDateDa)+"','YYYYMMDD')");
			//sql.append(" AND a.insert_date >= to_timestamp('" +sdf.format(insertDateDa)+"','YYYYMMDD HH24:MI')");
			//Date insertDateFine = UtilDate.addDay(insertDate,1);
			//sql.append(" AND a.insert_date between to_timestamp('" +sdf.format(insertDate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(insertDateFine)+"','YYYYMMDD HH24:MI')" );
			//params.addValue("insertDate", insertDate, java.sql.Types.DATE);
		}
		if(insertDateA!=null){
			//sql.append(" AND a.insert_date <= to_timestamp('" +sdf.format(insertDateA)+"','YYYYMMDD HH24:MI')");
			sql.append(" AND date_trunc('DAY',a.insert_date) <= to_timestamp('" +sdf.format(insertDateA)+"','YYYYMMDD')");
		}
		
		
		if(StringUtil.isNotBlank(iuv)){
			sql.append(" AND a.iuv = '"+iuv+"'");
		}
		if(StringUtil.isNotBlank(idEsitoPagamento)){ 
			params.addValue("idEsitoPagamento", Integer.parseInt(idEsitoPagamento), java.sql.Types.INTEGER);
			sql.append(" AND a.id_esito_pagamento=:idEsitoPagamento");
		}
		if(StringUtil.isNotBlank(idMsgRichiesta)){
			sql.append(" AND a.id_msg_richiesta=:idMsgRichiesta");
			params.addValue("idMsgRichiesta", idMsgRichiesta, java.sql.Types.VARCHAR);
		}
		sql.append(" ORDER BY a.insert_date DESC ");
		

		LoggerUtil.info("Integer id " +id );
		LoggerUtil.info("String applicationId "+applicationId );
		LoggerUtil.info("String transactionId " + transactionId);
		LoggerUtil.info("Date lastUpdateDa " +lastUpdateDa);
		LoggerUtil.info("Date lastUpdateA " +lastUpdateA);
		LoggerUtil.info("Date insertDateDa " +insertDateDa);
		LoggerUtil.info("Date insertDateA " +insertDateA);
		LoggerUtil.info("String iuv "+ iuv );
		LoggerUtil.info("String idEsitoPagamento "+ idEsitoPagamento );

		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	}
	
	
		
	public RT mapRow(ResultSet rs, int index) throws SQLException {
		RT rt = new RT();
		rt.setId(rs.getInt("id"));
		rt.setApplicationId(rs.getString("application_id"));
		rt.setTransactionId(rs.getString("transaction_id"));
		rt.setInsertDate(rs.getTimestamp("insert_date"));
		rt.setLastUpdate(rs.getTimestamp("last_update"));
		rt.setDataMsgRicevuta(rs.getTimestamp("data_msg_ricevuta"));
		rt.setIdMsgRicevuta(rs.getString("id_msg_ricevuta"));
		rt.setRtData(rs.getBytes("rt_data"));
		rt.setTipoFirma(rs.getString("tipo_firma"));
		rt.setIuv(rs.getString("iuv"));
		rt.setIdEsitoPagamento(rs.getInt("id_esito_pagamento"));
		rt.setDescEsitoPagamento(rs.getString("descrizione"));
		rt.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));

		return rt;
	}
}
