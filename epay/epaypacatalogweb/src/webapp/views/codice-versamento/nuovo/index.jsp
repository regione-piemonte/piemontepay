<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../../include/head.jsp"%>

<body>
	<%@ include file="../../include/portal-header.jsp"%>
	<%@ include file="breadcrumb.jsp"%>
	<c:url var="urlCodiciSecondari" value="/ajax/ricerca-codici-versamento-da-associare" />
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<form:form 
					id="modifica_codice_versamento" 
					class="form-horizontal"
					method="post" 
					commandName="modifica_codice_versamento" modelAttribute="modifica_codice_versamento"
					action="${context}/codici-versamento/${scenario == 'modifica' ? 'modifica/' : 'inserisci'}${scenario == 'modifica' ? modifica_codice_versamento.id : ''}"
					track-changes="true"
				>
					<fieldset>

						<h3>${scenario == 'modifica' ? 'Modifica' : 'Inserisci'} <spring:message code="filter.codiceversamento" /></h3>

						<br />

						<div class="step-content">
							<div class="step-pane active">
							
							<c:if test="${alert_no_opzioni_multibeneficiario}">
							
							 <div class ="col-sm-12" id= "alertNoOpzioniMultibeneficiarioId" >
							 </c:if>
							 
							 <c:if test="${!alert_no_opzioni_multibeneficiario}">
							
							 <div class ="col-sm-12" id= "alertNoOpzioniMultibeneficiarioId" style="display: none;" >
							 </c:if>
							 
							
	                         
		                   <p class="alert alert-warning">
			               <spring:message code="codiceversamento.modifica.alert.no.opzioni.multibeneficiario" />
		                 </p>
						</div>
	

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
										<%@ include file="form_parametri.jsp"%>
									</div>
								</div>
								
							
							<div id = "formElementiMultibeneficiarioContainerId"> 
								<h4><spring:message code="index.elementi.multibeneficiario" /></h4>
								
								<div class="container-fluid">
									<div class="row-fluid"  >
									<%@ include file="form_elementi_multibeneficiario.jsp"%>
									</div>
								</div>
								</div>

								
									<%@ include file="form_abilitazione_notifiche_appio.jsp" %>

                                
                              
                                     
                                <%@ include file="form_elementi_pnd.jsp"%>
                                   
								
								<c:if test="${scenario.equals('modifica')}">
									<h4><spring:message code="codiceversamento.index.codiciversamentocollegati" /></h4>
								
									<div class="col-sm-12"  id="formCodiciCollegatiContainer">
										<%@ include file="form_codici_collegati.jsp"%>
									</div>
									<div class="row-fluid">
										<p class="margin-medium">
										
											<c:if test="${visibilita_totale}">
												<sec:authorize access="hasRole('INSERISCI_CODICE_VERSAMENTO')">
													<a id="insert" href="${context}/codici-versamento/${id_codice_versamento}/codici-versamento-collegati/nuovo?source=modifica" 
														class="btn btn-primary btn-action pull-left"
													>
														<spring:message code="codiceversamento.modifica.index.associanuovocodiceversamento" />
													</a>
												</sec:authorize>
											</c:if>
											
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
								
								<div class="row-fluid">
									<p class="margin-medium">
										<a id="buttonClean" class="btn btn-secondary btn-action"><spring:message code="button.cleanup" /></a>
									</p>
								</div>
							</div>
						</div>

						<p class="margin-medium">
							<a class="btn btn-secondary btn-action" id="buttonBack"><spring:message code="button.back" /></a>
							<button type="button" id="buttonSave" class="btn btn-primary btn-action pull-right">
							<div id = "labelButtonSave"> <spring:message code="button.save" /></div>
							<div id = "labelButtonProceed" style="display: none;"><spring:message code="button.proceed" /></div>
							</button>
						</p>
					</fieldset>
				</form:form>
			</div>
		</div>
	</div>
	
	<%@ include file="modale-voci-entrata/index.jsp"%>
	<%@ include file="modale-informazione-abilitazione-gestione-esterna/index.jsp"%>
	
	<%@ include file="script.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
