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
					id="chiudi_riferimento_contabile" 
					class="form-horizontal"
					method="post" 
					commandName="chiudi_riferimento_contabile" modelAttribute="chiudi_riferimento_contabile"
					action="${context}/riferimenti-contabili/chiudi/${chiudi_riferimento_contabile.id}"
				>
					<fieldset>

						<h3>Chiudi Riferimento Contabile</h3>

						<br />

						<div class="step-content">
							<div class="step-pane active">

								<h4>Riferimento Contabile</h4>

								<div class="container-fluid">
									<div class="row-fluid" id="formParametriContainer">
										<%@ include file="form.jsp"%>
									</div>
								</div>
							</div>
						</div>

						<p class="margin-medium">
							<a class="btn btn-secondary btn-action" id="buttonBack">Indietro</a>
							<button type="button" id="buttonSave" class="btn btn-primary btn-action pull-right">Conferma chiusura</button>
						</p>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
