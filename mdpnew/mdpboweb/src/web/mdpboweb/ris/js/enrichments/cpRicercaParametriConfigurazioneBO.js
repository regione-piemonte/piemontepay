/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpRicercaParametriConfigurazioneBO] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpRicercaParametriConfigurazioneBO() {
	var contentPanelName = "cpRicercaParametriConfigurazioneBO";
	/// arricchimento per guigen::Table [tdParametriConf]: tooltip header di colonna
	var tdParametriConfTips = [
		'',
		'',
		'',
		''	];

	var enhanceGridTdParametriConf =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tdParametriConf',tdParametriConfTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabella", enhanceGridTdParametriConf);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpRicercaParametriConfigurazioneBO(){
/*PROTECTED REGION ID(R960529773) ENABLED START*/
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

function initUIStructCpRicercaParametriConfigurazioneBO(){
	var contentPanelName = "cpRicercaParametriConfigurazioneBO";
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
            name: "p_smpRicercaParametriConfigurazioneBO", panels: [
            ]
          }
,          {
            name: "p_wpTabella", panels: []
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

	initStdEnrichments4CpRicercaParametriConfigurazioneBO();
	initCustomEnrichments4CpRicercaParametriConfigurazioneBO();
	initUIStructCpRicercaParametriConfigurazioneBO();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpRicercaParametriConfigurazioneBO"); 
}); 

	