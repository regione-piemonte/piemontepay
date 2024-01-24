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

	
	<div id="p_pIbanEnteApplication" class="formPanelBlock"><!-- startFragment:p_pIbanEnteApplication -->
		
	
<div class="formPanel" id="pIbanEnteApplication">


	
	

	
	
			
	
	<div id="p_wpListaIban" class="widgetsPanelBlock"><!-- startFragment:p_wpListaIban -->
	
	

<div class="widgetsPanel" id="wpListaIban">
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','tbIbanEnteApplicatio')" >

	
<div class="tableWidget">


<!-- widget tbIbanEnteApplicatio -->
	<csiuicore:ajaxify id="p_wpListaIban" 
		widgetType="table" 
		pageId="cpDettaglioApplicazione">
<s:set name="cpDettaglioApplicazione_tbIbanEnteApplicatio_clearStatus" value="isTableClearStatus('cpDettaglioApplicazione_tbIbanEnteApplicatio')" />
<display:table name="appDatalistaIbanEnteApplication"
               excludedParams="*"
               export="true"
               id="widg_tbIbanEnteApplicatio"
               pagesize="20"
               requestURI="cpDettaglioApplicazione.do"
               keepStatus="true"
               clearStatus="${cpDettaglioApplicazione_tbIbanEnteApplicatio_clearStatus}"
               uid="row_tbIbanEnteApplicatio"
               summary="" 
               
               class="dataTable">
	
		<display:column headerClass="nosort" media="html">
			<s:radio list="%{#attr.row_tbIbanEnteApplicatio.id}" name="appDataselettoreIbanEnteApplication" id="%{'tbIbanEnteApplicatio-editcell-'+ (#attr.row_tbIbanEnteApplicatio_rowNum - 1)}" cssClass="radio" />
		</display:column>

		<display:column headerClass="nosort" media="excel pdf">
		</display:column>
		<display:column property="applicationId" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.applicationId.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="applicationId" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.applicationId.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="applicationId" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.applicationId.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="idEnte" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.idEnte.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="idEnte" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.idEnte.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="idEnte" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.idEnte.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="tipoversamento" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.tipoversamento.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="tipoversamento" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.tipoversamento.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="tipoversamento" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.tipoversamento.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="identificativopsp" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.identificativopsp.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="identificativopsp" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.identificativopsp.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="identificativopsp" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.identificativopsp.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
		<display:column property="iban" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.iban.label" 
			sortable="false" headerClass="nosort"
			    media="html" />

		<display:column property="iban" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.iban.export.label"
			sortable="false" headerClass="nosort"
			
			media="excel" />
		<display:column property="iban" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.iban.export.label"
			sortable="false" headerClass="nosort"
			 
			media="pdf" />	
		<display:column property="dataInizioValidita" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.dataInizioValidita.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="dataInizioValidita" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.dataInizioValidita.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="dataInizioValidita" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.dataInizioValidita.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="dataFineValidita" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.dataFineValidita.label" 
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"   media="html" />

		<display:column property="dataFineValidita" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.dataFineValidita.export.label"
			sortable="true" headerClass="sortable"
			comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="excel" />
		<display:column property="dataFineValidita" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.dataFineValidita.export.label"
			sortable="true" headerClass="sortable"
			 comparator="it.csi.mdp.mdpboweb.presentation.mdpboweb.comparator.CsiDateComparator"
			media="pdf" />	
		<display:column property="attivo" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.attivo.label" 
			sortable="true" headerClass="sortable"
			    media="html" />

		<display:column property="attivo" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.attivo.export.label"
			sortable="true" headerClass="sortable"
			
			media="excel" />
		<display:column property="attivo" titleKey="cpDettaglioApplicazione.tbIbanEnteApplicatio.attivo.export.label"
			sortable="true" headerClass="sortable"
			 
			media="pdf" />	
</display:table>



	</csiuicore:ajaxify>


	
</div>

</s:if>

	



	
	
	
</div>

	<!-- endFragment:p_wpListaIban --></div>

			
	
	<div id="p_wpActionListaIban" class="widgetsPanelBlock"><!-- startFragment:p_wpActionListaIban -->
	
	

<div class="widgetsPanel" id="wpActionListaIban">
	
	<customtag:widgetsPanel id="wpActionListaIbanTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btdisattiva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btdisattiva -->
<s:submit name="widg_btdisattiva" id="widg_btdisattiva" method="handleBtdisattiva_CLICKED"
	key="cpDettaglioApplicazione.btdisattiva.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btdisattiva')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btmodifica')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btmodifica -->
<s:submit name="widg_btmodifica" id="widg_btmodifica" method="handleBtmodifica_CLICKED"
	key="cpDettaglioApplicazione.btmodifica.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btmodifica')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpActionListaIban --></div>

			
	
	<div id="p_wpFieldModIban" class="widgetsPanelBlock"><!-- startFragment:p_wpFieldModIban -->
	
	

<div class="widgetsPanel" id="wpFieldModIban">
	
	<customtag:widgetsPanel id="wpFieldModIbanTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpFieldModIban_1_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpFieldModIban_1_1 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txIdApplication')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txIdApplication.label')}" labelFor="widg_txIdApplication" errorFor="appDataapplicazione.idApplicazione" labelId="txIdApplicationLbl"  position="first" >


