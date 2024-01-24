<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

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

	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><s:a action="show-main-menu">Home</s:a><span class="divider"></span></li>
				<li><s:a action="entry-ricerca-flussi-pagamenti">Ricerca flussi notifiche di pagamento</s:a><span class="divider"></span></li>
				<li class="active">Visualizza flusso notifiche di pagamento</li>
			</ul>
		</div>
	</div>

	<s:hidden id="param_idFlusso" value="%{idFlusso}" theme="simple"/>
	<s:hidden id="param_restartFrom" value="%{restartFrom}" theme="simple"/>
	<s:hidden id="param_sortingDir" value="%{sortingDir}" theme="simple"/>
	<s:hidden id="param_sortingIdx" value="%{sortingIdx}" theme="simple"/>
	<s:hidden id="param_pageLength" value="%{pageLength}" theme="simple"/>
	<s:hidden id="initTable" name="init" theme="simple"/>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<h3>Visualizza flusso notifiche di pagamento</h3>

				<%@ include file="../include/panel-flusso-pagamenti.jsp"%>

				<br/>

				<table class="table table-hover tab_left dataTable" id="tabellaRisultati">
					<thead>
						<tr>
							<th>Posizione debitoria</th>
							<th>Id univoco versamento (IUV)</th>
							<th>Importo pagato</th>
							<th>Data esito pagamento</th>
							<th>Descrizione causale versamento</th>
							<th>Codice fiscale/Partita Iva (Soggetto debitore)</th>
							<th>Cognome e Nome/Ragione sociale (Soggetto debitore)</th>
							<th class="tab_Right span1">&nbsp;</th>
						</tr>
					</thead>
					<tbody/>
				</table>

				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a> /
					<s:a action="entry-ricerca-flussi-pagamenti" cssClass="btn">RICERCA FLUSSI</s:a>
				</div>
			</div>
		</div>
	</div>

	<div id="tableRowActions" class="btn-group hide">
		<s:if test="isUseCaseEnabled('DET_ESP')">
			<a id="dettaglioPagamentoAction" data-form-id="dettaglioPagamentoForm" class="btn">dettaglio</a>
		</s:if>
	</div>

	<s:form id="dettaglioPagamentoForm" action="entry-dettaglio-pagamento.do" method="POST" theme="simple">
		<s:hidden name="idNotificaPagamento"/>
	</s:form>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<script src="js/epaypaweb/visualizza-flusso-pagamenti.js" type="text/javascript"></script>

</body>
</html>
