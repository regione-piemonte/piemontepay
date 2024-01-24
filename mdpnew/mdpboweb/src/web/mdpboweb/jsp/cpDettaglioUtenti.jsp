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



<!-- pageId:cpDettaglioUtenti -->
<s:form id="cpDettaglioUtenti" action="cpDettaglioUtenti" method="post">
		
	
	
		
<!-- ####################### PANNELLO CONTENUTI ###################### -->
<div id="contentPanel">

	<!-- ================ UDLRC Layout UP PANEL ================ -->
	<div id="northPanel">
		<div class="wrapper">
		
	
	<div id="p_pUp" class="formPanelBlock"><!-- startFragment:p_pUp -->

	
	

	
	
			
	
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

	

	<!-- endFragment:p_pUp --></div>

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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioUtenti','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioUtenti.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettaglioUtenti" class="stdMessagePanelBlock"><!-- startFragment:p_smpDettaglioUtenti -->
	
	
<div class="stdMessagePanel" id="smpDettaglioUtenti">
	<customtag:stdMessagePanel id="smpDettaglioUtenti" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioUtenti --></div>

			
	
	<div id="p_wpDettaglioUtenti" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioUtenti -->
	
	

<div class="widgetsPanel" id="wpDettaglioUtenti">
	
	<customtag:widgetsPanel id="wpDettaglioUtentiTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioUtenti','txidUtente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioUtenti.txidUtente.label')}" labelFor="widg_txidUtente" errorFor="appDatautente.idUtente" labelId="txidUtenteLbl"   >


<!-- widget txidUtente -->
<s:property value="appDatautente.idUtente" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioUtenti','tfdescrUtente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioUtenti.tfdescrUtente.label')}" labelFor="widg_tfdescrUtente" errorFor="appDatautente.descrUtente" labelId="tfdescrUtenteLbl"
	  >


<!-- widget tfdescrUtente -->
<s:textfield name="appDatautente.descrUtente" id="widg_tfdescrUtente"
	disabled="isWidgetDisabled('cpDettaglioUtenti','tfdescrUtente')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioUtenti','tfCodFisc')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioUtenti.tfCodFisc.label')}" labelFor="widg_tfCodFisc" errorFor="appDatautente.codFisc" labelId="tfCodFiscLbl"
	  >


<!-- widget tfCodFisc -->
<s:textfield name="appDatautente.codFisc" id="widg_tfCodFisc"
maxlength="16"	disabled="isWidgetDisabled('cpDettaglioUtenti','tfCodFisc')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioUtenti','tfEmail')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioUtenti.tfEmail.label')}" labelFor="widg_tfEmail" errorFor="appDatautente.email" labelId="tfEmailLbl"
	  >


<!-- widget tfEmail -->
<s:textfield name="appDatautente.email" id="widg_tfEmail"
	disabled="isWidgetDisabled('cpDettaglioUtenti','tfEmail')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioUtenti','Pwdservizio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioUtenti.Pwdservizio.label')}" labelFor="widg_Pwdservizio" errorFor="appDatautente.pwdservizio" labelId="PwdservizioLbl"
	  >


<!-- widget Pwdservizio -->
<s:textfield name="appDatautente.pwdservizio" id="widg_Pwdservizio"
	disabled="isWidgetDisabled('cpDettaglioUtenti','Pwdservizio')"
	size="50" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioUtenti','cbGruppo')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioUtenti.cbGruppo.label')}" labelFor="widg_cbGruppo" errorFor="appDataselettoreIdGruppo" labelId="cbGruppoLbl"
	  >


<!-- widget cbGruppo -->
<s:select name="appDataselettoreIdGruppo" id="widg_cbGruppo"
          headerKey="" headerValue="" 
          list="appDatagruppi"
          disabled="isWidgetDisabled('cpDettaglioUtenti','cbGruppo')"
          listKey="idgroup"
          listValue="description"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioUtenti --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioUtenti','btIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btIndietro -->
<s:submit name="widg_btIndietro" id="widg_btIndietro" method="handleBtIndietro_CLICKED"
	key="cpDettaglioUtenti.btIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioUtenti','btIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioUtenti','btSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btSalva -->
<s:submit name="widg_btSalva" id="widg_btSalva" method="handleBtSalva_CLICKED"
	key="cpDettaglioUtenti.btSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioUtenti','btSalva')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpAzioni --></div>

	

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
