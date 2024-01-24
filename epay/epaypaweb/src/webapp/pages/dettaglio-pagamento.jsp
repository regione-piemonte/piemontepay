<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../include/head.jsp"%>

<body onload="if (<s:property value='cursorClicked' />) location.hash = '#cursori'">

	<p class="nascosto">
		<a title="A-sommario"></a>
	</p>
	<ul id="sommario" class="nascosto">
		<li><s:a href="#A-contenuti">Salta ai contenuti</s:a></li>
	</ul>
	<hr />

	<div class="container-fluid-banner">
		<%@ include file="../include/portal-header.html"%>
		<%@ include file="navbar-login.jsp"%>
		<a title="A-contenuti"></a>
	</div>

	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><s:a action="show-main-menu">Home</s:a><span class="divider"></span></li>
				<li><s:a action="entry-ricerca-flussi-pagamenti">Ricerca flussi notifiche di pagamento</s:a><span class="divider"></span></li>
				
				<!-- ------------------------------ -->
				<!-- RDI-048 - START -->
				<!-- ------------------------------ -->	
				<!--		
				<li class="active"><s:a action="entry-visualizza-flusso-pagamenti">Visualizza flusso notifiche di pagamento</s:a><span class="divider"></span></li>
				 -->
				<!-- ------------------------------ -->
				<!-- RDI-048 - STOP -->
				<!-- ------------------------------ -->		
				<li class="active">Dettaglio notifica di pagamento</li>
			</ul>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<h3>Dettaglio notifica di pagamento</h3>

				<%@ include file="../include/panel-flusso-pagamenti.jsp"%>

				<br/>

				<s:if test="!primo || !ultimo">
					<div id="cursori" class="step-content">
						<div class="step-pane active">
							<h4>Scorri dettaglio</h4>
					
							<div class="row-fluid">
								<p>Per scorrere i dettagli delle <b>notifiche di pagamento</b> utilizza i pulsanti &nbsp;&nbsp;&nbsp;&nbsp;
									<s:if test="primo">
										<span class="btn disabled"><span class="glyphicon glyphicon-step-backward"></span></span>
										<span class="btn disabled"><span class="glyphicon glyphicon-triangle-left"></span></span>
									</s:if>
									<s:else>
										<s:a action="vai-al-primo-pagamento" cssClass="btn">
											<s:param name="idNotificaPagamento"><s:property value="idNotificaPagamento"/></s:param>
											<span class="glyphicon glyphicon-step-backward"></span>
										</s:a>
										<s:a action="vai-al-precedente-pagamento" cssClass="btn">
											<s:param name="idNotificaPagamento"><s:property value="idNotificaPagamento"/></s:param>
											<span class="glyphicon glyphicon-triangle-left"></span>
										</s:a>
									</s:else>
									<s:if test="ultimo">
										<span class="btn disabled"><span class="glyphicon glyphicon-triangle-right"></span></span>
										<span class="btn disabled"><span class="glyphicon glyphicon-step-forward"></span></span>
									</s:if>
									<s:else>
										<s:a action="vai-al-successivo-pagamento" cssClass="btn">
											<s:param name="idNotificaPagamento"><s:property value="idNotificaPagamento"/></s:param>
											<span class="glyphicon glyphicon-triangle-right"></span>
										</s:a>
										<s:a action="vai-all-ultimo-pagamento" cssClass="btn">
											<s:param name="idNotificaPagamento"><s:property value="idNotificaPagamento"/></s:param>
											<span class="glyphicon glyphicon-step-forward"></span>
										</s:a>
									</s:else>
								</p>
							</div>
						</div>
					</div>

					<br/>
				</s:if>

				<div class="step-content form-horizontal">
					<div class="step-pane active">
						<h4>Notifica di pagamento</h4>

						<div class="container-fluid">
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Posizione debitoria:</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.idPosizioneDebitoria"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Id univoco versamento (IUV):</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.IUV"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Id univoco riscossione (IUR):</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.IUR"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Importo pagato:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="notificaPagamento.importoPagato != null">
										<s:text name="format.money">
											<s:param name="value" value="notificaPagamento.importoPagato"/>
										</s:text>
									</s:if>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data esito pagamento:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="notificaPagamento.dataEsitoPagamento != null">
										<s:text name="format.date">
											<s:param name="value" value="notificaPagamento.dataEsitoPagamento"/>
										</s:text>
									</s:if>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Descrizione causale versamento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.desCausaleVersamento"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Codice fiscale/Partita Iva (Soggetto debitore):</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.idUnivocoFiscale"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Cognome e Nome/Ragione sociale (Soggetto debitore):</label>
								<p class="col-sm-8 form-control-static">
									<s:property value="notificaPagamento.soggettoDebitore.cognomeNomeOrRagioneSociale"/>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data scadenza:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="notificaPagamento.dataScadenza != null">
										<s:text name="format.date">
											<s:param name="value" value="notificaPagamento.dataScadenza"/>
										</s:text>
									</s:if>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Anno riferimento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.annoRiferimento"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Codice fiscale/Partita Iva (Soggetto versante):</label>
								<s:if test="notificaPagamento.soggettoVersante != null">
									<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.idUnivocoFiscale"/></p>
								</s:if>
							</div>
		
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Cognome e Nome/Ragione sociale (Soggetto versante):</label>
								<s:if test="notificaPagamento.soggettoVersante != null">
									<p class="col-sm-8 form-control-static">
										<s:property value="notificaPagamento.soggettoVersante.cognomeNomeOrRagioneSociale"/>
									</p>
								</s:if>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Dati specifici riscossione:</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.datiSpecificiRiscossione"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Note:</label>
								<p class="col-sm-8 form-control-static note"><s:property value="notificaPagamento.note"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Codice avviso:</label>
								<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.codAvviso"/></p>
							</div>
						</div>

						<br/>

						<div class="step-content form-horizontal">
							<div class="step-pane active" id="soggettoDebitorePanel">
								<div class="accordion-heading">
									<h5>
										Soggetto debitore
										<span class="pull-right clickable">
											<s:a
												data-toggle="collapse" href="#collapseSoggettoDebitorePanel"
												data-parent="#soggettoDebitorePanel"
												aria-expanded="true">
												<i class="glyphicon glyphicon-chevron-up"></i>
												<i class="glyphicon glyphicon-chevron-down"></i>
											</s:a>
										</span>
									</h5>
								</div>
								<div id="collapseSoggettoDebitorePanel" class="collapse in">
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Cognome/Ragione sociale:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="notificaPagamento.soggettoDebitore.tipologiaSoggettoEnum.name() == 'PERSONA_FISICA'">
												<s:property value="notificaPagamento.soggettoDebitore.cognome"/>
											</s:if>
											<s:if test="notificaPagamento.soggettoDebitore.tipologiaSoggettoEnum.name() == 'PERSONA_GIURIDICA'">
												<s:property value="notificaPagamento.soggettoDebitore.ragioneSociale"/>
											</s:if>
										</p>
									</div>

									<s:if test="notificaPagamento.soggettoDebitore.tipologiaSoggettoEnum.name() == 'PERSONA_FISICA'">
										<div class="row-fluid">
											<label class="col-sm-4 control-label">Nome:</label>
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.nome"/></p>
										</div>
									</s:if>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Codice fiscale/Partita Iva:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.idUnivocoFiscale"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Tipo soggetto:</label>
										<p class="col-sm-8 form-control-static" style="text-transform:uppercase"><strong><s:property value="notificaPagamento.soggettoDebitore.tipologiaSoggettoEnum.des"/></strong></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Indirizzo:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.indirizzo"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Civico:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.civico"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">CAP:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.cap"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Localit&agrave;:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.localita"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Provincia:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.provincia"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Nazione:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.nazione"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">E-mail:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoDebitore.email"/></p>
									</div>
								</div>
							</div>
						</div>
						
						<br/>

						<div class="step-content form-horizontal">
							<div class="step-pane active" id="soggettoVersantePanel">
								<div class="accordion-heading">
									<h5>
										Soggetto versante
										<span class="pull-right clickable">
											<s:a
												data-toggle="collapse" href="#collapseSoggettoVersantePanel"
												data-parent="#soggettoVersantePanel"
												aria-expanded="true">
												<i class="glyphicon glyphicon-chevron-up"></i>
												<i class="glyphicon glyphicon-chevron-down"></i>
											</s:a>
										</span>
									</h5>
								</div>
								<div id="collapseSoggettoVersantePanel" class="collapse in">
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Cognome/Ragione sociale:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static">
												<s:if test="notificaPagamento.soggettoVersante.tipologiaSoggettoEnum.name() == 'PERSONA_FISICA'">
													<s:property value="notificaPagamento.soggettoVersante.cognome"/>
												</s:if>
												<s:if test="notificaPagamento.soggettoVersante.tipologiaSoggettoEnum.name() == 'PERSONA_GIURIDICA'">
													<s:property value="notificaPagamento.soggettoVersante.ragioneSociale"/>
												</s:if>
											</p>
										</s:if>
									</div>

									<s:if test="notificaPagamento.soggettoVersante != null">
										<s:if test="notificaPagamento.soggettoVersante.tipologiaSoggettoEnum.name() == 'PERSONA_FISICA'">
											<div class="row-fluid">
												<label class="col-sm-4 control-label">Nome:</label>
												<s:if test="notificaPagamento.soggettoVersante != null">
													<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.nome"/></p>
												</s:if>
											</div>
										</s:if>
									</s:if>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Codice fiscale/Partita Iva:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.idUnivocoFiscale"/></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Tipo soggetto:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static" style="text-transform:uppercase"><strong><s:property value="notificaPagamento.soggettoVersante.tipologiaSoggettoEnum.des"/></strong></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Indirizzo:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.indirizzo"/></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Civico:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.civico"/></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">CAP:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.cap"/></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Localit&agrave;:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.localita"/></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Provincia:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.provincia"/></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Nazione:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.nazione"/></p>
										</s:if>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">E-mail:</label>
										<s:if test="notificaPagamento.soggettoVersante != null">
											<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.soggettoVersante.email"/></p>
										</s:if>
									</div>
								</div>
							</div>
						</div>
						
						<br/>

						<div class="step-content form-horizontal">
							<div class="step-pane active">
								<h5>Prestatore del servizio di pagamento - PSP</h5>
		
								<div class="container-fluid">
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Id PSP:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.idPsp"/> - <s:property value="notificaPagamento.ragioneSocialePsp"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Tipo versamento:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.codTipoVersamento"/> - <s:property value="notificaPagamento.desTipoVersamento"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Id flusso transazione PSP:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.idFlussoRendicontazionePsp"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Data avvio transazione:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="notificaPagamento.dataAvvioTransazione != null">
												<s:text name="format.date">
													<s:param name="value" value="notificaPagamento.dataAvvioTransazione"/>
												</s:text>
											</s:if>
										</p>
									</div>
