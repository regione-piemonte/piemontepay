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
		<li><a href="#A-contenuti"><spring:message code="sommario.saltacontenuti"/></a></li>
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
					<li class="active"><spring:message code="breadcrumb.accessonegato"/></li>
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
								<spring:message code="loginfailed.accessonegato"/>
							</div>
							
							<a class="btn btn-default" href="www.sistemapiemonte.it/cms/privati/">
							<spring:message code="loginfailed.ritornaalportale"/></a>
						</div>
					</div>
					<br />
				</div>
			</div>
		</div>
	</div>
	<%@ include file="./include/portal-footer.jsp"%>
</body>
