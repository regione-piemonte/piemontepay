<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpDettaglioGatewayNewAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpDettaglioGatewayNew -->
<s:form id="cpDettaglioGatewayNew" action="cpDettaglioGatewayNew" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','mnuView')" >

	


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

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettaglioGateway" class="stdMessagePanelBlock"><!-- startFragment:p_smpDettaglioGateway -->
	
	
<div class="stdMessagePanel" id="smpDettaglioGateway">
	<customtag:stdMessagePanel id="smpDettaglioGateway" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioGateway --></div>

			
	
	
	<div class="tabSetPanelContent">
	<div id="p_tstGateway" class="tabSetPanelBlock"><!-- startFragment:p_tstGateway -->
	
	
<div class="tabSetPanel" id="tstGateway">
	<ul>
		<s:url id="cpDettaglioGatewayNew_tstGateway_pDatiGeneraliGateway_URL" action="cpDettaglioGatewayNew" method="handleChangeTab" includeParams="get">
			<s:param name="selectedTabKey" value="%{'cpDettaglioGatewayNew_tstGateway_selectedMultiPanel'}" />
			<s:param name="selectedTabValue" value="%{'cpDettaglioGatewayNew_tstGateway_pDatiGeneraliGateway'}" />
		</s:url>
		<s:if test="%{#session.cpDettaglioGatewayNew_tstGateway_selectedMultiPanel == 'cpDettaglioGatewayNew_tstGateway_pDatiGeneraliGateway' || #session.cpDettaglioGatewayNew_tstGateway_selectedMultiPanel == null}">
			<li class="item-1 active"><span class="activeItem"><s:text name="cpDettaglioGatewayNew.pDatiGeneraliGateway.label" /></span></li>
		</s:if>
		<s:else>
			<li class="item-1"><s:a href="%{cpDettaglioGatewayNew_tstGateway_pDatiGeneraliGateway_URL}"><s:text name="cpDettaglioGatewayNew.pDatiGeneraliGateway.label" /></s:a></li>
		</s:else>
	</ul>
</div>


	
		

		
<s:if test="#session.cpDettaglioGatewayNew_tstGateway_selectedMultiPanel == 'cpDettaglioGatewayNew_tstGateway_pDatiGeneraliGateway' || #session.cpDettaglioGatewayNew_tstGateway_selectedMultiPanel == null">
	<s:include value="/jsp/tabs/cpDettaglioGatewayNew_tstGateway_pDatiGeneraliGateway.jsp" ></s:include>
</s:if>
	<!-- endFragment:p_tstGateway --></div>
	</div>

	

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
