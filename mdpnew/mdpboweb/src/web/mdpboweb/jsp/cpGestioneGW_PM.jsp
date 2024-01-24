<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneGW_PMAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneGW_PM -->
<s:form id="cpGestioneGW_PM" action="cpGestioneGW_PM" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneGW_PM','mnuView')" >

	


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
				<h3><s:text name="cpGestioneGW_PM.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGestioneGW_PM" class="stdMessagePanelBlock"><!-- startFragment:p_smpGestioneGW_PM -->
	
	
<div class="stdMessagePanel" id="smpGestioneGW_PM">
	<customtag:stdMessagePanel id="smpGestioneGW_PM" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGestioneGW_PM --></div>

			
	
	
	<div class="tabSetPanelContent">
	<div id="p_tspGW_PM" class="tabSetPanelBlock"><!-- startFragment:p_tspGW_PM -->
	
	
<h4 class="tspLabel"><s:text name="cpGestioneGW_PM.tspGW_PM.label" /></h4>
<div class="tabSetPanel" id="tspGW_PM">
	<ul>
		<s:url id="cpGestioneGW_PM_tspGW_PM_pGateways_URL" action="cpGestioneGW_PM" method="handleChangeTab" includeParams="get">
			<s:param name="selectedTabKey" value="%{'cpGestioneGW_PM_tspGW_PM_selectedMultiPanel'}" />
			<s:param name="selectedTabValue" value="%{'cpGestioneGW_PM_tspGW_PM_pGateways'}" />
		</s:url>
		<s:if test="%{#session.cpGestioneGW_PM_tspGW_PM_selectedMultiPanel == 'cpGestioneGW_PM_tspGW_PM_pGateways' || #session.cpGestioneGW_PM_tspGW_PM_selectedMultiPanel == null}">
			<li class="item-1 active"><span class="activeItem"><s:text name="cpGestioneGW_PM.pGateways.label" /></span></li>
		</s:if>
		<s:else>
			<li class="item-1"><s:a href="%{cpGestioneGW_PM_tspGW_PM_pGateways_URL}"><s:text name="cpGestioneGW_PM.pGateways.label" /></s:a></li>
		</s:else>
		<s:url id="cpGestioneGW_PM_tspGW_PM_pModalitaPagamento_URL" action="cpGestioneGW_PM" method="handleChangeTab" includeParams="get">
			<s:param name="selectedTabKey" value="%{'cpGestioneGW_PM_tspGW_PM_selectedMultiPanel'}" />
			<s:param name="selectedTabValue" value="%{'cpGestioneGW_PM_tspGW_PM_pModalitaPagamento'}" />
		</s:url>
		<s:if test="%{#session.cpGestioneGW_PM_tspGW_PM_selectedMultiPanel == 'cpGestioneGW_PM_tspGW_PM_pModalitaPagamento'}">
			<li class="item-2 active"><span class="activeItem"><s:text name="cpGestioneGW_PM.pModalitaPagamento.label" /></span></li>
		</s:if>
		<s:else>
			<li class="item-2"><s:a href="%{cpGestioneGW_PM_tspGW_PM_pModalitaPagamento_URL}"><s:text name="cpGestioneGW_PM.pModalitaPagamento.label" /></s:a></li>
		</s:else>
	</ul>
</div>


	
		

		
<s:if test="#session.cpGestioneGW_PM_tspGW_PM_selectedMultiPanel == 'cpGestioneGW_PM_tspGW_PM_pGateways' || #session.cpGestioneGW_PM_tspGW_PM_selectedMultiPanel == null">
	<s:include value="/jsp/tabs/cpGestioneGW_PM_tspGW_PM_pGateways.jsp" ></s:include>
</s:if>
		

		
<s:if test="#session.cpGestioneGW_PM_tspGW_PM_selectedMultiPanel == 'cpGestioneGW_PM_tspGW_PM_pModalitaPagamento'">
	<s:include value="/jsp/tabs/cpGestioneGW_PM_tspGW_PM_pModalitaPagamento.jsp" ></s:include>
</s:if>
	<!-- endFragment:p_tspGW_PM --></div>
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
