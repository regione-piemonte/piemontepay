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
				<li class="active">Prenota report flussi base</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:form theme="bootstrap" cssClass="form-horizontal">
					<div id="alert-div"></div>

					<h3>Prenota report flussi base</h3>

					<div class="step-content">
						<div class="step-pane active" id="filterPanel">
							<div class="accordion-heading">
								<h4>
									Filtro di ricerca
									<span class="pull-right clickable">
										<s:a
											data-toggle="collapse" href="#collapseFilterPanel"
											data-parent="#filterPanel"
											aria-expanded="true">
											<i class="glyphicon glyphicon-chevron-up"></i>
											<i class="glyphicon glyphicon-chevron-down"></i>
										</s:a>
									</span>
								</h4>
							</div>
							<div id="collapseFilterPanel" class="collapse in">
								<div class="row-fluid">
									<p><i>&Egrave; necessario inserire almeno un criterio di ricerca.</i></p>
								</div>

								<div class="container-fluid">
									<div class="row-fluid">
										<s:textfield id="nomeReport" name="nomeReport"
											label="Nome report" labelCssClass="col-sm-2" cssClass="span9" />
									</div>
									<div class="row-fluid">
										<s:textfield id="dataRicezioneInizio" name="dataRicezioneInizio" label="Data ricezione inizio"
											labelCssClass="col-sm-2" cssClass="span2 datepicker" />
									</div>

									<div class="row-fluid">
										<s:textfield id="dataRicezioneFine" name="dataRicezioneFine"
											label="Data ricezione fine" labelCssClass="col-sm-2" cssClass="span2 datepicker" />
									</div>
									
									<div class="row-fluid">
										<s:textfield id="codiceDescrizionePSP" name="codiceDescrizionePSP"
											label="PSP" labelCssClass="col-sm-2" cssClass="span2" placeholder="PSP (codice o descrizione)"/>
									</div>

									
								</div>
	
								<div class="row-fluid">
									<div class="margin-medium">
										<a class="btn" id="formSubmitButtonPulisci">Pulisci</a>
										<a class="btn btn-primary pull-right" id="formSubmitButtonCerca">Prenota</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</s:form>

				<br/>
				<s:hidden id="param_nomeReport" value="%{nomeReport}" theme="simple"/>
				<s:hidden id="param_dataRicezioneInizio" value="%{dataRicezioneInizio}" theme="simple"/>
				<s:hidden id="param_dataRicezioneFine" value="%{dataRicezioneFine}" theme="simple"/>
				<s:hidden id="param_codiceDescrizionePSP" value="%{codiceDescrizionePSP}" theme="simple"/>
				<s:hidden id="param_tipoFormato" value="%{tipoFormato}" theme="simple"/>
				<s:hidden id="initTable" name="init" theme="simple"/>
				<s:hidden id="executeSearch" name="executeSearch" theme="simple"/>
				
				<div id="divTabellaRisultati" class="hide">
					<table class="table table-hover tab_left dataTable" id="tabellaRisultati" style="width:100%">
						<thead>
							<tr>
								<th>Nominativo export</th>
								<th>Stato report</th>
								<th>Nome file</th>
								<th>Data prenotazione export</th>
								<th>Data ultima modifica</th>
								<th class="tab_Right span1">&nbsp;</th>
							</tr>
						</thead>
						<tbody/>
					</table>
					<div id="tableRowActions" class="btn-group hide">
						<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
							Azioni <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right">
					
							<s:if test="isUseCaseEnabled('RIC_FR')">
								<li><a id="scaricaAction" data-form-id="scaricaForm">Scarica report</a></li>
							</s:if>
							
						</ul>
						<s:form id="scaricaForm" action="scarica-flussi-base.do" method="POST" theme="simple">
							<s:hidden name="idFile"/>
							<s:hidden name="pleaseWaitTokenValue"/>	
						</s:form>
					</div>		
				</div>
				</div>
				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a>
				</div>
				
			</div>
		</div>

				
	

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>
	
	<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
	<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>
	
	<script src="js/epaypaweb/common-functions-for-ricerca-flussi.js" type="text/javascript"></script>
	<script src="js/epaypaweb/prenota-ricerca-report-flussi-base.js" type="text/javascript"></script>

</body>
</html>
