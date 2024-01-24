<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>

<c:choose>
	<c:when test="${(empty menu) || ((menu != 'autenticato') && (menu != 'homepage autenticato'))}">
		<c:url var="urlHomePage" value="/home" />
	</c:when>    
	<c:otherwise>
		<c:url var="urlHomePage" value="/private/home" />
  	</c:otherwise>
</c:choose>

<c:url var="urlPiemontePay" value="/home#piemontepay" />
<c:url var="urlRegistrati" value="/home#registrati" />
<c:url var="urlPagamenti" value="/home#pagamenti" />
<c:url var="urlFAQ" value="/home#faq" />
<c:url var="urlAreaRiservata" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/ris/epayweb/areadocumentale/" />
<c:url var="urlPagaSenzaIuv" value="/accessoLibero/pagaSenzaIuv" />
<c:url var="urlPagaSenzaIuvDonazione" value="/accessoLibero/pagaSenzaIuv/datiPersonali?donazione" />
<c:url var="urlPagaConIuv" value="/accessoLibero/pagaConIuv" />
<c:url var="urlVerifica" value="/accessoLibero/verifica" />
<c:url var="urlStampaAvviso" value="/accessoLibero/stampaAvviso"  />

<c:url var="urlPrivateHomePage" value="/private/home#home" />
<c:url var="urlPrivateLogout" value="/private/logout" />
<c:url var="urlPrivatePagaSenzaIuv" value="/private/pagaSenzaIuv" />
<c:url var="urlPrivatePagaConIuv" value="/private/pagaConIuv" />
<c:url var="urlPrivatePagaAltroRiferimentoConIuv" value="/private/pagaAltroRiferimentoConIuv" />
<c:url var="urlPrivateVerifica" value="/private/verifica" />
<c:url var="urlPrivateStampaAvviso" value="/private/stampaAvviso" />

<c:url var="urlStampa" value="salvaestampa" />

<c:url var="urlRicevutaPdf" value="/stampe/ricevutaPdf" />
<c:url var="urlRtXml" value="/stampe/ricevutaXml" />
<c:url var="urlQuietanzaPdf" value="/stampe/quietanzaPdf" />
<c:url var="urlAvvisoPagamento" value="/stampe/avvisoPagamento" />

<c:url var="urlTasseAjax" value="/ajax/tasse" />
