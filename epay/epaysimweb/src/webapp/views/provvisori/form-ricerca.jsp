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

		<h3><spring:message code="provvisori.ricercaprovvisori"/></h3>

		<div class="step-content" hidden="true">
			<div class="step-pane active" id="downloadPanel">
				<c:if test="${empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisori.formricerca.template"/>
						<span class="pull-right clickable"> <a
							href="#collapseFilterPanelDownloadTemplate"
							data-toggle="collapse" aria-expanded="true"
							data-parent="#downloadPanel" ><i
								class="glyphicon glyphicon-chevron-up"></i> <i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanelDownloadTemplate" class="collapse in">
				</c:if>
				<c:if test="${not empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisori.formricerca.template"/>
						<span class="pull-right clickable"> <a
							href="#collapseFilterPanelDownloadTemplate"
							data-toggle="collapse" aria-expanded="false"
							data-parent="#downloadPanel" class="anch"><i
								class="glyphicon glyphicon-chevron-up"></i> <i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanelDownloadTemplate" class="collapse">
				</c:if>
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-12 control-label"">
								<spring:message code="provvisori.formricerca.scaricatemplatelistaprovvisorisceltaformato"/> 
								</label>

								<div class="col-sm-12 controls">

									<div class="btn-toolbar pull-left">
										<a id="formButtonExportExcel" name="EsportaExcel"
											class="btn btn-default btn-action pull-right"
											href="${context}/provvisori/esporta-modello-xls"
											style="margin-right: 1em;"> <span
											class="fas fa-file-excel"></span> <spring:message code="provvisori.formricerca.scaricatemplateexcel"/> 
										</a> <a id="formButtonExportCsv" name="EsportaCsv"
											class="btn btn-default btn-action pull-right"
											href="${context}/provvisori/esporta-modello-csv"
											style="margin-right: 0em;"> <span class="fas fa-file-alt"></span>
											<spring:message code="provvisori.formricerca.scaricatemplatecsv"/>
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

		<div class="step-content">
			<div class="step-pane active" id="filterPanel">
				<c:if test="${empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisori.formricerca.filtroricerca"/> <span class="pull-right clickable"> <a
							href="#collapseFilterPanel" data-toggle="collapse"
							aria-expanded="true" data-parent="#filterPanel"><i
								class="glyphicon glyphicon-chevron-up"></i> <i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanel" class="collapse in">
				</c:if>
				<c:if test="${not empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="provvisori.formricerca.filtroricerca"/> <span class="pull-right clickable"> <a
							href="#collapseFilterPanel" data-toggle="collapse"
							aria-expanded="false" data-parent="#filterPanel"><i
								class="glyphicon glyphicon-chevron-up"></i> <i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanel" class="collapse">
				</c:if>
					<div class="row-fluid">
						<p><spring:message code="validation.search" /></p>
					</div>
				
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
									<spring:message code="provvisori.formricerca.identificativoflusso" /> 
								</label>
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
									<spring:message code="provvisori.formricerca.statoprovvisorio" /> </label>
								<div class="col-sm-10 controls">
									<form:select path="statoFlusso" class="form-control">
										<form:option value=""><spring:message code="provvisori.formricerca.qualsiasi" /></form:option>
										<form:option value="VALIDO"><spring:message code="provvisori.formricerca.valido" /></form:option>
										<form:option value="ANNULLATO"><spring:message code="provvisori.formricerca.annullato" /></form:option>
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
									<spring:message code="provvisori.formricerca.dataelaborazioneda" /> </label>
								<div class="col-sm-10 controls">
									<form:input id="dataElaborazioneDa" name="dataElaborazioneDa"
										path="dataElaborazioneDa" class="form-control datepicker"
										placeholder="data elaborazione da" />
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
									<spring:message code="provvisori.formricerca.dataelaborazionea" /> </label>
								<div class="col-sm-10 controls">
									<form:input id="dataElaborazioneA" name="dataElaborazioneA"
										path="dataElaborazioneA" class="form-control datepicker"
										placeholder="data elaborazione a" />
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
								href="${context}/provvisori/pulisci" class="btn btn-secondary"><spring:message code="button.cleanup" /></a>
							<input id="formSubmitButtonCerca" type="submit" value="Cerca"
								name="Cerca" class="btn btn-primary pull-right" />
						</p>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form:form>

