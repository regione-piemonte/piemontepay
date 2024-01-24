<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneAuditingAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneAuditing -->
<s:form id="cpGestioneAuditing" action="cpGestioneAuditing" method="post">
		
	
	
		
<!-- ####################### PANNELLO CONTENUTI ###################### -->
<div id="contentPanel">

	<!-- ================ UDLRC Layout UP PANEL ================ -->
	<div id="northPanel">
		<div class="wrapper">
		
	
	<div id="p_pUp" class="formPanelBlock"><!-- startFragment:p_pUp -->

	
	

	
	
			
	
	<div id="p_pUserInfo" class="userInfoPanelBlock"><!-- startFragment:p_pUserInfo -->
	
	
<div class="userInfoPanel" id="pUserInfo">
	<div class="userData">
		<span class="user">Utente: <span class="value"><s:property value="appDatacurrentUser.nome" /> <s:property value="appDatacurrentUser.cognome" /></span></span>
		<span class="organization">Ente: <span class="value"><s:property value="appDatacurrentUser.ente" /></span></span>
		<span class="role">Ruolo: <span class="value"><s:property value="appDatacurrentUser.ruolo" /></span></span>
	</div>
	<div class="userInfoActions">
		<!-- uscita dal servizio e/o cambio ruolo -->
		<s:url id="logoutUrl" action="Logout" method="localLogout" />
		<s:a href="%{logoutUrl}" title="chiudi: questo link fa uscire dal servizio">esci</s:a>
	</div>
</div>

	<!-- endFragment:p_pUserInfo --></div>

	

	<!-- endFragment:p_pUp --></div>

		</div>
	</div>
	<!-- ================ FINE UDLRC Layout UP PANEL ================ -->


	<!-- ================ UDLRC Layout WEST-CENTER-EAST WRAPPER ================ -->
	<div id="westCenterWrapper" class="floatWrapper">
		<!-- ***** UDLRC Layout LEFT PANEL ***** -->
		<div id="westPanel">
			<div class="wrapper">
			
	
	<div id="p_pLeft" class="formPanelBlock"><!-- startFragment:p_pLeft -->

	
	

	
	
			
	
	<div id="p_pMenu" class="menuPanelBlock"><!-- startFragment:p_pMenu -->
	
	
<div class="menuPanel vertical" id="pMenu">

	
		
			
<s:if test="isWidgetVisible('cpGestioneAuditing','mnuView')" >

	


	<s:include value="fragments/menu.jsp"></s:include>

	

</s:if>

	


		
	
	
</div>

	<!-- endFragment:p_pMenu --></div>

	

	<!-- endFragment:p_pLeft --></div>

			</div>
		</div>
		<!-- ***** FINE UDLRC Layout LEFT PANEL ***** -->


		<!-- ***** UDLRC Layout CENTER PANEL ***** -->
		<div id="centerPanel">
			<div class="wrapper">
				<!-- titolo pannello PRIMO livello -->
				<h3><s:text name="cpGestioneAuditing.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGestioneAuditing" class="stdMessagePanelBlock"><!-- startFragment:p_smpGestioneAuditing -->
	
	
<div class="stdMessagePanel" id="smpGestioneAuditing">
	<customtag:stdMessagePanel id="smpGestioneAuditing" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGestioneAuditing --></div>

			
	
	<div id="p_pFiltri" class="formPanelBlock scroll"><!-- startFragment:p_pFiltri -->
		
	
<div class="formPanel" id="pFiltri">


	
	

	
	
			
	
	<div id="p_wpDatiRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpDatiRicerca -->
	
	

<div class="widgetsPanel" id="wpDatiRicerca">
	
	<customtag:widgetsPanel id="wpDatiRicercaTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneAuditing','cercaDataInizio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneAuditing.cercaDataInizio.label')}" labelFor="widg_cercaDataInizio" errorFor="appDataricercaAudit.cercaDataInizio" labelId="cercaDataInizioLbl"
	position="first"  >


