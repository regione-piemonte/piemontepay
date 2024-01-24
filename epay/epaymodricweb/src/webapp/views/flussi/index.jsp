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
				<li class="active"><spring:message code="flussi.index.ricercaflussi" /> <span class="caret"></li>
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
						id="entry-ricerca-provvisori"
						name="entry-ricerca-vocientrata"
						action="${context}/voci-entrata/ricerca" 
						method="post"
						class="" 
						modelAttribute="filtro_voci_entrata"
					>
						<fieldset>
				
							<div id="alert-div"></div>
				
							<h3><spring:message code="flussi.index.ricercaflussi" /></h3>
				
							<div class="step-content">
								<div class="step-pane active" id="filterPanel">
									<div class="accordion-heading">
										<h4>
											<spring:message code="flussi.index.filtrodiricerca" /><span class="pull-right clickable"> <a
												href="#collapseFilterPanel" data-toggle="collapse"
												aria-expanded="false" data-parent="#filterPanel"
												class="anch"><i
													class="glyphicon glyphicon-chevron-up"></i> <i
													class="glyphicon glyphicon-chevron-down"></i></a>
											</span>
										</h4>
									</div>
									<div id="collapseFilterPanel" class="collapse">
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
														<spring:message code="flussi.index.codice" />
													</label>
													<div class="col-sm-9 controls">
														<form:input path="codice" class="form-control" id="codice"
															name="codice" />
													</div>
												</div>
											</div>
										</div>
										
										<div class="container-fluid">
											<div class="row-fluid">
				
												<div class="form-group ">
													<label class="col-sm-2 control-label" for="descrizione">
														<spring:message code="flussi.index.descrizione" />
													</label>
													<div class="col-sm-9 controls">
														<form:input path="descrizione" class="form-control"
															id="descrizione" name="descrizione" />
													</div>
												</div>
											</div>
										</div>
				
				
										<div class="container-fluid">
											<div class="row-fluid">
												<div class="form-group ">
													<label class="col-sm-2 control-label"
														for="entry-ricerca-listedicarico_idCodVersamento">
														<spring:message code="flussi.index.tematica" />
													</label>
													<div class="col-sm-9 controls">
														<form:select path="codiceTematica" class="form-control">
															<form:option value="">-- Qualsiasi --</form:option>
															<form:options items="${modelTematiche}"
																itemLabel="descrizione" itemValue="codice" />
														</form:select>
				
													</div>
												</div>
											</div>
										</div>
												
										<div class="container-fluid">
											<div class="row-fluid">
				
												<div class="form-group ">
													<label class="col-sm-2 control-label"
														for="entry-ricerca-listedicarico_idCodVersamento">
														<spring:message code="flussi.index.macrotipo" />
													</label>
													<div class="col-sm-9 controls">
														<form:select path="codiceMacrotipo" class="form-control">
															<form:option value="">-- Qualsiasi --</form:option>
															<form:options items="${modelMacrotipi}"
																itemLabel="descrizione" itemValue="codice" />
														</form:select>
				
													</div>
												</div>
				
											</div>
										</div>
				
										<div class="row-fluid">
											<p class="margin-medium">
												<a id="formSubmitButtonPulisci" href="${context}/voci-entrata/pulisci"
													class="btn btn-secondary"><spring:message code="button.pulisci" /></a> 
													
												<input
													id="formSubmitButtonCerca" type="submit" value="Cerca"
													name="Cerca" class="btn btn-primary pull-right" />
											</p>
										</div>
									</div>
								</div>
							</div>
						</fieldset>
					</form:form>
					
					<div class="container-fluid">
						<div class="row">
						
							<c:if test="${lista_risultati_vuota}">
							<div class ="col-sm-12">
								<p class="alert alert-warning">
									<spring:message code="flussi.index.nessunrisultato" />
								</p>
							</div>
							</c:if>
							
							<c:if test ="${not empty lista_risultati}">
							<div class ="col-sm-12">
								<div class="row-fluid">
									<p class="margin-medium">
										<a
											id="formButtonExportCsv"
											name="EsportaCsv" 
											class="btn btn-default pull-right"
											href="${context}/voci-entrata/esporta-csv"
											style="margin-right: 0em;"
										>
											<span class="fas fa-file-alt"></span>
											<spring:message code="button.exportCSV" />
										</a>
										<a
											id="formButtonExportExcel"
											name="EsportaExcel" 
											class="btn btn-default pull-right"
											href="${context}/voci-entrata/esporta-excel"
											style="margin-right: 1em;"
										>
											<span class="fas fa-file-excel"></span>
											<spring:message code="button.exportXLS" />
										</a>
									</p>
								</div>
							</div>
							
							<div class ="col-sm-12">
								<table id="results" class="table table-hover tab_left dataTable no-footer" auto-data-table>
									<thead>
									<tr>
										<th><spring:message code="flussi.index.codice" /></th>
										<th><spring:message code="flussi.index.descrizione" /></th>
										<th><spring:message code="flussi.index.tematica" /></th>
										<th><spring:message code="flussi.index.macrotipo" /></th>
										<th><spring:message code="table.actions" /></th>
									</tr>
									</thead>
									<tbody>
										<c:forEach var="result" items="${lista_risultati}">
										<tr>
											<td data-key="codice"><c:out value="${result.codice}"/></td>
											<td data-key="descrizione"><c:out value="${result.descrizione}"/></td>
											<td data-key="descrizioneTematica"><c:out value="${result.descrizioneTematica}"/></td>
											<td data-key="descrizioneMacrotipo"><c:out value="${result.descrizioneMacrotipo}"/></td>
											<td data-key=""><div id="tableRowActions" class="btn-group">
												<button disabled class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
													<spring:message code="table.actions" /> <span class="caret"></span>
												</button>
												<ul class="dropdown-menu pull-right">
													<li><a href="#" id="visualizzaFlussoAction" data-form-id="visualizzaFlussoForm" data-row-id="195"><spring:message code="flussi.index.visualizzaflusso" /></a></li>
													<li><a href="#" id="eliminaFlussoAction" data-form-id="eliminaFlussoForm" data-row-id="195"><spring:message code="flussi.index.eliminaflusso" /></a></li>
													<li><a href="#" id="inviaFlussoAction" data-form-id="inviaFlussoForm" data-row-id="195"><spring:message code="flussi.index.inviaflusso" /></a></li>
												</ul>
												</div>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../include/portal-footer.jsp"%>
</body>
