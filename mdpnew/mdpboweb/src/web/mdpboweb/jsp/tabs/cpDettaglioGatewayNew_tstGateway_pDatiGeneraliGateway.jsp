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

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpDettaglioGatewayNewAction" />

	
	<div id="p_pDatiGeneraliGateway" class="formPanelBlock"><!-- startFragment:p_pDatiGeneraliGateway -->
		
	
<div class="formPanel" id="pDatiGeneraliGateway">


	
	

	
	
			
	
	<div id="p_wpDettaglioGateway" class="widgetsPanelBlock scroll"><!-- startFragment:p_wpDettaglioGateway -->
	
	

<div class="widgetsPanel" id="wpDettaglioGateway">
	
	<customtag:widgetsPanel id="wpDettaglioGatewayTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','tfIdGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNew.tfIdGateway.label')}" labelFor="widg_tfIdGateway" errorFor="appDatagateway.idGateway" labelId="tfIdGatewayLbl"  position="first" >


<!-- widget tfIdGateway -->
<s:property value="appDatagateway.idGateway" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpDettaglioGateway_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','tfDescrGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNew.tfDescrGateway.label')}" labelFor="widg_tfDescrGateway" errorFor="appDatagateway.descrGateway" labelId="tfDescrGatewayLbl"
	position="first"  >


<!-- widget tfDescrGateway -->
<s:textfield name="appDatagateway.descrGateway" id="widg_tfDescrGateway"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','tfDescrGateway')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpDettaglioGateway_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','tfProviderGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNew.tfProviderGateway.label')}" labelFor="widg_tfProviderGateway" errorFor="appDatagateway.providerGateway" labelId="tfProviderGatewayLbl"
	position="first"  >


<!-- widget tfProviderGateway -->
<s:textfield name="appDatagateway.providerGateway" id="widg_tfProviderGateway"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','tfProviderGateway')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpDettaglioGateway_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','tfServiceJNDI')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNew.tfServiceJNDI.label')}" labelFor="widg_tfServiceJNDI" errorFor="appDatagateway.serviceJNDIName" labelId="tfServiceJNDILbl"
	position="first"  >


<!-- widget tfServiceJNDI -->
<s:textfield name="appDatagateway.serviceJNDIName" id="widg_tfServiceJNDI"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','tfServiceJNDI')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpDettaglioGateway_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioGateway --></div>

			
	
	<div id="p_wpTabellaAttr" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaAttr -->
	
	

<div class="widgetsPanel" id="wpTabellaAttr">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','tbListaAttributi')" >

	
<div class="tableWidget">


<!-- widget tbListaAttributi -->
	<csiuicore:ajaxify id="p_wpTabellaAttr" 
		widgetType="table" 
		pageId="cpDettaglioGatewayNew">
<s:set name="cpDettaglioGatewayNew_tbListaAttributi_clearStatus" value="isTableClearStatus('cpDettaglioGatewayNew_tbListaAttributi')" />
<display:table name="appDataextraAttributes"
               excludedParams="*"
               export="false"
               id="widg_tbListaAttributi"
               pagesize="0"
               requestURI="cpDettaglioGatewayNew.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGatewayNew_tbListaAttributi_clearStatus}"
               uid="row_tbListaAttributi"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAttributi.chiave}" name="appDataselettoreChiaveAttr" id="%{'tbListaAttributi-editcell-'+ (#attr.row_tbListaAttributi_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="nome" titleKey="cpDettaglioGatewayNew.tbListaAttributi.nome.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descrizione" titleKey="cpDettaglioGatewayNew.tbListaAttributi.descrizione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaAttr --></div>

			
	
	<div id="p_wpAzioniEa" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniEa -->
	
	

<div class="widgetsPanel" id="wpAzioniEa">
	
	<customtag:widgetsPanel id="wpAzioniEaTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','btnEliminaAttributo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnEliminaAttributo -->
<s:submit name="widg_btnEliminaAttributo" id="widg_btnEliminaAttributo" method="handleBtnEliminaAttributo_CLICKED"
	key="cpDettaglioGatewayNew.btnEliminaAttributo.label" cssClass="buttonWidget deleteItem"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','btnEliminaAttributo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniEa --></div>

			
	
	<div id="p_wpExtraAttributes" class="widgetsPanelBlock"><!-- startFragment:p_wpExtraAttributes -->
	
	
<h4 class="wpLabel">Nuovo extra attribute </h4>
<div class="widgetsPanel" id="wpExtraAttributes">
	
	<customtag:widgetsPanel id="wpExtraAttributesTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpExtraAttributes_1_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpExtraAttributes_1_1 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpExtraAttributes_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpExtraAttributes_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','txtNome')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNew.txtNome.label')}" labelFor="widg_txtNome" errorFor="appDatanuovoExtraAttribute.nome" labelId="txtNomeLbl"
	position="first"  >


<!-- widget txtNome -->
<s:textfield name="appDatanuovoExtraAttribute.nome" id="widg_txtNome"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','txtNome')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpExtraAttributes_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpExtraAttributes_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','txtDescrizione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNew.txtDescrizione.label')}" labelFor="widg_txtDescrizione" errorFor="appDatanuovoExtraAttribute.descrizione" labelId="txtDescrizioneLbl"
	position="first"  >


<!-- widget txtDescrizione -->
<s:textfield name="appDatanuovoExtraAttribute.descrizione" id="widg_txtDescrizione"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','txtDescrizione')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpExtraAttributes_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','wpExtraAttributes_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpExtraAttributes --></div>

			
	
	<div id="p_wpAzioniAttr" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniAttr -->
	
	

<div class="widgetsPanel" id="wpAzioniAttr">
	
	<customtag:widgetsPanel id="wpAzioniAttrTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','btnAggiungiAttributo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnAggiungiAttributo -->
<s:submit name="widg_btnAggiungiAttributo" id="widg_btnAggiungiAttributo" method="handleBtnAggiungiAttributo_CLICKED"
	key="cpDettaglioGatewayNew.btnAggiungiAttributo.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','btnAggiungiAttributo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniAttr --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','btnIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnIndietro -->
<s:submit name="widg_btnIndietro" id="widg_btnIndietro" method="handleBtnIndietro_CLICKED"
	key="cpDettaglioGatewayNew.btnIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','btnIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNew','btnSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnSalva -->
<s:submit name="widg_btnSalva" id="widg_btnSalva" method="handleBtnSalva_CLICKED"
	key="cpDettaglioGatewayNew.btnSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioGatewayNew','btnSalva')" />

	
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
