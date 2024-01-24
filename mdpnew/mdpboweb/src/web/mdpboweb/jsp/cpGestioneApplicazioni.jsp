<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneApplicazioniAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneApplicazioni -->
<s:form id="cpGestioneApplicazioni" action="cpGestioneApplicazioni" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneApplicazioni','mnuView')" >

	


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
				<h3><s:text name="cpGestioneApplicazioni.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGestioneApplicazioni" class="stdMessagePanelBlock"><!-- startFragment:p_smpGestioneApplicazioni -->
	
	
<div class="stdMessagePanel" id="smpGestioneApplicazioni">
	<customtag:stdMessagePanel id="smpGestioneApplicazioni" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGestioneApplicazioni --></div>

			
	
	<div id="p_wpTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpTabella -->
	
	
<h4 class="wpLabel">Elenco applicazioni </h4>
<div class="widgetsPanel" id="wpTabella">
	

	
	
<s:if test="isWidgetVisible('cpGestioneApplicazioni','tListaApplicazioni')" >

	
<div class="tableWidget">


<!-- widget tListaApplicazioni -->
	<csiuicore:ajaxify id="p_wpTabella" 
		widgetType="table" 
		pageId="cpGestioneApplicazioni">
<s:set name="cpGestioneApplicazioni_tListaApplicazioni_clearStatus" value="isTableClearStatus('cpGestioneApplicazioni_tListaApplicazioni')" />
<display:table name="appDataapplicazioni"
               excludedParams="*"
               export="true"
               id="widg_tListaApplicazioni"
               pagesize="20"
               requestURI="cpGestioneApplicazioni.do"
               keepStatus="true"
               clearStatus="${cpGestioneApplicazioni_tListaApplicazioni_clearStatus}"
               uid="row_tListaApplicazioni"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tListaApplicazioni.idApplicazione}" name="appDataselettoreIdApplicazione" id="%{'tListaApplicazioni-editcell-'+ (#attr.row_tListaApplicazioni_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="idApplicazione" titleKey="cpGestioneApplicazioni.tListaApplicazioni.idApplicazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="idApplicazione" titleKey="cpGestioneApplicazioni.tListaApplicazioni.idApplicazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="idApplicazione" titleKey="cpGestioneApplicazioni.tListaApplicazioni.idApplicazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="nomeApplicazione" titleKey="cpGestioneApplicazioni.tListaApplicazioni.nomeApplicazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="nomeApplicazione" titleKey="cpGestioneApplicazioni.tListaApplicazioni.nomeApplicazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="nomeApplicazione" titleKey="cpGestioneApplicazioni.tListaApplicazioni.nomeApplicazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="referenteCSI" titleKey="cpGestioneApplicazioni.tListaApplicazioni.referenteCSI.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="referenteCSI" titleKey="cpGestioneApplicazioni.tListaApplicazioni.referenteCSI.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="referenteCSI" titleKey="cpGestioneApplicazioni.tListaApplicazioni.referenteCSI.export.label"
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
	

	
	
<s:if test="isWidgetVisible('cpGestioneApplicazioni','btnNuovaApplicazione')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnNuovaApplicazione -->
<s:submit name="widg_btnNuovaApplicazione" id="widg_btnNuovaApplicazione" method="handleBtnNuovaApplicazione_CLICKED"
	key="cpGestioneApplicazioni.btnNuovaApplicazione.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneApplicazioni','btnNuovaApplicazione')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneApplicazioni','btnModificaApplicazione')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnModificaApplicazione -->
<s:submit name="widg_btnModificaApplicazione" id="widg_btnModificaApplicazione" method="handleBtnModificaApplicazione_CLICKED"
	key="cpGestioneApplicazioni.btnModificaApplicazione.label" cssClass="buttonWidget detail"
	disabled="isWidgetDisabled('cpGestioneApplicazioni','btnModificaApplicazione')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneApplicazioni','btnCancellaApplicazione')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnCancellaApplicazione -->
<s:submit name="widg_btnCancellaApplicazione" id="widg_btnCancellaApplicazione" method="handleBtnCancellaApplicazione_CLICKED"
	key="cpGestioneApplicazioni.btnCancellaApplicazione.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpGestioneApplicazioni','btnCancellaApplicazione')" />

	
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
