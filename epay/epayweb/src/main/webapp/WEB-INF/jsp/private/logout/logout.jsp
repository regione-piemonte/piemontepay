<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/tag_lib.jsp"%>

<!DOCTYPE html>
<html lang="it">
	<head>
		<%@ include file="../../common/header_html.jsp"%>
		<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/skin-interna.css" />
		<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported_skin_interna.css" />
		<script src="<c:url value="/resources/js/home.js" />"></script>
	</head>
	<body>
		<c:set var="menu" scope="request" value="logout"/>
		<%@ include file="../../common/header_page.jsp"%>
		<%@ include file="body.jsp"%>
		<%@ include file="../../common/footer_page.jsp"%>
	</body>
</html>
