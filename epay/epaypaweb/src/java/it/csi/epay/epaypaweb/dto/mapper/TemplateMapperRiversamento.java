/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.mapper;

import java.util.Date;

import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.RiversamentoDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;
import it.csi.epay.epaypaweb.util.*;

public class TemplateMapperRiversamento implements TemplateMapper<RiversamentoDto> {

	//@formatter:off
	@Override
	public Object getValue(FlussoDto head, RiversamentoDto item, CampoFlussoEnum campoFlusso) {
		if (item != null && campoFlusso != null) {
			switch (campoFlusso) {
				case FREND_HEAD_ID_LOTTO: return head.getIdMessaggio();
				case FREND_HEAD_ID_FLUSSO_RENDICONTAZIONE: return head.getIdFlussoRendicontazione();
				case FREND_HEAD_DATA_RICEZIONE: return head.getDataInserimento();
				case FREND_HEAD_NUM_TOTALE_PAGAMENTI: return head.getNumeroElementi();
				case FREND_HEAD_IMPORTO_TOTALE_PAGAMENTI: return head.getImportoTotale();
				case FREND_HEAD_DATA_ORA_CREAZIONE_FLUSSO: return head.getDataOraCreazioneFlusso();
				case FREND_HEAD_ID_UNIVOCO_REGOLAMENTO: return head.getIdUnivocoRegolamento();
				case FREND_HEAD_DATA_REGOLAMENTO: return head.getDataRegolamento();
				case FREND_HEAD_TIPO_MITTENTE: return head.getIdAndDesTipoMittente();
				case FREND_HEAD_COD_MITTENTE: return head.getCodIdUnivocoMittente();
				case FREND_HEAD_DENOMINAZIONE_MITTENTE: return head.getDenominazioneMittente();
				case FREND_HEAD_COD_BIC_PSP: return head.getCodBicPsp();
				case FREND_ITEM_IUV: return item.getIUV();
				case FREND_ITEM_IUR: return item.getIUR();
				case FREND_ITEM_INDICE_DATI_RT: return item.getIndicePagamento();
				case FREND_ITEM_IMPORTO_PAGATO: return item.getImportoPagato();
				case FREND_ITEM_COD_ESITO: return item.getCodAndDesEsito();
				case FREND_ITEM_DATA_ESITO: return item.getDataEsito();
				case FREND_ITEM_STATO_FLUSSO_AGGREGATO: return head.getStatoFlussoAggregato();
				case FREND_ITEM_DATI_AGGIUNTIVI_FLUSSO: return head.getDatiAggiuntiviStato();
				case FREND_ITEM_DATA_ORA_INZIO_ELABORAZIONE_REPORT: return Util.date2strdatetime(new Date());
				default: return null;
			}
		} else
			return null;
	}
	//@formatter:on
}
