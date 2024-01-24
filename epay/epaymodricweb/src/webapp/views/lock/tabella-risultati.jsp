<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<table id="results" class="table table-hover tab_left dataTable no-footer">
	<thead>
	<tr>
		<th></th>
		<th><spring:message code="lock.tabellarisultati.ente"/></th>
		<th><spring:message code="lock.tabellarisultati.utente"/></th>
		<th><spring:message code="lock.tabellarisultati.datainserimento"/>/th>
		<th><spring:message code="lock.tabellarisultati.datarilascio"/></th>
	</tr>
	</thead>
	<tbody>
		<c:forEach items="${lista_risultati.risultati}" var="risultati" varStatus="status">
		<tr>
			<td>
				<form:checkbox path="risultati[${status.index}].checkControl"/>
				<form:input path="risultati[${status.index}].id" type="hidden" />
			</td>
			<td data-key="descrizioneEnte"><c:out value="${risultati.descrizioneEnte}">--</c:out></td>
			<td data-key="utente">         <c:out value="${risultati.utente}"         >--</c:out></td>
			
			<td data-order="${not empty risultati.dataLockDa ? risultati.getDataLockDa().getTime() : '0'}">
				<c:if test="${not empty risultati.dataLockDa}">
					<fmt:formatDate value="${risultati.dataLockDa}" pattern="dd/MM/yyyy HH:mm:ss" />
				</c:if>
				<c:if test="${empty risultati.dataLockDa}">
					--
				</c:if>
			</td>
			<td data-order="${not empty risultati.dataLockA ? risultati.getDataLockA().getTime() : '0'}">
				<c:if test="${not empty risultati.dataLockA}">
					<fmt:formatDate value="${risultati.dataLockA}" pattern="dd/MM/yyyy HH:mm:ss" />
				</c:if>
				<c:if test="${empty risultati.dataLockA}">
					--
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
