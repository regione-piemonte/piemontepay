/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioPaymentMode] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioPaymentMode() {
	var contentPanelName = "cpDettaglioPaymentMode";
	/// arricchimento di base per guigen::TextField [tfDescrPM]
	var addBasicEnrichmentToTfDescrPM =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfDescrPM',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioPaymentMode", addBasicEnrichmentToTfDescrPM);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioPaymentMode(){
/*PROTECTED REGION ID(R233872813) ENABLED START*/
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

function initUIStructCpDettaglioPaymentMode(){
	var contentPanelName = "cpDettaglioPaymentMode";
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
            name: "p_smpDettaglioPM", panels: [
            ]
          }
,          {
            name: "p_wpDettaglioPaymentMode", panels: []
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

	initStdEnrichments4CpDettaglioPaymentMode();
	initCustomEnrichments4CpDettaglioPaymentMode();
	initUIStructCpDettaglioPaymentMode();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioPaymentMode"); 
}); 

	