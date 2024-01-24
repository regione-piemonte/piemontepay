/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.FlussoSingoloPagamento;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;

public class InserisciFlussoSingoloPagamentoDAO extends BaseDAO<FlussoSingoloPagamento> {

	public InserisciFlussoSingoloPagamentoDAO ( FlussoSingoloPagamento singoloPagamento ) {
		
		setStatementMapper(new GenericObjectArrayStatementMapper(
				singoloPagamento.getIdFlusso(), 
				singoloPagamento.getIuv(), 
				singoloPagamento.getIdentificativoUnivocoRiscossione(), 
				singoloPagamento.getSingoloImportoPagato(), 
				singoloPagamento.getCodiceEsitoSingoloPagamento(), 
				singoloPagamento.getApplicationId(),
				singoloPagamento.getDataEsitoSingoloPagamento(),
			singoloPagamento.getIndiceDatiSingoloPagamento (),
			singoloPagamento.getEsitoUltimoInvioAFruitore (),
			singoloPagamento.getDataUltimoInvioAFruitore (),
			singoloPagamento.getMsgUltimoEsitoInvioAFruitore ()
			));
	}
	

	@Override
	public String componiQuery() {
		return "INSERT INTO flusso_singolo_pagamento(  id_flusso, " + 
		    "  iuv, " + 
		    "  identificativounivocoriscossione, " + 
		    "  singoloimportopagato, " + 
		    "  codiceesitosingolopagamento, " + 
		    "  application_id, " + 
		    "  dataesitosingolopagamento, " + 
		    "  datainserimento, " + 
		    "  datamodifica, " + 
		    "  indicedatisingolopagamento, " + 
		    "  esito_ultimo_invio_a_fruitore, " + 
		    "  data_ultimo_invio_a_fruitore, " + 
		    "  msg_ultimo_esito_invio_a_fruitore)VALUES (?, ?, ?, ?, ?, ?, ?, now(), now(), ?, ?, ?, ?);";
	}

}
