<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="container-fluid mt-2">
	<div class="row">
		<c:if test="${lista_risultati_vuota_acv}">
		<div class ="col-sm-12">
			<p class="alert alert-warning">
				<spring:message code="validation.noResult" />
			</p>
		</div>
		</c:if>
		<c:if test ="${not empty lista_risultati_acv}">
		
		<%@ include file="tabella_risultati.jsp"%>
		
		</c:if>
	</div>
</div>
