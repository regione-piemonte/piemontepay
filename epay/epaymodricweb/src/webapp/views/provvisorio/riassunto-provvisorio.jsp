<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<div class="accordion-heading">
			<h4>
				<spring:message code="provvisorio.riassuntoprovvisorio.provvisorio"/><span class="pull-right clickable"> <a
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
						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}"
							for="identificativoFlusso"> <!-- obbligatorio -->
							<spring:message code="provvisorio.riassuntoprovvisorio.identificativoflusso"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="identificativoFlusso" name="identificativoFlusso"
								path="identificativoFlusso" class="form-control"
								placeholder="${disable_input == 'false'? 'identificativo flusso':''}"
								disabled="${ disable_input }" maxlength="35" />
								<form:errors path="identificativoFlusso" cssClass="boxedError"/>
						</div>

					</div>

					<div class="form-group">
						<!-- obbligatorio -->
						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}" for="dataMovimento">
							<spring:message code="provvisorio.riassuntoprovvisorio.datamovimento"/></label>
						<div class="col-sm-4 controls">
							<form:input id="dataMovimento" name="dataMovimento"
								path="dataMovimento" class="form-control datepicker" placeholder="${disable_input == 'false'? 'Data Movimento':''}"
								disabled="${ disable_input }" />
								<form:errors path="dataMovimento" cssClass="boxedError" />
						</div>

						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}" for="annoEsercizio">
							<!-- obbligatorio --><spring:message code="provvisorio.riassuntoprovvisorio.annoesercizio"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="annoEsercizio" name="annoEsercizio"
								path="annoEsercizio" class="form-control" type="number" min="1970"
								placeholder="${disable_input == 'false'? 'Anno esercizio':''}" disabled="${ disable_input }" max="9999" />
								<form:errors path="annoEsercizio" cssClass="boxedError"/>
						</div>
					</div>
					<div class="form-group">
						<!-- 						obbligatorio -->
						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}"
							for="annoProvvisorio"><spring:message code="provvisorio.riassuntoprovvisorio.annoprovvisorio"/></label>
						<div class="col-sm-4 controls">
							<form:input id="annoProvvisorio" name="annoProvvisorio"
								path="annoProvvisorio" class="form-control" type="number" min="1970" max="9999"
								placeholder="${disable_input == 'false'? 'Anno Provvisorio':''}" disabled="${ disable_input }" />
								<form:errors path="annoProvvisorio" cssClass="boxedError" />
						</div>

						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}"
							for="numeroProvvisorio"> <!-- 							obbligatorio -->
							<spring:message code="provvisorio.riassuntoprovvisorio.numeroprovvisorio"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="numeroProvvisorio" name="numeroProvvisorio" type="number" min="0" max="9999999"
								path="numeroProvvisorio" class="form-control" placeholder="${disable_input == 'false'? 'Numero Provvisorio':''}"
								disabled="${ disable_input }" />
								<form:errors path="numeroProvvisorio" cssClass="boxedError"  />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}"
							for="importoProvvisorio"> <!--obbligatorio -->
							<spring:message code="provvisorio.riassuntoprovvisorio.importoprovvisorio"/>
						</label>
						<div class="col-sm-4 controls">
							<form:input id="importoProvvisorio" name="importoProvvisorio" type="number" step=".01"
								path="importoProvvisorio" class="form-control" placeholder="${disable_input == 'false'? 'Importo Provvisorio':''}" min="-99999999999999999"
								disabled="${ disable_input }"  max="99999999999999999" />
								<form:errors path="importoProvvisorio" cssClass="boxedError" />
						</div>
					</div>
					<div class="form-group">
						<!--obbligatorio o VALIDATO O ANNULLATO -->
						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}" for="stato">
							<spring:message code="provvisorio.riassuntoprovvisorio.stato"/>
						</label>
						<div class="col-sm-4 controls">
						<!--  	<form:select path="stato" class="form-control" disabled="disabled">  -->
								<input id="stato" name="stato" class="form-control" value="VALIDO" path="stato" readonly />
						<!--  </form:select>  -->	
						</div>
						<label class="col-sm-2 control-label ${disable_input == 'false'? 'required':''}" for="descrizione">
							<!-- 							obbligatoria --><spring:message code="provvisorio.riassuntoprovvisorio.descrizione"/>
						</label>
						<div class="col-sm-4 controls">
							<form:textarea id="descrizione" name="descrizione"
								path="descrizione" class="form-control"
								placeholder="${disable_input == 'false'? 'Descrizione':''}" disabled="${ disable_input }"  maxlength="90" row="2"/>
								<form:errors path="descrizione" cssClass="boxedError" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
