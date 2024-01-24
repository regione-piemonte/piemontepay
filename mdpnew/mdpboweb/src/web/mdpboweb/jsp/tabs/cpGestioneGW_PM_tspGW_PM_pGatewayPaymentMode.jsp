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

	
	<div id="p_pGatewayPaymentMode" class="formPanelBlock"><!-- startFragment:p_pGatewayPaymentMode -->
		
	
<div class="formPanel" id="pGatewayPaymentMode">


	
	

	
	
			
	
	<div id="p_wpTabellaGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaGWMP -->
	
	

<div class="widgetsPanel" id="wpTabellaGWMP">
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tbListaAssociazioni')" >

	
<div class="tableWidget">


<!-- widget tbListaAssociazioni -->
	<csiuicore:ajaxify id="p_wpTabellaGWMP" 
		widgetType="table" 
		pageId="cpGestioneGW_PM">
<s:set name="cpGestioneGW_PM_tbListaAssociazioni_clearStatus" value="isTableClearStatus('cpGestioneGW_PM_tbListaAssociazioni')" />
<display:table name="appDataassociazioniGW_MP"
               excludedParams="*"
               export="true"
               id="widg_tbListaAssociazioni"
               pagesize="20"
               requestURI="cpGestioneGW_PM.do"
               keepStatus="true"
               clearStatus="${cpGestioneGW_PM_tbListaAssociazioni_clearStatus}"
               uid="row_tbListaAssociazioni"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAssociazioni.chiave}" name="appDataselettoreItemAssociazioneGW_MP" id="%{'tbListaAssociazioni-editcell-'+ (#attr.row_tbListaAssociazioni_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="descrGateway" titleKey="cpGestioneGW_PM.tbListaAssociazioni.descrGateway.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrGateway" titleKey="cpGestioneGW_PM.tbListaAssociazioni.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrGateway" titleKey="cpGestioneGW_PM.tbListaAssociazioni.descrGateway.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="descrPayment" titleKey="cpGestioneGW_PM.tbListaAssociazioni.descrPayment.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="descrPayment" titleKey="cpGestioneGW_PM.tbListaAssociazioni.descrPayment.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="descrPayment" titleKey="cpGestioneGW_PM.tbListaAssociazioni.descrPayment.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="abilitazione" titleKey="cpGestioneGW_PM.tbListaAssociazioni.abilitazione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="abilitazione" titleKey="cpGestioneGW_PM.tbListaAssociazioni.abilitazione.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="abilitazione" titleKey="cpGestioneGW_PM.tbListaAssociazioni.abilitazione.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaGWMP --></div>

			
	
	<div id="p_wpAzioniUpdGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniUpdGWMP -->
	
	

<div class="widgetsPanel" id="wpAzioniUpdGWMP">
	
	<customtag:widgetsPanel id="wpAzioniUpdGWMPTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnCaricaAssociazioneSelezionataGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnCaricaAssociazioneSelezionataGW_MP -->
<s:submit name="widg_btnCaricaAssociazioneSelezionataGW_MP" id="widg_btnCaricaAssociazioneSelezionataGW_MP" method="handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED"
	key="cpGestioneGW_PM.btnCaricaAssociazioneSelezionataGW_MP.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnCaricaAssociazioneSelezionataGW_MP')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniUpdGWMP --></div>

			
	
	<div id="p_wpGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpGWMP -->
	
	
<h4 class="wpLabel">Dati generali </h4>
<div class="widgetsPanel" id="wpGWMP">
	
	<customtag:widgetsPanel id="wpGWMPTbl" columns="10" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','cbGateways')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.cbGateways.label')}" labelFor="widg_cbGateways" errorFor="appDataassociazioneGW_MP.idGateway" labelId="cbGatewaysLbl"
	position="first"  >


<!-- widget cbGateways -->
<s:select name="appDataassociazioneGW_MP.idGateway" id="widg_cbGateways"
          headerKey="" headerValue="" 
          list="appDatagateways"
          disabled="isWidgetDisabled('cpGestioneGW_PM','cbGateways')"
          listKey="idGateway"
          listValue="descrGateway"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_1_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_1_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_1_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_1_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','cbPaymentModes')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.cbPaymentModes.label')}" labelFor="widg_cbPaymentModes" errorFor="appDataassociazioneGW_MP.idPayment" labelId="cbPaymentModesLbl"
	position="first"  >


<!-- widget cbPaymentModes -->
<s:select name="appDataassociazioneGW_MP.idPayment" id="widg_cbPaymentModes"
          headerKey="" headerValue="" 
          list="appDatapaymentModes"
          disabled="isWidgetDisabled('cpGestioneGW_PM','cbPaymentModes')"
          listKey="idPayment"
          listValue="descrPayment"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_2_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_2_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_2_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_2_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tfDefaultPaymentUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.tfDefaultPaymentUrl.label')}" labelFor="widg_tfDefaultPaymentUrl" errorFor="appDataassociazioneGW_MP.defaultPaymentUrl" labelId="tfDefaultPaymentUrlLbl"
	position="first"  >


