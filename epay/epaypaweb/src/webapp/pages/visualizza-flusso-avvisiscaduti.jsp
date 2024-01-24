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
				<li><s:a action="entry-ricerca-flussi-avvisiscaduti">Ricerca flussi avvisi scaduti</s:a><span class="divider"></span></li>
				<li class="active">Visualizza flusso avvisi scaduti</li>
			</ul>
		</div>
	</div>

	<s:hidden id="param_idFlusso" value="%{idFlusso}" theme="simple"/>
	<s:hidden id="initTable" name="init" theme="simple"/>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<h3>Visualizza flusso avvisi scaduti</h3>

				<%@ include file="../include/panel-flusso-avvisiscaduti.jsp"%>

				<br/>

				<table class="table table-hover tab_left dataTable" id="tabellaRisultati">
					<thead>
						<tr>
							<th>Id univoco versamento (IUV)</th>
							<th>Importo</th>
							<th>Data scadenza</th>
						</tr>
					</thead>
					<tbody/>
				</table>

				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a> /
					<s:a action="entry-ricerca-flussi-avvisiscaduti" cssClass="btn">RICERCA FLUSSI</s:a>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<script src="js/epaypaweb/visualizza-flusso-avvisiscaduti.js" type="text/javascript"></script>

</body>
</html>
