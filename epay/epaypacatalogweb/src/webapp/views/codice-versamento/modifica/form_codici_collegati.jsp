<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results" class="table table-hover tab_left dataTable no-footer">
	<thead>
		<tr>
			<th><spring:message code="form.ricerca.codice" /></th>
			<th><spring:message code="form.ricerca.descrizione" /></th>
			<th><spring:message code="iban" /></th>
			<th><spring:message code="bic" /></th>
			<th></th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="riga" items="${modifica_codice_versamento.codiciVersamentoCollegati}">
		<tr>
			<td data-key="codice">
				<c:out value="${riga.codice}" />
			</td>
			<td data-key="descrizione">
				<c:out value="${riga.descrizione}" />
			</td>
			<td data-key="iban">
				<c:out value="${riga.iban}" > -- </c:out>
			</td>
			<td data-key="bic">
				<c:out value="${riga.bic}" > -- </c:out>
			</td>
			<td>
				<sec:authorize access="hasRole('MODIFICA_CODICE_VERSAMENTO') or hasRole('ELIMINA_CODICE_VERSAMENTO')">
					<div id="tableRowActions" class="btn-group">
						<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
							<spring:message code="table.actions" /> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right">
							<li>
								<a class="modificaAction" href="${context}/codici-versamento/${modifica_codice_versamento.id}/codici-versamento-collegati/visualizza/${riga.id}?source=modifica">
									<spring:message code="codiceversamento.modifica.formcodicicollegati.visualizzacodiceversamentocollegato" />
								</a>
								<sec:authorize access="hasRole('MODIFICA_CODICE_VERSAMENTO')">
									<a class="modificaAction" href="${context}/codici-versamento/${modifica_codice_versamento.id}/codici-versamento-collegati/modifica/${riga.id}?source=modifica">
										<spring:message code="codiceversamento.modifica.formcodicicollegati.modificacodiceversamentocollegato" />
									</a>
								</sec:authorize>
								<sec:authorize access="hasRole('ELIMINA_CODICE_VERSAMENTO')">
									<a class="eliminaAction" 
										onclick="eliminaCodiceVersamentoCollegato(${riga.id})">
										<spring:message code="codiceversamento.modifica.formcodicicollegati.eliminacodiceversamentocollegato" />
									</a>
								</sec:authorize>
							</li>
						</ul>
					</div>
				</sec:authorize>
			</td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
