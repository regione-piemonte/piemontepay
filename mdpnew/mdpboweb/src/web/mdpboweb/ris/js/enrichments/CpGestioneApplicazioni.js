/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneApplicazioni] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneApplicazioni() {
	var contentPanelName = "cpGestioneApplicazioni";
	/// arricchimento per guigen::Table [tListaApplicazioni]: tooltip header di colonna
	var tListaApplicazioniTips = [
		'',
		'',
		'',
		''	];

	var enhanceGridTListaApplicazioni =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tListaApplicazioni',tListaApplicazioniTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabella", enhanceGridTListaApplicazioni);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneApplicazioni(){
/*PROTECTED REGION ID(R1797630218) ENABLED START*/
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

function initUIStructCpGestioneApplicazioni(){
	var contentPanelName = "cpGestioneApplicazioni";
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
            name: "p_smpGestioneApplicazioni", panels: [
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

	initStdEnrichments4CpGestioneApplicazioni();
	initCustomEnrichments4CpGestioneApplicazioni();
	initUIStructCpGestioneApplicazioni();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneApplicazioni"); 
}); 

	