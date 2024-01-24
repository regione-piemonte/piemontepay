<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/customtag" prefix="customtag" %>
<%@taglib uri="/csiuicore" prefix="csiuicore" %>

<s:include value="fragments/header.jsp" ></s:include>



<!-- pageId:cpDettaglioInformativaPsp -->
<s:form id="cpDettaglioInformativaPsp" action="cpDettaglioInformativaPsp" method="post">
		
	
	
		
<!-- ####################### PANNELLO CONTENUTI ###################### -->
<div id="contentPanel">

	<!-- ================ UDLRC Layout UP PANEL ================ -->
	<div id="northPanel">
		<div class="wrapper">
		
	
	<div id="p_pUP" class="formPanelBlock"><!-- startFragment:p_pUP -->

	
	

	
	
			
	
	<div id="p_pUserInfo" class="userInfoPanelBlock"><!-- startFragment:p_pUserInfo -->
	
	
<div class="userInfoPanel" id="pUserInfo">
	<div class="userData">
		<span class="user">Utente: <span class="value"><s:property value="appDatacurrentUser.nome" /> <s:property value="appDatacurrentUser.cognome" /></span></span>
		<span class="organization">Ente: <span class="value"><s:property value="appDatacurrentUser.ente" /></span></span>
		<span class="role">Ruolo: <span class="value"><s:property value="appDatacurrentUser.ruolo" /></span></span>
	</div>
	<div class="userInfoActions">
		<!-- uscita dal servizio e/o cambio ruolo -->
		<s:url id="logoutUrl" action="Logout" method="localLogout" />
		<s:a href="%{logoutUrl}" title="chiudi: questo link fa uscire dal servizio">esci</s:a>
	</div>
</div>

	<!-- endFragment:p_pUserInfo --></div>

	

	<!-- endFragment:p_pUP --></div>

		</div>
	</div>
	<!-- ================ FINE UDLRC Layout UP PANEL ================ -->


	<!-- ================ UDLRC Layout WEST-CENTER-EAST WRAPPER ================ -->
	<div id="westCenterWrapper" class="floatWrapper">
		<!-- ***** UDLRC Layout LEFT PANEL ***** -->
		<div id="westPanel">
			<div class="wrapper">
			
	
	<div id="p_pLeft" class="formPanelBlock"><!-- startFragment:p_pLeft -->

	
	

	
	
			
	
	<div id="p_pMenu" class="menuPanelBlock"><!-- startFragment:p_pMenu -->
	
	
<div class="menuPanel vertical" id="pMenu">

	
		
			
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','mnuView')" >

	


	<s:include value="fragments/menu.jsp"></s:include>

	

</s:if>

	


		
	
	
</div>

	<!-- endFragment:p_pMenu --></div>

	

	<!-- endFragment:p_pLeft --></div>

			</div>
		</div>
		<!-- ***** FINE UDLRC Layout LEFT PANEL ***** -->


		<!-- ***** UDLRC Layout CENTER PANEL ***** -->
		<div id="centerPanel">
			<div class="wrapper">
				<!-- titolo pannello PRIMO livello -->
				<h3><s:text name="cpDettaglioInformativaPsp.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettaglioPsP" class="stdMessagePanelBlock scroll"><!-- startFragment:p_smpDettaglioPsP -->
	
	
<div class="stdMessagePanel" id="smpDettaglioPsP">
	<customtag:stdMessagePanel id="smpDettaglioPsP" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioPsP --></div>

			
	
	<div id="p_wpDettaglioInformativaPsp" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioInformativaPsp -->
	
	
<h4 class="wpLabel">Dettaglio Informativa Psp </h4>
<div class="widgetsPanel" id="wpDettaglioInformativaPsp">
	
	<customtag:widgetsPanel id="wpDettaglioInformativaPspTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txidinformativapsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txidinformativapsp.label')}" labelFor="widg_txidinformativapsp" errorFor="appDatainformativePsp.idinformativapsp" labelId="txidinformativapspLbl"  position="first" tdStyleClass="numbers">