<!-- widget tfDefaultPaymentUrl -->
<s:textfield name="appDataassociazioneGW_MP.defaultPaymentUrl" id="widg_tfDefaultPaymentUrl"
maxlength="200"	disabled="isWidgetDisabled('cpGestioneGW_PM','tfDefaultPaymentUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_3_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_3_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_3_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_3_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tfReturnUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.tfReturnUrl.label')}" labelFor="widg_tfReturnUrl" errorFor="appDataassociazioneGW_MP.returnUrl" labelId="tfReturnUrlLbl"
	position="first"  >


<!-- widget tfReturnUrl -->
<s:textfield name="appDataassociazioneGW_MP.returnUrl" id="widg_tfReturnUrl"
maxlength="200"	disabled="isWidgetDisabled('cpGestioneGW_PM','tfReturnUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_4_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_4_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_4_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_4_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_4_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_4_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tfReceiptUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.tfReceiptUrl.label')}" labelFor="widg_tfReceiptUrl" errorFor="appDataassociazioneGW_MP.receiptUrl" labelId="tfReceiptUrlLbl"
	position="first"  >


<!-- widget tfReceiptUrl -->
<s:textfield name="appDataassociazioneGW_MP.receiptUrl" id="widg_tfReceiptUrl"
maxlength="200"	disabled="isWidgetDisabled('cpGestioneGW_PM','tfReceiptUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_5_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_5_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_5_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_5_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_5_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_5_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tfKolUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.tfKolUrl.label')}" labelFor="widg_tfKolUrl" errorFor="appDataassociazioneGW_MP.cancelUrl" labelId="tfKolUrlLbl"
	position="first"  >


<!-- widget tfKolUrl -->
<s:textfield name="appDataassociazioneGW_MP.cancelUrl" id="widg_tfKolUrl"
maxlength="200"	disabled="isWidgetDisabled('cpGestioneGW_PM','tfKolUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_6_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_6_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_6_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_6_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_6_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_6_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_6_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_6_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','chkAbilitazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.chkAbilitazione.label')}" labelFor="widg_chkAbilitazione" errorFor="appDataassociazioneGW_MP.abilitazione" labelId="chkAbilitazioneLbl"
	position="first"  >


<!-- widget chkAbilitazione -->
<s:checkbox name="appDataassociazioneGW_MP.abilitazione" id="widg_chkAbilitazione"
	disabled="isWidgetDisabled('cpGestioneGW_PM','chkAbilitazione')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpGestioneGW_PM','chkAbilitazione')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.abilitazione" id="__checkbox_widg_chkAbilitazione" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_7_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_7_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_7_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_7_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_7_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_7_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_7_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_7_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tfcontextpg')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.tfcontextpg.label')}" labelFor="widg_tfcontextpg" errorFor="appDataassociazioneGW_MP.contextpg" labelId="tfcontextpgLbl"
	position="first"  >


<!-- widget tfcontextpg -->
<s:textfield name="appDataassociazioneGW_MP.contextpg" id="widg_tfcontextpg"
maxlength="200"	disabled="isWidgetDisabled('cpGestioneGW_PM','tfcontextpg')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_8_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_8_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_8_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_8_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_8_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_8_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_8_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_8_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tfhttpport')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.tfhttpport.label')}" labelFor="widg_tfhttpport" errorFor="appDataassociazioneGW_MP.httpport" labelId="tfhttpportLbl"
	position="first"  >


<!-- widget tfhttpport -->
<s:textfield name="appDataassociazioneGW_MP.httpport" id="widg_tfhttpport"
maxlength="200"	disabled="isWidgetDisabled('cpGestioneGW_PM','tfhttpport')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_9_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_9_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_9_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_9_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_9_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_9_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_9_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_9_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','tfmdpgatewaypage')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneGW_PM.tfmdpgatewaypage.label')}" labelFor="widg_tfmdpgatewaypage" errorFor="appDataassociazioneGW_MP.mdpgatewaypage" labelId="tfmdpgatewaypageLbl"
	position="first"  >


<!-- widget tfmdpgatewaypage -->
<s:textfield name="appDataassociazioneGW_MP.mdpgatewaypage" id="widg_tfmdpgatewaypage"
maxlength="200"	disabled="isWidgetDisabled('cpGestioneGW_PM','tfmdpgatewaypage')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_10_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_10_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_10_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_10_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_10_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_10_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_10_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_10_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_11_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpGWMP_11_1 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_11_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_11_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_11_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_11_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_11_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_11_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneGW_PM','wpGWMP_11_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_11_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpGWMP --></div>

			
	
	<div id="p_wpAzioniGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniGWMP -->
	
	

<div class="widgetsPanel" id="wpAzioniGWMP">
	
	<customtag:widgetsPanel id="wpAzioniGWMPTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneGW_PM','btnSalvaAssociazioneGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnSalvaAssociazioneGW_MP -->
<s:submit name="widg_btnSalvaAssociazioneGW_MP" id="widg_btnSalvaAssociazioneGW_MP" method="handleBtnSalvaAssociazioneGW_MP_CLICKED"
	key="cpGestioneGW_PM.btnSalvaAssociazioneGW_MP.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpGestioneGW_PM','btnSalvaAssociazioneGW_MP')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniGWMP --></div>

	

		
	
</div>

	<!-- endFragment:p_pGatewayPaymentMode --></div>
