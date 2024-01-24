/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpFlussoRiversamento] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpFlussoRiversamento() {
	var contentPanelName = "cpFlussoRiversamento";
	/// arricchimento di base per guigen::TextField [txIdentificativoUnivocoRegolamento]
	var addBasicEnrichmentToTxIdentificativoUnivocoRegolamento =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txIdentificativoUnivocoRegolamento',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpricerca", addBasicEnrichmentToTxIdentificativoUnivocoRegolamento);
	/// arricchimento per guigen::Calendar [txDataRegolamentoDa]: aggiunta datepicker
	var addDateToTxDataRegolamentoDa = function(){uiNRichLib.addDatePickerNRich("widg_txDataRegolamentoDa", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpricerca", addDateToTxDataRegolamentoDa);
	/// arricchimento per guigen::Calendar [txDataRegolamentoA]: aggiunta datepicker
	var addDateToTxDataRegolamentoA = function(){uiNRichLib.addDatePickerNRich("widg_txDataRegolamentoA", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpricerca", addDateToTxDataRegolamentoA);
	/// arricchimento di base per guigen::TextField [txdenominazionemittente]
	var addBasicEnrichmentToTxdenominazionemittente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txdenominazionemittente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpricerca", addBasicEnrichmentToTxdenominazionemittente);
	/// arricchimento di base per guigen::TextField [txidentificativoistitutoricevente]
	var addBasicEnrichmentToTxidentificativoistitutoricevente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txidentificativoistitutoricevente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpricerca", addBasicEnrichmentToTxidentificativoistitutoricevente);
	/// arricchimento per guigen::Table [tbRicerca]: tooltip header di colonna
	var tbRicercaTips = [
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTbRicerca =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbRicerca',tbRicercaTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRisultati", enhanceGridTbRicerca);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpFlussoRiversamento(){
/*PROTECTED REGION ID(R-1768219514) ENABLED START*/
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

function initUIStructCpFlussoRiversamento(){
	var contentPanelName = "cpFlussoRiversamento";
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
            name: "p_wpricerca", panels: []
          }
,          {
            name: "p_wpActionRicerca", panels: []
          }
,          {
            name: "p_wpRisultati", panels: []
          }
,          {
            name: "p_wpActRis", panels: []
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

	initStdEnrichments4CpFlussoRiversamento();
	initCustomEnrichments4CpFlussoRiversamento();
	initUIStructCpFlussoRiversamento();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpFlussoRiversamento"); 
}); 

	