<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneGiornaleEventiAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpGestioneGiornaleEventi -->
<s:form id="cpGestioneGiornaleEventi" action="cpGestioneGiornaleEventi" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','mnuView')" >

	


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
				<h3><s:text name="cpGestioneGiornaleEventi.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpGiornaleEventi" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smpGiornaleEventi -->
	
	
<div class="stdMessagePanel" id="smpGiornaleEventi">
	<customtag:stdMessagePanel id="smpGiornaleEventi" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpGiornaleEventi --></div>

			
	
	<div id="p_wpRicercaGde" class="widgetsPanelBlock"><!-- startFragment:p_wpRicercaGde -->
	
	
<h4 class="wpLabel">Ricerca </h4>
<div class="widgetsPanel" id="wpRicercaGde">
	
	<customtag:widgetsPanel id="wpRicercaGdeTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','iuv')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGiornaleEventi.iuv.label')}" labelFor="widg_iuv" errorFor="appDataricercaGiornaleEventi.iuv" labelId="iuvLbl"
	  >


<!-- widget iuv -->
<s:textfield name="appDataricercaGiornaleEventi.iuv" id="widg_iuv"
maxlength="35"	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','iuv')"
	size="35" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','dataEvento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGiornaleEventi.dataEvento.label')}" labelFor="widg_dataEvento" errorFor="appDataricercaGiornaleEventi.dataoraevento" labelId="dataEventoLbl"
	  >


<!-- widget dataEvento -->
<s:textfield name="appDataricercaGiornaleEventi.dataoraevento" id="widg_dataEvento"
	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','dataEvento')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','identificativoDominio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGiornaleEventi.identificativoDominio.label')}" labelFor="widg_identificativoDominio" errorFor="appDataricercaGiornaleEventi.identificativodominio" labelId="identificativoDominioLbl"
	  >


<!-- widget identificativoDominio -->
<s:textfield name="appDataricercaGiornaleEventi.identificativodominio" id="widg_identificativoDominio"
	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','identificativoDominio')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','identificativoFruitore')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGiornaleEventi.identificativoFruitore.label')}" labelFor="widg_identificativoFruitore" errorFor="appDataricercaGiornaleEventi.identificativofruitore" labelId="identificativoFruitoreLbl"
	  >


<!-- widget identificativoFruitore -->
<s:textfield name="appDataricercaGiornaleEventi.identificativofruitore" id="widg_identificativoFruitore"
	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','identificativoFruitore')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','codicePagamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGiornaleEventi.codicePagamento.label')}" labelFor="widg_codicePagamento" errorFor="appDataricercaGiornaleEventi.codiceContesto" labelId="codicePagamentoLbl"
	  >


<!-- widget codicePagamento -->
<s:textfield name="appDataricercaGiornaleEventi.codiceContesto" id="widg_codicePagamento"
	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','codicePagamento')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpRicercaGde --></div>

			
	
	<div id="p_wpRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wpRicerca -->
	
	

<div class="widgetsPanel" id="wpRicerca">
	
	<customtag:widgetsPanel id="wpRicercaTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','cerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget cerca -->
<s:submit name="widg_cerca" id="widg_cerca" method="handleCerca_CLICKED"
	key="cpGestioneGiornaleEventi.cerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','cerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','btPulisci')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btPulisci -->
<s:submit name="widg_btPulisci" id="widg_btPulisci" method="handleBtPulisci_CLICKED"
	key="cpGestioneGiornaleEventi.btPulisci.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','btPulisci')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpRicerca --></div>

			
	
	<div id="p_wpRisultatiEventi" class="widgetsPanelBlock"><!-- startFragment:p_wpRisultatiEventi -->
	
	

<div class="widgetsPanel" id="wpRisultatiEventi">
	

	
	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','tRicerca')" >

	
<div class="tableWidget">


<!-- widget tRicerca -->
	<csiuicore:ajaxify id="p_wpRisultatiEventi" 
		widgetType="table" 
		pageId="cpGestioneGiornaleEventi">
<s:set name="cpGestioneGiornaleEventi_tRicerca_clearStatus" value="isTableClearStatus('cpGestioneGiornaleEventi_tRicerca')" />
<display:table name="appDatalistaGiornaliEventi"
               excludedParams="*"
               export="true"
               id="widg_tRicerca"
               pagesize="10"
               requestURI="cpGestioneGiornaleEventi.do"
               keepStatus="true"
               clearStatus="${cpGestioneGiornaleEventi_tRicerca_clearStatus}"
               uid="row_tRicerca"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tRicerca.id}" name="appDataselettoreIdGiornaleEvento" id="%{'tRicerca-editcell-'+ (#attr.row_tRicerca_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="iuv" titleKey="cpGestioneGiornaleEventi.tRicerca.iuv.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="iuv" titleKey="cpGestioneGiornaleEventi.tRicerca.iuv.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="iuv" titleKey="cpGestioneGiornaleEventi.tRicerca.iuv.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="dataoraevento" titleKey="cpGestioneGiornaleEventi.tRicerca.dataoraevento.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="dataoraevento" titleKey="cpGestioneGiornaleEventi.tRicerca.dataoraevento.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="dataoraevento" titleKey="cpGestioneGiornaleEventi.tRicerca.dataoraevento.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="identificativodominio" titleKey="cpGestioneGiornaleEventi.tRicerca.identificativodominio.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="identificativodominio" titleKey="cpGestioneGiornaleEventi.tRicerca.identificativodominio.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="identificativodominio" titleKey="cpGestioneGiornaleEventi.tRicerca.identificativodominio.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="identificativofruitore" titleKey="cpGestioneGiornaleEventi.tRicerca.identificativofruitore.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="identificativofruitore" titleKey="cpGestioneGiornaleEventi.tRicerca.identificativofruitore.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="identificativofruitore" titleKey="cpGestioneGiornaleEventi.tRicerca.identificativofruitore.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="codiceContesto" titleKey="cpGestioneGiornaleEventi.tRicerca.codiceContesto.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="codiceContesto" titleKey="cpGestioneGiornaleEventi.tRicerca.codiceContesto.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="codiceContesto" titleKey="cpGestioneGiornaleEventi.tRicerca.codiceContesto.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="tipoevento" titleKey="cpGestioneGiornaleEventi.tRicerca.tipoevento.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="tipoevento" titleKey="cpGestioneGiornaleEventi.tRicerca.tipoevento.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="tipoevento" titleKey="cpGestioneGiornaleEventi.tRicerca.tipoevento.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="esito" titleKey="cpGestioneGiornaleEventi.tRicerca.esito.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="esito" titleKey="cpGestioneGiornaleEventi.tRicerca.esito.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="esito" titleKey="cpGestioneGiornaleEventi.tRicerca.esito.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpRisultatiEventi --></div>

			
	
	<div id="p_wpAzioniTabella" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniTabella -->
	
	

<div class="widgetsPanel" id="wpAzioniTabella">
	
	<customtag:widgetsPanel id="wpAzioniTabellaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGiornaleEventi','bpDettaglio')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget bpDettaglio -->
<s:submit name="widg_bpDettaglio" id="widg_bpDettaglio" method="handleBpDettaglio_CLICKED"
	key="cpGestioneGiornaleEventi.bpDettaglio.label" cssClass="buttonWidget load"
	disabled="isWidgetDisabled('cpGestioneGiornaleEventi','bpDettaglio')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
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
