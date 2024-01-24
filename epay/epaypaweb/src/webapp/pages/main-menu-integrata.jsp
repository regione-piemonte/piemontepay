<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../include/head.jsp"%>

<body>

	<p class="nascosto">
		<a title="A-sommario"></a>
	</p>
	<ul id="sommario" class="nascosto">
		<li><s:a href="#A-contenuti">Salta ai contenuti</s:a></li>
	</ul>
	<hr />

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

					<!-- NUOVA HOMEPAGE - CON INTEGRAZIONE -->
					<div class="ContainerIndexDefault">
					
						<%@ include file="bussolotti-home/bussolotto-01-liste-di-carico.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-02-aggiornamento-posizioni.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-03-notifiche-pagamento.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-04-avvisi-scaduti.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-05-flussi-rendicontazione.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-06-catalogo-entrate.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-07-profilazione-utente.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-08-modulo-riconciliazione.jsp"%>
						<%@ include file="bussolotti-home/bussolotto-09-modulo-contabile.jsp"%>

					</div>
				</s:form>
			</div>
		</div>
		<br />
		<%@ include file="../include/portal-footer.html"%>
		<%@ include file="../include/javascript.html"%>

		<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}" />
		<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}" />

		<script src="js/epaypaweb/main-menu.js" type="text/javascript"></script>
	</div>
</body>
</html>
