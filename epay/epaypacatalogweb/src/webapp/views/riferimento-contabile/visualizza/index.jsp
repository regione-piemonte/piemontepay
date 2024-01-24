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
					id="modifica_riferimento_contabile" 
					class="form-horizontal"
					method="post" 
					commandName="modifica_riferimento_contabile" modelAttribute="modifica_riferimento_contabile"
				>
					<fieldset>

						<h3>Visualizza Riferimento Contabile</h3>

						<br />

						<div class="step-content">
							<div class="step-pane active">

								<h4>Riferimento contabile</h4>

								<div class="container-fluid">
									<div class="row-fluid" id="formParametriContainer">
										<%@ include file="form.jsp"%>
									</div>
								</div>

							</div>
						</div>

						<p class="margin-medium">
							<a class="btn btn-secondary btn-action" id="buttonBack">Indietro</a>
						</p>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
