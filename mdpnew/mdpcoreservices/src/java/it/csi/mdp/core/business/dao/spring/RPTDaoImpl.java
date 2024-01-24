/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import it.csi.mdp.core.business.dao.RPTDao;
import it.csi.mdp.core.business.dto.RPT;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.business.util.StringUtil;
import it.csi.mdp.core.util.LoggerUtil;

public class RPTDaoImpl extends AbstractDAO implements ParameterizedRowMapper<RPT>, RPTDao
{
	protected NamedParameterJdbcTemplate jdbcTemplate;
	protected SimpleJdbcTemplate jdbcSimpleTemplate;
	
	
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
		jdbcSimpleTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public void insert(RPT dto) throws DaoException {
		LoggerUtil.begin();
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "INSERT INTO rpt("+
			"  application_id,"+
			"  transaction_id,"+
			"  insert_date,"+
			"  last_update,"+
			"  auth_soggetto,"+
			"  data_msg_richiesta,"+
			"  id_canale ,"+
			"  id_interm_psp,"+ 
			"  id_msg_richiesta,"+
			"  id_psp ,"+
			"  identificativo_dominio,"+
			"  identificativo_intermediario_pa,"+
			"  identificativo_stazione_intermediario_pa,"+
			"  rpt_xml,"+
			"  iuv,"+
			//CSI_PAG-1837 AGGIUNTI 2 NUOVI CAMPI ALLA FINE
			"  id_stati_rpt, codice_contesto_pagamento, da_inviare, data_invio, multibeneficiario, numero_avviso)" +
			" VALUES " +
			" (:application_id,"+ 
			"  :transaction_id,"+
			"  now(), "+
			"  now(),"+
			"  :auth_soggetto, "+
			"  :data_msg_richiesta, "+
			"  :id_canale, "+
			"  :id_interm_psp, "+
			"  :id_msg_richiesta, "+
			"  :id_psp, "+
			"  :identificativo_dominio ,"+
			"  :identificativo_intermediario_pa,"+
			"  :identificativo_stazione_intermediario_pa, "+
			"  :rpt_xml,"+
			"  :iuv ,"+
			//CSI_PAG-1837 AGGIUNTI 2 NUOVI CAMPI ALLA FINE
			"  :id_stati_rpt, " +
			"  :codice_contesto_pagamento, " +
			"  :da_inviare, " +
			"  :data_invio, " +
			"  :multibeneficiario, " +
			"  :numero_avviso)";
		
			params.addValue("application_id",dto.getApplicationId(),java.sql.Types.VARCHAR);
			params.addValue("transaction_id",dto.getTransactionId(),java.sql.Types.VARCHAR);
			params.addValue("auth_soggetto",dto.getAuthSoggetto(),java.sql.Types.VARCHAR);
			params.addValue("data_msg_richiesta",dto.getDataMsgRichiesta(),java.sql.Types.TIMESTAMP);
			params.addValue("id_canale",dto.getIdCanale(),java.sql.Types.VARCHAR);
			params.addValue("id_interm_psp",dto.getIdIntermPsp(),java.sql.Types.VARCHAR);
			params.addValue("id_msg_richiesta",dto.getIdMsgRichiesta(),java.sql.Types.VARCHAR);
			params.addValue("id_psp",dto.getIdPsp(),java.sql.Types.VARCHAR);
			params.addValue("identificativo_dominio",dto.getIdentificativoDominio(),java.sql.Types.VARCHAR);
			params.addValue("identificativo_intermediario_pa",dto.getIdentificativoIntermediarioPa(),java.sql.Types.VARCHAR);
			params.addValue("identificativo_stazione_intermediario_pa",dto.getIdentificativoStazioneIntermediarioPa(),java.sql.Types.VARCHAR);
			params.addValue("rpt_xml",dto.getRptXml(),java.sql.Types.VARCHAR);
			params.addValue("iuv",dto.getIuv(),java.sql.Types.VARCHAR);
			params.addValue("id_stati_rpt",dto.getIdStatiRpt(),java.sql.Types.INTEGER);
			params.addValue("codice_contesto_pagamento",dto.getCodiceContestoPagamento(),java.sql.Types.VARCHAR);
			params.addValue("da_inviare",dto.getDaInviare(),java.sql.Types.BOOLEAN);
			params.addValue("data_invio",dto.getDataInvio(),java.sql.Types.TIMESTAMP);
			//CSI_PAG-1837 AGGIUNTI 2 NUOVI CAMPI ALLA FINE
			params.addValue("multibeneficiario",dto.getMultibeneficiario() == null ? false : dto.getMultibeneficiario());
			params.addValue("numero_avviso",dto.getNumeroAvviso());

			LoggerUtil.end();
			jdbcTemplate.update(mysql,params);
		
	}

