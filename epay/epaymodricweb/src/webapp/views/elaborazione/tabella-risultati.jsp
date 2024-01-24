<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<table id="results" class="table table-hover tab_left dataTable no-footer">
	<thead>
	<tr>
		<th><spring:message code="elaborazione.tabellarisultati.datainserimentoelaborazione" /></th>
		<th><spring:message code="elaborazione.tabellarisultati.datainizioelaborazione" /></th>
		<th><spring:message code="elaborazione.tabellarisultati.datafineelaborazione" /></th>
		<th><spring:message code="elaborazione.tabellarisultati.statoelaborazione" /></th>
		<th><spring:message code="table.actions" /></th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${lista_risultati}">
		<tr>
		  
		    <td data-order="${not empty result.dataElaborazione ? result.getDataElaborazione().getTime() : '0'}">
				<c:if test="${not empty result.dataElaborazione}">
					<fmt:formatDate value="${result.dataElaborazione}" pattern="dd/MM/yyyy HH:mm:ss" />
				</c:if>
				<c:if test="${empty result.dataElaborazione}">
					--
				</c:if>
			</td>
			
			<td data-order="${not empty result.dataInizio ? result.getDataInizio().getTime() : '0'}">
			    <c:if test="${not empty result.dataInizio}">
					<fmt:formatDate value="${result.dataInizio}" pattern="dd/MM/yyyy HH:mm:ss" />
				</c:if>
				<c:if test="${empty result.dataInizio}">
					--
				</c:if>
		
			</td>
			
			<td data-order="${not empty result.dataFine ? result.getDataFine().getTime() : '0'}">
			
			    <c:if test="${not empty result.dataFine}">
					<fmt:formatDate value="${result.dataFine}" pattern="dd/MM/yyyy HH:mm:ss" />
				</c:if>
			    <c:if test="${empty result.dataFine}">
					--
				</c:if>
			
			</td>
			<td data-key="stato Elaborazione" ><c:out value="${result.statoElaborazione}"></c:out></td>
			<td>
				<div id="tableRowActions" class="btn-group">
					<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
						<spring:message code="table.actions" /> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li>
							<a href="${context}/elaborazione/${result.id}/dettaglio" 
								id="visualizzaElaborazioneAction" 
							>
								<spring:message code="elaborazione.tabellarisultati.visualizzaelaborazione" />
							</a>
							<sec:authorize access="hasRole('FORZATURA_ELABORAZIONE')">
									<c:if test="${result.statoElaborazione eq 'Elaborazione in errore' or result.statoElaborazione eq 'Elaborazione terminata'}">
										<a class="eliminaAction" onclick="chiediConfermaForzaturaElaborazione(${result.id})"> <spring:message code="elaborazione.tabellarisultati.prenotaelaborazione" />
										</a>
									</c:if>
								</sec:authorize>
							</li>
					</ul>
				</div>
			</td>
		</tr>
		</c:forEach>
		
	</tbody>
	
</table>
