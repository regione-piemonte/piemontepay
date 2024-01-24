/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.common;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import it.csi.epay.epaypaweb.enumeration.ColumnNameAggPosizioneDebitoriaEnum;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTAggPosizioneDebitoriaLight;

public class EpaypaTAggPosizioneDebitoriaCommon {
	@SuppressWarnings("rawtypes")
	static public Path getColumnPath(Root<EpaypaTAggPosizioneDebitoriaLight> root, ColumnNameAggPosizioneDebitoriaEnum columnEnum) {
		Path columnPath = null;

		if (columnEnum != null) {
			switch (columnEnum) {
			case ID_AGG_POSIZIONE_DEBITORIA:
				columnPath = root.get("idAggPosizioneDebitoria");
				break;
			case ID_FLUSSO:
				columnPath = root.get("idFlusso");
				break;
			case TIPO_AGG_POSIZIONE_DEBITORIA:
				columnPath = root.get("epaypaDTipoAggPosizioneDebitoria").get("descrizione");
				break;
			case ID_POSIZIONE_DEBITORIA_ESTERNA:
				columnPath = root.get("idPosizioneDebitoriaEst");
				break;
			case MOTIVAZIONE:
				columnPath = root.get("motivazione");
				break;
			case CODICE_AVVISO:
				columnPath = root.get("codiceAvviso");
				break;
			case CODICE_ESITO:
				columnPath = root.get("codEsito");
				break;
			case DETTAGLIO_ESITO:
				columnPath = root.get("dettaglioEsito");
				break;
			case CONCAT_CODICEESITO_DETTAGLIOESITO:
				// N.B. ritorna il codice esito, non anche il dettaglio esito. La ricombinazione codice esito e dettaglio esito si fa programmaticamente
				columnPath = root.get("codEsito");
				break;
			}
		}

		return columnPath;
	}

}
