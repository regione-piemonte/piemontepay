<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
	<div class="step-pane active row">
		<div class="col-sm-6">
			
			<div class="container-fluid">
				<div class="row mt-1 mb-1">
					<div class="col-sm-12">
						<h4><spring:message code="text.funzioniattivate" /></h4>
					</div>
				</div>
				
				<div class="row mt-1 mb-2">
					<div class="col-sm-12">
						<input class="form-control input-filtro-funzione" id="inputAttivate" type="text" 
							placeholder='<spring:message code="text.ricercafunzioneattivata" />'>
					</div>
				</div>
			</div>
			
			<div class="container-fluid" id="container-attivate">
				<c:forEach var="categoria" items="${categorie}">
					<div class="row category-row category-row-attivata category-row-${categoria.codice}" id="category-row-${categoria.codice}-attivate">
						<div class="col-sm-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<span>${categoria.descrizione}</span>
									<a class="btn btn-xs btn-danger category-revoke-button "
										id="category-revoke-button-${categoria.codice}" 
										data-codice="${categoria.codice}"
									>
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
										<span class="descrizione-azione">
											<spring:message code="autorizzazionefunzione.modifica.formfunzioni.revoca" />
										</span>
									</a>
								</div>
								<div class="panel-body">
									<ul class="list-group">
										<c:forEach var="activeFunction" items="${activeFunctionsList}" varStatus="idx">
											<c:if test="${activeFunction.getCodiceCategoria().equals(categoria.getCodice())}">
												<li class="list-group-item function-row function-row-attivata" id="function-row-${activeFunction.codice}">
	
													<input name="active" type="hidden" id="${activeFunction.codice}" value="${activeFunction.codice}" />
													
													<span class="descrizione">
														${activeFunction.descrizione}
													</span>
													
													<a class="btn btn-xs btn-danger function-toggle-button "
														id="function-toggle-button-${activeFunction.codice}" 
														data-codice="${activeFunction.codice}"
														data-codice-categoria="${activeFunction.codiceCategoria}"
													>
														<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
														<span class="descrizione-azione">
															<spring:message code="autorizzazionefunzione.modifica.formfunzioni.revoca" />
														</span>
													</a>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>
		<div class="col-sm-6">
				
			<div class="container-fluid">
				<div class="row mt-1 mb-1">
					<div class="col-sm-12">
						<h4><spring:message code="text.funzioniattivabili" /></h4>
					</div>
				</div>
				
				<div class="row mt-1 mb-2">
					<div class="col-sm-12">
						<input class="form-control input-filtro-funzione" id="inputAttivabili" type="text" placeholder='<spring:message code="text.ricercafunzioneattivabile" />'>
					</div>
				</div>
			</div>
						
			<div class="container-fluid" id="container-attivabili">
				<c:forEach var="categoria" items="${categorie}">
					<div class="row category-row category-row-attivabile category-row-${categoria.codice}" id="category-row-${categoria.codice}-attivabili">
						<div class="col-sm-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<span>${categoria.descrizione}</span>
									<a class="btn btn-xs btn-success category-grant-button "
										id="category-grant-button-${categoria.codice}" 
										data-codice="${categoria.codice}"
									>
										<span class="glyphicon glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
										<span class="descrizione-azione">
											<spring:message code="autorizzazionefunzione.modifica.formfunzioni.autorizza" />
										</span>
									</a>
								</div>
								<div class="panel-body">
									<ul class="list-group">
										<c:forEach var="activeFunction" items="${unactiveFunctionList}" varStatus="idx">
											<c:if test="${activeFunction.getCodiceCategoria().equals(categoria.getCodice())}">
												<li class="list-group-item function-row function-row-attivabile" id="function-row-${activeFunction.codice}">
	
													<input name="unactive" type="hidden" id="${activeFunction.codice}" value="${activeFunction.codice}" />
													
													<span class="descrizione">
														${activeFunction.descrizione}
													</span>
													
													<a class="btn btn-xs btn-success function-toggle-button "
														id="function-toggle-button-${activeFunction.codice}" 
														data-codice="${activeFunction.codice}"
														data-codice-categoria="${activeFunction.codiceCategoria}"
													>
														<span class="glyphicon glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>
														<span class="descrizione-azione">
															<spring:message code="autorizzazionefunzione.modifica.formfunzioni.autorizza" />
														</span>
													</a>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
		</div>

	</div>
</div>
