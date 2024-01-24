/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioRPT] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioRPT() {
	var contentPanelName = "cpDettaglioRPT";
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioRPT(){
/*PROTECTED REGION ID(R163706778) ENABLED START*/
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

function initUIStructCpDettaglioRPT(){
	var contentPanelName = "cpDettaglioRPT";
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
            name: "p_smpDettaglioRPT", panels: [
            ]
          }
,          {
            name: "p_wpDettaglioPRT", panels: []
          }
,          {
            name: "p_wpDettaglioAzioniPRT", panels: []
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

	initStdEnrichments4CpDettaglioRPT();
	initCustomEnrichments4CpDettaglioRPT();
	initUIStructCpDettaglioRPT();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioRPT"); 
}); 

	