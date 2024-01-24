/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioInformativaPsp] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioInformativaPsp() {
	var contentPanelName = "cpDettaglioInformativaPsp";
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioInformativaPsp(){
/*PROTECTED REGION ID(R-1840498603) ENABLED START*/
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

function initUIStructCpDettaglioInformativaPsp(){
	var contentPanelName = "cpDettaglioInformativaPsp";
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
            name: "p_smpDettaglioPsP", panels: [
            ]
          }
,          {
            name: "p_wpDettaglioInformativaPsp", panels: []
          }
,          {
            name: "p_wpDettaglioAzioniPsp", panels: []
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

	initStdEnrichments4CpDettaglioInformativaPsp();
	initCustomEnrichments4CpDettaglioInformativaPsp();
	initUIStructCpDettaglioInformativaPsp();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioInformativaPsp"); 
}); 

	