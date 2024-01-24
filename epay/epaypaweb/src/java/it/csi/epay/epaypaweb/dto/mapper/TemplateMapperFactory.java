/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.mapper;

import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;

public class TemplateMapperFactory {

	//@formatter:off
	public static TemplateMapper<? extends ElementoFlussoDto> getMapper(TipoFlussoEnum tipoFlusso) {
		if (tipoFlusso != null) {
			switch (tipoFlusso) {
			case AGGIORNAMENTO_POSIZIONI_DEBITORIE: return new TemplateMapperPosizioneDebitoriaDaAggiornare();
			case AVVISI_SCADUTI: return new TemplateMapperAvvisoScaduto();
			case LISTE_DI_CARICO: return new TemplateMapperPosizioneDebitoria();
			case NOTIFICHE_PAGAMENTO: return new TemplateMapperNotificaPagamento();
			case TRASMETTI_FLUSSO_RENDICONTAZIONE: return new TemplateMapperRiversamento();
			default: return null;
			}
		} else
			return null;
	}
	//@formatter:on
}
