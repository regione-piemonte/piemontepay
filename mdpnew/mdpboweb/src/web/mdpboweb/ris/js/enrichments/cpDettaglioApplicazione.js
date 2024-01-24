/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


		
/**
 * Arricchimenti standard per il ContentPanel [cpDettaglioApplicazione] da registrare nella
 * variabile globale uiEnricherMgr
 */
function initStdEnrichments4CpDettaglioApplicazione() {
	var contentPanelName = "cpDettaglioApplicazione";
	/// arricchimento di base per guigen::TextField [tfNomeApplicazione]
	var addBasicEnrichmentToTfNomeApplicazione =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfNomeApplicazione',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfNomeApplicazione);
	/// arricchimento di base per guigen::TextField [tfReferente]
	var addBasicEnrichmentToTfReferente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfReferente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfReferente);
	/// arricchimento di base per guigen::TextField [tfCliente]
	var addBasicEnrichmentToTfCliente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfCliente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfCliente);
	/// arricchimento di base per guigen::TextField [tfProgetto]
	var addBasicEnrichmentToTfProgetto =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfProgetto',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfProgetto);
	/// arricchimento di base per guigen::TextField [tfEmailEsercente]
	var addBasicEnrichmentToTfEmailEsercente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfEmailEsercente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfEmailEsercente);
	/// arricchimento di base per guigen::TextField [tfNumeroVerde]
	var addBasicEnrichmentToTfNumeroVerde =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfNumeroVerde',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpDatiGenerali", addBasicEnrichmentToTfNumeroVerde);
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

	/// arricchimento per guigen::ComboBox [cbPaymentModes]: autonarrowing
	var addNarrowingToCbPaymentModes =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbPaymentModes',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addNarrowingToCbPaymentModes);
	

	/// arricchimento di base per guigen::TextField [txCodIstat]
	var addBasicEnrichmentToTxCodIstat =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txCodIstat',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP", addBasicEnrichmentToTxCodIstat);
	/// arricchimento di base per guigen::TextField [tfIdEsercente]
	var addBasicEnrichmentToTfIdEsercente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfIdEsercente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP1", addBasicEnrichmentToTfIdEsercente);
	/// arricchimento di base per guigen::TextField [tfPasswordEsercente]
	var addBasicEnrichmentToTfPasswordEsercente =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfPasswordEsercente',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP1", addBasicEnrichmentToTfPasswordEsercente);
	/// arricchimento di base per guigen::TextField [tfMacAvvio]
	var addBasicEnrichmentToTfMacAvvio =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfMacAvvio',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP1", addBasicEnrichmentToTfMacAvvio);
	/// arricchimento per guigen::ComboBox [cbtipologiaComm]: autonarrowing
	var addNarrowingToCbtipologiaComm =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbtipologiaComm',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP2", addNarrowingToCbtipologiaComm);
	

	/// arricchimento di base per guigen::TextField [impMin]
	var addBasicEnrichmentToImpMin =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_impMin',
			 false, 
			 'java.util.ArrayList<float>', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP2", addBasicEnrichmentToImpMin);
	/// arricchimento di base per guigen::TextField [impMax]
	var addBasicEnrichmentToImpMax =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_impMax',
			 false, 
			 'float', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP2", addBasicEnrichmentToImpMax);
	/// arricchimento di base per guigen::TextField [tfSoglia]
	var addBasicEnrichmentToTfSoglia =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfSoglia',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP2", addBasicEnrichmentToTfSoglia);
	/// arricchimento di base per guigen::TextField [tfApplicationurlresponseko]
	var addBasicEnrichmentToTfApplicationurlresponseko =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfApplicationurlresponseko',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP3", addBasicEnrichmentToTfApplicationurlresponseko);
	/// arricchimento di base per guigen::TextField [tfApplicationurlresponseok]
	var addBasicEnrichmentToTfApplicationurlresponseok =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfApplicationurlresponseok',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP3", addBasicEnrichmentToTfApplicationurlresponseok);
	/// arricchimento di base per guigen::TextField [tfApplicationurlback]
	var addBasicEnrichmentToTfApplicationurlback =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfApplicationurlback',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpGWMP3", addBasicEnrichmentToTfApplicationurlback);
	/// arricchimento per guigen::Table [tbListaAttributi]: tooltip header di colonna
	var tbListaAttributiTips = [
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTbListaAttributi =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbListaAttributi',tbListaAttributiTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpTabellaAttr", enhanceGridTbListaAttributi);
	/// arricchimento di base per guigen::TextField [tfValore]
	var addBasicEnrichmentToTfValore =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_tfValore',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpExtraAttributes", addBasicEnrichmentToTfValore);
	/// arricchimento per guigen::ComboBox [cbEnteId]: autonarrowing
	var addNarrowingToCbEnteId =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbEnteId',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpListaEnti", addNarrowingToCbEnteId);
	

	/// arricchimento per guigen::Table [tbIbanEnteApplicatio]: tooltip header di colonna
	var tbIbanEnteApplicatioTips = [
		'',
		'',
		'',
		'',
		'',
		'',
		'',
		'',
		''	];

	var enhanceGridTbIbanEnteApplicatio =  
		function(){uiNRichLib.dynaDisplayTableNRich(
			'row_tbIbanEnteApplicatio',tbIbanEnteApplicatioTips
			)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpListaIban", enhanceGridTbIbanEnteApplicatio);
	/// arricchimento per guigen::ComboBox [cbListaTipoVersamento]: autonarrowing
	var addNarrowingToCbListaTipoVersamento =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbListaTipoVersamento',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFieldModIban", addNarrowingToCbListaTipoVersamento);
	

	/// arricchimento per guigen::ComboBox [cbIdentificativoPsp]: autonarrowing
	var addNarrowingToCbIdentificativoPsp =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbIdentificativoPsp',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFieldModIban", addNarrowingToCbIdentificativoPsp);
	

	/// arricchimento per guigen::ComboBox [cbAttivazione]: autonarrowing
	var addNarrowingToCbAttivazione =  
		function(){uiNRichLib.addComboNarrowingNRich(
			'widg_cbAttivazione',
			 true)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFieldModIban", addNarrowingToCbAttivazione);
	

	/// arricchimento di base per guigen::TextField [txIban]
	var addBasicEnrichmentToTxIban =  
		function(){uiNRichLib.basicTextFieldNRich(
			'widg_txIban',
			 false, 
			 'java.lang.String', 
			 null)}; 
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFieldModIban", addBasicEnrichmentToTxIban);
	/// arricchimento per guigen::Calendar [clDataInizioValidita]: aggiunta datepicker
	var addDateToClDataInizioValidita = function(){uiNRichLib.addDatePickerNRich("widg_clDataInizioValidita", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFieldModIban", addDateToClDataInizioValidita);
	/// arricchimento per guigen::Calendar [cLdataFineValidita]: aggiunta datepicker
	var addDateToCLdataFineValidita = function(){uiNRichLib.addDatePickerNRich("widg_cLdataFineValidita", false)};
	uiEnricherMgr.registerEnrichment(contentPanelName, "p_wpFieldModIban", addDateToCLdataFineValidita);
}

/**
 * Arricchimenti custom
 */
function initCustomEnrichments4CpDettaglioApplicazione(){
/*PROTECTED REGION ID(R198447803) ENABLED START*/
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

function initUIStructCpDettaglioApplicazione(){
	var contentPanelName = "cpDettaglioApplicazione";
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
            name: "p_smpDettaglioApplicazione", panels: [
            ]
          }
,          {
            name: "p_tspApplicazione", panels: [
            {
              name: "p_pDatiGenerali", panels: [
              {
                name: "p_wpDatiGenerali", panels: []
              }
,              {
                name: "p_wpAzioniApp", panels: []
              }
              ]
            }
,            {
              name: "p_pGatewayPaymentMode", panels: [
              {
                name: "p_wpTabellaGWMP", panels: []
              }
,              {
                name: "p_wpAzioniAssociazioniGWMP", panels: []
              }
,              {
                name: "p_wpGWMP", panels: []
              }
,              {
                name: "p_wpGWMP1", panels: []
              }
,              {
                name: "p_wpGWMP2", panels: []
              }
,              {
                name: "p_wpGWMP3", panels: []
              }
,              {
                name: "p_wpFlagMail", panels: []
              }
,              {
                name: "p_wpFiles", panels: []
              }
,              {
                name: "p_wpFilesAction", panels: []
              }
,              {
                name: "p_wpTabellaAttr", panels: []
              }
,              {
                name: "p_wpExtraAttributes", panels: []
              }
,              {
                name: "p_wpAzioniAttr", panels: []
              }
,              {
                name: "p_wpAzioniGWMP", panels: []
              }
              ]
            }
,            {
              name: "p_pAssociazioneEnti", panels: [
              {
                name: "p_wpEnteSelezionato", panels: []
              }
,              {
                name: "p_wpListaEnti", panels: []
              }
,              {
                name: "p_wpAzioniAssociazioneEnte", panels: []
              }
              ]
            }
,            {
              name: "p_pIbanEnteApplication", panels: [
              {
                name: "p_wpListaIban", panels: []
              }
,              {
                name: "p_wpActionListaIban", panels: []
              }
,              {
                name: "p_wpFieldModIban", panels: []
              }
,              {
                name: "p_wpActionFieldModIban", panels: []
              }
,              {
                name: "p_wpHidden", panels: []
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

	initStdEnrichments4CpDettaglioApplicazione();
	initCustomEnrichments4CpDettaglioApplicazione();
	initUIStructCpDettaglioApplicazione();
	uiEnricherMgr.setReady(); 
	uiEnricherMgr.applyAll("cpDettaglioApplicazione"); 
}); 

	