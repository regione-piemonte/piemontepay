/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioFlussoRiversamento] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioFlussoRiversamento() {
	var contentPanelName = "cpDettaglioFlussoRiversamento";
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioFlussoRiversamento(){
/*PROTECTED REGION ID(R-1257392073) ENABLED START*/
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

function initUIStructCpDettaglioFlussoRiversamento(){
	var contentPanelName = "cpDettaglioFlussoRiversamento";
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
            name: "p_wpDettaglio", panels: []
          }
,          {
            name: "p_wpAction", panels: []
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

	initStdEnrichments4CpDettaglioFlussoRiversamento();
	initCustomEnrichments4CpDettaglioFlussoRiversamento();
	initUIStructCpDettaglioFlussoRiversamento();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioFlussoRiversamento"); 
}); 

	