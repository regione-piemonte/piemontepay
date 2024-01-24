<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="container-fluid mt-2">
	<div class="row">
		<c:if test="${lista_risultati_vuota_af}">
		<div class ="col-sm-12">
			<p class="alert alert-warning">
				<spring:message code="validation.noResult" />
			</p>
		</div>
		</c:if>
		<c:if test ="${not empty lista_risultati_af}">
		
		<%@ include file="tabella_risultati.jsp"%>
		<div class ="col-sm-12">
			<div class="row-fluid">
				<p class="margin-medium">
					<a
						id="formButtonExportCsv"
						name="EsportaCsv" 
						class="btn btn-default btn-action pull-right"
						href="${context}/autorizzazione-funzioni/esporta-csv"
						style="margin-right: 0em;"
					>
						<span class="fas fa-file-alt"></span>
						<spring:message code="esportafilecsv" />
					</a>
					<a
						id="formButtonExportExcel"
						name="EsportaExcel" 
						class="btn btn-default btn-action pull-right"
						href="${context}/autorizzazione-funzioni/esporta-excel"
						style="margin-right: 1em;"
					>
						<span class="fas fa-file-excel"></span>
						<spring:message code="esportafileexcel" />
					</a>
				</p>
			</div>
		</div>
		
		</c:if>
	</div>
</div>
