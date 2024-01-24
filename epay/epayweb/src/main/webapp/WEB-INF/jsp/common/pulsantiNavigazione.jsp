<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="tag_lib.jsp"%>
<%@ include file="alertStampaAvvisoModal.jsp"   %>

<div class="row">
	<div class="col-sm-12">
		<div class="form-group pulsantiera">
		
			<c:if test="${(!empty commonData.indietro) && commonData.indietro}">
				<form:button type="submit" class="btn btn-default" name="action" id="action_indietro" value="indietro">
			 		<i class="fa fa-chevron-left" aria-hidden="true"></i> Indietro
				</form:button>
			</c:if>
			
			<c:choose>
			
				<c:when test="${(!empty commonData.prosegui) && commonData.prosegui}">
					<form:button type="submit" class="btn btn-primary pull-right" name="action" id="action_prosegui" value="prosegui">
						Prosegui <i class="fa fa-chevron-right" aria-hidden="true"></i>
					</form:button>
				</c:when>
			
				<c:otherwise>
					<c:if test="${(!empty commonData.stampaAvviso) && commonData.stampaAvviso}">
<%-- 						<a class="btn btn-primary pull-right" target="_blank" href="${urlStampa}" id="stampa"><i class="fa fa-print" aria-hidden="true"></i> scarica pdf</a> --%>
						
						<a class="btn btn-primary pull-right"  href="#" id="stampa" onclick="$(document.getElementById('AlertStampaAvvisoModal')).modal().show(); $('.modal-backdrop').css('display', 'block')">
						<i class="fa fa-print" aria-hidden="true"></i> scarica pdf</a>
						
						
					</c:if>
				</c:otherwise>
			
			</c:choose>
			
			<c:if test="${urlCodiceFiscale == '/epayweb/accessoLibero/pagaConIuv/codiceFiscale'}">
				<form:button type="submit" class="btn btn-primary pull-right" style="margin-right: 10px;" name="action" id="action_stampa" value="salvaestampa" >
			 		<i class="fa fa-print" aria-hidden="true"></i> Stampa
				</form:button>
			</c:if>
		
		</div>
	</div>
</div>
