/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpInformativePsp] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpInformativePsp() {
	var contentPanelName = "cpInformativePsp";
	/// arricchimento per guigen::Calendar [txdatapubblicazione]: aggiunta datepicker
	var addDateToTxdatapubblicazione = function(){uiNRichLib.addDatePickerNRich("widg_txdatapubblicazione", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wsRicercaInformativa", addDateToTxdatapubblicazione);
	/// arricchimento per guigen::Calendar [txdatainserimento]: aggiunta datepicker
	var addDateToTxdatainserimento = function(){uiNRichLib.addDatePickerNRich("widg_txdatainserimento", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wsRicercaInformativa", addDateToTxdatainserimento);
	/// arricchimento di base per guigen::TextField [txragionesociale]
	var addBasicEnrichmentToTxragionesociale =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txragionesociale',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wsRicercaInformativa", addBasicEnrichmentToTxragionesociale);
	/// arricchimento per guigen::ComboBox [cbtipoversamento]: autonarrowing
	var addNarrowingToCbtipoversamento =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbtipoversamento',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wsRicercaInformativa", addNarrowingToCbtipoversamento);
	

	/// arricchimento per guigen::Table [tbRisultati]: tooltip header di colonna
	var tbRisultatiTips = [
		'',
		'',
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTbRisultati =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbRisultati',tbRisultatiTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wsListaInformative", enhanceGridTbRisultati);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpInformativePsp(){
/*PROTECTED REGION ID(R1412761248) ENABLED START*/
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

function initUIStructCpInformativePsp(){
	var contentPanelName = "cpInformativePsp";
	var structure = 
      {
        name: "p_pmain", panels: [
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
            name: "p_wsRicercaInformativa", panels: []
          }
,          {
            name: "p_wsRicerca", panels: []
          }
,          {
            name: "p_wsListaInformative", panels: []
          }
,          {
            name: "p_wsAction", panels: []
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

	initStdEnrichments4CpInformativePsp();
	initCustomEnrichments4CpInformativePsp();
	initUIStructCpInformativePsp();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpInformativePsp"); 
}); 

	