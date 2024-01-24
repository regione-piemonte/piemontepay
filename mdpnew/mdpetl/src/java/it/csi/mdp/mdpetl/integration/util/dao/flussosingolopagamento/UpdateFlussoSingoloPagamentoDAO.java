/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao.flussosingolopagamento;

import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.integration.util.dao.BaseDAO;


@SuppressWarnings ( "rawtypes" )
public class UpdateFlussoSingoloPagamentoDAO extends BaseDAO {

	private final FlussoSingoloPagamento flusso;

	public UpdateFlussoSingoloPagamentoDAO ( FlussoSingoloPagamento flusso ) {
		this.flusso = flusso;
	}

	@Override
	public String componiQuery () {
		Integer id = flusso.getIdFlusso ();
		String esito = flusso.getEsitoUltimoInvioAFruitore ();
		String msg = flusso.getMsgUltimoEsitoInvioAFruitore ();
		return " update flusso_singolo_pagamento set data_ultimo_invio_a_fruitore = now(), " +
						"esito_ultimo_invio_a_fruitore = '" + esito + "'," +
						"msg_ultimo_esito_invio_a_fruitore = '" + msg + "'," +
						"where id = " + id;
	}
}
