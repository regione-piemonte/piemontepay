/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


@SuppressWarnings ( "rawtypes" )
public class AggiornaMDPGetPaymentDAO extends BaseDAO {

	public AggiornaMDPGetPaymentDAO ( Integer id ) {
		setStatementMapper ( new GenericObjectArrayStatementMapper (
						id
		) );
	}

	@Override public String componiQuery () {
		return " update mdp_getpayment set cod_stato_getpayment = 1, data_ora_invio = now() where id_getpayment = ?";
	}
}
