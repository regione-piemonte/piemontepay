/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;


import it.csi.mdp.mdpetl.dto.InformazioniServizio;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciInformazioniServizioDAO extends BaseDAO {

	public  InserisciInformazioniServizioDAO(InformazioniServizio infoServizio) throws SerialException, SQLException {
	
		setStatementMapper(new GenericObjectArrayStatementMapper(
				infoServizio.getCodiceLingua(),
				infoServizio.getDescrizioneServizio(),
				infoServizio.getDisponibilitaServizio(),
				infoServizio.getLimitazioniServzio(),
				infoServizio.getUrlInformazioniServizio(),
				infoServizio.getIdinformativapsp()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO informazioni_servizio(codice_lingua, descrizione_servizio, disponibilita_servizio, limitazioni_servizio, url_informazioni_canale, id_informativa_psp)VALUES (?,?,?,?,?,?)";
	}

}
