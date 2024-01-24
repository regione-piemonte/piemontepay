/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

//import it.csi.bilsrvrp.cmpsrvrp.dto.bilancio.capitoli.entity.Capitolo;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciInformazioniServizioBCKDAO extends BaseDAO {

	Integer gg;
	public  InserisciInformazioniServizioBCKDAO(Integer giorniBck) throws SerialException, SQLException {
		gg = giorniBck;
	}
	

	@Override
	public String componiQuery() {
		StringBuffer strSql = new StringBuffer();
		strSql.append("INSERT INTO informazioni_servizio_bck (codice_lingua, descrizione_servizio, disponibilita_servizio, limitazioni_servizio, url_informazioni_canale, ")
			.append("id_informativa_psp, data_validita, valido) SELECT codice_lingua, descrizione_servizio, disponibilita_servizio, limitazioni_servizio, ")
			.append("url_informazioni_canale,id_informativa_psp, data_validita, valido FROM informazioni_servizio WHERE ")
		    .append(" data_validita < CURRENT_TIMESTAMP - INTERVAL ' "+gg+" days'" );
		log.info("", strSql.toString());
		return strSql.toString();
		 
	}
}

