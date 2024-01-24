/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioGatewayNew] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioGatewayNew() {
	var contentPanelName = "cpDettaglioGatewayNew";
	/// arricchimento di base per guigen::TextField [tfDescrGateway]
	var addBasicEnrichmentToTfDescrGateway =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfDescrGateway',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioGateway", addBasicEnrichmentToTfDescrGateway);
	/// arricchimento di base per guigen::TextField [tfProviderGateway]
	var addBasicEnrichmentToTfProviderGateway =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfProviderGateway',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioGateway", addBasicEnrichmentToTfProviderGateway);
	/// arricchimento di base per guigen::TextField [tfServiceJNDI]
	var addBasicEnrichmentToTfServiceJNDI =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfServiceJNDI',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioGateway", addBasicEnrichmentToTfServiceJNDI);
	/// arricchimento per guigen::Table [tbListaAttributi]: tooltip header di colonna
	var tbListaAttributiTips = [
		'',
		'',
		''	];

	var enhanceGridTbListaAttributi =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbListaAttributi',tbListaAttributiTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabellaAttr", enhanceGridTbListaAttributi);
	/// arricchimento di base per guigen::TextField [txtNome]
	var addBasicEnrichmentToTxtNome =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txtNome',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpExtraAttributes", addBasicEnrichmentToTxtNome);
	/// arricchimento di base per guigen::TextField [txtDescrizione]
	var addBasicEnrichmentToTxtDescrizione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txtDescrizione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpExtraAttributes", addBasicEnrichmentToTxtDescrizione);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioGatewayNew(){
/*PROTECTED REGION ID(R574678684) ENABLED START*/
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

function initUIStructCpDettaglioGatewayNew(){
	var contentPanelName = "cpDettaglioGatewayNew";
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
            name: "p_smpDettaglioGateway", panels: [
            ]
          }
,          {
            name: "p_tstGateway", panels: [
            {
              name: "p_pDatiGeneraliGateway", panels: [
              {
                name: "p_wpDettaglioGateway", panels: []
              }
,              {
                name: "p_wpTabellaAttr", panels: []
              }
,              {
                name: "p_wpAzioniEa", panels: []
              }
,              {
                name: "p_wpExtraAttributes", panels: []
              }
,              {
                name: "p_wpAzioniAttr", panels: []
              }
,              {
                name: "p_wpAzioni", panels: []
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

	initStdEnrichments4CpDettaglioGatewayNew();
	initCustomEnrichments4CpDettaglioGatewayNew();
	initUIStructCpDettaglioGatewayNew();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioGatewayNew"); 
}); 

	