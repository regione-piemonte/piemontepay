<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpStoricoErroriAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpStoricoErrori -->
<s:form id="cpStoricoErrori" action="cpStoricoErrori" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpStoricoErrori','mnuView')" >

	


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
				<h3><s:text name="cpStoricoErrori.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGestioneTransazioni" class="stdMessagePanelBlock"><!-- startFragment:p_smpGestioneTransazioni -->
	
	
<div class="stdMessagePanel" id="smpGestioneTransazioni">
	<customtag:stdMessagePanel id="smpGestioneTransazioni" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGestioneTransazioni --></div>

			
	
	<div id="p_wpRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpRicerca -->
	
	

<div class="widgetsPanel" id="wpRicerca">
	
	<customtag:widgetsPanel id="wpRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpStoricoErrori','idTransazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStoricoErrori.idTransazione.label')}" labelFor="widg_idTransazione" errorFor="appDatacercaErrore.idTransazione" labelId="idTransazioneLbl"
	  >


<!-- widget idTransazione -->
<s:textfield name="appDatacercaErrore.idTransazione" id="widg_idTransazione"
	disabled="isWidgetDisabled('cpStoricoErrori','idTransazione')"
	size="25" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpStoricoErrori','cbApplicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStoricoErrori.cbApplicazione.label')}" labelFor="widg_cbApplicazione" errorFor="appDatacercaErrore.idApplicazione" labelId="cbApplicazioneLbl"
	  >


<!-- widget cbApplicazione -->
<s:select name="appDatacercaErrore.idApplicazione" id="widg_cbApplicazione"
          headerKey="" headerValue="" 
          list="appDataapplicazioni"
          disabled="isWidgetDisabled('cpStoricoErrori','cbApplicazione')"
          listKey="idApplicazione"
          listValue="nomeApplicazione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpStoricoErrori','cbGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStoricoErrori.cbGateway.label')}" labelFor="widg_cbGateway" errorFor="appDatacercaErrore.idGateway" labelId="cbGatewayLbl"
	  >


<!-- widget cbGateway -->
<s:select name="appDatacercaErrore.idGateway" id="widg_cbGateway"
          headerKey="" headerValue="" 
          list="appDatagateways"
          disabled="isWidgetDisabled('cpStoricoErrori','cbGateway')"
          listKey="idGateway"
          listValue="descrGateway"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpStoricoErrori','cbDallaData')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStoricoErrori.cbDallaData.label')}" labelFor="widg_cbDallaData" errorFor="appDatacercaErrore.cercaDataInizio" labelId="cbDallaDataLbl"
	  >


<!-- widget cbDallaData -->
<s:textfield name="appDatacercaErrore.cercaDataInizio" id="widg_cbDallaData"
	disabled="isWidgetDisabled('cpStoricoErrori','cbDallaData')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpStoricoErrori','cbAllaData')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStoricoErrori.cbAllaData.label')}" labelFor="widg_cbAllaData" errorFor="appDatacercaErrore.cercaDataFine" labelId="cbAllaDataLbl"
	  >


<!-- widget cbAllaData -->
<s:textfield name="appDatacercaErrore.cercaDataFine" id="widg_cbAllaData"
	disabled="isWidgetDisabled('cpStoricoErrori','cbAllaData')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpRicerca --></div>

			
	
	<div id="p_wpAzioniRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniRicerca -->
	
	

<div class="widgetsPanel" id="wpAzioniRicerca">
	
	<customtag:widgetsPanel id="wpAzioniRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpStoricoErrori','btnCerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnCerca -->
<s:submit name="widg_btnCerca" id="widg_btnCerca" method="handleBtnCerca_CLICKED"
	key="cpStoricoErrori.btnCerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpStoricoErrori','btnCerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniRicerca --></div>

			
	
	<div id="p_wpTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpTabella -->
	
	

<div class="widgetsPanel" id="wpTabella">
	

	
	
<s:if test="isWidgetVisible('cpStoricoErrori','tRicerca')" >

	
<div class="tableWidget">


<!-- widget tRicerca -->
	<csiuicore:ajaxify id="p_wpTabella" 
		widgetType="table" 
		pageId="cpStoricoErrori">
<s:set name="cpStoricoErrori_tRicerca_clearStatus" value="isTableClearStatus('cpStoricoErrori_tRicerca')" />
<display:table name="appDataerrori"
               excludedParams="*"
               export="true"
               id="widg_tRicerca"
               pagesize="20"
               requestURI="cpStoricoErrori.do"
               keepStatus="true"
               clearStatus="${cpStoricoErrori_tRicerca_clearStatus}"
               uid="row_tRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tRicerca.idErrore}" name="appDataselettoreIdErrore" id="%{'tRicerca-editcell-'+ (#attr.row_tRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="nomeApplicazione" titleKey="cpStoricoErrori.tRicerca.nomeApplicazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="nomeApplicazione" titleKey="cpStoricoErrori.tRicerca.nomeApplicazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="nomeApplicazione" titleKey="cpStoricoErrori.tRicerca.nomeApplicazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="idTransazione" titleKey="cpStoricoErrori.tRicerca.idTransazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="idTransazione" titleKey="cpStoricoErrori.tRicerca.idTransazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="idTransazione" titleKey="cpStoricoErrori.tRicerca.idTransazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrGateway" titleKey="cpStoricoErrori.tRicerca.descrGateway.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrGateway" titleKey="cpStoricoErrori.tRicerca.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrGateway" titleKey="cpStoricoErrori.tRicerca.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="importoTransazione" titleKey="cpStoricoErrori.tRicerca.importoTransazione.label" 
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0.00}"  class="numbers"  media="html" />

		<display:column property="importoTransazione" titleKey="cpStoricoErrori.tRicerca.importoTransazione.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="importoTransazione" titleKey="cpStoricoErrori.tRicerca.importoTransazione.export.label"
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0.00}" 
			media="pdf" />	
		<display:column property="dataTransazione" titleKey="cpStoricoErrori.tRicerca.dataTransazione.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="dataTransazione" titleKey="cpStoricoErrori.tRicerca.dataTransazione.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="dataTransazione" titleKey="cpStoricoErrori.tRicerca.dataTransazione.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="dettaglioErrore" titleKey="cpStoricoErrori.tRicerca.dettaglioErrore.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="dettaglioErrore" titleKey="cpStoricoErrori.tRicerca.dettaglioErrore.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="dettaglioErrore" titleKey="cpStoricoErrori.tRicerca.dettaglioErrore.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabella --></div>

			
	
	<div id="p_pComandiTabella" class="widgetsPanelBlock"><!-- startFragment:p_pComandiTabella -->
	
	

<div class="widgetsPanel" id="pComandiTabella">
	
	<customtag:widgetsPanel id="pComandiTabellaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpStoricoErrori','btnShowError')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnShowError -->
<s:submit name="widg_btnShowError" id="widg_btnShowError" method="handleBtnShowError_CLICKED"
	key="cpStoricoErrori.btnShowError.label" cssClass="buttonWidget detail"
	disabled="isWidgetDisabled('cpStoricoErrori','btnShowError')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpStoricoErrori','ptTestoErrore')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget ptTestoErrore -->
<s:property value="appDatacercaErrore.testoErroreCompleto" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_pComandiTabella --></div>

	

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
