/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpRicercaEnti] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpRicercaEnti() {
	var contentPanelName = "cpRicercaEnti";
	/// arricchimento di base per guigen::TextField [txdescrizione]
	var addBasicEnrichmentToTxdescrizione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txdescrizione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaEnti", addBasicEnrichmentToTxdescrizione);
	/// arricchimento di base per guigen::TextField [txPartitaIva]
	var addBasicEnrichmentToTxPartitaIva =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txPartitaIva',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaEnti", addBasicEnrichmentToTxPartitaIva);
	/// arricchimento di base per guigen::TextField [enteId]
	var addBasicEnrichmentToEnteId =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_enteId',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaEnti", addBasicEnrichmentToEnteId);
	/// arricchimento per guigen::ComboBox [attivo]: autonarrowing
	var addNarrowingToAttivo =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_attivo',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFiltroRicercaEnti", addNarrowingToAttivo);
	

	/// arricchimento per guigen::Table [tRicerca]: tooltip header di colonna
	var tRicercaTips = [
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTRicerca =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tRicerca',tRicercaTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpRisultatoRicercaEnti", enhanceGridTRicerca);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpRicercaEnti(){
/*PROTECTED REGION ID(R2098030282) ENABLED START*/
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

function initUIStructCpRicercaEnti(){
	var contentPanelName = "cpRicercaEnti";
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
            name: "p_smpEnti", panels: [
            ]
          }
,          {
            name: "p_wpFiltroRicercaEnti", panels: []
          }
,          {
            name: "p_wpAzioneRicercaEnti", panels: []
          }
,          {
            name: "p_wpRisultatoRicercaEnti", panels: []
          }
,          {
            name: "p_wpAzioniEnti", panels: []
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

	initStdEnrichments4CpRicercaEnti();
	initCustomEnrichments4CpRicercaEnti();
	initUIStructCpRicercaEnti();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpRicercaEnti"); 
}); 

	