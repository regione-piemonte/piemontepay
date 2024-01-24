<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %>

	
	<div id="p_pAssociazioneEnti" class="formPanelBlock"><!-- startFragment:p_pAssociazioneEnti -->
		
	
<div class="formPanel" id="pAssociazioneEnti">


	
	

	
	
			
	
	<div id="p_wpEnteSelezionato" class="widgetsPanelBlock"><!-- startFragment:p_wpEnteSelezionato -->
	
	

<div class="widgetsPanel" id="wpEnteSelezionato">
	
	<customtag:widgetsPanel id="wpEnteSelezionatoTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txEnteId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txEnteId.label')}" labelFor="widg_txEnteId" errorFor="appDataenti.enteId" labelId="txEnteIdLbl"  position="first" >


<!-- widget txEnteId -->
<s:property value="appDataenti.enteId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txPartitaIva')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txPartitaIva.label')}" labelFor="widg_txPartitaIva" errorFor="appDataenti.partitaIva" labelId="txPartitaIvaLbl"  position="first" >


<!-- widget txPartitaIva -->
<s:property value="appDataenti.partitaIva" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txDescrizioneEnte')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txDescrizioneEnte.label')}" labelFor="widg_txDescrizioneEnte" errorFor="appDataenti.descrizione" labelId="txDescrizioneEnteLbl"  position="first" >


<!-- widget txDescrizioneEnte -->
<s:property value="appDataenti.descrizione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpEnteSelezionato --></div>

			
	
	<div id="p_wpListaEnti" class="widgetsPanelBlock"><!-- startFragment:p_wpListaEnti -->
	
	

<div class="widgetsPanel" id="wpListaEnti">
	
	<customtag:widgetsPanel id="wpListaEntiTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cbEnteId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cbEnteId.label')}" labelFor="widg_cbEnteId" errorFor="appDataenti.enteIdSelezionato" labelId="cbEnteIdLbl"
	  >


<!-- widget cbEnteId -->
<s:select name="appDataenti.enteIdSelezionato" id="widg_cbEnteId"
          headerKey="" headerValue="" 
          list="appDatalistaEnti"
          disabled="isWidgetDisabled('cpDettaglioApplicazione','cbEnteId')"
          listKey="enteId"
          listValue="descrizione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpListaEnti --></div>

			
	
	<div id="p_wpAzioniAssociazioneEnte" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioniAssociazioneEnte -->
	
	

<div class="widgetsPanel" id="wpAzioniAssociazioneEnte">
	
	<customtag:widgetsPanel id="wpAzioniAssociazioneEnteTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','ctAssocia')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget ctAssocia -->
<s:submit name="widg_ctAssocia" id="widg_ctAssocia" method="handleCtAssocia_CLICKED"
	key="cpDettaglioApplicazione.ctAssocia.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','ctAssocia')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioniAssociazioneEnte --></div>

	

		
	
</div>

	<!-- endFragment:p_pAssociazioneEnti --></div>
