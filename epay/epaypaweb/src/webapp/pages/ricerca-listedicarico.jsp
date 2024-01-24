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

	<%@ include file="../include/modal-confirm.jsp"%>
	<%@ include file="../include/modal-pleasewait.jsp"%>
	
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
				<li class="active">Ricerca liste di carico</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:set var="pageTitle">Ricerca liste di carico</s:set>
				<%@ include file="../include/panel-ricerca-listeaggposdeb-listedicarico.jsp"%>

				<div id="divTabellaRisultati" class="hide">
					<table class="table table-hover tab_left dataTable" id="tabellaRisultati">
						<thead>
							<tr>
								<th>Lotto</th>
								<th>Codice versamento</th>
								<th>Numero posizioni debitorie</th>
								<th>Importo totale</th>
								<th>Stato flusso</th>
								<th>Data</th>
								<th class="tab_Right span1">&nbsp;</th>
							</tr>
						</thead>
						<tbody/>
					</table>
				</div>

				<div class="margin-medium">
					<s:form action="entry-visualizza-listadicarico.do" method="POST" theme="simple">
						<s:a action="show-main-menu" cssClass="btn">HOME</s:a>
						<s:if test="isUseCaseEnabled('INS_LDC')">
							<s:hidden name="init" value="true"/>
							<s:hidden name="origineHomePerInserimento"/>
							<s:hidden name="operativita" value="INSERISCI"/>
							<s:submit value="Nuova lista di carico" cssClass="btn btn-primary pull-right"/>
						</s:if>
					</s:form>
				</div>
			</div>
		</div>
	</div>

	<div id="tableRowActions" class="btn-group hide">
		<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
			Azioni <span class="caret"></span>
		</button>
		<ul class="dropdown-menu pull-right">
			<s:if test="isUseCaseEnabled('DET_LDC')">
				<li><a id="visualizzaFlussoAction" data-form-id="visualizzaFlussoForm">visualizza flusso</a></li>
			</s:if>
			<s:if test="isUseCaseEnabled('INS_LDC')">
				<li><a id="modificaFlussoAction" data-form-id="modificaFlussoForm">modifica flusso</a></li>
			</s:if>
			<s:if test="isUseCaseEnabled('ELM_LDC')">
				<li><a id="eliminaFlussoAction" data-form-id="eliminaFlussoForm">elimina flusso</a></li>
			</s:if>
			<s:if test="isUseCaseEnabled('INS_LDC')">
				<li><a id="pubblicaFlussoAction" data-form-id="pubblicaFlussoForm">pubblica flusso</a></li>
			</s:if>
			<s:if test="isUseCaseEnabled('INV_LDC')">
				<li><a id="inviaFlussoAction" data-form-id="inviaFlussoForm">invia flusso</a></li>
			</s:if>
			<s:if test="isUseCaseEnabled('DLD_LDC')">
				<li>
					<a id="scaricaCSVAction" data-form-id="scaricaCSVForm">
						scarica
						<s:iterator value="sessionContext.formatiFile" var="item">
							<s:if test="#item.tipoFormatoFile == @it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum@CSV">
								<s:property value="#item.descrizione"/>
							</s:if>
						</s:iterator>
					</a>
				</li>
				<li>
					<a id="scaricaExcelAction" data-form-id="scaricaExcelForm">
						scarica
						<s:iterator value="sessionContext.formatiFile" var="item">
							<s:if test="#item.tipoFormatoFile == @it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum@XLSX">
								<s:property value="#item.descrizione"/>
							</s:if>
						</s:iterator>
					</a>
				</li>
			</s:if>
		</ul>
	</div>

	<s:form id="visualizzaFlussoForm" action="entry-visualizza-listadicarico.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="init" value="true"/>
		<s:hidden name="operativita" value="VISUALIZZA"/>
	</s:form>
	<s:form id="modificaFlussoForm" action="modifica-listadicarico.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="init" value="true"/>
		<s:hidden name="operativita" value="MODIFICA"/>
	</s:form>
	<s:form id="inviaFlussoForm" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
	</s:form>
	<s:form id="scaricaCSVForm" action="scarica-listadicarico.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="tipoFormato" value="CSV"/>
		<s:hidden name="pleaseWaitTokenValue"/>
	</s:form>
	<s:form id="scaricaExcelForm" action="scarica-listadicarico.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="tipoFormato" value="XLSX"/>
		<s:hidden name="pleaseWaitTokenValue"/>		
	</s:form>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
	<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>

	<script src="js/epaypaweb/common-functions-for-ricerca-flussi.js" type="text/javascript"></script>
	<script src="js/epaypaweb/ricerca-listedicarico.js" type="text/javascript"></script>

</body>
</html>
