<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<h4><spring:message code="riferimenticontabili.ricerca.risultati.tabella.multibeneficiario" /></h4>

<div class="container-fluid" style="padding-bottom: 1rem">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-3 control-label" for="flagElementiMultibeneficiario"><spring:message code="riferimenticontabili.ricerca.risultati.visualizzazione.abilitazione.multibeneficiario" /></label>
			<div class="col-sm-3 controls">
				<form:checkbox class="checkbox-inline" path="flagElementiMultibeneficiario" id="flagElementiMultibeneficiario" readonly="true" disabled="true"/>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="mbModalita"><spring:message code="modalitaAssociazioneMultibeneficiario" /></label>
			<div class="col-sm-9 controls">
				<form:input class="form-control view-uppercase input-multibeneficiario" path="mbModalita" readonly="true" id="mbModalita"/>
				<a class="suggest" data-explanation="${fn:escapeXml( MEX_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO )}" data-trigger="hover" data-placement="top">
					<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
				</a>
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-9"><form:errors path="mbModalita" cssClass="error" /></div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-3 control-label" for="mbEnteSecondario"><spring:message code="enteSecondarioAssociazioneMultibeneficiario" /></label>
			<div class="col-sm-9 controls">
				<form:input class="form-control view-uppercase input-multibeneficiario" path="mbEnteSecondario" id="mbEnteSecondario" readonly="true"/>
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-9"><form:errors path="mbEnteSecondario" cssClass="error" /></div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group">
			<label class="col-sm-3 control-label" for="mbCodiceVersamentoAssociato"><spring:message code="riferimento.contabile.AssociazioneMultibeneficiario" /></label>
			<div class="col-sm-9 controls">
				<form:input class="form-control view-uppercase input-multibeneficiario" path="mbCodiceVersamentoAssociato" id="mbCodiceVersamentoAssociato" readonly="true"/>
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-9"><form:errors path="mbCodiceVersamentoAssociato" cssClass="error" /></div>
		</div>
	</div>
</div>

