<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpDettaglioGatewayAction" />

	
	<div id="p_pDatiGeneraliGateway" class="formPanelBlock"><!-- startFragment:p_pDatiGeneraliGateway -->
		
	
<div class="formPanel" id="pDatiGeneraliGateway">


	
	

	
	
			
	
	<div id="p_wpDettaglioGateway" class="widgetsPanelBlock scroll"><!-- startFragment:p_wpDettaglioGateway -->
	
	

<div class="widgetsPanel" id="wpDettaglioGateway">
	
	<customtag:widgetsPanel id="wpDettaglioGatewayTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpDettaglioGateway_1_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpDettaglioGateway_1_1 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpDettaglioGateway_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfIdGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfIdGateway.label')}" labelFor="widg_tfIdGateway" errorFor="appDatagateway.idGateway" labelId="tfIdGatewayLbl"  position="first" >


<!-- widget tfIdGateway -->
<s:property value="appDatagateway.idGateway" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpDettaglioGateway_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfDescrGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfDescrGateway.label')}" labelFor="widg_tfDescrGateway" errorFor="appDatagateway.descrGateway" labelId="tfDescrGatewayLbl"
	position="first"  >


<!-- widget tfDescrGateway -->
<s:textfield name="appDatagateway.descrGateway" id="widg_tfDescrGateway"
	disabled="isWidgetDisabled('cpDettaglioGateway','tfDescrGateway')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpDettaglioGateway_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfProviderGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfProviderGateway.label')}" labelFor="widg_tfProviderGateway" errorFor="appDatagateway.providerGateway" labelId="tfProviderGatewayLbl"
	position="first"  >


<!-- widget tfProviderGateway -->
<s:textfield name="appDatagateway.providerGateway" id="widg_tfProviderGateway"
	disabled="isWidgetDisabled('cpDettaglioGateway','tfProviderGateway')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpDettaglioGateway_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfServiceJNDI')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfServiceJNDI.label')}" labelFor="widg_tfServiceJNDI" errorFor="appDatagateway.serviceJNDIName" labelId="tfServiceJNDILbl"
	position="first"  >


<!-- widget tfServiceJNDI -->
<s:textfield name="appDatagateway.serviceJNDIName" id="widg_tfServiceJNDI"
	disabled="isWidgetDisabled('cpDettaglioGateway','tfServiceJNDI')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpDettaglioGateway_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioGateway --></div>

			
	
	<div id="p_wpTabellaAttr" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaAttr -->
	
	
<h4 class="wpLabel">Lista extra attribute </h4>
<div class="widgetsPanel" id="wpTabellaAttr">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','tbListaAttributi')" >

	
<div class="tableWidget">


<!-- widget tbListaAttributi -->
	<csiuicore:ajaxify id="p_wpTabellaAttr" 
		widgetType="table" 
		pageId="cpDettaglioGateway">
<s:set name="cpDettaglioGateway_tbListaAttributi_clearStatus" value="isTableClearStatus('cpDettaglioGateway_tbListaAttributi')" />
<display:table name="appDataextraAttributes"
               excludedParams="*"
               export="false"
               id="widg_tbListaAttributi"
               pagesize="0"
               requestURI="cpDettaglioGateway.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGateway_tbListaAttributi_clearStatus}"
               uid="row_tbListaAttributi"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAttributi.chiave}" name="appDataselettoreChiaveAttr" id="%{'tbListaAttributi-editcell-'+ (#attr.row_tbListaAttributi_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="nome" titleKey="cpDettaglioGateway.tbListaAttributi.nome.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descrizione" titleKey="cpDettaglioGateway.tbListaAttributi.descrizione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaAttr --></div>

			
	
	<div id="p_wpAzioniAttr" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniAttr -->
	
	

<div class="widgetsPanel" id="wpAzioniAttr">
	
	<customtag:widgetsPanel id="wpAzioniAttrTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','btnAggiungiAttributo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnAggiungiAttributo -->
<s:submit name="widg_btnAggiungiAttributo" id="widg_btnAggiungiAttributo" method="handleBtnAggiungiAttributo_CLICKED"
	key="cpDettaglioGateway.btnAggiungiAttributo.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioGateway','btnAggiungiAttributo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniAttr --></div>

			
	
	<div id="p_wpTabellaLingue" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaLingue -->
	
	
<h4 class="wpLabel">Lista lingue </h4>
<div class="widgetsPanel" id="wpTabellaLingue">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','tLingue')" >

	
<div class="tableWidget">


<!-- widget tLingue -->
	<csiuicore:ajaxify id="p_wpTabellaLingue" 
		widgetType="table" 
		pageId="cpDettaglioGateway">
<s:set name="cpDettaglioGateway_tLingue_clearStatus" value="isTableClearStatus('cpDettaglioGateway_tLingue')" />
<display:table name="appDatalingue"
               excludedParams="*"
               export="false"
               id="widg_tLingue"
               pagesize="0"
               requestURI="cpDettaglioGateway.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGateway_tLingue_clearStatus}"
               uid="row_tLingue"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tLingue.chiave}" name="appDataselettoreIdLingua" id="%{'tLingue-editcell-'+ (#attr.row_tLingue_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="idLinguaMdp" titleKey="cpDettaglioGateway.tLingue.idLinguaMdp.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="idLinguaGateway" titleKey="cpDettaglioGateway.tLingue.idLinguaGateway.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descLingua" titleKey="cpDettaglioGateway.tLingue.descLingua.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaLingue --></div>

			
	
	<div id="p_wpTabellaDivise" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaDivise -->
	
	
<h4 class="wpLabel">Lista divise </h4>
<div class="widgetsPanel" id="wpTabellaDivise">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','tDivise')" >

	
<div class="tableWidget">


<!-- widget tDivise -->
	<csiuicore:ajaxify id="p_wpTabellaDivise" 
		widgetType="table" 
		pageId="cpDettaglioGateway">
<s:set name="cpDettaglioGateway_tDivise_clearStatus" value="isTableClearStatus('cpDettaglioGateway_tDivise')" />
<display:table name="appDatadivise"
               excludedParams="*"
               export="false"
               id="widg_tDivise"
               pagesize="0"
               requestURI="cpDettaglioGateway.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGateway_tDivise_clearStatus}"
               uid="row_tDivise"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tDivise.chiave}" name="appDataselettoreIdDivisa" id="%{'tDivise-editcell-'+ (#attr.row_tDivise_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="idDivisaMdp" titleKey="cpDettaglioGateway.tDivise.idDivisaMdp.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="idDivisaGateway" titleKey="cpDettaglioGateway.tDivise.idDivisaGateway.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descDivisa" titleKey="cpDettaglioGateway.tDivise.descDivisa.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaDivise --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','btnIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnIndietro -->
<s:submit name="widg_btnIndietro" id="widg_btnIndietro" method="handleBtnIndietro_CLICKED"
	key="cpDettaglioGateway.btnIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioGateway','btnIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','btnSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnSalva -->
<s:submit name="widg_btnSalva" id="widg_btnSalva" method="handleBtnSalva_CLICKED"
	key="cpDettaglioGateway.btnSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioGateway','btnSalva')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioni --></div>

	

		
	
</div>

	<!-- endFragment:p_pDatiGeneraliGateway --></div>
