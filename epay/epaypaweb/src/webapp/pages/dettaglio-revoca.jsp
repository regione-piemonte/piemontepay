<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../include/head.jsp"%>

<body
	onload="if (<s:property value='cursorClicked' />) location.hash = '#cursori'">

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
				<li><s:a action="show-main-menu">Home</s:a><span
					class="divider"></span></li>
				<li><s:a action="entry-ricerca-flussi-pagamenti">Ricerca Richieste di Revoca</s:a><span
					class="divider"></span></li>


				<li class="active">Dettaglio Richiesta di Revoca</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<h3>Dettaglio Richiesta di Revoca</h3>

				<div class="row-fluid">
					<label class="col-sm-8 control-label">Identificativo Messaggio Revoca:</label>
					<s:if test="singolaRevoca.idRr != null">
						<p class="col-sm-8 form-control-static">
							<s:property value="singolaRevoca.idRr" />
						</p>
					</s:if>
				</div>
				<div class="row-fluid">
					<label class="col-sm-8 control-label">Singolo importo revocato:</label>
					<s:if test="singolaRevoca.singoloImportoRevocato != null">
						<p class="col-sm-8 form-control-static">	 
							<s:property value="singolaRevoca.singoloImportoRevocato" />
						</p>
					</s:if>
				</div>
				<div class="row-fluid">
					<label class="col-sm-8 control-label">IUR:</label>
					<s:if test="singolaRevoca.iur != null">
						<p class="col-sm-8 form-control-static">
							<s:property value="singolaRevoca.iur" />
						</p>
					</s:if>
				</div>
				<div class="row-fluid">
					<label class="col-sm-8 control-label">Causale Revoca:</label>
					<s:if test="singolaRevoca.casualeRevoca != null">
						<p class="col-sm-8 form-control-static">
							<s:property value="singolaRevoca.casualeRevoca" />
						</p>
					</s:if>
				</div>
				<div class="row-fluid">
					<label class="col-sm-8 control-label">Dati aggiuntivi Revoca:</label>
					<s:if test="singolaRevoca.datiAggiuntiRevoca != null">
						<p class="col-sm-8 form-control-static">
							<s:property value="singolaRevoca.datiAggiuntiRevoca"/>
						</p>
					</s:if>
				</div>


				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a>

					<s:a action="entry-ricerca-richieste-revoca" cssClass="btn">RICERCA RICHIESTE DI REVOCA</s:a>

				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<script src="js/epaypaweb/dettaglio-pagamento.js"
		type="text/javascript"></script>

</body>
</html>
