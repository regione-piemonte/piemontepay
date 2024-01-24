<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results"
	class="table table-hover tab_left dataTable no-footer">
	<thead>
		<tr>
			<th><spring:message code="provvisori.tabellarisultati.identificativoflusso"/></th>
			<th><spring:message code="provvisori.tabellarisultati.dataoramovimento"/></th>
			<th><spring:message code="provvisori.riassuntoprovvisorio.annoprovvisorio"/></th>
			<th><spring:message code="provvisori.riassuntoprovvisorio.annoesercizio"/></th>
			<th><spring:message code="provvisori.riassuntoprovvisorio.numeroprovvisorio"/></th>
			<th><spring:message code="provvisori.riassuntoprovvisorio.importoprovvisorio"/></th>
			<th><spring:message code="provvisori.tabellarisultati.stato"/></th>
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
				<td data-key="stato"><c:out value="${result.stato}">--</c:out></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
