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
				<li class="active"><spring:message code="lock.ricerca.ricercalockapplicativi"/></li>
			</ul>
		</div>
	</div>

	<div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<%@ include file="form-ricerca.jsp"%>

					<div class="container-fluid">
						<div class="row">

							<c:if test="${lista_risultati_vuota}">
								<div class="col-sm-12">
									<p class="alert alert-warning"><spring:message code="lock.ricerca.nessunrisultato"/></p>
								</div>
							</c:if>

							<c:if test="${not empty lista_risultati.risultati}">
							
								<form:form 
									id="lista_risultati"
									name="lista_risultati"
									action="${context}/lock/elimina" 
									method="post"
									class="" 
									modelAttribute="lista_risultati"
									>
							
									<div class="col-sm-12">
										<%@ include file="tabella-risultati.jsp"%>
									</div>
	
									<div class="col-sm-12"
										style="margin-top: 0.5em; margin-bottom: 0.5em;">
										<button id="btnRielaboraBottom" type="button" disabled
											class="btnRielabora btn btn-primary pull-right">
											<spring:message code="lock.ricerca.eliminalockselezionati"/></button>
	
										<a id="formSubmitButtonPulisci" href="${context}/lock/pulisci"
											class="btn btn-secondary"><spring:message code="button.pulisci"/></a> 
											
										<input style="display:none;"
											id="formSubmitButtonElimina" type="submit"
											name="Cerca" class="btn btn-primary pull-right">
											<spring:message code="button.elimina"/>
										</input>
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
	<%@ include file="script-ricerca.jsp"%>
</body>
