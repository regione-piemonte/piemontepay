/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioTransazione] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioTransazione() {
	var contentPanelName = "cpDettaglioTransazione";
	/// arricchimento per guigen::ComboBox [cbStato]: autonarrowing
	var addNarrowingToCbStato =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbStato',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpCambiaStato", addNarrowingToCbStato);
	

}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioTransazione(){
/*PROTECTED REGION ID(R1107149666) ENABLED START*/
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

function initUIStructCpDettaglioTransazione(){
	var contentPanelName = "cpDettaglioTransazione";
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
            name: "p_smpDettaglioTransazione", panels: [
            ]
          }
,          {
            name: "p_wpDettaglioTransazione", panels: []
          }
,          {
            name: "p_wpCambiaStato", panels: []
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

	initStdEnrichments4CpDettaglioTransazione();
	initCustomEnrichments4CpDettaglioTransazione();
	initUIStructCpDettaglioTransazione();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioTransazione"); 
}); 

	