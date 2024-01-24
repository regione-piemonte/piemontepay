/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;


import it.csi.mdp.mdpetl.dto.FasciaCostoServizio;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

public class InserisciFasciaCostoServizioDAO extends BaseDAO {

	public  InserisciFasciaCostoServizioDAO(FasciaCostoServizio fascia) throws SerialException, SQLException {
	
		setStatementMapper(new GenericObjectArrayStatementMapper(
				fascia.getImportoMassimoFascia(),
				fascia.getCostoFisso(),
				fascia.getValoreCommissione(),
				fascia.getIdinformativapsp()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO fascia_costo_servizio(importo_massimo_fasci,costo_fisso,valore_commissione,id_informativa_psp) VALUES (?,?,?,?)";
	}

}