<!-- widget cercaDataInizio -->
<s:textfield name="appDataricercaAudit.cercaDataInizio" id="widg_cercaDataInizio"
	disabled="isWidgetDisabled('cpGestioneAuditing','cercaDataInizio')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpDatiRicerca_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiRicerca_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','cercaDataFine')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneAuditing.cercaDataFine.label')}" labelFor="widg_cercaDataFine" errorFor="appDataricercaAudit.cercaDataFine" labelId="cercaDataFineLbl"
	position="first"  >


<!-- widget cercaDataFine -->
<s:textfield name="appDataricercaAudit.cercaDataFine" id="widg_cercaDataFine"
	disabled="isWidgetDisabled('cpGestioneAuditing','cercaDataFine')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpDatiRicerca_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiRicerca_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','idTransazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneAuditing.idTransazione.label')}" labelFor="widg_idTransazione" errorFor="appDataricercaAudit.idTransazione" labelId="idTransazioneLbl"
	position="first"  >


<!-- widget idTransazione -->
<s:textfield name="appDataricercaAudit.idTransazione" id="widg_idTransazione"
	disabled="isWidgetDisabled('cpGestioneAuditing','idTransazione')"
	size="25" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpDatiRicerca_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiRicerca_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDatiRicerca --></div>

			
	
	<div id="p_wpAppCombo" class="widgetsPanelBlock scroll"><!-- startFragment:p_wpAppCombo -->
	
	
<h4 class="wpLabel">Filtro applicazioni <span id="toggle_wpAppCombo"></span></h4>
<div class="widgetsPanel" id="wpAppCombo">
	
	<customtag:widgetsPanel id="wpAppComboTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneAuditing','cbAppToAdd')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbAppToAdd" errorFor="appDataselettoreApp1" labelId="cbAppToAddLbl"
	position="first"  >


<!-- widget cbAppToAdd -->
<s:select name="appDataselettoreApp1" id="widg_cbAppToAdd"
           
          list="appDataappForAudit"
          disabled="isWidgetDisabled('cpGestioneAuditing','cbAppToAdd')"
          listKey="idApplicazione"
          listValue="nomeApplicazione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','btnAddAppFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnAddAppFiltro -->
<s:submit name="widg_btnAddAppFiltro" id="widg_btnAddAppFiltro" method="handleBtnAddAppFiltro_CLICKED"
	key="cpGestioneAuditing.btnAddAppFiltro.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneAuditing','btnAddAppFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpAppCombo_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpAppCombo_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','cbAppFiltered')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbAppFiltered" errorFor="appDatafilteredApplication" labelId="cbAppFilteredLbl"
	position="first"  >


<!-- widget cbAppFiltered -->
<s:select name="appDatafilteredApplication" id="widg_cbAppFiltered"
           
          list="appDataappForAuditFiltered"
          disabled="isWidgetDisabled('cpGestioneAuditing','cbAppFiltered')"
          multiple="true"
          cssClass="selectMultiple"
          size="5"
          listKey="idApplicazione"
          listValue="nomeApplicazione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','btnEliminAppFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnEliminAppFiltro -->
<s:submit name="widg_btnEliminAppFiltro" id="widg_btnEliminAppFiltro" method="handleBtnEliminAppFiltro_CLICKED"
	key="cpGestioneAuditing.btnEliminAppFiltro.label" cssClass="buttonWidget cancel"
	disabled="isWidgetDisabled('cpGestioneAuditing','btnEliminAppFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpAppCombo_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpAppCombo_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAppCombo --></div>

			
	
	<div id="p_wpAzioniCombo" class="widgetsPanelBlock scroll"><!-- startFragment:p_wpAzioniCombo -->
	
	
<h4 class="wpLabel">Filtro azioni <span id="toggle_wpAzioniCombo"></span></h4>
<div class="widgetsPanel" id="wpAzioniCombo">
	
	<customtag:widgetsPanel id="wpAzioniComboTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneAuditing','cbAzioneToAdd')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbAzioneToAdd" errorFor="appDataselettoreAzione1" labelId="cbAzioneToAddLbl"
	position="first"  >


