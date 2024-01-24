/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioGiornaleEventi] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioGiornaleEventi() {
	var contentPanelName = "cpDettaglioGiornaleEventi";
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioGiornaleEventi(){
/*PROTECTED REGION ID(R-375951232) ENABLED START*/
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

function initUIStructCpDettaglioGiornaleEventi(){
	var contentPanelName = "cpDettaglioGiornaleEventi";
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
            name: "p_smpDettaglioGiornaleEventi", panels: [
            ]
          }
,          {
            name: "p_wpDettaglioGiornaleEventi", panels: []
          }
,          {
            name: "p_wpAzioni", panels: []
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

	initStdEnrichments4CpDettaglioGiornaleEventi();
	initCustomEnrichments4CpDettaglioGiornaleEventi();
	initUIStructCpDettaglioGiornaleEventi();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioGiornaleEventi"); 
}); 

	