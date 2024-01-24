<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpStatisticheTranzazioniAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpStatisticheTranzazioni -->
<s:form id="cpStatisticheTranzazioni" action="cpStatisticheTranzazioni" method="post">
		
	
	
		
<!-- ####################### PANNELLO CONTENUTI ###################### -->
<div id="contentPanel">

	<!-- ================ UDLRC Layout UP PANEL ================ -->
	<div id="northPanel">
		<div class="wrapper">
		
	
	<div id="p_pUP" class="formPanelBlock"><!-- startFragment:p_pUP -->

	
	

	
	
			
	
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

	

	<!-- endFragment:p_pUP --></div>

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

	
		
			
<s:if test="isWidgetVisible('cpStatisticheTranzazioni','mnuView')" >

	


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
				<h3><s:text name="cpStatisticheTranzazioni.cpStatisticheTransazioni.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpRT" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smpRT -->
	
	
<div class="stdMessagePanel" id="smpRT">
	<customtag:stdMessagePanel id="smpRT" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpRT --></div>

			
	
	<div id="p_wpFiltroRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpFiltroRicerca -->
	
	

<div class="widgetsPanel" id="wpFiltroRicerca">
	
	<customtag:widgetsPanel id="wpFiltroRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpStatisticheTranzazioni','txApplicationId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStatisticheTranzazioni.txApplicationId.label')}" labelFor="widg_txApplicationId" errorFor="appDatafiltroRicercaStatisticaApplicazioneTransazione.applicationId" labelId="txApplicationIdLbl"
	  >


<!-- widget txApplicationId -->
<s:textfield name="appDatafiltroRicercaStatisticaApplicazioneTransazione.applicationId" id="widg_txApplicationId"
	disabled="isWidgetDisabled('cpStatisticheTranzazioni','txApplicationId')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpStatisticheTranzazioni','txDataDa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStatisticheTranzazioni.txDataDa.label')}" labelFor="widg_txDataDa" errorFor="appDatafiltroRicercaStatisticaApplicazioneTransazione.dataDa" labelId="txDataDaLbl"
	  fieldRequired="true">


<!-- widget txDataDa -->
<s:textfield name="appDatafiltroRicercaStatisticaApplicazioneTransazione.dataDa" id="widg_txDataDa"
	disabled="isWidgetDisabled('cpStatisticheTranzazioni','txDataDa')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpStatisticheTranzazioni','txDataA')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpStatisticheTranzazioni.txDataA.label')}" labelFor="widg_txDataA" errorFor="appDatafiltroRicercaStatisticaApplicazioneTransazione.dataA" labelId="txDataALbl"
	  fieldRequired="true">


<!-- widget txDataA -->
<s:textfield name="appDatafiltroRicercaStatisticaApplicazioneTransazione.dataA" id="widg_txDataA"
	disabled="isWidgetDisabled('cpStatisticheTranzazioni','txDataA')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFiltroRicerca --></div>

			
	
	<div id="p_wpAzioneRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioneRicerca -->
	
	

<div class="widgetsPanel" id="wpAzioneRicerca">
	
	<customtag:widgetsPanel id="wpAzioneRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpStatisticheTranzazioni','btCerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btCerca -->
<s:submit name="widg_btCerca" id="widg_btCerca" method="handleBtCerca_CLICKED"
	key="cpStatisticheTranzazioni.btCerca.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpStatisticheTranzazioni','btCerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioneRicerca --></div>

			
	
	<div id="p_wpRisultati" class="widgetsPanelBlock"><!-- startFragment:p_wpRisultati -->
	
	

<div class="widgetsPanel" id="wpRisultati">
	

	
	
<s:if test="isWidgetVisible('cpStatisticheTranzazioni','tbRisultati')" >

	
<div class="tableWidget">


<!-- widget tbRisultati -->
<s:set name="cpStatisticheTranzazioni_tbRisultati_clearStatus" value="isTableClearStatus('cpStatisticheTranzazioni_tbRisultati')" />
<display:table name="appDatalistaStatisticaApplicazioneTransazione"
               excludedParams="*"
               export="false"
               id="widg_tbRisultati"
               pagesize="20"
               requestURI="cpStatisticheTranzazioni.do"
               keepStatus="true"
               clearStatus="${cpStatisticheTranzazioni_tbRisultati_clearStatus}"
               uid="row_tbRisultati"
               summary="" 
               
               class="dataTable">
	
		<display:column property="applicationId" titleKey="cpStatisticheTranzazioni.tbRisultati.applicationId.label" 
			sortable="true" headerClass="sortable"
			    media="html" />
		<display:column property="notInitialized" titleKey="cpStatisticheTranzazioni.tbRisultati.notInitialized.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="initialized" titleKey="cpStatisticheTranzazioni.tbRisultati.initialized.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="configured" titleKey="cpStatisticheTranzazioni.tbRisultati.configured.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="started" titleKey="cpStatisticheTranzazioni.tbRisultati.started.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="successful" titleKey="cpStatisticheTranzazioni.tbRisultati.successful.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="unsuccessful" titleKey="cpStatisticheTranzazioni.tbRisultati.unsuccessful.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="canceled" titleKey="cpStatisticheTranzazioni.tbRisultati.canceled.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="refunded" titleKey="cpStatisticheTranzazioni.tbRisultati.refunded.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="toBeConfirmed" titleKey="cpStatisticheTranzazioni.tbRisultati.toBeConfirmed.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="attesaRt" titleKey="cpStatisticheTranzazioni.tbRisultati.attesaRt.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
		<display:column property="totForAppId" titleKey="cpStatisticheTranzazioni.tbRisultati.totForAppId.label" 
			sortable="true" headerClass="sortable"
			format="{0,number,#,##0}"  class="numbers"  media="html" />
</display:table>





	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpRisultati --></div>

			
	
	<div id="p_wpExport" class="widgetsPanelBlock"><!-- startFragment:p_wpExport -->
	
	

<div class="widgetsPanel" id="wpExport">
	
	<customtag:widgetsPanel id="wpExportTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpStatisticheTranzazioni','btexport')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btexport -->
<s:submit name="widg_btexport" id="widg_btexport" method="handleBtexport_CLICKED"
	key="cpStatisticheTranzazioni.btexport.label" cssClass="buttonWidget showReport"
	disabled="isWidgetDisabled('cpStatisticheTranzazioni','btexport')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpExport --></div>

	

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