<!-- widget txIdApplication -->
<s:property value="appDataapplicazione.idApplicazione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txIdEnte')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txIdEnte.label')}" labelFor="widg_txIdEnte" errorFor="appDataenti.enteId" labelId="txIdEnteLbl"  position="first" >


<!-- widget txIdEnte -->
<s:property value="appDataenti.enteId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cbListaTipoVersamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cbListaTipoVersamento.label')}" labelFor="widg_cbListaTipoVersamento" errorFor="appDataibanEnteApplication.tipoversamento" labelId="cbListaTipoVersamentoLbl"
	position="first"  >


<!-- widget cbListaTipoVersamento -->
<s:select name="appDataibanEnteApplication.tipoversamento" id="widg_cbListaTipoVersamento"
           
          list="appDatalistaTipoversamento"
          disabled="isWidgetDisabled('cpDettaglioApplicazione','cbListaTipoVersamento')"
          listKey="id"
          listValue="descrizione"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cbIdentificativoPsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cbIdentificativoPsp.label')}" labelFor="widg_cbIdentificativoPsp" errorFor="appDataibanEnteApplication.identificativopsp" labelId="cbIdentificativoPspLbl"
	position="first"  >


<!-- widget cbIdentificativoPsp -->
<s:select name="appDataibanEnteApplication.identificativopsp" id="widg_cbIdentificativoPsp"
           
          list="appDatalistaInformativePsp"
          disabled="isWidgetDisabled('cpDettaglioApplicazione','cbIdentificativoPsp')"
          listKey="identificativopsp"
          listValue="identificativopsp"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cbAttivazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cbAttivazione.label')}" labelFor="widg_cbAttivazione" errorFor="appDataibanEnteApplication.attivo" labelId="cbAttivazioneLbl"
	position="first"  >


<!-- widget cbAttivazione -->
<s:select name="appDataibanEnteApplication.attivo" id="widg_cbAttivazione"
           
          list="appDatalistaAttivazione"
          disabled="isWidgetDisabled('cpDettaglioApplicazione','cbAttivazione')"
          listKey="key"
          listValue="value"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','txIban')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.txIban.label')}" labelFor="widg_txIban" errorFor="appDataibanEnteApplication.iban" labelId="txIbanLbl"
	position="first"  >


<!-- widget txIban -->
<s:textfield name="appDataibanEnteApplication.iban" id="widg_txIban"
maxlength="35"	disabled="isWidgetDisabled('cpDettaglioApplicazione','txIban')"
	size="35" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','clDataInizioValidita')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.clDataInizioValidita.label')}" labelFor="widg_clDataInizioValidita" errorFor="appDataibanEnteApplication.dataInizioValidita" labelId="clDataInizioValiditaLbl"
	position="first"  >


<!-- widget clDataInizioValidita -->
<s:textfield name="appDataibanEnteApplication.dataInizioValidita" id="widg_clDataInizioValidita"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','clDataInizioValidita')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','cLdataFineValidita')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioApplicazione.cLdataFineValidita.label')}" labelFor="widg_cLdataFineValidita" errorFor="appDataibanEnteApplication.dataFineValidita" labelId="cLdataFineValiditaLbl"
	position="first"  >


<!-- widget cLdataFineValidita -->
<s:textfield name="appDataibanEnteApplication.dataFineValidita" id="widg_cLdataFineValidita"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','cLdataFineValidita')"
	size="10" maxlength="10"
	cssClass="calendar"
/> <div class="infoCalendar"></div>

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','wpFieldModIban_10_1')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="first" >


<!-- widget wpFieldModIban_10_1 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpFieldModIban --></div>

			
	
	<div id="p_wpActionFieldModIban" class="widgetsPanelBlock"><!-- startFragment:p_wpActionFieldModIban -->
	
	

<div class="widgetsPanel" id="wpActionFieldModIban">
	
	<customtag:widgetsPanel id="wpActionFieldModIbanTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','btSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btSalva -->
<s:submit name="widg_btSalva" id="widg_btSalva" method="handleBtSalva_CLICKED"
	key="cpDettaglioApplicazione.btSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioApplicazione','btSalva')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpActionFieldModIban --></div>

			
	
	<div id="p_wpHidden" class="widgetsPanelBlock"><!-- startFragment:p_wpHidden -->
	
	

<div class="widgetsPanel" id="wpHidden">
	
	<customtag:widgetsPanel id="wpHiddenTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','hId')" >

	
<div class="hidden">


<!-- widget hId -->
<s:hidden name="appDataibanEnteApplication.id" id="widg_hId" />

	
</div>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioApplicazione','hIbanOld')" >

	
<div class="hidden">


<!-- widget hIbanOld -->
<s:hidden name="appDataibanEnteApplication.ibanOld" id="widg_hIbanOld" />

	
</div>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpHidden --></div>

	

		
	
</div>

	<!-- endFragment:p_pIbanEnteApplication --></div>
