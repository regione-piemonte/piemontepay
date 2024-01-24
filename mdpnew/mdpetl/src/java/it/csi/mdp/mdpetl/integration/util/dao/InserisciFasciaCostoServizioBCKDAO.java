/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

//import it.csi.bilsrvrp.cmpsrvrp.dto.bilancio.capitoli.entity.Capitolo;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciFasciaCostoServizioBCKDAO extends BaseDAO {

	Integer gg;
	public  InserisciFasciaCostoServizioBCKDAO(Integer giorniBck) throws SerialException, SQLException {
		gg = giorniBck;
	}
	

	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append("INSERT INTO fascia_costo_servizio_bck (importo_massimo_fasci,costo_fisso,valore_commissione,id_informativa_psp, data_validita, valido) " +
				" SELECT importo_massimo_fasci,costo_fisso,valore_commissione,id_informativa_psp, data_validita, valido ")
			.append(" FROM fascia_costo_servizio WHERE data_validita < CURRENT_TIMESTAMP - INTERVAL ' "+gg+" days'" );
		log.info("", strSql.toString());
		return strSql.toString();
		 
	}
}

