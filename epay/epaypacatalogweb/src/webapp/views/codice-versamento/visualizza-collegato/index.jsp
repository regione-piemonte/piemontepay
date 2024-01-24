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
				<h3><spring:message code="codiceversamento.ricerca.tabellarisultati.visualizzacodiceversamentoassociato" /></h3>
				
				<br />

				<form:form 
					id="codice_versamento_padre"
					method="get" 
					class="form-horizontal"
					commandName="codice_versamento_padre"
					action="${context}/codici-versamento/ricerca"
				>
					<div class="step-content">
						<div class="step-pane active" id="filterPanel">
							<div class="accordion-heading">
								<h4>
								<spring:message code="codiceversamento.collegato.index.codiceversamentopadre" />
									<span class="pull-right clickable"> 
										<a href="#collapseFilterPanel" data-toggle="collapse" aria-expanded="true" data-parent="#filterPanel" class="">
											<i class="glyphicon glyphicon-chevron-down"></i> 
											<i class="glyphicon glyphicon-chevron-up"></i>
										</a>
									</span>
								</h4>
							</div>
							<div id="collapseFilterPanel" class="collapse in" aria-expanded="true" style="">
								<%@ include file="form_codice_versamento.jsp"%>
							</div>
						</div>
					</div>
				</form:form>
				
				<form:form 
					id="modifica_codice_versamento_collegato" 
					class="form-horizontal"
					method="post" 
					commandName="modifica_codice_versamento_collegato"
					action=""
					track-changes="true"
				>
					<fieldset>

						<div class="step-content">
							<div class="step-pane active">
							
								<h4><spring:message code="filter.codiceversamento" /></h4>
								<div class="container-fluid">
									<div class="row-fluid" id="formCollegatoContainer">
										<%@ include file="form.jsp"%>
									</div>
								</div>
				
							</div>
						</div>
		
					</fieldset>
				</form:form>
				
				<p class="margin-medium">
					<a class="btn btn-secondary btn-action" id="buttonBack"><spring:message code="button.back" /></a>
				</p>
			</div>
		</div>
	</div>

	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