	public List<RPT> findWhereFiltro(RPT filtro) throws DaoException {
		return getRPTByParam(filtro.getId(),filtro.getApplicationId(), filtro.getTransactionId(), null, null, null,null, filtro.getIuv(), filtro.getIdStatiRpt() != null ? String.valueOf(filtro.getIdStatiRpt())  : null,filtro.getIdMsgRichiesta());
	}

	@SuppressWarnings("unchecked")
	public List<RPT> getRPTByParam(Integer id , String applicationId, String transactionId, Date lastUpdateDa,Date lastUpdateA,Date insertDateDa,Date insertDateA, String iuv, String idStatiRpt,String idMsgRichiesta) throws DaoException {
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
		sql.append("  a.auth_soggetto,");
		sql.append("  a.data_msg_richiesta,");
		sql.append("  a.id_canale ,");
		sql.append("  a.id_interm_psp,");
		sql.append("  a.id_msg_richiesta,");
		sql.append("  a.id_psp ,");
		sql.append("  a.identificativo_dominio,");
		sql.append("  a.identificativo_intermediario_pa,");
		sql.append("  a.identificativo_stazione_intermediario_pa,");
		sql.append("  a.rpt_xml,");
		sql.append("  a.iuv,");
		sql.append("  a.id_stati_rpt , ");
		//CSI_PAG-1837 AGGIUNTI 2 NUOVI CAMPI ALLA FINE
		sql.append("  b.descrizione, a.codice_contesto_pagamento, a.da_inviare, a.data_invio, multibeneficiario, numero_avviso ");
		sql.append("  FROM  rpt a , ");
		sql.append("  stati_rpt b ");
		sql.append("  WHERE a.id_stati_rpt=b.id_stati_rpt ");	
		 
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
			sql.append(" AND date_trunc('DAY',a.last_update) >= to_timestamp('" +sdf.format(lastUpdateDa)+"','YYYYMMDD')");
			//sql.append(" AND a.last_update >= to_timestamp('" +sdf.format(lastUpdateDa)+"','YYYYMMDD HH24:MI')");
//			Date lastUpdateFine = UtilDate.addDay(lastUpdate,1);
//			sql.append(" AND a.last_update between to_timestamp('" +sdf.format(lastUpdate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(lastUpdateFine)+"','YYYYMMDD HH24:MI')" );
//			params.addValue("lastUpdate", lastUpdate, java.sql.Types.DATE);
		}
		if(lastUpdateA!=null){ 
			sql.append(" AND date_trunc('DAY',a.last_update) <= to_timestamp('" +sdf.format(lastUpdateA)+"','YYYYMMDD')");
			//sql.append(" AND a.last_update <= to_timestamp('" +sdf.format(lastUpdateA)+"','YYYYMMDD HH24:MI')");

//			Date lastUpdateFine = UtilDate.addDay(lastUpdate,1);
//			sql.append(" AND a.last_update between to_timestamp('" +sdf.format(lastUpdate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(lastUpdateFine)+"','YYYYMMDD HH24:MI')" );
//			params.addValue("lastUpdate", lastUpdate, java.sql.Types.DATE);
		}

		if(insertDateDa!=null){ 
			sql.append(" AND date_trunc('DAY',a.insert_date) >= to_timestamp('" +sdf.format(insertDateDa)+"','YYYYMMDD')");
			//sql.append(" AND a.insert_date <= to_timestamp('" +sdf.format(insertDateDa)+"','YYYYMMDD HH24:MI')");
//			Date insertDateFine = UtilDate.addDay(insertDate,1);
//			sql.append(" AND a.insert_date between to_timestamp('" +sdf.format(insertDate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(insertDateFine)+"','YYYYMMDD HH24:MI')" );
		}

