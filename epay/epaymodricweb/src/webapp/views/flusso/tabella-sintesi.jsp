<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results" class="table table-hover tab_left dataTable no-footer">
	<thead>
	<tr>
		<th><spring:message code="flusso.tabellasintesi.codiceversamento" /></th>
		<th><spring:message code="flusso.tabellasintesi.descrizioneversamento" /></th>
		<th><spring:message code="flusso.tabellasintesi.datispecificidiriscossione" /></th>
		<th><spring:message code="flusso.tabellasintesi.importoquotaaggregazione" /></th>
		<th><spring:message code="flusso.tabellasintesi.numeroversamentoquotaaggregazione" /></th>
		<th><spring:message code="flusso.tabellasintesi.provvisorioanno" /></th>
		<th><spring:message code="flusso.tabellasintesi.provvisorionumero" /></th>
		<th><spring:message code="flusso.tabellasintesi.numeroaccertamento" /></th>
		<th><spring:message code="flusso.tabellasintesi.annoaccertamento" /></th>
		<th><spring:message code="table.actions" /></th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${sintesi_flusso.flussiSintesi}">
		<tr>
			<td data-key="codiceVersamento"><c:out value="${result.codiceVersamento}">--</c:out></td>
			<td data-key="descrizioneVersamento"><c:out value="${result.descrizioneVersamento}">--</c:out></td>
			<td data-key="datiSpecificiDiRiscossione"><c:out value="${result.datiSpecificiDiRiscossione}">--</c:out></td>
			<td data-order="${not empty result.importoQuotaAggregazione ? result.importoQuotaAggregazione : '0'}">
				<c:if test="${not empty result.importoQuotaAggregazione}">
					<fmt:formatNumber value="${result.importoQuotaAggregazione}" type="currency" currencySymbol=""/>
					&euro;
				</c:if>
				<c:if test="${empty result.importoQuotaAggregazione}">
					--
				</c:if>
			</td>
			<td data-key="numeroVersamentoQuotaAggregazione"><c:out value="${result.numeroVersamentoQuotaAggregazione}">--</c:out></td>
			<td data-key="provvisorioAnno"><c:out value="${result.provvisorioAnno}">--</c:out></td>
			<td data-key="provvisorioNumero"><c:out value="${result.provvisorioNumero}">--</c:out></td>
			<td data-key="numeroaccertamento"> <c:out value="${result.numeroAccertamento}">--</c:out> </td>
			<td data-key="annoAccertamento"><c:out value="${result.annoAccertamento}">--</c:out></td>
			
			<td>
				<div id="tableRowActions" class="btn-group">
					<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
						<spring:message code="table.actions" /> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li>
							<a href="${context}/flussi/${sintesi_flusso.flussoOrigine.id}/sintesi/${result.id}/dettagli" 
								id="visualizzaFlussoSintesiAction"
							>
								<spring:message code="flusso.tabellasintesi.visualizzadettaglio" />
							</a>
						</li>
					</ul>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
