<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpFlussoSingoloPagamentoAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpFlussoSingoloPagamento -->
<s:form id="cpFlussoSingoloPagamento" action="cpFlussoSingoloPagamento" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','mnuView')" >

	


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
				<h3><s:text name="cpFlussoSingoloPagamento.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_stdMsg" class="stdMessagePanelBlock scroll"><!-- startFragment:p_stdMsg -->
	
	
<div class="stdMessagePanel" id="stdMsg">
	<customtag:stdMessagePanel id="stdMsg" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_stdMsg --></div>

			
	
	<div id="p_wRicercaSingoloFlusso " class="widgetsPanelBlock"><!-- startFragment:p_wRicercaSingoloFlusso  -->
	
	

<div class="widgetsPanel" id="wRicercaSingoloFlusso ">
	
	<customtag:widgetsPanel id="wRicercaSingoloFlusso Tbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','txDataRegolamentoDa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoSingoloPagamento.txDataRegolamentoDa.label')}" labelFor="widg_txDataRegolamentoDa" errorFor="appDataricercaFlussoSingoloPagamento.dataregolamentoDA" labelId="txDataRegolamentoDaLbl"
	position="first"  >


<!-- widget txDataRegolamentoDa -->
<s:textfield name="appDataricercaFlussoSingoloPagamento.dataregolamentoDA" id="widg_txDataRegolamentoDa"
	disabled="isWidgetDisabled('cpFlussoSingoloPagamento','txDataRegolamentoDa')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','txDataRegolamentoA')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoSingoloPagamento.txDataRegolamentoA.label')}" labelFor="widg_txDataRegolamentoA" errorFor="appDataricercaFlussoSingoloPagamento.dataregolamentoA" labelId="txDataRegolamentoALbl"
	position="first"  >


<!-- widget txDataRegolamentoA -->
<s:textfield name="appDataricercaFlussoSingoloPagamento.dataregolamentoA" id="widg_txDataRegolamentoA"
	disabled="isWidgetDisabled('cpFlussoSingoloPagamento','txDataRegolamentoA')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','cbListaApplication')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoSingoloPagamento.cbListaApplication.label')}" labelFor="widg_cbListaApplication" errorFor="appDataricercaFlussoSingoloPagamento.applicationId" labelId="cbListaApplicationLbl"
	position="first"  >


<!-- widget cbListaApplication -->
<s:select name="appDataricercaFlussoSingoloPagamento.applicationId" id="widg_cbListaApplication"
          headerKey="" headerValue="" 
          list="appDataapplicazioni"
          disabled="isWidgetDisabled('cpFlussoSingoloPagamento','cbListaApplication')"
          listKey="idApplicazione"
          listValue="nomeApplicazione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','txidentificativoistitutoricevente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoSingoloPagamento.txidentificativoistitutoricevente.label')}" labelFor="widg_txidentificativoistitutoricevente" errorFor="appDataricercaFlussoSingoloPagamento" labelId="txidentificativoistitutoriceventeLbl"
	position="first"  >


<!-- widget txidentificativoistitutoricevente -->
<s:textfield name="appDataricercaFlussoSingoloPagamento" id="widg_txidentificativoistitutoricevente"
	disabled="isWidgetDisabled('cpFlussoSingoloPagamento','txidentificativoistitutoricevente')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wRicercaSingoloFlusso  --></div>

			
	
	<div id="p_wActionRicercaSingoliFlussi" class="widgetsPanelBlock"><!-- startFragment:p_wActionRicercaSingoliFlussi -->
	
	

<div class="widgetsPanel" id="wActionRicercaSingoliFlussi">
	
	<customtag:widgetsPanel id="wActionRicercaSingoliFlussiTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','btricerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btricerca -->
<s:submit name="widg_btricerca" id="widg_btricerca" method="handleBtricerca_CLICKED"
	key="cpFlussoSingoloPagamento.btricerca.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpFlussoSingoloPagamento','btricerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wActionRicercaSingoliFlussi --></div>

			
	
	<div id="p_wListaSingoliFlussi" class="widgetsPanelBlock"><!-- startFragment:p_wListaSingoliFlussi -->
	
	

<div class="widgetsPanel" id="wListaSingoliFlussi">
	

	
	
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','tbRicerca')" >

	
<div class="tableWidget">


<!-- widget tbRicerca -->
	<csiuicore:ajaxify id="p_wListaSingoliFlussi" 
		widgetType="table" 
		pageId="cpFlussoSingoloPagamento">
