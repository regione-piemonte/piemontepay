<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/tag_lib.jsp"%>

<div class="col-md-12 col-sm-12 col-xs-12">
<c:if test="${not empty riferimento.enteDesc and not empty riferimento.pagamentoDesc and not empty riferimento.iuv}">
	<div class="well">
		<p>
			Ente:
			<c:out value="${riferimento.enteDesc}" />
		</p>
		<p>
			Pagamento:
			<c:out value="${riferimento.pagamentoDesc}" />
		</p>
		<c:if test="${not empty riferimento.iuv}">
			<p>
				IUV:
				<c:out value="${riferimento.iuv}" />
			</p>
		</c:if>
		<p>
			Data operazione:
			<fmt:formatDate pattern="dd/MM/yyyy" value="${riferimento.dataOperazione}" />
		</p>
		<c:if test="${!commonData.pagamentoSpontaneo}">
			<p>
				Note:
				<c:out value="${datiPersonali.note}" />
			</p>
		</c:if>
		<br>

		<c:if test="${!commonData.pagamentoSpontaneo}">
			<p>
				Importo:&nbsp;
				<c:choose>
					<c:when test="${datiPersonali.hasComponentiImporto()}">
						<a href="#modalComponentiImporto" data-toggle="modal"> <fmt:formatNumber type="CURRENCY" currencySymbol="&euro;"
								value="${datiPersonali.importo}" />
						</a>
					</c:when>
					<c:otherwise>
						<fmt:formatNumber type="CURRENCY" currencySymbol="&euro;" value="${datiPersonali.importo}" />
					</c:otherwise>
				</c:choose>
			</p>
			<p>Soggetto Giuridico: ${datiPersonali.soggettoGiuridico == 'personaFisica' ? 'Persona Fisica' : 'Persona Giuridica'}</p>
			<c:choose>
				<c:when test="${datiPersonali.soggettoGiuridico == 'personaFisica'}">
					<p>
						Nome:
						<c:out value="${datiPersonali.nome}" />
					</p>
					<p>
						Cognome:
						<c:out value="${datiPersonali.cognome}" />
					</p>
				</c:when>
				<c:otherwise>
					<p>
						Ragione Sociale:
						<c:out value="${datiPersonali.ragioneSociale}" />
					</p>
				</c:otherwise>
			</c:choose>
			<p>Codice Fiscale / Partita Iva: <c:out value="${datiPersonali.codiceFiscale}" /></p>
		</c:if>
			<br>
		<c:if test="${not empty riferimento.riferimentiPagamento}">
			<p>Riferimenti pagamento </p>
			<c:forEach var="riferimentoPagamento" items="${riferimento.riferimentiPagamento}"> 
			
				<c:if test="${not empty riferimentoPagamento.nome}">
					<p>
						<c:out value="${riferimentoPagamento.nome}" />: <c:out value="${riferimentoPagamento.valore}" />
					</p>
				</c:if>              
				
			</c:forEach>
		</c:if>
		
	</div>
	</c:if>
</div>
