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
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
				</li>
				<li class="active"><spring:message code="home.flussi.errore.ricerca.page" /></li>
			</ul>
		</div>
	</div>

	<div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<!-- includi form di ricerca -->
					<%@ include file="form-ricerca.jsp"%>

					<div class="container-fluid">
						<div class="row">

							<c:if test="${empty lista_risultati}">
								<div class="col-sm-12"
									style="margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0px">

									<div class="btn-toolbar pull-right" hidden="true">
										<button id="btnUploadXlsProvvisorio" type="button"
											onclick="location.href='${context}/errori/importaxls'"
											value="InserisciXls" class="btn btn-primary pull-left">Carica
											da file EXCEL</button>
										<button id="btnUploadCsvProvvisorio" type="button"
											onclick="location.href='${context}/errori/importacsv'"
											value="InserisciCsv" class="btn btn-primary pull-left">Carica
											da file CSV</button>
										<button id="btnInserisciProvvisorio" type="button"
											onclick="location.href='${context}/errori/inserisci'"
											value="inserisci" class="btn btn-primary pull-left">Inserisci
											Provvisorio</button>
									</div>
								</div>
							</c:if>

							<c:if test="${lista_risultati_vuota}">
								<div class="col-sm-12">
									<p class="alert alert-warning">Nessun risultato
										corrispondente ai parametri di filtro impostati.</p>
								</div>
							</c:if>

							<c:if test="${not empty lista_risultati}">
							
                                <div class="col-sm-12">
                                    <div class="row-fluid">
                                        <p class="margin-medium">
                                            <a id="formButtonExportCsv" name="EsportaCsv" class="btn btn-default btn-action pull-right" href="${context}/errori/esportacsv" style="margin-right: 0em;"> <span class="fas fa-file-alt"></span>
                                                <spring:message code="button.exportCSV" />
                                            </a>
                                            <a id="formButtonExportExcel" name="EsportaExcel" class="btn btn-default btn-action pull-right" href="${context}/errori/esportaxls" style="margin-right: 1em;"> <span class="fas fa-file-excel"></span>
                                                <spring:message code="button.exportXLS" />
                                            </a>
                                        </p>
                                    </div>
                                </div>
								
								<div class="col-sm-12">
									<!-- includi form risultati -->
									<%@ include file="tabella-risultati.jsp"%>
								</div>
								
                                <div class="col-sm-12">
                                    <div class="row-fluid">
                                        <p class="margin-medium">
                                            <a id="formButtonExportCsv" name="EsportaCsv" class="btn btn-default btn-action pull-right" href="${context}/errori/esportacsv" style="margin-right: 0em;"> <span class="fas fa-file-alt"></span>
                                                <spring:message code="button.exportCSV" />
                                            </a>
                                            <a id="formButtonExportExcel" name="EsportaExcel" class="btn btn-default btn-action pull-right" href="${context}/errori/esportaxls" style="margin-right: 1em;"> <span class="fas fa-file-excel"></span>
                                                <spring:message code="button.exportXLS" />
                                            </a>
                                        </p>
                                    </div>
                                </div>
                                
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" class="btn btn-secondary" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a>
				</p>
			</div>
		</div>
	</div>
	<%@ include file="../include/portal-footer.jsp"%>
	<%@ include file="script-ricerca.jsp"%>
</body>
