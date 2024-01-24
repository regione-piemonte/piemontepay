/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

//import it.csi.bilsrvrp.cmpsrvrp.dto.bilancio.capitoli.entity.Capitolo;
import it.csi.mdp.mdpetl.dto.InformativePSP;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.UtilDate;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciInformativePSPDAO extends BaseDAO {

	//private int size;

	public  InserisciInformativePSPDAO(InformativePSP infoPsp) throws SerialException, SQLException {
	//public  InserisciInformativePSPDAO(List<InformativePSP> l ) throws SerialException, SQLException {
//		this.size = l.size();
//		
//		Object[] params = new Object[size * 17];
//		
//		int i=0;
//		for(InformativePSP e: l){
//			params[i++] = e.getIdentificativoFlusso();
//			params[i++] = e.getIdentificativoPSP();
//			params[i++] = e.getRagioneSociale();
//			params[i++] = e.getDataPubblicazione();
//			params[i++] = e.getDataInizioValidita();
//			params[i++] = e.getUrlInformazioniPSP();
//			params[i++] = e.getStornoPagamento();
//			params[i++] = e.getIdentificativoIntermediario();
//			params[i++] = e.getIdentificativoCanale();
//			params[i++] = e.getTipoVersamento();
//			params[i++] = e.getModelloPagamento();
//			params[i++] = e.getPriorita();
//			params[i++] = e.getDisponibilitaServizio();
//			params[i++] = e.getDescrizioneServizio();
//			params[i++] = e.getCondizioniEconomicheMassime();
//			params[i++] = e.getUrlInformazioniCanale();
//			params[i++] = e.getDataInserimento();
//
//		}
//		
//		setStatementParams(params);
		
		setStatementMapper(new GenericObjectArrayStatementMapper(
				infoPsp.getIdentificativoFlusso(),
				infoPsp.getIdentificativoPSP(),
				infoPsp.getRagioneSociale(),
				UtilDate.convertStringToTimestamp(infoPsp.getDataPubblicazione()),
				UtilDate.convertStringToTimestamp(infoPsp.getDataInizioValidita()),
				infoPsp.getUrlInformazioniPSP(),
				infoPsp.getStornoPagamento(),
				infoPsp.getIdentificativoIntermediario(),
				infoPsp.getIdentificativoCanale(),
				infoPsp.getTipoVersamento(),
				infoPsp.getModelloPagamento(),
				infoPsp.getPriorita(),
				infoPsp.getDisponibilitaServizio(),
				infoPsp.getDescrizioneServizio(),
				infoPsp.getCondizioniEconomicheMassime(),
				infoPsp.getUrlInformazioniCanale(),
				//infoPsp.getDatainserimento(),
				infoPsp.getOrdinamento(),
				infoPsp.getStatoinserimento(),
				infoPsp.getOrigine()
			));
	}
	

	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" INSERT INTO  informativa_psp ");
		strSql.append(" (");
		strSql.append(" identificativoFlusso ,");
		strSql.append(" identificativoPSP ,");
		strSql.append(" ragioneSociale ,");
		strSql.append(" dataPubblicazione ,");
		strSql.append(" dataInizioValidita ,");
		strSql.append(" urlInformazioniPSP ,");
		strSql.append(" stornoPagamento ,");
		strSql.append(" identificativoIntermediario ,");
		strSql.append(" identificativoCanale ,");
		strSql.append(" tipoVersamento ,");
		strSql.append(" modelloPagamento ,");
		strSql.append(" priorita ,");
		strSql.append(" disponibilitaServizio ,");
		strSql.append(" descrizioneServizio ,");
		strSql.append(" condizioniEconomicheMassime ,");
		strSql.append(" urlInformazioniCanale, ");
		strSql.append(" datainserimento, ");		
		strSql.append(" ordinamento , ");
		strSql.append(" statoinserimento ,");
		strSql.append(" origine");
		strSql.append(" )values( ");
		strSql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,? ");	
		strSql.append(" )");	
		
//		String methodName="";
//		log.info(methodName, "sql " + strSql.substring(0, strSql.length()-1).toString());
//		return strSql.substring(0, strSql.length()-1).toString();
		return strSql.toString();
		 
	}

}
