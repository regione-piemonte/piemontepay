<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results" class="table table-hover tab_left dataTable no-footer">
	<thead>
	<tr>
		<th></th>
		<th><spring:message code="flusso.tabellarisultati.nominativoexport" /></th>
		<th><spring:message code="flusso.tabellarisultati.tiporeport" /></th>
		<th><spring:message code="flusso.tabellarisultati.statoreport" /></th>
		<th><spring:message code="flusso.tabellarisultati.nomefile" /></th>
		<th><spring:message code="flusso.tabellarisultati.dataoraprenotazioneexport" /></th>
		<th><spring:message code="flusso.tabellarisultati.dataoraultimamodifica" /></th>
		<th><spring:message code="flusso.tabellarisultati.dataorainizioelaborazione" /></th>
		<th><spring:message code="table.actions" /></th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${lista_risultati}">
			<tr>
				<td></td>
				<td data-key="nominativoExport"><c:out value="${result.nominativoExport}">--</c:out></td>
				<td data-key="tipoReport"><c:out value="${result.tipoReport}">--</c:out></td>
				<td data-key="statoReport"><c:out value="${result.statoReport}">--</c:out></td>
				<td data-key="nomeFile"><c:out value="${result.nomeFile}">--</c:out></td>
				<td data-key="dataOraPrenotazione"><c:out value="${result.dataOraPrenotazione}">--</c:out></td>
				<td data-key="dataOraModifica"><c:out value="${result.dataOraUltimaModifica}">--</c:out></td>
				<td data-key="dataOraElaborazione"><c:out value="${result.dataOraInizioElaborazione}">--</c:out></td>
				<td>
				<div id="tableRowActions" class="btn-group">
					<c:if test="${result.codiceStato == 'COMPLETED' or result.codiceStato == 'DOWNLOADED'}">
						<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
					</c:if>
					<c:if test="${result.codiceStato != 'COMPLETED' and result.codiceStato != 'DOWNLOADED'}">
						<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown" disabled>
					</c:if>
						<spring:message code="table.actions" /><span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li>
							<a href="${context}/report/${result.id}/download" 
								id="scaricaReport" 
							>
								<spring:message code="report.tabellarisultati.scaricareport" />
							</a>
						</li>
					</ul>
				</div>
			</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
