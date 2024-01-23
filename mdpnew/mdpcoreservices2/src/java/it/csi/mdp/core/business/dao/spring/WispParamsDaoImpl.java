/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.WispParamsDao;
import it.csi.mdp.core.business.dto.ParametroWisp;
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

public class WispParamsDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ParametroWisp>, WispParamsDao {
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

	
	public void insert(ParametroWisp toInsert) throws DaoException {

		LoggerUtil.begin();
		LoggerUtil.dumpObject("DA INSERIRE ",toInsert);
		
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "INSERT INTO wisp_params(keypa, application_id, transaction_id, identificativodominio, ente_creditore, url_return, url_back, primitiva, num_pagamenti_rpt, storno_pagamento, bollo_digitale, terzo_modello_pagamento, id_psp, tipo_versamento, importo_transazione, insert_date, last_update, versioneInterfacciaWisp, contoPoste, pagamentiModello2, ibanAccredito, urlGestione, codiceLingua)" +
					   " VALUES (:keypa, :application_id, :transaction_id, :identificativodominio, :ente_creditore, :url_return, :url_back, :primitiva, :num_pagamenti_rpt, :storno_pagamento, :bollo_digitale, :terzo_modello_pagamento, :id_psp, :tipo_versamento, :importo_transazione, now(), now(), :versioneInterfacciaWisp, :contoPoste, :pagamentiModello2, :ibanAccredito, :urlGestione, :codiceLingua)";
		
		params.addValue("keypa",toInsert.getKeyPA(),java.sql.Types.VARCHAR);
		params.addValue("application_id",toInsert.getApplicationId(),java.sql.Types.VARCHAR);
		params.addValue("transaction_id",toInsert.getTransactionId(),java.sql.Types.VARCHAR);
		params.addValue("identificativodominio",toInsert.getIdDominio(),java.sql.Types.VARCHAR);
		params.addValue("ente_creditore",toInsert.getEnteCreditore(),java.sql.Types.VARCHAR);
		params.addValue("url_return",toInsert.getUrlReturn(),java.sql.Types.VARCHAR);
		params.addValue("url_back",toInsert.getUrlBack(),java.sql.Types.VARCHAR);
		params.addValue("primitiva",toInsert.getPrimitiva(),java.sql.Types.VARCHAR);
		params.addValue("num_pagamenti_rpt",toInsert.getNumPagamentiRPT(),java.sql.Types.INTEGER);
		params.addValue("storno_pagamento",toInsert.getStornoPagamento(),java.sql.Types.VARCHAR);
		params.addValue("bollo_digitale",toInsert.getBolloDigitale(),java.sql.Types.VARCHAR);
		params.addValue("terzo_modello_pagamento",toInsert.getTerzoModelloPagamento(),java.sql.Types.VARCHAR);
		params.addValue("id_psp",toInsert.getIdPsp(),java.sql.Types.VARCHAR);
		params.addValue("tipo_versamento",toInsert.getTipoVersamento(),java.sql.Types.VARCHAR);
		params.addValue("importo_transazione",toInsert.getImportoTransazione(),java.sql.Types.DOUBLE);
		params.addValue("versioneInterfacciaWisp",toInsert.getVersioneInterfacciaWisp(),java.sql.Types.VARCHAR);
		params.addValue("contoPoste",toInsert.getContoPoste(),java.sql.Types.VARCHAR);
		params.addValue("pagamentiModello2",toInsert.getPagamentiModello2(),java.sql.Types.VARCHAR);
		params.addValue("ibanAccredito",toInsert.getIbanAccredito(),java.sql.Types.VARCHAR);
		params.addValue("urlGestione",toInsert.getUrlGestione(),java.sql.Types.VARCHAR);
		params.addValue("codiceLingua",toInsert.getCodiceLingua(),java.sql.Types.VARCHAR);
		
		LoggerUtil.debug(mysql);
		LoggerUtil.end();
		jdbcTemplate.update(mysql,params);
	}
	
