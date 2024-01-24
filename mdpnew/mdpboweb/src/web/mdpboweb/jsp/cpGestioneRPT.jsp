<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneRPTAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneRPT -->
<s:form id="cpGestioneRPT" action="cpGestioneRPT" method="post">
		
	
	
		
<!-- ####################### PANNELLO CONTENUTI ###################### -->
<div id="contentPanel">

	<!-- ================ UDLRC Layout UP PANEL ================ -->
	<div id="northPanel">
		<div class="wrapper">
		
	
	<div id="p_Up Panel" class="formPanelBlock"><!-- startFragment:p_Up Panel -->

	
	

	
	
			
	
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

	

	<!-- endFragment:p_Up Panel --></div>

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

	
		
			
<s:if test="isWidgetVisible('cpGestioneRPT','mnuView')" >

	


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
				<h3><s:text name="cpGestioneRPT.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpRPT" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smpRPT -->
	
	
<div class="stdMessagePanel" id="smpRPT">
	<customtag:stdMessagePanel id="smpRPT" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpRPT --></div>

			
	
	<div id="p_wpFiltroRicercaRPT" class="widgetsPanelBlock"><!-- startFragment:p_wpFiltroRicercaRPT -->
	
	

<div class="widgetsPanel" id="wpFiltroRicercaRPT">
	
	<customtag:widgetsPanel id="wpFiltroRicercaRPTTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneRPT','iuv')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.iuv.label')}" labelFor="widg_iuv" errorFor="appDataricercaRPT.iuv" labelId="iuvLbl"
	  >


<!-- widget iuv -->
<s:textfield name="appDataricercaRPT.iuv" id="widg_iuv"
maxlength="35"	disabled="isWidgetDisabled('cpGestioneRPT','iuv')"
	size="35" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','idTransaction')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.idTransaction.label')}" labelFor="widg_idTransaction" errorFor="appDataricercaRPT.transactionId" labelId="idTransactionLbl"
	  >


<!-- widget idTransaction -->
<s:textfield name="appDataricercaRPT.transactionId" id="widg_idTransaction"
	disabled="isWidgetDisabled('cpGestioneRPT','idTransaction')"
	size="35" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','idApplication')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.idApplication.label')}" labelFor="widg_idApplication" errorFor="appDataricercaRPT.applicationId" labelId="idApplicationLbl"
	  >


<!-- widget idApplication -->
<s:textfield name="appDataricercaRPT.applicationId" id="widg_idApplication"
	disabled="isWidgetDisabled('cpGestioneRPT','idApplication')"
	size="35" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','lastUpdateDa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.lastUpdateDa.label')}" labelFor="widg_lastUpdateDa" errorFor="appDataricercaRPT.lastUpdateDa" labelId="lastUpdateDaLbl"
	  >


<!-- widget lastUpdateDa -->
<s:textfield name="appDataricercaRPT.lastUpdateDa" id="widg_lastUpdateDa"
	disabled="isWidgetDisabled('cpGestioneRPT','lastUpdateDa')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','lastUpdateA')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.lastUpdateA.label')}" labelFor="widg_lastUpdateA" errorFor="appDataricercaRPT.lastUpdateA" labelId="lastUpdateALbl"
	  >


<!-- widget lastUpdateA -->
<s:textfield name="appDataricercaRPT.lastUpdateA" id="widg_lastUpdateA"
	disabled="isWidgetDisabled('cpGestioneRPT','lastUpdateA')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','insertDateDa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.insertDateDa.label')}" labelFor="widg_insertDateDa" errorFor="appDataricercaRPT.insertDateDa" labelId="insertDateDaLbl"
	  >


<!-- widget insertDateDa -->
<s:textfield name="appDataricercaRPT.insertDateDa" id="widg_insertDateDa"
	disabled="isWidgetDisabled('cpGestioneRPT','insertDateDa')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','insertDateA')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.insertDateA.label')}" labelFor="widg_insertDateA" errorFor="appDataricercaRPT.insertDateA" labelId="insertDateALbl"
	  >


<!-- widget insertDateA -->
<s:textfield name="appDataricercaRPT.insertDateA" id="widg_insertDateA"
	disabled="isWidgetDisabled('cpGestioneRPT','insertDateA')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','cbStatiRpt')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.cbStatiRpt.label')}" labelFor="widg_cbStatiRpt" errorFor="appDataricercaRPT.idStatiRpt" labelId="cbStatiRptLbl"
	  >


<!-- widget cbStatiRpt -->
<s:select name="appDataricercaRPT.idStatiRpt" id="widg_cbStatiRpt"
          headerKey="" headerValue="" 
          list="appDatalistaStatiRpt"
          disabled="isWidgetDisabled('cpGestioneRPT','cbStatiRpt')"
          listKey="idStatiRpt"
          listValue="descrizione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneRPT','idMsgRichiesta')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneRPT.idMsgRichiesta.label')}" labelFor="widg_idMsgRichiesta" errorFor="appDataricercaRPT.idMsgRichiesta" labelId="idMsgRichiestaLbl"
	  >


<!-- widget idMsgRichiesta -->
<s:textfield name="appDataricercaRPT.idMsgRichiesta" id="widg_idMsgRichiesta"
maxlength="35"	disabled="isWidgetDisabled('cpGestioneRPT','idMsgRichiesta')"
	size="35" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFiltroRicercaRPT --></div>

			
	
	<div id="p_wpAzioneRicercaRPT" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioneRicercaRPT -->
	
	

