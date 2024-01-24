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



<!-- pageId:cpDettaglioParametroConfigurazioneBO -->
<s:form id="cpDettaglioParametroConfigurazioneBO" action="cpDettaglioParametroConfigurazioneBO" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioParametroConfigurazioneBO.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpCercaPar" class="stdMessagePanelBlock"><!-- startFragment:p_smpCercaPar -->
	
	
<div class="stdMessagePanel" id="smpCercaPar">
	<customtag:stdMessagePanel id="smpCercaPar" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpCercaPar --></div>

			
	
	<div id="p_wpDati" class="widgetsPanelBlock"><!-- startFragment:p_wpDati -->
	
	

<div class="widgetsPanel" id="wpDati">
	
	<customtag:widgetsPanel id="wpDatiTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','ptDatiObbligatori')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioParametroConfigurazioneBO.ptDatiObbligatori.label')}" labelFor="widg_ptDatiObbligatori" errorFor="widg_ptDatiObbligatori" labelId="ptDatiObbligatoriLbl"   >


<!-- widget ptDatiObbligatori -->


	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','ptChiave')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioParametroConfigurazioneBO.ptChiave.label')}" labelFor="widg_ptChiave" errorFor="appDataparametroConfigurazioneBO.idParametro" labelId="ptChiaveLbl"   >


<!-- widget ptChiave -->
<s:property value="appDataparametroConfigurazioneBO.idParametro" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','tfChiave')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioParametroConfigurazioneBO.tfChiave.label')}" labelFor="widg_tfChiave" errorFor="appDataparametroConfigurazioneBO.idParametro" labelId="tfChiaveLbl"
	  >


<!-- widget tfChiave -->
<s:textfield name="appDataparametroConfigurazioneBO.idParametro" id="widg_tfChiave"
	disabled="isWidgetDisabled('cpDettaglioParametroConfigurazioneBO','tfChiave')"
	size="15" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','tfValore')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioParametroConfigurazioneBO.tfValore.label')}" labelFor="widg_tfValore" errorFor="appDataparametroConfigurazioneBO.valore" labelId="tfValoreLbl"
	  >


<!-- widget tfValore -->
<s:textfield name="appDataparametroConfigurazioneBO.valore" id="widg_tfValore"
maxlength="80"	disabled="isWidgetDisabled('cpDettaglioParametroConfigurazioneBO','tfValore')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','tfDescrizione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioParametroConfigurazioneBO.tfDescrizione.label')}" labelFor="widg_tfDescrizione" errorFor="appDataparametroConfigurazioneBO.descrParametro" labelId="tfDescrizioneLbl"
	  >


<!-- widget tfDescrizione -->
<s:textfield name="appDataparametroConfigurazioneBO.descrParametro" id="widg_tfDescrizione"
maxlength="80"	disabled="isWidgetDisabled('cpDettaglioParametroConfigurazioneBO','tfDescrizione')"
	size="80" 
/>

	
</customtag:widgetsPanelColumn>

</s:if>

	



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDati --></div>

			
	
	<div id="p_wpComandi" class="widgetsPanelBlock"><!-- startFragment:p_wpComandi -->
	
	

<div class="widgetsPanel" id="wpComandi">
	
	<customtag:widgetsPanel id="wpComandiTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','btnIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnIndietro -->
<s:submit name="widg_btnIndietro" id="widg_btnIndietro" method="handleBtnIndietro_CLICKED"
	key="cpDettaglioParametroConfigurazioneBO.btnIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioParametroConfigurazioneBO','btnIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioParametroConfigurazioneBO','btnSalva')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnSalva -->
<s:submit name="widg_btnSalva" id="widg_btnSalva" method="handleBtnSalva_CLICKED"
	key="cpDettaglioParametroConfigurazioneBO.btnSalva.label" cssClass="buttonWidget save"
	disabled="isWidgetDisabled('cpDettaglioParametroConfigurazioneBO','btnSalva')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpComandi --></div>

	

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
