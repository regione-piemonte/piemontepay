<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>

<c:if test="${datiPersonali.hasComponentiImporto()}">
<div class="modal fade" id="modalComponentiImporto" tabindex="-1" role="dialog" aria-labelledby="modalComponentiImporto">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
 			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h3 class="modal-title" id="modalComponentiImportoLabel">Componenti importo</h3>
			</div>
			<div class="modal-body" style="color:black;">
				<div class="row">
					<c:forEach var="comp" items="${datiPersonali.componentiImporto}">
						<div class="col-sm-9">${comp.causale}</div>
						<div class="col-sm-3"><fmt:formatNumber type="CURRENCY" currencySymbol="&euro;" value="${comp.importo}"/></div>
					</c:forEach>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
			</div>
		</div>
	</div>
</div>
</c:if>
