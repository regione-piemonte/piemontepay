<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="w00" value="2" />
<c:set var="w01" value="10" />
<c:set var="w00h" value="2" />
<c:set var="w01h" value="4" />

<form:form method="post"
	commandName="filtro_ricerca_codici_versamento" 
	action="${context}/codici-versamento/ricerca"
>
	<fieldset>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-md-12 control-label"> 
					<form:errors path="id" cssClass="boxedError" /></label>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="descrizione"><spring:message code="codiceversamento.ricerca.form.descrizionecodiceversamento" /></label>
					<div class="col-xs-12 col-sm-9 col-md-10 controls">
						<form:input id="descrizione" class="form-control input-filtro-codice-versamento" path="descrizione" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="codiceTematica"><spring:message code="form.ricerca.tematica" />
					</label>
					<div class="col-xs-12 col-sm-9 col-md-5 controls">
						<form:select path="codiceTematica" class="form-control input-filtro-tematica">
							<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
							<form:options items="${modelTematiche}" itemLabel="codiceDescrizione"
								itemValue="codice" />
						</form:select>
					</div>
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="codiceMacrotipo"><spring:message code="form.ricerca.macrotipo" />
					</label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:select path="codiceMacrotipo" class="form-control input-filtro-macrotipo">
							<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
							<form:options items="${modelMacrotipi}" itemLabel="codiceDescrizione"
								itemValue="codice" />
						</form:select>
					</div>
				</div>
		
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="descrizioneVoceEntrata"><spring:message code="codiceversamento.form.voceentratappay" /></label>
					<div class="col-xs-12 col-sm-9 col-md-5 controls">
						<form:input class="form-control input-filtro-voce-entrata" path="descrizioneVoceEntrata" />
						<form:hidden path="codiceVoceEntrata" />
					</div>
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="iban"><spring:message code="iban" /></label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:input id="iban" class="form-control view-uppercase input-filtro-iban" path="iban" maxlength="35" />
					</div>
				</div>
			</div>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="codiceMultibeneficiario"><spring:message code="codiceversamento.ricerca.form.codice.multibeneficiario" /></label>
					<div class="col-xs-12 col-sm-9 col-md-5 controls">
						<form:hidden id="codiceMultibeneficiario" path="codiceMultibeneficiario" />
						<form:checkbox class="checkbox-inline" id="codiceMultibeneficiario_checkbox" path="codiceMultibeneficiarioCheckbox" value="false"/>
					</div>
				</div>
			</div>
		</div>

		<div class="row-fluid">
			<p class="margin-medium">
				<a 
					id="formSubmitButtonPulisci" 
					href="${context}/codici-versamento/pulisci"
					class="btn btn-secondary btn-action"
				><spring:message code="button.cleanup" /></a> 
	
				<sec:authorize access="hasRole('INSERISCI_CODICE_VERSAMENTO')">
				<a id="buttonNew" class="btn btn-primary btn-action" href="${context}/codici-versamento/nuovo"
					style="margin-left: 1em;"
				>
					<spring:message code="codiceversamento.ricerca.form.inseriscicodicebase" />
				</a> 
				</sec:authorize>
				
				<a class="btn btn-primary btn-action pull-right" 
					id="form-search-submit"><spring:message code="button.search" /></a>
			</p>
		</div>
	</fieldset>
</form:form>
<script>
$("#codiceMultibeneficiario_checkbox").on("click", function() {
	$("#codiceMultibeneficiario").val( $('#codiceMultibeneficiario_checkbox').is(':checked'));
});
</script>
