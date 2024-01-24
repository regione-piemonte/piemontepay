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

				<form:form id="autorizzazioneCodiciVersamentoForm"
					name="depositCodeForm"
					action="${context}/autorizzazione-codici-versamento/salva"
					method="post"
					modelAttribute="albero_tematiche" class="form-horizontal"
					track-changes="true"
				>
					<fieldset>
					
						<h3><spring:message code="autorizzazionecodiciversamento.modifica.autorizzazionerevocacodiciversamento" /></h3>
						
						<br />
						<%@ include file="form_utente.jsp"%>
						<br />
						<%@ include file="form_codici.jsp"%>

						<p class="margin-medium">
							<a href="${context}/autorizzazione-codici-versamento/ricerca" 
								class="btn btn-secondary btn-action"><spring:message code="button.back" /></a>
								
							<form:button 
								type="submit" 
								id="autorizzazioneCodiciVersamentoForm_0" 
								value="Salva" 
								class="btn btn-primary pull-right btn-action">
								<spring:message code="button.save" />
							</form:button>
						</p>

					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<br />


<%@ include file="script.jsp"%>
<%@ include file="style.jsp"%>
<%@ include file="../../include/portal-footer.jsp"%>

</body>
