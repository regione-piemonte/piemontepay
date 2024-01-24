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

	
	<div id="p_pModalitaPagamento" class="formPanelBlock"><!-- startFragment:p_pModalitaPagamento -->
		
	
<div class="formPanel" id="pModalitaPagamento">


	
	

	
	
			
	
	<div id="p_wpTabellaPM" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaPM -->
	
	

<div class="widgetsPanel" id="wpTabellaPM">
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tbListaPaymentModes')" >

	
<div class="tableWidget">


<!-- widget tbListaPaymentModes -->
	<csiuicore:ajaxify id="p_wpTabellaPM" 
		widgetType="table" 
		pageId="cpGestioneGW_PM">
<s:set name="cpGestioneGW_PM_tbListaPaymentModes_clearStatus" value="isTableClearStatus('cpGestioneGW_PM_tbListaPaymentModes')" />
<display:table name="appDatapaymentModes"
               excludedParams="*"
               export="true"
               id="widg_tbListaPaymentModes"
               pagesize="0"
               requestURI="cpGestioneGW_PM.do"
               keepStatus="true"
               clearStatus="${cpGestioneGW_PM_tbListaPaymentModes_clearStatus}"
               uid="row_tbListaPaymentModes"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaPaymentModes.idPayment}" name="appDataselettoreIdPaymentMode" id="%{'tbListaPaymentModes-editcell-'+ (#attr.row_tbListaPaymentModes_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="idPayment" titleKey="cpGestioneGW_PM.tbListaPaymentModes.idPayment.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="idPayment" titleKey="cpGestioneGW_PM.tbListaPaymentModes.idPayment.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="idPayment" titleKey="cpGestioneGW_PM.tbListaPaymentModes.idPayment.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="descrPayment" titleKey="cpGestioneGW_PM.tbListaPaymentModes.descrPayment.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="descrPayment" titleKey="cpGestioneGW_PM.tbListaPaymentModes.descrPayment.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="descrPayment" titleKey="cpGestioneGW_PM.tbListaPaymentModes.descrPayment.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaPM --></div>

			
	
	<div id="p_wpAzioniPM" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniPM -->
	
	

<div class="widgetsPanel" id="wpAzioniPM">
	
	<customtag:widgetsPanel id="wpAzioniPMTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnNuovoPaymentMode')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnNuovoPaymentMode -->
<s:submit name="widg_btnNuovoPaymentMode" id="widg_btnNuovoPaymentMode" method="handleBtnNuovoPaymentMode_CLICKED"
	key="cpGestioneGW_PM.btnNuovoPaymentMode.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnNuovoPaymentMode')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnModificaPaymentMode')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnModificaPaymentMode -->
<s:submit name="widg_btnModificaPaymentMode" id="widg_btnModificaPaymentMode" method="handleBtnModificaPaymentMode_CLICKED"
	key="cpGestioneGW_PM.btnModificaPaymentMode.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnModificaPaymentMode')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniPM --></div>

	

		
	
</div>

	<!-- endFragment:p_pModalitaPagamento --></div>
