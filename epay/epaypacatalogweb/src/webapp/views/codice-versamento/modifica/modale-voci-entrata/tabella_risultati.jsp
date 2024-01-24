<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../../../include/head_ajax.jsp"%>

<div id="rendered">

	<c:if test="${lista_risultati_ve_vuota}">
	<div class ="col-sm-12">
		<p class="alert alert-warning">
			<spring:message code="validation.noResultAllowed" />
		</p>
	</div>
	</c:if>
	
	<c:if test ="${not empty lista_risultati_ve}">
	<div class ="col-sm-12">
		<table id="results-ve" class="table table-hover tab_left dataTable no-footer" auto-data-table>
			<thead>
			<tr>
				<th><spring:message code="form.ricerca.codice" /></th>
				<th><spring:message code="form.ricerca.descrizione" /></th>
				<th><spring:message code="form.ricerca.tematica" /></th>
				<th><spring:message code="form.ricerca.macrotipo" /></th>
				<th></th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${lista_risultati_ve}">
				<tr>
					<td data-key="codice"><c:out value="${result.codice}"/></td>
					<td data-key="descrizione"><c:out value="${result.descrizione}"/></td>
					<td data-key="descrizioneTematica"><c:out value="${result.descrizioneTematica}"/></td>
					<td data-key="descrizioneMacrotipo"><c:out value="${result.descrizioneMacrotipo}"/></td>
					<td>
						<a class="btn btn-primary btn-action pull-right" onclick="selezionaVoceEntrata('${result.codice}')"><spring:message code="button.select" /></a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	</c:if>
</div>