<!-- widget txidinformativapsp -->
<s:property value="appDatainformativePsp.idinformativapsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txidentificativoflusso')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txidentificativoflusso.label')}" labelFor="widg_txidentificativoflusso" errorFor="appDatainformativePsp.identificativoflusso" labelId="txidentificativoflussoLbl"  position="first" >


<!-- widget txidentificativoflusso -->
<s:property value="appDatainformativePsp.identificativoflusso" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txidentificativopsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txidentificativopsp.label')}" labelFor="widg_txidentificativopsp" errorFor="appDatainformativePsp.identificativopsp" labelId="txidentificativopspLbl"  position="first" >


<!-- widget txidentificativopsp -->
<s:property value="appDatainformativePsp.identificativopsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txragionesociale')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txragionesociale.label')}" labelFor="widg_txragionesociale" errorFor="appDatainformativePsp.ragionesociale" labelId="txragionesocialeLbl"  position="first" >


<!-- widget txragionesociale -->
<s:property value="appDatainformativePsp.ragionesociale" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txdatapubblicazione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txdatapubblicazione.label')}" labelFor="widg_txdatapubblicazione" errorFor="appDatainformativePsp.datapubblicazione" labelId="txdatapubblicazioneLbl"  position="first" >


<!-- widget txdatapubblicazione -->
<s:property value="appDatainformativePsp.datapubblicazione" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txdatainiziovalidita')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txdatainiziovalidita.label')}" labelFor="widg_txdatainiziovalidita" errorFor="appDatainformativePsp.datainiziovalidita" labelId="txdatainiziovaliditaLbl"  position="first" >


<!-- widget txdatainiziovalidita -->
<s:property value="appDatainformativePsp.datainiziovalidita" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txurlinformazionipsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txurlinformazionipsp.label')}" labelFor="widg_txurlinformazionipsp" errorFor="appDatainformativePsp.urlinformazionipsp" labelId="txurlinformazionipspLbl"  position="first" >


<!-- widget txurlinformazionipsp -->
<s:property value="appDatainformativePsp.urlinformazionipsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txstornopagamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txstornopagamento.label')}" labelFor="widg_txstornopagamento" errorFor="appDatainformativePsp.stornopagamento" labelId="txstornopagamentoLbl"  position="first" tdStyleClass="numbers">


<!-- widget txstornopagamento -->
<s:property value="appDatainformativePsp.stornopagamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txidentificativointermediario')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txidentificativointermediario.label')}" labelFor="widg_txidentificativointermediario" errorFor="appDatainformativePsp.identificativointermediario" labelId="txidentificativointermediarioLbl"  position="first" >


<!-- widget txidentificativointermediario -->
<s:property value="appDatainformativePsp.identificativointermediario" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txidentificativocanale')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txidentificativocanale.label')}" labelFor="widg_txidentificativocanale" errorFor="appDatainformativePsp.identificativocanale" labelId="txidentificativocanaleLbl"  position="first" >


<!-- widget txidentificativocanale -->
<s:property value="appDatainformativePsp.identificativocanale" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txtipoversamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txtipoversamento.label')}" labelFor="widg_txtipoversamento" errorFor="appDatainformativePsp.tipoversamento" labelId="txtipoversamentoLbl"  position="first" >


<!-- widget txtipoversamento -->
<s:property value="appDatainformativePsp.tipoversamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txmodellopagamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txmodellopagamento.label')}" labelFor="widg_txmodellopagamento" errorFor="appDatainformativePsp.modellopagamento" labelId="txmodellopagamentoLbl"  position="first" tdStyleClass="numbers">


<!-- widget txmodellopagamento -->
<s:property value="appDatainformativePsp.modellopagamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txpriorita')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txpriorita.label')}" labelFor="widg_txpriorita" errorFor="appDatainformativePsp.priorita" labelId="txprioritaLbl"  position="first" tdStyleClass="numbers">


