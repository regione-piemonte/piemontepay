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
				<li>
					<a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
				</li>
				<li class="active"><spring:message code="voceentrata.index.ricercavocippay" /></li>
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
	
					<form:form 
						id="entry-ricerca-vocientrata"
						name="entry-ricerca-vocientrata"
						action="${context}/voci-entrata/ricerca" 
						method="post"
						class="" 
						modelAttribute="filtro_voci_entrata"
					>
						<fieldset>
				
							<div id="alert-div"></div>
				
							<h3><spring:message code="voceentrata.index.ricercavocippay" /></h3>
				
							<div class="step-content">
								<div class="step-pane active" id="filterPanel">
									<c:if test ="${not empty lista_risultati}">
									<div class="accordion-heading">
										<h4>
											<spring:message code="filter.search" /> <span class="pull-right clickable"> <a
												href="#collapseFilterPanel" data-toggle="collapse"
												aria-expanded="false" data-parent="#filterPanel"
												class="anch"><i
													class="glyphicon glyphicon-chevron-up"></i> <i
													class="glyphicon glyphicon-chevron-down"></i></a>
											</span>
										</h4>
									</div>
									<div id="collapseFilterPanel" class="collapse">
									</c:if>
									<c:if test ="${empty lista_risultati}">
									<div class="accordion-heading">
										<h4>
											<spring:message code="filter.search" /> <span class="pull-right clickable"> <a
												href="#collapseFilterPanel" data-toggle="collapse"
												aria-expanded="true" data-parent="#filterPanel"
												class="anch"><i
													class="glyphicon glyphicon-chevron-up"></i> <i
													class="glyphicon glyphicon-chevron-down"></i></a>
											</span>
										</h4>
									</div>
									<div id="collapseFilterPanel" class="collapse in">
									</c:if>
										<div class="container-fluid">
											<div class="row-fluid">
												<div class="form-group ">
													<label class="col-sm-12 control-label">
													<form:errors path="id" cssClass="boxedError" /></label>    
												</div>
											</div>
										</div>
										
										<div class="container-fluid">
											<div class="row-fluid">
				
												<div class="form-group ">
													<label class="col-sm-2 control-label" for="codice">
														<spring:message code="voceentrata.index.codicevoceentrata" />
													</label>
													<div class="col-sm-10 controls">
														<form:input path="codice" class="form-control input-filtro-voce-entrata"
															style="margin-bottom: 10px;" />
													</div>
												</div>
											</div>
										</div>
										
										<div class="container-fluid">
											<div class="row-fluid">
				
												<div class="form-group ">
													<label class="col-sm-2 control-label" for="descrizione">
														<spring:message code="voceentrata.index.descrizionevoceentrata" />
													</label>
													<div class="col-sm-10 controls">
														<form:input path="descrizione" class="form-control input-filtro-voce-entrata"
															style="margin-bottom: 10px;" />
													</div>
												</div>
											</div>
										</div>
										
										<div class="container-fluid">
											<div class="row-fluid">
												<div class="form-group ">
													<label class="col-sm-2 control-label"
														for="entry-ricerca-listedicarico_idCodVersamento"><spring:message code="form.ricerca.tematica" />
													</label>
													<div class="col-sm-5 controls">
														<form:select path="codiceTematica" class="form-control input-filtro-tematica">
															<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
															<form:options items="${modelTematiche}"
																itemLabel="codiceDescrizione" itemValue="codice" />
														</form:select>
				
													</div>
													<label class="col-sm-1 control-label"
														for="entry-ricerca-listedicarico_idCodVersamento"><spring:message code="form.ricerca.macrotipo" />
													</label>
													<div class="col-sm-4 controls">
														<form:select path="codiceMacrotipo" class="form-control input-filtro-macrotipo">
															<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
															<form:options items="${modelMacrotipi}"
																itemLabel="codiceDescrizione" itemValue="codice" />
														</form:select>
				
													</div>
												</div>
				
											</div>
										</div>
				
										<div class="row-fluid">
											<p class="margin-medium">
												<a id="formSubmitButtonPulisci" href="${context}/voci-entrata/pulisci"
													class="btn btn-secondary btn-action"><spring:message code="button.cleanup" /></a> 

												<a class="btn btn-primary btn-action pull-right" 
													id="formSubmitButtonCerca"> <spring:message code="button.search" /></a>
											</p>
										</div>
									</div>
								</div>
							</div>
						</fieldset>
					</form:form>
					
					<div class="container-fluid">
						<div class="row mt-2">
						
							<c:if test="${lista_risultati_vuota}">
							<div class ="col-sm-12">
								<p class="alert alert-warning">
									<spring:message code="validation.noResult" />
								</p>
							</div>
							</c:if>
							
							<c:if test ="${not empty lista_risultati}">
							<div class ="col-sm-12">
								<table id="results" class="table table-hover tab_left dataTable no-footer" auto-data-table>
									<thead>
									<tr>
										<th><spring:message code="voceentrata" /></th>
										<th class="hidden-xs"><spring:message code="form.ricerca.tematica" /></th>
										<th class="hidden-xs"><spring:message code="form.ricerca.macrotipo" /></th>
									</tr>
									</thead>
									<tbody>
										<c:forEach var="result" items="${lista_risultati}">
										<tr>
											<td data-key="codice">
												<span class="hidden-xs">
													<c:out value="${result.codice} - ${result.descrizione}"/>
												</span>
												<span class="visible-xs-block">
													<c:out value="${result.codice} - ${result.descrizione}"/>
													
													<div style="text-align: right; margin-top: 0.5em;">
														<small>
															<c:out value="${result.codiceTematica} - ${result.descrizioneTematica}"/>
														</small>
														<br />
														<small>
															<c:out value="${result.codiceMacrotipo} - ${result.descrizioneMacrotipo}"/>
														</small>
													</div>
												</span>
											</td>
											<td class="hidden-xs" data-key="codiceTematica"><c:out 
												value="${result.codiceTematica} - ${result.descrizioneTematica}"/></td>
											<td class="hidden-xs" data-key="codiceMacrotipo"><c:out 
												value="${result.codiceMacrotipo} - ${result.descrizioneMacrotipo}"/></td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
							<div class ="col-sm-12">
								<div class="row-fluid">
									<p class="margin-medium">
										<a
											id="formButtonExportCsv"
											name="EsportaCsv" 
											class="btn btn-default btn-action pull-right"
											href="${context}/voci-entrata/esporta-csv"
											style="margin-right: 0em;"
										>
											<span class="fas fa-file-alt"></span>
											<spring:message code="esportafilecsv" />
										</a>
										<a
											id="formButtonExportExcel"
											name="EsportaExcel" 
											class="btn btn-default btn-action pull-right"
											href="${context}/voci-entrata/esporta-excel"
											style="margin-right: 1em;"
										>
											<span class="fas fa-file-excel"></span>
											<spring:message code="esportafileexcel" />
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
					<a id="buttonHome" class="btn btn-secondary btn-action" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a>
				</p>
			</div>
		</div>
	</div>
	<%@ include file="script.jsp"%>
	<%@ include file="../include/portal-footer.jsp"%>
	
</body>
