<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<form:form id="filtro_ricerca_provvisori"
	name="filtro_ricerca_provvisori" action="${context}/provvisori/ricerca"
	method="post" class="" modelAttribute="filtro_ricerca_provvisori">
	<fieldset>

		<div id="alert-div"></div>

		<h3><spring:message code="provvisorio.formricerca.ricercaprovvisori"/></h3>

	<!-- 	<div class="sterp-content" hidden="true">
			<div class="step-pane active" id="downloadPanel">
				<c:if test="${not empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisorio.formricerca.template"/><span class="pull-right clickable"> 
						<a
							href="#collapseFilterPanelDownloadTemplate"
							data-toggle="collapse" aria-expanded="false"
							data-parent="#downloadPanel" class="anch"><i
								class="glyphicon glyphicon-chevron-up" style="color: black"></i> <i
								class="glyphicon glyphicon-chevron-down" style="color: black"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanelDownloadTemplate" class="collapse">
				</c:if>
				<c:if test="${empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisorio.formricerca.template"/><span class="pull-right clickable"> 
						<a
							href="#collapseFilterPanelDownloadTemplate"
							data-toggle="collapse" aria-expanded="true"
							data-parent="#downloadPanel" class="anch"><i
								class="glyphicon glyphicon-chevron-up" style="color: black"></i> <i
								class="glyphicon glyphicon-chevron-down" style="color: black"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanelDownloadTemplate" class="collapse in">
				</c:if>
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-12 control-label""><spring:message code="provvisorio.formricerca.scaricatemplate"/></label>

								<div class="col-sm-12 controls">

									<div class="btn-toolbar pull-left">
										<a id="formButtonExportExcel" name="EsportaExcel"
											class="btn btn-default btn-action pull-right"
											href="${context}/provvisori/esportamodelloxls"
											style="margin-right: 1em;"><spring:message code="button.exportXLS"/>
										</a> <a id="formButtonExportCsv" name="EsportaCsv"
											class="btn btn-default btn-action pull-right"
											href="${context}/provvisori/esportamodellocsv"
											style="margin-right: 0em;">
											<spring:message code="button.exportCSV"/>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<br>
 -->
		<div class="step-content">
			<div class="step-pane active" id="filterPanel">
		
	<!-- 	
			<c:if test="${not empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisorio.formricerca.template"/><span class="pull-right clickable"> 
						<a
							href="#collapseFilterPanelDownloadTemplate"
							data-toggle="collapse" aria-expanded="false"
							data-parent="#downloadPanel" class="anch"><i
								class="glyphicon glyphicon-chevron-up" style="color: black"></i> <i
								class="glyphicon glyphicon-chevron-down" style="color: black"></i></a>
						</span>
					</h4>
				</div>
				
				<div id="collapseFilterPanel" class="collapse">
				</c:if>
		 -->		
				
				<c:if test="${empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisorio.formricerca.filtrodiricerca"/><span class="pull-right clickable"> <a
							href="#collapseFilterPanel" data-toggle="collapse"
							aria-expanded="true" data-parent="#filterPanel" class="anch"><i
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
								<label class="col-sm-12 control-label"> <form:errors
										path="validitaGenerica" cssClass="boxedError" /></label>
							</div>
						</div>
					</div>

					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="identificativoFlusso">
									<spring:message code="provvisorio.formricerca.identificativoflusso"/></label>
								<div class="col-sm-10 controls">
									<form:input id="identificativoFlusso"
										name="identificativoFlusso" path="identificativoFlusso"
										class="form-control" placeholder="identificativo flusso" />
								</div>
							</div>
						</div>
					</div>

					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group ">
								<label class="col-sm-2 control-label" for="statoFlusso">
									<spring:message code="provvisorio.formricerca.statoprovvisorio"/></label>
								<div class="col-sm-10 controls">
									<form:select path="statoFlusso" class="form-control">
										<form:option value="">-- Qualsiasi --</form:option>
										<form:option value="VALIDO">VALIDO</form:option>
										<form:option value="ANNULLATO">ANNULLATO</form:option>
									</form:select>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="statoFlusso" cssClass="boxedError" />
									</label>
								</div>
							</div>
						</div>
					</div>

					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for=dataElaborazioneDa>
									<spring:message code="provvisorio.formricerca.datamovimentoda"/></label>
								<div class="col-sm-10 controls">
									<form:input id="dataElaborazioneDa" name="dataElaborazioneDa"
										path="dataElaborazioneDa" class="form-control datepicker"
										placeholder="data movimento da" />
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="dataElaborazioneDa" cssClass="boxedError" />
									</label>
								</div>
							</div>
						</div>
					</div>

					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for=dataElaborazioneA>
									<spring:message code="provvisorio.formricerca.datamovimentoa"/></label>
								<div class="col-sm-10 controls">
									<form:input id="dataElaborazioneA" name="dataElaborazioneA"
										path="dataElaborazioneA" class="form-control datepicker"
										placeholder="data movimento a" />
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="dataElaborazioneA" cssClass="boxedError" />
									</label>
								</div>
							</div>
						</div>
					</div>

					<div class="row-fluid">
						<p class="margin-medium">
							<a id="formSubmitButtonPulisci"
								href="${context}/provvisori/pulisci" class="btn btn-secondary"><spring:message code="button.pulisci"/></a>
							<input id="formSubmitButtonCerca" type="submit"
								name="Cerca" value=<spring:message code="button.search"/> class="btn btn-primary pull-right">
							</input>
						</p>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form:form>