<!-- widget txpriorita -->
<s:property value="appDatainformativePsp.priorita" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txdisponibilitaservizio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txdisponibilitaservizio.label')}" labelFor="widg_txdisponibilitaservizio" errorFor="appDatainformativePsp.disponibilitaservizio" labelId="txdisponibilitaservizioLbl"  position="first" >


<!-- widget txdisponibilitaservizio -->
<s:property value="appDatainformativePsp.disponibilitaservizio" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txdescrizioneservizio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txdescrizioneservizio.label')}" labelFor="widg_txdescrizioneservizio" errorFor="appDatainformativePsp.descrizioneservizio" labelId="txdescrizioneservizioLbl"  position="first" >


<!-- widget txdescrizioneservizio -->
<s:property value="appDatainformativePsp.descrizioneservizio" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txcondizionieconomichemassime')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txcondizionieconomichemassime.label')}" labelFor="widg_txcondizionieconomichemassime" errorFor="appDatainformativePsp.condizionieconomichemassime" labelId="txcondizionieconomichemassimeLbl"  position="first" >


<!-- widget txcondizionieconomichemassime -->
<s:property value="appDatainformativePsp.condizionieconomichemassime" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txurlinformazionicanale')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txurlinformazionicanale.label')}" labelFor="widg_txurlinformazionicanale" errorFor="appDatainformativePsp.urlinformazionicanale" labelId="txurlinformazionicanaleLbl"  position="first" >


<!-- widget txurlinformazionicanale -->
<s:property value="appDatainformativePsp.urlinformazionicanale" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','datainserimento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.datainserimento.label')}" labelFor="widg_datainserimento" errorFor="appDatainformativePsp.datainserimento" labelId="datainserimentoLbl"  position="first" >


<!-- widget datainserimento -->
<s:property value="appDatainformativePsp.datainserimento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txordinamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txordinamento.label')}" labelFor="widg_txordinamento" errorFor="appDatainformativePsp.ordinamento" labelId="txordinamentoLbl"  position="first" tdStyleClass="numbers">


<!-- widget txordinamento -->
<s:property value="appDatainformativePsp.ordinamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txstatoinserimento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txstatoinserimento.label')}" labelFor="widg_txstatoinserimento" errorFor="appDatainformativePsp.statoinserimento" labelId="txstatoinserimentoLbl"  position="first" >


<!-- widget txstatoinserimento -->
<s:property value="appDatainformativePsp.statoinserimento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','txorigine')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioInformativaPsp.txorigine.label')}" labelFor="widg_txorigine" errorFor="appDatainformativePsp.origine" labelId="txorigineLbl"  position="first" >


<!-- widget txorigine -->
<s:property value="appDatainformativePsp.origine" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioInformativaPsp --></div>

			
	
	<div id="p_wpDettaglioAzioniPsp" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioAzioniPsp -->
	
	

<div class="widgetsPanel" id="wpDettaglioAzioniPsp">
	
	<customtag:widgetsPanel id="wpDettaglioAzioniPspTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioInformativaPsp','btBck')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btBck -->
<s:submit name="widg_btBck" id="widg_btBck" method="handleBtBck_CLICKED"
	key="cpDettaglioInformativaPsp.btBck.label" cssClass="buttonWidget lookup"
	disabled="isWidgetDisabled('cpDettaglioInformativaPsp','btBck')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioAzioniPsp --></div>

	

	<!-- endFragment:p_pCenter --></div>

			</div>
		</div>
		<!-- ***** FINE UDLRC Layout CENTER PANEL ***** -->



	</div>
	<!-- ================ FINE UDLRC Layout WEST-CENTER-EAST WRAPPER ================ -->



</div>
<!-- #################### FINE PANNELLO CONTENUTI #################### -->




</s:form>


	<s:include value="fragments/footer.jsp" ></s:include>
