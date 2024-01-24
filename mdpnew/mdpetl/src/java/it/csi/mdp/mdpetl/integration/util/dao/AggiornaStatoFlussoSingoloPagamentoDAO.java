/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


public class AggiornaStatoFlussoSingoloPagamentoDAO extends BaseDAO {

	public AggiornaStatoFlussoSingoloPagamentoDAO ( Integer idFlusso, String iuv ) {
		setStatementMapper ( new GenericObjectArrayStatementMapper (
						idFlusso, iuv
		) );
	}

	@Override
	public String componiQuery () {
		return " update flusso_singolo_pagamento set esito_ultimo_invio_a_fruitore = 'OK', data_ultimo_invio_a_fruitore = now() where id_flusso = ? and iuv=?";
	}
}
