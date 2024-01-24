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
				<li><s:a action="entry-ricerca-listedicarico">Ricerca liste di carico</s:a><span class="divider"></span></li>
				<li class="active"><s:a action="entry-visualizza-listadicarico">Visualizza lista di carico</s:a><span class="divider"></span></li>
				<li class="active">Dettaglio posizione debitoria</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<h3>Dettaglio posizione debitoria</h3>

				<div class="step-content form-horizontal">
					<div class="step-pane active" id="memoParameters">
						<div class="accordion-heading">
							<h4>
								Testata
								<span class="pull-right clickable">
									<s:a
										data-toggle="collapse" href="#collapseMemoPanel"
										data-parent="#memoParameters"
										aria-expanded="true">
										<i class="glyphicon glyphicon-chevron-up"></i>
										<i class="glyphicon glyphicon-chevron-down"></i>
									</s:a>
								</span>
							</h4>
						</div>						
						<div id="collapseMemoPanel" class="collapse in">
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Lotto:</label>
								<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.idMessaggio"/>
							</div>
			
							<div class="row-fluid">
								<!--//TODO REMOVE 5 -->
								<label class="col-sm-4 control-label">Codice versamento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.codVersamento"/> - <s:property value="flussoSelezionato.desCodVersamento"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Multibeneficiario:</label>
								<p class="col-sm-8 form-control-static">
									<s:property value="multibeneficiario"/>
								</p>
							</div>
			
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Numero posizioni debitorie:</label>
								<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.numeroElementi"/></p>
							</div>
			
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Totale importo posizioni debitorie:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="flussoSelezionato.importoTotale != null">
										<s:text name="format.money">
											<s:param name="value" value="flussoSelezionato.importoTotale"/>
										</s:text>
									</s:if>
								</p>
							</div>
			
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Stato flusso:</label>
								<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.desStatoFlusso"/></p>
							</div>
			
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="flussoSelezionato.dataUltimaVariazione != null">
										<s:text name="format.date">
											<s:param name="value" value="flussoSelezionato.dataUltimaVariazione"/>
										</s:text>
									</s:if>
								</p>
							</div>
			
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Esito:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="flussoSelezionato.codEsito != null">
										<s:property value="flussoSelezionato.codEsito"/>
										<s:if test="flussoSelezionato.detEsito != null">
											-
										</s:if>
									</s:if>
									<s:if test="flussoSelezionato.detEsito != null">
										<s:property value="flussoSelezionato.detEsito"/>
									</s:if>
								</p>
							</div>
						</div>
					</div>
				</div>

				<br/>
				
				<s:if test="!primo || !ultimo">
					<div id="cursori" class="step-content">
						<div class="step-pane active">
							<h4>Scorri dettaglio</h4>
					
							<div class="row-fluid">
								<p>Per scorrere i dettagli delle <b>posizioni debitorie</b> utilizza i pulsanti &nbsp;&nbsp;&nbsp;&nbsp; 
									<s:if test="primo">
										<span class="btn disabled"><span class="glyphicon glyphicon-step-backward"></span></span>
										<span class="btn disabled"><span class="glyphicon glyphicon-triangle-left"></span></span>
									</s:if>
									<s:else>
										<s:a action="vai-alla-prima-posizionedebitoria" cssClass="btn">
											<s:param name="idPosizioneDebitoria"><s:property value="idPosizioneDebitoria"/></s:param>
											<span class="glyphicon glyphicon-step-backward"></span>
										</s:a>
										<s:a action="vai-alla-precedente-posizionedebitoria" cssClass="btn">
											<s:param name="idPosizioneDebitoria"><s:property value="idPosizioneDebitoria"/></s:param>
											<span class="glyphicon glyphicon-triangle-left"></span>
										</s:a>
									</s:else>								
									<s:if test="ultimo">
										<span class="btn disabled"><span class="glyphicon glyphicon-triangle-right"></span></span>
										<span class="btn disabled"><span class="glyphicon glyphicon-step-forward"></span></span>
									</s:if>
									<s:else>
										<s:a action="vai-alla-successiva-posizionedebitoria" cssClass="btn">
											<s:param name="idPosizioneDebitoria"><s:property value="idPosizioneDebitoria"/></s:param>
											<span class="glyphicon glyphicon-triangle-right"></span>
										</s:a>
										<s:a action="vai-alla-ultima-posizionedebitoria" cssClass="btn">
											<s:param name="idPosizioneDebitoria"><s:property value="idPosizioneDebitoria"/></s:param>
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
						<h4>Posizione debitoria</h4>
						<div class="container-fluid">
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Id univoco versamento (IUV):</label>
								<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.IUV"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Importo totale da pagare:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="posizioneDebitoria.importoTotale != null">
										<s:text name="format.money">
											<s:param name="value" value="posizioneDebitoria.importoTotale"/>
										</s:text>
									</s:if>
								</p>
							</div>

							<s:if test="multibeneficiario == 'SI'">
								<s:if test="posizioneDebitoria.importoPrincipale != null">
								<div class="row-fluid">
									<label class="col-sm-4 control-label">Importo Principale:</label>
									<p class="col-sm-8 form-control-static">
										<s:text name="format.money">
											<s:param name="value" value="posizioneDebitoria.importoPrincipale"/>
										</s:text>
									</p>
								</div>
								</s:if>
								<s:if test="posizioneDebitoria.importoSecondarioAltroEnte != null">
								<div class="row-fluid">
									<label class="col-sm-4 control-label">Importo Secondario:</label>
									<p class="col-sm-8 form-control-static">
										<s:text name="format.money">
											<s:param name="value" value="posizioneDebitoria.importoSecondarioAltroEnte"/>
										</s:text>
									</p>
								</div>
								</s:if>
							</s:if>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Descrizione causale versamento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.desCausaleVersamento"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data scadenza:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="posizioneDebitoria.dataScadenza != null">
										<s:text name="format.date">
											<s:param name="value" value="posizioneDebitoria.dataScadenza"/>
										</s:text>
									</s:if>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Codice fiscale/Partita Iva (Soggetto debitore):</label>
								<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.idUnivocoFiscale"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Posizione debitoria esterna:</label>
								<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.idPosizioneDebitoriaEsterna"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Descrizione rata:</label>
								<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.desRata"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data inizio validit&agrave;:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="posizioneDebitoria.dataInizioValidita != null">
										<s:text name="format.date">
											<s:param name="value" value="posizioneDebitoria.dataInizioValidita"/>
										</s:text>
									</s:if>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data fine validit&agrave;:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="posizioneDebitoria.dataFineValidita != null">
										<s:text name="format.date">
											<s:param name="value" value="posizioneDebitoria.dataFineValidita"/>
										</s:text>
									</s:if>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Anno riferimento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.annoRiferimento"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Codice avviso:</label>
								<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.codAvviso"/></p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Esito:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="posizioneDebitoria.codEsito != null">
										<s:property value="posizioneDebitoria.codEsito"/>
										<s:if test="posizioneDebitoria.detEsito != null">
											-
										</s:if>
									</s:if>
									<s:if test="posizioneDebitoria.detEsito != null">
										<s:property value="posizioneDebitoria.detEsito"/>
									</s:if>
								</p>
							</div>

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Note per pagatore:</label>
								<p class="col-sm-8 form-control-static note"><s:property value="posizioneDebitoria.notePerPagatore"/></p>
							</div>
						</div>
						
						<br/>

						<s:if test="posizioneDebitoria.componenteImportoDtoList.size() > 0">
							<div class="step-content form-horizontal">
								<div class="step-pane active">
									<h5>Componenti importo Principale</h5>
									<table class="table table-hover tab_left dataTable" id="tabellaComponentiImporto">
										<thead>
											<tr>
												<th>Importo</th>
												<th>Causale</th>
												<th>Anno accertamento</th>
												<th>Numero accertamento</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="posizioneDebitoria.componenteImportoDtoList" status="status" var="item">
												<s:if test="#item.flagComponenteSecondario == null || #item.flagComponenteSecondario == false">
													<tr role="row" class="<s:if test='#status.even'>even</s:if><s:else>odd</s:else>">
														<td>
															<s:text name="format.money">
																<s:param name="value" value="importo"/>
															</s:text>
														</td>
														<td><s:property value="causale"/></td>
														<td><s:property value="annoAccertamento"/></td>
														<td><s:property value="numeroAccertamento"/></td>
													</tr>
												</s:if>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</s:if>
						<br/>
						<s:if test="multibeneficiario == 'SI'">
							<div class="step-content">
								<div class="step-pane active">
									<h5>Componenti importo secondario</h5>
									<table class="table table-hover tab_left dataTable" id="tabellaComponentiImportoSecondario">
										<thead>
										<tr>
											<th>Importo</th>
											<th>Causale</th>
										</tr>
										</thead>
										<tbody>
										<s:iterator value="posizioneDebitoria.componenteImportoDtoList" status="status" var="item">
											<s:if test="#item.flagComponenteSecondario != null || #item.flagComponenteSecondario == true">
												<tr role="row" class="<s:if test='#status.even'>even</s:if><s:else>odd</s:else>">
													<td>
														<s:text name="format.money">
															<s:param name="value" value="importo"/>
														</s:text>
													</td>
													<td><s:property value="causale"/></td>
												</tr>
											</s:if>
										</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</s:if>
						<br/>
						<s:if test="posizioneDebitoria.riferimentoPagamentoDtoList.size() > 0">
							<div class="step-content form-horizontal">
								<div class="step-pane active">
									<h5>Riferimenti pagamento</h5>
			
									<table class="table table-hover tab_left dataTable" id="tabellaRiferimentiPagamento">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Valore</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="posizioneDebitoria.riferimentoPagamentoDtoList" status="status">
												<tr role="row" class="<s:if test='#status.even'>even</s:if><s:else>odd</s:else>">
													<td><s:property value="nome"/></td>
													<td><s:property value="valore"/></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</s:if>
						
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
											<s:if test="posizioneDebitoria.soggettoDebitore.tipologiaSoggettoEnum.name() == 'PERSONA_FISICA'">
												<s:property value="posizioneDebitoria.soggettoDebitore.cognome"/>
											</s:if>
											<s:if test="posizioneDebitoria.soggettoDebitore.tipologiaSoggettoEnum.name() == 'PERSONA_GIURIDICA'">
												<s:property value="posizioneDebitoria.soggettoDebitore.ragioneSociale"/>
											</s:if>
										</p>
									</div>

									<s:if test="posizioneDebitoria.soggettoDebitore.tipologiaSoggettoEnum.name() == 'PERSONA_FISICA'">
										<div class="row-fluid">
											<label class="col-sm-4 control-label">Nome:</label>
											<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.nome"/></p>
										</div>
									</s:if>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Codice fiscale/Partita Iva:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.idUnivocoFiscale"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Tipo soggetto:</label>
										<p class="col-sm-8 form-control-static" style="text-transform:uppercase"><strong><s:property value="posizioneDebitoria.soggettoDebitore.tipologiaSoggettoEnum.des"/></strong></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Indirizzo:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.indirizzo"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Civico:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.civico"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">CAP:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.cap"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Localit&agrave;:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.localita"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Provincia:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.provincia"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Nazione:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.nazione"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">E-mail:</label>
										<p class="col-sm-8 form-control-static"><s:property value="posizioneDebitoria.soggettoDebitore.email"/></p>
									</div>
								</div>
							</div>
						</div>

						<br/>

					</div>
				</div>

				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a> /
					<s:a action="entry-ricerca-listedicarico" cssClass="btn">RICERCA LISTE DI CARICO</s:a> /
					<s:a action="entry-visualizza-listadicarico" cssClass="btn">VISUALIZZA LISTA DI CARICO</s:a>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<script src="js/epaypaweb/dettaglio-posizionedebitoria.js" type="text/javascript"></script>

</body>
</html>
