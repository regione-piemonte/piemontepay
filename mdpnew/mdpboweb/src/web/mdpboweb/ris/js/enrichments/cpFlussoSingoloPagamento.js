/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpFlussoSingoloPagamento] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpFlussoSingoloPagamento() {
	var contentPanelName = "cpFlussoSingoloPagamento";
	/// arricchimento per guigen::Calendar [txDataRegolamentoDa]: aggiunta datepicker
	var addDateToTxDataRegolamentoDa = function(){uiNRichLib.addDatePickerNRich("widg_txDataRegolamentoDa", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wRicercaSingoloFlusso ", addDateToTxDataRegolamentoDa);
	/// arricchimento per guigen::Calendar [txDataRegolamentoA]: aggiunta datepicker
	var addDateToTxDataRegolamentoA = function(){uiNRichLib.addDatePickerNRich("widg_txDataRegolamentoA", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wRicercaSingoloFlusso ", addDateToTxDataRegolamentoA);
	/// arricchimento per guigen::ComboBox [cbListaApplication]: autonarrowing
	var addNarrowingToCbListaApplication =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbListaApplication',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wRicercaSingoloFlusso ", addNarrowingToCbListaApplication);
	

	/// arricchimento di base per guigen::TextField [txidentificativoistitutoricevente]
	var addBasicEnrichmentToTxidentificativoistitutoricevente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txidentificativoistitutoricevente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wRicercaSingoloFlusso ", addBasicEnrichmentToTxidentificativoistitutoricevente);
	/// arricchimento per guigen::Table [tbRicerca]: tooltip header di colonna
	var tbRicercaTips = [
		'',
		'',
		'',
		'',
		'',
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTbRicerca =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbRicerca',tbRicercaTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wListaSingoliFlussi", enhanceGridTbRicerca);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpFlussoSingoloPagamento(){
/*PROTECTED REGION ID(R-378941838) ENABLED START*/
	/*
	 * definire e registrare qui eventuali arricchimenti custom, nel formato:
	 * var customEnr_n = function(){
	 *	// codice da eseguire all'applicazione dell'arricchimento
	 * };
	 * String customEnr_fragmentID = p_[id del pannello a cui deve essere associato l'arricchimento];
	 * uiEnricherMgr.registerEnrichment(contentPanelName, customEnr_fragmentID, customEnr_n);
	*/	
/*PROTECTED REGION END*/
}

function initUIStructCpFlussoSingoloPagamento(){
	var contentPanelName = "cpFlussoSingoloPagamento";
	var structure = 
      {
        name: "p_pMain", panels: [
        {
          name: "p_pLeft", panels: [
          {
            name: "p_pMenu", panels: []
          }
          ]
        }
,        {
          name: "p_pCenter", panels: [
          {
            name: "p_stdMsg", panels: [
            ]
          }
,          {
            name: "p_wRicercaSingoloFlusso ", panels: []
          }
,          {
            name: "p_wActionRicercaSingoliFlussi", panels: []
          }
,          {
            name: "p_wListaSingoliFlussi", panels: []
          }
,          {
            name: "p_wAction", panels: []
          }
          ]
        }
,        {
          name: "p_pUp", panels: [
          {
            name: "p_pUserInfo", panels: [
            ]
          }
          ]
        }
        ]
      }
;
	uiEnricherMgr.setPageStructure(contentPanelName, structure);
}

Ext.onReady( function() { 
	
	uiNRichLib.initStateManager();

	initStdEnrichments4CpFlussoSingoloPagamento();
	initCustomEnrichments4CpFlussoSingoloPagamento();
	initUIStructCpFlussoSingoloPagamento();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpFlussoSingoloPagamento"); 
}); 

	