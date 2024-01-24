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



<!-- pageId:cpDettaglioRPT -->
<s:form id="cpDettaglioRPT" action="cpDettaglioRPT" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioRPT','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioRPT.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettaglioRPT" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smpDettaglioRPT -->
	
	
<div class="stdMessagePanel" id="smpDettaglioRPT">
	<customtag:stdMessagePanel id="smpDettaglioRPT" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioRPT --></div>

			
	
	<div id="p_wpDettaglioPRT" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioPRT -->
	
	
<h4 class="wpLabel">Dettaglio RPT </h4>
<div class="widgetsPanel" id="wpDettaglioPRT">
	
	<customtag:widgetsPanel id="wpDettaglioPRTTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioRPT','id')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.id.label')}" labelFor="widg_id" errorFor="appDatarichiestaPagamentoTelematico.id" labelId="idLbl"  position="first" tdStyleClass="numbers">


<!-- widget id -->
<s:property value="appDatarichiestaPagamentoTelematico.id" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','applicationId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.applicationId.label')}" labelFor="widg_applicationId" errorFor="appDatarichiestaPagamentoTelematico.applicationId" labelId="applicationIdLbl"  position="first" >


<!-- widget applicationId -->
<s:property value="appDatarichiestaPagamentoTelematico.applicationId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','transactionId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.transactionId.label')}" labelFor="widg_transactionId" errorFor="appDatarichiestaPagamentoTelematico.transactionId" labelId="transactionIdLbl"  position="first" >


<!-- widget transactionId -->
<s:property value="appDatarichiestaPagamentoTelematico.transactionId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','insertDate')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.insertDate.label')}" labelFor="widg_insertDate" errorFor="appDatarichiestaPagamentoTelematico.insertDate" labelId="insertDateLbl"  position="first" >


<!-- widget insertDate -->
<s:property value="appDatarichiestaPagamentoTelematico.insertDate" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','lastUpdate')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.lastUpdate.label')}" labelFor="widg_lastUpdate" errorFor="appDatarichiestaPagamentoTelematico.lastUpdate" labelId="lastUpdateLbl"  position="first" >


<!-- widget lastUpdate -->
<s:property value="appDatarichiestaPagamentoTelematico.lastUpdate" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','authSoggetto')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.authSoggetto.label')}" labelFor="widg_authSoggetto" errorFor="appDatarichiestaPagamentoTelematico.authSoggetto" labelId="authSoggettoLbl"  position="first" >


<!-- widget authSoggetto -->
<s:property value="appDatarichiestaPagamentoTelematico.authSoggetto" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','dataMsgRichiesta')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.dataMsgRichiesta.label')}" labelFor="widg_dataMsgRichiesta" errorFor="appDatarichiestaPagamentoTelematico.dataMsgRichiesta" labelId="dataMsgRichiestaLbl"  position="first" >


<!-- widget dataMsgRichiesta -->
<s:property value="appDatarichiestaPagamentoTelematico.dataMsgRichiesta" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','idCanale')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.idCanale.label')}" labelFor="widg_idCanale" errorFor="appDatarichiestaPagamentoTelematico.idCanale" labelId="idCanaleLbl"  position="first" >


<!-- widget idCanale -->
<s:property value="appDatarichiestaPagamentoTelematico.idCanale" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','idIntermPsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.idIntermPsp.label')}" labelFor="widg_idIntermPsp" errorFor="appDatarichiestaPagamentoTelematico.idIntermPsp" labelId="idIntermPspLbl"  position="first" >


<!-- widget idIntermPsp -->
<s:property value="appDatarichiestaPagamentoTelematico.idIntermPsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','idMsgRichiesta')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.idMsgRichiesta.label')}" labelFor="widg_idMsgRichiesta" errorFor="appDatarichiestaPagamentoTelematico.idMsgRichiesta" labelId="idMsgRichiestaLbl"  position="first" >


<!-- widget idMsgRichiesta -->
<s:property value="appDatarichiestaPagamentoTelematico.idMsgRichiesta" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','idPsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.idPsp.label')}" labelFor="widg_idPsp" errorFor="appDatarichiestaPagamentoTelematico.idPsp" labelId="idPspLbl"  position="first" >


<!-- widget idPsp -->
<s:property value="appDatarichiestaPagamentoTelematico.idPsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','identificativoDominio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.identificativoDominio.label')}" labelFor="widg_identificativoDominio" errorFor="appDatarichiestaPagamentoTelematico.identificativoDominio" labelId="identificativoDominioLbl"  position="first" >


<!-- widget identificativoDominio -->
<s:property value="appDatarichiestaPagamentoTelematico.identificativoDominio" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','identificativoIntermediarioPa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.identificativoIntermediarioPa.label')}" labelFor="widg_identificativoIntermediarioPa" errorFor="appDatarichiestaPagamentoTelematico.identificativoIntermediarioPa" labelId="identificativoIntermediarioPaLbl"  position="first" >


<!-- widget identificativoIntermediarioPa -->
<s:property value="appDatarichiestaPagamentoTelematico.identificativoIntermediarioPa" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','identificativoStazioneIntermediarioPa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.identificativoStazioneIntermediarioPa.label')}" labelFor="widg_identificativoStazioneIntermediarioPa" errorFor="appDatarichiestaPagamentoTelematico.identificativoStazioneIntermediarioPa" labelId="identificativoStazioneIntermediarioPaLbl"  position="first" >


<!-- widget identificativoStazioneIntermediarioPa -->
<s:property value="appDatarichiestaPagamentoTelematico.identificativoStazioneIntermediarioPa" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','iuv')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.iuv.label')}" labelFor="widg_iuv" errorFor="appDatarichiestaPagamentoTelematico.iuv" labelId="iuvLbl"  position="first" >


<!-- widget iuv -->
<s:property value="appDatarichiestaPagamentoTelematico.iuv" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','idStatiRpt')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.idStatiRpt.label')}" labelFor="widg_idStatiRpt" errorFor="appDatarichiestaPagamentoTelematico.idStatiRpt" labelId="idStatiRptLbl"  position="first" >


<!-- widget idStatiRpt -->
<s:property value="appDatarichiestaPagamentoTelematico.idStatiRpt" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioRPT','descStatiRpt')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioRPT.descStatiRpt.label')}" labelFor="widg_descStatiRpt" errorFor="appDatarichiestaPagamentoTelematico.descStatiRpt" labelId="descStatiRptLbl"  position="first" >


<!-- widget descStatiRpt -->
<s:property value="appDatarichiestaPagamentoTelematico.descStatiRpt" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioPRT --></div>

			
	
	<div id="p_wpDettaglioAzioniPRT" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioAzioniPRT -->
	
	

<div class="widgetsPanel" id="wpDettaglioAzioniPRT">
	
	<customtag:widgetsPanel id="wpDettaglioAzioniPRTTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioRPT','btIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btIndietro -->
<s:submit name="widg_btIndietro" id="widg_btIndietro" method="handleBtIndietro_CLICKED"
	key="cpDettaglioRPT.btIndietro.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpDettaglioRPT','btIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioAzioniPRT --></div>

	

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
