<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpDettaglioApplicazioneAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpDettaglioApplicazione -->
<s:form id="cpDettaglioApplicazione" action="cpDettaglioApplicazione" method="post" enctype="multipart/form-data">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioApplicazione','mnuView')" >

	


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

	
	

	
	
			
	
	<div id="p_smpDettaglioApplicazione" class="stdMessagePanelBlock"><!-- startFragment:p_smpDettaglioApplicazione -->
	
	
<div class="stdMessagePanel" id="smpDettaglioApplicazione">
	<customtag:stdMessagePanel id="smpDettaglioApplicazione" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioApplicazione --></div>

			
	
	
	<div class="tabSetPanelContent">
	<div id="p_tspApplicazione" class="tabSetPanelBlock"><!-- startFragment:p_tspApplicazione -->
	
	
<div class="tabSetPanel" id="tspApplicazione">
	<ul>
		<s:url id="cpDettaglioApplicazione_tspApplicazione_pDatiGenerali_URL" action="cpDettaglioApplicazione" method="handleChangeTab" includeParams="get">
			<s:param name="selectedTabKey" value="%{'cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel'}" />
			<s:param name="selectedTabValue" value="%{'cpDettaglioApplicazione_tspApplicazione_pDatiGenerali'}" />
		</s:url>
		<s:if test="%{#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pDatiGenerali' || #session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == null}">
			<li class="item-1 active"><span class="activeItem"><s:text name="cpDettaglioApplicazione.pDatiGenerali.label" /></span></li>
		</s:if>
		<s:else>
			<li class="item-1"><s:a href="%{cpDettaglioApplicazione_tspApplicazione_pDatiGenerali_URL}"><s:text name="cpDettaglioApplicazione.pDatiGenerali.label" /></s:a></li>
		</s:else>
		<s:url id="cpDettaglioApplicazione_tspApplicazione_pGatewayPaymentMode_URL" action="cpDettaglioApplicazione" method="handleChangeTab" includeParams="get">
			<s:param name="selectedTabKey" value="%{'cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel'}" />
			<s:param name="selectedTabValue" value="%{'cpDettaglioApplicazione_tspApplicazione_pGatewayPaymentMode'}" />
		</s:url>
		<s:if test="%{#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pGatewayPaymentMode'}">
			<li class="item-2 active"><span class="activeItem"><s:text name="cpDettaglioApplicazione.pGatewayPaymentMode.label" /></span></li>
		</s:if>
		<s:else>
			<li class="item-2"><s:a href="%{cpDettaglioApplicazione_tspApplicazione_pGatewayPaymentMode_URL}"><s:text name="cpDettaglioApplicazione.pGatewayPaymentMode.label" /></s:a></li>
		</s:else>
		<s:url id="cpDettaglioApplicazione_tspApplicazione_pAssociazioneEnti_URL" action="cpDettaglioApplicazione" method="handleChangeTab" includeParams="get">
			<s:param name="selectedTabKey" value="%{'cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel'}" />
			<s:param name="selectedTabValue" value="%{'cpDettaglioApplicazione_tspApplicazione_pAssociazioneEnti'}" />
		</s:url>
		<s:if test="%{#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pAssociazioneEnti'}">
			<li class="item-3 active"><span class="activeItem"><s:text name="cpDettaglioApplicazione.pAssociazioneEnti.label" /></span></li>
		</s:if>
		<s:else>
			<li class="item-3"><s:a href="%{cpDettaglioApplicazione_tspApplicazione_pAssociazioneEnti_URL}"><s:text name="cpDettaglioApplicazione.pAssociazioneEnti.label" /></s:a></li>
		</s:else>
		<s:url id="cpDettaglioApplicazione_tspApplicazione_pIbanEnteApplication_URL" action="cpDettaglioApplicazione" method="handleChangeTab" includeParams="get">
			<s:param name="selectedTabKey" value="%{'cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel'}" />
			<s:param name="selectedTabValue" value="%{'cpDettaglioApplicazione_tspApplicazione_pIbanEnteApplication'}" />
		</s:url>
		<s:if test="%{#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pIbanEnteApplication'}">
			<li class="item-4 active"><span class="activeItem"><s:text name="cpDettaglioApplicazione.pIbanEnteApplication.label" /></span></li>
		</s:if>
		<s:else>
			<li class="item-4"><s:a href="%{cpDettaglioApplicazione_tspApplicazione_pIbanEnteApplication_URL}"><s:text name="cpDettaglioApplicazione.pIbanEnteApplication.label" /></s:a></li>
		</s:else>
	</ul>
</div>


	
		

		
<s:if test="#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pDatiGenerali' || #session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == null">
	<s:include value="/jsp/tabs/cpDettaglioApplicazione_tspApplicazione_pDatiGenerali.jsp" ></s:include>
</s:if>
		

		
<s:if test="#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pGatewayPaymentMode'">
	<s:include value="/jsp/tabs/cpDettaglioApplicazione_tspApplicazione_pGatewayPaymentMode.jsp" ></s:include>
</s:if>
		

		
<s:if test="#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pAssociazioneEnti'">
	<s:include value="/jsp/tabs/cpDettaglioApplicazione_tspApplicazione_pAssociazioneEnti.jsp" ></s:include>
</s:if>
		

		
<s:if test="#session.cpDettaglioApplicazione_tspApplicazione_selectedMultiPanel == 'cpDettaglioApplicazione_tspApplicazione_pIbanEnteApplication'">
	<s:include value="/jsp/tabs/cpDettaglioApplicazione_tspApplicazione_pIbanEnteApplication.jsp" ></s:include>
</s:if>
	<!-- endFragment:p_tspApplicazione --></div>
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
