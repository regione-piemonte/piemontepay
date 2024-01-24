/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.InformativePSP;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

//import it.csi.bilsrvrp.cmpsrvrp.dto.bilancio.capitoli.entity.Capitolo;


public class ModificaInformativePSPDAO extends BaseDAO{


	
	
	public  ModificaInformativePSPDAO(InformativePSP infoPsp) throws SerialException, SQLException {
		setStatementMapper(new GenericObjectArrayStatementMapper(
				infoPsp.getIdentificativoFlusso(),
				//infoPsp.getIdentificativoPSP(),
				infoPsp.getRagioneSociale(),
				infoPsp.getDataPubblicazione(),
				infoPsp.getDataInizioValidita(),
				infoPsp.getUrlInformazioniPSP(),
				infoPsp.getStornoPagamento(),
//				infoPsp.getIdentificativoIntermediario(),
//				infoPsp.getIdentificativoCanale(),
//				infoPsp.getTipoVersamento(),
				infoPsp.getModelloPagamento(),
				infoPsp.getPriorita(),
				infoPsp.getDisponibilitaServizio(),
				infoPsp.getDescrizioneServizio(),
				infoPsp.getCondizioniEconomicheMassime(),
				infoPsp.getUrlInformazioniCanale(),
				infoPsp.getDatainserimento(),
				infoPsp.getIdentificativoPSP(),
				infoPsp.getIdentificativoIntermediario(),
				infoPsp.getIdentificativoCanale(),
				infoPsp.getTipoVersamento()

			));
		
	}
	

	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" update informativa_psp set ");
		strSql.append(" identificativoFlusso=? ,");
//		strSql.append(" identificativoPSP=? ,");
		strSql.append(" ragioneSociale=? ,");
		strSql.append(" dataPubblicazione=? ,");
		strSql.append(" dataInizioValidita=? ,");
		strSql.append(" urlInformazioniPSP=? ,");
		strSql.append(" stornoPagamento=? ,");
//		strSql.append(" identificativoIntermediario=? ,");
//		strSql.append(" identificativoCanale=? ,");
//		strSql.append(" tipoVersamento=? ,");
		strSql.append(" modelloPagamento=? ,");
		strSql.append(" priorita=? ,");
		strSql.append(" disponibilitaServizio=? ,");
		strSql.append(" descrizioneServizio=? ,");
		strSql.append(" condizioniEconomicheMassime=? ,");
		strSql.append(" urlInformazioniCanale=?, ");
		strSql.append(" dataInserimento=? ");
		strSql.append(" WHERE ");
		strSql.append("     identificativoPSP=? ");
		strSql.append(" AND identificativoIntermediario=? ");
		strSql.append(" AND identificativoCanale=? ");
		strSql.append(" AND tipoVersamento=? ");
		return strSql.toString();
	}
}
