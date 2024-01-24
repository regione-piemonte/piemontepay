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



<!-- pageId:cpGestioneEnti -->
<s:form id="cpGestioneEnti" action="cpGestioneEnti" method="post">
		
	
	
		
<!-- ####################### PANNELLO CONTENUTI ###################### -->
<div id="contentPanel">

	<!-- ================ UDLRC Layout UP PANEL ================ -->
	<div id="northPanel">
		<div class="wrapper">
		
	
	<div id="p_uUp" class="formPanelBlock"><!-- startFragment:p_uUp -->

	
	

	
	
			
	
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

	

	<!-- endFragment:p_uUp --></div>

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

	
		
			
<s:if test="isWidgetVisible('cpGestioneEnti','mnuView')" >

	


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
				<h3><s:text name="cpGestioneEnti.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_stdMsg" class="stdMessagePanelBlock scroll"><!-- startFragment:p_stdMsg -->
	
	
<div class="stdMessagePanel" id="stdMsg">
	<customtag:stdMessagePanel id="stdMsg" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_stdMsg --></div>

			
	
	<div id="p_campiEnte" class="widgetsPanelBlock"><!-- startFragment:p_campiEnte -->
	
	

<div class="widgetsPanel" id="campiEnte">
	
	<customtag:widgetsPanel id="campiEnteTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneEnti','partitaIva')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneEnti.partitaIva.label')}" labelFor="widg_partitaIva" errorFor="appDataenti.partitaIva" labelId="partitaIvaLbl"
	  >


<!-- widget partitaIva -->
<s:textfield name="appDataenti.partitaIva" id="widg_partitaIva"
maxlength="11"	disabled="isWidgetDisabled('cpGestioneEnti','partitaIva')"
	size="11" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneEnti','descrizione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneEnti.descrizione.label')}" labelFor="widg_descrizione" errorFor="appDataenti.descrizione" labelId="descrizioneLbl"
	  >


<!-- widget descrizione -->
<s:textfield name="appDataenti.descrizione" id="widg_descrizione"
	disabled="isWidgetDisabled('cpGestioneEnti','descrizione')"
	size="40" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneEnti','ptEnteId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneEnti.ptEnteId.label')}" labelFor="widg_ptEnteId" errorFor="appDataenti.enteId" labelId="ptEnteIdLbl"   >


<!-- widget ptEnteId -->
<s:property value="appDataenti.enteId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneEnti','txEnteId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneEnti.txEnteId.label')}" labelFor="widg_txEnteId" errorFor="appDataenti.enteId" labelId="txEnteIdLbl"
	  >


<!-- widget txEnteId -->
<s:textfield name="appDataenti.enteId" id="widg_txEnteId"
maxlength="4"	disabled="isWidgetDisabled('cpGestioneEnti','txEnteId')"
	size="4" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpGestioneEnti','attivo')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpGestioneEnti.attivo.label')}" labelFor="widg_attivo" errorFor="appDataenti.attivo" labelId="attivoLbl"
	  >


<!-- widget attivo -->
<s:select name="appDataenti.attivo" id="widg_attivo"
           
          list="appDatalistaStatiAttivazioneEnti"
          disabled="isWidgetDisabled('cpGestioneEnti','attivo')"
          listKey="attivo"
          listValue="stato"
          
          />


	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_campiEnte --></div>

			
	
	<div id="p_wAction" class="widgetsPanelBlock"><!-- startFragment:p_wAction -->
	
	

<div class="widgetsPanel" id="wAction">
	
	<customtag:widgetsPanel id="wActionTbl" columns="6" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpGestioneEnti','indietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget indietro -->
<s:submit name="widg_indietro" id="widg_indietro" method="handleIndietro_CLICKED"
	key="cpGestioneEnti.indietro.label" cssClass="buttonWidget forward"
	disabled="isWidgetDisabled('cpGestioneEnti','indietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneEnti','inserisci')" >

	
<customtag:widgetsPanelColumn colSpan="2" >


<!-- widget inserisci -->
<s:submit name="widg_inserisci" id="widg_inserisci" method="handleInserisci_CLICKED"
	key="cpGestioneEnti.inserisci.label" cssClass="buttonWidget addItem"
	disabled="isWidgetDisabled('cpGestioneEnti','inserisci')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  />
</s:else>


	
<s:if test="isWidgetVisible('cpGestioneEnti','modifica')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget modifica -->
<s:submit name="widg_modifica" id="widg_modifica" method="handleModifica_CLICKED"
	key="cpGestioneEnti.modifica.label" cssClass="buttonWidget editItem"
	disabled="isWidgetDisabled('cpGestioneEnti','modifica')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wAction --></div>

	

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