		if(insertDateA!=null){ 
			sql.append(" AND date_trunc('DAY',a.insert_date) <= to_timestamp('" +sdf.format(insertDateA)+"','YYYYMMDD')");
			//sql.append(" AND a.insert_date <= to_timestamp('" +sdf.format(insertDateA)+"','YYYYMMDD HH24:MI')");
//			Date insertDateFine = UtilDate.addDay(insertDate,1);
//			sql.append(" AND a.insert_date between to_timestamp('" +sdf.format(insertDate)+"','YYYYMMDD HH24:MI') AND to_timestamp('" +sdf.format(insertDateFine)+"','YYYYMMDD HH24:MI')" );
		}

		if(StringUtil.isNotBlank(iuv)){
		    //Paolo - Tolto il like per motivi di performance. I vari metodi che utilizzano
		    //getRPTByParam salgono fino a recuperaRichiestaPagamentoConFiltro arrivando al
		    //client, li tutto dovrebbe gia' arrivare completo e non parziale perche' arriva
		    //dal nodo : es. private List<RPT> reperisciRPTPerID(CtRicevutaTelematica rt, Payment p, String iuv)
			sql.append(" AND a.iuv ='"+iuv+"'");
		}

		if(StringUtil.isNotBlank(idMsgRichiesta)){
			sql.append(" AND a.id_msg_richiesta=:idMsgRichiesta");
			params.addValue("idMsgRichiesta", idMsgRichiesta, java.sql.Types.VARCHAR);
		}
		
		if(StringUtil.isNotBlank(idStatiRpt)){
			sql.append(" AND a.id_stati_rpt=:idStatiRpt");
			params.addValue("idStatiRpt", Integer.parseInt(idStatiRpt), java.sql.Types.INTEGER);
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
		LoggerUtil.info("Integer idStatiRpt "+idStatiRpt );
		LoggerUtil.info("String idMsgRichiesta " + idMsgRichiesta);
		
		LoggerUtil.info("sql --> " + sql.toString());
		LoggerUtil.end();
		return jdbcTemplate.query(sql.toString(), params, this);
	}

	public List<RPT> findAll() throws DaoException {
		return getRPTByParam(null,null,null,null,null, null, null, null, null,null);
	}
	
	/**
	 * Aggiorna solo i campi oggeto di modifica
	 */
	public void update(RPT dto) throws DaoException {
		LoggerUtil.begin();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String sqlUpdate = "UPDATE rpt " +
				" SET last_update=now(), " +
				"id_canale=:id_canale, " +
				"id_interm_psp=:id_interm_psp,  " +
				"id_psp=:id_psp, " +
				"identificativo_dominio=:identificativo_dominio, " +
				"rpt_xml=:rpt_xml, " +
				"id_stati_rpt=:id_stati_rpt, " +
				"codice_contesto_pagamento=:codice_contesto_pagamento " +
				//CSI_PAG-1837 AGGIUNTI 2 NUOVI CAMPI ALLA FINE
				"multibeneficiario=:multibeneficiario " +
				"numero_avviso=:numero_avviso " +
				"WHERE id=:id ";

		params.addValue("id_canale",dto.getIdCanale(),java.sql.Types.VARCHAR);
		params.addValue("id_interm_psp",dto.getIdIntermPsp(),java.sql.Types.VARCHAR);
		params.addValue("id_psp",dto.getIdPsp(),java.sql.Types.VARCHAR);
		params.addValue("identificativo_dominio",dto.getIdentificativoDominio(),java.sql.Types.VARCHAR);
		params.addValue("identificativo_intermediario_pa",dto.getIdentificativoIntermediarioPa(),java.sql.Types.VARCHAR);
		params.addValue("identificativo_stazione_intermediario_pa",dto.getIdentificativoStazioneIntermediarioPa(),java.sql.Types.VARCHAR);
		params.addValue("rpt_xml",dto.getRptXml(),java.sql.Types.VARCHAR);
		params.addValue("id_stati_rpt",dto.getIdStatiRpt(),java.sql.Types.INTEGER);
		params.addValue("codice_contesto_pagamento",dto.getCodiceContestoPagamento(),java.sql.Types.VARCHAR);
		//CSI_PAG-1837 AGGIUNTI 2 NUOVI CAMPI ALLA FINE
		params.addValue("multibeneficiario",dto.getCodiceContestoPagamento(),java.sql.Types.VARCHAR);
		
		LoggerUtil.debug("ID " + dto.getId());
		params.addValue("id", dto.getId());

		LoggerUtil.end();
		jdbcTemplate.update(sqlUpdate,params);
	}

