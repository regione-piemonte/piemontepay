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
				<li><s:a action="entry-inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step1.do">Inserisci lista aggiornamento posizioni debitorie da file (Passo 1 di 2)</s:a><span class="divider"></span></li>
				<li class="active">Inserisci lista aggiornamento posizioni debitorie da file (Passo 2 di 2)</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:form method="POST" enctype="multipart/form-data" action="elabora-flusso-posizionidebitoriedaaggiornare" theme="bootstrap" cssClass="form-horizontal">
					<div id="alert-div"></div>

					<%@ include file="../include/validation-message.jsp"%>

					<h3>Inserisci lista aggiornamento posizioni debitorie da file (Passo 2 di 2)</h3>

					<div class="step-content form-horizontal">
						<div class="step-pane active">
							<h4>Parametri</h4>
	
							<div class="container-fluid">
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Codice versamento:</label>
									<p class="col-sm-9 form-control-static">
										<s:iterator value="sessionContext.codiciVersamentoListeDiCarico" var="item">
											<s:if test="#item.id == idCodVersamento">
												<s:property value="#item.cod"/> - <s:property value="#item.des"/>
											</s:if>
										</s:iterator>
									</p>
									<s:hidden name="idCodVersamento"/>
								</div>

								<div class="row-fluid">
									<label class="col-sm-3 control-label">Multibeneficiario:</label>
									<p class="col-sm-9 form-control-static">
										<s:property value="multibeneficiario"/>
									</p>
									<s:hidden name="multibeneficiario"/>
								</div>

								<div class="row-fluid">
									<label class="col-sm-3 control-label">Numero posizioni debitorie:</label>
									<p class="col-sm-9 form-control-static"><s:property value="numeroPosizioniDebitorie"/></p>
									<s:hidden name="numeroPosizioniDebitorie" value="%{numeroPosizioniDebitorie}"/>
								</div>

								<div class="row-fluid">
									<label class="col-sm-3 control-label">Formato file:</label>
									<p class="col-sm-9 form-control-static">
										<s:property value="descrizioneTipoFormatoFile"/>
									</p>
									<s:hidden name="tipoFormato"/>
								</div>
							</div>
						</div>
					</div>
					
					<br/>

					<div class="step-content form-horizontal">
						<div class="step-pane active">
							<h4>File da elaborare</h4>
							
							<div class="container-fluid">
								<div class="row-fluid">
									<s:file name="file" label="Scegli il file" cssClass="margin-bottom-button-file-upload" />
								</div>
							</div>
						</div>
					</div>
	
					<div class="margin-medium">
						<s:a action="show-main-menu" cssClass="btn">HOME</s:a> /
						<s:a action="entry-inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step1" cssClass="btn">INSERISCI PARAMETRI</s:a>
						<a class="btn btn-primary pull-right" id="formSubmitButtonEsegui">Esegui</a>
					</div>
				</s:form>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<s:hidden name="executeElaborazione" id="executeElaborazione"/>
	<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
	<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>
	
	<script src="js/epaypaweb/inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step2.js" type="text/javascript"></script>

</body>
</html>
