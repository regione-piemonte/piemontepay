/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.enumeration;

public enum CampoFlussoEnum {
	//@formatter:off

	// campi della notifica di pagamento
	NP_ID_POSIZIONE_DEBITORIA,
	NP_ANNO_DI_RIFERIMENTO,
	NP_IUV,
	NP_IMPORTO_PAGATO,
	NP_DATA_SCADENZA,
	NP_DESCRIZIONE_CAUSALE_VERSAMENTO,
	NP_DATA_ESITO_PAGAMENTO,
	NP_DATI_SPECIFICI_RISCOSSIONE,
	NP_NOTE,
	NP_CODICE_AVVISO,

	// campi del soggetto debitore della notifica di pagamento
	NPSD_ID_UNIVOCO_FISCALE,
	NPSD_TIPOLOGIA_SOGGETTO,
	NPSD_COGNOME_RAGIONE_SOCIALE,
	NPSD_NOME,
	NPSD_INDIRIZZO,
	NPSD_CIVICO,
	NPSD_CAP,
	NPSD_LOCALITA,
	NPSD_PROVINCIA,
	NPSD_NAZIONE,
	NPSD_EMAIL,

	// campi del soggetto versante della notifica di pagamento
	NPSV_ID_UNIVOCO_FISCALE,
	NPSV_TIPOLOGIA_SOGGETTO,
	NPSV_COGNOME_RAGIONE_SOCIALE,
	NPSV_NOME,
	NPSV_INDIRIZZO,
	NPSV_CIVICO,
	NPSV_CAP,
	NPSV_LOCALITA,
	NPSV_PROVINCIA,
	NPSV_NAZIONE,
	NPSV_EMAIL,

	// campi della transazione psp
	TP_RAGIONE_SOCIALE_PSP,
	TP_CODICE_TIPO_VERSAMENTO,
	TP_DESCRIZIONE_TIPO_VERSAMENTO,
	TP_ID_FLUSSO_RENDICONTAZIONE,
	TP_DATA_AVVIO_TRANSAZIONE,
	TP_IUR,
	TP_DATA_AUTORIZZAZIONE,
	TP_TIPO_SICUREZZA,
	TP_IMPORTO_TRANSATO,
	TP_IMPORTO_COMMISSIONI,

	// campi della posizione debitoria
	PD_ID_POSIZIONE_DEBITORIA_EST,
	PD_ANNO_DI_RIFERIMENTO,
	PD_IMPORTO_TOTALE,
	PD_DATA_SCADENZA,
	PD_DATA_INIZIO_VALIDITA,
	PD_DATA_FINE_VALIDITA,
	PD_DESCRIZIONE_CAUSALE_VERSAMENTO,
	PD_DESCRIZIONE_RATA,
	PD_NOTE_PER_PAGATORE,
	PD_IUV,
	PD_CODICE_AVVISO,
	PD_CODICE_ESITO,
	PD_DETTAGLIO_ESITO,

	// campi del soggetto debitore della posizione debitoria
	PDSD_ID_UNIVOCO_FISCALE,
	PDSD_TIPOLOGIA_SOGGETTO,
	PDSD_COGNOME_RAGIONE_SOCIALE,
	PDSD_NOME,
	PDSD_INDIRIZZO,
	PDSD_CIVICO,
	PDSD_CAP,
	PDSD_LOCALITA,
	PDSD_PROVINCIA,
	PDSD_NAZIONE,
	PDSD_EMAIL,

	// campi di componente importo
	CI_IMPORTO_1,
	CI_CAUSALE_1,
	CI_DATI_SPECIFICI_RISCOSSIONE_1,
    CI_ANNO_ACCERTAMENTO_1,
    CI_NUMERO_ACCERTAMENTO_1,
	CI_IMPORTO_2,
	CI_CAUSALE_2,
	CI_DATI_SPECIFICI_RISCOSSIONE_2,
    CI_ANNO_ACCERTAMENTO_2,
    CI_NUMERO_ACCERTAMENTO_2,
	CI_IMPORTO_3,
	CI_CAUSALE_3,
	CI_DATI_SPECIFICI_RISCOSSIONE_3,
    CI_ANNO_ACCERTAMENTO_3,
    CI_NUMERO_ACCERTAMENTO_3,
	CI_IMPORTO_4,
	CI_CAUSALE_4,
	CI_DATI_SPECIFICI_RISCOSSIONE_4,
    CI_ANNO_ACCERTAMENTO_4,
    CI_NUMERO_ACCERTAMENTO_4,
	CI_IMPORTO_5,
	CI_CAUSALE_5,
	CI_DATI_SPECIFICI_RISCOSSIONE_5,
    CI_ANNO_ACCERTAMENTO_5,
    CI_NUMERO_ACCERTAMENTO_5,
    CI_NOME_1,
    CI_VALORE_1,
    CI_NOME_2,
    CI_VALORE_2,
    CI_NOME_3,
    CI_VALORE_3,
    CI_NOME_4,
    CI_VALORE_4,
    CI_NOME_5,
    CI_VALORE_5,

	// campi di aggiorna posizione debitoria
	AGPD_TIPO_AGG_POSIZIONE_DEBITORIA,
	AGPD_ID_POSIZIONE_DEBITORIA_EST,
	AGPD_MOTIVAZIONE,
	AGPD_CODICE_AVVISO,
	AGPD_CODICE_ESITO,
	AGPD_DETTAGLIO_ESITO,
	AGPD_DATA_INIZIO_VALIDITA,
	AGPD_DATA_FINE_VALIDITA,
	AGPD_DATA_SCADENZA,
	AGPD_DATA_IMPORTO_TOTALE,
	AGPD_DESCRIZIONE_CAUSALE_VERSAMENTO,

