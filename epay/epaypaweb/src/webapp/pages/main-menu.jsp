<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%@ include file="../include/head.jsp"%>

<% 
/*
 * DEPRECATA POST INTEGRAZIONE CON CATALOG 
 * la nuova pagina e' main-menu-integrata.jsp 
 */
%>

<body>

	<p class="nascosto">
		<a title="A-sommario"></a>
	</p>
	<ul id="sommario" class="nascosto">
		<li><s:a href="#A-contenuti">Salta ai contenuti</s:a></li>
	</ul>
	<hr/>

	<div class="container-fluid-banner">
		<%@ include file="../include/portal-header.html"%>
		<%@ include file="navbar-login.jsp"%>
		<a title="A-contenuti"></a>
	</div>

	<p>&nbsp;</p>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:form id="menuGestore" modelAttribute="menuGestoreModel" method="POST" action="start.do">
					<div id="alert-div"></div>

					<div class="ContainerIndexDefault">				
						<s:if test="isCategoryEnabled('LISTE_CARICO')">
							<div class="btnIndex">
								<div class="iconBoxModuli">
									<div class="TitleBox">liste di carico</div>
								</div>
								<div class="txtDefModuli txtWhite">
									<s:if test="isUseCaseEnabled('INS_LDC')">
										<s:a action="entry-inserisci-listadicarico-da-file-step1">
											<p><span class="bullPointModuli"></span><span>inserisci da file</span></p>
											<s:param name="init" value="true"/>
										</s:a>
										<p class="DivisBoxModuli"></p>
									</s:if>
									<s:if test="isUseCaseEnabled('INS_LDC')">
										<s:a action="entry-visualizza-listadicarico">
											<p><span class="bullPointModuli"></span><span>inserisci</span></p>
											<s:param name="init" value="true"/>
											<s:param name="origineHomePerInserimento" value="true"/>
											<s:param name="operativita" value="@it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI"/>
										</s:a>
										<p class="DivisBoxModuli"></p>
									</s:if>
									<s:if test="isUseCaseEnabled('RIC_LDC')">
										<s:a action="entry-ricerca-listedicarico">
											<p><span class="bullPointModuli"></span><span>ricerca flussi</span></p>
											<s:param name="init" value="true"/>
											<s:param name="origineHomePerInserimento" value="false"/>
										</s:a>
									</s:if>
								</div>
							</div>
						</s:if>

						<s:if test="isCategoryEnabled('AGGIORNA_POSDEB')">
							<div class="btnIndex">
								<div class="iconBoxModuli">
									<div class="TitleBox">aggiornamento posizioni debitorie</div>
								</div>
								<div class="txtDefModuli txtWhite">
									<s:if test="isUseCaseEnabled('INS_AGPD')">
										<s:a action="entry-inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step1">
											<p><span class="bullPointModuli"></span><span>inserisci da file</span></p>
											<s:param name="init" value="true"/>
										</s:a>
										<p class="DivisBoxModuli"></p>
									</s:if>
									<s:if test="isUseCaseEnabled('INS_AGPD')">
										<s:a action="entry-visualizza-flusso-posizionidebitoriedaaggiornare">
											<p><span class="bullPointModuli"></span><span>inserisci</span></p>
											<s:param name="init" value="true"/>
											<s:param name="origineHomePerInserimento" value="true"/>
											<s:param name="operativita" value="@it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI"/>
										</s:a>
										<p class="DivisBoxModuli"></p>
									</s:if>
									<s:if test="isUseCaseEnabled('RIC_AGPD')">
										<s:a action="entry-ricerca-listeaggiornamentoposizionidebitorie">
											<p><span class="bullPointModuli"></span><span>ricerca flussi</span></p>
											<s:param name="init" value="true"/>
											<s:param name="origineHomePerInserimento" value="false"/>
										</s:a>
									</s:if>
								</div>
							</div>
						</s:if>

						<s:if test="isCategoryEnabled('ESITO_PAGTI')">
							<div class="btnIndex">
								<div class="iconBoxRisultati">
									<div class="TitleBox">notifiche pagamento</div>
								</div>
								<div class="txtGestRisultati txtWhite">
									<s:if test="isUseCaseEnabled('RIC_ESP')">
										<s:a action="entry-ricerca-flussi-pagamenti">
											<p><span class="bullPointRisultati"></span><span>ricerca flussi</span></p>
											<s:param name="init" value="true"/>
										</s:a>
									</s:if>
									<!-- CSI_PAG-184 -->
<%-- 									<s:if test="isUseCaseEnabled('RIC_REV')"> --%>
<%-- 										<s:a action="entry-ricerca-richieste-revoca"> --%>
<%-- 											<p><span class="bullPointRisultati"></span><span>ricerca richieste di revoca</span></p> --%>
<%-- 											<s:param name="init" value="true"/> --%>
<%-- 										</s:a> --%>
<%-- 									</s:if>			 --%>
									<!-- CSI_PAG-184 -->
									<s:if test="isUseCaseEnabled('RIC_ENTI')">
										<s:a action="entry-ricerca-report-enti">
											<p><span class="bullPointRisultati"></span><span>ricerca enti</span></p>
											<s:param name="init" value="true"/>
										</s:a>
									</s:if>
								</div>
							</div>
						</s:if>

<%-- 						<s:if test="isCategoryEnabled('AVVISI_SCADUTI')"> --%>
<!-- 							<div class="btnIndex"> -->
<!-- 								<div class="iconBoxRisultati"> -->
<!-- 									<div class="TitleBox">avvisi scaduti</div> -->
<!-- 								</div> -->
<!-- 								<div class="txtGestRisultati txtWhite"> -->
<%-- 									<s:if test="isUseCaseEnabled('RIC_AS')"> --%>
<%-- 										<s:a action="entry-ricerca-flussi-avvisiscaduti"> --%>
<%-- 											<p><span class="bullPointRisultati"></span><span>ricerca flussi</span></p> --%>
<%-- 											<s:param name="init" value="true"/> --%>
<%-- 										</s:a> --%>
<%-- 									</s:if> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<%-- 						</s:if> --%>

						<s:if test="isCategoryEnabled('FLUSSI_RENDICN')">
							<div class="btnIndex">
								<div class="iconBoxFlussiRendicontazione">
									<div class="TitleBox">flussi di rendicontazione</div>
								</div>
								<div class="txtGestFlussiRendicontazione txtWhite">
									<s:if test="isUseCaseEnabled('RIC_FR')">
										<s:a action="entry-ricerca-flussi-rendicontazione-base">
											<p><span class="bullPointFlussiRendicontazione"></span><span>ricerca flussi</span></p>
											<s:param name="init" value="true"/>
										</s:a>
									</s:if>
								</div>
							</div>
						</s:if>
					</div>
				</s:form>
			</div>
		</div>
		<br />
		<%@ include file="../include/portal-footer.html"%>
		<%@ include file="../include/javascript.html"%>

		<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
		<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>

		<script src="js/epaypaweb/main-menu.js" type="text/javascript"></script>
	</div>
</body>
</html>
