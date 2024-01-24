<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<div class="accordion-heading">
			<h4>
				<spring:message code="provvisori.riassuntoprovvisorio.provvisorio"/> <span class="pull-right clickable"> <a
					href="#collapseFilterPanel" data-toggle="collapse"
					aria-expanded="true" data-parent="#filterPanel"> <i
						class="glyphicon glyphicon-chevron-up"></i> <i
						class="glyphicon glyphicon-chevron-down"></i>
				</a>
				</span>
			</h4>
		</div>
		<div id="collapseFilterPanel" class="collapse in">

			<form:hidden path="id" id="id" name="id"/>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="identificativoFlusso"> <!-- 							obbligatorio -->
							<spring:message code="provvisori.riassuntoprovvisorio.identificativoflusso"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="identificativoFlusso" name="identificativoFlusso"
								path="identificativoFlusso" class="form-control"
								placeholder="identificativo flusso"
								disabled="${ disable_input }" />
								<form:errors path="identificativoFlusso" cssClass="boxedError" />
						</div>

					</div>

					<div class="form-group">
						<!-- obbligatorio -->
						<label class="col-sm-2 control-label required" for="dataMovimento">
							<spring:message code="provvisori.riassuntoprovvisorio.datamovimento"/> </label>
						<div class="col-sm-4 controls">
							<form:input id="dataMovimento" name="dataMovimento"
								path="dataMovimento" class="form-control datepicker" placeholder="Data Movimento"
								disabled="${ disable_input }" />
								<form:errors path="dataMovimento" cssClass="boxedError" />
						</div>

						<label class="col-sm-2 control-label required" for="annoEsercizio">
							<!-- obbligatorio --> <spring:message code="provvisori.riassuntoprovvisorio.annoesercizio"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="annoEsercizio" name="annoEsercizio"
								path="annoEsercizio" class="form-control"
								placeholder="Anno esercizio" disabled="${ disable_input }" />
								<form:errors path="annoEsercizio" cssClass="boxedError" />
						</div>
					</div>
					<div class="form-group">
						<!-- 						obbligatorio -->
						<label class="col-sm-2 control-label required"
							for="annoProvvisorio"> <spring:message code="provvisori.riassuntoprovvisorio.annoprovvisorio"/> </label>
						<div class="col-sm-4 controls">
							<form:input id="annoProvvisorio" name="annoProvvisorio"
								path="annoProvvisorio" class="form-control"
								placeholder="Anno Provvisorio" disabled="${ disable_input }" />
								<form:errors path="annoProvvisorio" cssClass="boxedError" />
						</div>

						<label class="col-sm-2 control-label required"
							for="numeroProvvisorio"> <!-- 							obbligatorio -->
							<spring:message code="provvisori.riassuntoprovvisorio.numeroprovvisorio"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="numeroProvvisorio" name="numeroProvvisorio"
								path="numeroProvvisorio" class="form-control" placeholder="Numero Provvisorio"
								disabled="${ disable_input }" />
								<form:errors path="numeroProvvisorio" cssClass="boxedError" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="importoProvvisorio"> <!-- 							obbligatorio -->
							<spring:message code="provvisori.riassuntoprovvisorio.importoprovvisorio"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="importoProvvisorio" name="importoProvvisorio"
								path="importoProvvisorio" class="form-control" placeholder="Importo Provvisorio"
								disabled="${ disable_input }" />
								<form:errors path="importoProvvisorio" cssClass="boxedError" />
						</div>
					</div>
					<div class="form-group">
						<!-- 						obbligatorio o VALIDATO O ANNULLATO -->
						<label class="col-sm-2 control-label required" for="stato">
							<spring:message code="provvisori.riassuntoprovvisorio.stato"/> </label>
						<div class="col-sm-4 controls">
						    <form:input id="stato" name="stato"
								path="stato" class="form-control" placeholder="Stato"
								disabled="${ disable_input }" />							
						</div>
						<label class="col-sm-2 control-label required" for="descrizione" hidden="true">
							<!-- 							obbligatoria --> 
							<spring:message code="provvisori.riassuntoprovvisorio.descrizione"/>
						</label>
						<div class="col-sm-4 controls" hidden="true">
							<form:input id="descrizione" name="descrizione"
								path="descrizione" class="form-control"
								placeholder="Descrizione" disabled="${ disable_input }" />
								<form:errors path="descrizione" cssClass="boxedError" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