<s:set name="cpFlussoSingoloPagamento_tbRicerca_clearStatus" value="isTableClearStatus('cpFlussoSingoloPagamento_tbRicerca')" />
<display:table name="appDatalistaFlussoSingoloPagamento"
               excludedParams="*"
               export="true"
               id="widg_tbRicerca"
               pagesize="10"
               requestURI="cpFlussoSingoloPagamento.do"
               keepStatus="true"
               clearStatus="${cpFlussoSingoloPagamento_tbRicerca_clearStatus}"
               uid="row_tbRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbRicerca.id}" name="appDataselettoreFlussoSingoloPagamento" id="%{'tbRicerca-editcell-'+ (#attr.row_tbRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="id" titleKey="cpFlussoSingoloPagamento.tbRicerca.id.label" 
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0}"  class="numbers"  media="html" />

		<display:column property="id" titleKey="cpFlussoSingoloPagamento.tbRicerca.id.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="id" titleKey="cpFlussoSingoloPagamento.tbRicerca.id.export.label"
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0}" 
			media="pdf" />	
		<display:column property="iuv" titleKey="cpFlussoSingoloPagamento.tbRicerca.iuv.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="iuv" titleKey="cpFlussoSingoloPagamento.tbRicerca.iuv.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="iuv" titleKey="cpFlussoSingoloPagamento.tbRicerca.iuv.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="identificativounivocoriscossione" titleKey="cpFlussoSingoloPagamento.tbRicerca.identificativounivocoriscossione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="identificativounivocoriscossione" titleKey="cpFlussoSingoloPagamento.tbRicerca.identificativounivocoriscossione.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="identificativounivocoriscossione" titleKey="cpFlussoSingoloPagamento.tbRicerca.identificativounivocoriscossione.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="singoloimportopagato" titleKey="cpFlussoSingoloPagamento.tbRicerca.singoloimportopagato.label" 
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0.00}"  class="numbers"  media="html" />

		<display:column property="singoloimportopagato" titleKey="cpFlussoSingoloPagamento.tbRicerca.singoloimportopagato.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="singoloimportopagato" titleKey="cpFlussoSingoloPagamento.tbRicerca.singoloimportopagato.export.label"
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0.00}" 
			media="pdf" />	
		<display:column property="codiceesitosingolopagamento" titleKey="cpFlussoSingoloPagamento.tbRicerca.codiceesitosingolopagamento.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="codiceesitosingolopagamento" titleKey="cpFlussoSingoloPagamento.tbRicerca.codiceesitosingolopagamento.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="codiceesitosingolopagamento" titleKey="cpFlussoSingoloPagamento.tbRicerca.codiceesitosingolopagamento.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="dataesitosingolopagamento" titleKey="cpFlussoSingoloPagamento.tbRicerca.dataesitosingolopagamento.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="dataesitosingolopagamento" titleKey="cpFlussoSingoloPagamento.tbRicerca.dataesitosingolopagamento.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="dataesitosingolopagamento" titleKey="cpFlussoSingoloPagamento.tbRicerca.dataesitosingolopagamento.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="datainserimento" titleKey="cpFlussoSingoloPagamento.tbRicerca.datainserimento.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="datainserimento" titleKey="cpFlussoSingoloPagamento.tbRicerca.datainserimento.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="datainserimento" titleKey="cpFlussoSingoloPagamento.tbRicerca.datainserimento.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="datamodifica" titleKey="cpFlussoSingoloPagamento.tbRicerca.datamodifica.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="datamodifica" titleKey="cpFlussoSingoloPagamento.tbRicerca.datamodifica.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="datamodifica" titleKey="cpFlussoSingoloPagamento.tbRicerca.datamodifica.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="applicationname" titleKey="cpFlussoSingoloPagamento.tbRicerca.applicationname.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="applicationname" titleKey="cpFlussoSingoloPagamento.tbRicerca.applicationname.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="applicationname" titleKey="cpFlussoSingoloPagamento.tbRicerca.applicationname.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wListaSingoliFlussi --></div>

			
	
	<div id="p_wAction" class="widgetsPanelBlock"><!-- startFragment:p_wAction -->
	
	

<div class="widgetsPanel" id="wAction">
	
	<customtag:widgetsPanel id="wActionTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpFlussoSingoloPagamento','goToFlusso')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget goToFlusso -->
<s:submit name="widg_goToFlusso" id="widg_goToFlusso" method="handleGoToFlusso_CLICKED"
	key="cpFlussoSingoloPagamento.goToFlusso.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpFlussoSingoloPagamento','goToFlusso')" />

	
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
