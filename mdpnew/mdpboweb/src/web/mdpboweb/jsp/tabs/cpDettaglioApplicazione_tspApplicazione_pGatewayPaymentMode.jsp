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

<fmt:setBundle basename="/it/csi/mdp/mdpboweb/presentation/mdpboweb/action/mdpbackoffice/CpDettaglioApplicazioneAction" />

	
	<div id="p_pGatewayPaymentMode" class="formPanelBlock"><!-- startFragment:p_pGatewayPaymentMode -->
		
	
<div class="formPanel" id="pGatewayPaymentMode">


	
	

	
	
			
	
	<div id="p_wpTabellaGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaGWMP -->
	
	

<div class="widgetsPanel" id="wpTabellaGWMP">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tbListaAssociazioni')" >

	
<div class="tableWidget">


<!-- widget tbListaAssociazioni -->
	<csiuicore:ajaxify id="p_wpTabellaGWMP" 
		widgetType="table" 
		pageId="cpDettaglioApplicazione">
<s:set name="cpDettaglioApplicazione_tbListaAssociazioni_clearStatus" value="isTableClearStatus('cpDettaglioApplicazione_tbListaAssociazioni')" />
<display:table name="appDataassociazioniGW_MP"
               excludedParams="*"
               export="false"
               id="widg_tbListaAssociazioni"
               pagesize="0"
               requestURI="cpDettaglioApplicazione.do"
               keepStatus="true"
               clearStatus="${cpDettaglioApplicazione_tbListaAssociazioni_clearStatus}"
               uid="row_tbListaAssociazioni"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAssociazioni.chiave}" name="appDataselettoreItemAssociazioneGW_MP" id="%{'tbListaAssociazioni-editcell-'+ (#attr.row_tbListaAssociazioni_rowNum - 1)}" cssClass="radio" />
		</display:column>
		<display:column property="descrGateway" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrGateway.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="descrPayment" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrPayment.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
		<display:column property="abilitazione" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.abilitazione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaGWMP --></div>

			
	
	<div id="p_wpAzioniAssociazioniGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniAssociazioniGWMP -->
	
	

<div class="widgetsPanel" id="wpAzioniAssociazioniGWMP">
	
	<customtag:widgetsPanel id="wpAzioniAssociazioniGWMPTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnUpdAssGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnUpdAssGW_MP -->
<s:submit name="widg_btnUpdAssGW_MP" id="widg_btnUpdAssGW_MP" method="handleBtnUpdAssGW_MP_CLICKED"
	key="cpDettaglioApplicazione.btnUpdAssGW_MP.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnUpdAssGW_MP')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniAssociazioniGWMP --></div>

			
	
	<div id="p_wpGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpGWMP -->
	
	
<h4 class="wpLabel">Associazione gateway e strumenti di pagamento </h4>
<div class="widgetsPanel" id="wpGWMP">
	
	<customtag:widgetsPanel id="wpGWMPTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txIdApplicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txIdApplicazione.label')}" labelFor="widg_txIdApplicazione" errorFor="appDataapplicazione.idApplicazione" labelId="txIdApplicazioneLbl"  position="first" >


<!-- widget txIdApplicazione -->
<s:property value="appDataapplicazione.idApplicazione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cbGateways')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cbGateways.label')}" labelFor="widg_cbGateways" errorFor="appDataselettoreIdGateway" labelId="cbGatewaysLbl"
	position="first"  >


<!-- widget cbGateways -->
<s:select name="appDataselettoreIdGateway" id="widg_cbGateways"
           
          list="appDatagateways"
          disabled="isWidgetDisabled('cpDettaglioApplicazione','cbGateways')"
          listKey="idGateway"
          listValue="descrGateway"
          onclick="onCBClick(this,'confermacbGateways','conferma','cpDettaglioApplicazione!handleCbGateways_VALUE_CHANGED.do')" onfocus="inFocus(this)" onblur="lostFocus(this,'confermacbGateways','conferma','cpDettaglioApplicazione!handleCbGateways_VALUE_CHANGED.do')" 
          />
<noscript><s:submit value="conferma" name="confermacbGateways" id="confermacbGateways" cssClass="" method="handleCbGateways_VALUE_CHANGED" /></noscript>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cbPaymentModes')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cbPaymentModes.label')}" labelFor="widg_cbPaymentModes" errorFor="appDataselettoreIdPaymentMode" labelId="cbPaymentModesLbl"
	position="first"  >