	public int update(ParametroWisp toInsert) throws DaoException {
		
		LoggerUtil.begin();
		LoggerUtil.dumpObject("DA AGGIORNARE ", toInsert);
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String mysql = "UPDATE wisp_params SET application_id=:application_id, transaction_id=:transaction_id, " +
				"identificativodominio=:identificativodominio, ente_creditore=:ente_creditore, url_return=:url_return, url_back=:url_back, " +
				"primitiva=:primitiva, num_pagamenti_rpt=:num_pagamenti_rpt, storno_pagamento=:storno_pagamento, bollo_digitale=:bollo_digitale, " +
				"terzo_modello_pagamento=:terzo_modello_pagamento, id_psp=:id_psp, tipo_versamento=:tipo_versamento, " +
				"importo_transazione=:importo_transazione, keywisp=:keywisp, type=:type, " +
				"effettuazionescelta=:effettuazionescelta, identificativopspscelto=:identificativopspscelto, " +
				"identificativointermediariopspscelto=:identificativointermediariopspscelto, identificativocanalescelto=:identificativocanalescelto, " +
				"tipoversamentoscelto=:tipoversamentoscelto, esito=:esito, stringaerrore=:stringaerrore, last_update=now(), codiceLingua = :codiceLingua WHERE keypa=:keypa";
		
		params.addValue("keypa",toInsert.getKeyPA(),java.sql.Types.VARCHAR);
		params.addValue("application_id",toInsert.getApplicationId(),java.sql.Types.VARCHAR);
		params.addValue("transaction_id",toInsert.getTransactionId(),java.sql.Types.VARCHAR);
		params.addValue("identificativodominio",toInsert.getIdDominio(),java.sql.Types.VARCHAR);
		params.addValue("ente_creditore",toInsert.getEnteCreditore(),java.sql.Types.VARCHAR);
		params.addValue("url_return",toInsert.getUrlReturn(),java.sql.Types.VARCHAR);
		params.addValue("url_back",toInsert.getUrlBack(),java.sql.Types.VARCHAR);
		params.addValue("primitiva",toInsert.getPrimitiva(),java.sql.Types.VARCHAR);
		params.addValue("num_pagamenti_rpt",toInsert.getNumPagamentiRPT(),java.sql.Types.INTEGER);
		params.addValue("storno_pagamento",toInsert.getStornoPagamento(),java.sql.Types.VARCHAR);
		params.addValue("bollo_digitale",toInsert.getBolloDigitale(),java.sql.Types.VARCHAR);
		params.addValue("terzo_modello_pagamento",toInsert.getTerzoModelloPagamento(),java.sql.Types.VARCHAR);
		params.addValue("id_psp",toInsert.getIdPsp(),java.sql.Types.VARCHAR);
		params.addValue("tipo_versamento",toInsert.getTipoVersamento(),java.sql.Types.VARCHAR);
		params.addValue("importo_transazione",toInsert.getImportoTransazione(),java.sql.Types.DOUBLE);
		
		params.addValue("keywisp",toInsert.getKeyWISP(),java.sql.Types.VARCHAR);
		params.addValue("type",toInsert.getType(),java.sql.Types.VARCHAR);
		params.addValue("effettuazionescelta",toInsert.getEffettuazioneScelta(),java.sql.Types.VARCHAR);
		params.addValue("identificativopspscelto",toInsert.getIdentificativoPSPScelto(),java.sql.Types.VARCHAR);
		params.addValue("identificativointermediariopspscelto",toInsert.getIdnetificativoIntermediarioPSPScelto(),java.sql.Types.VARCHAR);
		params.addValue("identificativocanalescelto",toInsert.getIdentificativoCanale(),java.sql.Types.VARCHAR);
		params.addValue("tipoversamentoscelto",toInsert.getTipoVersamentoScelto(),java.sql.Types.VARCHAR);
		params.addValue("esito",toInsert.getEsito(),java.sql.Types.VARCHAR);
		params.addValue("stringaerrore",toInsert.getStringaErrore(),java.sql.Types.VARCHAR);
		params.addValue("codiceLingua", toInsert.getCodiceLingua());
		params.addValue("keypa",toInsert.getKeyPA(),java.sql.Types.VARCHAR);
		
		LoggerUtil.debug(mysql);
		LoggerUtil.end();
		return jdbcTemplate.update(mysql,params);
	}

	public List<ParametroWisp> find(ParametroWisp filtro) throws DaoException {
		LoggerUtil.begin();
		LoggerUtil.dumpObject("FILTRO ",filtro);
		
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "select * from wisp_params where 1=1 and ";
		
		
		if (StringUtils.isNotEmpty(filtro.getKeyPA())) {
			mysql += " keypa = :keypa ";
			params.addValue("keypa",filtro.getKeyPA(),java.sql.Types.VARCHAR);
		}
		
		
		LoggerUtil.debug(mysql);
		LoggerUtil.end();
		return jdbcTemplate.query(mysql, params, this);
	}

	public ParametroWisp mapRow(ResultSet rs, int arg1) throws SQLException {
		ParametroWisp riga = new ParametroWisp();
		
		riga.setApplicationId(rs.getString("application_id"));
		riga.setTransactionId(rs.getString("transaction_id"));
		riga.setKeyPA(rs.getString("keypa"));
		riga.setIdDominio(rs.getString("identificativodominio"));
		riga.setEnteCreditore(rs.getString("ente_creditore"));
		riga.setUrlReturn(rs.getString("url_return"));
		riga.setUrlBack(rs.getString("url_back"));
		riga.setPrimitiva(rs.getString("primitiva"));
		riga.setNumPagamentiRPT(rs.getInt("num_pagamenti_rpt"));
		riga.setStornoPagamento(rs.getString("storno_pagamento"));
		riga.setBolloDigitale(rs.getString("bollo_digitale"));
		riga.setTerzoModelloPagamento(rs.getString("terzo_modello_pagamento"));
		riga.setIdPsp(rs.getString("id_psp"));
		riga.setTipoVersamento(rs.getString("tipo_versamento"));
		riga.setImportoTransazione(rs.getDouble("importo_transazione"));
		riga.setIdentificativoCanale(rs.getString("identificativocanale"));
		riga.setKeyWISP(rs.getString("keywisp"));
		riga.setType(rs.getString("type"));
		riga.setEffettuazioneScelta(rs.getString("effettuazionescelta"));
		riga.setIdentificativoPSPScelto(rs.getString("identificativopspscelto"));
		riga.setIdentificativoCanale(rs.getString("identificativocanalescelto"));
		riga.setIdentificativoIntermediarioPSPScelto(rs.getString("identificativointermediariopspscelto"));
		riga.setTipoVersamentoScelto(rs.getString("tipoversamentoscelto"));
		riga.setEsito(rs.getString("esito"));
		riga.setStringaErrore(rs.getString("stringaerrore"));
		riga.setVersioneInterfacciaWisp(rs.getString("versioneinterfacciawisp"));
		riga.setUrlGestione(rs.getString("urlGestione"));
		riga.setContoPoste(rs.getString("contoposte"));
		riga.setIbanAccredito(rs.getString("ibanaccredito"));
		riga.setPagamentiModello2(rs.getString("pagamentimodello2"));
		riga.setCodiceLingua(rs.getString("codiceLingua"));
		
		return riga;
	}


	public int delete(ParametroWisp filtro) throws DaoException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("keypa", filtro.getKeyPA());
		return jdbcTemplate.update("DELETE FROM wisp_params WHERE keypa = :keypa",params);
	}

}