	// campi di avvisi scaduti
	AVSC_IUV,
	AVSC_DT_SCADENZA,
	AVSC_IMPORTO,

	// campi dei dati di riversamento del flusso di rendicontazione
	FREND_HEAD_ID_LOTTO,
	FREND_HEAD_ID_FLUSSO_RENDICONTAZIONE,
	FREND_HEAD_DATA_RICEZIONE,
	FREND_HEAD_NUM_TOTALE_PAGAMENTI,
	FREND_HEAD_IMPORTO_TOTALE_PAGAMENTI,
	FREND_HEAD_DATA_ORA_CREAZIONE_FLUSSO,
	FREND_HEAD_ID_UNIVOCO_REGOLAMENTO,
	FREND_HEAD_DATA_REGOLAMENTO,
	FREND_HEAD_TIPO_MITTENTE,
	FREND_HEAD_COD_MITTENTE,
	FREND_HEAD_DENOMINAZIONE_MITTENTE,
	FREND_HEAD_COD_BIC_PSP,
	FREND_ITEM_IUV,
	FREND_ITEM_IUR,
	FREND_ITEM_INDICE_DATI_RT,
	FREND_ITEM_IMPORTO_PAGATO,
	FREND_ITEM_COD_ESITO,
    FREND_ITEM_DATA_ESITO,
    
    FREND_ITEM_STATO_FLUSSO_AGGREGATO,
    FREND_ITEM_DATI_AGGIUNTIVI_FLUSSO,
    
    FREND_ITEM_DATA_ORA_INZIO_ELABORAZIONE_REPORT,

    // campi dei pagamenti
    PAGTI_POSIZ_DEBIT_EXT,
    PAGTI_ANNO_RIFERIMENTO,
    PAGTI_IMPORTO_TOTALE,
    PAGTI_DATA_SCADENZA,
    PAGTI_CAUSALE_VERS,
    PAGTI_DESCR_RATA,
    PAGTI_IMP_COMP_1,
    PAGTI_CAUSALE_COMP_1,
    PAGTI_DSR_COMP_1,
    PAGTI_ANNO_ACC_COMP_1,
    PAGTI_NUM_ACC_COMP_1,
    PAGTI_IMP_COMP_2,
    PAGTI_CAUSALE_COMP_2,
    PAGTI_DSR_COMP_2,
    PAGTI_ANNO_ACC_COMP_2,
    PAGTI_NUM_ACC_COMP_2,
    PAGTI_IMP_COMP_3,
    PAGTI_CAUSALE_COMP_3,
    PAGTI_DSR_COMP_3,
    PAGTI_ANNO_ACC_COMP_3,
    PAGTI_NUM_ACC_COMP_3,
    PAGTI_IMP_COMP_4,
    PAGTI_CAUSALE_COMP_4,
    PAGTI_DSR_COMP_4,
    PAGTI_ANNO_ACC_COMP_4,
    PAGTI_NUM_ACC_COMP_4,
    PAGTI_IMP_COMP_5,
    PAGTI_CAUSALE_COMP_5,
    PAGTI_DSR_COMP_5,
    PAGTI_ANNO_ACC_COMP_5,
    PAGTI_NUM_ACC_COMP_5,
    PAGTI_NOME_RIF_PAG_1,
    PAGTI_VAL_RIF_PAG_1,
    PAGTI_NOME_RIF_PAG_2,
    PAGTI_VAL_RIF_PAG_2,
    PAGTI_NOME_RIF_PAG_3,
    PAGTI_VAL_RIF_PAG_3,
    PAGTI_NOME_RIF_PAG_4,
    PAGTI_VAL_RIF_PAG_4,
    PAGTI_NOME_RIF_PAG_5,
    PAGTI_VAL_RIF_PAG_5,
    PAGTI_TIPO_SOGG_DEB,
    PAGTI_CF_SOGG_DEB,
    PAGTI_COGNOME_SOGG_DEB,
    PAGTI_NOME_SOGG_DEB,
    PAGTI_RAGSOC_SOGG_DEB,
    PAGTI_INDIR_SOGG_DEB,
    PAGTI_CIVICO_SOGG_DEB,
    PAGTI_CAP_SOGG_DEB,
    PAGTI_LOCALITA_SOGG_DEB,
    PAGTI_PROV_SOGG_DEB,
    PAGTI_NAZIONE_SOGG_DEB,
    PAGTI_EMAIL_SOGG_DEB,
    PAGTI_NOTE_PAGATORE,
    PAGTI_IMPORTO_PAGATO,
    PAGTI_DATA_PAGAMENTO,
    PAGTI_STATO_PAGAMENTO,
    PAGTI_IUV,
    PAGTI_CODICE_VERSAMENTO,
    PAGTI_CODICE_AVVISO,
    PAGTI_NOME_VALORE,
    PAGTI_SOGGETTO_DEBITORE,
		PAGTI_CAUSALE,

	//@formatter:on
		//campi gestione multibeneficiario
		MULTI_IMP_PRIN,
		MULTI_IMP_SECONDARIO,
		MULTI_CI_IMPORTO_1_SECONDARIO,
		MULTI_CAUSALE_1_SECONDARIO,
		PAGTI_COSTI_NOTIFICA

}
