/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpVerificaFlussi] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpVerificaFlussi() {
	var contentPanelName = "cpVerificaFlussi";
	/// arricchimento per guigen::Table [tbRiepilogo]: tooltip header di colonna
	var tbRiepilogoTips = [

		'',
		'',
		''	];

	var enhanceGridTbRiepilogo =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbRiepilogo',tbRiepilogoTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wsRiepilogo", enhanceGridTbRiepilogo);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpVerificaFlussi(){
/*PROTECTED REGION ID(R-1087092720) ENABLED START*/
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

function initUIStructCpVerificaFlussi(){
	var contentPanelName = "cpVerificaFlussi";
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
            name: "p_stdMsg", panels: [
            ]
          }
,          {
            name: "p_wsRiepilogo", panels: []
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

	initStdEnrichments4CpVerificaFlussi();
	initCustomEnrichments4CpVerificaFlussi();
	initUIStructCpVerificaFlussi();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpVerificaFlussi"); 
}); 

	