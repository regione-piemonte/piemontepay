<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../include/head.jsp"%>

<body>

	<%@ include file="../include/modal-confirm.jsp"%>
	<%@ include file="../include/modal-alert.jsp"%>

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
				<li><a id="backToHomeAction1">Home</a><span class="divider"></span></li>
				<s:if test="!origineHomePerInserimento">
					<li><a id="backToRicercaListeAggiornamentoPosizioniDebitorieAction1">Ricerca liste aggiornamento posizioni debitorie</a><span class="divider"></span></li>
				</s:if>
				<li><a id="backToVisualizzaFlussoPosizioniDebitorieDaAggiornare1"><s:property value="operativita.des"/> lista aggiornamento posizioni debitorie</a><span class="divider"></span></li>
				<li class="active"><s:property value="operativita.des"/> posizione debitoria da aggiornare</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:form id="formId" action="salva-posizionedebitoriadaaggiornare.do" method="POST" cssClass="form-horizontal" theme="bootstrap">
					<div id="alert-div"></div>

					<%@ include file="../include/validation-message.jsp"%>

					<h3><s:property value="operativita.des"/> posizione debitoria da aggiornare</h3>
					<s:hidden id="origineHomePerInserimento" name="origineHomePerInserimento"/>
					<s:hidden id="operativita" name="operativita"/>
					<s:hidden name="idFlusso"/>
					<s:hidden name="addendoElemento"/>

					<div class="step-content">
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
									<label class="col-sm-3 control-label">Lotto:</label>
									<p class="col-sm-9 form-control-static"><s:property value="idMessaggio"/>
									<s:hidden name="idMessaggio"/>
								</div>

								<div class="row-fluid">
									<label class="col-sm-3 control-label">Codice versamento:</label>
									<p class="col-sm-9 form-control-static">
										<s:iterator value="sessionContext.codiciVersamento" var="item">
											<s:if test="#item.id == idCodVersamento">
												<s:property value="#item.cod"/> - <s:property value="#item.des"/>
											</s:if>
										</s:iterator>
									</p>
									<s:hidden name="idCodVersamento"/>
								</div>

								<div class="row-fluid">
									<label class="col-sm-3 control-label">Multibeneficiario:</label>
									<p class="col-sm-9 form-control-static"><s:property value="multibeneficiario"/>
								</div>

								<div class="row-fluid">
									<label class="col-sm-3 control-label">Numero posizioni debitorie:</label>
									<p class="col-sm-9 form-control-static"><s:property value="numeroElementi"/></p>
									<s:hidden name="numeroElementi"/>
								</div>
							</div>
						</div>
					</div>

					<br/>

					<div class="step-content">
						<div class="step-pane active">
							<h4>Posizione debitoria</h4>
							<div class="container-fluid">
								<div class="row-fluid">
									<div class="col-sm-3">
									</div>
									<div class="col-sm-6">
										<span id="s-nf" class="hide" style="color: red;"><b>Posizione Debitoria inesistente per il codice versamento selezionato: <span id="s-nf-cov"></span></b></span>
										<span id="s-nu" class="hide" style="color: red;"><b>Posizione Debitoria non aggiornabile, stato: <span id="s-nu-stato"></span></b></span>
									</div>
								</div>
								<div class="row-fluid" style="padding-bottom:13px">
									<s:radio name="aggiornamentoIdTipo" label="Tipo aggiornamento"
										labelposition="inline" labelCssClass="col-sm-3"
										elementCssClass="col-sm-3" list="aggiornamentoIdTipoList" listKey="cod"
										listValue="option"/>
								</div>
								<hr/>
								<div id="label-container" class="row-fluid hide" style="margin-bottom: 35px; margin-top: 20px;">
									<div class="col-sm-3">
										<b>&Egrave; necessario valorizzare almeno un campo tra quelli seguenti.</b>
									</div>
								</div>
								<hr/>
								<div class="row-fluid">
									<s:select name="idPosizioneDebitoriaEsterna" maxlength="50" autocomplete="off"
										label="Posizione debitoria esterna*" labelCssClass="col-sm-3" cssClass="span5 basicAutoComplete"
										list="fakeList" listKey="cod" id="idPosizioneDebitoriaEsterna"
										listValue="option" headerKey="" headerValue=""
									/>
								</div>
								<div id="iuv-container" class="hide">
									<div class="row-fluid">
										<s:textfield name="iuv" maxlenghth="35" autocomplete="off"
												 label="Id univoco versamento (IUV)" labelCssClass="col-sm-3" cssClass="span5 basicAutoCompleteIUV"
												 id="iuv"
										/>
									</div>
								</div>
								<div class="row-fluid">
									<input type="button" value="Carica" id="caricaAction" class="btn btn-primary pull-right hide"/>
								</div>
								<div class="row-fluid" id="div_motivazione">
									<s:textfield name="motivazione" maxlength="50"
										label="Motivazione*" labelCssClass="col-sm-3" cssClass="span5" />
								</div>

								<div id="divAggiornamentoAnnullamento" class="hide">
								</div>
								<div id="div1AggiornamentoModifica" class="hide">
									<div class="row-fluid">
										<s:if test="activateValorizzareAlmenoUnCampoTraQuelliSeguenti">
											<p style="color:#a94442">ATTENZIONE! &Egrave; necessario valorizzare almeno un campo tra quelli seguenti.</p>
										</s:if>
										<s:else>
											<p><i>&Egrave; necessario valorizzare almeno un campo tra quelli seguenti.</i></p>
										</s:else>
									</div>

									<div class="row-fluid">
										<s:textfield name="strDataInizioValidita"
											label="Data inizio validita" labelCssClass="col-sm-3" cssClass="span2 datepicker"/>
									</div>

									<div class="row-fluid">
										<s:textfield name="strDataFineValidita"
											label="Data fine validita" labelCssClass="col-sm-3" cssClass="span2 datepicker"/>
									</div>

									<div class="row-fluid">
										<s:textfield name="strDataScadenza"
											label="Data scadenza" labelCssClass="col-sm-3" cssClass="span2 datepicker"/>
									</div>

									<div class="row-fluid">
										<s:textfield name="strCompimpImportoTotale" maxlength="12"
											label="Importo totale da pagare" labelCssClass="col-sm-3" cssClass="span2"/>
									</div>

									<s:if test="multibeneficiario == 'SI'">
										<div class="row-fluid">
											<s:textfield name="strImportoPrincipale" maxlength="12"
												 label="Importo Principale" labelCssClass="col-sm-3" cssClass="span2"/>
										</div>
										<div class="row-fluid">
											<s:textfield name="strImportoSecondario" disabled="true"
														 label="Importo Secondario" labelCssClass="col-sm-3" cssClass="span2"/>
										</div>
									</s:if>

									<div class="row-fluid">
										<s:textfield name="desCausaleVersamento" maxlength="100"
											label="Descrizione causale versamento" labelCssClass="col-sm-3" cssClass="span10" />
									</div>
								</div>
							</div>

							<br/>

							<div id="div2AggiornamentoModifica" class="hide">
								<div class="step-content">
									<div class="step-pane active">
										<h5>Componenti importo Principale</h5>
										<table class="table table-hover tab_left dataTable" id="tabellaComponentiImporto">
											<thead>
												<tr>
													<th>Importo</th>
													<th>Causale</th>
													<th>Anno accertamento</th>
													<th>Numero accertamento</th>
													<th class="tab_Right span1">&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr role="row">
													<s:hidden id="compimpId0" name="compimpId0"/>
													<td><s:textfield id="strCompimpImporto0" name="strCompimpImporto0" maxlength="12" cssClass="span6"/></td>
													<td><s:textfield id="compimpCausale0" name="compimpCausale0" maxlength="80" cssClass="span10" /></td>
													<td><s:textfield id="strCompimpAnnoAccert0" name="strCompimpAnnoAccert0" maxlength="4" cssClass="span6"/></td>
													<td><s:textfield id="strCompimpNumeroAccert0" name="strCompimpNumeroAccert0" maxlength="35" cssClass="span10" /></td>
													<td><input type="button" id="eliminaComponenteImporto0" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="compimpId1" name="compimpId1"/>
													<td><s:textfield id="strCompimpImporto1" name="strCompimpImporto1" maxlength="12" cssClass="span6" /></td>
													<td><s:textfield id="compimpCausale1" name="compimpCausale1" maxlength="80" cssClass="span10" /></td>
													<td><s:textfield id="strCompimpAnnoAccert1" name="strCompimpAnnoAccert1" maxlength="4" cssClass="span6"/></td>
													<td><s:textfield id="strCompimpNumeroAccert1" name="strCompimpNumeroAccert1" maxlength="35" cssClass="span10" /></td>
													<td><input type="button" id="eliminaComponenteImporto1" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="compimpId2" name="compimpId2"/>
													<td><s:textfield id="strCompimpImporto2" name="strCompimpImporto2" maxlength="12" cssClass="span6" /></td>
													<td><s:textfield id="compimpCausale2" name="compimpCausale2" maxlength="80" cssClass="span10" /></td>													
													<td><s:textfield id="strCompimpAnnoAccert2" name="strCompimpAnnoAccert2" maxlength="4" cssClass="span6"/></td>
													<td><s:textfield id="strCompimpNumeroAccert2" name="strCompimpNumeroAccert2" maxlength="35" cssClass="span10" /></td>
													<td><input type="button" id="eliminaComponenteImporto2" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="compimpId3" name="compimpId3"/>
													<td><s:textfield id="strCompimpImporto3" name="strCompimpImporto3" maxlength="12" cssClass="span6" /></td>
													<td><s:textfield id="compimpCausale3" name="compimpCausale3" maxlength="80" cssClass="span10" /></td>
													<td><s:textfield id="strCompimpAnnoAccert3" name="strCompimpAnnoAccert3" maxlength="4" cssClass="span6"/></td>
													<td><s:textfield id="strCompimpNumeroAccert3" name="strCompimpNumeroAccert3" maxlength="35" cssClass="span10" /></td>
													<td><input type="button" id="eliminaComponenteImporto3" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="compimpId4" name="compimpId4"/>
													<td><s:textfield id="strCompimpImporto4" name="strCompimpImporto4" maxlength="12" cssClass="span6" /></td>
													<td><s:textfield id="compimpCausale4" name="compimpCausale4" maxlength="80" cssClass="span10" /></td>
													<td><s:textfield id="strCompimpAnnoAccert4" name="strCompimpAnnoAccert4" maxlength="4" cssClass="span6"/></td>
													<td><s:textfield id="strCompimpNumeroAccert4" name="strCompimpNumeroAccert4" maxlength="35" cssClass="span10" /></td>
													<td><input type="button" id="eliminaComponenteImporto4" class="btn" value="elimina"/></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<s:if test="multibeneficiario == 'SI'">
								<br>
								<div class="step-content">
									<div class="step-pane active">
										<h5>Componenti importo Secondario</h5>
										<table class="table table-hover tab_left dataTable" id="tabellaComponentiImportoSecondario">
											<thead>
											<tr>
												<th>Importo*</th>
												<th>Causale*</th>
												<th class="tab_Right span1">&nbsp;</th>
											</tr>
											</thead>
											<tbody>
											<tr role="row">
												<s:hidden id="compimpId0Secondario" name="compimpId0Secondario"/>
												<td><s:textfield id="strCompimpImporto0Secondario" name="strCompimpImporto0Secondario" maxlength="12" cssClass="span6"/></td>
												<td><s:textfield id="compimpCausale0Secondario" name="compimpCausale0Secondario" maxlength="80" cssClass="span10" /></td>
												<td><input type="button" id="eliminaComponenteImporto0Secondario" class="btn" value="elimina"/></td>
											</tr>
											</tbody>
										</table>
									</div>
								</div>
								<br>
								</s:if>
							</div>
							<div id="div3AggiornamentoModifica" class="hide">
								<div class="step-content">
									<div class="step-pane active">
										<h5>Riferimenti pagamento - Dati aggiuntivi da utilizzare secondo le indicazioni del manuale</h5>
										<table class="table table-hover tab_left dataTable" id="tabellaRiferimentiPagamento">
											<thead>
												<tr>
													<th>Nome</th>
													<th>Valore</th>
													<th class="tab_Right span1">&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr role="row">
													<s:hidden id="rifpagId0" name="rifpagId0"/>
													<td><s:textfield id="rifpagNome0" name="rifpagNome0" maxlength="70" cssClass="span12"/></td>
													<td><s:textfield id="rifpagValore0" name="rifpagValore0" maxlength="70" cssClass="span12"/></td>
													<td><input type="button" id="eliminaRiferimentoPagamento0" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="rifpagId1" name="rifpagId1"/>
													<td><s:textfield id="rifpagNome1" name="rifpagNome1" maxlength="70" cssClass="span12"/></td>
													<td><s:textfield id="rifpagValore1" name="rifpagValore1" maxlength="70" cssClass="span12"/></td>
													<td><input type="button" id="eliminaRiferimentoPagamento1" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="rifpagId2" name="rifpagId2"/>
													<td><s:textfield id="rifpagNome2" name="rifpagNome2" maxlength="70" cssClass="span12"/></td>
													<td><s:textfield id="rifpagValore2" name="rifpagValore2" maxlength="70" cssClass="span12"/></td>
													<td><input type="button" id="eliminaRiferimentoPagamento2" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="rifpagId0" name="rifpagId3"/>
													<td><s:textfield id="rifpagNome3" name="rifpagNome3" maxlength="70" cssClass="span12"/></td>
													<td><s:textfield id="rifpagValore3" name="rifpagValore3" maxlength="70" cssClass="span12"/></td>
													<td><input type="button" id="eliminaRiferimentoPagamento3" class="btn" value="elimina"/></td>
												</tr>
												<tr role="row">
													<s:hidden id="rifpagId4" name="rifpagId4"/>
													<td><s:textfield id="rifpagNome4" name="rifpagNome4" maxlength="70" cssClass="span12"/></td>
													<td><s:textfield id="rifpagValore4" name="rifpagValore4" maxlength="70" cssClass="span12"/></td>
													<td><input type="button" id="eliminaRiferimentoPagamento4" class="btn" value="elimina"/></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>

							<br/>
							<div id="div4AggiornamentoModifica" class="hide">
							<div class="step-pane active">
								<h5>Soggetto debitore (1)</h5>

									<div class="row-fluid" style="padding-bottom:13px">
											<s:select id="soggettoIdTipo_id" name="soggettoIdTipo"
												label="Tipo soggetto" labelCssClass="col-sm-3"
												cssClass="span3"
												list="soggettoIdTipoList" listKey="cod"
												listValue="option" headerKey="" headerValue=""/>
										</div>


									<div id="divPersonaFisica" class="hide">
										<div class="row-fluid">
											<s:textfield id="soggettoIdUnivocoFiscalePersonaFisica" name="soggettoIdUnivocoFiscalePersonaFisica" maxlength="35"
												label="Codice fiscale*" labelCssClass="col-sm-3" cssClass="span3" onblur="controllaCF()"/>
										</div>

										<div class="row-fluid">
											<s:textfield id="soggettoCognome" name="soggettoCognome" maxlength="70"
												label="Cognome*" labelCssClass="col-sm-3" cssClass="span3" />

										</div>

										<div class="row-fluid">
											<s:textfield id="soggettoNome" name="soggettoNome" maxlength="70"
												label="Nome*" labelCssClass="col-sm-3" cssClass="span3" />
										</div>
									</div>
									<div id="divPersonaGiuridica" class="hide">
										<div class="row-fluid">
											<s:textfield id="soggettoIdUnivocoFiscalePersonaGiuridica" name="soggettoIdUnivocoFiscalePersonaGiuridica" maxlength="35"
												label="Codice fiscale/Partita Iva*" labelCssClass="col-sm-3" cssClass="span3" onblur="controllaPIVA()" />
										</div>

										<div class="row-fluid">
											<s:textfield id="soggettoRagioneSociale" name="soggettoRagioneSociale" maxlength="70"
												label="Ragione sociale*" labelCssClass="col-sm-3" cssClass="span3" />
										</div>
									</div>
									<div id="divPersonaDatiFacoltativi" class="hide">
                                    <div class="row-fluid">
										<s:textfield name="soggettoIndirizzo" maxlength="70" id="soggettoIndirizzo"
											label="Indirizzo" labelCssClass="col-sm-3" cssClass="span4" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoCivico" maxlength="16" id="soggettoCivico"
											label="Civico" labelCssClass="col-sm-3" cssClass="span1" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoCap" maxlength="16" id="soggettoCap"
											label="CAP" labelCssClass="col-sm-3" cssClass="span2" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoLocalita" maxlength="35" id="soggettoLocalita"
											label="Localit&agrave;" labelCssClass="col-sm-3" cssClass="span2" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoProvincia" maxlength="35" id="soggettoProvincia"
											label="Provincia" labelCssClass="col-sm-3" cssClass="span2" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoNazione" maxlength="2" id="soggettoNazione"
											label="Nazione" labelCssClass="col-sm-3" cssClass="span1" />
									</div>

									<div class="row-fluid">
										<s:textfield id="soggettoEmailId" name="soggettoEmail" maxlength="256"
											label="E-mail" labelCssClass="col-sm-3" cssClass="span4" onblur="controllaMail()"/>
									</div>

                                </div>
								</div>
								<div class="row-fluid">
									<p ><b>(1)&nbsp;</b><span style="color:#a94442">L'aggiornamento delle posizioni che comprende campi di anagrafica e' possibile solo se:</span></p>
								    <p >1&nbsp; &nbsp; &nbsp; <span style="color:#a94442" >le posizioni debitorie risultano pagabili;</span></p>
								    <p >2&nbsp; &nbsp; &nbsp; <span style="color:#a94442" >i campi 'Nome', 'Cognome' e 'Codice Fiscale' sono attualmente valorizzati come 'ANONIMO';</span></p>
								    <p >3&nbsp; &nbsp; &nbsp; <span style="color:#a94442" >oltre a Tipo Soggetto vengono inseriti gli altri elementi obbligatori dell'anagrafica (Nome,Cognome, Codice Fiscale in caso di Persona Fisica - Ragione Sociale e Piva in caso di Persona Giuridica)</span></p>
							    </div>
							</div>
							</div>


							<div class="row-fluid">
								<div class="margin-medium">
								  		<a id="ripristinaAction" class="btn">Ripristina</a>

								</div>
							</div>
						</div>
					</div>

					<div class="margin-medium">
						<a id="backToHomeAction2" class="btn">HOME</a>
						<s:if test="!origineHomePerInserimento">
							/ <a id="backToRicercaListeAggiornamentoPosizioniDebitorieAction2" class="btn">RICERCA FLUSSI</a>
						</s:if>
						<a id="backToVisualizzaFlussoPosizioniDebitorieDaAggiornare2" class="btn"><span style="text-transform:uppercase"><s:property value="operativita.des"/></span> FLUSSO</a>
						<s:submit id="salvaAction" value="Salva" cssClass="btn btn-primary pull-right"/>
					</div>
				</s:form>
			</div>
		</div>
	</div>

	<s:form id="backToHomeForm" action="show-main-menu.do" method="POST" theme="simple"/>
	<s:form id="backToRicercaListeAggiornamentoPosizioniDebitorieForm" action="entry-ricerca-listeaggiornamentoposizionidebitorie.do" method="POST" theme="simple"/>
	<s:form id="backToVisualizzaFlussoPosizioniDebitorieDaAggiornareForm" action="entry-visualizza-flusso-posizionidebitoriedaaggiornare.do" method="POST" theme="simple"/>
	<s:form id="ripristinaForm" action="ripristina_posizionedebitoriadaaggiornare.do" method="POST" theme="simple">
		<s:hidden name="origineHomePerInserimento"/>
		<s:hidden name="operativita"/>
		<s:hidden name="idPosizioneDebitoriaDaAggiornare"/>
	</s:form>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
	<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>

	<script src="js/epaypaweb/crea-or-modifica-posizionedebitoriadaaggiornare.js" type="text/javascript"></script>
	<script src="js/bootstrap-autocomplete/bootstrap-autocomplete_custom.min.js" type="text/javascript"></script>

	<script>

	function controllaMail()
	{
		var error = '';
		var mail = document.getElementById("soggettoEmailId");
		if( mail.value == '' )  return '';
		if( ! /^.+@.+/.test(mail.value) )
		{
		error = 'Formato mail non corrretto.';
		}
		 if (error!='')
			 alertAction(error);

	}

		function controllaPIVA() {
			var error = '';

			var pi = document.getElementById("soggettoIdUnivocoFiscalePersonaGiuridica");
			if (pivalue == '') return '';
			pi.value = pi.value.toUpperCase();
			if (!/^[0-9]{11}$/.test(pi.value)) {
				error = 'La partita IVA deve contenere 11 cifre.';
			} else {
				var s = 0;
				for (i = 0; i <= 9; i += 2)
					s += pi.value.charCodeAt(i) - '0'.charCodeAt(0);
				for (var i = 1; i <= 9; i += 2) {
					var c = 2 * (pi.value.charCodeAt(i) - '0'.charCodeAt(0));
					if (c > 9) c = c - 9;
					s += c;
				}
				var atteso = (10 - s % 10) % 10;
				if (atteso != pi.value.charCodeAt(10) - '0'.charCodeAt(0))
					error = 'La partita IVA non e' valida: il codice di controllo non corrisponde.';
				else error = '';
			}

			if (error != '') {
				alertAction(error);
			}
		}

		function controllaCF() {
			var error = '';
			var cf = document.getElementById("soggettoIdUnivocoFiscalePersonaFisica");
			error = validaCodiceFiscale(cf.value);
			if (error != '')
				alertAction(error);
		}


		function validaCodiceFiscale(cf)
		      {
		          var validi, i, s, set1, set2, setpari, setdisp;
		          if( cf == '' )  return '';
		          cf = cf.toUpperCase();
		          if( cf.length != 16 )
		              return 'Il codice fiscale deve essere composto da 16 caratteri.';
		          validi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		          for( i = 0; i < 16; i++ ){
		              if( validi.indexOf( cf.charAt(i) ) == -1 )
		                  return 'Il codice fiscale contiene caratteri non validi. ';
		          }
		          set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		          set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
		          setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		          setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
		          s = 0;
		          for( i = 1; i <= 13; i += 2 )
		              s += setpari.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
		          for( i = 0; i <= 14; i += 2 )
		              s += setdisp.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
		          if( s%26 != cf.charCodeAt(15)-'A'.charCodeAt(0) )
		              return 'Il codice fiscale non e' valido: il codice di controllo non corrisponde. ';
		          return '';
		      }

		function cleanAllFields() {
			$("#formId_strDataInizioValidita").val("");
			$("#formId_strDataFineValidita").val("");
			$("#formId_strDataScadenza").val("");
			$("#formId_strCompimpImportoTotale").val("");
			$("#formId_desCausaleVersamento").val("");
			$("#compimpId0").val("");
			$("#strCompimpImporto0").val("");
			$("#compimpCausale0").val("");
			$("#compimpDatiSpec0").val("");
			$("#strCompimpAnnoAccert0").val("");
			$("#strCompimpNumeroAccert0").val("");
			$("#compimpId1").val("");
			$("#strCompimpImporto1").val("");
			$("#compimpCausale1").val("");
			$("#compimpDatiSpec1").val("");
			$("#strCompimpAnnoAccert1").val("");
			$("#strCompimpNumeroAccert1").val("");
			$("#compimpId2").val("");
			$("#strCompimpImporto2").val("");
			$("#compimpCausale2").val("");
			$("#compimpDatiSpec2").val("");
			$("#strCompimpAnnoAccert2").val("");
			$("#strCompimpNumeroAccert2").val("");
			$("#compimpId3").val("");
			$("#strCompimpImporto3").val("");
			$("#compimpCausale3").val("");
			$("#compimpDatiSpec3").val("");
			$("#strCompimpAnnoAccert3").val("");
			$("#strCompimpNumeroAccert3").val("");
			$("#compimpId4").val("");
			$("#strCompimpImporto4").val("");
			$("#compimpCausale4").val("");
			$("#compimpDatiSpec4").val("");
			$("#strCompimpAnnoAccert4").val("");
			$("#strCompimpNumeroAccert4").val("");
			$("#rifpagId0").val("");
			$("#rifpagNome0").val("");
			$("#rifpagValore0").val("");
			$("#rifpagId1").val("");
			$("#rifpagNome1").val("");
			$("#rifpagValore1").val("");
			$("#rifpagId2").val("");
			$("#rifpagNome2").val("");
			$("#rifpagValore2").val("");
			$("#rifpagId3").val("");
			$("#rifpagNome3").val("");
			$("#rifpagValore3").val("");
			$("#rifpagId4").val("");
			$("#rifpagNome4").val("");
			$("#rifpagValore4").val("");
			$("#soggettoIdTipo_id").val("");
			$("#soggettoIdUnivocoFiscalePersonaGiuridica").val("");
			$("#soggettoRagioneSociale").val("");
			$("#soggettoIndirizzo").val("");
			$("#soggettoIdUnivocoFiscalePersonaFisica").val("");
			$("#soggettoCognome").val("");
			$("#soggettoNome").val("");
			$("#soggettoCivico").val("");
			$("#soggettoCap").val("");
			$("#soggettoLocalita").val("");
			$("#soggettoProvincia").val("");
			$("#soggettoNazione").val("");
			$("#soggettoEmailId").val("");
			$("#divPersonaFisica").addClass("hide");
			$("#divPersonaGiuridica").addClass("hide");
			$("#divPersonaDatiFacoltativi").addClass("hide");
			$("#compimpId0Secondario").val("");
			$("#strCompimpImporto0Secondario").val("");
			$("#compimpCausale0Secondario").val("");
			$("#formId_strImportoPrincipale").val("");
			$("#formId_strImportoSecondario").val("");
		}
		var resultStringArray = [];
		var resultLongArray = [];
		var idLongSelected = null;
		var idStringSeleceted = null;
		function getPosDebDaAgg(id) {
			$.ajax({
				type: "POST",
				url: "autocomplete_get_posizione_debitoria_esterna.do?idPosDebDaAgg=" + id,
				success: function (data) {

				},
				error: function () {
					console.log("ERROR on autocomplete_get_posizione_debitoria_esterna");
				}
			});
		}
		$('.basicAutoComplete').autoComplete({
			minLength: 3,
			resolverSettings: {
				url: "autocomplete_posizione_debitoria_esterna.do",
				requestThrottling: 1000,
			},
			events: {
				searchPre: function (newValue) {
					return newValue;
				},
				searchPost: function (resultFromServer) {
					resultStringArray = resultFromServer.result;
					resultLongArray = resultFromServer.resultIds;
					return resultStringArray;
				},
			},
			noResultsText: "Non sono stati trovati risultati"
		});
		var pre = "";
		$('.basicAutoCompleteIUV').autoComplete({
			minLength: 3,
			resolverSettings: {
				url: "autocomplete_iuv.do",
				requestThrottling: 1000,
			},
			events: {
				searchPre: function (newValue) {
					pre = newValue;
					return newValue;
				},
				searchPost: function (resultFromServer) {
					$('.basicAutoCompleteIUV').autoComplete('set', {value: pre, text: pre}); // serve per non fare sbiancare l'autocomplete in caso di no match
					$("input[name='iuv_text']").val(pre);
					return resultFromServer.result;
				},
			},
			noResultsText: "Non sono stati trovati risultati"
		});
		$("#idPosizioneDebitoriaEsterna").on("autocomplete.select", function (evt, item) {
			$('input[name="idPosizioneDebitoriaEsterna"]').val( $("#idPosizioneDebitoriaEsterna").val() ); //imp workaround autocomplete vs struts2
			// $('.basicAutoCompleteIUV').autoComplete('set', {value: "", text: ""})
			$("input[name='iuv_text']").val("");
			if ( $("input[name='aggiornamentoIdTipo']").filter(":checked").val() === 'A' ) {
				cleanAllFields();
				return;
			}
			if ( "" === item || undefined === item ) {
				cleanAllFields();
				return;
			}
			// for (let i = 0; i < resultStringArray.length; i++) {
			// 	if (item === resultStringArray[i]) {
			// 		const idPosizione = resultLongArray[i];
			// 		getPosDebDaAgg(idPosizione);
			// 		idLongSelected = idPosizione;
			// 		idStringSeleceted = resultStringArray[i];
			// 		break;
			// 	}
			// }
		});
		$("#aggiornamentoIdTipo-1").on("click", function() {
			cleanAllFields();
		});

	idStringSeleceted = "<s:property value="#session.idStringSeleceted" />";
	idLongSeleceted = "<s:property value="#session.idLongSeleceted" />";
	$( document ).ready(function() {
		if ("" !== idStringSeleceted) {
			$('#idPosizioneDebitoriaEsterna').autoComplete('set', {value: idLongSelected, text: idStringSeleceted});
			$('input[name="idPosizioneDebitoriaEsterna"]').val( $("#idPosizioneDebitoriaEsterna").val() );
		}
		$("#strCompimpImporto0Secondario").on("change keyup", function() {
			$("#formId_strImportoSecondario").val( $("#strCompimpImporto0Secondario").val() );
		});

	});
	</script>
</body>
</html>
