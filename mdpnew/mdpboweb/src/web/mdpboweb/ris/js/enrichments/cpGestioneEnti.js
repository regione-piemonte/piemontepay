/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneEnti] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneEnti() {
	var contentPanelName = "cpGestioneEnti";
	/// arricchimento di base per guigen::TextField [descrizione]
	var addBasicEnrichmentToDescrizione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_descrizione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_campiEnte", addBasicEnrichmentToDescrizione);
	/// arricchimento per guigen::ComboBox [attivo]: autonarrowing
	var addNarrowingToAttivo =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_attivo',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_campiEnte", addNarrowingToAttivo);
	

}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneEnti(){
/*PROTECTED REGION ID(R-620293367) ENABLED START*/
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

function initUIStructCpGestioneEnti(){
	var contentPanelName = "cpGestioneEnti";
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
            name: "p_campiEnte", panels: []
          }
,          {
            name: "p_wAction", panels: []
          }
          ]
        }
,        {
          name: "p_uUp", panels: [
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

	initStdEnrichments4CpGestioneEnti();
	initCustomEnrichments4CpGestioneEnti();
	initUIStructCpGestioneEnti();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneEnti"); 
}); 

	