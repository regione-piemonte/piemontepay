<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<!-- VECCHIA TABELLA, MANTENGO IL FILE TEMPORANEAMENTE COME REFERENZA DEI CAMPI -->

<table id="results" class="table table-hover tab_left dataTable no-footer">
	<thead>
	<tr>
		<th><spring:message code="flusso.tabelladettaglifull.pagatore" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.causale" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.codversamento" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.datapagamento" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.datispecificidiriscossione" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.descrizioneversamento" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.esitopagamento" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.idtransazione" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.idunicoriscossione" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.idunicoversamento" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.importosingoloversamento" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.indicesingoloversamento" /></th>
		<th><spring:message code="flusso.tabelladettaglifull.statoinviofruitore" /></th>
		<th><spring:message code="table.actions" /></th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${dettaglio_flusso.flussiDettaglio}">
		<tr>
			<td data-key="anagraficaPagatore">
				<c:out value="${result.anagraficaPagatore}">--</c:out>
				<br/>
				<c:out value="${result.codiceFiscalePagatore}" />
			</td>
			<td data-key="causale"><c:out value="${result.causale}">--</c:out></td>
			<td data-key="codiceVersamento"><c:out value="${result.codiceVersamento}">--</c:out></td>
			<td data-order="${not empty result.dataPagamento ? result.getDataPagamento().getTime() : '0'}">
				<c:if test="${not empty result.dataPagamento}">
					<fmt:formatDate value="${result.dataPagamento}" pattern="dd/MM/yyyy" />
				</c:if>
				<c:if test="${empty result.dataPagamento}">
					--
				</c:if>
			</td>
			<td data-key="datiSpecificiDiRiscossione"><c:out value="${result.datiSpecificiDiRiscossione}">--</c:out></td>
			<td data-key="descrizioneVersamento"><c:out value="${result.descrizioneVersamento}">--</c:out></td>
			<td data-key="esitoPagamento"><c:out value="${result.esitoPagamento}">--</c:out></td>
			<td data-key="idTransazione"><c:out value="${result.idTransazione}">--</c:out></td>
			<td data-key="identificativoUnicoRiscossione"><c:out value="${result.identificativoUnicoRiscossione}">--</c:out></td>
			<td data-key="identificativoUnicoVersamento"><c:out value="${result.identificativoUnicoVersamento}">--</c:out></td>
			<td data-order="${not empty result.importoSingoloVersamento ? result.importoSingoloVersamento : '0'}">
				<c:if test="${not empty result.importoSingoloVersamento}">
					<fmt:formatNumber value="${result.importoSingoloVersamento}" type="currency" currencySymbol=""/>
					&euro;
				</c:if>
				<c:if test="${empty result.importoSingoloVersamento}">
					--
				</c:if>
			</td>
			<td data-key="indiceSingoloVersamento"><c:out value="${result.indiceSingoloVersamento}">--</c:out></td>
			<td data-key="statoInvioFruitore"><c:out value="${result.statoInvioFruitore}">--</c:out></td>
			<td>
				<div id="tableRowActions" class="btn-group">
					<button disabled class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
						<spring:message code="table.actions" /><span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
					</ul>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
