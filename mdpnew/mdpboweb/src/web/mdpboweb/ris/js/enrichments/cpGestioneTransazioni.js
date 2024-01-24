/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneTransazioni] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneTransazioni() {
	var contentPanelName = "cpGestioneTransazioni";
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
	

	/// arricchimento per guigen::ComboBox [cbStato]: autonarrowing
	var addNarrowingToCbStato =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbStato',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addNarrowingToCbStato);
	

	/// arricchimento per guigen::Calendar [calDataInizio]: aggiunta datepicker
	var addDateToCalDataInizio = function(){uiNRichLib.addDatePickerNRich("widg_calDataInizio", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addDateToCalDataInizio);
	/// arricchimento per guigen::Calendar [calDataFine]: aggiunta datepicker
	var addDateToCalDataFine = function(){uiNRichLib.addDatePickerNRich("widg_calDataFine", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addDateToCalDataFine);
	/// arricchimento per guigen::Table [tRicerca]: tooltip header di colonna
	var tRicercaTips = [
		'',
		'',
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
function initCustomEnrichments4CpGestioneTransazioni(){
/*PROTECTED REGION ID(R1214948795) ENABLED START*/
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

function initUIStructCpGestioneTransazioni(){
	var contentPanelName = "cpGestioneTransazioni";
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
            name: "p_ptReportRicerca", panels: []
          }
,          {
            name: "p_ptReportCerca", panels: []
          }
,          {
            name: "p_wpTabella", panels: []
          }
,          {
            name: "p_wpAzioniTabella", panels: []
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

	initStdEnrichments4CpGestioneTransazioni();
	initCustomEnrichments4CpGestioneTransazioni();
	initUIStructCpGestioneTransazioni();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneTransazioni"); 
}); 

	