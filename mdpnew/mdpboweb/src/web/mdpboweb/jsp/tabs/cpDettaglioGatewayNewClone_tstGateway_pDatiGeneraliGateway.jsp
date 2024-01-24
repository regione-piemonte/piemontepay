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

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpDettaglioGatewayNewCloneAction" />

	
	<div id="p_pDatiGeneraliGateway" class="formPanelBlock"><!-- startFragment:p_pDatiGeneraliGateway -->
		
	
<div class="formPanel" id="pDatiGeneraliGateway">


	
	

	
	
			
	
	<div id="p_wpDettaglioGateway" class="widgetsPanelBlock scroll"><!-- startFragment:p_wpDettaglioGateway -->
	
	

<div class="widgetsPanel" id="wpDettaglioGateway">
	
	<customtag:widgetsPanel id="wpDettaglioGatewayTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','cbGateways')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNewClone.cbGateways.label')}" labelFor="widg_cbGateways" errorFor="appDataselettoreIdGateway2" labelId="cbGatewaysLbl"
	position="first"  >


<!-- widget cbGateways -->
<s:select name="appDataselettoreIdGateway2" id="widg_cbGateways"
          headerKey="" headerValue="" 
          list="appDatagateways"
          disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','cbGateways')"
          listKey="idGateway"
          listValue="descrGateway"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','btGatewaySel')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btGatewaySel -->
<s:submit name="widg_btGatewaySel" id="widg_btGatewaySel" method="handleBtGatewaySel_CLICKED"
	key="cpDettaglioGatewayNewClone.btGatewaySel.label" cssClass="buttonWidget load"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','btGatewaySel')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','tfIdGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNewClone.tfIdGateway.label')}" labelFor="widg_tfIdGateway" errorFor="appDatagateway.idGateway" labelId="tfIdGatewayLbl"  position="first" >


<!-- widget tfIdGateway -->
<s:property value="appDatagateway.idGateway" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpDettaglioGateway_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','tfDescrGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNewClone.tfDescrGateway.label')}" labelFor="widg_tfDescrGateway" errorFor="appDatagateway.descrGateway" labelId="tfDescrGatewayLbl"
	position="first"  >


<!-- widget tfDescrGateway -->
<s:textfield name="appDatagateway.descrGateway" id="widg_tfDescrGateway"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','tfDescrGateway')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpDettaglioGateway_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','tfProviderGateway')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNewClone.tfProviderGateway.label')}" labelFor="widg_tfProviderGateway" errorFor="appDatagateway.providerGateway" labelId="tfProviderGatewayLbl"
	position="first"  >


<!-- widget tfProviderGateway -->
<s:textfield name="appDatagateway.providerGateway" id="widg_tfProviderGateway"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','tfProviderGateway')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpDettaglioGateway_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGateway_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','tfServiceJNDI')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNewClone.tfServiceJNDI.label')}" labelFor="widg_tfServiceJNDI" errorFor="appDatagateway.serviceJNDIName" labelId="tfServiceJNDILbl"
	position="first"  >


<!-- widget tfServiceJNDI -->
<s:textfield name="appDatagateway.serviceJNDIName" id="widg_tfServiceJNDI"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','tfServiceJNDI')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpDettaglioGateway_5_2')" >

	
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
	
	

<div class="widgetsPanel" id="wpTabellaAttr">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','tbListaAttributi')" >

	
<div class="tableWidget">


<!-- widget tbListaAttributi -->
	<csiuicore:ajaxify id="p_wpTabellaAttr" 
		widgetType="table" 
		pageId="cpDettaglioGatewayNewClone">
<s:set name="cpDettaglioGatewayNewClone_tbListaAttributi_clearStatus" value="isTableClearStatus('cpDettaglioGatewayNewClone_tbListaAttributi')" />
<display:table name="appDataextraAttributes"
               excludedParams="*"
               export="false"
               id="widg_tbListaAttributi"
               pagesize="0"
               requestURI="cpDettaglioGatewayNewClone.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGatewayNewClone_tbListaAttributi_clearStatus}"
               uid="row_tbListaAttributi"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAttributi.chiave}" name="appDataselettoreChiaveAttr" id="%{'tbListaAttributi-editcell-'+ (#attr.row_tbListaAttributi_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="nome" titleKey="cpDettaglioGatewayNewClone.tbListaAttributi.nome.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descrizione" titleKey="cpDettaglioGatewayNewClone.tbListaAttributi.descrizione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="chiave" titleKey="cpDettaglioGatewayNewClone.tbListaAttributi.chiave.label" 
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','btnEliminaAttributo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnEliminaAttributo -->
<s:submit name="widg_btnEliminaAttributo" id="widg_btnEliminaAttributo" method="handleBtnEliminaAttributo_CLICKED"
	key="cpDettaglioGatewayNewClone.btnEliminaAttributo.label" cssClass="buttonWidget deleteItem"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','btnEliminaAttributo')" />

	
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpExtraAttributes_1_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpExtraAttributes_1_1 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpExtraAttributes_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpExtraAttributes_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','txtNome')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNewClone.txtNome.label')}" labelFor="widg_txtNome" errorFor="appDatanuovoExtraAttribute.nome" labelId="txtNomeLbl"
	position="first"  >


