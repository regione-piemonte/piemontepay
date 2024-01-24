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

	
	<div id="p_pGatewayPaymentMode" class="formPanelBlock"><!-- startFragment:p_pGatewayPaymentMode -->
		
	
<div class="formPanel" id="pGatewayPaymentMode">


	
	

	
	
			
	
	<div id="p_wpTabellaGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaGWMP -->
	
	
<h4 class="wpLabel">Lista associazioni sistema di pagamento </h4>
<div class="widgetsPanel" id="wpTabellaGWMP">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','tbListaAssociazioni')" >

	
<div class="tableWidget">


<!-- widget tbListaAssociazioni -->
	<csiuicore:ajaxify id="p_wpTabellaGWMP" 
		widgetType="table" 
		pageId="cpDettaglioGateway">
<s:set name="cpDettaglioGateway_tbListaAssociazioni_clearStatus" value="isTableClearStatus('cpDettaglioGateway_tbListaAssociazioni')" />
<display:table name="appDataassociazioniGW_MP"
               excludedParams="*"
               export="false"
               id="widg_tbListaAssociazioni"
               pagesize="20"
               requestURI="cpDettaglioGateway.do"
               keepStatus="true"
               clearStatus="${cpDettaglioGateway_tbListaAssociazioni_clearStatus}"
               uid="row_tbListaAssociazioni"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAssociazioni.chiave}" name="appDataselettoreItemAssociazioneGW_MP" id="%{'tbListaAssociazioni-editcell-'+ (#attr.row_tbListaAssociazioni_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="descrGateway" titleKey="cpDettaglioGateway.tbListaAssociazioni.descrGateway.label" 
			sortable="true" headerClass="sortable"
			    media="html" />
		<display:column property="descrPayment" titleKey="cpDettaglioGateway.tbListaAssociazioni.descrPayment.label" 
			sortable="true" headerClass="sortable"
			    media="html" />
		<display:column property="abilitazione" titleKey="cpDettaglioGateway.tbListaAssociazioni.abilitazione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','btnCaricaAssociazioneSelezionataGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnCaricaAssociazioneSelezionataGW_MP -->
<s:submit name="widg_btnCaricaAssociazioneSelezionataGW_MP" id="widg_btnCaricaAssociazioneSelezionataGW_MP" method="handleBtnCaricaAssociazioneSelezionataGW_MP_CLICKED"
	key="cpDettaglioGateway.btnCaricaAssociazioneSelezionataGW_MP.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioGateway','btnCaricaAssociazioneSelezionataGW_MP')" />

	
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfIdGatewayAss')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfIdGatewayAss.label')}" labelFor="widg_tfIdGatewayAss" errorFor="appDatagateway.idGateway" labelId="tfIdGatewayAssLbl"  position="first" >


<!-- widget tfIdGatewayAss -->
<s:property value="appDatagateway.idGateway" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_1_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_1_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_1_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_1_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfDescrGatewayAss')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfDescrGatewayAss.label')}" labelFor="widg_tfDescrGatewayAss" errorFor="appDatagateway.descrGateway" labelId="tfDescrGatewayAssLbl"  position="first" >


<!-- widget tfDescrGatewayAss -->
<s:property value="appDatagateway.descrGateway" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_2_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_2_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_2_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_2_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','cbPaymentModesMp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.cbPaymentModesMp.label')}" labelFor="widg_cbPaymentModesMp" errorFor="appDataassociazioneGW_MP.idPayment" labelId="cbPaymentModesMpLbl"
	position="first"  >


<!-- widget cbPaymentModesMp -->
<s:select name="appDataassociazioneGW_MP.idPayment" id="widg_cbPaymentModesMp"
          headerKey="" headerValue="" 
          list="appDatapaymentModes"
          disabled="isWidgetDisabled('cpDettaglioGateway','cbPaymentModesMp')"
          listKey="idPayment"
          listValue="descrPayment"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_3_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_3_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_3_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_3_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfDefaultPaymentUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfDefaultPaymentUrl.label')}" labelFor="widg_tfDefaultPaymentUrl" errorFor="appDataassociazioneGW_MP.defaultPaymentUrl" labelId="tfDefaultPaymentUrlLbl"
	position="first"  >


<!-- widget tfDefaultPaymentUrl -->
<s:textfield name="appDataassociazioneGW_MP.defaultPaymentUrl" id="widg_tfDefaultPaymentUrl"
maxlength="200"	disabled="isWidgetDisabled('cpDettaglioGateway','tfDefaultPaymentUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_4_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_4_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_4_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_4_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_4_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_4_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfReturnUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfReturnUrl.label')}" labelFor="widg_tfReturnUrl" errorFor="appDataassociazioneGW_MP.returnUrl" labelId="tfReturnUrlLbl"
	position="first"  >


