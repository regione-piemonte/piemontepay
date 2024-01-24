<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %>

	
	<div id="p_pDatiGenerali" class="formPanelBlock"><!-- startFragment:p_pDatiGenerali -->
		
	
<div class="formPanel" id="pDatiGenerali">


	
	

	
	
			
	
	<div id="p_wpDatiGenerali" class="widgetsPanelBlock"><!-- startFragment:p_wpDatiGenerali -->
	
	
<h4 class="wpLabel">Dati generali </h4>
<div class="widgetsPanel" id="wpDatiGenerali">
	
	<customtag:widgetsPanel id="wpDatiGeneraliTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','tfIdApplicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.tfIdApplicazione.label')}" labelFor="widg_tfIdApplicazione" errorFor="appDataapplicazione.idApplicazione" labelId="tfIdApplicazioneLbl"
	position="first"  >


<!-- widget tfIdApplicazione -->
<s:textfield name="appDataapplicazione.idApplicazione" id="widg_tfIdApplicazione"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','tfIdApplicazione')"
	size="25" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_1_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_1_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','tfNomeApplicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.tfNomeApplicazione.label')}" labelFor="widg_tfNomeApplicazione" errorFor="appDataapplicazione.nomeApplicazione" labelId="tfNomeApplicazioneLbl"
	position="first"  >


<!-- widget tfNomeApplicazione -->
<s:textfield name="appDataapplicazione.nomeApplicazione" id="widg_tfNomeApplicazione"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','tfNomeApplicazione')"
	size="100" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_2_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_2_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','tfReferente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.tfReferente.label')}" labelFor="widg_tfReferente" errorFor="appDataapplicazione.referenteCSI" labelId="tfReferenteLbl"
	position="first"  >


<!-- widget tfReferente -->
<s:textfield name="appDataapplicazione.referenteCSI" id="widg_tfReferente"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','tfReferente')"
	size="100" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_3_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_3_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','tfCliente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.tfCliente.label')}" labelFor="widg_tfCliente" errorFor="appDataapplicazione.cliente" labelId="tfClienteLbl"
	position="first"  >


<!-- widget tfCliente -->
<s:textfield name="appDataapplicazione.cliente" id="widg_tfCliente"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','tfCliente')"
	size="100" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_4_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_4_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','tfProgetto')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.tfProgetto.label')}" labelFor="widg_tfProgetto" errorFor="appDataapplicazione.progetto" labelId="tfProgettoLbl"
	position="first"  >


<!-- widget tfProgetto -->
<s:textfield name="appDataapplicazione.progetto" id="widg_tfProgetto"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','tfProgetto')"
	size="100" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_5_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_5_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','tfEmailEsercente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.tfEmailEsercente.label')}" labelFor="widg_tfEmailEsercente" errorFor="appDataapplicazione.emailEsercente" labelId="tfEmailEsercenteLbl"
	position="first"  >


<!-- widget tfEmailEsercente -->
<s:textfield name="appDataapplicazione.emailEsercente" id="widg_tfEmailEsercente"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','tfEmailEsercente')"
	size="100" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_6_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_6_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_6_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_6_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','tfnumeroVerde')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.tfnumeroVerde.label')}" labelFor="widg_tfnumeroVerde" errorFor="appDataapplicazione.numeroVerde" labelId="tfnumeroVerdeLbl"
	position="first"  >


<!-- widget tfnumeroVerde -->
<s:textfield name="appDataapplicazione.numeroVerde" id="widg_tfnumeroVerde"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','tfnumeroVerde')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_7_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_7_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_7_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_7_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','taNote')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazioneNew.taNote.label')}" labelFor="widg_taNote" errorFor="appDataapplicazione.note" labelId="taNoteLbl"
	position="first"  >


<!-- widget taNote -->
<s:textarea name="appDataapplicazione.note" id="widg_taNote"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','taNote')"
	rows="4"
	cols="100"
	></s:textarea>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_8_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2"  >


<!-- widget wpDatiGenerali_8_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" />
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','wpDatiGenerali_8_3')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDatiGenerali_8_3 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDatiGenerali --></div>

			
	
	<div id="p_wpAzioniApp" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniApp -->
	
	

<div class="widgetsPanel" id="wpAzioniApp">
	
	<customtag:widgetsPanel id="wpAzioniAppTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazioneNew','btnSalvaApplicazione')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnSalvaApplicazione -->
<s:submit name="widg_btnSalvaApplicazione" id="widg_btnSalvaApplicazione" method="handleBtnSalvaApplicazione_CLICKED"
	key="cpDettaglioApplicazioneNew.btnSalvaApplicazione.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioApplicazioneNew','btnSalvaApplicazione')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniApp --></div>

	

		
	
</div>

	<!-- endFragment:p_pDatiGenerali --></div>
