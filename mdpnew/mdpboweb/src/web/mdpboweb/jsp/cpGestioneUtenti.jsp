<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneUtentiAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneUtenti -->
<s:form id="cpGestioneUtenti" action="cpGestioneUtenti" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneUtenti','mnuView')" >

	


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
				<h3><s:text name="cpGestioneUtenti.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGestioneUtenti" class="stdMessagePanelBlock"><!-- startFragment:p_smpGestioneUtenti -->
	
	
<div class="stdMessagePanel" id="smpGestioneUtenti">
	<customtag:stdMessagePanel id="smpGestioneUtenti" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGestioneUtenti --></div>

			
	
	<div id="p_wpTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpTabella -->
	
	
<h4 class="wpLabel">Elenco utenti </h4>
<div class="widgetsPanel" id="wpTabella">
	

	
	
<s:if test="isWidgetVisible('cpGestioneUtenti','tListaUtenti')" >

	
<div class="tableWidget">


<!-- widget tListaUtenti -->
	<csiuicore:ajaxify id="p_wpTabella" 
		widgetType="table" 
		pageId="cpGestioneUtenti">
<s:set name="cpGestioneUtenti_tListaUtenti_clearStatus" value="isTableClearStatus('cpGestioneUtenti_tListaUtenti')" />
<display:table name="appDatautenti"
               excludedParams="*"
               export="true"
               id="widg_tListaUtenti"
               pagesize="20"
               requestURI="cpGestioneUtenti.do"
               keepStatus="true"
               clearStatus="${cpGestioneUtenti_tListaUtenti_clearStatus}"
               uid="row_tListaUtenti"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tListaUtenti.idUtente}" name="appDataselettoreUtente" id="%{'tListaUtenti-editcell-'+ (#attr.row_tListaUtenti_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="descrUtente" titleKey="cpGestioneUtenti.tListaUtenti.descrUtente.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrUtente" titleKey="cpGestioneUtenti.tListaUtenti.descrUtente.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrUtente" titleKey="cpGestioneUtenti.tListaUtenti.descrUtente.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="codFisc" titleKey="cpGestioneUtenti.tListaUtenti.codFisc.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="codFisc" titleKey="cpGestioneUtenti.tListaUtenti.codFisc.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="codFisc" titleKey="cpGestioneUtenti.tListaUtenti.codFisc.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabella --></div>

			
	
	<div id="p_wpAzioniTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniTabella -->
	
	

<div class="widgetsPanel" id="wpAzioniTabella">
	
	<customtag:widgetsPanel id="wpAzioniTabellaTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneUtenti','btNuovoUtente')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btNuovoUtente -->
<s:submit name="widg_btNuovoUtente" id="widg_btNuovoUtente" method="handleBtNuovoUtente_CLICKED"
	key="cpGestioneUtenti.btNuovoUtente.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneUtenti','btNuovoUtente')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneUtenti','btModUtente')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btModUtente -->
<s:submit name="widg_btModUtente" id="widg_btModUtente" method="handleBtModUtente_CLICKED"
	key="cpGestioneUtenti.btModUtente.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpGestioneUtenti','btModUtente')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneUtenti','btEliminaUtente')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btEliminaUtente -->
<s:submit name="widg_btEliminaUtente" id="widg_btEliminaUtente" method="handleBtEliminaUtente_CLICKED"
	key="cpGestioneUtenti.btEliminaUtente.label" cssClass="buttonWidget deleteItem"
	disabled="isWidgetDisabled('cpGestioneUtenti','btEliminaUtente')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniTabella --></div>

	

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
