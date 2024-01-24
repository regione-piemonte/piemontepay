/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneRPT] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneRPT() {
	var contentPanelName = "cpGestioneRPT";
	/// arricchimento di base per guigen::TextField [iuv]
	var addBasicEnrichmentToIuv =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_iuv',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addBasicEnrichmentToIuv);
	/// arricchimento di base per guigen::TextField [idTransaction]
	var addBasicEnrichmentToIdTransaction =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idTransaction',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addBasicEnrichmentToIdTransaction);
	/// arricchimento di base per guigen::TextField [idApplication]
	var addBasicEnrichmentToIdApplication =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idApplication',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addBasicEnrichmentToIdApplication);
	/// arricchimento per guigen::Calendar [lastUpdateDa]: aggiunta datepicker
	var addDateToLastUpdateDa = function(){uiNRichLib.addDatePickerNRich("widg_lastUpdateDa", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addDateToLastUpdateDa);
	/// arricchimento per guigen::Calendar [lastUpdateA]: aggiunta datepicker
	var addDateToLastUpdateA = function(){uiNRichLib.addDatePickerNRich("widg_lastUpdateA", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addDateToLastUpdateA);
	/// arricchimento per guigen::Calendar [insertDateDa]: aggiunta datepicker
	var addDateToInsertDateDa = function(){uiNRichLib.addDatePickerNRich("widg_insertDateDa", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addDateToInsertDateDa);
	/// arricchimento per guigen::Calendar [insertDateA]: aggiunta datepicker
	var addDateToInsertDateA = function(){uiNRichLib.addDatePickerNRich("widg_insertDateA", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addDateToInsertDateA);
	/// arricchimento per guigen::ComboBox [cbStatiRpt]: autonarrowing
	var addNarrowingToCbStatiRpt =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbStatiRpt',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addNarrowingToCbStatiRpt);
	

	/// arricchimento di base per guigen::TextField [idMsgRichiesta]
	var addBasicEnrichmentToIdMsgRichiesta =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idMsgRichiesta',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaRPT", addBasicEnrichmentToIdMsgRichiesta);
	/// arricchimento per guigen::Table [tbRicerca]: tooltip header di colonna
	var tbRicercaTips = [
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
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRisultatoRicercaRPT", enhanceGridTbRicerca);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneRPT(){
/*PROTECTED REGION ID(R-1857259537) ENABLED START*/
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

function initUIStructCpGestioneRPT(){
	var contentPanelName = "cpGestioneRPT";
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
            name: "p_smpRPT", panels: [
            ]
          }
,          {
            name: "p_wpFiltroRicercaRPT", panels: []
          }
,          {
            name: "p_wpAzioneRicercaRPT", panels: []
          }
,          {
            name: "p_wpRisultatoRicercaRPT", panels: []
          }
,          {
            name: "p_wpAzioni", panels: []
          }
          ]
        }
,        {
          name: "p_Up Panel", panels: [
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

	initStdEnrichments4CpGestioneRPT();
	initCustomEnrichments4CpGestioneRPT();
	initUIStructCpGestioneRPT();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneRPT"); 
}); 

	