/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


public class AggiornaStatoReceiptInviataDAO extends BaseDAO {

	public AggiornaStatoReceiptInviataDAO ( Integer id ) {
		setStatementMapper ( new GenericObjectArrayStatementMapper (
						id
		) );
	}

	@Override
	public String componiQuery () {
		return " update mdp_receipt set pagamento_inviato = true, data_invio_fruitore = now() where id = ?";
	}
}
