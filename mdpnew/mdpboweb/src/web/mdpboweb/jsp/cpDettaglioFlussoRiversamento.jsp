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



<!-- pageId:cpDettaglioFlussoRiversamento -->
<s:form id="cpDettaglioFlussoRiversamento" action="cpDettaglioFlussoRiversamento" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioFlussoRiversamento.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_stdMsg" class="stdMessagePanelBlock scroll"><!-- startFragment:p_stdMsg -->
	
	
<div class="stdMessagePanel" id="stdMsg">
	<customtag:stdMessagePanel id="stdMsg" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_stdMsg --></div>

			
	
	<div id="p_wpDettaglio" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglio -->
	
	

<div class="widgetsPanel" id="wpDettaglio">
	
	<customtag:widgetsPanel id="wpDettaglioTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txidentificativopsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txidentificativopsp.label')}" labelFor="widg_txidentificativopsp" errorFor="appDataflussoRiversamento.identificativopsp" labelId="txidentificativopspLbl"   >


<!-- widget txidentificativopsp -->
<s:property value="appDataflussoRiversamento.identificativopsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txidentificativoflusso')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txidentificativoflusso.label')}" labelFor="widg_txidentificativoflusso" errorFor="appDataflussoRiversamento.identificativoflusso" labelId="txidentificativoflussoLbl"   >


<!-- widget txidentificativoflusso -->
<s:property value="appDataflussoRiversamento.identificativoflusso" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txversioneoggetto')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txversioneoggetto.label')}" labelFor="widg_txversioneoggetto" errorFor="appDataflussoRiversamento.versioneoggetto" labelId="txversioneoggettoLbl"   >


<!-- widget txversioneoggetto -->
<s:property value="appDataflussoRiversamento.versioneoggetto" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txidentificativounivocoregolamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txidentificativounivocoregolamento.label')}" labelFor="widg_txidentificativounivocoregolamento" errorFor="appDataflussoRiversamento.identificativounivocoregolamento" labelId="txidentificativounivocoregolamentoLbl"   >


<!-- widget txidentificativounivocoregolamento -->
<s:property value="appDataflussoRiversamento.identificativounivocoregolamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','identificativoistitutomittente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.identificativoistitutomittente.label')}" labelFor="widg_identificativoistitutomittente" errorFor="appDataflussoRiversamento.identificativoistitutomittente" labelId="identificativoistitutomittenteLbl"   >


<!-- widget identificativoistitutomittente -->
<s:property value="appDataflussoRiversamento.identificativoistitutomittente" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txidentificativoistitutoricevente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txidentificativoistitutoricevente.label')}" labelFor="widg_txidentificativoistitutoricevente" errorFor="appDataflussoRiversamento.identificativoistitutoricevente" labelId="txidentificativoistitutoriceventeLbl"   >


<!-- widget txidentificativoistitutoricevente -->
<s:property value="appDataflussoRiversamento.identificativoistitutoricevente" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txnumerototalepagamenti')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txnumerototalepagamenti.label')}" labelFor="widg_txnumerototalepagamenti" errorFor="appDataflussoRiversamento.numerototalepagamenti" labelId="txnumerototalepagamentiLbl"   tdStyleClass="numbers">


<!-- widget txnumerototalepagamenti -->
<s:property value="appDataflussoRiversamento.numerototalepagamenti" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','tximportototalepagamenti')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.tximportototalepagamenti.label')}" labelFor="widg_tximportototalepagamenti" errorFor="appDataflussoRiversamento.importototalepagamenti" labelId="tximportototalepagamentiLbl"   tdStyleClass="numbers">


<!-- widget tximportototalepagamenti -->
<s:property value="appDataflussoRiversamento.importototalepagamenti" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txdataoraflusso')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txdataoraflusso.label')}" labelFor="widg_txdataoraflusso" errorFor="appDataflussoRiversamento.dataoraflusso" labelId="txdataoraflussoLbl"   >


<!-- widget txdataoraflusso -->
<s:property value="appDataflussoRiversamento.dataoraflusso" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txdataregolamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txdataregolamento.label')}" labelFor="widg_txdataregolamento" errorFor="appDataflussoRiversamento.dataregolamento" labelId="txdataregolamentoLbl"   >


<!-- widget txdataregolamento -->
<s:property value="appDataflussoRiversamento.dataregolamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txdatainserimento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txdatainserimento.label')}" labelFor="widg_txdatainserimento" errorFor="appDataflussoRiversamento.datainserimento" labelId="txdatainserimentoLbl"   >


<!-- widget txdatainserimento -->
<s:property value="appDataflussoRiversamento.datainserimento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txdatamodifica')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txdatamodifica.label')}" labelFor="widg_txdatamodifica" errorFor="appDataflussoRiversamento.datamodifica" labelId="txdatamodificaLbl"   >


<!-- widget txdatamodifica -->
<s:property value="appDataflussoRiversamento.datamodifica" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txdenominazionemittente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txdenominazionemittente.label')}" labelFor="widg_txdenominazionemittente" errorFor="appDataflussoRiversamento.denominazionemittente" labelId="txdenominazionemittenteLbl"   >


<!-- widget txdenominazionemittente -->
<s:property value="appDataflussoRiversamento.denominazionemittente" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','txdenominazionericevente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioFlussoRiversamento.txdenominazionericevente.label')}" labelFor="widg_txdenominazionericevente" errorFor="appDataflussoRiversamento.denominazionericevente" labelId="txdenominazionericeventeLbl"   >


<!-- widget txdenominazionericevente -->
<s:property value="appDataflussoRiversamento.denominazionericevente" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglio --></div>

			
	
	<div id="p_wpAction" class="widgetsPanelBlock"><!-- startFragment:p_wpAction -->
	
	

<div class="widgetsPanel" id="wpAction">
	
	<customtag:widgetsPanel id="wpActionTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','btIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btIndietro -->
<s:submit name="widg_btIndietro" id="widg_btIndietro" method="handleBtIndietro_CLICKED"
	key="cpDettaglioFlussoRiversamento.btIndietro.label" cssClass="buttonWidget lookup"
	disabled="isWidgetDisabled('cpDettaglioFlussoRiversamento','btIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','goTosp')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget goTosp -->
<s:submit name="widg_goTosp" id="widg_goTosp" method="handleGoTosp_CLICKED"
	key="cpDettaglioFlussoRiversamento.goTosp.label" cssClass="buttonWidget detail"
	disabled="isWidgetDisabled('cpDettaglioFlussoRiversamento','goTosp')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioFlussoRiversamento','esportaFlusso')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget esportaFlusso -->
<s:submit name="widg_esportaFlusso" id="widg_esportaFlusso" method="handleEsportaFlusso_CLICKED"
	key="cpDettaglioFlussoRiversamento.esportaFlusso.label" cssClass="buttonWidget showReport"
	disabled="isWidgetDisabled('cpDettaglioFlussoRiversamento','esportaFlusso')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAction --></div>

	

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
