<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../../include/head.jsp"%>

<body>
	<%@ include file="../../include/portal-header.jsp"%>
	<%@ include file="breadcrumb.jsp"%>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				
				<form:form 
					id="modifica_utente" 
					class="form-horizontal"
					method="post" 
					commandName="modifica_utente" modelAttribute="modifica_utente"
					action="${context}/utenti/${scenario == 'modifica' ? 'modifica/' : 'nuovo'}${scenario == 'modifica' ? modifica_utente.id : ''}"
					track-changes="true"
				>
					<fieldset>

						<h3>${scenario == 'modifica' ? 'Modifica' : 'Inserisci'} <spring:message code="utente" /></h3>

						<br />

						<div class="step-content">
							<div class="step-pane active">

								<h4><spring:message code="index.parametri" /></h4>

								<div class="row-fluid">
									<p>
										<span><spring:message code="index.campicontrassegnatida" /></span>
										<span class="required"><spring:message code="index.asterisco" /></span>
										<span><spring:message code="index.sonoobbligatori" /></span>
									</p>
								</div>

								<div class="container-fluid">
									<div class="row-fluid" id="formParametriContainer">
										<%@ include file="form.jsp"%>
									</div>
								</div>

								<div class="row-fluid">
									<p class="margin-medium">
										<a id="buttonClean" class="btn btn-secondary btn-action"><spring:message code="button.cleanup" /></a>
									</p>
								</div>
							</div>
						</div>

						<p class="margin-medium">
							<a class="btn btn-secondary btn-action" id="buttonBack"><spring:message code="button.back" /></a>
							<button type="button" id="buttonSave" class="btn btn-primary btn-action pull-right"><spring:message code="button.save" /></button>
						</p>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
