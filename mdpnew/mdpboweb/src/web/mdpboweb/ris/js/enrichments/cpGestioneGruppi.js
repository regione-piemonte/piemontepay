/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneGruppi] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneGruppi() {
	var contentPanelName = "cpGestioneGruppi";
	/// arricchimento per guigen::Table [tListaGruppi]: tooltip header di colonna
	var tListaGruppiTips = [
		'',
		'',
		''	];

	var enhanceGridTListaGruppi =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tListaGruppi',tListaGruppiTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabella", enhanceGridTListaGruppi);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneGruppi(){
/*PROTECTED REGION ID(R-1217918230) ENABLED START*/
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

function initUIStructCpGestioneGruppi(){
	var contentPanelName = "cpGestioneGruppi";
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
          name: "p_pUp", panels: [
          {
            name: "p_pUserInfo", panels: [
            ]
          }
          ]
        }
,        {
          name: "p_pCenter", panels: [
          {
            name: "p_smpGestioneGruppi", panels: [
            ]
          }
,          {
            name: "p_wpTabella", panels: []
          }
,          {
            name: "p_wpAzioniTabella", panels: []
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

	initStdEnrichments4CpGestioneGruppi();
	initCustomEnrichments4CpGestioneGruppi();
	initUIStructCpGestioneGruppi();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneGruppi"); 
}); 

	