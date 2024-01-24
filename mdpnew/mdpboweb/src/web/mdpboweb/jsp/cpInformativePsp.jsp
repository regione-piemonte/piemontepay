<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpInformativePspAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpInformativePsp -->
<s:form id="cpInformativePsp" action="cpInformativePsp" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpInformativePsp','mnuView')" >

	


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
				<h3><s:text name="cpInformativePsp.pmain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_stdMsg" class="stdMessagePanelBlock scroll"><!-- startFragment:p_stdMsg -->
	
	
<div class="stdMessagePanel" id="stdMsg">
	<customtag:stdMessagePanel id="stdMsg" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_stdMsg --></div>

			
	
	<div id="p_wsRicercaInformativa" class="widgetsPanelBlock"><!-- startFragment:p_wsRicercaInformativa -->
	
	

<div class="widgetsPanel" id="wsRicercaInformativa">
	
	<customtag:widgetsPanel id="wsRicercaInformativaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpInformativePsp','txdatapubblicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpInformativePsp.txdatapubblicazione.label')}" labelFor="widg_txdatapubblicazione" errorFor="appDatafiltroRicercaInformativePsp.datapubblicazione" labelId="txdatapubblicazioneLbl"
	  >


<!-- widget txdatapubblicazione -->
<s:textfield name="appDatafiltroRicercaInformativePsp.datapubblicazione" id="widg_txdatapubblicazione"
	disabled="isWidgetDisabled('cpInformativePsp','txdatapubblicazione')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpInformativePsp','txdatainserimento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpInformativePsp.txdatainserimento.label')}" labelFor="widg_txdatainserimento" errorFor="appDatafiltroRicercaInformativePsp.datainserimento" labelId="txdatainserimentoLbl"
	  >


<!-- widget txdatainserimento -->
<s:textfield name="appDatafiltroRicercaInformativePsp.datainserimento" id="widg_txdatainserimento"
	disabled="isWidgetDisabled('cpInformativePsp','txdatainserimento')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpInformativePsp','txragionesociale')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpInformativePsp.txragionesociale.label')}" labelFor="widg_txragionesociale" errorFor="appDatafiltroRicercaInformativePsp.ragionesociale" labelId="txragionesocialeLbl"
	  >


<!-- widget txragionesociale -->
<s:textfield name="appDatafiltroRicercaInformativePsp.ragionesociale" id="widg_txragionesociale"
	disabled="isWidgetDisabled('cpInformativePsp','txragionesociale')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpInformativePsp','cbtipoversamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpInformativePsp.cbtipoversamento.label')}" labelFor="widg_cbtipoversamento" errorFor="appDatafiltroRicercaInformativePsp.tipoversamento" labelId="cbtipoversamentoLbl"
	  >


<!-- widget cbtipoversamento -->
<s:select name="appDatafiltroRicercaInformativePsp.tipoversamento" id="widg_cbtipoversamento"
          headerKey="" headerValue="" 
          list="appDatalistaTipoversamento"
          disabled="isWidgetDisabled('cpInformativePsp','cbtipoversamento')"
          listKey="id"
          listValue="descrizione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wsRicercaInformativa --></div>

			
	
	<div id="p_wsRicerca" class="widgetsPanelBlock"><!-- startFragment:p_wsRicerca -->
	
	

<div class="widgetsPanel" id="wsRicerca">
	
	<customtag:widgetsPanel id="wsRicercaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpInformativePsp','btRicerca')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btRicerca -->
<s:submit name="widg_btRicerca" id="widg_btRicerca" method="handleBtRicerca_CLICKED"
	key="cpInformativePsp.btRicerca.label" cssClass="buttonWidget search"
	disabled="isWidgetDisabled('cpInformativePsp','btRicerca')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wsRicerca --></div>

			
	
	<div id="p_wsListaInformative" class="widgetsPanelBlock"><!-- startFragment:p_wsListaInformative -->
	
	

<div class="widgetsPanel" id="wsListaInformative">
	

	
	
<s:if test="isWidgetVisible('cpInformativePsp','tbRisultati')" >

	
<div class="tableWidget">


<!-- widget tbRisultati -->
	<csiuicore:ajaxify id="p_wsListaInformative" 
		widgetType="table" 
		pageId="cpInformativePsp">
<s:set name="cpInformativePsp_tbRisultati_clearStatus" value="isTableClearStatus('cpInformativePsp_tbRisultati')" />
<display:table name="appDatalistaInformativePsp"
               excludedParams="*"
               export="true"
               id="widg_tbRisultati"
               pagesize="30"
               requestURI="cpInformativePsp.do"
               keepStatus="true"
               clearStatus="${cpInformativePsp_tbRisultati_clearStatus}"
               uid="row_tbRisultati"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbRisultati.idinformativapsp}" name="appDataselettoreInformativePsp" id="%{'tbRisultati-editcell-'+ (#attr.row_tbRisultati_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="datapubblicazione" titleKey="cpInformativePsp.tbRisultati.datapubblicazione.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="datapubblicazione" titleKey="cpInformativePsp.tbRisultati.datapubblicazione.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="datapubblicazione" titleKey="cpInformativePsp.tbRisultati.datapubblicazione.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="datainiziovalidita" titleKey="cpInformativePsp.tbRisultati.datainiziovalidita.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="datainiziovalidita" titleKey="cpInformativePsp.tbRisultati.datainiziovalidita.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="datainiziovalidita" titleKey="cpInformativePsp.tbRisultati.datainiziovalidita.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="ragionesociale" titleKey="cpInformativePsp.tbRisultati.ragionesociale.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="ragionesociale" titleKey="cpInformativePsp.tbRisultati.ragionesociale.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="ragionesociale" titleKey="cpInformativePsp.tbRisultati.ragionesociale.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="tipoversamento" titleKey="cpInformativePsp.tbRisultati.tipoversamento.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="tipoversamento" titleKey="cpInformativePsp.tbRisultati.tipoversamento.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="tipoversamento" titleKey="cpInformativePsp.tbRisultati.tipoversamento.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="condizionieconomichemassime" titleKey="cpInformativePsp.tbRisultati.condizionieconomichemassime.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="condizionieconomichemassime" titleKey="cpInformativePsp.tbRisultati.condizionieconomichemassime.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="condizionieconomichemassime" titleKey="cpInformativePsp.tbRisultati.condizionieconomichemassime.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="datainserimento" titleKey="cpInformativePsp.tbRisultati.datainserimento.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="datainserimento" titleKey="cpInformativePsp.tbRisultati.datainserimento.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="datainserimento" titleKey="cpInformativePsp.tbRisultati.datainserimento.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wsListaInformative --></div>

			
	
	<div id="p_wsAction" class="widgetsPanelBlock"><!-- startFragment:p_wsAction -->
	
	

<div class="widgetsPanel" id="wsAction">
	
	<customtag:widgetsPanel id="wsActionTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpInformativePsp','btVai')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btVai -->
<s:submit name="widg_btVai" id="widg_btVai" method="handleBtVai_CLICKED"
	key="cpInformativePsp.btVai.label" cssClass="buttonWidget detail"
	disabled="isWidgetDisabled('cpInformativePsp','btVai')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wsAction --></div>

	

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
