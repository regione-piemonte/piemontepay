<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneStatiRptAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneStatiRpt -->
<s:form id="cpGestioneStatiRpt" action="cpGestioneStatiRpt" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneStatiRpt','wiewMenu')" >

	


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
				<h3><s:text name="cpGestioneStatiRpt.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smtMsg" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smtMsg -->
	
	
<div class="stdMessagePanel" id="smtMsg">
	<customtag:stdMessagePanel id="smtMsg" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smtMsg --></div>

			
	
	<div id="p_wStatiRPT" class="widgetsPanelBlock"><!-- startFragment:p_wStatiRPT -->
	
	
<h4 class="wpLabel">Stati RPT </h4>
<div class="widgetsPanel" id="wStatiRPT">
	
	<customtag:widgetsPanel id="wStatiRPTTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneStatiRpt','esito')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneStatiRpt.esito.label')}" labelFor="widg_esito" errorFor="appDatastatoRPTRisposta.esito" labelId="esitoLbl"  position="first" >


<!-- widget esito -->
<s:property value="appDatastatoRPTRisposta.esito" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneStatiRpt','statoAttuale')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneStatiRpt.statoAttuale.label')}" labelFor="widg_statoAttuale" errorFor="appDatastatoRPTRisposta.statoAttuale" labelId="statoAttualeLbl"  position="first" >


<!-- widget statoAttuale -->
<s:property value="appDatastatoRPTRisposta.statoAttuale" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneStatiRpt','urlPagamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneStatiRpt.urlPagamento.label')}" labelFor="widg_urlPagamento" errorFor="appDatastatoRPTRisposta.urlPagamento" labelId="urlPagamentoLbl"  position="first" >


<!-- widget urlPagamento -->
<s:property value="appDatastatoRPTRisposta.urlPagamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wStatiRPT --></div>

			
	
	<div id="p_wListaStoricoRPT" class="widgetsPanelBlock"><!-- startFragment:p_wListaStoricoRPT -->
	
	

<div class="widgetsPanel" id="wListaStoricoRPT">
	

	
	
<s:if test="isWidgetVisible('cpGestioneStatiRpt','tbListaSingoloStatoRpt')" >

	
<div class="tableWidget">


<!-- widget tbListaSingoloStatoRpt -->
<s:set name="cpGestioneStatiRpt_tbListaSingoloStatoRpt_clearStatus" value="isTableClearStatus('cpGestioneStatiRpt_tbListaSingoloStatoRpt')" />
<display:table name="appDatalistaSingoloStatoRpt"
               excludedParams="*"
               export="false"
               id="widg_tbListaSingoloStatoRpt"
               pagesize="10"
               requestURI="cpGestioneStatiRpt.do"
               keepStatus="true"
               clearStatus="${cpGestioneStatiRpt_tbListaSingoloStatoRpt_clearStatus}"
               uid="row_tbListaSingoloStatoRpt"
               summary="" 
               
               class="dataTable">
	
		<display:column property="data" titleKey="cpGestioneStatiRpt.tbListaSingoloStatoRpt.data.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="stato" titleKey="cpGestioneStatiRpt.tbListaSingoloStatoRpt.stato.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descrizione" titleKey="cpGestioneStatiRpt.tbListaSingoloStatoRpt.descrizione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>





	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wListaStoricoRPT --></div>

			
	
	<div id="p_wListaSingoloStatoVersamento" class="widgetsPanelBlock"><!-- startFragment:p_wListaSingoloStatoVersamento -->
	
	

<div class="widgetsPanel" id="wListaSingoloStatoVersamento">
	

	
	
<s:if test="isWidgetVisible('cpGestioneStatiRpt','tbListaSingoloStatoVersamento')" >

	
<div class="tableWidget">


<!-- widget tbListaSingoloStatoVersamento -->
<s:set name="cpGestioneStatiRpt_tbListaSingoloStatoVersamento_clearStatus" value="isTableClearStatus('cpGestioneStatiRpt_tbListaSingoloStatoVersamento')" />
<display:table name="appDatalistaSingoloStatoVersamento"
               excludedParams="*"
               export="false"
               id="widg_tbListaSingoloStatoVersamento"
               pagesize="10"
               requestURI="cpGestioneStatiRpt.do"
               keepStatus="true"
               clearStatus="${cpGestioneStatiRpt_tbListaSingoloStatoVersamento_clearStatus}"
               uid="row_tbListaSingoloStatoVersamento"
               summary="" 
               
               class="dataTable">
	
		<display:column property="progressivo" titleKey="cpGestioneStatiRpt.tbListaSingoloStatoVersamento.progressivo.label" 
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="data" titleKey="cpGestioneStatiRpt.tbListaSingoloStatoVersamento.data.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="stato" titleKey="cpGestioneStatiRpt.tbListaSingoloStatoVersamento.stato.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descrizione" titleKey="cpGestioneStatiRpt.tbListaSingoloStatoVersamento.descrizione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>





	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wListaSingoloStatoVersamento --></div>

			
	
	<div id="p_wAction" class="widgetsPanelBlock"><!-- startFragment:p_wAction -->
	
	

<div class="widgetsPanel" id="wAction">
	
	<customtag:widgetsPanel id="wActionTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneStatiRpt','btIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btIndietro -->
<s:submit name="widg_btIndietro" id="widg_btIndietro" method="handleBtIndietro_CLICKED"
	key="cpGestioneStatiRpt.btIndietro.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpGestioneStatiRpt','btIndietro')" />

	
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