<!-- widget tfReturnUrl -->
<s:textfield name="appDataassociazioneGW_MP.returnUrl" id="widg_tfReturnUrl"
maxlength="200"	disabled="isWidgetDisabled('cpDettaglioGateway','tfReturnUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_5_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_5_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_5_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_5_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_5_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_5_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfReceiptUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfReceiptUrl.label')}" labelFor="widg_tfReceiptUrl" errorFor="appDataassociazioneGW_MP.receiptUrl" labelId="tfReceiptUrlLbl"
	position="first"  >


<!-- widget tfReceiptUrl -->
<s:textfield name="appDataassociazioneGW_MP.receiptUrl" id="widg_tfReceiptUrl"
maxlength="200"	disabled="isWidgetDisabled('cpDettaglioGateway','tfReceiptUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_6_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_6_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_6_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_6_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_6_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_6_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_6_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_6_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfKolUrl')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfKolUrl.label')}" labelFor="widg_tfKolUrl" errorFor="appDataassociazioneGW_MP.cancelUrl" labelId="tfKolUrlLbl"
	position="first"  >


<!-- widget tfKolUrl -->
<s:textfield name="appDataassociazioneGW_MP.cancelUrl" id="widg_tfKolUrl"
maxlength="200"	disabled="isWidgetDisabled('cpDettaglioGateway','tfKolUrl')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_7_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_7_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_7_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_7_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_7_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_7_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_7_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_7_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','chkAbilitazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.chkAbilitazione.label')}" labelFor="widg_chkAbilitazione" errorFor="appDataassociazioneGW_MP.abilitazione" labelId="chkAbilitazioneLbl"
	position="first"  >


<!-- widget chkAbilitazione -->
<s:checkbox name="appDataassociazioneGW_MP.abilitazione" id="widg_chkAbilitazione"
	disabled="isWidgetDisabled('cpDettaglioGateway','chkAbilitazione')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioGateway','chkAbilitazione')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.abilitazione" id="__checkbox_widg_chkAbilitazione" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_8_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_8_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_8_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_8_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_8_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_8_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_8_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_8_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfcontextpg')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfcontextpg.label')}" labelFor="widg_tfcontextpg" errorFor="appDataassociazioneGW_MP.contextpg" labelId="tfcontextpgLbl"
	position="first"  >


<!-- widget tfcontextpg -->
<s:textfield name="appDataassociazioneGW_MP.contextpg" id="widg_tfcontextpg"
maxlength="200"	disabled="isWidgetDisabled('cpDettaglioGateway','tfcontextpg')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_9_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_9_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_9_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_9_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_9_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_9_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_9_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_9_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfhttpport')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfhttpport.label')}" labelFor="widg_tfhttpport" errorFor="appDataassociazioneGW_MP.httpport" labelId="tfhttpportLbl"
	position="first"  >


<!-- widget tfhttpport -->
<s:textfield name="appDataassociazioneGW_MP.httpport" id="widg_tfhttpport"
maxlength="200"	disabled="isWidgetDisabled('cpDettaglioGateway','tfhttpport')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_10_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_10_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_10_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_10_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_10_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_10_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_10_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_10_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','tfmdpgatewaypage')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.tfmdpgatewaypage.label')}" labelFor="widg_tfmdpgatewaypage" errorFor="appDataassociazioneGW_MP.mdpgatewaypage" labelId="tfmdpgatewaypageLbl"
	position="first"  >


<!-- widget tfmdpgatewaypage -->
<s:textfield name="appDataassociazioneGW_MP.mdpgatewaypage" id="widg_tfmdpgatewaypage"
maxlength="200"	disabled="isWidgetDisabled('cpDettaglioGateway','tfmdpgatewaypage')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_11_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_11_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_11_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_11_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_11_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_11_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_11_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_11_5 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','chkVerificaEsito')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGateway.chkVerificaEsito.label')}" labelFor="widg_chkVerificaEsito" errorFor="appDataassociazioneGW_MP.verificaesito" labelId="chkVerificaEsitoLbl"
	position="first"  >


<!-- widget chkVerificaEsito -->
<s:checkbox name="appDataassociazioneGW_MP.verificaesito" id="widg_chkVerificaEsito"
	disabled="isWidgetDisabled('cpDettaglioGateway','chkVerificaEsito')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioGateway','chkVerificaEsito')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.verificaesito" id="__checkbox_widg_chkVerificaEsito" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_12_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_12_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_12_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_12_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_12_4')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_12_4 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGateway','wpGWMP_12_5')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_12_5 -->


	
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
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGateway','btnSalvaAssociazioneGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnSalvaAssociazioneGW_MP -->
<s:submit name="widg_btnSalvaAssociazioneGW_MP" id="widg_btnSalvaAssociazioneGW_MP" method="handleBtnSalvaAssociazioneGW_MP_CLICKED"
	key="cpDettaglioGateway.btnSalvaAssociazioneGW_MP.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioGateway','btnSalvaAssociazioneGW_MP')" />

	
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
