<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="post"
	commandName="filtro_ricerca_utenti_acv" 
	action="${context}/autorizzazione-codici-versamento/ricerca"
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
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="nome"><spring:message code="filter.nome" /></label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:input id="nome" class="form-control input-filtro-nome" path="nome" />
					</div>
					<div class="col-md-1 display-md-inline"></div>
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="cognome"><spring:message code="filter.cognome" /></label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:input id="cognome" class="form-control input-filtro-cognome" path="cognome" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="codiceFiscale"><spring:message code="filter.ssnNumber" /></label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:input id="codiceFiscale" class="form-control view-uppercase input-filtro-codice-fiscale" path="codiceFiscale" />
					</div>
					<div class="col-md-1 display-md-inline"></div>
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="email"><spring:message code="filter.email" /></label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:input id="email" class="form-control input-filtro-email" path="email" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="descrizioneCompletaCodiceVersamentoSelezionato"><spring:message code="filter.codiceversamento" /></label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:input id="descrizioneCompletaCodiceVersamentoSelezionato" 
							class="form-control input-filtro-codice-versamento" path="descrizioneCompletaCodiceVersamentoSelezionato" />
						<form:hidden path="idCodiceVersamento" />
					</div>
					<div class="col-md-1 display-md-inline"></div>
					<label class="col-xs-12 col-sm-3 col-md-1 control-label" for="codiceTematica"><spring:message code="form.ricerca.tematica" />
					</label>
					<div class="col-xs-12 col-sm-9 col-md-4 controls">
						<form:select path="codiceTematica" class="form-control input-filtro-tematica">
							<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
							<form:options items="${modelTematiche}" itemLabel="codiceDescrizione"
								itemValue="codice" />
						</form:select>
		
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="hidden-xs col-xs-3 col-sm-3 col-md-2 control-label" for="soloUtentiInVita"><spring:message code="filter.onlyActiveUsers" /></label>
					<div class="col-xs-12 col-sm-9 col-md-10 controls">
						<form:checkbox 
							id="soloUtentiInVita" 
							class="checkbox-inline" 
							path="soloUtentiInVita" 
						/>
						<label class="visible-xs-inline col-xs-3control-label" for="soloUtentiInVita"
							style="text-align: left;"
						>
							<spring:message code="filter.onlyActiveUsers" />
						</label>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row-fluid">
			<p class="margin-medium">
				<a 
					id="formSubmitButtonPulisci" 
					href="${context}/autorizzazione-codici-versamento/pulisci"
					class="btn btn-secondary btn-action"
				><spring:message code="button.cleanup" /></a> 

				<a class="btn btn-primary btn-action pull-right" 
					id="form-search-submit"><spring:message code="button.search" /></a>
			</p>
		</div>
	</fieldset>
</form:form>
