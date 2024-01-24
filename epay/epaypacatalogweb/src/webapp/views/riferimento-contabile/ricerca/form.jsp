<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="w00" value="3" />
<c:set var="w01" value="9" />

<form:form method="post"
	commandName="filtro_ricerca_riferimenti_contabili" 
	action="${context}/riferimenti-contabili/ricerca"
>
	<fieldset>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-sm-12 control-label"> 
					<form:errors path="id" cssClass="boxedError" /></label>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="descrizioneCompletaCodiceVersamentoSelezionato"
					> <spring:message code="form.ricerca.codiceversamento" /> </label>
					<div class="col-xs-12 col-sm-9 col-md-10 controls">
						<form:input id="descrizioneCompletaCodiceVersamentoSelezionato" 
							class="form-control input-filtro-codice-versamento" path="descrizioneCompletaCodiceVersamentoSelezionato" />
						<form:hidden path="idCodiceVersamento" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="descrizioneVoceEntrata"
					> <spring:message code="form.ricerca.voceentrata" /> </label>
					<div class="col-xs-12 col-sm-9 col-md-10 controls">
						<form:input class="form-control input-filtro-voce-entrata" path="descrizioneVoceEntrata" />
						<form:hidden path="codiceVoceEntrata" />
					</div>
					
					<div class="col-xs-12 col-sm-3 col-md-2"></div>
					<div class="col-xs-12 col-sm-9 col-md-10">
						<form:errors path="codiceVoceEntrata" cssClass="error" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="codiceTematica"
					> <spring:message code="form.ricerca.tematica" />
					</label>
					<div class="col-xs-12 col-sm-9 col-md-5 controls">
						<form:select path="codiceTematica" class="form-control input-filtro-tematica">
							<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
							<form:options items="${modelTematiche}" itemLabel="codiceDescrizione"
								itemValue="codice" />
						</form:select>
		
					</div>
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="codiceMacrotipo"
					> <spring:message code="form.ricerca.macrotipo" />
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
					<label class="hidden-xs col-xs-3 col-sm-3 col-md-3 control-label" for="ancheRiferimentiNonInVita">
						<spring:message code="form.ricerca.riferimentinoninvita" />
					</label>
					<div class="col-xs-12 col-sm-9 col-md-9 controls">
						<form:checkbox 
							id="ancheRiferimentiNonInVita" 
							class="checkbox-inline" 
							path="ancheRiferimentiNonInVita" 
						/>
						<label class="visible-xs-inline col-xs-3control-label" for="ancheRiferimentiNonInVita"
							style="text-align: left;"
						>
							<spring:message code="form.ricerca.riferimentinoninvita" />
						</label>
					</div>
				</div>
			</div>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="hidden-xs col-xs-3 col-sm-3 col-md-3 control-label" for="multibeneficiarioCheckbox">
						<spring:message code="multibeneficiario" />
					</label>
					<div class="col-xs-12 col-sm-9 col-md-9 controls">
						<form:checkbox id="multibeneficiarioCheckbox" class="checkbox-inline" path="multibeneficiarioCheckbox"/>
						<label class="visible-xs-inline col-xs-3control-label" for="multibeneficiarioCheckbox" style="text-align: left;">
							<spring:message code="multibeneficiario"/>
						</label>
					</div>
				</div>
			</div>
		</div>

		<div class="row-fluid">
			<p class="margin-medium">
				<a 
					id="formSubmitButtonPulisci" 
					href="${context}/riferimenti-contabili/pulisci"
					class="btn btn-secondary btn-action"
				> <spring:message code="button.cleanup" /> </a> 

				<a id="buttonNew" class="btn btn-primary btn-action"
					style="margin-left: 1em;" 
					href="${context}/riferimenti-contabili/nuovo"
					> <spring:message code="riferimenticontabili.action.inserisci" /> </a>

				<a class="btn btn-primary btn-action pull-right" 
					id="form-search-submit"> <spring:message code="button.search" /> </a>
			</p>
		</div>
	</fieldset>
</form:form>
