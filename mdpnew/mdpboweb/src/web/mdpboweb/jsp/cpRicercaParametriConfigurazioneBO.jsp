<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpRicercaParametriConfigurazioneBOAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpRicercaParametriConfigurazioneBO -->
<s:form id="cpRicercaParametriConfigurazioneBO" action="cpRicercaParametriConfigurazioneBO" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpRicercaParametriConfigurazioneBO','mnuView')" >

	


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
				<h3><s:text name="cpRicercaParametriConfigurazioneBO.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpRicercaParametriConfigurazioneBO" class="stdMessagePanelBlock"><!-- startFragment:p_smpRicercaParametriConfigurazioneBO -->
	
	
<div class="stdMessagePanel" id="smpRicercaParametriConfigurazioneBO">
	<customtag:stdMessagePanel id="smpRicercaParametriConfigurazioneBO" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpRicercaParametriConfigurazioneBO --></div>

			
	
	<div id="p_wpTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpTabella -->
	
	

<div class="widgetsPanel" id="wpTabella">
	

	
	
<s:if test="isWidgetVisible('cpRicercaParametriConfigurazioneBO','tdParametriConf')" >

	
<div class="tableWidget">


<!-- widget tdParametriConf -->
	<csiuicore:ajaxify id="p_wpTabella" 
		widgetType="table" 
		pageId="cpRicercaParametriConfigurazioneBO">
<s:set name="cpRicercaParametriConfigurazioneBO_tdParametriConf_clearStatus" value="isTableClearStatus('cpRicercaParametriConfigurazioneBO_tdParametriConf')" />
<display:table name="appDataparametriConfigurazioneBO"
               excludedParams="*"
               export="true"
               id="widg_tdParametriConf"
               pagesize="20"
               requestURI="cpRicercaParametriConfigurazioneBO.do"
               keepStatus="true"
               clearStatus="${cpRicercaParametriConfigurazioneBO_tdParametriConf_clearStatus}"
               uid="row_tdParametriConf"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tdParametriConf.idParametro}" name="appDataselettoreParametroConf" id="%{'tdParametriConf-editcell-'+ (#attr.row_tdParametriConf_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="idParametro" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.idParametro.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="idParametro" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.idParametro.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="idParametro" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.idParametro.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="valore" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.valore.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="valore" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.valore.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="valore" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.valore.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrParametro" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.descrParametro.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="descrParametro" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.descrParametro.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="descrParametro" titleKey="cpRicercaParametriConfigurazioneBO.tdParametriConf.descrParametro.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabella --></div>

			
	
	<div id="p_wpComandi" class="widgetsPanelBlock"><!-- startFragment:p_wpComandi -->
	
	

<div class="widgetsPanel" id="wpComandi">
	
	<customtag:widgetsPanel id="wpComandiTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpRicercaParametriConfigurazioneBO','btnNuovo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnNuovo -->
<s:submit name="widg_btnNuovo" id="widg_btnNuovo" method="handleBtnNuovo_CLICKED"
	key="cpRicercaParametriConfigurazioneBO.btnNuovo.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpRicercaParametriConfigurazioneBO','btnNuovo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaParametriConfigurazioneBO','btnModifica')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnModifica -->
<s:submit name="widg_btnModifica" id="widg_btnModifica" method="handleBtnModifica_CLICKED"
	key="cpRicercaParametriConfigurazioneBO.btnModifica.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpRicercaParametriConfigurazioneBO','btnModifica')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaParametriConfigurazioneBO','btnElimina')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnElimina -->
<s:submit name="widg_btnElimina" id="widg_btnElimina" method="handleBtnElimina_CLICKED"
	key="cpRicercaParametriConfigurazioneBO.btnElimina.label" cssClass="buttonWidget cancel"
	disabled="isWidgetDisabled('cpRicercaParametriConfigurazioneBO','btnElimina')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpComandi --></div>

	

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
