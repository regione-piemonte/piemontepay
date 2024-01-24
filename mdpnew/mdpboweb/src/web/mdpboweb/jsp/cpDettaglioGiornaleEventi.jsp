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



<!-- pageId:cpDettaglioGiornaleEventi -->
<s:form id="cpDettaglioGiornaleEventi" action="cpDettaglioGiornaleEventi" method="post">
		
	
	
		
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

	
		
			
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','mnuView')" >

	


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
				<h3><s:text name="cpDettaglioGiornaleEventi.pMain.label" /></h3>

					
	
	<div id="p_pCenter" class="formPanelBlock"><!-- startFragment:p_pCenter -->

	
	

	
	
			
	
	<div id="p_smpDettaglioGiornaleEventi" class="stdMessagePanelBlock"><!-- startFragment:p_smpDettaglioGiornaleEventi -->
	
	
<div class="stdMessagePanel" id="smpDettaglioGiornaleEventi">
	<customtag:stdMessagePanel id="smpDettaglioGiornaleEventi" errorMessage="true" errorDetails="true" actionMessage="true" />
</div>

	<!-- endFragment:p_smpDettaglioGiornaleEventi --></div>

			
	
	<div id="p_wpDettaglioGiornaleEventi" class="widgetsPanelBlock"><!-- startFragment:p_wpDettaglioGiornaleEventi -->
	
	
<h4 class="wpLabel">Dettaglio Giornale Eventi </h4>
<div class="widgetsPanel" id="wpDettaglioGiornaleEventi">
	
	<customtag:widgetsPanel id="wpDettaglioGiornaleEventiTbl" columns="4" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptInsertDate')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptInsertDate.label')}" labelFor="widg_ptInsertDate" errorFor="appDatagiornaleEventi.insertDate" labelId="ptInsertDateLbl"  position="first" >


<!-- widget ptInsertDate -->
<s:property value="appDatagiornaleEventi.insertDate" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_1_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_1_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptlastUpdate')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptlastUpdate.label')}" labelFor="widg_ptlastUpdate" errorFor="appDatagiornaleEventi.lastUpdate" labelId="ptlastUpdateLbl"  position="first" >


<!-- widget ptlastUpdate -->
<s:property value="appDatagiornaleEventi.lastUpdate" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_2_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_2_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptDataoraevento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="" labelFor="widg_ptDataoraevento" errorFor="appDatagiornaleEventi.dataoraevento" labelId="ptDataoraeventoLbl"  position="first" >


<!-- widget ptDataoraevento -->
<s:property value="appDatagiornaleEventi.dataoraevento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_3_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_3_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptIdentificativoDominio')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptIdentificativoDominio.label')}" labelFor="widg_ptIdentificativoDominio" errorFor="appDatagiornaleEventi.identificativodominio" labelId="ptIdentificativoDominioLbl"  position="first" >


<!-- widget ptIdentificativoDominio -->
<s:property value="appDatagiornaleEventi.identificativodominio" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_4_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_4_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptIuv')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptIuv.label')}" labelFor="widg_ptIuv" errorFor="appDatagiornaleEventi.iuv" labelId="ptIuvLbl"  position="first" >


<!-- widget ptIuv -->
<s:property value="appDatagiornaleEventi.iuv" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_5_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_5_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptCodicecontesto')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptCodicecontesto.label')}" labelFor="widg_ptCodicecontesto" errorFor="appDatagiornaleEventi.codiceContesto" labelId="ptCodicecontestoLbl"  position="first" >


<!-- widget ptCodicecontesto -->
<s:property value="appDatagiornaleEventi.codiceContesto" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_6_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_6_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptIdPsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptIdPsp.label')}" labelFor="widg_ptIdPsp" errorFor="appDatagiornaleEventi.idPsp" labelId="ptIdPspLbl"  position="first" >


<!-- widget ptIdPsp -->
<s:property value="appDatagiornaleEventi.idPsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_7_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_7_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptTipoVersamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptTipoVersamento.label')}" labelFor="widg_ptTipoVersamento" errorFor="appDatagiornaleEventi.tipoversamento" labelId="ptTipoVersamentoLbl"  position="first" >


<!-- widget ptTipoVersamento -->
<s:property value="appDatagiornaleEventi.tipoversamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_8_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_8_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptComponente')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptComponente.label')}" labelFor="widg_ptComponente" errorFor="appDatagiornaleEventi.componente" labelId="ptComponenteLbl"  position="first" >


<!-- widget ptComponente -->
<s:property value="appDatagiornaleEventi.componente" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_9_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_9_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptCategoriaEvento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptCategoriaEvento.label')}" labelFor="widg_ptCategoriaEvento" errorFor="appDatagiornaleEventi.categoriaevento" labelId="ptCategoriaEventoLbl"  position="first" >


<!-- widget ptCategoriaEvento -->
<s:property value="appDatagiornaleEventi.categoriaevento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_10_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_10_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptTipoEvento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptTipoEvento.label')}" labelFor="widg_ptTipoEvento" errorFor="appDatagiornaleEventi.tipoevento" labelId="ptTipoEventoLbl"  position="first" >