<!-- widget txtNome -->
<s:textfield name="appDatanuovoExtraAttribute.nome" id="widg_txtNome"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','txtNome')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpExtraAttributes_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpExtraAttributes_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','txtDescrizione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGatewayNewClone.txtDescrizione.label')}" labelFor="widg_txtDescrizione" errorFor="appDatanuovoExtraAttribute.descrizione" labelId="txtDescrizioneLbl"
	position="first"  >


<!-- widget txtDescrizione -->
<s:textfield name="appDatanuovoExtraAttribute.descrizione" id="widg_txtDescrizione"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','txtDescrizione')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpExtraAttributes_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','wpExtraAttributes_3_3')" >

	
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','btnAggiungiAttributo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnAggiungiAttributo -->
<s:submit name="widg_btnAggiungiAttributo" id="widg_btnAggiungiAttributo" method="handleBtnAggiungiAttributo_CLICKED"
	key="cpDettaglioGatewayNewClone.btnAggiungiAttributo.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','btnAggiungiAttributo')" />

	
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','tLingue')" >

	
<div class="tableWidget">


<!-- widget tLingue -->
	<csiuicore:ajaxify id="p_wpTabellaLingue" 
		widgetType="table" 
		pageId="cpDettaglioGatewayNewClone">
<s:set name="cpDettaglioGatewayNewClone_tLingue_clearStatus" value="isTableClearStatus('cpDettaglioGatewayNewClone_tLingue')" />
<display:table name="appDatalingue"
               excludedParams="*"
               export="false"
               id="widg_tLingue"
               pagesize="0"
               requestURI="cpDettaglioGatewayNewClone.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGatewayNewClone_tLingue_clearStatus}"
               uid="row_tLingue"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tLingue.chiave}" name="appDataselettoreIdLingua" id="%{'tLingue-editcell-'+ (#attr.row_tLingue_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="idLinguaMdp" titleKey="cpDettaglioGatewayNewClone.tLingue.idLinguaMdp.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="idLinguaGateway" titleKey="cpDettaglioGatewayNewClone.tLingue.idLinguaGateway.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descLingua" titleKey="cpDettaglioGatewayNewClone.tLingue.descLingua.label" 
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','tDivise')" >

	
<div class="tableWidget">


<!-- widget tDivise -->
	<csiuicore:ajaxify id="p_wpTabellaDivise" 
		widgetType="table" 
		pageId="cpDettaglioGatewayNewClone">
<s:set name="cpDettaglioGatewayNewClone_tDivise_clearStatus" value="isTableClearStatus('cpDettaglioGatewayNewClone_tDivise')" />
<display:table name="appDatadivise"
               excludedParams="*"
               export="false"
               id="widg_tDivise"
               pagesize="0"
               requestURI="cpDettaglioGatewayNewClone.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGatewayNewClone_tDivise_clearStatus}"
               uid="row_tDivise"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tDivise.chiave}" name="appDataselettoreIdDivisa" id="%{'tDivise-editcell-'+ (#attr.row_tDivise_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="idDivisaMdp" titleKey="cpDettaglioGatewayNewClone.tDivise.idDivisaMdp.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="idDivisaGateway" titleKey="cpDettaglioGatewayNewClone.tDivise.idDivisaGateway.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descDivisa" titleKey="cpDettaglioGatewayNewClone.tDivise.descDivisa.label" 
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','btnIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnIndietro -->
<s:submit name="widg_btnIndietro" id="widg_btnIndietro" method="handleBtnIndietro_CLICKED"
	key="cpDettaglioGatewayNewClone.btnIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','btnIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGatewayNewClone','btnSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnSalva -->
<s:submit name="widg_btnSalva" id="widg_btnSalva" method="handleBtnSalva_CLICKED"
	key="cpDettaglioGatewayNewClone.btnSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioGatewayNewClone','btnSalva')" />

	
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
