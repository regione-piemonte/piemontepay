<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpFlussoRiversamentoAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpFlussoRiversamento -->
<s:form id="cpFlussoRiversamento" action="cpFlussoRiversamento" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpFlussoRiversamento','mnuView')" >

	


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
				<h3><s:text name="cpFlussoRiversamento.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_stdMsg" class="stdMessagePanelBlock scroll"><!-- startFragment:p_stdMsg -->
	
	
<div class="stdMessagePanel" id="stdMsg">
	<customtag:stdMessagePanel id="stdMsg" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_stdMsg --></div>

			
	
	<div id="p_wpricerca" class="widgetsPanelBlock"><!-- startFragment:p_wpricerca -->
	
	

<div class="widgetsPanel" id="wpricerca">
	
	<customtag:widgetsPanel id="wpricercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpFlussoRiversamento','txIdentificativoUnivocoRegolamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoRiversamento.txIdentificativoUnivocoRegolamento.label')}" labelFor="widg_txIdentificativoUnivocoRegolamento" errorFor="appDataricercaFlussoRiversamento.identificativounivocoregolamento" labelId="txIdentificativoUnivocoRegolamentoLbl"
	  >


<!-- widget txIdentificativoUnivocoRegolamento -->
<s:textfield name="appDataricercaFlussoRiversamento.identificativounivocoregolamento" id="widg_txIdentificativoUnivocoRegolamento"
	disabled="isWidgetDisabled('cpFlussoRiversamento','txIdentificativoUnivocoRegolamento')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpFlussoRiversamento','txDataRegolamentoDa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoRiversamento.txDataRegolamentoDa.label')}" labelFor="widg_txDataRegolamentoDa" errorFor="appDataricercaFlussoRiversamento.dataregolamentoDA" labelId="txDataRegolamentoDaLbl"
	  >


<!-- widget txDataRegolamentoDa -->
<s:textfield name="appDataricercaFlussoRiversamento.dataregolamentoDA" id="widg_txDataRegolamentoDa"
	disabled="isWidgetDisabled('cpFlussoRiversamento','txDataRegolamentoDa')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpFlussoRiversamento','txDataRegolamentoA')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoRiversamento.txDataRegolamentoA.label')}" labelFor="widg_txDataRegolamentoA" errorFor="appDataricercaFlussoRiversamento.dataregolamentoA" labelId="txDataRegolamentoALbl"
	  >


<!-- widget txDataRegolamentoA -->
<s:textfield name="appDataricercaFlussoRiversamento.dataregolamentoA" id="widg_txDataRegolamentoA"
	disabled="isWidgetDisabled('cpFlussoRiversamento','txDataRegolamentoA')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpFlussoRiversamento','txdenominazionemittente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoRiversamento.txdenominazionemittente.label')}" labelFor="widg_txdenominazionemittente" errorFor="appDataricercaFlussoRiversamento.denominazionemittente" labelId="txdenominazionemittenteLbl"
	  >


<!-- widget txdenominazionemittente -->
<s:textfield name="appDataricercaFlussoRiversamento.denominazionemittente" id="widg_txdenominazionemittente"
	disabled="isWidgetDisabled('cpFlussoRiversamento','txdenominazionemittente')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpFlussoRiversamento','txidentificativoistitutoricevente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpFlussoRiversamento.txidentificativoistitutoricevente.label')}" labelFor="widg_txidentificativoistitutoricevente" errorFor="appDataricercaFlussoRiversamento.identificativoistitutoricevente" labelId="txidentificativoistitutoriceventeLbl"
	  >


<!-- widget txidentificativoistitutoricevente -->
<s:textfield name="appDataricercaFlussoRiversamento.identificativoistitutoricevente" id="widg_txidentificativoistitutoricevente"
	disabled="isWidgetDisabled('cpFlussoRiversamento','txidentificativoistitutoricevente')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpricerca --></div>

			
	
	<div id="p_wpActionRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpActionRicerca -->
	
	

