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
		<li><a href="#A-contenuti">Salta ai contenuti</a></li>
	</ul>
	<hr />

	<div class="banner">

		<%@ include file="./include/portal-header.jsp"%>

		<div class="row-fluid">
			<div class="span12">
				<ul class="breadcrumb">
					<li class="active">Home</li>
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
									<sec:authorize access="(hasRole('RICERCA_PROVVISORIO') or hasRole('RICERCA_ELABORAZIONE') or hasRole('RICERCA_FLUSSI'))">
										<div class="btnIndex">
											<div class="iconBoxMod">
												<div class="TitleBox">MODULO DI RICONCILIAZIONE</div>
											</div>
											<div class="txtDefModuliMod">
												<sec:authorize access="hasRole('RICERCA_ELABORAZIONE')">
													<a href="${context}/elaborazione/ricercaElaborazionePrecedente"> <span class="bullPointModuliMod"></span> <span> RICERCA ELABORAZIONI </span>
													</a>
													<p class="divisBoxModuliMod"></p>
												</sec:authorize>
												<sec:authorize access="hasRole('RICERCA_PROVVISORIO')">
													<a href="${context}/provvisori/ricerca"> <span class="bullPointModuliMod"></span> <span> GESTIONE PROVVISORI </span>
													</a>
													<p class="divisBoxModuliMod"></p>
												</sec:authorize>
													<p class="divisBoxModuliMod"></p>
											</div>
										</div>
									</sec:authorize>

									<sec:authorize access="not (hasRole('RICERCA_PROVVISORIO') or hasRole('RICERCA_ELABORAZIONE') or hasRole('RICERCA_FLUSSI'))">
										<p class="alert alert-warning">
											Non sei autorizzato a nessuna delle funzionalit&agrave; disponibili sull'applicativo. <br /> <br /> Contatta l'assistenza per richiedere le autorizzazioni necessarie.
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
