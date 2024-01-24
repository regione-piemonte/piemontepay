/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioUtenti] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioUtenti() {
	var contentPanelName = "cpDettaglioUtenti";
	/// arricchimento di base per guigen::TextField [tfdescrUtente]
	var addBasicEnrichmentToTfdescrUtente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfdescrUtente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioUtenti", addBasicEnrichmentToTfdescrUtente);
	/// arricchimento di base per guigen::TextField [tfCodFisc]
	var addBasicEnrichmentToTfCodFisc =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfCodFisc',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioUtenti", addBasicEnrichmentToTfCodFisc);
	/// arricchimento di base per guigen::TextField [tfEmail]
	var addBasicEnrichmentToTfEmail =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfEmail',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioUtenti", addBasicEnrichmentToTfEmail);
	/// arricchimento di base per guigen::TextField [Pwdservizio]
	var addBasicEnrichmentToPwdservizio =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_Pwdservizio',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioUtenti", addBasicEnrichmentToPwdservizio);
	/// arricchimento per guigen::ComboBox [cbGruppo]: autonarrowing
	var addNarrowingToCbGruppo =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbGruppo',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDettaglioUtenti", addNarrowingToCbGruppo);
	

}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioUtenti(){
/*PROTECTED REGION ID(R575581853) ENABLED START*/
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

function initUIStructCpDettaglioUtenti(){
	var contentPanelName = "cpDettaglioUtenti";
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
            name: "p_smpDettaglioUtenti", panels: [
            ]
          }
,          {
            name: "p_wpDettaglioUtenti", panels: []
          }
,          {
            name: "p_wpAzioni", panels: []
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

	initStdEnrichments4CpDettaglioUtenti();
	initCustomEnrichments4CpDettaglioUtenti();
	initUIStructCpDettaglioUtenti();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioUtenti"); 
}); 

	