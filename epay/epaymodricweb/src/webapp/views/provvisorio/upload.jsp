<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../include/head.jsp"%>

<body>
	<%@ include file="../include/portal-header.jsp"%>
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span></li>
				<li><a href="${context}/provvisori/ricerca">Ricerca Provvisori</a> <span class="divider"></span></li>
				<li class="active"><spring:message code="provvisorio.upload.uploadprovvisori"/></li>
			</ul>
		</div>
	</div>

	<form:form id="formUploadProvvisori" method="post" commandName="provvisori_file" enctype="multipart/form-data" action="${context}/provvisori/upload" class="form-horizontal" track-changes="true">

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<div class="container-fluid">
						<c:if test="${msgInfo != null}">
							<div class="stdMessagePanel">
								<div class="alert alert-success" role="alert">
									<pre class="preFromatClass">
										<c:out value="${msgInfo}"></c:out>
									</pre>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty listaErrori}">
							<fieldset>
								<div class="stdMessagePanel">
									<div class="alert alert-warning" role="error">
										<button type="button" class="close" data-dismiss="alert">x</button>
										<p>
											<span>
												<p>
													<spring:message code="provvisorio.upload.elaborazionedelfile"/>
													<c:out value="${nomeFile}">---</c:out>
													<spring:message code="provvisorio.upload.terminataconerrori"/>
												</p>

											</span>
										</p>
										<dl>
											<c:forEach var="result" items="${listaErrori}">
												<dd>
													<c:out value="${result}"></c:out>
												</dd>
											</c:forEach>
										</dl>
									</div>
								</div>
							</fieldset>
						</c:if>
						<c:if test="${empty listaErrori && not empty msgError}">
							<div class="alert alert-error" role="error">
								<button type="button" class="close" data-dismiss="alert">x</button>
								<p>
									<span>
										<p>
											<c:out value="${msgError}">---</c:out>
										</p>

									</span>
								</p>
							</div>
						</c:if>
						<div class="row"></div>

						<%@ include file="./form_upload_provvisori.jsp"%>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" href="${context}/provvisori/ricerca" class="btn btn-secondary"><spring:message code="button.indietro"/></a>
					<button id="btnInserisciProvvisorio" type="submit" value="Salva" class="btn btn-primary pull-right"><spring:message code="button.save"/></button>
				</p>
			</div>
		</div>
	</form:form>
	<%@ include file="../include/portal-footer.jsp"%>
	<%@ include file="script-upload.jsp"%>
</body>
