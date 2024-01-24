<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-sm-12">
			<h1>${commonData.titolo}<span><img src="${risorseStatiche}/images/pagoPA_blu.png" alt="logo" style="height: 70px; float: right"></span></h1>
			<%--
			<c:if test="${not empty commonData.descrizione}">
				<p class="hidden-xs">${commonData.descrizione}</p>
			</c:if>
			--%>
		</div>
	</div>
</div>
