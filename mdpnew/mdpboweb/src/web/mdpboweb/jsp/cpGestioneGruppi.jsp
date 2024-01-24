<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneGruppiAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneGruppi -->
<s:form id="cpGestioneGruppi" action="cpGestioneGruppi" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneGruppi','mnuView')" >

	


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
				<h3><s:text name="cpGestioneGruppi.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGestioneGruppi" class="stdMessagePanelBlock"><!-- startFragment:p_smpGestioneGruppi -->
	
	
<div class="stdMessagePanel" id="smpGestioneGruppi">
	<customtag:stdMessagePanel id="smpGestioneGruppi" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGestioneGruppi --></div>

			
	
	<div id="p_wpTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpTabella -->
	
	
<h4 class="wpLabel">Elenco gruppi </h4>
<div class="widgetsPanel" id="wpTabella">
	

	
	
<s:if test="isWidgetVisible('cpGestioneGruppi','tListaGruppi')" >

	
<div class="tableWidget">


<!-- widget tListaGruppi -->
	<csiuicore:ajaxify id="p_wpTabella" 
		widgetType="table" 
		pageId="cpGestioneGruppi">
<s:set name="cpGestioneGruppi_tListaGruppi_clearStatus" value="isTableClearStatus('cpGestioneGruppi_tListaGruppi')" />
<display:table name="appDatagruppi"
               excludedParams="*"
               export="false"
               id="widg_tListaGruppi"
               pagesize="20"
               requestURI="cpGestioneGruppi.do"
               keepStatus="true"
               clearStatus="${cpGestioneGruppi_tListaGruppi_clearStatus}"
               uid="row_tListaGruppi"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tListaGruppi.idgroup}" name="appDataselettoreGruppo" id="%{'tListaGruppi-editcell-'+ (#attr.row_tListaGruppi_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="idgroup" titleKey="cpGestioneGruppi.tListaGruppi.idgroup.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="description" titleKey="cpGestioneGruppi.tListaGruppi.description.label" 
			sortable="true" headerClass="sortable"
			    media="html" />
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabella --></div>

			
	
	<div id="p_wpAzioniTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniTabella -->
	
	

<div class="widgetsPanel" id="wpAzioniTabella">
	
	<customtag:widgetsPanel id="wpAzioniTabellaTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGruppi','btNuovoGruppo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btNuovoGruppo -->
<s:submit name="widg_btNuovoGruppo" id="widg_btNuovoGruppo" method="handleBtNuovoGruppo_CLICKED"
	key="cpGestioneGruppi.btNuovoGruppo.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneGruppi','btNuovoGruppo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGruppi','btModificaGruppo')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btModificaGruppo -->
<s:submit name="widg_btModificaGruppo" id="widg_btModificaGruppo" method="handleBtModificaGruppo_CLICKED"
	key="cpGestioneGruppi.btModificaGruppo.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpGestioneGruppi','btModificaGruppo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGruppi','btCancellaGruppo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btCancellaGruppo -->
<s:submit name="widg_btCancellaGruppo" id="widg_btCancellaGruppo" method="handleBtCancellaGruppo_CLICKED"
	key="cpGestioneGruppi.btCancellaGruppo.label" cssClass="buttonWidget deleteItem"
	disabled="isWidgetDisabled('cpGestioneGruppi','btCancellaGruppo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniTabella --></div>

	

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
