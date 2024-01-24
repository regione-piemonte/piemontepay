<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpRicercaEntiAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpRicercaEnti -->
<s:form id="cpRicercaEnti" action="cpRicercaEnti" method="post">
		
	
	
		
<!-- ####################### PANNELLO CONTENUTI ###################### -->
<div id="contentPanel">

	<!-- ================ UDLRC Layout UP PANEL ================ -->
	<div id="northPanel">
		<div class="wrapper">
		
	
	<div id="p_uUp" class="formPanelBlock"><!-- startFragment:p_uUp -->

	
	

	
	
			
	
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

	

	<!-- endFragment:p_uUp --></div>

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

	
		
			
<s:if test="isWidgetVisible('cpRicercaEnti','mnuView')" >

	


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
				<h3><s:text name="cpRicercaEnti.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpEnti" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smpEnti -->
	
	
<div class="stdMessagePanel" id="smpEnti">
	<customtag:stdMessagePanel id="smpEnti" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpEnti --></div>

			
	
	<div id="p_wpFiltroRicercaEnti" class="widgetsPanelBlock"><!-- startFragment:p_wpFiltroRicercaEnti -->
	
	

<div class="widgetsPanel" id="wpFiltroRicercaEnti">
	
	<customtag:widgetsPanel id="wpFiltroRicercaEntiTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpRicercaEnti','txdescrizione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpRicercaEnti.txdescrizione.label')}" labelFor="widg_txdescrizione" errorFor="appDataricercaEnti.descrizione" labelId="txdescrizioneLbl"
	position="first"  >


<!-- widget txdescrizione -->
<s:textfield name="appDataricercaEnti.descrizione" id="widg_txdescrizione"
	disabled="isWidgetDisabled('cpRicercaEnti','txdescrizione')"
	size="40" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaEnti','txPartitaIva')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpRicercaEnti.txPartitaIva.label')}" labelFor="widg_txPartitaIva" errorFor="appDataricercaEnti.partitaIva" labelId="txPartitaIvaLbl"
	position="first"  >


<!-- widget txPartitaIva -->
<s:textfield name="appDataricercaEnti.partitaIva" id="widg_txPartitaIva"
maxlength="11"	disabled="isWidgetDisabled('cpRicercaEnti','txPartitaIva')"
	size="11" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaEnti','enteId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpRicercaEnti.enteId.label')}" labelFor="widg_enteId" errorFor="appDataricercaEnti.enteId" labelId="enteIdLbl"
	position="first"  >


<!-- widget enteId -->
<s:textfield name="appDataricercaEnti.enteId" id="widg_enteId"
maxlength="4"	disabled="isWidgetDisabled('cpRicercaEnti','enteId')"
	size="4" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaEnti','attivo')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpRicercaEnti.attivo.label')}" labelFor="widg_attivo" errorFor="appDataricercaEnti.attivo" labelId="attivoLbl"
	position="first"  >


<!-- widget attivo -->
<s:select name="appDataricercaEnti.attivo" id="widg_attivo"
           
          list="appDatalistaStatiAttivazioneEnti"
          disabled="isWidgetDisabled('cpRicercaEnti','attivo')"
          listKey="attivo"
          listValue="stato"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFiltroRicercaEnti --></div>

			
	
	<div id="p_wpAzioneRicercaEnti" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioneRicercaEnti -->
	
	

<div class="widgetsPanel" id="wpAzioneRicercaEnti">
	
	<customtag:widgetsPanel id="wpAzioneRicercaEntiTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpRicercaEnti','cerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget cerca -->
<s:submit name="widg_cerca" id="widg_cerca" method="handleCerca_CLICKED"
	key="cpRicercaEnti.cerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpRicercaEnti','cerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaEnti','btClean')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btClean -->
<s:submit name="widg_btClean" id="widg_btClean" method="handleBtClean_CLICKED"
	key="cpRicercaEnti.btClean.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpRicercaEnti','btClean')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioneRicercaEnti --></div>

			
	
	<div id="p_wpRisultatoRicercaEnti" class="widgetsPanelBlock"><!-- startFragment:p_wpRisultatoRicercaEnti -->
	
	

<div class="widgetsPanel" id="wpRisultatoRicercaEnti">
	

	
	
<s:if test="isWidgetVisible('cpRicercaEnti','tRicerca')" >

	
<div class="tableWidget">


<!-- widget tRicerca -->
	<csiuicore:ajaxify id="p_wpRisultatoRicercaEnti" 
		widgetType="table" 
		pageId="cpRicercaEnti">
<s:set name="cpRicercaEnti_tRicerca_clearStatus" value="isTableClearStatus('cpRicercaEnti_tRicerca')" />
<display:table name="appDatalistaEnti"
               excludedParams="*"
               export="true"
               id="widg_tRicerca"
               pagesize="10"
               requestURI="cpRicercaEnti.do"
               keepStatus="true"
               clearStatus="${cpRicercaEnti_tRicerca_clearStatus}"
               uid="row_tRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tRicerca.enteId}" name="appDataselettoreIdEnte" id="%{'tRicerca-editcell-'+ (#attr.row_tRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="enteId" titleKey="cpRicercaEnti.tRicerca.enteId.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="enteId" titleKey="cpRicercaEnti.tRicerca.enteId.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="enteId" titleKey="cpRicercaEnti.tRicerca.enteId.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="partitaIva" titleKey="cpRicercaEnti.tRicerca.partitaIva.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="partitaIva" titleKey="cpRicercaEnti.tRicerca.partitaIva.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="partitaIva" titleKey="cpRicercaEnti.tRicerca.partitaIva.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrizione" titleKey="cpRicercaEnti.tRicerca.descrizione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrizione" titleKey="cpRicercaEnti.tRicerca.descrizione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrizione" titleKey="cpRicercaEnti.tRicerca.descrizione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="stato" titleKey="cpRicercaEnti.tRicerca.stato.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="stato" titleKey="cpRicercaEnti.tRicerca.stato.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="stato" titleKey="cpRicercaEnti.tRicerca.stato.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpRisultatoRicercaEnti --></div>

			
	
	<div id="p_wpAzioniEnti" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniEnti -->
	
	

<div class="widgetsPanel" id="wpAzioniEnti">
	
	<customtag:widgetsPanel id="wpAzioniEntiTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpRicercaEnti','newEnte')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget newEnte -->
<s:submit name="widg_newEnte" id="widg_newEnte" method="handleNewEnte_CLICKED"
	key="cpRicercaEnti.newEnte.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpRicercaEnti','newEnte')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaEnti','updEnte')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget updEnte -->
<s:submit name="widg_updEnte" id="widg_updEnte" method="handleUpdEnte_CLICKED"
	key="cpRicercaEnti.updEnte.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpRicercaEnti','updEnte')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpRicercaEnti','delEnte')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget delEnte -->
<s:submit name="widg_delEnte" id="widg_delEnte" method="handleDelEnte_CLICKED"
	key="cpRicercaEnti.delEnte.label" cssClass="buttonWidget deleteItem"
	disabled="isWidgetDisabled('cpRicercaEnti','delEnte')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniEnti --></div>

	

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