<!-- widget cbPaymentModes -->
<s:select name="appDataselettoreIdPaymentMode" id="widg_cbPaymentModes"
          headerKey="" headerValue="" 
          list="appDatapaymentModes"
          disabled="isWidgetDisabled('cpDettaglioApplicazione','cbPaymentModes')"
          listKey="idPayment"
          listValue="descrPayment"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txCodIstat')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txCodIstat.label')}" labelFor="widg_txCodIstat" errorFor="appDataassociazioneGW_MP.codIstat" labelId="txCodIstatLbl"
	position="first"  >


<!-- widget txCodIstat -->
<s:textfield name="appDataassociazioneGW_MP.codIstat" id="widg_txCodIstat"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','txCodIstat')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_4_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_4_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','chkAbilitato')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.chkAbilitato.label')}" labelFor="widg_chkAbilitato" errorFor="appDataassociazioneGW_MP.abilitazione" labelId="chkAbilitatoLbl"
	position="first"  >


<!-- widget chkAbilitato -->
<s:checkbox name="appDataassociazioneGW_MP.abilitazione" id="widg_chkAbilitato"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','chkAbilitato')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioApplicazione','chkAbilitato')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.abilitazione" id="__checkbox_widg_chkAbilitato" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP_5_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP_5_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpGWMP --></div>

			
	
	<div id="p_wpGWMP1" class="widgetsPanelBlock"><!-- startFragment:p_wpGWMP1 -->
	
	
<h4 class="wpLabel">Utenza gateway applicazione </h4>
<div class="widgetsPanel" id="wpGWMP1">
	
	<customtag:widgetsPanel id="wpGWMP1Tbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfIdEsercente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfIdEsercente.label')}" labelFor="widg_tfIdEsercente" errorFor="appDataassociazioneGW_MP.idEsercente" labelId="tfIdEsercenteLbl"
	position="first"  >


<!-- widget tfIdEsercente -->
<s:textfield name="appDataassociazioneGW_MP.idEsercente" id="widg_tfIdEsercente"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfIdEsercente')"
	size="25" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP1_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP1_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP1_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP1_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfPasswordEsercente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfPasswordEsercente.label')}" labelFor="widg_tfPasswordEsercente" errorFor="appDataassociazioneGW_MP.passwordEsercente" labelId="tfPasswordEsercenteLbl"
	position="first"  >


<!-- widget tfPasswordEsercente -->
<s:textfield name="appDataassociazioneGW_MP.passwordEsercente" id="widg_tfPasswordEsercente"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfPasswordEsercente')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP1_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP1_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP1_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP1_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfMacAvvio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfMacAvvio.label')}" labelFor="widg_tfMacAvvio" errorFor="appDataassociazioneGW_MP.MACAvvio" labelId="tfMacAvvioLbl"
	position="first"  >


<!-- widget tfMacAvvio -->
<s:textfield name="appDataassociazioneGW_MP.MACAvvio" id="widg_tfMacAvvio"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfMacAvvio')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP1_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP1_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP1_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP1_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpGWMP1 --></div>

			
	
	<div id="p_wpGWMP2" class="widgetsPanelBlock"><!-- startFragment:p_wpGWMP2 -->
	
	
<h4 class="wpLabel">Commissioni applicazione </h4>
<div class="widgetsPanel" id="wpGWMP2">
	
	<customtag:widgetsPanel id="wpGWMP2Tbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cbtipologiaComm')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cbtipologiaComm.label')}" labelFor="widg_cbtipologiaComm" errorFor="appDataselettoreIdTipologiaCommissione" labelId="cbtipologiaCommLbl"
	position="first"  >


<!-- widget cbtipologiaComm -->
<s:select name="appDataselettoreIdTipologiaCommissione" id="widg_cbtipologiaComm"
          headerKey="" headerValue="" 
          list="appDatatipologiaCommissioni"
          disabled="isWidgetDisabled('cpDettaglioApplicazione','cbtipologiaComm')"
          listKey="commissionId"
          listValue="commissionDescription"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP2_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP2_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','impMin')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.impMin.label')}" labelFor="widg_impMin" errorFor="appDataassociazioneGW_MP.impMin" labelId="impMinLbl"
	position="first"  >


