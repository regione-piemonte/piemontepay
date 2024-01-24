<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class ="col-sm-12">
	<table id="results" class="table table-hover tab_left dataTable no-footer">
		<thead>
		<tr>
			<th class=""> <spring:message code="form.ricercatassonomia.risultati.tabella.nomemacroarea" /> </th>
			<th class=" "> <spring:message code="form.ricercatassonomia.risultati.tabella.descrizionemacroarea" /> </th>
			<th class=" "> <spring:message code="form.ricercatassonomia.risultati.tabella.tiposervizio" /> </th>
			<th class=" "> <spring:message code="form.ricercatassonomia.risultati.tabella.motivogiuridicoriscossione" /> </th>
			<th class=""> <spring:message code="table.startDate" /> </th>
			<th class=""> <spring:message code="table.expirationDate" /> </th>
			<th class=""></th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="riga" items="${lista_risultati}">
			<tr id='view_<c:out value="${riga.id}" />'>
				<td data-key="nomeMacroArea">
					<c:out value="${riga.nomeMacroArea}" />
				</td>
				<td data-key="descrMacroArea">
					<c:out value="${riga.descrMacroArea}" />
				</td>
				<td data-key="tipoServizio">
					<c:out value="${riga.tipoServizio}" />
				</td>
				<td data-key="desMotivoGiuridicoRiscossione">
					<c:out value="${riga.desMotivoGiuridicoRiscossione}" />
				</td>
				<td data-order="${not empty riga.dataInizioValidita ? riga.getDataInizioValidita().getTime() : '0'}">
					<c:if test="${not empty riga.dataInizioValidita}">
						<fmt:formatDate value="${riga.dataInizioValidita}" pattern="dd/MM/yyyy" />
					</c:if>
					<c:if test="${empty riga.dataInizioValidita}">
						--
					</c:if>
				</td>
				<td data-order="${not empty riga.dataFineValidita ? riga.getDataFineValidita().getTime() : '0'}">
					<c:if test="${not empty riga.dataFineValidita}">
						<fmt:formatDate value="${riga.dataFineValidita}" pattern="dd/MM/yyyy" />
					</c:if>
					<c:if test="${empty riga.dataFineValidita}">
						--
					</c:if>
				</td>
				<td></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
