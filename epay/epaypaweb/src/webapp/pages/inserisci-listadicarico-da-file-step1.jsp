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

	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><s:a action="show-main-menu">Home</s:a><span class="divider"></span></li>
				<li class="active">Inserisci lista di carico da file (Passo 1 di 2)</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:form method="POST" action="inserisci-listadicarico-da-file-step1" theme="bootstrap" cssClass="form-horizontal">
					<%@ include file="../include/validation-message.jsp"%>

					<h3>Inserisci lista di carico da file (Passo 1 di 2)</h3>

					<div class="step-content">
						<div class="step-pane active" id="templateDownloadPanel">
							<div class="accordion-heading">
								<h4>
									Template
									<span class="pull-right clickable">
										<s:a
											data-toggle="collapse" href="#collapseTemplateDownloadPanel"
											data-parent="#templateDownloadPanel"
											aria-expanded="true">
											<i class="glyphicon glyphicon-chevron-up"></i>
											<i class="glyphicon glyphicon-chevron-down"></i>
										</s:a>
									</span>
								</h4>
							</div>
							<div id="collapseTemplateDownloadPanel" class="collapse in">
								<div class="row-fluid">
									<p>Scarica il template per preparare la lista di carico, scegli il formato.</p>
									<p class="margin-medium">
										<s:a action="download-template" cssClass="btn">
											Scarica template
											<s:iterator value="sessionContext.formatiFile" var="item">
												<s:if test="#item.tipoFormatoFile == @it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum@CSV">
													<s:property value="#item.descrizione"/>
												</s:if>
											</s:iterator>
											<s:param name="templateProperty">template.listadicarico.filename.csv</s:param>
											<s:param name="tipoFormato">CSV</s:param>
										</s:a>
										<s:a action="download-template" cssClass="btn">
											Scarica template
											<s:iterator value="sessionContext.formatiFile" var="item">
												<s:if test="#item.tipoFormatoFile == @it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum@XLSX">
													<s:property value="#item.descrizione"/>
												</s:if>
											</s:iterator>
											<s:param name="templateProperty">template.listadicarico.filename.xslx</s:param>
											<s:param name="tipoFormato">XLSX</s:param>
										</s:a>
									</p>
								</div>
							</div>
						</div>
					</div>

					<br/>
							
					<div class="step-content">
						<div class="step-pane active">
							<h4>Parametri</h4>

							<div class="row-fluid">
								<p>Tutti i parametri sono obbligatori.</p>
							</div>

							<div class="container-fluid">
								<div class="row-fluid">
									<s:select name="idCodVersamento"
										label="Codice versamento" labelCssClass="col-sm-3" cssClass="span10"
										list="codiciVersamentoListeDiCarico" listKey="id"
										listValue="codDes" headerKey="" headerValue="Scegli" />
								</div>

								<div class="row-fluid">
									<s:textfield name="strDataInizioValidita" 
										label="Data inizio validità" labelCssClass="col-sm-3" cssClass="span2 datepicker" />
								</div>

								<div class="row-fluid">
									<s:textfield name="strDataFineValidita"
										label="Data fine validità" labelCssClass="col-sm-3" cssClass="span2 datepicker" />
								</div>

								<div class="row-fluid">
									<s:textfield name="strNumeroPosizioniDebitorie" maxlength="4"
										label="Numero posizioni debitorie" labelCssClass="col-sm-3" cssClass="span2" />
								</div>

								<div class="row-fluid">
									<s:textfield name="strTotaleImportoPosizioniDebitorie" maxlength="12"
										label="Totale importo posizioni debitorie" labelCssClass="col-sm-3" cssClass="span2" />
								</div>
							</div>

							<div class="container-fluid">
								<s:radio list="sessionContext.formatiFile" listKey="tipoFormatoFile" listValue="descrizione" name="tipoFormato"
									label="Formato file" labelposition="inline" labelCssClass="col-sm-3"/>
							</div>

							<div class="row-fluid">
								<p class="margin-medium">
									<s:a action="entry-inserisci-listadicarico-da-file-step1" cssClass="btn" id="formSubmitButtonPulisci">
										Pulisci
										<s:param name="init" value="true"/>
									</s:a>
								</p>
							</div>
						</div>
					</div>

					<div class="margin-medium">
						<s:a action="show-main-menu" cssClass="btn">HOME</s:a>
						<s:submit value="Prosegui" cssClass="btn btn-primary pull-right"/>
					</div>
				</s:form>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>
	
	<script src="js/epaypaweb/inserisci-listadicarico-da-file-step1.js" type="text/javascript"></script>

</body>
</html>