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
					id="modifica_codice_versamento" 
					class="form-horizontal"
					method="post" 
					commandName="modifica_codice_versamento" modelAttribute="modifica_codice_versamento"
					action="${context}/codici-versamento/${scenario == 'modifica' ? 'modifica/' : 'inserisci/'}${scenario == 'modifica' ? modifica_codice_versamento.id : codice_voce_entrata}"
					track-changes="true"
				>
					<fieldset>

						<h3><spring:message code="codiceversamento.visualizza.breadcrumb.visualizzacodiceversamento" /></h3>

						<br />

						<div class="step-content">
							<div class="step-pane active">

								<h4><spring:message code="filter.codiceversamento" /></h4>

								<div class="container-fluid">
									<div class="row-fluid" id="formParametriContainer">
										<%@ include file="form_parametri.jsp"%>
									</div>
								</div>

								<%@ include file="form_elementi_multibeneficiario.jsp"%>

								<sec:authorize access="hasRole('ASSISTENZA')">
									<c:if test="${modifica_codice_versamento.codiceTipoPagamento.equals('LDC') or
								modifica_codice_versamento.codiceTipoPagamento.equals('REDS') or
								modifica_codice_versamento.codiceTipoPagamento.equals('PABL') or
								modifica_codice_versamento.codiceTipoPagamento.equals('REDI')}">
										<h4><spring:message code="index.abilitazione.appio"/></h4>
										<%@ include file="form_abilitazione_notifiche_appio.jsp" %>
									</c:if>
								</sec:authorize>
                                
                                <%@ include file="form_elementi_pnd.jsp"%>
                                
                                

								<c:if test="${scenario.equals('modifica')}">
									<h4><spring:message code="codiceversamento.index.codiciversamentocollegati" /></h4>
								
									<div class="col-sm-12"  id="formCodiciCollegatiContainer">
										<%@ include file="form_codici_collegati.jsp"%>
									</div>
									<div class="row-fluid">
										<p class="margin-medium">
										
											<c:if test="${not empty modifica_codice_versamento.codiciVersamentoCollegati}">
											<a id="formSubmitButtonScaricaCSV" class="btn btn-secondary btn-action pull-right"
												href="${context}/codici-versamento/${id_codice_versamento}/codici-versamento-collegati/esporta-csv"  
												style="margin-right: 0em;"
											>
												<span class="fas fa-file-alt"></span>
												<spring:message code="scaricafilecsv" />
											</a> 
											<a id="formSubmitButtonScaricaExcel" class="btn btn-secondary btn-action pull-right"
												href="${context}/codici-versamento/${id_codice_versamento}/codici-versamento-collegati/esporta-excel" 
												style="margin-right: 1em;"
											>
												<span class="fas fa-file-excel"></span>
												<spring:message code="scaricafileexcel" />
											</a>
											</c:if>
										</p>
									</div>
									
									<br />
								</c:if>
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
