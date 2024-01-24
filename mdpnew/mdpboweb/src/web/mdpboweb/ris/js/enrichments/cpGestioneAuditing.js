/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpGestioneAuditing] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpGestioneAuditing() {
	var contentPanelName = "cpGestioneAuditing";
	/// arricchimento per guigen::Calendar [cercaDataInizio]: aggiunta datepicker
	var addDateToCercaDataInizio = function(){uiNRichLib.addDatePickerNRich("widg_cercaDataInizio", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiRicerca", addDateToCercaDataInizio);
	/// arricchimento per guigen::Calendar [cercaDataFine]: aggiunta datepicker
	var addDateToCercaDataFine = function(){uiNRichLib.addDatePickerNRich("widg_cercaDataFine", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiRicerca", addDateToCercaDataFine);
	/// arricchimento di base per guigen::TextField [idTransazione]
	var addBasicEnrichmentToIdTransazione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_idTransazione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiRicerca", addBasicEnrichmentToIdTransazione);






	/// arricchimento per guigen::Table [tAuditItems]: tooltip header di colonna
	var tAuditItemsTips = [

		'',
		'',
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTAuditItems =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tAuditItems',tAuditItemsTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabella", enhanceGridTAuditItems);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpGestioneAuditing(){
/*PROTECTED REGION ID(R1835948946) ENABLED START*/
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

function initUIStructCpGestioneAuditing(){
	var contentPanelName = "cpGestioneAuditing";
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
            name: "p_smpGestioneAuditing", panels: [
            ]
          }
,          {
            name: "p_pFiltri", panels: [
            {
              name: "p_wpDatiRicerca", panels: []
            }
,            {
              name: "p_wpAppCombo", panels: []
            }
,            {
              name: "p_wpAzioniCombo", panels: []
            }
,            {
              name: "p_wpUtentiCombo", panels: []
            }
,            {
              name: "p_wpComandiRicerca", panels: []
            }
,            {
              name: "p_wpTabella", panels: []
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

	initStdEnrichments4CpGestioneAuditing();
	initCustomEnrichments4CpGestioneAuditing();
	initUIStructCpGestioneAuditing();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpGestioneAuditing"); 
}); 

	