<!-- widget cbAzioneToAdd -->
<s:select name="appDataselettoreAzione1" id="widg_cbAzioneToAdd"
           
          list="appDataazioniForAudit"
          disabled="isWidgetDisabled('cpGestioneAuditing','cbAzioneToAdd')"
          listKey="idAzione"
          listValue="descrAzione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','btnAddAzioneFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnAddAzioneFiltro -->
<s:submit name="widg_btnAddAzioneFiltro" id="widg_btnAddAzioneFiltro" method="handleBtnAddAzioneFiltro_CLICKED"
	key="cpGestioneAuditing.btnAddAzioneFiltro.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneAuditing','btnAddAzioneFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpAzioniCombo_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpAzioniCombo_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','cbAzioneFiltered')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbAzioneFiltered" errorFor="appDatafilteredAzioni" labelId="cbAzioneFilteredLbl"
	position="first"  >


<!-- widget cbAzioneFiltered -->
<s:select name="appDatafilteredAzioni" id="widg_cbAzioneFiltered"
           
          list="appDataazioniForAuditFiltered"
          disabled="isWidgetDisabled('cpGestioneAuditing','cbAzioneFiltered')"
          multiple="true"
          cssClass="selectMultiple"
          size="5"
          listKey="idAzione"
          listValue="descrAzione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','btnEliminAzioneFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnEliminAzioneFiltro -->
<s:submit name="widg_btnEliminAzioneFiltro" id="widg_btnEliminAzioneFiltro" method="handleBtnEliminAzioneFiltro_CLICKED"
	key="cpGestioneAuditing.btnEliminAzioneFiltro.label" cssClass="buttonWidget cancel"
	disabled="isWidgetDisabled('cpGestioneAuditing','btnEliminAzioneFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpAzioniCombo_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpAzioniCombo_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniCombo --></div>

			
	
	<div id="p_wpUtentiCombo" class="widgetsPanelBlock scroll"><!-- startFragment:p_wpUtentiCombo -->
	
	
<h4 class="wpLabel">Filtro utenti <span id="toggle_wpUtentiCombo"></span></h4>
<div class="widgetsPanel" id="wpUtentiCombo">
	
	<customtag:widgetsPanel id="wpUtentiComboTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneAuditing','cbUtenteToAdd')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbUtenteToAdd" errorFor="appDataselettoreUtente1" labelId="cbUtenteToAddLbl"
	position="first"  >


<!-- widget cbUtenteToAdd -->
<s:select name="appDataselettoreUtente1" id="widg_cbUtenteToAdd"
           
          list="appDatautentiForAudit"
          disabled="isWidgetDisabled('cpGestioneAuditing','cbUtenteToAdd')"
          listKey="idUtente"
          listValue="descrUtente"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','btnAddUtenteFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnAddUtenteFiltro -->
<s:submit name="widg_btnAddUtenteFiltro" id="widg_btnAddUtenteFiltro" method="handleBtnAddUtenteFiltro_CLICKED"
	key="cpGestioneAuditing.btnAddUtenteFiltro.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneAuditing','btnAddUtenteFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpUtentiCombo_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpUtentiCombo_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','cbUtenteFiltered')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbUtenteFiltered" errorFor="appDatafilteredUtenti" labelId="cbUtenteFilteredLbl"
	position="first"  >


<!-- widget cbUtenteFiltered -->
<s:select name="appDatafilteredUtenti" id="widg_cbUtenteFiltered"
           
          list="appDatautentiForAuditFiltered"
          disabled="isWidgetDisabled('cpGestioneAuditing','cbUtenteFiltered')"
          multiple="true"
          cssClass="selectMultiple"
          size="5"
          listKey="idUtente"
          listValue="descrUtente"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','btnEliminUtenteFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnEliminUtenteFiltro -->
<s:submit name="widg_btnEliminUtenteFiltro" id="widg_btnEliminUtenteFiltro" method="handleBtnEliminUtenteFiltro_CLICKED"
	key="cpGestioneAuditing.btnEliminUtenteFiltro.label" cssClass="buttonWidget deleteItem"
	disabled="isWidgetDisabled('cpGestioneAuditing','btnEliminUtenteFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneAuditing','wpUtentiCombo_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpUtentiCombo_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpUtentiCombo --></div>

			
	
	<div id="p_wpComandiRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpComandiRicerca -->
	
	

