/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneStatiRpt] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneStatiRpt() {
	var contentPanelName = "cpGestioneStatiRpt";
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneStatiRpt(){
/*PROTECTED REGION ID(R-1643094004) ENABLED START*/
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

function initUIStructCpGestioneStatiRpt(){
	var contentPanelName = "cpGestioneStatiRpt";
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
            name: "p_smtMsg", panels: [
            ]
          }
,          {
            name: "p_wStatiRPT", panels: []
          }
,          {
            name: "p_wListaStoricoRPT", panels: []
          }
,          {
            name: "p_wListaSingoloStatoVersamento", panels: []
          }
,          {
            name: "p_wAction", panels: []
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

	initStdEnrichments4CpGestioneStatiRpt();
	initCustomEnrichments4CpGestioneStatiRpt();
	initUIStructCpGestioneStatiRpt();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneStatiRpt"); 
}); 

	