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

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpGestioneGW_PMAction" />

	
	<div id="p_pGateways" class="formPanelBlock"><!-- startFragment:p_pGateways -->
		
	
<div class="formPanel" id="pGateways">


	
	

	
	
			
	
	<div id="p_wpTabellaGW" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaGW -->
	
	

<div class="widgetsPanel" id="wpTabellaGW">
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tbListaGateways')" >

	
<div class="tableWidget">


<!-- widget tbListaGateways -->
	<csiuicore:ajaxify id="p_wpTabellaGW" 
		widgetType="table" 
		pageId="cpGestioneGW_PM">
<s:set name="cpGestioneGW_PM_tbListaGateways_clearStatus" value="isTableClearStatus('cpGestioneGW_PM_tbListaGateways')" />
<display:table name="appDatagateways"
               excludedParams="*"
               export="true"
               id="widg_tbListaGateways"
               pagesize="0"
               requestURI="cpGestioneGW_PM.do"
               keepStatus="true"
               clearStatus="${cpGestioneGW_PM_tbListaGateways_clearStatus}"
               uid="row_tbListaGateways"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaGateways.idGateway}" name="appDataselettoreIdGateway" id="%{'tbListaGateways-editcell-'+ (#attr.row_tbListaGateways_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="idGateway" titleKey="cpGestioneGW_PM.tbListaGateways.idGateway.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="idGateway" titleKey="cpGestioneGW_PM.tbListaGateways.idGateway.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="idGateway" titleKey="cpGestioneGW_PM.tbListaGateways.idGateway.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrGateway" titleKey="cpGestioneGW_PM.tbListaGateways.descrGateway.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrGateway" titleKey="cpGestioneGW_PM.tbListaGateways.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrGateway" titleKey="cpGestioneGW_PM.tbListaGateways.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="providerGateway" titleKey="cpGestioneGW_PM.tbListaGateways.providerGateway.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="providerGateway" titleKey="cpGestioneGW_PM.tbListaGateways.providerGateway.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="providerGateway" titleKey="cpGestioneGW_PM.tbListaGateways.providerGateway.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaGW --></div>

			
	
	<div id="p_wpAzioniGW" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniGW -->
	
	

<div class="widgetsPanel" id="wpAzioniGW">
	
	<customtag:widgetsPanel id="wpAzioniGWTbl" columns="8" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnNuovoGateway')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnNuovoGateway -->
<s:submit name="widg_btnNuovoGateway" id="widg_btnNuovoGateway" method="handleBtnNuovoGateway_CLICKED"
	key="cpGestioneGW_PM.btnNuovoGateway.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnNuovoGateway')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnClonaGateway')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnClonaGateway -->
<s:submit name="widg_btnClonaGateway" id="widg_btnClonaGateway" method="handleBtnClonaGateway_CLICKED"
	key="cpGestioneGW_PM.btnClonaGateway.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnClonaGateway')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnModificaGateway')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnModificaGateway -->
<s:submit name="widg_btnModificaGateway" id="widg_btnModificaGateway" method="handleBtnModificaGateway_CLICKED"
	key="cpGestioneGW_PM.btnModificaGateway.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnModificaGateway')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnCancellaGateway')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnCancellaGateway -->
<s:submit name="widg_btnCancellaGateway" id="widg_btnCancellaGateway" method="handleBtnCancellaGateway_CLICKED"
	key="cpGestioneGW_PM.btnCancellaGateway.label" cssClass="buttonWidget deleteItem"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnCancellaGateway')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniGW --></div>

	

		
	
</div>

	<!-- endFragment:p_pGateways --></div>
