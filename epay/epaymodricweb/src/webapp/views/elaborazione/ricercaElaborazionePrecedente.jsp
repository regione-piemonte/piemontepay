<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../include/head.jsp"%>

<body>
	<%@ include file="../include/portal-header.jsp"%>
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
				</li>
				<li class="active"><spring:message code="elaborazione.ricercaelaborazioneprecedente.ricercaelaborazioneprecedente" /></li>
			</ul>
		</div>
	</div>
	
	<div align="center">
        <div id="result"></div>
        <br>
    </div>

	<div id="divSuperioreIdResult">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<%@ include file="form-ricerca-elab-precedente.jsp"%>

					<div class="container-fluid">
						<!--  modal errori ricerca -->
							<div class="row">
							    <c:if test="${not empty ricerca_elaborazione_errore}"> 
							      <div class="col-sm-12">
								    <p class="alert alert-warning">
									 <spring:message code="elaborazione.ricercaelaborazioneprecedente.errore" />
									 <br/><br/>
									 <c:out value="${ricerca_elaborazione_errore}" />
								   </p>
						         </div>
							 </c:if>
						  </div>
						<!--  fine modal -->
					
						<div class="row">
							<c:if test="${lista_risultati_vuota}">
								<div class="col-sm-12">
									<p class="alert alert-warning">
										<spring:message code="elaborazione.ricercaelaborazioneprecedente.nessunrisultato" />
									</p>
								</div>
							</c:if>

							<c:if test="${not empty lista_risultati}">
							
								<form:form 
									id="lista_risultati"
									name="lista_risultati"
									action="${context}/elaborazione/elimina" 
									method="post"
									class="" 
									modelAttribute="lista_risultati"
									>
									
									
									<div class="col-sm-12">
										<%@ include file="tabella-risultati.jsp"%>
									</div>
	
								</form:form>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid" style="padding-bottom: 2em;">
				<p class="margin-medium">
					<a id="buttonHome" class="btn btn-secondary" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a>
				</p>
			</div>
		</div>
	</div>
	<%@ include file="../include/portal-footer.jsp"%>
	<%@ include file="script-ricerca-elab-precedente.jsp"%>
</body>
