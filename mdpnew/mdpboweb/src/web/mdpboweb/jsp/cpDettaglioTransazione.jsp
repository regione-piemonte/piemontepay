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



<!-- pageId:cpDettaglioTransazione -->
<s:form id="cpDettaglioTransazione" action="cpDettaglioTransazione" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioTransazione','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioTransazione.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettaglioTransazione" class="stdMessagePanelBlock"><!-- startFragment:p_smpDettaglioTransazione -->
	
	
<div class="stdMessagePanel" id="smpDettaglioTransazione">
	<customtag:stdMessagePanel id="smpDettaglioTransazione" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioTransazione --></div>

			
	
	<div id="p_wpDettaglioTransazione" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioTransazione -->
	
	

<div class="widgetsPanel" id="wpDettaglioTransazione">
	
	<customtag:widgetsPanel id="wpDettaglioTransazioneTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptIdTransazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptIdTransazione.label')}" labelFor="widg_ptIdTransazione" errorFor="appDatatransazione.idTransazione" labelId="ptIdTransazioneLbl"  position="first" >


<!-- widget ptIdTransazione -->
<s:property value="appDatatransazione.idTransazione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptIdApplicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptIdApplicazione.label')}" labelFor="widg_ptIdApplicazione" errorFor="appDatatransazione.idApplicazione" labelId="ptIdApplicazioneLbl"  position="first" >


<!-- widget ptIdApplicazione -->
<s:property value="appDatatransazione.idApplicazione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptDataInizio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptDataInizio.label')}" labelFor="widg_ptDataInizio" errorFor="appDatatransazione.dataInizio" labelId="ptDataInizioLbl"  position="first" >


<!-- widget ptDataInizio -->
<s:property value="appDatatransazione.dataInizio" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptDataFine')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptDataFine.label')}" labelFor="widg_ptDataFine" errorFor="appDatatransazione.dataFine" labelId="ptDataFineLbl"  position="first" >


<!-- widget ptDataFine -->
<s:property value="appDatatransazione.dataFine" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptGatewayPagamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptGatewayPagamento.label')}" labelFor="widg_ptGatewayPagamento" errorFor="appDatatransazione.descrGateway" labelId="ptGatewayPagamentoLbl"  position="first" >


<!-- widget ptGatewayPagamento -->
<s:property value="appDatatransazione.descrGateway" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptModalitaPagamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptModalitaPagamento.label')}" labelFor="widg_ptModalitaPagamento" errorFor="appDatatransazione.descrPayment" labelId="ptModalitaPagamentoLbl"  position="first" >


<!-- widget ptModalitaPagamento -->
<s:property value="appDatatransazione.descrPayment" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_6_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_6_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptImportoTransazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptImportoTransazione.label')}" labelFor="widg_ptImportoTransazione" errorFor="appDatatransazione.importoTransazione" labelId="ptImportoTransazioneLbl"  position="first" tdStyleClass="numbers">


<!-- widget ptImportoTransazione -->
<s:property value="appDatatransazione.importoTransazione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_7_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_7_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptStatoAttuale')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptStatoAttuale.label')}" labelFor="widg_ptStatoAttuale" errorFor="appDatatransazione.descrStato" labelId="ptStatoAttualeLbl"  position="first" >


<!-- widget ptStatoAttuale -->
<s:property value="appDatatransazione.descrStato" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_8_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_8_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptbasketId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptbasketId.label')}" labelFor="widg_ptbasketId" errorFor="appDatatransazione.basketId" labelId="ptbasketIdLbl"  position="first" >


<!-- widget ptbasketId -->
<s:property value="appDatatransazione.basketId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_9_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_9_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','tpmerchantId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.tpmerchantId.label')}" labelFor="widg_tpmerchantId" errorFor="appDatatransazione.merchantId" labelId="tpmerchantIdLbl"  position="first" >


<!-- widget tpmerchantId -->
<s:property value="appDatatransazione.merchantId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_10_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_10_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptpaymentid')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptpaymentid.label')}" labelFor="widg_ptpaymentid" errorFor="appDatatransazione.paymentid" labelId="ptpaymentidLbl"  position="first" >


