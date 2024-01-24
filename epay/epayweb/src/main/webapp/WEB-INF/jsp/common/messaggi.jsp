<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>

<c:if test="${not empty messaggi and not messaggi.isEmpty()}">
	<c:forEach var="messaggio" items="${messaggi.orderGravityMessaggi}">
		<div class="${messaggio.level.cssAlertClass}" role="alert">
			<c:out value="${messaggio.testo}"/>
		</div>
	</c:forEach>
</c:if>
