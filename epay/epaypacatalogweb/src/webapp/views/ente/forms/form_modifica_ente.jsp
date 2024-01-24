<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:hidden path="id" />

<c:set var="w00" value="3" />
<c:set var="w01" value="9" />
<c:set var="w10" value="1" />
<c:set var="w11" value="3" />
<c:set var="w12" value="8" />
<c:set var="w02" value="2" />
<c:set var="w22" value="4" />

<div class="container-fluid">
	<div class="row-fluid">
		<div class="col-sm-6">
			<div class="col-md-12 step-content">
				<div class="step-pane active" id="filterPanel">
					<div class="accordion-heading">
						<h4>
							<spring:message code="ente.forms.formmodificaente.anagraficaente" /><span class="pull-right clickable"> </span>
						</h4>
					</div>
					<div id="collapseFilterPanel" class="collapse in">
						<br>
						<div class="form-group ">
							<label class="col-sm-${w00} control-label" for="codiceFiscale"><spring:message code="filter.ssnNumber" /></label>
							<div class="col-sm-${w01} controls">
								<form:input id="codiceFiscale" disabled="true"
									class="form-control input-codice-ente" path="codiceFiscale" maxlength="16" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="codiceFiscale" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label required"
								for="denominazione"><spring:message code="ente.forms.formmodificaente.denominazione" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="denominazione" class="form-control input-denominazione-ente"
									path="denominazione" maxlength="250" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="denominazione" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label required"
								   for="codiceIstat"><spring:message code="ente.forms.formmodificaente.codice.istat" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="codiceIstat" class="form-control" path="codiceIstat" maxlength="250" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="codiceIstat" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
                            <label class="col-sm-${w00} control-label required"
                                for="indirizzo"><spring:message code="ente.forms.formmodificaente.indirizzo" /> </label>
                            <div class="col-sm-${w01} controls">
                                <form:textarea id="indirizzo" class="form-control input-indirizzo" path="indirizzo"
                                    maxlength="70" row="2"/>
                            </div>
                            <div class="col-sm-${w00}"></div>
                            <div class="col-sm-${w01}">
                                <form:errors path="indirizzo" cssClass="error" />
                            </div>
                        </div>
                        
						<div class="form-group ">
							<label class="col-sm-${w00} control-label required" for="civico">
								<spring:message code="ente.forms.formmodificaente.civico" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="civico" class="form-control input-civico" path="civico"
									maxlength="16" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="civico" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label required"
								for="localita"><spring:message code="ente.forms.formmodificaente.localita" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="localita" class="form-control input-localita" path="localita"
									maxlength="100" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="localita" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label required" for="cap"><spring:message code="ente.forms.formmodificaente.cap" />
							</label>
							<div class="col-sm-${w01} controls">
								<form:input id="cap" class="form-control input-cap" path="cap"
									maxlength="5" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="cap" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label required"
								for="siglaProvincia"> <spring:message code="ente.forms.formmodificaente.siglaprovincia" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="siglaProvincia" class="form-control input-sigla-provincia"
									path="siglaProvincia" maxlength="2" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="siglaProvincia" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label" for="cbill">
								<spring:message code="ente.forms.formmodificaente.cbill" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="cbill" class="form-control input-cbill" path="cbill"
									maxlength="5" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="cbill" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label" for="email">
								<spring:message code="filter.email" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="email" class="form-control input-email" path="email"
									maxlength="250" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="email" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label" for="gs1Gln">
								<spring:message code="ente.forms.formmodificaente.gs1gln" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input type="number" id="gs1Gln" class="form-control input-gs1gln" path="gs1Gln"
									maxlength="13" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="gs1Gln" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label required" for="iban">
								<spring:message code="ibanTesoreria" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="iban" class="form-control view-uppercase input-iban" path="iban"
									maxlength="35" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="iban" cssClass="error" />
							</div>
						</div>
						
						<div class="form-group ">
							<label class="col-sm-${w00} control-label required" for="bic">
								<spring:message code="bic" /> </label>
							<div class="col-sm-${w01} controls">
								<form:input id="bic" class="form-control view-uppercase input-bic" path="bic"
									maxlength="11" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="bic" cssClass="error" />
							</div>
						</div>

						<div class="form-group ">
							<label class="col-sm-${w00} control-label required"
								for="logoEnteContent"><spring:message code="ente.forms.formmodificaente.logoente" /> </label>
							<div class="col-sm-${w01} controls upload">
								<div id="logoDetailsContainer" class="well"
									style="${not empty modifica_ente.logoFileName ? '' : 'display:none;'}">
									<div class="row">
										<div class="col-sm-8">
											<i class="glyphicon glyphicon-paperclip"></i> <span
												id="logoDetailsContainerName"> <c:if
													test="${not empty modifica_ente.logoFileName}">
													<c:out value="${modifica_ente.logoFileName}" />
												</c:if>
											</span> <br /> <span id="logoDetailsContainerSize"> <c:if
													test="${not empty modifica_ente.logoFileSize}">
													<fmt:formatNumber
														value="${modifica_ente.logoFileSize / 1024}"
														maxFractionDigits="2" /> <spring:message code="ente.forms.formmodificaente.kb" />
												</c:if>
											</span>
										</div>
										<div class="col-sm-4">
											<div class="logoDetailsContainerThumbnail">
												<img id="logoDetailsThumbnail" style="max-width: 80px;"
													class="pull-right"
													<c:if test="${not empty modifica_ente.logoFileName}">
														src="data:image/png;base64,${modifica_ente.logoContentEncoded}" 
													</c:if>
													<c:if test="${empty modifica_ente.logoFileName}">
														src=""
													</c:if> />
											</div>
										</div>
									</div>
								</div>
								<div id="noLogoDetailsContainer"
									style="${empty modifica_ente.logoFileName ? '' : 'display:none;'}">
									<spring:message code="validation.nofileselected" /></div>

								<div class="file btn btn-lg btn-action btn-primary"
									id="newLogoButton"><spring:message code="button.selectfile" /></div>

								<!-- 
								<a class="btn btn-lg btn-secondary btn-action" id="clearLogoButton"
									style="${empty modifica_ente.logoFileName ? 'display:none;' : ''}"
								>
									Cancella logo
								</a>
								 -->

								<input type="file" name="newLogo" id="newLogo" class="upload"
									accept="image/*">
								<form:hidden id="cancellaLogo" path="cancellaLogo" />
								<form:hidden id="logoAttuale" path="logoAttuale" />
							</div>
							<div class="col-sm-${w00}"></div>
							<div class="col-sm-${w01}">
								<form:errors path="logoContent" cssClass="error" />
							</div>
						</div>

						<sec:authorize access="hasRole('ASSISTENZA')">
                        <br>
                        
                        <div class="row-fluid  ">
                            <label class="col-sm-${w00} control-label" for="flagAdesioneCittaFacile"> <spring:message code="ente.forms.formmodificaente.attiva.adesione.a.cittafacile" /> </label>
                            <div class="col-sm-${w01} controls">
                                <form:checkbox id="flagAdesioneCittaFacile" class="checkbox-inline" path="flagAdesioneCittaFacile" />
                            </div>
                            <div class="col-sm-${w00}"></div>
                            <div class="col-sm-${w01}">
                                <form:errors path="flagAdesioneCittaFacile" cssClass="error" />
                            </div>
                        </div>
                        <br>
                        
                                              <div class="form-group " id = "templateEmailIdClass" 
                                              ${modifica_ente.flagAdesioneCittaFacile ? "" : " style='display: none;' "}>
                            <label class="col-sm-${w00} control-label required"
                                   for="templateEmailId"><spring:message code="ente.forms.formmodificaente.template.email.id" /> </label>
                            <div class="col-sm-${w01} controls">
                                <form:input id="templateEmailId" class="form-control" path="templateEmailId" maxlength="50" />
                            </div>
                            <div class="col-sm-${w00}"></div>
                            <div class="col-sm-${w01}">
                                <form:errors path="templateEmailId" cssClass="error" />
                            </div>
                        </div>
                        
                                           <div class="form-group " id = "urlDominioClass" 
                                           ${modifica_ente.flagAdesioneCittaFacile ? "" : " style='display: none;' "}>
                            <label class="col-sm-${w00} control-label required"
                                   for=urlDominio><spring:message code="ente.forms.formmodificaente.url.dominio" /> </label>
                            <div class="col-sm-${w01} controls">
                                <form:input id="urlDominio" class="form-control" path="urlDominio" maxlength="50" />
                            </div>
                            <div class="col-sm-${w00}"></div>
                            <div class="col-sm-${w01}">
                                <form:errors path="urlDominio" cssClass="error" />
                            </div>
                        </div>
                        
                                      <div class="form-group " id = "codiceIpaClass" 
                                           ${modifica_ente.flagAdesioneCittaFacile ? "" : " style='display: none;' "}>
                            <label class="col-sm-${w00} control-label required"
                                   for=codiceIpa><spring:message code="ente.forms.formmodificaente.codice.ipa" /> </label>
                            <div class="col-sm-${w01} controls">
                                <form:input id="codiceIpa" class="form-control" path="codiceIpa" maxlength="10" />
                            </div>
                            <div class="col-sm-${w00}"></div>
                            <div class="col-sm-${w01}">
                                <form:errors path="codiceIpa" cssClass="error" />
                            </div>
                        </div>
                         </sec:authorize>
                         
                         <sec:authorize access="not (hasRole('ASSISTENZA') )">
                         <form:hidden id="flagAdesioneCittaFacile" path="flagAdesioneCittaFacile" />
                         <form:hidden id="templateEmailId" path="templateEmailId" />
                         <form:hidden id="urlDominio" path="urlDominio" />
                         <form:hidden id="codiceIpa" path="codiceIpa" />
                                        
                                    </sec:authorize>
                        
                        
					</div>

				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="row-fluid">
				<div class="col-md-12 step-content">
					<div class="step-pane active" id="filterPanel">
						<div class="accordion-heading">
							<h4>
								<spring:message code="ente.forms.formmodificaente.configurazioneflussi" /><span class="pull-right clickable">
								</span>
							</h4>
						</div>
						<div id="collapseFilterPanel" class="collapse in">

							<br>
							
							<div class="form-group  ">
								<label class="col-sm-${w00} control-label"> <spring:message code="ente.forms.formmodificaente.attivaricezioneflussorendicontazione" /> </label>
								<div class="col-sm-${w01} controls">
									<form:checkbox id="flagRicezioneFlussoBaseRendicontazione"
										class="checkbox-inline"
										path="flagRicezioneFlussoBaseRendicontazione" />
								</div>
								<div class="col-sm-${w00}"></div>
								<div class="col-sm-${w01}">
									<form:errors path="flagRicezioneFlussoBaseRendicontazione"
										cssClass="error" />
								</div>
							</div>

							<br>

							<div class="conditional-controls" id="conditionalControls1"
								${modifica_ente.flagRicezioneFlussoBaseRendicontazione ? "" : " style='display: none;' "}>
								<div class="form-group  ">
									<div class="col-sm-${w10}"></div>
									<label class="col-sm-${w11} control-label"> <spring:message code="ente.forms.formmodificaente.qualsiasicodiceversamento" /> </label>
									<div class="col-sm-${w12} controls">
										<form:checkbox id="flagQualsiasiCodiceVersamento"
											class="checkbox-inline" path="flagQualsiasiCodiceVersamento" />

										<br /> <br /> <a data-toggle="modal"
											data-target="#modalGestioneFlussiSpecifici"
											class="btn btn-lg btn-action btn-primary ${modifica_ente.flagQualsiasiCodiceVersamento ? 'disabled' : ''}"
											id="btnGestioneFlussiSpecifici"> <spring:message code="ente.forms.formmodificaente.gestionecodiciversamento" /> </a>

									</div>
									<div class="col-sm-${w10}"></div>
									<div class="col-sm-${w11}"></div>
									<div class="col-sm-${w12}">
										<form:errors path="flagQualsiasiCodiceVersamento"
											cssClass="error" />
									</div>
								</div>
							</div>

							<br />

							<div class="form-group">
								<label class="col-sm-${w00} control-label"> <spring:message code="ente.forms.formmodificaente.enteplurintermediato" /> </label>
								<div class="col-sm-${w01} controls">
									<form:checkbox id="flagPlurintermediato"
										class="checkbox-inline" path="flagEntePlurintermediato" />
								</div>
								<div class="col-sm-${w00}"></div>
								<div class="col-sm-${w01}">
									<form:errors path="flagEntePlurintermediato" cssClass="error" />
								</div>
							</div>

							<br>

							<div class="form-group  ">
								<label class="col-sm-${w00} control-label"> <spring:message code="ente.forms.formmodificaente.attivaelaborazione" /> </label>
								<div class="col-sm-${w01} controls">
									<form:checkbox id="flagRiconciliazione" class="checkbox-inline"
										path="flagRiconciliazioneVersamenti" />
								</div>
								<div class="col-sm-${w00}"></div>
								<div class="col-sm-${w01}">
									<form:errors path="flagRiconciliazioneVersamenti"
										cssClass="error" />
								</div>
							</div>

							<br>

							<div class="conditional-controls" id="conditionalControls0"
								${modifica_ente.flagRiconciliazioneVersamenti ? "" : " style='display: none;' "}>
								<div class="form-group ">
									<div class="col-sm-${w10}"></div>
									<label class="col-sm-${w11} control-label required"
										for="periodSchedRiconcil"><spring:message code="ente.forms.formmodificaente.periodicitaschedulazioneelaborazione" /> </label>
									<div class="col-sm-${w12} controls">
										<form:select
											path="codicePeriodicitaSchedulazioneRiconciliazione"
											class="form-control input-periodicita-schedulazione-riconciliazione">
											<form:option value="" label="--- scegli ---" />
											<form:options
												items="${listaOpzioniPeriodicitaSchedulazioneRiconciliazione}"
												itemValue="codice" itemLabel="descrizione" />
										</form:select>
									</div>
									<div class="col-sm-${w10}"></div>
									<div class="col-sm-${w11}"></div>
									<div class="col-sm-${w12}">
										<form:errors
											path="codicePeriodicitaSchedulazioneRiconciliazione"
											cssClass="error" />
									</div>
								</div>

								<div class="form-group" id="containerGiornoSchedulazione">
									<div class="col-sm-${w10}"></div>
									<label class="col-sm-${w11} control-label required"
										for="giornoSchedulazione" id="labelGiornoSchedulazione">
										<spring:message code="ente.forms.formmodificaente.scegligiorno" /> </label>
									<div class="col-sm-${w12} controls">
										<form:input
											type="${not empty modifica_ente.codicePeriodicitaSchedulazioneRiconciliazione and modifica_ente.getCodicePeriodicitaSchedulazioneRiconciliazione().equals('ANN') ? 'text' : 'number'}"
											id="giornoSchedulazione" class="form-control input-giorno-schedulazione"
											path="giornoSchedulazione" autocomplete="false" />
									</div>
									<div class="col-sm-${w10}"></div>
									<div class="col-sm-${w11}"></div>
									<div class="col-sm-${w12}"
										id="containerGiornoSchedulazioneErrors">
										<form:errors path="giornoSchedulazione" cssClass="error" />
									</div>
								</div>

								<div class="form-group ">
									<div class="col-sm-${w10}"></div>
									<label class="col-sm-${w11} control-label required"
										for="tipologiaAccertamento"><spring:message code="ente.forms.formmodificaente.tipologiaaccertamento" /></label>
									<div class="col-sm-${w12} controls">
										<form:select path="codiceTipologiaAccertamento"
											class="form-control input-tipologia-accertamento">
											<form:option value="" label="--- scegli ---" />
											<form:options items="${listaOpzioniTipologiaAccertamento}"
												itemValue="codice" itemLabel="descrizione" />
										</form:select>
									</div>
									<div class="col-sm-${w10}"></div>
									<div class="col-sm-${w11}"></div>
									<div class="col-sm-${w12}">
										<form:errors path="codiceTipologiaAccertamento"
											cssClass="error" />
									</div>
								</div>

								<div class="form-group  ">
									<div class="col-sm-${w10}"></div>
									<label class="col-sm-${w11} control-label"> <spring:message code="ente.forms.formmodificaente.accertamentoobbligatorio" /> </label>
									<div class="col-sm-${w12} controls">
										<form:checkbox id="flagAccertamento" class="checkbox-inline"
											path="flagAccertamento" />
									</div>
									<div class="col-sm-${w10}"></div>
									<div class="col-sm-${w11}"></div>
									<div class="col-sm-${w12}">
										<form:errors path="flagAccertamento" cssClass="error" />
									</div>
								</div>

								<div class="form-group ">
									<div class="col-sm-${w10}"></div>
									<label class="col-sm-${w11} control-label required"
										for="modalAcquisizProvvisori"><spring:message code="ente.forms.formmodificaente.modalitaacquisizioneprovvisori" /></label>
									<div class="col-sm-${w12} controls">
										<form:select path="codiceModalitaAcquisizioneProvvisori"
											class="form-control input-modalita-acquisizione-provvisori">
											<form:option value="" label="--- scegli ---" />
											<form:options
												items="${listaOpzioniModalitaAcquisizioneProvvisori}"
												itemValue="codice" itemLabel="descrizione" />
										</form:select>
									</div>
									<div class="col-sm-${w10}"></div>
									<div class="col-sm-${w11}"></div>
									<div class="col-sm-${w12}">
										<form:errors path="codiceModalitaAcquisizioneProvvisori"
											cssClass="error" />
									</div>
								</div>

								<div class="form-group  ">
									<div class="col-sm-${w10}"></div>
									<label class="col-sm-${w11} control-label"> <spring:message code="ente.forms.formmodificaente.feedbackerrorielaborazione" /> </label>
									<div class="col-sm-${w12} controls">
										<form:checkbox id="flagRicezErrori" class="checkbox-inline"
											path="flagRicezioneErrori" />
									</div>
									<div class="col-sm-${w10}"></div>
									<div class="col-sm-${w11}"></div>
									<div class="col-sm-${w12}">
										<form:errors path="flagRicezioneErrori" cssClass="error" />
									</div>
								</div>

							</div>
						</div>
					</div>

				</div>

				<div class="modal" tabindex="-1" role="dialog"
					id="modalGestioneFlussiSpecifici">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title"><spring:message code="ente.forms.formmodificaente.gestionecodiciversamento" /> </h4>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-5">
										<label for="codiciVersamentoNonSelezionati"><spring:message code="ente.forms.formmodificaente.codiciversamentogestibili" /></label> <select
											name="codiciVersamentoNonSelezionati"
											id="codiciVersamentoNonSelezionati"
											size="${modifica_ente.codiciVersamentoSelezionati.size() + modifica_ente.codiciVersamentoNonSelezionati.size()}"
											multiple="multiple" class="form-control"
											style="height: auto !important;">
											<c:forEach var="ass"
												items="${modifica_ente.codiciVersamentoNonSelezionati}">
												<option
													<c:if test="${false and ass.selezionata}"><spring:message code="ente.forms.formmodificaente.selected" /></c:if>
													value="${ass.oggetto.id}">${ass.oggetto.codice}-
													${ass.oggetto.descrizione}</option>
											</c:forEach>
										</select>

									</div>

									<div class="col-sm-2" style="padding: 0px;">

										<button type="button" class="btn btn-block"
											id="buttonAddCodiciVersamento">
											<spring:message code="button.addall" /> <i class="glyphicon glyphicon-forward"></i>
										</button>
										<button type="button" class="btn btn-block"
											id="buttonAddCodiceVersamento">
											<spring:message code="button.addselected" /> <i
												class="glyphicon glyphicon-chevron-right"></i>
										</button>
										<button type="button" class="btn btn-block"
											id="buttonSubCodiceVersamento">
											<i class="glyphicon glyphicon-chevron-left"></i>
											<spring:message code="button.removeselected" />
										</button>
										<button type="button" class="btn btn-block"
											id="buttonSubCodiciVersamento">
											<i class="glyphicon glyphicon-backward"></i> 
											<spring:message code="button.removeall" />
										</button>
									</div>

									<div class="col-sm-5">
										<label for="codiciVersamentoSelezionati">
										<spring:message code="ente.forms.formmodificaente.codiciversamentogestiti" />
										</label> <select name="codiciVersamentoSelezionati"
											id="codiciVersamentoSelezionati"
											size="${modifica_ente.codiciVersamentoSelezionati.size() + modifica_ente.codiciVersamentoNonSelezionati.size()}"
											multiple="multiple" class="form-control"
											style="height: auto !important;">
											<c:forEach var="ass"
												items="${modifica_ente.codiciVersamentoSelezionati}">
												<option
													<c:if test="${false and ass.selezionata}"><spring:message code="ente.forms.formmodificaente.selected" /></c:if>
													value="${ass.oggetto.id}">${ass.oggetto.codice}-
													${ass.oggetto.descrizione}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary btn-action"
									data-dismiss="modal"><spring:message code="button.close" /></button>
							</div>
						</div>
					</div>
				</div>

				<div style="display: none;"
					id="idCodiciVersamentoSelezionatiContainer">
					<c:forEach var="cv" items="${listaOpzioniCodiciVersamento}">
						<form:hidden id="idCodiciVersamentoSelezionati${cv.id}"
							path="idCodiciVersamentoSelezionati[${cv.id}]"
							value="${modifica_ente.idCodiciVersamentoSelezionati[cv.id]}" />
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<br>
		<div class="col-md-12">
			<label class="col-sm-${w02} control-label"
				for="descrizioneUtenteAmministratore"><spring:message code="utenteamministratore" />
			</label>
			<div class="col-sm-${w22} controls">
				<form:input id="descrizioneUtenteAmministratore" disabled="true"
					class="form-control input-utente-amministratore" path="descrizioneUtenteAmministratore" />
			</div>
		</div>
	</div>
</div>