<div class="widgetsPanel" id="wpComandiRicerca">
	
	<customtag:widgetsPanel id="wpComandiRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneAuditing','btnCerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnCerca -->
<s:submit name="widg_btnCerca" id="widg_btnCerca" method="handleBtnCerca_CLICKED"
	key="cpGestioneAuditing.btnCerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpGestioneAuditing','btnCerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpComandiRicerca --></div>

			
	
	<div id="p_wpTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpTabella -->
	
	

<div class="widgetsPanel" id="wpTabella">
	

	
	
<s:if test="isWidgetVisible('cpGestioneAuditing','tAuditItems')" >

	
<div class="tableWidget">


<!-- widget tAuditItems -->
	<csiuicore:ajaxify id="p_wpTabella" 
		widgetType="table" 
		pageId="cpGestioneAuditing">
<s:set name="cpGestioneAuditing_tAuditItems_clearStatus" value="isTableClearStatus('cpGestioneAuditing_tAuditItems')" />
<display:table name="appDataaudities"
               excludedParams="*"
               export="true"
               id="widg_tAuditItems"
               pagesize="20"
               requestURI="cpGestioneAuditing.do"
               keepStatus="true"
               clearStatus="${cpGestioneAuditing_tAuditItems_clearStatus}"
               uid="row_tAuditItems"
               summary="" 
               
               class="dataTable">
	
		<display:column property="dataAudit" titleKey="cpGestioneAuditing.tAuditItems.dataAudit.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="dataAudit" titleKey="cpGestioneAuditing.tAuditItems.dataAudit.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="dataAudit" titleKey="cpGestioneAuditing.tAuditItems.dataAudit.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="descrApplicazione" titleKey="cpGestioneAuditing.tAuditItems.descrApplicazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrApplicazione" titleKey="cpGestioneAuditing.tAuditItems.descrApplicazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrApplicazione" titleKey="cpGestioneAuditing.tAuditItems.descrApplicazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrOperazione" titleKey="cpGestioneAuditing.tAuditItems.descrOperazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrOperazione" titleKey="cpGestioneAuditing.tAuditItems.descrOperazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrOperazione" titleKey="cpGestioneAuditing.tAuditItems.descrOperazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrUser" titleKey="cpGestioneAuditing.tAuditItems.descrUser.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrUser" titleKey="cpGestioneAuditing.tAuditItems.descrUser.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrUser" titleKey="cpGestioneAuditing.tAuditItems.descrUser.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="idTransazione" titleKey="cpGestioneAuditing.tAuditItems.idTransazione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="idTransazione" titleKey="cpGestioneAuditing.tAuditItems.idTransazione.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="idTransazione" titleKey="cpGestioneAuditing.tAuditItems.idTransazione.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="idaction" titleKey="cpGestioneAuditing.tAuditItems.idaction.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="idaction" titleKey="cpGestioneAuditing.tAuditItems.idaction.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="idaction" titleKey="cpGestioneAuditing.tAuditItems.idaction.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="codfisc" titleKey="cpGestioneAuditing.tAuditItems.codfisc.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="codfisc" titleKey="cpGestioneAuditing.tAuditItems.codfisc.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="codfisc" titleKey="cpGestioneAuditing.tAuditItems.codfisc.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabella --></div>

	

		
	
</div>

	<!-- endFragment:p_pFiltri --></div>

	

	<!-- endFragment:p_pCenter --></div>

			</div>
		</div>
		<!-- ***** FINE UDLRC Layout CENTER PANEL ***** -->



	</div>
	<!-- ================ FINE UDLRC Layout WEST-CENTER-EAST WRAPPER ================ -->



</div>
<!-- #################### FINE PANNELLO CONTENUTI #################### -->




</s:form>


	<s:include value="fragments/footer.jsp" ></s:include>
