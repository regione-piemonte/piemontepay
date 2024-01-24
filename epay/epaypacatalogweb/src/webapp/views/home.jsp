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
					<li class="active"><spring:message code="text.home" /></li>
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
									
									<sec:authorize access="(hasRole('MODIFICA_ENTE') and hasRole('AMMINISTRATORE_ENTE')) or hasRole('RICERCA_VOCE_ENTRATA') or hasRole('RICERCA_CODICE_VERSAMENTO') or hasRole('RICERCA_RIFERIMENTO_CONTABILE')">
									<div class="btnIndex">
										<div class="iconBoxCata" >
											<div class="TitleBox"><spring:message code="home.gestioneente"></spring:message></div>
										</div>
										<div class="txtDefModuliCata">
											
											<sec:authorize access="hasRole('MODIFICA_ENTE') and hasRole('AMMINISTRATORE_ENTE')"> 
											<a href="${context}/enti/modifica"> 
												<span class="bullPointModuliCata"></span>
												<span>
													<spring:message code="home.ricercaente">
													</spring:message>
												</span>
											</a>
											<p class="divisBoxModuliCata"></p>
											</sec:authorize>
											
											<sec:authorize access="hasRole('RICERCA_VOCE_ENTRATA')">
											<a href="${context}/voci-entrata/ricerca"> 
												<span class="bullPointModuliCata"></span>
												<span>
													<spring:message code="home.ricercavocientrata">
													</spring:message>
												</span>
											</a>
											<p class="divisBoxModuliCata"></p>
											</sec:authorize>
											
											<sec:authorize access="hasRole('RICERCA_CODICE_VERSAMENTO')">
											<a href="${context}/codici-versamento/ricerca"> 
												<span class="bullPointModuliCata"></span>
												<span>
													<spring:message code="home.codiciversamento" />
												</span>
											</a>
											<p class="divisBoxModuliCata"></p>
											</sec:authorize>
											
											<sec:authorize access="hasRole('RICERCA_RIFERIMENTO_CONTABILE')">
											<a href="${context}/riferimenti-contabili/ricerca"> 
												<span class="bullPointModuliCata"></span>
												<span>
													<spring:message code="home.riferimenticontabilicodiceversamento" />
												</span>
											</a>
											<p class="divisBoxModuliCata"></p>
											</sec:authorize>
											
										</div>
									</div>
									</sec:authorize>
									
									<sec:authorize access="(hasRole('RICERCA_UTENTE') or hasRole('AUTORIZZA_CODICE_VERSAMENTO') or hasRole('AUTORIZZA_FUNZIONE')) and hasRole('AMMINISTRATORE_ENTE')">
									<div class="btnIndex">
										<div class="iconBoxUsrCata">
											<div class="TitleBox"><spring:message code="home.profilazioneutente"></spring:message></div>
										</div>
										<div class="txtDefModuliCata">
										
											<sec:authorize access="hasRole('RICERCA_UTENTE')"> 
											<a href="${context}/utenti/ricerca"> <span
												class="bullPointModuliCata"></span><span></span>
												<span>
												<spring:message code="home.gestioneutenti"></spring:message>
												</span>
											</a>
											<p class="divisBoxModuliCata"></p>
											</sec:authorize>
											
											<sec:authorize access="hasRole('AUTORIZZA_CODICE_VERSAMENTO')">
											<a href="${context}/autorizzazione-codici-versamento/ricerca"> <span
												class="bullPointModuliCata"></span><span></span><span>
												<spring:message code="home.autorizzazionicodiciversamento"></spring:message></span>
											</a>
											<p class="divisBoxModuliCata"></p>
											</sec:authorize>
											
											<sec:authorize access="hasRole('AUTORIZZA_FUNZIONE')">
											<a href="${context}/autorizzazione-funzioni/ricerca"> <span
												class="bullPointModuliCata"></span><span></span><span>
												<spring:message code="home.autorizzazionifunzione"></spring:message></span>
											</a>
											<p class="divisBoxModuliCata"></p>
											</sec:authorize>
											
										</div>
									</div>
									</sec:authorize>

									<sec:authorize access="not ( ( (hasRole('RICERCA_UTENTE') or hasRole('AUTORIZZA_CODICE_VERSAMENTO') or hasRole('AUTORIZZA_FUNZIONE')) and hasRole('AMMINISTRATORE_ENTE') ) or ( (hasRole('MODIFICA_ENTE') and hasRole('AMMINISTRATORE_ENTE')) or hasRole('RICERCA_VOCE_ENTRATA') or hasRole('RICERCA_CODICE_VERSAMENTO') or hasRole('RICERCA_RIFERIMENTO_CONTABILE') ) )">
										<p class="alert alert-warning">
											<spring:message code="alert.nonautorizzato"></spring:message>
											<br/>
											<br/>
											<spring:message code="alert.contattareassistenza"></spring:message>
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
