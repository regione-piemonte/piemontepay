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
					<li class="active"><spring:message code="home" /></li>
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
								<div class="ContainerIndexDefault">
									<sec:authorize access="(hasRole('INSERISCI_PROVVISORI') or hasRole('RICERCA_PROVVISORI') or hasRole('RICERCA_ERRORI') or hasRole('RICERCA_FLUSSO') or hasRole('IMPOSTA_ESITO_FLUSSO'))">
										<div class="btnIndex">
											<div class="iconBoxSim">
												<div class="TitleBox">
													<spring:message code="home.gestioneente" />
												</div>
											</div>
											<div class="txtDefModuliSim">
												<sec:authorize access="hasRole('INSERISCI_PROVVISORI')">
												
												<div class="container-fluid">
													<div class="row">
														<div class="col-xs-2">
															<span class="bullPointModuliSim"></span> 
														</div>
														<div class="col-xs-10">
															<a href="${context}/provvisori/inserisci">
																<span><spring:message code="home.provvisori.inserisci" /></span>		
															</a>
														</div>
													</div>
												</div>
												<p class="divisBoxModuliSim"></p>
												</sec:authorize>

												<sec:authorize access="hasRole('RICERCA_PROVVISORI')">
													<div class="container-fluid">
														<div class="row">
															<div class="col-xs-2">
																<span class="bullPointModuliSim"></span> 
															</div>
															<div class="col-xs-10">
																<a href="${context}/provvisori/ricerca">
																	<span><spring:message code="home.provvisori.ricerca" /></span>
																</a>
															</div>
														</div>
													</div>
													<p class="divisBoxModuliSim"></p>
												</sec:authorize>

												<sec:authorize access="hasRole('RICERCA_ERRORI')">
												
												<div class="container-fluid">
													<div class="row">
														<div class="col-xs-2">
															<span class="bullPointModuliSim"></span> 
														</div>
														<div class="col-xs-10">
															<a href="${context}/errori/ricerca">	
																<span> <spring:message code="home.flussi.errore.ricerca"/></span>
															</a>
														</div>
													</div>
												</div>
												<p class="divisBoxModuliSim"></p>
												</sec:authorize>

												<sec:authorize access="hasRole('RICERCA_FLUSSO')">
												<div class="container-fluid">
													<div class="row">
														<div class="col-xs-2">
															<span class="bullPointModuliSim"></span> 
														</div>
														<div class="col-xs-10">
															<a href="${context}/flussi/ricerca">
																<span><spring:message code="home.flussi.ricerca"/></span>
															</a>
														</div>
													</div>
												</div>
												<p class="divisBoxModuliSim"></p>
												</sec:authorize>

												<sec:authorize access="hasRole('IMPOSTA_ESITO_FLUSSO')">
													<div class="container-fluid">
														<div class="row">
															<div class="col-xs-2">
																<span class="bullPointModuliSim"></span> 
															</div>
															<div class="col-xs-10">
																<a href="${context}/flussi/ricerca">
																	<span><spring:message code="home.flussi.imposta"/></span>
																</a>	
															</div>
														</div>
													</div>
													<p class="divisBoxModuliSim"></p>
												</sec:authorize>
											</div>
										</div>
									</sec:authorize>
									<sec:authorize access="not (hasRole('INSERISCI_PROVVISORI') or hasRole('RICERCA_PROVVISORI') or hasRole('RICERCA_ERRORI') or hasRole('RICERCA_FLUSSO') or hasRole('IMPOSTA_ESITO_FLUSSO'))">
										<p class="alert alert-warning">
											<spring:message code="security.error" /><br /> <br /> <spring:message code="security.help" />
										</p>
									</sec:authorize>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="./include/portal-footer.jsp"%>
</body>
