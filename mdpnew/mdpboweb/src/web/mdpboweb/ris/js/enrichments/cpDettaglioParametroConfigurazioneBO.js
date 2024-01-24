/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioParametroConfigurazioneBO] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioParametroConfigurazioneBO() {
	var contentPanelName = "cpDettaglioParametroConfigurazioneBO";
	/// arricchimento di base per guigen::TextField [tfChiave]
	var addBasicEnrichmentToTfChiave =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfChiave',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDati", addBasicEnrichmentToTfChiave);
	/// arricchimento di base per guigen::TextField [tfValore]
	var addBasicEnrichmentToTfValore =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfValore',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDati", addBasicEnrichmentToTfValore);
	/// arricchimento di base per guigen::TextField [tfDescrizione]
	var addBasicEnrichmentToTfDescrizione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfDescrizione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDati", addBasicEnrichmentToTfDescrizione);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioParametroConfigurazioneBO(){
/*PROTECTED REGION ID(R-1960484225) ENABLED START*/
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

function initUIStructCpDettaglioParametroConfigurazioneBO(){
	var contentPanelName = "cpDettaglioParametroConfigurazioneBO";
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
            name: "p_smpCercaPar", panels: [
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

	initStdEnrichments4CpDettaglioParametroConfigurazioneBO();
	initCustomEnrichments4CpDettaglioParametroConfigurazioneBO();
	initUIStructCpDettaglioParametroConfigurazioneBO();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioParametroConfigurazioneBO"); 
}); 

	