<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpVerificaFlussiAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpVerificaFlussi -->
<s:form id="cpVerificaFlussi" action="cpVerificaFlussi" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpVerificaFlussi','mnuView')" >

	


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
				<h3><s:text name="cpVerificaFlussi.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_stdMsg" class="stdMessagePanelBlock scroll"><!-- startFragment:p_stdMsg -->
	
	
<div class="stdMessagePanel" id="stdMsg">
	<customtag:stdMessagePanel id="stdMsg" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_stdMsg --></div>

			
	
	<div id="p_wsRiepilogo" class="widgetsPanelBlock"><!-- startFragment:p_wsRiepilogo -->
	
	

<div class="widgetsPanel" id="wsRiepilogo">
	

	
	
<s:if test="isWidgetVisible('cpVerificaFlussi','tbRiepilogo')" >

	
<div class="tableWidget">


<!-- widget tbRiepilogo -->
	<csiuicore:ajaxify id="p_wsRiepilogo" 
		widgetType="table" 
		pageId="cpVerificaFlussi">
<s:set name="cpVerificaFlussi_tbRiepilogo_clearStatus" value="isTableClearStatus('cpVerificaFlussi_tbRiepilogo')" />
<display:table name="appDatalistaFlussoRiversamento"
               excludedParams="*"
               export="true"
               id="widg_tbRiepilogo"
               pagesize="0"
               requestURI="cpVerificaFlussi.do"
               keepStatus="true"
               clearStatus="${cpVerificaFlussi_tbRiepilogo_clearStatus}"
               uid="row_tbRiepilogo"
               summary="" 
               
               class="dataTable">
	
		<display:column property="identificativoflusso" titleKey="cpVerificaFlussi.tbRiepilogo.identificativoflusso.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="identificativoflusso" titleKey="cpVerificaFlussi.tbRiepilogo.identificativoflusso.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="identificativoflusso" titleKey="cpVerificaFlussi.tbRiepilogo.identificativoflusso.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="dataoraflusso" titleKey="cpVerificaFlussi.tbRiepilogo.dataoraflusso.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="dataoraflusso" titleKey="cpVerificaFlussi.tbRiepilogo.dataoraflusso.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="dataoraflusso" titleKey="cpVerificaFlussi.tbRiepilogo.dataoraflusso.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="flussoAcquisito" titleKey="cpVerificaFlussi.tbRiepilogo.flussoAcquisito.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="flussoAcquisito" titleKey="cpVerificaFlussi.tbRiepilogo.flussoAcquisito.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="flussoAcquisito" titleKey="cpVerificaFlussi.tbRiepilogo.flussoAcquisito.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wsRiepilogo --></div>

			
	
	<div id="p_wAction" class="widgetsPanelBlock"><!-- startFragment:p_wAction -->
	
	

<div class="widgetsPanel" id="wAction">
	
	<customtag:widgetsPanel id="wActionTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpVerificaFlussi','btIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btIndietro -->
<s:submit name="widg_btIndietro" id="widg_btIndietro" method="handleBtIndietro_CLICKED"
	key="cpVerificaFlussi.btIndietro.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpVerificaFlussi','btIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wAction --></div>

	

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
