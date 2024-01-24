/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.mapper;

import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.NotificaPagamentoDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;

public class TemplateMapperNotificaPagamento implements TemplateMapper<NotificaPagamentoDto> {

	//@formatter:off
	public Object getValue(FlussoDto head, NotificaPagamentoDto item, CampoFlussoEnum campoFlusso) {
		if (item != null && campoFlusso != null) {
			switch (campoFlusso) {
				case NP_ANNO_DI_RIFERIMENTO: return item.getAnnoRiferimento();
				case NP_IUV: return item.getIUV();
				case NP_IMPORTO_PAGATO: return item.getImportoPagato();
				case NP_DATA_SCADENZA: return item.getDataScadenza();
				case NP_DESCRIZIONE_CAUSALE_VERSAMENTO: return item.getDesCausaleVersamento();
				case NP_DATA_ESITO_PAGAMENTO: return item.getDataEsitoPagamento();
				case NP_ID_POSIZIONE_DEBITORIA: return item.getIdPosizioneDebitoria();
				case NP_DATI_SPECIFICI_RISCOSSIONE: return item.getDatiSpecificiRiscossione();
				case NP_NOTE: return item.getNote();
				case NP_CODICE_AVVISO: return item.getCodAvviso();
				case NPSD_ID_UNIVOCO_FISCALE: return (item.getSoggettoDebitore() != null && item.getSoggettoDebitore().getIdUnivocoFiscale() != null) ? item.getSoggettoDebitore().getIdUnivocoFiscale() : null;
				case NPSD_TIPOLOGIA_SOGGETTO: return (item.getSoggettoDebitore() != null && item.getSoggettoDebitore().getTipologiaSoggettoEnum() != null) ? item.getSoggettoDebitore().getTipologiaSoggettoEnum().getId() : null;
				case NPSD_COGNOME_RAGIONE_SOCIALE:
					String sdres = null;
					if (item.getSoggettoDebitore() != null && item.getSoggettoDebitore().getTipologiaSoggettoEnum() != null) {
						switch (item.getSoggettoDebitore().getTipologiaSoggettoEnum()) {
						case PERSONA_FISICA:
							sdres = item.getSoggettoDebitore().getCognome();
							break;
						case PERSONA_GIURIDICA:
							sdres = item.getSoggettoDebitore().getRagioneSociale();
							break;
						}
					}
					return sdres;
				case NPSD_NOME: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getNome() : null;
				case NPSD_INDIRIZZO: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getIndirizzo() : null;
				case NPSD_CIVICO: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getCivico() : null;
				case NPSD_CAP: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getCap() : null;
				case NPSD_LOCALITA: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getLocalita() : null;
				case NPSD_PROVINCIA: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getProvincia() : null;
				case NPSD_NAZIONE: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getNazione() : null;
				case NPSD_EMAIL: return (item.getSoggettoDebitore() != null) ? item.getSoggettoDebitore().getEmail() : null;
				case NPSV_ID_UNIVOCO_FISCALE: return (item.getSoggettoVersante() != null && item.getSoggettoVersante().getIdUnivocoFiscale() != null) ? item.getSoggettoVersante().getIdUnivocoFiscale() : null;
				case NPSV_TIPOLOGIA_SOGGETTO: return (item.getSoggettoVersante() != null && item.getSoggettoVersante().getTipologiaSoggettoEnum() != null) ? item.getSoggettoVersante().getTipologiaSoggettoEnum().getId() : null;
				case NPSV_COGNOME_RAGIONE_SOCIALE:
					String svres = null;
					if (item.getSoggettoVersante() != null && item.getSoggettoVersante().getTipologiaSoggettoEnum() != null) {
						switch (item.getSoggettoVersante().getTipologiaSoggettoEnum()) {
						case PERSONA_FISICA:
							svres = item.getSoggettoVersante().getCognome();
							break;
						case PERSONA_GIURIDICA:
							svres = item.getSoggettoVersante().getRagioneSociale();
							break;
						}
					}
					return svres;
				case NPSV_NOME: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getNome() : null;
				case NPSV_INDIRIZZO: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getIndirizzo() : null;
				case NPSV_CIVICO: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getCivico() : null;
				case NPSV_CAP: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getCap() : null;
				case NPSV_LOCALITA: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getLocalita() : null;
				case NPSV_PROVINCIA: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getProvincia() : null;
				case NPSV_NAZIONE: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getNazione() : null;
				case NPSV_EMAIL: return (item.getSoggettoVersante() != null) ? item.getSoggettoVersante().getEmail() : null;
				case TP_RAGIONE_SOCIALE_PSP: return item.getRagioneSocialePsp();
				case TP_CODICE_TIPO_VERSAMENTO: return item.getCodTipoVersamento();
				case TP_DESCRIZIONE_TIPO_VERSAMENTO: return item.getDesTipoVersamento();
				case TP_ID_FLUSSO_RENDICONTAZIONE: return item.getIdFlussoRendicontazionePsp();
				case TP_DATA_AVVIO_TRANSAZIONE:  return item.getDataAvvioTransazione();
				case TP_IUR: return item.getIUR();
				case TP_DATA_AUTORIZZAZIONE: return item.getDataOraAutorizzazione();
				case TP_TIPO_SICUREZZA: return item.getTipoSicurezza();
				case TP_IMPORTO_TRANSATO: return item.getImportoTransato();
				case TP_IMPORTO_COMMISSIONI: return item.getImportoCommissioni();
				default: return null;
			}
		} else
			return null;
	}
	//@formatter:on

}
