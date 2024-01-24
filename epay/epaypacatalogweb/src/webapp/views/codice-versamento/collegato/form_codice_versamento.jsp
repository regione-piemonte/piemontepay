<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="3" />
<c:set var="w01" value="9" />
<c:set var="w04" value="4" />
<c:set var="w05" value="2" />

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="codice"><spring:message code="filter.aliasVoceEntrata" /></label>
			<div class="col-sm-${w01} controls">
				<div class="input-group input-group-inline">
			  		<span class="input-group-addon input-codice-prefisso" id="container-codice-codice-versamento"><c:out value='${codice_versamento_padre.codice}' /></span>
					<form:input class="form-control input-descrizione-codice-versamento" path="descrizione" readonly="true" />
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="codiceVoceEntrata"><spring:message code="codiceversamento.collegato.formcodiceversamento.voceentratappay" /></label>
			<div class="col-sm-${w01} controls">
				<div class="input-group input-group-inline">
			  		<span class="input-group-addon input-codice-prefisso" id="container-codice-voce-entrata"><c:out value='${codice_versamento_padre.codiceVoceEntrata}' /></span>
					<form:input class="form-control input-descrizione-voce-entrata" path="descrizioneVoceEntrata" id="descrizioneVoceEntrata" readonly="true" />
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="codiceTipoPagamento"><spring:message code="tipopagamento" /></label>
			<div class="col-sm-${w01} controls">
			
				<form:input class="form-control input-tipo-pagamento" path="descrizioneTipoPagamento" readonly="true" />
			</div>
		</div>
	</div>
</div>
