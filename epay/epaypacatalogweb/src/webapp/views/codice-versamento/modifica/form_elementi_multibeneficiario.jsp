<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="2" />
<c:set var="w01" value="10" />
<c:set var="w04" value="4" />
<c:set var="w05" value="2" />



		<div class ="col-sm-12" id= "alertNoOpzioniEnteSecondarioId"  >
	 <p class="alert alert-warning">
			               <spring:message code="codiceversamento.modifica.alert.no.opzioni.ente.secondario" />
	</p>
	</div>

									<div class="container-fluid">
										<div class="row-fluid">
											<div class="form-group " style="height: 20px;">
													<label class="col-sm-2 control-label" style="text-align: left" for="flagElementiMultibeneficiario">
													<spring:message code="flagElementiMultibeneficiario" /></label>
						              			<div class="col-sm-2 controls">
							          				<form:checkbox class="checkbox-inline" path="flagElementiMultibeneficiario" id="flagElementiMultibeneficiario" readonly="true" disabled="true"/>				
						             			</div>
												<div class="col-sm-2"></div>
												<div class="col-sm-10">
													<form:errors path="flagElementiMultibeneficiario" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									
									<div class="container-fluid  mb-1" id  = "elementiMultibeneficiarioId" >
										<div class="row-fluid">
											<div class="form-group ">	
											<label class="col-sm-2 control-label required"   for="modalitaAssociazioneMultibeneficiario"><spring:message code="modalitaAssociazioneMultibeneficiario" /></label>
												<div class="col-sm-10 controls">
													<form:select path="modalitaAssociazioneMultibeneficiario"
														class="form-control input-modalita-associazione-multibeneficiario input-inline">
														<form:option value=""><spring:message code="form.ricerca.scegli" /></form:option>	
					                                       <form:options items="${modelModalitAssociazioneMb}" 
                                                            itemLabel="descrizione" itemValue="id" />   
													</form:select>
													
													<a class="suggest" 
															data-explanation="${fn:escapeXml( MEX_SUGGEST_MOD_ASSOCIAZIONE_MULTIBENFICIARIO )}" 
															data-trigger="hover" 
															data-placement="top"
														>
														<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
													</a>
				
												</div>
												<div class="col-sm-2"></div>
												<div class="col-sm-10">
													<form:errors path="modalitaAssociazioneMultibeneficiario" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									
									<div class="container-fluid  mb-1" id = "elementiMultibeneficiarioEnteId" >
										<div class="row-fluid">
											<div class="form-group ">	
											<label class="col-sm-2 control-label required"   for="enteSecondarioAssociazioneMultibeneficiario"><spring:message code="enteSecondarioAssociazioneMultibeneficiario" /></label>
												<div class="col-sm-10 controls">
													<form:select path="enteSecondarioAssociazioneMultibeneficiario" id="enteSecondarioAssociazioneMultibeneficiario"
														class="form-control input-modalita-associazione-multibeneficiario input-inline">
														<form:option value=""><spring:message code="form.ricerca.scegli" /></form:option>	
														<form:options items="${modelEnteSecondario}" 
						                                    itemLabel="descrizione" itemValue="id" />					
													</form:select>
				
												</div>
												<div class="col-sm-2"></div>
												<div class="col-sm-10">
													<form:errors path="enteSecondarioAssociazioneMultibeneficiario" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									
									<div class="container-fluid mb-1" id = "elementiMultibeneficiariorCovAssociatoId" >
										<div class="row-fluid">
											<div class="form-group ">	
											<label class="col-sm-2 control-label required"   for="covAssociatoAssociazioneMultibeneficiario"><spring:message code="covAssociatoAssociazioneMultibeneficiario" /></label>
												<div class="col-sm-10 controls">
													<form:select path="covAssociatoAssociazioneMultibeneficiario" id="covAssociatoAssociazioneMultibeneficiario"
														class="form-control input-modalita-associazione-multibeneficiario input-inline">
														<form:option value=""><spring:message code="form.ricerca.scegli" /></form:option>	
														<form:options items="${modelCovDaAssociare}" 
						                                    itemLabel="descrizione" itemValue="id" />						
													</form:select>
				
												</div>
												<div class="col-sm-2"></div>
												<div class="col-sm-10">
													<form:errors path="covAssociatoAssociazioneMultibeneficiario" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									
<form:hidden  path="modalitaAssociazioneMultibeneficiarioOld"  readonly="true"/>
<form:hidden  path="covAssociatoAssociazioneMultibeneficiarioOld"  readonly="true"/>
