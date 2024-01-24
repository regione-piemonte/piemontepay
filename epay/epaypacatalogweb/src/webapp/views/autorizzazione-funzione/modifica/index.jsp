<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../../include/head.jsp"%>

<body>
	<%@ include file="../../include/portal-header.jsp"%>

	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span></li>
				<li><a href="${context}/autorizzazione-funzioni/ricerca"><spring:message code="autorizzazionefunzione.modifica.index.autorizzazionefunzioni" /></a><span class="divider"></span></li>
				<li class="active"><spring:message code="autorizzazionefunzione.modifica.index.autorizzarevocafunzioni" /></li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<form:form id="autorizzazioneFunzioniForm" name="autorizzazioneFunzioniForm" 
					action="${context}/autorizzazione-funzioni/salva" method="post" 
					enctype="multipart/form-data" 
					modelAttribute="unactiveFunctionList" 
					class="form-horizontal"
					track-changes="true"
				>
					<fieldset>

						<h3><spring:message code="autorizzazionefunzione.modifica.index.autorizzarevocafunzioni" /></h3>					
						
						<%@ include file="form_utente.jsp"%>
						<br />
						<%@ include file="form_funzioni.jsp"%>
						
						<p class="margin-medium">
							<a href="${context}/autorizzazione-funzioni/ricerca" 
								class="btn btn-secondary btn-action"><spring:message code="button.back" /></a>
								
							<a class="btn btn-primary btn-action pull-right" id="form-save-submit"><spring:message code="button.save" /></a>
						</p>
						
<!-- 						<p class="margin-medium"> -->
<%-- 							<a href="${context}/autorizzazione-funzioni/ricerca" class="btn btn-secondary btn-action">Indietro</a> --%>
<%-- 							<form:button type="submit" id="autorizzazioneFunzioniForm" value="Salva" class="btn btn-primary btn-action pull-right" --%>
<%-- 							>Salva</form:button> --%>
<!-- 						</p> -->

					</fieldset>
				</form:form>
			</div>
		</div>
	</div>

	<br />
	
	<%@ include file="script.jsp"%>
	<%@ include file="style.jsp"%>
	<%@ include file="../../include/portal-footer.jsp"%>
</body>
