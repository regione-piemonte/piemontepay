/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.common;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.enumeration.ColumnNamePosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoria;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTPosizioneDebitoriaLight;

public class EpaypaTPosizioneDebitoriaCommon {
	@SuppressWarnings("rawtypes")
	static public Path getColumnPath(Root<EpaypaTPosizioneDebitoriaLight> root, ColumnNamePosizioneDebitoriaEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			case ID_POSIZIONE_DEBITORIA:
				columnPath = root.get("idPosizioneDebitoria");
				break;
			case ID_FLUSSO:
				columnPath = root.get("idFlusso");
				break;
			case IUV:
				columnPath = root.get("iuv");
				break;
			case IMPORTO_TOTALE:
				columnPath = root.get("importoTotale");
				break;
			case DESCRIZIONE_CAUSALE_VERSAMENTO:
				columnPath = root.get("desCausaleVersamento");
				break;
			case DATA_SCADENZA:
				columnPath = root.get("dtScadenza");
				break;
			case ID_FISCALE_DEBITORE:
				columnPath = root.get("epaypaTSoggettoDebitore").get("idUnivocoFiscale");
				break;
			}
		}

		return columnPath;
	}


	@SuppressWarnings("rawtypes")
	static public Path getColumnPathPD(Root<EpaypaTPosizioneDebitoria> root, ColumnNamePosizioneDebitoriaEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			//			case ID_POSIZIONE_DEBITORIA:
			//				columnPath = root.get("idPosizioneDebitoria");
			//				break;
			//			case ID_FLUSSO:
			//				columnPath = root.get("idFlusso");
			//				break;
			case IUV:
				columnPath = root.get("iuv");
				break;
				//			case IMPORTO_TOTALE:
				//				columnPath = root.get("importoTotale");
				//				break;
				//			case DESCRIZIONE_CAUSALE_VERSAMENTO:
				//				columnPath = root.get("desCausaleVersamento");
				//				break;
			case COGNOME:
				columnPath = root.get("epaypaTSoggettoDebitore").get("cognomeRagSoc");
				break;
			case ID_FISCALE_DEBITORE:
				columnPath = root.get("epaypaTSoggettoDebitore").get("idUnivocoFiscale");
				break;
			}
		}

		return columnPath;
	}

}
