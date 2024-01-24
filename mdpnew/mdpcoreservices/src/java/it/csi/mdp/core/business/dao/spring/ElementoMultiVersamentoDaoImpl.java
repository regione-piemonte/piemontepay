/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.core.business.dao.spring;

import it.csi.mdp.core.business.dao.ElementoMultiVersamentoDao;
import it.csi.mdp.core.business.dao.WispParamsDao;
import it.csi.mdp.core.business.dto.ElementoMultiversamento;
import it.csi.mdp.core.business.dto.ParametroWisp;
import it.csi.mdp.core.business.dto.multicarrello.DatiSingoloVersamentoRPT;
import it.csi.mdp.core.business.exceptions.DaoException;
import it.csi.mdp.core.util.LoggerUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ElementoMultiVersamentoDaoImpl extends AbstractDAO implements ParameterizedRowMapper<ElementoMultiversamento>, ElementoMultiVersamentoDao {
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

	//TAG_PPU - 2019 - RDI 004 - RDI 005 - START
	public void insert(ElementoMultiversamento toInsert) throws DaoException {

		LoggerUtil.begin();
		LoggerUtil.dumpObject("DA INSERIRE ",toInsert);
		
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "INSERT INTO elemento_multiversamento(application_id, transaction_id, posizione, iuv, xml_elemento, modellopagamento, insert_date)" +
				" values (:application_id, :transaction_id, :posizione, :iuv, :xml, :modellopagamento, now())";
		
		params.addValue("application_id",toInsert.getApplicationId(),java.sql.Types.VARCHAR);
		params.addValue("transaction_id",toInsert.getTransactionId(),java.sql.Types.VARCHAR);
		params.addValue("posizione",toInsert.getPosizione(),java.sql.Types.INTEGER);
		params.addValue("iuv",toInsert.getIuv(),java.sql.Types.VARCHAR);
		params.addValue("xml",toInsert.getXmlElemento(),java.sql.Types.VARCHAR);
		params.addValue("modellopagamento",toInsert.getModelloPagamento(),java.sql.Types.VARCHAR);
		
		LoggerUtil.debug(mysql);
		LoggerUtil.dumpObject ( "PARAMETRI", params );
		
		LoggerUtil.end();
		jdbcTemplate.update(mysql,params);
		
		if (CollectionUtils.isNotEmpty(toInsert.getElencoSingoliPagamentiSpacchettati())) {

		    LoggerUtil.debug("SINGOLI PAGAMENTI SPACCHETTATI PRESENTI");
			
		    List<ElementoMultiversamento> appenaInserito = find(toInsert);
			ElementoMultiversamento inserito = appenaInserito.get(0);
			
			//TAG_PPU - 2019 - RDI 004 - RDI 005 - START
			//CSI_PAG-1837 AGGIUNTO 1 NUOVO CAMPO APPLICATION_DI ALLA FINE
			String insertDettaglio = "INSERT INTO dati_singolo_versamento(posizione,importoSingoloVersamento,commissioneCaricoPA,credenzialiPagatore,causaleVersamento,"+
									"datiSpecificiRiscossione,tipoBollo,hashDocumento,provinciaResidenza, multi_id,annoAccertamento,numeroAccertamento,applicationId)" + 
									"VALUES (" +
									":posizione,:importoSingoloVersamento,:commissioneCaricoPA,:credenzialiPagatore,:causaleVersamento,"+
									":datiSpecificiRiscossione,:tipoBollo,:hashDocumento,:provinciaResidenza, :multi_id,:annoAccertamento,:numeroAccertamento,:applicationId)";
			//TAG_PPU - 2019 - RDI 004 - RDI 005 - STOP
			
			int pos = 1;
			for (DatiSingoloVersamentoRPT singoloagamentoDaInserire : toInsert.getElencoSingoliPagamentiSpacchettati()) {
				params = new MapSqlParameterSource();
				params.addValue("posizione",pos,java.sql.Types.INTEGER);
				params.addValue("importoSingoloVersamento",singoloagamentoDaInserire.getImportoSingoloVersamento(),java.sql.Types.DOUBLE);
				params.addValue("commissioneCaricoPA",singoloagamentoDaInserire.getCommissioneCaricoPA(),java.sql.Types.DOUBLE);

				String cred = StringUtils.substring ( singoloagamentoDaInserire.getCredenzialiPagatore(), 0, 35 );
				params.addValue("credenzialiPagatore",cred,java.sql.Types.VARCHAR);
				
				params.addValue("causaleVersamento",singoloagamentoDaInserire.getCausaleVersamento(),java.sql.Types.VARCHAR);
				params.addValue("datiSpecificiRiscossione",singoloagamentoDaInserire.getDatiSpecificiRiscossione(),java.sql.Types.VARCHAR);
				params.addValue("tipoBollo",singoloagamentoDaInserire.getDatiMarcaBolloDigitale() != null ? singoloagamentoDaInserire.getDatiMarcaBolloDigitale().getTipoBollo() : null,java.sql.Types.VARCHAR);
				params.addValue("hashDocumento",singoloagamentoDaInserire.getDatiMarcaBolloDigitale() != null ? singoloagamentoDaInserire.getDatiMarcaBolloDigitale().getHashDocumento() : null,java.sql.Types.VARCHAR);
				params.addValue("provinciaResidenza",singoloagamentoDaInserire.getDatiMarcaBolloDigitale() != null ? singoloagamentoDaInserire.getDatiMarcaBolloDigitale().getProvinciaResidenza() : null,java.sql.Types.VARCHAR);
				params.addValue("multi_id",inserito.getId(),java.sql.Types.INTEGER);
				
				//TAG_PPU - 2019 - RDI 004 - RDI 005 - START
				if(singoloagamentoDaInserire.getDatiAccertamento() != null) {
				    params.addValue("annoAccertamento",  singoloagamentoDaInserire.getDatiAccertamento().getAnnoAccertamento(),java.sql.Types.INTEGER);
				    params.addValue("numeroAccertamento",singoloagamentoDaInserire.getDatiAccertamento().getNumeroAccertamento(),java.sql.Types.INTEGER);
	                LoggerUtil.debug("annoAccertamento:" + singoloagamentoDaInserire.getDatiAccertamento().getAnnoAccertamento());
	                LoggerUtil.debug("numeroAccertamento:" + singoloagamentoDaInserire.getDatiAccertamento().getNumeroAccertamento());
				} else {
                    params.addValue("annoAccertamento",  null,java.sql.Types.INTEGER);
                    params.addValue("numeroAccertamento",null,java.sql.Types.INTEGER);
				}
                //TAG_PPU - 2019 - RDI 004 - RDI 005 - STOP
				//CSI_PAG-1837 AGGIUNTO 1 NUOVO CAMPO APPLICATION_DI ALLA FINE
				params.addValue("applicationId",inserito.getApplicationId(),java.sql.Types.VARCHAR);
				
				LoggerUtil.debug(insertDettaglio);
		        LoggerUtil.dumpObject ( "PARAMETRI", params );
		        
		        jdbcTemplate.update(insertDettaglio, params);
			}
		}
		
		
	}
	//TAG_PPU - 2019 - RDI 004 - RDI 005 - STOP
	
	public int update(ElementoMultiversamento toUpdate) throws DaoException {
		return 0;
	}

	public List<ElementoMultiversamento> find(ElementoMultiversamento filtro) throws DaoException {
		LoggerUtil.begin();
		LoggerUtil.dumpObject("FILTRO ",filtro);
		
		MapSqlParameterSource params = new MapSqlParameterSource();

		String mysql = "select * from elemento_multiversamento where 1=1 ";
		
		if (StringUtils.isNotEmpty(filtro.getIuv())) {
			mysql += " and iuv = :iuv ";
			params.addValue("iuv",filtro.getIuv(),java.sql.Types.VARCHAR);
		}
		
		
		if (StringUtils.isNotEmpty(filtro.getTransactionId())) {
			mysql += " and transaction_id = :transaction_id ";
			params.addValue("transaction_id",filtro.getTransactionId(),java.sql.Types.VARCHAR);
		}
		
		
		LoggerUtil.debug(mysql);
		LoggerUtil.end();
		return jdbcTemplate.query(mysql, params, this);
	}

	public ElementoMultiversamento mapRow(ResultSet rs, int arg1) throws SQLException {
		ElementoMultiversamento riga = new ElementoMultiversamento();
		riga.setId(rs.getInt("id"));
		riga.setApplicationId(rs.getString("application_id"));
		riga.setTransactionId(rs.getString("transaction_id"));
		riga.setIuv(rs.getString("iuv"));
		riga.setPosizione(rs.getInt("posizione"));
		riga.setXmlElemento(rs.getString("xml_elemento"));
		riga.setModelloPagamento(rs.getString("modelloPagamento"));
		
		return riga;
	}


	public int delete(ParametroWisp filtro) throws DaoException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("keypa", filtro.getKeyPA());
		return jdbcTemplate.update("DELETE FROM wisp_params WHERE keypa = :keypa",params);
	}


	public int delete(ElementoMultiversamento filtro) throws DaoException {
		return 0;
	}

}
