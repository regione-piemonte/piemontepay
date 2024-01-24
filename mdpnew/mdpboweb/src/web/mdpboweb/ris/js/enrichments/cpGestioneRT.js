/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneRT] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneRT() {
	var contentPanelName = "cpGestioneRT";
	/// arricchimento di base per guigen::TextField [idTransazione]
	var addBasicEnrichmentToIdTransazione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idTransazione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addBasicEnrichmentToIdTransazione);
	/// arricchimento di base per guigen::TextField [idApplicazione]
	var addBasicEnrichmentToIdApplicazione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idApplicazione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addBasicEnrichmentToIdApplicazione);
	/// arricchimento per guigen::Calendar [lastUpdateDa]: aggiunta datepicker
	var addDateToLastUpdateDa = function(){uiNRichLib.addDatePickerNRich("widg_lastUpdateDa", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addDateToLastUpdateDa);
	/// arricchimento per guigen::Calendar [lastUpdateA]: aggiunta datepicker
	var addDateToLastUpdateA = function(){uiNRichLib.addDatePickerNRich("widg_lastUpdateA", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addDateToLastUpdateA);
	/// arricchimento per guigen::Calendar [insertDateDa]: aggiunta datepicker
	var addDateToInsertDateDa = function(){uiNRichLib.addDatePickerNRich("widg_insertDateDa", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addDateToInsertDateDa);
	/// arricchimento per guigen::Calendar [insertDateA]: aggiunta datepicker
	var addDateToInsertDateA = function(){uiNRichLib.addDatePickerNRich("widg_insertDateA", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addDateToInsertDateA);
	/// arricchimento per guigen::ComboBox [cbListaStatiRpt]: autonarrowing
	var addNarrowingToCbListaStatiRpt =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbListaStatiRpt',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addNarrowingToCbListaStatiRpt);
	

	/// arricchimento di base per guigen::TextField [idMsgRichiesta]
	var addBasicEnrichmentToIdMsgRichiesta =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idMsgRichiesta',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRT", addBasicEnrichmentToIdMsgRichiesta);
	/// arricchimento per guigen::Table [tRicerca]: tooltip header di colonna
	var tRicercaTips = [
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
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRisultatoRicercaRT", enhanceGridTRicerca);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneRT(){
/*PROTECTED REGION ID(R1024709005) ENABLED START*/
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

function initUIStructCpGestioneRT(){
	var contentPanelName = "cpGestioneRT";
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
            name: "p_smpRT", panels: [
            ]
          }
,          {
            name: "p_wpFiltroRicercaRT", panels: []
          }
,          {
            name: "p_wpAzioneRicercaRT", panels: []
          }
,          {
            name: "p_wpRisultatoRicercaRT", panels: []
          }
,          {
            name: "p_wpAzioni", panels: []
          }
          ]
        }
,        {
          name: "p_uUp", panels: [
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

	initStdEnrichments4CpGestioneRT();
	initCustomEnrichments4CpGestioneRT();
	initUIStructCpGestioneRT();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneRT"); 
}); 

	