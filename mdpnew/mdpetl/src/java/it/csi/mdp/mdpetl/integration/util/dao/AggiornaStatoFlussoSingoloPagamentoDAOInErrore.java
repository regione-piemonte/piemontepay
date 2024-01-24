/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;


public class AggiornaStatoFlussoSingoloPagamentoDAOInErrore extends BaseDAO {

	private final String messaggioErrore;

	public AggiornaStatoFlussoSingoloPagamentoDAOInErrore ( Integer idFlusso, String iuv, String messaggioErrore ) {
		this.messaggioErrore = messaggioErrore.substring ( 0, Math.min ( messaggioErrore.length (), 255 ) );
		setStatementMapper ( new GenericObjectArrayStatementMapper (
						idFlusso, iuv
		) );
	}

	@Override public String componiQuery () {
		return " update flusso_singolo_pagamento set esito_ultimo_invio_a_fruitore = 'KO', data_ultimo_invio_a_fruitore = now() " +
						" and msg_ultimo_esito_invio_a_fruitore = '" + messaggioErrore + "' where id_flusso = ? and iuv=?";
	}
}
