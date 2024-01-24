<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>

<div class="container">
	<p class="visible-xs">
		Sei allo step <strong>${commonData.step.attuale}</strong> di ${commonData.step.numero}
	</p>
	<ul class="nav nav-tabs wizard hidden-xs">
		<c:forEach var="i" begin="1" end="${commonData.step.numero}">
			<li class="${i == 1 ? 'first' : i == commonData.step.numero ? 'last' : 'center'} ${i == commonData.step.attuale ? 'active' : i < commonData.step.attuale ? 'comp' : ''}">
				<span class="badge">${i}</span>
				${commonData.step.getDescrizione(i)}
			</li>
		</c:forEach>
	</ul>
</div>
