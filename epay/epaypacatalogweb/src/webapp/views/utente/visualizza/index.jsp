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
					action=""
					track-changes="true"
				>
					<fieldset>

						<h3><spring:message code="page.viewuser" /></h3>

						<br />

						<div class="step-content">
							<div class="step-pane active">

								<div class="container-fluid">
									<div class="row-fluid" id="formParametriContainer">
										<%@ include file="form.jsp"%>
									</div>
								</div>

							</div>
						</div>

						<p class="margin-medium">
							<a class="btn btn-secondary btn-action" id="buttonBack"><spring:message code="button.back" /></a>
						</p>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
