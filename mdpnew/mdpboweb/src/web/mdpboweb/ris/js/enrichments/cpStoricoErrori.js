/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpStoricoErrori] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpStoricoErrori() {
	var contentPanelName = "cpStoricoErrori";
	/// arricchimento di base per guigen::TextField [idTransazione]
	var addBasicEnrichmentToIdTransazione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idTransazione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addBasicEnrichmentToIdTransazione);
	/// arricchimento per guigen::ComboBox [cbApplicazione]: autonarrowing
	var addNarrowingToCbApplicazione =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbApplicazione',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addNarrowingToCbApplicazione);
	

	/// arricchimento per guigen::ComboBox [cbGateway]: autonarrowing
	var addNarrowingToCbGateway =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbGateway',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addNarrowingToCbGateway);
	

	/// arricchimento per guigen::Calendar [cbDallaData]: aggiunta datepicker
	var addDateToCbDallaData = function(){uiNRichLib.addDatePickerNRich("widg_cbDallaData", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addDateToCbDallaData);
	/// arricchimento per guigen::Calendar [cbAllaData]: aggiunta datepicker
	var addDateToCbAllaData = function(){uiNRichLib.addDatePickerNRich("widg_cbAllaData", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addDateToCbAllaData);
	/// arricchimento per guigen::Table [tRicerca]: tooltip header di colonna
	var tRicercaTips = [
		'',
		'',
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTRicerca =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tRicerca',tRicercaTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabella", enhanceGridTRicerca);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpStoricoErrori(){
/*PROTECTED REGION ID(R-1514555065) ENABLED START*/
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

function initUIStructCpStoricoErrori(){
	var contentPanelName = "cpStoricoErrori";
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
            name: "p_smpGestioneTransazioni", panels: [
            ]
          }
,          {
            name: "p_wpRicerca", panels: []
          }
,          {
            name: "p_wpAzioniRicerca", panels: []
          }
,          {
            name: "p_wpTabella", panels: []
          }
,          {
            name: "p_pComandiTabella", panels: []
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

	initStdEnrichments4CpStoricoErrori();
	initCustomEnrichments4CpStoricoErrori();
	initUIStructCpStoricoErrori();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpStoricoErrori"); 
}); 

	