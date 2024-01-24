/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.common;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.enumeration.ColumnNameNotificaPagamentoEnum;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTNotificaPagamentoLight;

public class EpaypaTNotificaPagamentoCommon {
	@SuppressWarnings("rawtypes")
	static public Path getColumnPath(Root<EpaypaTNotificaPagamentoLight> root, ColumnNameNotificaPagamentoEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			case ID_NOTIFICA_PAGAMENTO:
				columnPath = root.get("idNotificaPagamento");
				break;
			case ID_FLUSSO:
				columnPath = root.get("idFlusso");
				break;
			case ID_POSIZIONE_DEBITORIA:
				columnPath = root.get("idPosizioneDebitoria");
				break;
			case IUV:
				columnPath = root.get("iuv");
				break;
			case IMPORTO_PAGATO:
				columnPath = root.get("importoPagato");
				break;
			case DESCRIZIONE_CAUSALE_VERSAMENTO:
				columnPath = root.get("desCausaleVersamento");
				break;
			case DATA_ESITO_PAGAMENTO:
				columnPath = root.get("dtEsitoPagamento");
				break;
			case ID_FISCALE_DEBITORE:
				columnPath = root.get("epaypaTSoggettoDebitore").get("idUnivocoFiscale");
				break;
			case COGNOME_OR_RAGIONE_SOCIALE_DEBITORE:
				columnPath = root.get("epaypaTSoggettoDebitore").get("cognomeRagSoc");
				break;
			case NOME_DEBITORE:
				columnPath = root.get("epaypaTSoggettoDebitore").get("nome");
				break;
			case CONCAT_COGNOME_NOME_OR_RAGIONE_SOCIALE_DEBITORE:
				// N.B. ritorna il cognome o la ragione sociale, non anche il nome. La ricombinazione cognome-nome o ragione sociale si fa programmaticamente
				columnPath = root.get("epaypaTSoggettoDebitore").get("cognomeRagSoc");
				break;
			}
		}

		return columnPath;
	}

}
