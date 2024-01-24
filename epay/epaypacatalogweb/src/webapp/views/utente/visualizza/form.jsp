<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-2 control-label required" for="codiceFiscale"><spring:message code="filter.ssnNumber" /></label>
		<div class="col-sm-10 controls">
			<form:input class="form-control view-uppercase input-codice-fiscale" path="codiceFiscale" readonly="true" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<form:errors path="codiceFiscale" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-2 control-label required" for="nome"><spring:message code="filter.nome" /></label>
		<div class="col-sm-10 controls">
			<form:input class="form-control input-nome" path="nome" readonly="true" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<form:errors path="nome" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-2 control-label required" for="cognome"><spring:message code="filter.cognome" /></label>
		<div class="col-sm-10 controls">
			<form:input class="form-control input-cognome" path="cognome" readonly="true" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<form:errors path="cognome" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-2 control-label" for="email"><spring:message code="filter.email" /></label>
		<div class="col-sm-10 controls">
			<form:input class="form-control input-email" path="email" readonly="true" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<form:errors path="email" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-2 control-label required" for="dataInizioValidita"><spring:message code="table.startDate" /> </label>
		<div class="col-sm-10 controls input-append">
			<form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" readonly="true" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<form:errors path="dataInizioValidita" cssClass="error" />
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-2 control-label" for="dataFineValidita"><spring:message code="table.expirationDate" /> </label>
		<div class="col-sm-10 controls input-append">
			<form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" readonly="true" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<form:errors path="dataFineValidita" cssClass="error" />
		</div>
	</div>
</div>
