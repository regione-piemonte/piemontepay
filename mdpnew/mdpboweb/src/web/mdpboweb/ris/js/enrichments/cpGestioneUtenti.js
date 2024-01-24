/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneUtenti] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneUtenti() {
	var contentPanelName = "cpGestioneUtenti";
	/// arricchimento per guigen::Table [tListaUtenti]: tooltip header di colonna
	var tListaUtentiTips = [
		'',
		'',
		''	];

	var enhanceGridTListaUtenti =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tListaUtenti',tListaUtentiTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabella", enhanceGridTListaUtenti);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneUtenti(){
/*PROTECTED REGION ID(R819647016) ENABLED START*/
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

function initUIStructCpGestioneUtenti(){
	var contentPanelName = "cpGestioneUtenti";
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
            name: "p_smpGestioneUtenti", panels: [
            ]
          }
,          {
            name: "p_wpTabella", panels: []
          }
,          {
            name: "p_wpAzioniTabella", panels: []
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

	initStdEnrichments4CpGestioneUtenti();
	initCustomEnrichments4CpGestioneUtenti();
	initUIStructCpGestioneUtenti();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneUtenti"); 
}); 

	