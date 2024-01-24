<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %><%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpDettaglioGruppoAction" />


<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpDettaglioGruppo -->
<s:form id="cpDettaglioGruppo" action="cpDettaglioGruppo" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioGruppo','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioGruppo.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettGruppi" class="stdMessagePanelBlock"><!-- startFragment:p_smpDettGruppi -->
	
	
<div class="stdMessagePanel" id="smpDettGruppi">
	<customtag:stdMessagePanel id="smpDettGruppi" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettGruppi --></div>

			
	
	<div id="p_wpDati" class="widgetsPanelBlock"><!-- startFragment:p_wpDati -->
	
	

<div class="widgetsPanel" id="wpDati">
	
	<customtag:widgetsPanel id="wpDatiTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGruppo','txIdGruppo')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGruppo.txIdGruppo.label')}" labelFor="widg_txIdGruppo" errorFor="appDatagruppo.idgroup" labelId="txIdGruppoLbl"  position="first" >


<!-- widget txIdGruppo -->
<s:property value="appDatagruppo.idgroup" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDati_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDati_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','txDescrizioneGruppo')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGruppo.txDescrizioneGruppo.label')}" labelFor="widg_txDescrizioneGruppo" errorFor="appDatagruppo.description" labelId="txDescrizioneGruppoLbl"
	position="first"  >


<!-- widget txDescrizioneGruppo -->
<s:textfield name="appDatagruppo.description" id="widg_txDescrizioneGruppo"
	disabled="isWidgetDisabled('cpDettaglioGruppo','txDescrizioneGruppo')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDati_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDati_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','cbAppGroupToAdd')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbAppGroupToAdd" errorFor="appDataselettoreAppGroup" labelId="cbAppGroupToAddLbl"
	position="first"  >


<!-- widget cbAppGroupToAdd -->
<s:select name="appDataselettoreAppGroup" id="widg_cbAppGroupToAdd"
           
          list="appDataappForGroup"
          disabled="isWidgetDisabled('cpDettaglioGruppo','cbAppGroupToAdd')"
          listKey="idApplicazione"
          listValue="nomeApplicazione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','btnAddAppFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnAddAppFiltro -->
<s:submit name="widg_btnAddAppFiltro" id="widg_btnAddAppFiltro" method="handleBtnAddAppFiltro_CLICKED"
	key="cpDettaglioGruppo.btnAddAppFiltro.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioGruppo','btnAddAppFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDati_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','cbAppGruoupFiltered')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_cbAppGruoupFiltered" errorFor="appDatafilteredApplicationGroup" labelId="cbAppGruoupFilteredLbl"
	position="first"  >


<!-- widget cbAppGruoupFiltered -->
<s:select name="appDatafilteredApplicationGroup" id="widg_cbAppGruoupFiltered"
           
          list="appDataappForGroupFiltered"
          disabled="isWidgetDisabled('cpDettaglioGruppo','cbAppGruoupFiltered')"
          multiple="true"
          cssClass="selectMultiple"
          size="5"
          listKey="idApplicazione"
          listValue="nomeApplicazione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','btnEliminAppFiltro')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnEliminAppFiltro -->
<s:submit name="widg_btnEliminAppFiltro" id="widg_btnEliminAppFiltro" method="handleBtnEliminAppFiltro_CLICKED"
	key="cpDettaglioGruppo.btnEliminAppFiltro.label" cssClass="buttonWidget cancel"
	disabled="isWidgetDisabled('cpDettaglioGruppo','btnEliminAppFiltro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_4_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDati_4_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','cbListaRuoli')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGruppo.cbListaRuoli.label')}" labelFor="widg_cbListaRuoli" errorFor="appDataselettoreIdRuolo" labelId="cbListaRuoliLbl"
	position="first"  >


<!-- widget cbListaRuoli -->
<s:select name="appDataselettoreIdRuolo" id="widg_cbListaRuoli"
          headerKey="" headerValue="" 
          list="appDataruoli"
          disabled="isWidgetDisabled('cpDettaglioGruppo','cbListaRuoli')"
          listKey="idRuolo"
          listValue="descrRuolo"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDati_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','wpDati_5_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDati_5_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDati --></div>

			
	
	<div id="p_wpComandi" class="widgetsPanelBlock"><!-- startFragment:p_wpComandi -->
	
	

<div class="widgetsPanel" id="wpComandi">
	
	<customtag:widgetsPanel id="wpComandiTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGruppo','btIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btIndietro -->
<s:submit name="widg_btIndietro" id="widg_btIndietro" method="handleBtIndietro_CLICKED"
	key="cpDettaglioGruppo.btIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioGruppo','btIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGruppo','btSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btSalva -->
<s:submit name="widg_btSalva" id="widg_btSalva" method="handleBtSalva_CLICKED"
	key="cpDettaglioGruppo.btSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioGruppo','btSalva')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpComandi --></div>

			
	
	<div id="p_wpUtenti" class="widgetsPanelBlock"><!-- startFragment:p_wpUtenti -->
	
	

<div class="widgetsPanel" id="wpUtenti">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGruppo','tbListaUtenti')" >

	
<div class="tableWidget">


<!-- widget tbListaUtenti -->
	<csiuicore:ajaxify id="p_wpUtenti" 
		widgetType="table" 
		pageId="cpDettaglioGruppo">
<s:set name="cpDettaglioGruppo_tbListaUtenti_clearStatus" value="isTableClearStatus('cpDettaglioGruppo_tbListaUtenti')" />
<display:table name="appDatautentiDelGruppo"
               excludedParams="*"
               export="false"
               id="widg_tbListaUtenti"
               pagesize="20"
               requestURI="cpDettaglioGruppo.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGruppo_tbListaUtenti_clearStatus}"
               uid="row_tbListaUtenti"
               summary="" 
               
               class="dataTable">
	
		<display:column property="descrUtente" titleKey="cpDettaglioGruppo.tbListaUtenti.descrUtente.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpUtenti --></div>

	

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