<!-- widget impMin -->
<s:textfield name="appDataassociazioneGW_MP.impMin" id="widg_impMin"
maxlength="10"	disabled="isWidgetDisabled('cpDettaglioApplicazione','impMin')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP2_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP2_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','impMax')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.impMax.label')}" labelFor="widg_impMax" errorFor="appDataassociazioneGW_MP.impMax" labelId="impMaxLbl"
	position="first"  >


<!-- widget impMax -->
<s:textfield name="appDataassociazioneGW_MP.impMax" id="widg_impMax"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','impMax')"
	size="15" cssClass="numbers"
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP2_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP2_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfSoglia')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfSoglia.label')}" labelFor="widg_tfSoglia" errorFor="appDataassociazioneGW_MP.sogliaa" labelId="tfSogliaLbl"
	position="first"  >


<!-- widget tfSoglia -->
<s:textfield name="appDataassociazioneGW_MP.sogliaa" id="widg_tfSoglia"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfSoglia')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP2_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_4_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP2_4_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpGWMP2 --></div>

			
	
	<div id="p_wpGWMP3" class="widgetsPanelBlock"><!-- startFragment:p_wpGWMP3 -->
	
	
<h4 class="wpLabel">Integazione con l'applicazione </h4>
<div class="widgetsPanel" id="wpGWMP3">
	
	<customtag:widgetsPanel id="wpGWMP3Tbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfApplicationurlresponseko')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfApplicationurlresponseko.label')}" labelFor="widg_tfApplicationurlresponseko" errorFor="appDataassociazioneGW_MP.applicationurlresponseko" labelId="tfApplicationurlresponsekoLbl"
	position="first"  >


<!-- widget tfApplicationurlresponseko -->
<s:textfield name="appDataassociazioneGW_MP.applicationurlresponseko" id="widg_tfApplicationurlresponseko"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfApplicationurlresponseko')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP3_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP3_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP3_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP3_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfApplicationurlresponseok')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfApplicationurlresponseok.label')}" labelFor="widg_tfApplicationurlresponseok" errorFor="appDataassociazioneGW_MP.applicationurlresponseok" labelId="tfApplicationurlresponseokLbl"
	position="first"  >


<!-- widget tfApplicationurlresponseok -->
<s:textfield name="appDataassociazioneGW_MP.applicationurlresponseok" id="widg_tfApplicationurlresponseok"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfApplicationurlresponseok')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP3_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP3_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP3_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP3_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfApplicationurlback')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfApplicationurlback.label')}" labelFor="widg_tfApplicationurlback" errorFor="appDataassociazioneGW_MP.applicationurlback" labelId="tfApplicationurlbackLbl"
	position="first"  >


<!-- widget tfApplicationurlback -->
<s:textfield name="appDataassociazioneGW_MP.applicationurlback" id="widg_tfApplicationurlback"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfApplicationurlback')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP3_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP3_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP3_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP3_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpGWMP3 --></div>

			
	
	<div id="p_wpFlagMail" class="widgetsPanelBlock"><!-- startFragment:p_wpFlagMail -->
	
	

<div class="widgetsPanel" id="wpFlagMail">
	
	<customtag:widgetsPanel id="wpFlagMailTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','chkMailacquirenteok')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.chkMailacquirenteok.label')}" labelFor="widg_chkMailacquirenteok" errorFor="appDataassociazioneGW_MP.mail2Buyerok" labelId="chkMailacquirenteokLbl"
	position="first"  >


<!-- widget chkMailacquirenteok -->
<s:checkbox name="appDataassociazioneGW_MP.mail2Buyerok" id="widg_chkMailacquirenteok"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','chkMailacquirenteok')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioApplicazione','chkMailacquirenteok')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.mail2Buyerok" id="__checkbox_widg_chkMailacquirenteok" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','chkMailacquirenteko')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.chkMailacquirenteko.label')}" labelFor="widg_chkMailacquirenteko" errorFor="appDataassociazioneGW_MP.mail2Buyerko" labelId="chkMailacquirentekoLbl"
	  >


<!-- widget chkMailacquirenteko -->
<s:checkbox name="appDataassociazioneGW_MP.mail2Buyerko" id="widg_chkMailacquirenteko"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','chkMailacquirenteko')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioApplicazione','chkMailacquirenteko')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.mail2Buyerko" id="__checkbox_widg_chkMailacquirenteko" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpFlagMail_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpFlagMail_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','chkMailesercenteok')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.chkMailesercenteok.label')}" labelFor="widg_chkMailesercenteok" errorFor="appDataassociazioneGW_MP.mail2Esercok" labelId="chkMailesercenteokLbl"
	position="first"  >