<!-- widget ptTipoEvento -->
<s:property value="appDatagiornaleEventi.tipoevento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_11_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_11_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptSottotipoevento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptSottotipoevento.label')}" labelFor="widg_ptSottotipoevento" errorFor="appDatagiornaleEventi.sottotipoevento" labelId="ptSottotipoeventoLbl"  position="first" >


<!-- widget ptSottotipoevento -->
<s:property value="appDatagiornaleEventi.sottotipoevento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_12_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_12_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptIdentificativoFruitore')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptIdentificativoFruitore.label')}" labelFor="widg_ptIdentificativoFruitore" errorFor="appDatagiornaleEventi.identificativofruitore" labelId="ptIdentificativoFruitoreLbl"  position="first" >


<!-- widget ptIdentificativoFruitore -->
<s:property value="appDatagiornaleEventi.identificativofruitore" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_13_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_13_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptIdentificativoErogatore')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptIdentificativoErogatore.label')}" labelFor="widg_ptIdentificativoErogatore" errorFor="appDatagiornaleEventi.identificativoerogatore" labelId="ptIdentificativoErogatoreLbl"  position="first" >


<!-- widget ptIdentificativoErogatore -->
<s:property value="appDatagiornaleEventi.identificativoerogatore" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_14_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_14_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptIdentificativostazioneintermediariopa')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptIdentificativostazioneintermediariopa.label')}" labelFor="widg_ptIdentificativostazioneintermediariopa" errorFor="appDatagiornaleEventi.identificativostazioneintermediariopa" labelId="ptIdentificativostazioneintermediariopaLbl"  position="first" >


<!-- widget ptIdentificativostazioneintermediariopa -->
<s:property value="appDatagiornaleEventi.identificativostazioneintermediariopa" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_15_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_15_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptIdIntPsp')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptIdIntPsp.label')}" labelFor="widg_ptIdIntPsp" errorFor="appDatagiornaleEventi.idIntPsp" labelId="ptIdIntPspLbl"  position="first" >


<!-- widget ptIdIntPsp -->
<s:property value="appDatagiornaleEventi.idIntPsp" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_16_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_16_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptCanalepagamento')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptCanalepagamento.label')}" labelFor="widg_ptCanalepagamento" errorFor="appDatagiornaleEventi.canalepagamento" labelId="ptCanalepagamentoLbl"  position="first" >


<!-- widget ptCanalepagamento -->
<s:property value="appDatagiornaleEventi.canalepagamento" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_17_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_17_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptParametrispecificiinterfaccia')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptParametrispecificiinterfaccia.label')}" labelFor="widg_ptParametrispecificiinterfaccia" errorFor="appDatagiornaleEventi.parametrispecificiinterfaccia" labelId="ptParametrispecificiinterfacciaLbl"  position="first" >


<!-- widget ptParametrispecificiinterfaccia -->
<s:property value="appDatagiornaleEventi.parametrispecificiinterfaccia" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_18_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_18_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptesito')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptesito.label')}" labelFor="widg_ptesito" errorFor="appDatagiornaleEventi.esito" labelId="ptesitoLbl"  position="first" >


<!-- widget ptesito -->
<s:property value="appDatagiornaleEventi.esito" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_19_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_19_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','ptApplicationId')" >

	
<customtag:widgetsPanelColumn labelField="true" textLabel="%{getText('cpDettaglioGiornaleEventi.ptApplicationId.label')}" labelFor="widg_ptApplicationId" errorFor="appDatagiornaleEventi.applicationId" labelId="ptApplicationIdLbl"  position="first" >


<!-- widget ptApplicationId -->
<s:property value="appDatagiornaleEventi.applicationId" escape="false" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="first"/>
</s:else>


	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','wpDettaglioGiornaleEventi_20_2')" >

	
<customtag:widgetsPanelColumn  colSpan="2" position="last" >


<!-- widget wpDettaglioGiornaleEventi_20_2 -->


	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue="" colSpan="2" position="last"/>
</s:else>



	
	
	
	</customtag:widgetsPanel>
	
</div>

	<!-- endFragment:p_wpDettaglioGiornaleEventi --></div>

			
	
	<div id="p_wpAzioni" class="widgetsPanelBlock"><!-- startFragment:p_wpAzioni -->
	
	

<div class="widgetsPanel" id="wpAzioni">
	
	<customtag:widgetsPanel id="wpAzioniTbl" columns="2" tableStyleClass="formTable"
		summary="" 
		>
	

	
	
<s:if test="isWidgetVisible('cpDettaglioGiornaleEventi','btIndietro')" >

	
<customtag:widgetsPanelColumn colSpan="2" position="first">


<!-- widget btIndietro -->
<s:submit name="widg_btIndietro" id="widg_btIndietro" method="handleBtIndietro_CLICKED"
	key="cpDettaglioGiornaleEventi.btIndietro.label" cssClass="buttonWidget back"
	disabled="isWidgetDisabled('cpDettaglioGiornaleEventi','btIndietro')" />

	
</customtag:widgetsPanelColumn>

</s:if>

	
<s:else>
	<customtag:widgetsPanelColumn textValue=""  position="first"/>
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
