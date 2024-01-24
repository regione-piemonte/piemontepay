/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioApplicazioneNew] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioApplicazioneNew() {
	var contentPanelName = "cpDettaglioApplicazioneNew";
	/// arricchimento di base per guigen::TextField [tfIdApplicazione]
	var addBasicEnrichmentToTfIdApplicazione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfIdApplicazione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfIdApplicazione);
	/// arricchimento di base per guigen::TextField [tfNomeApplicazione]
	var addBasicEnrichmentToTfNomeApplicazione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfNomeApplicazione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfNomeApplicazione);
	/// arricchimento di base per guigen::TextField [tfReferente]
	var addBasicEnrichmentToTfReferente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfReferente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfReferente);
	/// arricchimento di base per guigen::TextField [tfCliente]
	var addBasicEnrichmentToTfCliente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfCliente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfCliente);
	/// arricchimento di base per guigen::TextField [tfProgetto]
	var addBasicEnrichmentToTfProgetto =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfProgetto',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfProgetto);
	/// arricchimento di base per guigen::TextField [tfEmailEsercente]
	var addBasicEnrichmentToTfEmailEsercente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfEmailEsercente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfEmailEsercente);
	/// arricchimento di base per guigen::TextField [tfnumeroVerde]
	var addBasicEnrichmentToTfnumeroVerde =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfnumeroVerde',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfnumeroVerde);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioApplicazioneNew(){
/*PROTECTED REGION ID(R-483840407) ENABLED START*/
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

function initUIStructCpDettaglioApplicazioneNew(){
	var contentPanelName = "cpDettaglioApplicazioneNew";
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
            name: "p_smpDettaglioApplicazione", panels: [
            ]
          }
,          {
            name: "p_tspApplicazione", panels: [
            {
              name: "p_pDatiGenerali", panels: [
              {
                name: "p_wpDatiGenerali", panels: []
              }
,              {
                name: "p_wpAzioniApp", panels: []
              }
              ]
            }
            ]
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

	initStdEnrichments4CpDettaglioApplicazioneNew();
	initCustomEnrichments4CpDettaglioApplicazioneNew();
	initUIStructCpDettaglioApplicazioneNew();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioApplicazioneNew"); 
}); 

	