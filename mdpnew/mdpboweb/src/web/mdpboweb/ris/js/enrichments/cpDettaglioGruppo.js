/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioGruppo] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioGruppo() {
	var contentPanelName = "cpDettaglioGruppo";
	/// arricchimento di base per guigen::TextField [txDescrizioneGruppo]
	var addBasicEnrichmentToTxDescrizioneGruppo =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txDescrizioneGruppo',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDati", addBasicEnrichmentToTxDescrizioneGruppo);

	

	/// arricchimento per guigen::ComboBox [cbListaRuoli]: autonarrowing
	var addNarrowingToCbListaRuoli =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbListaRuoli',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDati", addNarrowingToCbListaRuoli);
	

	/// arricchimento per guigen::Table [tbListaUtenti]: tooltip header di colonna
	var tbListaUtentiTips = [

		''	];

	var enhanceGridTbListaUtenti =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbListaUtenti',tbListaUtentiTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpUtenti", enhanceGridTbListaUtenti);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioGruppo(){
/*PROTECTED REGION ID(R2061119973) ENABLED START*/
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

function initUIStructCpDettaglioGruppo(){
	var contentPanelName = "cpDettaglioGruppo";
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
            name: "p_smpDettGruppi", panels: [
            ]
          }
,          {
            name: "p_wpDati", panels: []
          }
,          {
            name: "p_wpComandi", panels: []
          }
,          {
            name: "p_wpUtenti", panels: []
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

	initStdEnrichments4CpDettaglioGruppo();
	initCustomEnrichments4CpDettaglioGruppo();
	initUIStructCpDettaglioGruppo();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioGruppo"); 
}); 

	