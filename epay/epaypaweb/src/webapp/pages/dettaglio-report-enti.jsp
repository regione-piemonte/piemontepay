<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../include/head.jsp"%>

<body>

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
				<li><s:a action="entry-ricerca-report-enti">Ricerca Enti</s:a><span class="divider"></span></li>
				
				<li class="active">Dettaglio</li>
			</ul>
		</div>
	</div>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<h3>Dettaglio</h3>

				<br/>


				<div class="step-content form-horizontal">
					<div class="step-pane active">
						<h4>Pagamento</h4>

						<div class="container-fluid">

							<div class="row-fluid">
								<label class="col-sm-4 control-label">Id univoco versamento (IUV):</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.iuv"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Causale:</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.causale"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Importo dovuto originale:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="pagamentoSelezionato.dettaglioPosizioneDebitoria.importoDovuto != null">
										<s:text name="format.money">
											<s:param name="value" value="pagamentoSelezionato.dettaglioPosizioneDebitoria.importoDovuto"/>
										</s:text>
									</s:if>
								</p>
							</div>
								<div class="row-fluid">
								<label class="col-sm-4 control-label">Importo dovuto (eventualmente) aggiornato e pagato:</label>
								<p class="col-sm-8 form-control-static">	
									<s:if test="pagamentoSelezionato.dettaglioPosizioneDebitoria.importoPagato != null">
										<s:text name="format.money">
											<s:param name="value" value="pagamentoSelezionato.dettaglioPosizioneDebitoria.importoPagato"/>
										</s:text>
									</s:if>
								</p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Descrizione causale versamento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.descrizioneCausaleVersamento"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data fine validit&agrave;:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="pagamentoSelezionato.dettaglioPosizioneDebitoria.dataScadenza != null">
										<s:text name="format.date">
											<s:param name="value" value="pagamentoSelezionato.dettaglioPosizioneDebitoria.dataScadenza"/>
										</s:text>
									</s:if>
								</p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Data pagamento:</label>
								<p class="col-sm-8 form-control-static">
									<s:if test="pagamentoSelezionato.dettaglioPosizioneDebitoria.dataPagamento != null">
										<s:text name="format.date">
											<s:param name="value" value="pagamentoSelezionato.dettaglioPosizioneDebitoria.dataPagamento"/>
										</s:text>
									</s:if>
								</p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Stato pagamento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.descrizioneStatoPagamento"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Pagamento spontaneo:</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.descrizionePagamentoSpontaneo"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Codice fiscale (Soggetto debitore):</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.identificativoUnivocoDebitore"/></p>
							</div>				
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Posizione debitoria esterna:</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.posizioneDebitoriaEsterna"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Descrizione rata:</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.descrizioneRata"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Anno riferimento:</label>
								<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.annoRiferimento"/></p>
							</div>						
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Note per pagatore:</label>
								<p class="col-sm-8 form-control-static note"><s:property value="pagamentoSelezionato.dettaglioPosizioneDebitoria.notePagatore"/></p>
							</div>
							<div class="row-fluid">
								<label class="col-sm-4 control-label">Costi di Notifica:</label>
								
								<p class="col-sm-8 form-control-static">
									<s:if test="pagamentoSelezionato.dettaglioPosizioneDebitoria.costiDiNotifica != null && pagamentoSelezionato.dettaglioPosizioneDebitoria.costiDiNotifica">
										Presenti
									</s:if>
									<s:else>
    									Non presenti
									</s:else>
								</p>
							</div>


						<br/>

						<s:if test="pagamentoSelezionato.dettaglioComponentiImporto.size() > 0">
							<div class="step-content form-horizontal">
								<div class="step-pane active">
									<h5>Componenti importo</h5>
			
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
											<s:iterator value="pagamentoSelezionato.dettaglioComponentiImporto" status="status">
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
											<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.cognome"/>
										</p>
									</div>
									<s:set name="tipologiaSoggetto" value="pagamentoSelezionato.dettaglioSoggettoDebitore.tipoSoggetto"/>
										<s:if
											test='%{#tipologiaSoggetto == "F"}'>
											<div class="row-fluid">
												<label class="col-sm-4 control-label">Nome:</label>
												<p class="col-sm-8 form-control-static">
													<s:property
														value="pagamentoSelezionato.dettaglioSoggettoDebitore.nome" />
												</p>
											</div>
										</s:if>

										<div class="row-fluid">
										<label class="col-sm-4 control-label">Codice fiscale/Partita Iva:</label>
										<p class="col-sm-8 form-control-static"><s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.identificativoUnivoco"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Tipo soggetto:</label>
										<p class="col-sm-8 form-control-static">
										
												<s:if
													test='%{#tipologiaSoggetto == "F"}'>
												PERSONA FISICA
											</s:if>
												<s:if
													test='%{#tipologiaSoggetto == "G"}'>
												PERSONA GIURIDICA
											</s:if>
											</p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Indirizzo:</label>
										<p class="col-sm-8 form-control-static">
										<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.indirizzo"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Civico:</label>
										<p class="col-sm-8 form-control-static">
										<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.civico"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">CAP:</label>
										<p class="col-sm-8 form-control-static">
										<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.cap"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Localit&agrave;:</label>
										<p class="col-sm-8 form-control-static">
										<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.localita"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Provincia:</label>
										<p class="col-sm-8 form-control-static">
										<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.provincia"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Nazione:</label>
										<p class="col-sm-8 form-control-static">
										<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.nazione"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">E-mail:</label>
										<p class="col-sm-8 form-control-static">
										<s:property value="pagamentoSelezionato.dettaglioSoggettoDebitore.email"/></p>
									</div>
								</div>
							</div>
						</div>
						
						

					</div>
				</div>

				<div class="margin-medium">
					<s:a action="show-main-menu" cssClass="btn">HOME</s:a> /
					<s:a action="entry-ricerca-report-enti" cssClass="btn">RICERCA ENTI</s:a>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<script src="js/epaypaweb/dettaglio-pagamento.js" type="text/javascript"></script>
<%-- 	<script src="js/epaypaweb/visualizza-dettaglio-enti.js" type="text/javascript"></script> --%>

</body>
</html>
