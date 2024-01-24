/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneGW_PM] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneGW_PM() {
	var contentPanelName = "cpGestioneGW_PM";
	/// arricchimento per guigen::Table [tbListaGateways]: tooltip header di colonna
	var tbListaGatewaysTips = [
		'',
		'',
		'',
		''	];

	var enhanceGridTbListaGateways =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbListaGateways',tbListaGatewaysTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabellaGW", enhanceGridTbListaGateways);
	/// arricchimento per guigen::Table [tbListaPaymentModes]: tooltip header di colonna
	var tbListaPaymentModesTips = [
		'',
		'',
		''	];

	var enhanceGridTbListaPaymentModes =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbListaPaymentModes',tbListaPaymentModesTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabellaPM", enhanceGridTbListaPaymentModes);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneGW_PM(){
/*PROTECTED REGION ID(R-2019201403) ENABLED START*/
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

function initUIStructCpGestioneGW_PM(){
	var contentPanelName = "cpGestioneGW_PM";
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
          name: "p_pUp", panels: [
          {
            name: "p_pUserInfo", panels: [
            ]
          }
          ]
        }
,        {
          name: "p_pCenter", panels: [
          {
            name: "p_smpGestioneGW_PM", panels: [
            ]
          }
,          {
            name: "p_tspGW_PM", panels: [
            {
              name: "p_pGateways", panels: [
              {
                name: "p_wpTabellaGW", panels: []
              }
,              {
                name: "p_wpAzioniGW", panels: []
              }
              ]
            }
,            {
              name: "p_pModalitaPagamento", panels: [
              {
                name: "p_wpTabellaPM", panels: []
              }
,              {
                name: "p_wpAzioniPM", panels: []
              }
              ]
            }
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

	initStdEnrichments4CpGestioneGW_PM();
	initCustomEnrichments4CpGestioneGW_PM();
	initUIStructCpGestioneGW_PM();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneGW_PM"); 
}); 

	