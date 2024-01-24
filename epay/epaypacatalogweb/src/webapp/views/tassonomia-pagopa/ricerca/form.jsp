<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="w00" value="3" />
<c:set var="w01" value="9" />

<form:form method="post"
	commandName="filtro_ricerca_tassonomia_pagopa" 
	action="${context}/tassonomia-pagopa/ricerca"
	
    
>
	<fieldset>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="nomeMacroArea"
					> <spring:message code="form.ricercatassonomia.nomemacroarea" /> </label>
					<div class="col-xs-12 col-sm-9 col-md-5 controls"">
							<form:select path="nomeMacroArea" class="form-control input-nome-macro-area" >
					<form:option value="">-- Qualsiasi --</form:option>
					<form:options items="${model_macro_area}" itemLabel="descrizione" itemValue="codice" />
				</form:select>
				
					</div>
					
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="tipoServizio"
					> <spring:message code="form.ricercatassonomia.tiposervizio" /> </label>
					<div class="col-xs-12 col-sm-9 col-md-5 controls"">
							<form:select path="tipoServizio" class="form-control input-tipo-servizio" >
					<form:option value="">-- Qualsiasi --</form:option>
					<form:options items="${model_tipo_servizio}" itemLabel="descrizione" itemValue="codice" />
				</form:select>
					</div>
					
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="datiSpecificiIncasso"
					> <spring:message code="form.ricercatassonomia.datospecificoincasso" /> </label>
					<div class="col-xs-12 col-sm-9 col-md-5 controls">
                 <form:select path="datiSpecificiIncasso" class="form-control input-descrizione-dato-specifico-riscossione" >
					<form:option value="">-- Qualsiasi --</form:option>
					<form:options items="${model_dati_specifici_incasso}" itemLabel="descrizione" itemValue="codice" />
				</form:select>
					</div>
					
					</div>
				</div>
			</div>
			
			

			<div class="container-fluid">
	<div class="form-group ">
	
			<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="dataInizioValidita"
					> <spring:message code="form.ricercatassonomia.datainiziovalidita" /> </label>
	
		
			<div class="col-sm-5 controls input-append">
				<form:input 
					path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data"
				/>
			</div>
		
		
		
		<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="dataFineValidita"
					> <spring:message code="form.ricercatassonomia.datafinevalidita" /> </label>
		
		
			<div class="col-sm-5 controls input-append">
				<form:input 
					path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data"
				/>
			</div>
		
		
		<div class="col-sm-3"></div>
		<div class="col-sm-3">
			<form:errors path="dataInizioValidita" cssClass="error" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-4">
			<form:errors path="dataFineValidita" cssClass="error" />
		</div>
	</div>
</div>
	
	
	<div class="row-fluid">
			<p class="margin-medium">
				<a 
					id="formSubmitButtonPulisci" 
					href="${context}/tassonomia-pagopa/pulisci"
					class="btn btn-secondary btn-action"
				> <spring:message code="button.cleanup" /> </a> 

			

				<a class="btn btn-primary btn-action pull-right" 
					id="form-search-submit"> <spring:message code="button.search" /> </a>
			</p>
		</div>	
		
		
		
	</fieldset>
</form:form>
