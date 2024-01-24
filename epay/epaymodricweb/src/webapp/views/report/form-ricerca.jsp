<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<form:form 
	id="filtro_prenotazione_report"
	name="filtro_prenotazione_report"
	action="${context}/report/prenota-report-ricerca-flussi" 
	method="post"
	class="" 
	modelAttribute="filtro_prenotazione_report"
>
	<fieldset>

		<div id="alert-div"></div>

		<h3><spring:message code="flusso.formricerca.prenotareport" /></h3>

		<div class="step-content">
			<div class="step-pane active" id="filterPanel">
			
				<c:if test="${empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="flusso.formricerca.filtrodiricerca" /><span class="pull-right clickable"> 
						<a id="anch"
							href="#collapseFilterPanel" data-toggle="collapse"
							aria-expanded="true" data-parent="#filterPanel" 
							class="anch"
							><i
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
						<spring:message code="flusso.formricerca.filtrodiricerca" /><span class="pull-right clickable"> 
						<a id="anch"
							href="#collapseFilterPanel" data-toggle="collapse"
							aria-expanded="false" data-parent="#filterPanel"
							class="anch" 
							><i
								class="glyphicon glyphicon-chevron-up"></i> <i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanel" class="collapse">	
				</c:if>
				
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group ">
								<label class="col-sm-12 control-label">
								<form:errors path="validitaGenerica" cssClass="boxedError" /></label>
							</div>
						</div>
					</div>
					
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="nomeReport">
									<spring:message code="flusso.formricerca.nomereport" />
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="nomeReport"
										name="nomeReport"
										path="nomeReport" 
										class="form-control" 
									/>
								</div>
									<div class="col-sm-2"></div>
									<div class="col-sm-10">
										<form:errors path="nomeReport" cssClass="boxedError" /></label>
									</div>
							</div>
						</div>
					</div>
					
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="identificativoFlusso">
									<spring:message code="flusso.formricerca.identificativoflusso" />
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="identificativoFlusso"
										name="identificativoFlusso"
										path="identificativoFlusso" 
										class="form-control" 
										placeholder="identificativo flusso o causale del provvisorio"
									/>
								</div>
							</div>
						</div>
					</div>
					
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="iuv">
									<spring:message code="flusso.formricerca.iuv" />
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="iuv"
										name="iuv"
										path="iuv" 
										class="form-control" 
									/>
								</div>
							</div>
						</div>
					</div>
					
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="idCodVersamento">
									<spring:message code="flusso.formricerca.codiceversamento" />
								</label>
								<div class="col-sm-10 controls">
									<form:select path="idCodVersamento" class="form-control">
									<form:option value="">-- Qualsiasi --</form:option>
									<form:options items="${opzioni_codici_versamento}"
										itemLabel="descrizione" itemValue="codice" />
								</form:select>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="idCodVersamento" cssClass="boxedError" /></label>
								</div>	
							</div>
						</div>
					</div>
					
					
									
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group ">
								<label class="col-sm-2 control-label" for="statoFlusso">
									<spring:message code="flusso.formricerca.statoflusso" />
								</label>
								<div class="col-sm-10 controls">
									<form:select path="statoFlusso" class="form-control">
										<form:option value="">-- Qualsiasi --</form:option>
										<form:options items="${opzioni_stato_flusso}"
											itemLabel="descrizione" itemValue="id" />
									</form:select>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="statoFlusso" cssClass="boxedError" /></label>
								</div>
							</div>
						</div>
					</div>
				<div class="container-fluid">
					<div class="row-fluid">	
						<div class="form-group">
							<label class="col-sm-2 control-label" for=dataElaborazioneDa>
								<spring:message code="flusso.formricerca.dataelaborazioneda" />
							</label>
							<div class="col-sm-4 controls">
								<form:input 
									id="dataElaborazioneDa"
									name="dataElaborazioneDa"
									path="dataElaborazioneDa" 
									class="form-control datepicker" 
									placeholder="data elaborazione da"
								/>
							</div>
							
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for=dataElaborazioneA>
								<spring:message code="flusso.formricerca.dataelaborazionea" />
							</label>
							<div class="col-sm-4 controls">
								<form:input 
									id="dataElaborazioneA"
									name="dataElaborazioneA"
									path="dataElaborazioneA" 
									class="form-control datepicker" 
									placeholder="data elaborazione a"
								/>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="col-sm-2"></div>
						<div class="col-sm-4">
							<form:errors path="dataElaborazioneDa" cssClass="boxedError" /></label>
						</div>
						<div class="col-sm-2"></div>
						<div class="col-sm-4">
							<form:errors path="dataElaborazioneA" cssClass="boxedError" /></label>
						</div>
					</div>
				</div>
			
					
				<div class="container-fluid" >
					<div class="row-fluid">
									<div class="form-group">
										<label class="col-sm-2 control-label" for=dataRicezioneDa>
											<spring:message code="flusso.formricerca.dataricezioneda" />
										</label>
										<div class="col-sm-4 controls">
											<form:input 
												id="dataRicezioneDa"
												name="dataRicezioneDa"
												path="dataRicezioneDa" 
												class="form-control datepicker" 
												placeholder="data ricezione da"
											/>
										</div>
										
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for=dataRicezioneA>
											<spring:message code="flusso.formricerca.dataricezionea" />
										</label>
										<div class="col-sm-4 controls">
											<form:input 
												id="dataRicezioneA"
												name="dataRicezioneA"
												path="dataRicezioneA" 
												class="form-control datepicker" 
												placeholder="data ricezione a"
											/>
										</div>
										</div>
										<div class="row-fluid">
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<form:errors path="dataRicezioneDa" cssClass="boxedError" /></label>
										</div>
										<div class="col-sm-2"></div>
										<div class="col-sm-4">
											<form:errors path="dataRicezioneA" cssClass="boxedError" /></label>
										</div>
									</div>
								</div>
							</div>
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="psp">
									<spring:message code="flusso.formricerca.psp" />
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="psp"
										name="psp"
										path="psp" 
										class="form-control" 
										placeholder="PSP (codice o descrizione)"
									/>
								</div>
							</div>
						</div>
						</div>
