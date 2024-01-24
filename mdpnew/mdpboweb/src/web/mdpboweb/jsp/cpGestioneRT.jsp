<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneRTAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneRT -->
<s:form id="cpGestioneRT" action="cpGestioneRT" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneRT','mnuView')" >

	


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
				<h3><s:text name="cpGestioneRT.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpRT" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smpRT -->
	
	
<div class="stdMessagePanel" id="smpRT">
	<customtag:stdMessagePanel id="smpRT" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpRT --></div>

			
	
	<div id="p_wpFiltroRicercaRT" class="widgetsPanelBlock"><!-- startFragment:p_wpFiltroRicercaRT -->
	
	

<div class="widgetsPanel" id="wpFiltroRicercaRT">
	
	<customtag:widgetsPanel id="wpFiltroRicercaRTTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneRT','iuv')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.iuv.label')}" labelFor="widg_iuv" errorFor="appDataricercaRT.iuv" labelId="iuvLbl"
	  >


<!-- widget iuv -->
<s:textfield name="appDataricercaRT.iuv" id="widg_iuv"
maxlength="35"	disabled="isWidgetDisabled('cpGestioneRT','iuv')"
	size="35" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','idTransazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.idTransazione.label')}" labelFor="widg_idTransazione" errorFor="appDataricercaRT.transactionId" labelId="idTransazioneLbl"
	  >


<!-- widget idTransazione -->
<s:textfield name="appDataricercaRT.transactionId" id="widg_idTransazione"
	disabled="isWidgetDisabled('cpGestioneRT','idTransazione')"
	size="25" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','idApplicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.idApplicazione.label')}" labelFor="widg_idApplicazione" errorFor="appDataricercaRT.applicationId" labelId="idApplicazioneLbl"
	  >


<!-- widget idApplicazione -->
<s:textfield name="appDataricercaRT.applicationId" id="widg_idApplicazione"
	disabled="isWidgetDisabled('cpGestioneRT','idApplicazione')"
	size="25" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','lastUpdateDa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.lastUpdateDa.label')}" labelFor="widg_lastUpdateDa" errorFor="appDataricercaRT.lastUpdateDa" labelId="lastUpdateDaLbl"
	  >


<!-- widget lastUpdateDa -->
<s:textfield name="appDataricercaRT.lastUpdateDa" id="widg_lastUpdateDa"
	disabled="isWidgetDisabled('cpGestioneRT','lastUpdateDa')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','lastUpdateA')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.lastUpdateA.label')}" labelFor="widg_lastUpdateA" errorFor="appDataricercaRT.lastUpdateA" labelId="lastUpdateALbl"
	  >


<!-- widget lastUpdateA -->
<s:textfield name="appDataricercaRT.lastUpdateA" id="widg_lastUpdateA"
	disabled="isWidgetDisabled('cpGestioneRT','lastUpdateA')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','insertDateDa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.insertDateDa.label')}" labelFor="widg_insertDateDa" errorFor="appDataricercaRT.insertDateDa" labelId="insertDateDaLbl"
	  >


<!-- widget insertDateDa -->
<s:textfield name="appDataricercaRT.insertDateDa" id="widg_insertDateDa"
	disabled="isWidgetDisabled('cpGestioneRT','insertDateDa')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','insertDateA')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.insertDateA.label')}" labelFor="widg_insertDateA" errorFor="appDataricercaRT.insertDateA" labelId="insertDateALbl"
	  >


<!-- widget insertDateA -->
<s:textfield name="appDataricercaRT.insertDateA" id="widg_insertDateA"
	disabled="isWidgetDisabled('cpGestioneRT','insertDateA')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','cbListaStatiRpt')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.cbListaStatiRpt.label')}" labelFor="widg_cbListaStatiRpt" errorFor="appDataricercaRT.idEsitoPagamento" labelId="cbListaStatiRptLbl"
	  >


<!-- widget cbListaStatiRpt -->
<s:select name="appDataricercaRT.idEsitoPagamento" id="widg_cbListaStatiRpt"
          headerKey="" headerValue="" 
          list="appDatalistaCodiciEsitoPagamento"
          disabled="isWidgetDisabled('cpGestioneRT','cbListaStatiRpt')"
          listKey="idEsitoPagamento"
          listValue="descrizione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRT','idMsgRichiesta')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRT.idMsgRichiesta.label')}" labelFor="widg_idMsgRichiesta" errorFor="appDataricercaRT.idMsgRichiesta" labelId="idMsgRichiestaLbl"
	  >


