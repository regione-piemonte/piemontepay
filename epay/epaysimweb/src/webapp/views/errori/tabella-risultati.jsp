<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results"
	class="table table-hover tab_left dataTable no-footer">
	<thead>
		<tr>
			<th>Identificativo Flusso</th>
			<th>Data Inserimento</th>
			<th>Descrizione</th>
			<th><spring:message code="table.actions" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${lista_risultati}">
			<tr>
				<td data-key="identificativoFlusso"><c:out
						value="${result.identificativoFlusso}">--</c:out></td>
				<td
					data-order="${not empty result.dataInserimento ? result.getDataInserimento().getTime() : '0'}">
					<c:if test="${not empty result.dataInserimento}">
						<fmt:formatDate value="${result.dataInserimento}"
							pattern="dd/MM/yyyy HH:mm:ss" />
					</c:if> <c:if test="${empty result.dataInserimento}">
					--
				</c:if>
				</td>
				<td data-key="descrizione"><c:out value="${result.descrizioneErrore}">--</c:out></td>
				<td>
					<div id="tableRowActions" class="btn-group">
						<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
							<spring:message code="table.actions" /> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right">
							<li><a href="${context}/errori/${result.id}/visualizza"
								id="visualizzaProvvisorioAction"><spring:message code="home.flussi.errore.ricerca.visualerrore" /></a></li>
							<li hidden="true"><a href="${context}/errori/${result.id}/modifica"
								id="modificaProvvisorioAction"><spring:message code="home.flussi.errore.ricerca.moderrore" /></a></li>
							<li hidden="true"><a href="${context}/errori/${result.id}/cancella"
								id="visualizzaProvvisorioAction"><spring:message code="home.flussi.errore.ricerca.delerrore" /></a></li>
						</ul>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
