/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import it.csi.mdp.mdpetl.dto.CodaInvioRT;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

public class InserisciRtCodaInvioDAO extends BaseDAO<CodaInvioRT> {

	public  InserisciRtCodaInvioDAO(CodaInvioRT codaRt) throws SerialException, SQLException {
		
		setStatementMapper(new GenericObjectArrayStatementMapper(
				codaRt.getIuv(),
				codaRt.getTransactionId(),
				codaRt.getApplicationId(),
				codaRt.getContatoreTentativi(),
				codaRt.getDataTentativi(),
				codaRt.getDataInizioTentativi(),
				codaRt.getNumGiorniTentativiKo(),
				codaRt.getUltimoEsitoFruitore(),
				codaRt.getDataUltimaModifica()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO rt_coda_invio ("
				+ "iuv,"
				+ " transaction_id, "
				+ "application_id,  "
				+ "contatore_tentativi, "
				+ " data_tentativi, "
				+ "data_inizio_tentativi, "
				+ "num_giorni_tentativi_ko, "
				+ "ultimo_esito_fruitore, "
				+ "data_ultima_modifica,"
				+ "data_inserimento ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , now())";
	}

}
