/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.integration.util.dao;

import it.csi.mdp.mdpetl.dto.DatiRichiestaReceipt;
import it.csi.mdp.mdpetl.integration.util.dao.sm.GenericObjectArrayStatementMapper;
import it.csi.mdp.mdpetl.util.LogUtil;

public class EstraiReceiptPerIuvDominioDAO extends BaseDAO<DatiRichiestaReceipt> {
	 LogUtil log = new LogUtil(EstraiReceiptPerIuvDominioDAO.class);
	 
	public  EstraiReceiptPerIuvDominioDAO(String iuv, String identificativoDominio) {
		setStatementMapper(new GenericObjectArrayStatementMapper(iuv, identificativoDominio));
		setResultSetExtractor(new DatiRichiestaReceiptExtractor());
		System.out.println("iuv in EstraiReceiptPerIuvDominioDAO"+iuv+" - dominio:"+identificativoDominio);

	}

	@Override
	public String componiQuery() {
		
		return "select id, application_id,transaction_id, data_ora_insert as insert_date, data_ora_update as last_update, "
		+ " payment_method  as auth_soggetto, payment_datetime as data_msg_richiesta, psp_comany_name as id_canale, id_psp as id_interm_psp," 
		+" receipt_id as id_msg_richiesta, id_psp, "		
		+ " id_pa as identificativo_dominio, id_broker_pa as identificativo_intermediario_pa, "
		+ " id_station as identificativo_stazione_intermediario_pa, xml_receipt as rpt_xml, creditor_referenceid as iuv," 
		+ " 3 as id_stati_rpt, receipt_id as codice_contesto_pagamento, null as da_inviare,"
		+ " data_invio_fruitore data_invio,"
		+ " false as secondario, true as intermediato "
		+ " from mdp_receipt mr "
		+ " where creditor_referenceid = ? " 
		+ " and "
		+ " not exists (select * from mdp_getpayment mg where mg.creditor_referenceid = mr.creditor_referenceid)"
		+ " and iuv_sconosciuto = false"
		+ " and id_pa = ?" //e codice fiscale ente (idPA) = identificativoDominio
		+ " order by insert_date desc "
		+ " limit 1";
	}
}
