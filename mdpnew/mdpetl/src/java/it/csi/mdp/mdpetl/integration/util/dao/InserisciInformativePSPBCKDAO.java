/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

//import it.csi.bilsrvrp.cmpsrvrp.dto.bilancio.capitoli.entity.Capitolo;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciInformativePSPBCKDAO extends BaseDAO {

	Integer gg;
	public  InserisciInformativePSPBCKDAO(Integer giorniBck) throws SerialException, SQLException {
		gg = giorniBck;
	}
	

	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append(" INSERT INTO  informativa_psp_bck ");
		strSql.append(" (");
		strSql.append(" idinformativapsp ,");		
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
		
		
		//strSql.append(" )values( ");
		//strSql.append(" ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ");	
		strSql.append(" )");	
		
		strSql.append(" SELECT  ");
		strSql.append(" idinformativapsp ,");
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
		strSql.append(" origine ");
		strSql.append(" FROM ");
		strSql.append(" informativa_psp ");
		strSql.append(" WHERE ");
		//strSql.append(" 100 = ? " );
		strSql.append(" datainserimento < CURRENT_TIMESTAMP - INTERVAL ' "+gg+" days'" );
		log.info("", strSql.toString());
		return strSql.toString();
		 
	}
}

