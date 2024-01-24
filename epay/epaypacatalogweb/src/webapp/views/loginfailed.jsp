<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="./include/head.jsp"%>

<body>

	<p class="nascosto">
		<a title="A-sommario"></a>
	</p>
	<ul id="sommario" class="nascosto">
		<li><a href="#A-contenuti"><spring:message code="summary.saltacontenuti" /></a></li>
	</ul>
	<hr />

	<div class="banner">
		<%@ include file="./include/portal-header.jsp"%>
		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb">
					<li>
						<a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
					</li>
					<li class="active"><spring:message code="alert.error" /></li>
				</ul>
			</div>
		</div>
			
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="contentPage">
					<div class="container-fluid">
						<a title="A-contenuti"></a>
					</div>

					<p>&nbsp;</p>
					<div class="context container-fluid">
						<div class="row-fluid">
							<div class="contentPage">
								<p class="">
									<spring:message code="alert.notallowedaccess" />
								</p>
								<p class="alert alert-danger">
									<c:out value="${message}" />
								</p>
							</div>
							
							<a class="btn btn-default btn-action" href="${context}/j_security_logout""><spring:message code="text.esci" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="./include/portal-footer.jsp"%>
</body>
