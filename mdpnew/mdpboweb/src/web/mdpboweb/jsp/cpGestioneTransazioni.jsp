<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneTransazioniAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneTransazioni -->
<s:form id="cpGestioneTransazioni" action="cpGestioneTransazioni" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneTransazioni','mnuView')" >

	


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
				<h3><s:text name="cpGestioneTransazioni.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGestioneTransazioni" class="stdMessagePanelBlock"><!-- startFragment:p_smpGestioneTransazioni -->
	
	
<div class="stdMessagePanel" id="smpGestioneTransazioni">
	<customtag:stdMessagePanel id="smpGestioneTransazioni" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGestioneTransazioni --></div>

			
	
	<div id="p_wpRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpRicerca -->
	
	

<div class="widgetsPanel" id="wpRicerca">
	
	<customtag:widgetsPanel id="wpRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneTransazioni','idTransazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneTransazioni.idTransazione.label')}" labelFor="widg_idTransazione" errorFor="appDataricercaTransazione.idTransazione" labelId="idTransazioneLbl"
	  >


<!-- widget idTransazione -->
<s:textfield name="appDataricercaTransazione.idTransazione" id="widg_idTransazione"
	disabled="isWidgetDisabled('cpGestioneTransazioni','idTransazione')"
	size="25" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneTransazioni','cbApplicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneTransazioni.cbApplicazione.label')}" labelFor="widg_cbApplicazione" errorFor="appDataricercaTransazione.idApplicazione" labelId="cbApplicazioneLbl"
	  >


<!-- widget cbApplicazione -->
<s:select name="appDataricercaTransazione.idApplicazione" id="widg_cbApplicazione"
           
          list="appDataapplicazioni"
          disabled="isWidgetDisabled('cpGestioneTransazioni','cbApplicazione')"
          listKey="idApplicazione"
          listValue="nomeApplicazione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneTransazioni','cbStato')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneTransazioni.cbStato.label')}" labelFor="widg_cbStato" errorFor="appDataricercaTransazione.codStato" labelId="cbStatoLbl"
	  >


<!-- widget cbStato -->
<s:select name="appDataricercaTransazione.codStato" id="widg_cbStato"
           
          list="appDatastatiTransazione"
          disabled="isWidgetDisabled('cpGestioneTransazioni','cbStato')"
          listKey="codStato"
          listValue="descrStato"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneTransazioni','calDataInizio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneTransazioni.calDataInizio.label')}" labelFor="widg_calDataInizio" errorFor="appDataricercaTransazione.cercaDataInizio" labelId="calDataInizioLbl"
	  >


<!-- widget calDataInizio -->
<s:textfield name="appDataricercaTransazione.cercaDataInizio" id="widg_calDataInizio"
	disabled="isWidgetDisabled('cpGestioneTransazioni','calDataInizio')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneTransazioni','calDataFine')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneTransazioni.calDataFine.label')}" labelFor="widg_calDataFine" errorFor="appDataricercaTransazione.cercaDataFine" labelId="calDataFineLbl"
	  >


<!-- widget calDataFine -->
<s:textfield name="appDataricercaTransazione.cercaDataFine" id="widg_calDataFine"
	disabled="isWidgetDisabled('cpGestioneTransazioni','calDataFine')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpRicerca --></div>

			
	
	<div id="p_wpAzioniRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniRicerca -->
	
	

<div class="widgetsPanel" id="wpAzioniRicerca">
	
	<customtag:widgetsPanel id="wpAzioniRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneTransazioni','btnCerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnCerca -->
<s:submit name="widg_btnCerca" id="widg_btnCerca" method="handleBtnCerca_CLICKED"
	key="cpGestioneTransazioni.btnCerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpGestioneTransazioni','btnCerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniRicerca --></div>

			
	
	<div id="p_ptReportRicerca" class="widgetsPanelBlock"><!-- startFragment:p_ptReportRicerca -->
	
	

<div class="widgetsPanel" id="ptReportRicerca">
	
	<customtag:widgetsPanel id="ptReportRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneTransazioni','ptReportRicerca')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget ptReportRicerca -->
<s:property value="appDataselettoreIdTipologiaCommissione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_ptReportRicerca --></div>

			
	
	<div id="p_ptReportCerca" class="widgetsPanelBlock"><!-- startFragment:p_ptReportCerca -->
	
	

<div class="widgetsPanel" id="ptReportCerca">
	
	<customtag:widgetsPanel id="ptReportCercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneTransazioni','btnPrevPage')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnPrevPage -->
