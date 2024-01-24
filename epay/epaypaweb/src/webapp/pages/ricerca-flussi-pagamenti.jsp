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
				<li class="active">Ricerca flussi notifiche di pagamento</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:form theme="bootstrap" cssClass="form-horizontal">
					<div id="alert-div"></div>

					<h3>Ricerca flussi notifiche di pagamento</h3>

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
									<div class="row-fluid" style="padding-bottom:13px">
										<s:radio name="idTipoPagamento" label="Tipo pagamento"
											labelposition="inline" labelCssClass="col-sm-2"
											elementCssClass="col-sm-4" list="tipoPagamentoList" listKey="id"
											listValue="option" />
									</div>

									<div class="row-fluid">
										<s:select name="idCodVersamento"
											label="Codice versamento" labelCssClass="col-sm-2"
											list="sessionContext.codiciVersamento" listKey="id"
											listValue="codDes" headerKey="" headerValue="Scegli" />
									</div>

									<div class="row-fluid">
										<s:textfield id="dataRicezioneInizio" name="dataRicezioneInizio" label="Data ricezione inizio"
											labelCssClass="col-sm-2" cssClass="span2 datepicker" />
									</div>

									<div class="row-fluid">
										<s:textfield id="dataRicezioneFine" name="dataRicezioneFine"
											label="Data ricezione fine" labelCssClass="col-sm-2" cssClass="span2 datepicker" />
									</div>
									
									<!-- ------------------------------ -->
									<!-- RDI-048 - START -->
									<!-- ------------------------------ -->
									<div class="row-fluid">
										<s:textfield id="cognome" name="cognome"
											label="Cognome" labelCssClass="col-sm-2" cssClass="span9" />
									</div>
									<div class="row-fluid">
										<s:textfield id="iuv" name="iuv"
											label="IUV" labelCssClass="col-sm-2" cssClass="span4" />
									</div>
									<!-- ------------------------------ -->
									<!-- RDI-048 - STOP -->
									<!-- ------------------------------ -->
									
								</div>
	
								<div class="row-fluid">
									<div class="margin-medium">
										<a class="btn" id="formSubmitButtonPulisci">Pulisci</a>
										<a class="btn btn-primary pull-right" id="formSubmitButtonCerca">Cerca</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</s:form>

				<br/>

				<s:hidden id="param_idTipoPagamento" value="%{idTipoPagamento}" theme="simple"/>
				<s:hidden id="param_idCodVersamento" value="%{idCodVersamento}" theme="simple"/>
				<s:hidden id="param_dataRicezioneInizio" value="%{dataRicezioneInizio}" theme="simple"/>
				<s:hidden id="param_dataRicezioneFine" value="%{dataRicezioneFine}" theme="simple"/>

				<!-- ------------------------------ -->
				<!-- RDI-048 - START -->
				<!-- ------------------------------ -->	
				<s:hidden id="param_cognome" value="%{cognome}" theme="simple"/>
				<s:hidden id="param_iuv"     value="%{iuv}"     theme="simple"/>
				<!-- ------------------------------ -->
				<!-- RDI-048 - STOP -->
				<!-- ------------------------------ -->	
											
				<s:hidden id="initTable" name="init" theme="simple"/>
				<s:hidden id="executeSearch" name="executeSearch" theme="simple"/>

				<div id="divTabellaRisultati" class="hide">
					<table class="table table-hover tab_left dataTable" id="tabellaRisultati">
						<thead>
							<tr>
								<!-- ------------------------------ -->
								<!-- RDI-048 - START -->
								<!-- ------------------------------ -->								
								<th>Posizione debitoria</th>
								<th>Id univoco versamento (IUV)</th>
								<th>Importo pagato</th>
								<th>Data esito pagamento</th>
								<th>Descrizione causale versamento</th>
								<th>Codice fiscale/Partita Iva (Soggetto debitore)</th>
								<th>Cognome e Nome/Ragione sociale (Soggetto debitore)</th>
								<!-- ------------------------------ -->
								<!-- RDI-048 - STOP -->
								<!-- ------------------------------ -->								
<!-- 								<th>Lotto</th> -->
								<th>Codice versamento</th>
<!-- 								<th>Numero versamenti</th> -->
<!-- 								<th>Importo totale</th> -->
<!-- 								<th>Tipo pagamento</th> -->
								<th>Data ricezione</th>
								<!-- CSI_PAG-184 -->
								<th>Revoca</th>
								<!-- CSI_PAG-184 end-->
								<th class="tab_Right span1">&nbsp;</th>
							</tr>
						</thead>
						<tbody/>
					</table>
				</div>

				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a>
				</div>
			</div>
		</div>
	</div>

	<div id="tableRowActions" class="btn-group hide">
		<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
			Azioni <span class="caret"></span>
		</button>
		<ul class="dropdown-menu pull-right">
			<!-- ------------------------------ -->
			<!-- RDI-048 - START -->
			<!-- ------------------------------ -->					
			<!-- 
			<s:if test="isUseCaseEnabled('DET_ESP')">
				<li><a id="visualizzaFlussoAction" data-form-id="visualizzaFlussoForm">visualizza flusso</a></li>
			</s:if>
			-->
			<!-- ------------------------------ -->
			<!-- RDI-048 - STOP -->
			<!-- ------------------------------ -->
	
			<s:if test="isUseCaseEnabled('DET_ESP')">
				<li><a id="visualizzaFlussoDettaglioAction" data-form-id="visualizzaFlussoDettaglioForm">dettaglio</a></li>
			</s:if>
			
			<s:if test="isUseCaseEnabled('DLD_ESP')">
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

	<!-- ------------------------------ -->
	<!-- RDI-048 - START -->
	<!-- ------------------------------ -->					
	<!-- 
	<s:form id="visualizzaFlussoForm" action="entry-visualizza-flusso-pagamenti.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="init" value="true"/>
	</s:form>
	 -->
	<s:form id="visualizzaFlussoDettaglioForm" action="entry-dettaglio-pagamento.do" method="POST" theme="simple">
		<s:hidden name="idNotificaPagamento"/>
	</s:form>
	<!-- ------------------------------ -->
	<!-- RDI-048 - STOP -->
	<!-- ------------------------------ -->
	
	<s:form id="scaricaCSVForm" action="scarica-flusso-pagamenti.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="tipoFormato" value="CSV"/>
		<s:hidden name="pleaseWaitTokenValue"/>
	</s:form>
	
	<s:form id="scaricaExcelForm" action="scarica-flusso-pagamenti.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="tipoFormato" value="XLSX"/>
		<s:hidden name="pleaseWaitTokenValue"/>
	</s:form>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>
	
	<script src="js/epaypaweb/common-functions-for-ricerca-flussi.js" type="text/javascript"></script>
	<script src="js/epaypaweb/ricerca-flussi-pagamenti.js" type="text/javascript"></script>

</body>
</html>
