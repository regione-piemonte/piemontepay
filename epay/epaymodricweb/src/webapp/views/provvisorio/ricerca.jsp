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
				<li class="active"><spring:message code="provvisorio.ricerca.ricercaprovvisori"/></li>
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
	<sec:authorize access="hasRole('INSERISCI_PROVVISORI_GUI')">
						 	<div class="col-sm-12"
									style="margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0px">

									<div class="btn-toolbar pull-left">
										<button id="btnUploadXlsProvvisorio" type="button"
											onclick="location.href='${context}/provvisori/importaxls'"
											value="InserisciXls" class="btn btn-primary pull-left">
											<spring:message code="button.importXLS"/>
										</button>
										<button id="btnUploadCsvProvvisorio" type="button"
											onclick="location.href='${context}/provvisori/importacsv'"
											value="InserisciCsv" class="btn btn-primary pull-left">
											<spring:message code="button.importCSV"/>
										</button>
										<button id="btnInserisciProvvisorio" type="button"
											onclick="location.href='${context}/provvisori/inserisci'"
											value="inserisci" class="btn btn-primary pull-left">
											<spring:message code="provvisorio.ricerca.inserisciprovvisorio"/>
										</button>
									</div>
								</div> 
</sec:authorize>			
							</c:if>
							
							<c:if test="${lista_risultati_vuota}">
								<div class="col-sm-12">
									<p class="alert alert-warning"><spring:message code="provvisorio.ricerca.nessunrisultato"/></p>
								</div>
							</c:if>

							<c:if test="${not empty lista_risultati}">
								<div class="col-sm-12"
									style="margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0px">

									<div class="btn-toolbar pull-right">
										<button id="btnDownloadXlsProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/esportaxls'"
											value="EsportaXls" class="btn btn-secondary pull-left">
											<span class="fas fa-file-excel"></span><spring:message code="button.exportXLS"/>
										</button>
										<button id="btnDownloadCsvProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/esportacsv'"
											value="EsportaCsv" class="btn btn-secondary pull-left">
											<span class="fas fa-file-alt"></span><spring:message code="button.exportCSV"/>
										</button>
									</div>
<!--
									<div class="btn-toolbar pull-left">
										<button id="btnUploadXlsProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/importaxls'"
											value="InserisciXls" class="btn btn-primary pull-left">
											<spring:message code="button.importXLS"/>
										</button>
										<button id="btnUploadCsvProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/importacsv'"
											value="InserisciCsv" class="btn btn-primary pull-left">
											<spring:message code="button.importCSV"/>
										</button>
										<button id="btnInserisciProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/inserisci'"
											value="inserisci" class="btn btn-primary pull-left">
											<spring:message code="provvisorio.ricerca.inserisciprovvisorio"/>
										</button>
									</div>
-->
								</div>
								
								<div class="col-sm-12">
									<!-- 									includi form risultati -->
									<%@ include file="tabella-risultati.jsp"%>
								</div>
								<div class="col-sm-12"
									style="margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0px">

									<div class="btn-toolbar pull-right">
										<button id="btnDownloadXlsProvvisorioBottom" type="button"
											onclick="location.href='${context}/provvisori/esportaxls'"
											value="EsportaXls" class="btn btn-secondary pull-left">
											<span class="fas fa-file-excel"></span><spring:message code="button.exportXLS"/>
										</button>
										<button id="btnDownloadCsvProvvisorioBottom" type="button"
											onclick="location.href='${context}/provvisori/esportacsv'"
											value="EsportaCsv" class="btn btn-secondary pull-left">
											<span class="fas fa-file-alt"></span><spring:message code="button.exportCSV"/>
										</button>
									</div>

	<sec:authorize access="hasRole('INSERISCI_PROVVISORI_GUI')"> 
									<div class="btn-toolbar pull-left">
										<button id="btnUploadXlsProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/importaxls'"
											value="InserisciXls" class="btn btn-primary pull-left">
											<spring:message code="button.importXLS"/>
										</button>
										<button id="btnUploadCsvProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/importacsv'"
											value="InserisciCsv" class="btn btn-primary pull-left">
											<spring:message code="button.importCSV"/>
										</button>
										<button id="btnInserisciProvvisorioTop" type="button"
											onclick="location.href='${context}/provvisori/inserisci'"
											value="inserisci" class="btn btn-primary pull-left">
											<spring:message code="provvisorio.ricerca.inserisciprovvisorio"/>
										</button>
									</div>
		</sec:authorize>							
									
									
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
