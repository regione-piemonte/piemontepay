/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


@SuppressWarnings ( "rawtypes" )
public class AggiornaFlussoSingoloPagamentoDAO extends BaseDAO {

	public AggiornaFlussoSingoloPagamentoDAO ( Integer id ) {
		setStatementMapper ( new GenericObjectArrayStatementMapper (
						id
		) );
	}

	@Override public String componiQuery () {
		return " update flusso_singolo_pagamento set data_ultimo_invio_a_fruitore = now(), esito_ultimo_invio_a_fruitore = 'OK' where id = ?";
	}
}
