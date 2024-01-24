/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioGateway] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioGateway() {
	var contentPanelName = "cpDettaglioGateway";
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
	/// arricchimento per guigen::Table [tLingue]: tooltip header di colonna
	var tLingueTips = [
		'',
		'',
		'',
		''	];

	var enhanceGridTLingue =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tLingue',tLingueTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabellaLingue", enhanceGridTLingue);
	/// arricchimento per guigen::Table [tDivise]: tooltip header di colonna
	var tDiviseTips = [
		'',
		'',
		'',
		''	];

	var enhanceGridTDivise =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tDivise',tDiviseTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabellaDivise", enhanceGridTDivise);
	/// arricchimento per guigen::Table [tbListaAssociazioni]: tooltip header di colonna
	var tbListaAssociazioniTips = [
		'',
		'',
		'',
		''	];

	var enhanceGridTbListaAssociazioni =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbListaAssociazioni',tbListaAssociazioniTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabellaGWMP", enhanceGridTbListaAssociazioni);
	/// arricchimento per guigen::ComboBox [cbPaymentModesMp]: autonarrowing
	var addNarrowingToCbPaymentModesMp =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbPaymentModesMp',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addNarrowingToCbPaymentModesMp);
	

	/// arricchimento di base per guigen::TextField [tfDefaultPaymentUrl]
	var addBasicEnrichmentToTfDefaultPaymentUrl =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfDefaultPaymentUrl',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTfDefaultPaymentUrl);
	/// arricchimento di base per guigen::TextField [tfReturnUrl]
	var addBasicEnrichmentToTfReturnUrl =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfReturnUrl',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTfReturnUrl);
	/// arricchimento di base per guigen::TextField [tfReceiptUrl]
	var addBasicEnrichmentToTfReceiptUrl =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfReceiptUrl',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTfReceiptUrl);
	/// arricchimento di base per guigen::TextField [tfKolUrl]
	var addBasicEnrichmentToTfKolUrl =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfKolUrl',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTfKolUrl);
	/// arricchimento di base per guigen::TextField [tfcontextpg]
	var addBasicEnrichmentToTfcontextpg =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfcontextpg',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTfcontextpg);
	/// arricchimento di base per guigen::TextField [tfhttpport]
	var addBasicEnrichmentToTfhttpport =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfhttpport',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTfhttpport);
	/// arricchimento di base per guigen::TextField [tfmdpgatewaypage]
	var addBasicEnrichmentToTfmdpgatewaypage =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfmdpgatewaypage',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTfmdpgatewaypage);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioGateway(){
/*PROTECTED REGION ID(R1136885608) ENABLED START*/
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

function initUIStructCpDettaglioGateway(){
	var contentPanelName = "cpDettaglioGateway";
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
                name: "p_wpAzioniAttr", panels: []
              }
,              {
                name: "p_wpTabellaLingue", panels: []
              }
,              {
                name: "p_wpTabellaDivise", panels: []
              }
,              {
                name: "p_wpAzioni", panels: []
              }
              ]
            }
,            {
              name: "p_pGatewayPaymentMode", panels: [
              {
                name: "p_wpTabellaGWMP", panels: []
              }
,              {
                name: "p_wpAzioniUpdGWMP", panels: []
              }
,              {
                name: "p_wpGWMP", panels: []
              }
,              {
                name: "p_wpAzioniGWMP", panels: []
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

	initStdEnrichments4CpDettaglioGateway();
	initCustomEnrichments4CpDettaglioGateway();
	initUIStructCpDettaglioGateway();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioGateway"); 
}); 

	