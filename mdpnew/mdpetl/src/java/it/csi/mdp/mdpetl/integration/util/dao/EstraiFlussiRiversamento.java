/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.core.business.dto.FlussoRiversamento;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang.StringUtils;

public class EstraiFlussiRiversamento extends BaseDAO<List<FlussoRiversamento>> {
	 LogUtil log = new LogUtil(EstraiFlussiRiversamento.class);
	 private FiltroFlussiFlagInvioEnum filtro;
	
	public  EstraiFlussiRiversamento(FiltroFlussiFlagInvioEnum filtro) throws SerialException, SQLException {
		this.filtro = filtro;
		setStatementMapper(new GenericObjectArrayStatementMapper());
		setResultSetExtractor(new FlussoRiversamentoExtractor());
	}

	@Override
	public String componiQuery() {
		String query = "";
		
		if (null == filtro || StringUtils.isEmpty(filtro.getNomeAttributo())) {
			log.error("componiQuery", "Stato invio flusso non valorizzato");
		} else {
			switch (filtro) {
			
			case FLUSSO_BASE:
				query =
				"select distinct(fr.*) "
				+ "from flusso_riversamento fr "
				+ "join enti e on fr.identificativoistitutoricevente = e.partita_iva "
				+ "where fr.stato_invio_flusso_base = " + StatoInvioFlussoEnum.DA_INVIARE.getCodStato() + " "
				+ "and e.attivo = '1' "
				+ "and e.flag_invio_flusso_base = true"
				;
				break;
				
			case FLUSSO_ESTESO: 
				query = 
				"select distinct(fr.*) "
				+ "from flusso_riversamento fr "
				+ "join enti e on fr.identificativoistitutoricevente = e.partita_iva "
				+ "where fr.stato_invio_flusso_esteso = " + StatoInvioFlussoEnum.DA_INVIARE.getCodStato() + " "
				+ "and e.attivo = '1' "
				+ "and e.flag_invio_flusso_esteso = true"
				;
				break;
			default:
				log.error("componiQuery", String.format("Stato invio flusso [%s] non valorizzato", filtro.getNomeAttributo()));
			}
		}
		
		return query;
	}

}

class FlussoRiversamentoExtractor implements ResultSetExtractor<List<FlussoRiversamento>> {
	
	public List<FlussoRiversamento> extractData(ResultSet rs) throws SQLException {
		List<FlussoRiversamento> elencoFlussi = new ArrayList<FlussoRiversamento>();
		while(rs.next()){
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
			elencoFlussi.add(fr);
		}
		
		return elencoFlussi;
	}
	
}

