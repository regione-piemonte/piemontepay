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

	
	<div id="p_pAvanzi" class="formPanelBlock"><!-- startFragment:p_pAvanzi -->
		
	
<div class="formPanel" id="pAvanzi">


	
	

	
	
			
	
	<div id="p_wpGWMP2" class="widgetsPanelBlock"><!-- startFragment:p_wpGWMP2 -->
	
	
<h4 class="wpLabel">Dati generali </h4>
<div class="widgetsPanel" id="wpGWMP2">
	
	<customtag:widgetsPanel id="wpGWMP2Tbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_1_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpGWMP2_1_1 -->


	
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


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_2_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpGWMP2_2_1 -->


	
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


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP2_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_5_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP2_5_3 -->


	
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


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_6_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP2_6_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_6_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP2_6_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_7_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpGWMP2_7_1 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_7_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpGWMP2_7_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpGWMP2_7_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpGWMP2_7_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpGWMP2 --></div>

			
	
	<div id="p_wpAzioniGWMP2" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniGWMP2 -->
	
	

<div class="widgetsPanel" id="wpAzioniGWMP2">
	
	<customtag:widgetsPanel id="wpAzioniGWMP2Tbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnModificaAssGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnModificaAssGW_MP -->
<s:submit name="widg_btnModificaAssGW_MP" id="widg_btnModificaAssGW_MP" method="handleBtnModificaAssGW_MP_CLICKED"
	key="cpDettaglioApplicazione.btnModificaAssGW_MP.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnModificaAssGW_MP')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnSalvaAssociazioneGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget btnSalvaAssociazioneGW_MP -->
<s:submit name="widg_btnSalvaAssociazioneGW_MP" id="widg_btnSalvaAssociazioneGW_MP" method="handleBtnSalvaAssociazioneGW_MP_CLICKED"
	key="cpDettaglioApplicazione.btnSalvaAssociazioneGW_MP.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnSalvaAssociazioneGW_MP')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btnNuovaAssGW_MP')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnNuovaAssGW_MP -->
<s:submit name="widg_btnNuovaAssGW_MP" id="widg_btnNuovaAssGW_MP" method="handleBtnNuovaAssGW_MP_CLICKED"
	key="cpDettaglioApplicazione.btnNuovaAssGW_MP.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btnNuovaAssGW_MP')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniGWMP2 --></div>

			
	
	<div id="p_wpTabellaGWMP2" class="widgetsPanelBlock"><!-- startFragment:p_wpTabellaGWMP2 -->
	
	

<div class="widgetsPanel" id="wpTabellaGWMP2">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tbListaAssociazioni')" >

	
<div class="tableWidget">


<!-- widget tbListaAssociazioni -->
	<csiuicore:ajaxify id="p_wpTabellaGWMP2" 
		widgetType="table" 
		pageId="cpDettaglioApplicazione">
<s:set name="cpDettaglioApplicazione_tbListaAssociazioni_clearStatus" value="isTableClearStatus('cpDettaglioApplicazione_tbListaAssociazioni')" />
<display:table name="appDataassociazioniGW_MP"
               excludedParams="*"
               export="true"
               id="widg_tbListaAssociazioni"
               pagesize="0"
               requestURI="cpDettaglioApplicazione.do"
               keepStatus="true"
               clearStatus="${cpDettaglioApplicazione_tbListaAssociazioni_clearStatus}"
               uid="row_tbListaAssociazioni"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbListaAssociazioni.numItem}" name="appDataselettoreItemAssociazioneGW_MP.chiave" id="%{'tbListaAssociazioni-editcell-'+ (#attr.row_tbListaAssociazioni_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="descrGateway" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrGateway.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="descrGateway" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrGateway.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="descrGateway" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrGateway.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="descrPayment" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrPayment.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="descrPayment" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrPayment.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="descrPayment" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.descrPayment.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="abilitato" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.abilitato.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="abilitato" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.abilitato.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="abilitato" titleKey="cpDettaglioApplicazione.tbListaAssociazioni.abilitato.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpTabellaGWMP2 --></div>

	

		
	
</div>

	<!-- endFragment:p_pAvanzi --></div>
