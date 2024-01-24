/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpEliminaParametroConfigurazioneBO] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpEliminaParametroConfigurazioneBO() {
	var contentPanelName = "cpEliminaParametroConfigurazioneBO";
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpEliminaParametroConfigurazioneBO(){
/*PROTECTED REGION ID(R-2120630119) ENABLED START*/
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

function initUIStructCpEliminaParametroConfigurazioneBO(){
	var contentPanelName = "cpEliminaParametroConfigurazioneBO";
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
            name: "p_smp", panels: [
            ]
          }
,          {
            name: "p_wpDati", panels: []
          }
,          {
            name: "p_wpComandi", panels: []
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

	initStdEnrichments4CpEliminaParametroConfigurazioneBO();
	initCustomEnrichments4CpEliminaParametroConfigurazioneBO();
	initUIStructCpEliminaParametroConfigurazioneBO();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpEliminaParametroConfigurazioneBO"); 
}); 

	