<div class="widgetsPanel" id="wpAzioneRicercaRPT">
	
	<customtag:widgetsPanel id="wpAzioneRicercaRPTTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneRPT','btCerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btCerca -->
<s:submit name="widg_btCerca" id="widg_btCerca" method="handleBtCerca_CLICKED"
	key="cpGestioneRPT.btCerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpGestioneRPT','btCerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioneRicercaRPT --></div>

			
	
	<div id="p_wpRisultatoRicercaRPT" class="widgetsPanelBlock"><!-- startFragment:p_wpRisultatoRicercaRPT -->
	
	

<div class="widgetsPanel" id="wpRisultatoRicercaRPT">
	

	
	
<s:if test="isWidgetVisible('cpGestioneRPT','tbRicerca')" >

	
<div class="tableWidget">


<!-- widget tbRicerca -->
	<csiuicore:ajaxify id="p_wpRisultatoRicercaRPT" 
		widgetType="table" 
		pageId="cpGestioneRPT">
<s:set name="cpGestioneRPT_tbRicerca_clearStatus" value="isTableClearStatus('cpGestioneRPT_tbRicerca')" />
<display:table name="appDatalistaRPT"
               excludedParams="*"
               export="true"
               id="widg_tbRicerca"
               pagesize="10"
               requestURI="cpGestioneRPT.do"
               keepStatus="true"
               clearStatus="${cpGestioneRPT_tbRicerca_clearStatus}"
               uid="row_tbRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbRicerca.id}" name="appDataselettoreIdRPT" id="%{'tbRicerca-editcell-'+ (#attr.row_tbRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="iuv" titleKey="cpGestioneRPT.tbRicerca.iuv.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="iuv" titleKey="cpGestioneRPT.tbRicerca.iuv.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="iuv" titleKey="cpGestioneRPT.tbRicerca.iuv.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="applicationId" titleKey="cpGestioneRPT.tbRicerca.applicationId.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="applicationId" titleKey="cpGestioneRPT.tbRicerca.applicationId.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="applicationId" titleKey="cpGestioneRPT.tbRicerca.applicationId.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="transactionId" titleKey="cpGestioneRPT.tbRicerca.transactionId.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="transactionId" titleKey="cpGestioneRPT.tbRicerca.transactionId.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="transactionId" titleKey="cpGestioneRPT.tbRicerca.transactionId.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="insertDate" titleKey="cpGestioneRPT.tbRicerca.insertDate.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="insertDate" titleKey="cpGestioneRPT.tbRicerca.insertDate.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="insertDate" titleKey="cpGestioneRPT.tbRicerca.insertDate.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="lastUpdate" titleKey="cpGestioneRPT.tbRicerca.lastUpdate.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="lastUpdate" titleKey="cpGestioneRPT.tbRicerca.lastUpdate.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="lastUpdate" titleKey="cpGestioneRPT.tbRicerca.lastUpdate.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="descStatiRpt" titleKey="cpGestioneRPT.tbRicerca.descStatiRpt.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descStatiRpt" titleKey="cpGestioneRPT.tbRicerca.descStatiRpt.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descStatiRpt" titleKey="cpGestioneRPT.tbRicerca.descStatiRpt.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="idMsgRichiesta" titleKey="cpGestioneRPT.tbRicerca.idMsgRichiesta.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="idMsgRichiesta" titleKey="cpGestioneRPT.tbRicerca.idMsgRichiesta.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="idMsgRichiesta" titleKey="cpGestioneRPT.tbRicerca.idMsgRichiesta.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpRisultatoRicercaRPT --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="8" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneRPT','vaiAlDettaglio')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget vaiAlDettaglio -->
<s:submit name="widg_vaiAlDettaglio" id="widg_vaiAlDettaglio" method="handleVaiAlDettaglio_CLICKED"
	key="cpGestioneRPT.vaiAlDettaglio.label" cssClass="buttonWidget forward"
	disabled="isWidgetDisabled('cpGestioneRPT','vaiAlDettaglio')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneRPT','btxportRpt')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btxportRpt -->
<s:submit name="widg_btxportRpt" id="widg_btxportRpt" method="handleBtxportRpt_CLICKED"
	key="cpGestioneRPT.btxportRpt.label" cssClass="buttonWidget showReport"
	disabled="isWidgetDisabled('cpGestioneRPT','btxportRpt')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneRPT','EsportaRTassociata')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget EsportaRTassociata -->
<s:submit name="widg_EsportaRTassociata" id="widg_EsportaRTassociata" method="handleEsportaRTassociata_CLICKED"
	key="cpGestioneRPT.EsportaRTassociata.label" cssClass="buttonWidget showReport"
	disabled="isWidgetDisabled('cpGestioneRPT','EsportaRTassociata')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneRPT','btDettStatRPT')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btDettStatRPT -->
<s:submit name="widg_btDettStatRPT" id="widg_btDettStatRPT" method="handleBtDettStatRPT_CLICKED"
	key="cpGestioneRPT.btDettStatRPT.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpGestioneRPT','btDettStatRPT')" />

	
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