<!-- widget chkMailesercenteok -->
<s:checkbox name="appDataassociazioneGW_MP.mail2Esercok" id="widg_chkMailesercenteok"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','chkMailesercenteok')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioApplicazione','chkMailesercenteok')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.mail2Esercok" id="__checkbox_widg_chkMailesercenteok" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','chkMailesercenteko')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.chkMailesercenteko.label')}" labelFor="widg_chkMailesercenteko" errorFor="appDataassociazioneGW_MP.mail2Esercko" labelId="chkMailesercentekoLbl"
	  >


<!-- widget chkMailesercenteko -->
<s:checkbox name="appDataassociazioneGW_MP.mail2Esercko" id="widg_chkMailesercenteko"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','chkMailesercenteko')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioApplicazione','chkMailesercenteko')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.mail2Esercko" id="__checkbox_widg_chkMailesercenteko" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpFlagMail_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpFlagMail_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','chkMailsistemaok')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.chkMailsistemaok.label')}" labelFor="widg_chkMailsistemaok" errorFor="appDataassociazioneGW_MP.mail2Sysok" labelId="chkMailsistemaokLbl"
	position="first"  >


<!-- widget chkMailsistemaok -->
<s:checkbox name="appDataassociazioneGW_MP.mail2Sysok" id="widg_chkMailsistemaok"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','chkMailsistemaok')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioApplicazione','chkMailsistemaok')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.mail2Sysok" id="__checkbox_widg_chkMailsistemaok" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','chkMailsistemako')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.chkMailsistemako.label')}" labelFor="widg_chkMailsistemako" errorFor="appDataassociazioneGW_MP.mail2Sysko" labelId="chkMailsistemakoLbl"
	  >


<!-- widget chkMailsistemako -->
<s:checkbox name="appDataassociazioneGW_MP.mail2Sysko" id="widg_chkMailsistemako"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','chkMailsistemako')"
	cssClass="checkbox"
	/>
<s:if test="!isWidgetDisabled('cpDettaglioApplicazione','chkMailsistemako')" >
	<s:hidden name="__checkbox_appDataassociazioneGW_MP.mail2Sysko" id="__checkbox_widg_chkMailsistemako" />
</s:if>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpFlagMail_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpFlagMail_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFlagMail --></div>

			
	
	<div id="p_wpFiles" class="widgetsPanelBlock"><!-- startFragment:p_wpFiles -->
	
	
<h4 class="wpLabel">Allegato  </h4>
<div class="widgetsPanel" id="wpFiles">
	
	<customtag:widgetsPanel id="wpFilesTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txFile')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txFile.label')}" labelFor="widg_txFile" errorFor="widg_txFile" labelId="txFileLbl"
	position="first"  >


<s:file name="widg_txFile" id="widg_txFile"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','txFile')"
	
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpFiles_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpFiles_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFiles --></div>

			
	
	<div id="p_wpFilesAction" class="widgetsPanelBlock"><!-- startFragment:p_wpFilesAction -->
	
	

<div class="widgetsPanel" id="wpFilesAction">
	
	<customtag:widgetsPanel id="wpFilesActionTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnCaricaAllegato')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnCaricaAllegato -->
<s:submit name="widg_btnCaricaAllegato" id="widg_btnCaricaAllegato" method="handleBtnCaricaAllegato_CLICKED"
	key="cpDettaglioApplicazione.btnCaricaAllegato.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnCaricaAllegato')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFilesAction --></div>

			
	
	<div id="p_wpTabellaAttr" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaAttr -->
	
	
<h4 class="wpLabel">Extra attribute </h4>
<div class="widgetsPanel" id="wpTabellaAttr">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tbListaAttributi')" >

	
<div class="tableWidget">


<!-- widget tbListaAttributi -->
	<csiuicore:ajaxify id="p_wpTabellaAttr" 
		widgetType="table" 
		pageId="cpDettaglioApplicazione">
