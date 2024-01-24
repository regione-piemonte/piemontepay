<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class ="col-sm-12">
	<table id="results" class="table table-hover tab_left dataTable no-footer">
		<thead>
		<tr>
			<th><spring:message code="filter.ssnNumber" /></th>
			<th><spring:message code="filter.nome" /></th>
			<th><spring:message code="filter.cognome" /></th>
			<th><spring:message code="filter.email" /></th>
			<th><spring:message code="table.startDate" /></th>
			<th><spring:message code="table.expirationDate" /></th>
			<th></th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="riga" items="${lista_risultati}">
			<tr id='view_<c:out value="${riga.id}" />'>
				<td data-key="codiceFiscale">
					<c:out value="${riga.codiceFiscale}" />
				</td>
				<td data-key="nome">
					<c:out value="${riga.nome}" />
				</td>
				<td data-key="cognome">
					<c:out value="${riga.cognome}" />
				</td>
				<td data-key="email">
					<c:out value="${riga.email}" />
				</td>
				<td data-order="${not empty riga.dataInizioValidita ? riga.getDataInizioValidita().getTime() : '0'}">
					<c:if test="${not empty riga.dataInizioValidita}">
						<fmt:formatDate value="${riga.dataInizioValidita}" pattern="dd/MM/yyyy" />
					</c:if>
					<c:if test="${empty riga.dataInizioValidita}">
						--
					</c:if>
				</td>
				<td data-order="${not empty riga.dataFineValidita ? riga.getDataFineValidita().getTime() : '0'}">
					<c:if test="${not empty riga.dataFineValidita}">
						<fmt:formatDate value="${riga.dataFineValidita}" pattern="dd/MM/yyyy" />
					</c:if>
					<c:if test="${empty riga.dataFineValidita}">
						--
					</c:if>
				</td>
				<td>
					<sec:authorize access="hasRole('MODIFICA_UTENTE') or hasRole('ELIMINA_UTENTE')">
						<div id="tableRowActions" class="btn-group">
							<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown" ${not riga.modificaConsentita ? ' disabled ' : ''}>
								<spring:message code="table.actions" /> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu pull-right">
								<li>
									<a class="modificaAction" href="${context}/utenti/visualizza/${riga.id}"  >
												<spring:message code="page.viewuser" />
											</a>
									<c:if test="${riga.modificaConsentita}">
										<sec:authorize access="hasRole('MODIFICA_UTENTE')"> 
											<a class="modificaAction" href="${context}/utenti/modifica/${riga.id}"  >
												<spring:message code="page.edituser" />
											</a>
										</sec:authorize>
										<sec:authorize access="hasRole('ELIMINA_UTENTE')"> 
											<a class="eliminaAction" 
												onclick="eliminaUtente(${riga.id})">
												<spring:message code="page.removeuser" />
											</a>
										</sec:authorize>
									</c:if>
								</li>
							</ul>
						</div>
					</sec:authorize>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