<!-- widget ptpaymentid -->
<s:property value="appDatatransazione.paymentid" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_11_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_11_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptpayurl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptpayurl.label')}" labelFor="widg_ptpayurl" errorFor="appDatatransazione.payurl" labelId="ptpayurlLbl"  position="first" >


<!-- widget ptpayurl -->
<s:property value="appDatatransazione.payurl" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_12_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_12_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','ptpgresultcode')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.ptpgresultcode.label')}" labelFor="widg_ptpgresultcode" errorFor="appDatatransazione.pgresultcode" labelId="ptpgresultcodeLbl"  position="first" >


<!-- widget ptpgresultcode -->
<s:property value="appDatatransazione.pgresultcode" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_13_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_13_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','startDate')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.startDate.label')}" labelFor="widg_startDate" errorFor="appDatatransazione.startDate" labelId="startDateLbl"  position="first" >


<!-- widget startDate -->
<s:property value="appDatatransazione.startDate" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_14_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_14_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','tpcodStato')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.tpcodStato.label')}" labelFor="widg_tpcodStato" errorFor="appDatatransazione.codStato" labelId="tpcodStatoLbl"  position="first" tdStyleClass="numbers">


<!-- widget tpcodStato -->
<s:property value="appDatatransazione.codStato" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_15_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_15_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','oldstate')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.oldstate.label')}" labelFor="widg_oldstate" errorFor="appDatatransazione.oldstate" labelId="oldstateLbl"  position="first" tdStyleClass="numbers">


<!-- widget oldstate -->
<s:property value="appDatatransazione.oldstate" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpDettaglioTransazione_16_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioTransazione_16_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioTransazione --></div>

			
	
	<div id="p_wpCambiaStato" class="widgetsPanelBlock"><!-- startFragment:p_wpCambiaStato -->
	
	

<div class="widgetsPanel" id="wpCambiaStato">
	
	<customtag:widgetsPanel id="wpCambiaStatoTbl" columns="8" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioTransazione','cbStato')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioTransazione.cbStato.label')}" labelFor="widg_cbStato" errorFor="appDatatransazione.codNuovoStato" labelId="cbStatoLbl"
	position="first"  >


<!-- widget cbStato -->
<s:select name="appDatatransazione.codNuovoStato" id="widg_cbStato"
           
          list="appDatastatiTransazionexCambio"
          disabled="isWidgetDisabled('cpDettaglioTransazione','cbStato')"
          listKey="codStato"
          listValue="descrStato"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','btnCambiaStato')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnCambiaStato -->
<s:submit name="widg_btnCambiaStato" id="widg_btnCambiaStato" method="handleBtnCambiaStato_CLICKED"
	key="cpDettaglioTransazione.btnCambiaStato.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioTransazione','btnCambiaStato')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpCambiaStato_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpCambiaStato_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','wpCambiaStato_1_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpCambiaStato_1_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpCambiaStato --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="8" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioTransazione','btnIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnIndietro -->
<s:submit name="widg_btnIndietro" id="widg_btnIndietro" method="handleBtnIndietro_CLICKED"
	key="cpDettaglioTransazione.btnIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioTransazione','btnIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','btnVerificaStato')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnVerificaStato -->
<s:submit name="widg_btnVerificaStato" id="widg_btnVerificaStato" method="handleBtnVerificaStato_CLICKED"
	key="cpDettaglioTransazione.btnVerificaStato.label" cssClass="buttonWidget lookup"
	disabled="isWidgetDisabled('cpDettaglioTransazione','btnVerificaStato')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','btStorno')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btStorno -->
<s:submit name="widg_btStorno" id="widg_btStorno" method="handleBtStorno_CLICKED"
	key="cpDettaglioTransazione.btStorno.label" cssClass="buttonWidget lookup"
	disabled="isWidgetDisabled('cpDettaglioTransazione','btStorno')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioTransazione','btconferma')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btconferma -->
<s:submit name="widg_btconferma" id="widg_btconferma" method="handleBtconferma_CLICKED"
	key="cpDettaglioTransazione.btconferma.label" cssClass="buttonWidget lookup"
	disabled="isWidgetDisabled('cpDettaglioTransazione','btconferma')" />

	
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