<s:submit name="widg_btnPrevPage" id="widg_btnPrevPage" method="handleBtnPrevPage_CLICKED"
	key="cpGestioneTransazioni.btnPrevPage.label" cssClass="buttonWidget previousItem"
	disabled="isWidgetDisabled('cpGestioneTransazioni','btnPrevPage')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneTransazioni','btnNextPage')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnNextPage -->
<s:submit name="widg_btnNextPage" id="widg_btnNextPage" method="handleBtnNextPage_CLICKED"
	key="cpGestioneTransazioni.btnNextPage.label" cssClass="buttonWidget previousItem"
	disabled="isWidgetDisabled('cpGestioneTransazioni','btnNextPage')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_ptReportCerca --></div>

			
	
	<div id="p_wpTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpTabella -->
	
	

<div class="widgetsPanel" id="wpTabella">
	

	
	
<s:if test="isWidgetVisible('cpGestioneTransazioni','tRicerca')" >

	
<div class="tableWidget">


<!-- widget tRicerca -->
	<csiuicore:ajaxify id="p_wpTabella" 
		widgetType="table" 
		pageId="cpGestioneTransazioni">
<s:set name="cpGestioneTransazioni_tRicerca_clearStatus" value="isTableClearStatus('cpGestioneTransazioni_tRicerca')" />
<display:table name="appDatatransazioni"
               excludedParams="*"
               export="true"
               id="widg_tRicerca"
               pagesize="10"
               requestURI="cpGestioneTransazioni.do"
               keepStatus="true"
               clearStatus="${cpGestioneTransazioni_tRicerca_clearStatus}"
               uid="row_tRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tRicerca.idTransazione}" name="appDataselettoreIdTransazione" id="%{'tRicerca-editcell-'+ (#attr.row_tRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="nomeApplicazione" titleKey="cpGestioneTransazioni.tRicerca.nomeApplicazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="nomeApplicazione" titleKey="cpGestioneTransazioni.tRicerca.nomeApplicazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="nomeApplicazione" titleKey="cpGestioneTransazioni.tRicerca.nomeApplicazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="idTransazione" titleKey="cpGestioneTransazioni.tRicerca.idTransazione.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="idTransazione" titleKey="cpGestioneTransazioni.tRicerca.idTransazione.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="idTransazione" titleKey="cpGestioneTransazioni.tRicerca.idTransazione.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrGateway" titleKey="cpGestioneTransazioni.tRicerca.descrGateway.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrGateway" titleKey="cpGestioneTransazioni.tRicerca.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrGateway" titleKey="cpGestioneTransazioni.tRicerca.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrPayment" titleKey="cpGestioneTransazioni.tRicerca.descrPayment.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrPayment" titleKey="cpGestioneTransazioni.tRicerca.descrPayment.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrPayment" titleKey="cpGestioneTransazioni.tRicerca.descrPayment.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="dataInizio" titleKey="cpGestioneTransazioni.tRicerca.dataInizio.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="dataInizio" titleKey="cpGestioneTransazioni.tRicerca.dataInizio.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="dataInizio" titleKey="cpGestioneTransazioni.tRicerca.dataInizio.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="finishDate" titleKey="cpGestioneTransazioni.tRicerca.finishDate.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="finishDate" titleKey="cpGestioneTransazioni.tRicerca.finishDate.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="finishDate" titleKey="cpGestioneTransazioni.tRicerca.finishDate.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="descrStato" titleKey="cpGestioneTransazioni.tRicerca.descrStato.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrStato" titleKey="cpGestioneTransazioni.tRicerca.descrStato.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrStato" titleKey="cpGestioneTransazioni.tRicerca.descrStato.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="importoTransazione" titleKey="cpGestioneTransazioni.tRicerca.importoTransazione.label" 
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0.00}"  class="numbers"  media="html" />

		<display:column property="importoTransazione" titleKey="cpGestioneTransazioni.tRicerca.importoTransazione.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="importoTransazione" titleKey="cpGestioneTransazioni.tRicerca.importoTransazione.export.label"
			sortable="false" headerClass="nosort"
			format="{0,number,#,##0.00}" 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabella --></div>

			
	
	<div id="p_wpAzioniTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniTabella -->
	
	

<div class="widgetsPanel" id="wpAzioniTabella">
	
	<customtag:widgetsPanel id="wpAzioniTabellaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneTransazioni','btnDettaglio')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnDettaglio -->
<s:submit name="widg_btnDettaglio" id="widg_btnDettaglio" method="handleBtnDettaglio_CLICKED"
	key="cpGestioneTransazioni.btnDettaglio.label" cssClass="buttonWidget detail"
	disabled="isWidgetDisabled('cpGestioneTransazioni','btnDettaglio')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
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
