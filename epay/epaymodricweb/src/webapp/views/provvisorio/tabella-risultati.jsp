<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results"
	class="table table-hover tab_left dataTable no-footer">
	<thead>
		<tr>
			<th><spring:message code="provvisorio.tabellarisultati.identificativoflusso"/></th>
			<th><spring:message code="provvisorio.tabellarisultati.dataoramovimento"/></th>
			<th><spring:message code="provvisorio.riassuntoprovvisorio.annoprovvisorio"/></th>
			<th><spring:message code="provvisorio.riassuntoprovvisorio.annoesercizio"/></th>
			<th><spring:message code="provvisorio.riassuntoprovvisorio.numeroprovvisorio"/></th>
			<th><spring:message code="provvisorio.riassuntoprovvisorio.importoprovvisorio"/></th>
			<th><spring:message code="provvisorio.tabellarisultati.descrizione"/></th>
			<th><spring:message code="provvisorio.tabellarisultati.stato"/></th>
			<th><spring:message code="table.actions"/></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${lista_risultati}">
			<tr>
				<td data-key="identificativoFlusso"><c:out
						value="${result.identificativoFlusso}">--</c:out></td>
				<td
					data-order="${not empty result.dataMovimento ? result.getDataMovimento().getTime() : '0'}">
					<c:if test="${not empty result.dataMovimento}">
						<fmt:formatDate value="${result.dataMovimento}"
							pattern="dd/MM/yyyy HH:mm:ss" />
					</c:if> <c:if test="${empty result.dataMovimento}">
					--
				</c:if>
				</td>
				<td data-key="annoProvvisorio"><c:out value="${result.annoProvvisorio}">--</c:out></td>
				<td data-key="annoEsercizio"><c:out value="${result.annoEsercizio}">--</c:out></td>
				<td data-key="numeroProvvisorio"><c:out value="${result.numeroProvvisorio}">--</c:out></td>
				<td data-key="importoProvvisorio"><c:out value="${result.importoProvvisorio}">--</c:out></td>
				<td data-key="descrizione"><c:out value="${result.descrizione}">--</c:out></td>
				<td data-key="stato"><c:out value="${result.stato}">--</c:out></td>
				<td>
					<div id="tableRowActions" class="btn-group">
						<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
							<spring:message code="table.actions"/> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right">
							<li><a class="modificaAction" href="${context}/provvisori/${result.id}/modifica"
								id="modificaProvvisorioAction"> <spring:message code="provvisorio.tabellarisultati.modificaprovvisorio"/></a></li>
							<li><a class="eliminaAction" href="#"
								id="cancellaProvvisorioAction" onclick="eliminaProvvisorio('${context}/provvisori/${result.id}/cancella')">
								<spring:message code="provvisorio.tabellarisultati.cancellaprovvisorio"/></a></li>
						</ul>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