<div class="widgetsPanel" id="wpActionRicerca">
	
	<customtag:widgetsPanel id="wpActionRicercaTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpFlussoRiversamento','btRicerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btRicerca -->
<s:submit name="widg_btRicerca" id="widg_btRicerca" method="handleBtRicerca_CLICKED"
	key="cpFlussoRiversamento.btRicerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpFlussoRiversamento','btRicerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpFlussoRiversamento','btVerifica')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btVerifica -->
<s:submit name="widg_btVerifica" id="widg_btVerifica" method="handleBtVerifica_CLICKED"
	key="cpFlussoRiversamento.btVerifica.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpFlussoRiversamento','btVerifica')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpActionRicerca --></div>

			
	
	<div id="p_wpRisultati" class="widgetsPanelBlock"><!-- startFragment:p_wpRisultati -->
	
	

<div class="widgetsPanel" id="wpRisultati">
	

	
	
<s:if test="isWidgetVisible('cpFlussoRiversamento','tbRicerca')" >

	
<div class="tableWidget">


<!-- widget tbRicerca -->
	<csiuicore:ajaxify id="p_wpRisultati" 
		widgetType="table" 
		pageId="cpFlussoRiversamento">
<s:set name="cpFlussoRiversamento_tbRicerca_clearStatus" value="isTableClearStatus('cpFlussoRiversamento_tbRicerca')" />
<display:table name="appDatalistaFlussoRiversamento"
               excludedParams="*"
               export="true"
               id="widg_tbRicerca"
               pagesize="10"
               requestURI="cpFlussoRiversamento.do"
               keepStatus="true"
               clearStatus="${cpFlussoRiversamento_tbRicerca_clearStatus}"
               uid="row_tbRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbRicerca.id}" name="appDataselettoreFlussoRiversamento" id="%{'tbRicerca-editcell-'+ (#attr.row_tbRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="identificativounivocoregolamento" titleKey="cpFlussoRiversamento.tbRicerca.identificativounivocoregolamento.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="identificativounivocoregolamento" titleKey="cpFlussoRiversamento.tbRicerca.identificativounivocoregolamento.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="identificativounivocoregolamento" titleKey="cpFlussoRiversamento.tbRicerca.identificativounivocoregolamento.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="dataregolamento" titleKey="cpFlussoRiversamento.tbRicerca.dataregolamento.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="dataregolamento" titleKey="cpFlussoRiversamento.tbRicerca.dataregolamento.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="dataregolamento" titleKey="cpFlussoRiversamento.tbRicerca.dataregolamento.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="denominazionemittente" titleKey="cpFlussoRiversamento.tbRicerca.denominazionemittente.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="denominazionemittente" titleKey="cpFlussoRiversamento.tbRicerca.denominazionemittente.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="denominazionemittente" titleKey="cpFlussoRiversamento.tbRicerca.denominazionemittente.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="identificativoistitutoricevente" titleKey="cpFlussoRiversamento.tbRicerca.identificativoistitutoricevente.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="identificativoistitutoricevente" titleKey="cpFlussoRiversamento.tbRicerca.identificativoistitutoricevente.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="identificativoistitutoricevente" titleKey="cpFlussoRiversamento.tbRicerca.identificativoistitutoricevente.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpRisultati --></div>

			
	
	<div id="p_wpActRis" class="widgetsPanelBlock"><!-- startFragment:p_wpActRis -->
	
	

<div class="widgetsPanel" id="wpActRis">
	
	<customtag:widgetsPanel id="wpActRisTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpFlussoRiversamento','btGoTodett')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btGoTodett -->
<s:submit name="widg_btGoTodett" id="widg_btGoTodett" method="handleBtGoTodett_CLICKED"
	key="cpFlussoRiversamento.btGoTodett.label" cssClass="buttonWidget detail"
	disabled="isWidgetDisabled('cpFlussoRiversamento','btGoTodett')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpActRis --></div>

	

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
