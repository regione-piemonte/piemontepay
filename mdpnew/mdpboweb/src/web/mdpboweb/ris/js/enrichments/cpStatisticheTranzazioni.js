/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpStatisticheTranzazioni] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpStatisticheTranzazioni() {
	var contentPanelName = "cpStatisticheTranzazioni";
	/// arricchimento di base per guigen::TextField [txApplicationId]
	var addBasicEnrichmentToTxApplicationId =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txApplicationId',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicerca", addBasicEnrichmentToTxApplicationId);
	/// arricchimento per guigen::Calendar [txDataDa]: aggiunta datepicker
	var addDateToTxDataDa = function(){uiNRichLib.addDatePickerNRich("widg_txDataDa", true)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicerca", addDateToTxDataDa);
	/// arricchimento per guigen::Calendar [txDataA]: aggiunta datepicker
	var addDateToTxDataA = function(){uiNRichLib.addDatePickerNRich("widg_txDataA", true)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicerca", addDateToTxDataA);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpStatisticheTranzazioni(){
/*PROTECTED REGION ID(R-1851338325) ENABLED START*/
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

function initUIStructCpStatisticheTranzazioni(){
	var contentPanelName = "cpStatisticheTranzazioni";
	var structure = 
      {
        name: "p_cpStatisticheTransazioni", panels: [
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
            name: "p_wpFiltroRicerca", panels: []
          }
,          {
            name: "p_wpAzioneRicerca", panels: []
          }
,          {
            name: "p_wpRisultati", panels: []
          }
,          {
            name: "p_wpExport", panels: []
          }
          ]
        }
,        {
          name: "p_pUP", panels: [
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

	initStdEnrichments4CpStatisticheTranzazioni();
	initCustomEnrichments4CpStatisticheTranzazioni();
	initUIStructCpStatisticheTranzazioni();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpStatisticheTranzazioni"); 
}); 

	