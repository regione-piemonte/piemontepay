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



<!-- pageId:cpEliminaParametroConfigurazioneBO -->
<s:form id="cpEliminaParametroConfigurazioneBO" action="cpEliminaParametroConfigurazioneBO" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpEliminaParametroConfigurazioneBO','mnuView')" >

	


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
				<h3><s:text name="cpEliminaParametroConfigurazioneBO.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smp" class="stdMessagePanelBlock"><!-- startFragment:p_smp -->
	
	
<div class="stdMessagePanel" id="smp">
	<customtag:stdMessagePanel id="smp" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smp --></div>

			
	
	<div id="p_wpDati" class="widgetsPanelBlock"><!-- startFragment:p_wpDati -->
	
	

<div class="widgetsPanel" id="wpDati">
	
	<customtag:widgetsPanel id="wpDatiTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		percentCols="25,75">
	

	
	
<s:if test="isWidgetVisible('cpEliminaParametroConfigurazioneBO','ptConfermaEliminaParametro')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_ptConfermaEliminaParametro" errorFor="widg_ptConfermaEliminaParametro" labelId="ptConfermaEliminaParametroLbl"   >


<!-- widget ptConfermaEliminaParametro -->
<s:text name="cpEliminaParametroConfigurazioneBO.ptConfermaEliminaParametro.statictext.label" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpEliminaParametroConfigurazioneBO','ptChiave')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpEliminaParametroConfigurazioneBO.ptChiave.label')}" labelFor="widg_ptChiave" errorFor="appDataparametroConfigurazioneBO.idParametro" labelId="ptChiaveLbl"   >


<!-- widget ptChiave -->
<s:property value="appDataparametroConfigurazioneBO.idParametro" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpEliminaParametroConfigurazioneBO','ptValore')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpEliminaParametroConfigurazioneBO.ptValore.label')}" labelFor="widg_ptValore" errorFor="appDataparametroConfigurazioneBO.valore" labelId="ptValoreLbl"   >


<!-- widget ptValore -->
<s:property value="appDataparametroConfigurazioneBO.valore" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	


	
<s:if test="isWidgetVisible('cpEliminaParametroConfigurazioneBO','ptDescrizione')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpEliminaParametroConfigurazioneBO.ptDescrizione.label')}" labelFor="widg_ptDescrizione" errorFor="appDataparametroConfigurazioneBO.descrParametro" labelId="ptDescrizioneLbl"   >


<!-- widget ptDescrizione -->
<s:property value="appDataparametroConfigurazioneBO.descrParametro" escape="false" />

	
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
	

	
	
<s:if test="isWidgetVisible('cpEliminaParametroConfigurazioneBO','btnIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btnIndietro -->
<s:submit name="widg_btnIndietro" id="widg_btnIndietro" method="handleBtnIndietro_CLICKED"
	key="cpEliminaParametroConfigurazioneBO.btnIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpEliminaParametroConfigurazioneBO','btnIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpEliminaParametroConfigurazioneBO','btnElimina')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="last">


<!-- widget btnElimina -->
<s:submit name="widg_btnElimina" id="widg_btnElimina" method="handleBtnElimina_CLICKED"
	key="cpEliminaParametroConfigurazioneBO.btnElimina.label" cssClass="buttonWidget cancel"
	disabled="isWidgetDisabled('cpEliminaParametroConfigurazioneBO','btnElimina')" />

	
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