	public void delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub		
	}

	public RPT mapRow(ResultSet rs, int index) throws SQLException {
		
		RPT rpt = new RPT();
		rpt.setId(rs.getInt("id"));
		
		rpt.setApplicationId(rs.getString("application_id"));
		rpt.setTransactionId(rs.getString("transaction_id"));
		rpt.setInsertDate(rs.getTimestamp("insert_date"));
		rpt.setLastUpdate(rs.getTimestamp("last_update"));
		rpt.setAuthSoggetto(rs.getString("auth_soggetto"));
		
		rpt.setDataMsgRichiesta(rs.getTimestamp("data_msg_richiesta"));
		rpt.setIdCanale(rs.getString("id_canale"));
		rpt.setIdIntermPsp(rs.getString("id_interm_psp"));
		rpt.setIdMsgRichiesta(rs.getString("id_msg_richiesta"));
		rpt.setIdPsp(rs.getString("id_psp"));
		rpt.setIdentificativoDominio(rs.getString("identificativo_dominio"));
		rpt.setIdentificativoIntermediarioPa(rs.getString("identificativo_intermediario_pa"));
		rpt.setIdentificativoStazioneIntermediarioPa(rs.getString("identificativo_stazione_intermediario_pa"));
		rpt.setRptXml(rs.getString("rpt_xml"));
		rpt.setIuv(rs.getString("iuv"));
		rpt.setIdStatiRpt(rs.getInt("id_stati_rpt"));
		rpt.setDescStatoRpt(rs.getString("descrizione"));
		rpt.setCodiceContestoPagamento(rs.getString("codice_contesto_pagamento"));
		rpt.setDaInviare(rs.getBoolean("da_inviare"));
		rpt.setDataInvio(rs.getTimestamp("data_invio"));
		//CSI_PAG-1837 AGGIUNTI 2 NUOVI CAMPI ALLA FINE
		rpt.setMultibeneficiario(rs.getBoolean("multibeneficiario"));
		rpt.setNumeroAvviso(rs.getString("numero_avviso"));
		
		return rpt;
	}
	//RDI-45-MULTIBENEFICIARIO INIZIO
    public int findCountForIdDominioAndIuv ( String identificativoDominio, String numeroAvviso ) {
        LoggerUtil.begin ();
        String sql = "SELECT count(*) FROM rpt WHERE identificativo_dominio = ? AND numero_avviso = ? ";
//        MapSqlParameterSource params = new MapSqlParameterSource ();
//        params.addValue ( "identificativo_dominio", identificativoDominio, java.sql.Types.VARCHAR );
//        params.addValue ( "numero_avviso", numeroAvviso, java.sql.Types.VARCHAR );
        LoggerUtil.info ( "sql --> " + sql );
        LoggerUtil.end ();
        return jdbcSimpleTemplate.queryForObject ( sql, Integer.class, identificativoDominio, numeroAvviso );
//        return jdbcSimpleTemplate.queryForObject ( sql, Integer.class, params );
//        return jdbcSimpleTemplate.queryForInt ( sql, params );
    }
    
    public int findNexValueIdDominio () {
        LoggerUtil.begin ();
        String sql = "SELECT NEXTVAL('id_carrello_seq')";
        LoggerUtil.info ( "sql --> " + sql );
        LoggerUtil.end ();
        return jdbcSimpleTemplate.queryForObject ( sql, Integer.class );
    }
	//RDI-45-MULTIBENEFICIARIO FINE
}
