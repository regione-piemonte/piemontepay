<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results" class="table table-hover tab_left dataTable no-footer">
	<thead>
	<tr>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.identificativoflusso" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.dataoraflusso" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.istitutoricevente" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.psp" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.importototalepagamenti" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.numerototalepagamenti" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.datainserimento" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.contatoretentativi" /></th>
		<th><spring:message code="elaborazione.dettaglio.tabellarisultati.stato" /></th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${flussi_associati_a_elaborazione}">
		<tr>
		
			<td data-key="identificativoFlusso"><c:out value="${result.identificativoFlusso}">--</c:out></td>
			<td data-order="${not empty result.dataOraFlusso ? result.getDataOraFlusso().getTime() : '0'}">
				<c:if test="${not empty result.dataOraFlusso}">
					<fmt:formatDate value="${result.dataOraFlusso}" pattern="dd/MM/yyyy HH:mm:ss" />
				</c:if>
				<c:if test="${empty result.dataOraFlusso}">
					--
				</c:if>
			</td>
			<td data-key="identificativoIstitutoRicevente"><c:out value="${result.identificativoIstitutoRicevente}">--</c:out></td>
			<td data-key="identificativoPsp"><c:out value="${result.identificativoPsp}">--</c:out></td>
			<td data-order="${not empty result.importoTotalePagamenti ? result.importoTotalePagamenti : '0'}">
				<c:if test="${not empty result.importoTotalePagamenti}">
					<fmt:formatNumber value="${result.importoTotalePagamenti}" type="currency" currencySymbol=""/>
					&euro;
				</c:if>
				<c:if test="${empty result.importoTotalePagamenti}">
					--
				</c:if>
			</td>
			<td data-key="numeroTotalePagamenti"><c:out value="${result.numeroTotalePagamenti}">--</c:out></td>
			<td data-order="${not empty result.dataInserimento ? result.getDataInserimento().getTime() : '0'}">
				<c:if test="${not empty result.dataInserimento}">
					<fmt:formatDate value="${result.dataInserimento}" pattern="dd/MM/yyyy HH:mm:ss" />
				</c:if>
				<c:if test="${empty result.dataInserimento}">
					--
				</c:if>
			</td>
			<td data-key="contatoreTentativi"><c:out value="${result.contatoreTentativi}">--</c:out></td>
			<td data-key="descrizioneStato"><c:out value="${result.descrizioneStato}">--</c:out></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
