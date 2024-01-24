<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %>

<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpDettaglioPaymentMode -->
<s:form id="cpDettaglioPaymentMode" action="cpDettaglioPaymentMode" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioPaymentMode','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioPaymentMode.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettaglioPM" class="stdMessagePanelBlock"><!-- startFragment:p_smpDettaglioPM -->
	
	
<div class="stdMessagePanel" id="smpDettaglioPM">
	<customtag:stdMessagePanel id="smpDettaglioPM" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioPM --></div>

			
	
	<div id="p_wpDettaglioPaymentMode" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioPaymentMode -->
	
	

<div class="widgetsPanel" id="wpDettaglioPaymentMode">
	
	<customtag:widgetsPanel id="wpDettaglioPaymentModeTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioPaymentMode','txPaimentMode')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioPaymentMode.txPaimentMode.label')}" labelFor="widg_txPaimentMode" errorFor="appDatapaymentMode.idPayment" labelId="txPaimentModeLbl"   >


<!-- widget txPaimentMode -->
<s:property value="appDatapaymentMode.idPayment" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioPaymentMode','tfDescrPM')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioPaymentMode.tfDescrPM.label')}" labelFor="widg_tfDescrPM" errorFor="appDatapaymentMode.descrPayment" labelId="tfDescrPMLbl"
	  >


<!-- widget tfDescrPM -->
<s:textfield name="appDatapaymentMode.descrPayment" id="widg_tfDescrPM"
	disabled="isWidgetDisabled('cpDettaglioPaymentMode','tfDescrPM')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioPaymentMode --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioPaymentMode','btnIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnIndietro -->
<s:submit name="widg_btnIndietro" id="widg_btnIndietro" method="handleBtnIndietro_CLICKED"
	key="cpDettaglioPaymentMode.btnIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioPaymentMode','btnIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioPaymentMode','btnSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnSalva -->
<s:submit name="widg_btnSalva" id="widg_btnSalva" method="handleBtnSalva_CLICKED"
	key="cpDettaglioPaymentMode.btnSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioPaymentMode','btnSalva')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioni --></div>

	

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