<s:set name="cpDettaglioApplicazione_tbListaAttributi_clearStatus" value="isTableClearStatus('cpDettaglioApplicazione_tbListaAttributi')" />
<display:table name="appDataextraAttributes"
               excludedParams="*"
               export="true"
               id="widg_tbListaAttributi"
               pagesize="0"
               requestURI="cpDettaglioApplicazione.do"
               keepStatus="true"
               clearStatus="${cpDettaglioApplicazione_tbListaAttributi_clearStatus}"
               uid="row_tbListaAttributi"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAttributi.chiave}" name="appDataselettoreChiaveAttr" id="%{'tbListaAttributi-editcell-'+ (#attr.row_tbListaAttributi_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="nome" titleKey="cpDettaglioApplicazione.tbListaAttributi.nome.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="nome" titleKey="cpDettaglioApplicazione.tbListaAttributi.nome.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="nome" titleKey="cpDettaglioApplicazione.tbListaAttributi.nome.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="valore" titleKey="cpDettaglioApplicazione.tbListaAttributi.valore.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="valore" titleKey="cpDettaglioApplicazione.tbListaAttributi.valore.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="valore" titleKey="cpDettaglioApplicazione.tbListaAttributi.valore.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="descrizione" titleKey="cpDettaglioApplicazione.tbListaAttributi.descrizione.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="descrizione" titleKey="cpDettaglioApplicazione.tbListaAttributi.descrizione.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="descrizione" titleKey="cpDettaglioApplicazione.tbListaAttributi.descrizione.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="chiave" titleKey="cpDettaglioApplicazione.tbListaAttributi.chiave.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="chiave" titleKey="cpDettaglioApplicazione.tbListaAttributi.chiave.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="chiave" titleKey="cpDettaglioApplicazione.tbListaAttributi.chiave.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaAttr --></div>

			
	
	<div id="p_wpExtraAttributes" class="widgetsPanelBlock"><!-- startFragment:p_wpExtraAttributes -->
	
	

<div class="widgetsPanel" id="wpExtraAttributes">
	
	<customtag:widgetsPanel id="wpExtraAttributesTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txtChiave')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txtChiave.label')}" labelFor="widg_txtChiave" errorFor="appDatanuovoExtraAttribute.chiave" labelId="txtChiaveLbl"  position="first" >


<!-- widget txtChiave -->
<s:property value="appDatanuovoExtraAttribute.chiave" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txtNome')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txtNome.label')}" labelFor="widg_txtNome" errorFor="appDatanuovoExtraAttribute.nome" labelId="txtNomeLbl"  position="first" >


<!-- widget txtNome -->
<s:property value="appDatanuovoExtraAttribute.nome" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tfValore')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.tfValore.label')}" labelFor="widg_tfValore" errorFor="appDatanuovoExtraAttribute.valore" labelId="tfValoreLbl"
	position="first"  >


<!-- widget tfValore -->
<s:textfield name="appDatanuovoExtraAttribute.valore" id="widg_tfValore"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','tfValore')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txtDescrizione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txtDescrizione.label')}" labelFor="widg_txtDescrizione" errorFor="appDatanuovoExtraAttribute.descrizione" labelId="txtDescrizioneLbl"  position="first" >


<!-- widget txtDescrizione -->
<s:property value="appDatanuovoExtraAttribute.descrizione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpExtraAttributes_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpExtraAttributes_4_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpExtraAttributes_4_3 -->


	
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
	
	<customtag:widgetsPanel id="wpAzioniAttrTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnCaricaAttributo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnCaricaAttributo -->
<s:submit name="widg_btnCaricaAttributo" id="widg_btnCaricaAttributo" method="handleBtnCaricaAttributo_CLICKED"
	key="cpDettaglioApplicazione.btnCaricaAttributo.label" cssClass="buttonWidget"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnCaricaAttributo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnCompletaiAttributo')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnCompletaiAttributo -->
<s:submit name="widg_btnCompletaiAttributo" id="widg_btnCompletaiAttributo" method="handleBtnCompletaiAttributo_CLICKED"
	key="cpDettaglioApplicazione.btnCompletaiAttributo.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnCompletaiAttributo')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniAttr --></div>

			
	
	<div id="p_wpAzioniGWMP" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniGWMP -->
	
	

<div class="widgetsPanel" id="wpAzioniGWMP">
	
	<customtag:widgetsPanel id="wpAzioniGWMPTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnSalvaAssociazioneGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnSalvaAssociazioneGW_MP -->
<s:submit name="widg_btnSalvaAssociazioneGW_MP" id="widg_btnSalvaAssociazioneGW_MP" method="handleBtnSalvaAssociazioneGW_MP_CLICKED"
	key="cpDettaglioApplicazione.btnSalvaAssociazioneGW_MP.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnSalvaAssociazioneGW_MP')" />

	
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
