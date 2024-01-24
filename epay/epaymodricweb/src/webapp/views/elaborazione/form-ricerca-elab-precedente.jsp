<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<form:form 
	id="filtro_ricerca_elaborazione_precedente"
	name="filtro_ricerca_elaborazione_precedente"
	action="${context}/elaborazione/ricercaElaborazionePrecedente" 
	method="post"
	class="" 
	modelAttribute="filtro_ricerca_elaborazione_precedente"
	
>
	<fieldset>

		<div id="alert-div"></div>

		<h3><spring:message code="elaborazione.formricercaelabprecedente.ricercaelaborazioneprecedente" /></h3>

		<div class="step-content">
			<div class="step-pane active" id="filterPanel">
				<c:if test="${not empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="elaborazione.formricercaelabprecedente.filtrodiricerca" /><span class="pull-right clickable"> <a
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
				<c:if test="${empty lista_risultati}">
				<div class="accordion-heading">
					<h4>
						<spring:message code="elaborazione.formricercaelabprecedente.filtrodiricerca" /><span class="pull-right clickable"> <a
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
								<form:errors path="validitaGenerica" cssClass="boxedError" /></label>
							</div>
						</div>
					</div>
					
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group ">
								<label class="col-sm-2 control-label" for="statoElaborazione">
									<spring:message code="elaborazione.formricercaelabprecedente.statoelaborazione" />
								</label>
								<div class="col-sm-10 controls">
									<form:select path="statoElaborazione" class="form-control">
										<form:option value="">-- Qualsiasi --</form:option>
										<form:options items="${opzioni_stati_elaborazione}"
											itemLabel="descrizione" itemValue="codice" />
									</form:select>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="statoElaborazione" cssClass="boxedError" /></label>
								</div>
							</div>
						</div>
					</div>
					
					<!-- data inizio -->
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="dataInizio">
									<spring:message code="elaborazione.formricercaelabprecedente.datainizioelaborazioneda" />
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="dataInizio"
										name="dataInizio"
										path="dataInizio" 
										class="form-control datepicker" 
										
									/>
								</div>
								<!-- in -->
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="dataInizio" cssClass="boxedError" /></label>
								</div>
								<!-- fin -->
							</div>
						</div>
					</div>
					
					<!-- Fine data inizio -->
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="dataFine">
									<spring:message code="elaborazione.formricercaelabprecedente.datainizioelaborazionea" />
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="dataFine"
										name="dataFine"
										path="dataFine" 
										class="form-control datepicker"/>
								</div>
								<!-- in -->
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="dataFine" cssClass="boxedError" /></label>
								</div>
								<!-- fin -->
								
							</div>
						</div>
					</div>
					
					<div class="row-fluid">
						<p class="margin-medium">
							<a id="formSubmitButtonPulisci" href="${context}/elaborazione/pulisci"
								class="btn btn-secondary"><spring:message code="button.pulisci" /></a> 
								
							<input
								id="formSubmitButtonCerca" type="submit" value="Cerca"
								name="Cerca" class="btn btn-primary pull-right" />
						</p>
						<button id="refresh-btn"  type="submit" hidden="hidden"><spring:message code="elaborazione.formricercaelabprecedente.refreshtable" /></button>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form:form>