<!--		
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Data autorizzazione:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="notificaPagamento.dataOraAutorizzazione != null">
												<s:text name="format.date">
													<s:param name="value" value="notificaPagamento.dataOraAutorizzazione"/>
												</s:text>
											</s:if>
										</p>
									</div>
		
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Tipo sicurezza:</label>
										<p class="col-sm-8 form-control-static"><s:property value="notificaPagamento.tipoSicurezza"/></p>
									</div>
-->
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Importo transato:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="notificaPagamento.importoTransato != null">
												<s:text name="format.money">
													<s:param name="value" value="notificaPagamento.importoTransato"/>
												</s:text>
											</s:if>
										</p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Importo commissioni:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="notificaPagamento.importoCommissioni != null">
												<s:text name="format.money">
													<s:param name="value" value="notificaPagamento.importoCommissioni"/>
												</s:text>
											</s:if>
										</p>
									</div>
								</div>
							</div>
						</div>
						
						<br/>

					</div>
				</div>

				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a> /
					<s:a action="entry-ricerca-flussi-pagamenti" cssClass="btn">RICERCA FLUSSI</s:a>
				
				<!-- ------------------------------ -->
				<!-- RDI-048 - START -->
				<!-- ------------------------------ -->	
				<!--		
					<s:a action="entry-visualizza-flusso-pagamenti" cssClass="btn">VISUALIZZA FLUSSO</s:a>
				-->
				<!-- ------------------------------ -->
				<!-- RDI-048 - STOP -->
				<!-- ------------------------------ -->	
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<script src="js/epaypaweb/dettaglio-pagamento.js" type="text/javascript"></script>

</body>
</html>
