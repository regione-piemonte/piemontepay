<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<form:form 
	id="filtro_ricerca_lock"
	name="filtro_ricerca_lock"
	action="${context}/lock/ricerca" 
	method="post"
	class="" 
	modelAttribute="filtro_ricerca_lock"
>
	<fieldset>

		<div id="alert-div"></div>

		<h3><spring:message code="lock.formricerca.ricercalockapplicativi"/></h3>

		<div class="step-content">
			<div class="step-pane active" id="filterPanel">
				<div class="accordion-heading">
					<h4>
						<spring:message code="lock.formricerca.filtrodiricerca"/><span class="pull-right clickable"> <a
							href="#collapseFilterPanel" data-toggle="collapse"
							aria-expanded="true" data-parent="#filterPanel"><i
								class="glyphicon glyphicon-chevron-up"></i> <i
								class="glyphicon glyphicon-chevron-down"></i></a>
						</span>
					</h4>
				</div>
				<div id="collapseFilterPanel" class="collapse in">
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
								<label class="col-sm-2 control-label" for="idEnte">
									<spring:message code="lock.formricerca.ente"/>
								</label>
								<div class="col-sm-10 controls">
									<form:select path="idEnte" class="form-control">
										<form:option value="">-- Qualsiasi --</form:option>
										<form:options items="${opzioni_ente}"
											itemLabel="descrizione" itemValue="codice" />
									</form:select>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="idEnte" cssClass="boxedError" /></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for=dataElaborazioneDa>
									<spring:message code="lock.formricerca.datalockda"/>
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="dataLockDa"
										name="dataLockDa"
										path="dataLockDa" 
										class="form-control datepicker" 
										placeholder="data lock da"
									/>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="dataLockDa" cssClass="boxedError" /></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="container-fluid">
						<div class="row-fluid">
							<div class="form-group">
								<label class="col-sm-2 control-label" for=dataElaborazioneA>
									<spring:message code="lock.formricerca.datalocka"/>
								</label>
								<div class="col-sm-10 controls">
									<form:input 
										id="dataLockA"
										name="dataLockA"
										path="dataLockA" 
										class="form-control datepicker" 
										placeholder="data lock a"
									/>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<form:errors path="dataLockA" cssClass="boxedError" /></label>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row-fluid">
						<p class="margin-medium">
							<a id="formSubmitButtonPulisci" href="${context}/lock/pulisci"
								class="btn btn-secondary"><spring:message code="button.pulisci"/></a> 
								
							<input
								id="formSubmitButtonCerca" type="submit" value=<spring:message code="button.search"/>
								name="Cerca" class="btn btn-primary pull-right" />
						</p>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form:form>