<%-- 					<div class="container-fluid" > -->
<!-- 						<div class="row-fluid" > -->
<!-- 							<div class="form-group"> -->
<!-- 								<div class="col-sm-2"></div> -->
<!-- 								<div class="col-sm-2"> -->
<!-- 									<div class="radio"> -->
<!-- 										<label> -->
<!-- 											<form:radiobutton -->
<!-- 												id="reportContabile" -->
<!-- 												name="reportContabile" -->
<!-- 												path="tipoReport" -->
<-- 												value="${tipo_report_contabile}" --%>
												
<%-- 											/> -->
<!-- 											<spring:message code="report.formprenotazione.reportcontabile"/> -->
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-sm-8"> -->
<!-- 									<div class="radio"> -->
<!-- 										<label> -->
<!-- 											<form:radiobutton -->
<!-- 												id="reportFlussiCompleti" -->
<!-- 												name="reportFlussiCompleti" -->
<!-- 												path="tipoReport" -->
<-- 												value="${tipo_report_flussi_completi}" --%>
<!-- 											/> -->
<!-- 											<spring:message code="report.formprenotazione.reportflussicompleti"/> -->
<!-- 										</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 								<div class="col-sm-2"></div> -->
<!-- 								<div class="col-sm-10"> -->
<!-- 									<form:errors path="tipoReport" cssClass="boxedError" /></label> -->
<!-- 								</div>	 -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div>	 -->
					<div class="row-fluid">
						<p class="margin-medium">
							<a id="formSubmitButtonPulisci" href="${context}/report/pulisci"
								class="btn btn-secondary"><spring:message code="button.pulisci" /></a> 
								
							<input
								id="formSubmitButtonCerca" type="submit"
								name="Cerca" value=<spring:message code="button.prenota"/> class="btn btn-primary pull-right" >
							</input>
						</p>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form:form>

