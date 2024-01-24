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
			<c:forEach var="riga" items="${lista_risultati_acv}">

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
							<c:if test="${not riga.admin}">
								<div id="tableRowActions" class="btn-group">
									<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown" >
										<spring:message code="table.actions" /> <span class="caret"></span>
									</button>
									<ul class="dropdown-menu pull-right">
										<li>
											<a class="modificaAction" href="${context}/autorizzazione-codici-versamento/modifica/${riga.id}"  >
												<spring:message code="table.autorizzarevoca" />
											</a>
										</li>
									</ul>
								</div>
							</c:if>
							<c:if test="${riga.admin}">
								<spring:message code="table.utenteAdmin" />
							</c:if>
						</td>
					</tr>

			</c:forEach>
		</tbody>
	</table>
</div>