<!-- widget idMsgRichiesta -->
<s:textfield name="appDataricercaRT.idMsgRichiesta" id="widg_idMsgRichiesta"
	disabled="isWidgetDisabled('cpGestioneRT','idMsgRichiesta')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFiltroRicercaRT --></div>

			
	
	<div id="p_wpAzioneRicercaRT" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioneRicercaRT -->
	
	

<div class="widgetsPanel" id="wpAzioneRicercaRT">
	
	<customtag:widgetsPanel id="wpAzioneRicercaRTTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneRT','cerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget cerca -->
<s:submit name="widg_cerca" id="widg_cerca" method="handleCerca_CLICKED"
	key="cpGestioneRT.cerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpGestioneRT','cerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioneRicercaRT --></div>

			
	
	<div id="p_wpRisultatoRicercaRT" class="widgetsPanelBlock"><!-- startFragment:p_wpRisultatoRicercaRT -->
	
	

<div class="widgetsPanel" id="wpRisultatoRicercaRT">
	

	
	
<s:if test="isWidgetVisible('cpGestioneRT','tRicerca')" >

	
<div class="tableWidget">


<!-- widget tRicerca -->
	<csiuicore:ajaxify id="p_wpRisultatoRicercaRT" 
		widgetType="table" 
		pageId="cpGestioneRT">
<s:set name="cpGestioneRT_tRicerca_clearStatus" value="isTableClearStatus('cpGestioneRT_tRicerca')" />
<display:table name="appDatalistaRT"
               excludedParams="*"
               export="true"
               id="widg_tRicerca"
               pagesize="10"
               requestURI="cpGestioneRT.do"
               keepStatus="true"
               clearStatus="${cpGestioneRT_tRicerca_clearStatus}"
               uid="row_tRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tRicerca.id}" name="appDataselettoreIdRT" id="%{'tRicerca-editcell-'+ (#attr.row_tRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="iuv" titleKey="cpGestioneRT.tRicerca.iuv.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="iuv" titleKey="cpGestioneRT.tRicerca.iuv.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="iuv" titleKey="cpGestioneRT.tRicerca.iuv.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="applicationId" titleKey="cpGestioneRT.tRicerca.applicationId.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="applicationId" titleKey="cpGestioneRT.tRicerca.applicationId.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="applicationId" titleKey="cpGestioneRT.tRicerca.applicationId.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="transactionId" titleKey="cpGestioneRT.tRicerca.transactionId.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="transactionId" titleKey="cpGestioneRT.tRicerca.transactionId.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="transactionId" titleKey="cpGestioneRT.tRicerca.transactionId.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="insertDate" titleKey="cpGestioneRT.tRicerca.insertDate.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="insertDate" titleKey="cpGestioneRT.tRicerca.insertDate.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="insertDate" titleKey="cpGestioneRT.tRicerca.insertDate.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="lastUpdate" titleKey="cpGestioneRT.tRicerca.lastUpdate.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="lastUpdate" titleKey="cpGestioneRT.tRicerca.lastUpdate.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="lastUpdate" titleKey="cpGestioneRT.tRicerca.lastUpdate.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="descEsitoPagamento" titleKey="cpGestioneRT.tRicerca.descEsitoPagamento.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descEsitoPagamento" titleKey="cpGestioneRT.tRicerca.descEsitoPagamento.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descEsitoPagamento" titleKey="cpGestioneRT.tRicerca.descEsitoPagamento.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="idMsgRichiesta" titleKey="cpGestioneRT.tRicerca.idMsgRichiesta.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="idMsgRichiesta" titleKey="cpGestioneRT.tRicerca.idMsgRichiesta.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="idMsgRichiesta" titleKey="cpGestioneRT.tRicerca.idMsgRichiesta.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpRisultatoRicercaRT --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneRT','exportRT')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget exportRT -->
<s:submit name="widg_exportRT" id="widg_exportRT" method="handleExportRT_CLICKED"
	key="cpGestioneRT.exportRT.label" cssClass="buttonWidget showReport"
	disabled="isWidgetDisabled('cpGestioneRT','exportRT')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneRT','esportRPTassociata')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget esportRPTassociata -->
<s:submit name="widg_esportRPTassociata" id="widg_esportRPTassociata" method="handleEsportRPTassociata_CLICKED"
	key="cpGestioneRT.esportRPTassociata.label" cssClass="buttonWidget showReport"
	disabled="isWidgetDisabled('cpGestioneRT','esportRPTassociata')" />

	
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
