/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneGiornaleEventi] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneGiornaleEventi() {
	var contentPanelName = "cpGestioneGiornaleEventi";
	/// arricchimento di base per guigen::TextField [iuv]
	var addBasicEnrichmentToIuv =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_iuv',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicercaGde", addBasicEnrichmentToIuv);
	/// arricchimento per guigen::Calendar [dataEvento]: aggiunta datepicker
	var addDateToDataEvento = function(){uiNRichLib.addDatePickerNRich("widg_dataEvento", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicercaGde", addDateToDataEvento);
	/// arricchimento di base per guigen::TextField [identificativoDominio]
	var addBasicEnrichmentToIdentificativoDominio =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_identificativoDominio',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicercaGde", addBasicEnrichmentToIdentificativoDominio);
	/// arricchimento di base per guigen::TextField [identificativoFruitore]
	var addBasicEnrichmentToIdentificativoFruitore =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_identificativoFruitore',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicercaGde", addBasicEnrichmentToIdentificativoFruitore);
	/// arricchimento di base per guigen::TextField [codicePagamento]
	var addBasicEnrichmentToCodicePagamento =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_codicePagamento',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicercaGde", addBasicEnrichmentToCodicePagamento);
	/// arricchimento per guigen::WidgetsPanel [wpRicerca]: aggiunta tooltip su label
	var wpRicercaTips = [
		{id:'cercaLbl', tip:'ricerca evento'}	];
	var addLblTipsToWpRicerca = function(){uiNRichLib.addTooltipsByIDNRich(wpRicercaTips)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRicerca", addLblTipsToWpRicerca);
	/// arricchimento per guigen::Table [tRicerca]: tooltip header di colonna
	var tRicercaTips = [
		'',
		'',
		'',
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTRicerca =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tRicerca',tRicercaTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRisultatiEventi", enhanceGridTRicerca);
	/// arricchimento per guigen::WidgetsPanel [wpAzioniTabella]: aggiunta tooltip su label
	var wpAzioniTabellaTips = [
		{id:'bpDettaglioLbl', tip:'vai al dettaglio'}	];
	var addLblTipsToWpAzioniTabella = function(){uiNRichLib.addTooltipsByIDNRich(wpAzioniTabellaTips)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpAzioniTabella", addLblTipsToWpAzioniTabella);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneGiornaleEventi(){
/*PROTECTED REGION ID(R-2005346037) ENABLED START*/
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

function initUIStructCpGestioneGiornaleEventi(){
	var contentPanelName = "cpGestioneGiornaleEventi";
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
            name: "p_smpGiornaleEventi", panels: [
            ]
          }
,          {
            name: "p_wpRicercaGde", panels: []
          }
,          {
            name: "p_wpRicerca", panels: []
          }
,          {
            name: "p_wpRisultatiEventi", panels: []
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

	initStdEnrichments4CpGestioneGiornaleEventi();
	initCustomEnrichments4CpGestioneGiornaleEventi();
	initUIStructCpGestioneGiornaleEventi();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneGiornaleEventi"); 
}); 

	