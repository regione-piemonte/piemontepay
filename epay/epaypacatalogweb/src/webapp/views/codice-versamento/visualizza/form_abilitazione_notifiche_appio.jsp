<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="2" />
<c:set var="w01" value="10" />
<c:set var="w04" value="4" />
<c:set var="w05" value="2" />

<%--                         <h4><spring:message code="index.abilitazione.appio" /></h4>--%>

                                    <div class="container-fluid">
                                    <div class="row-fluid"  >

									<div class="container-fluid">
										<div class="row-fluid">
											<div class="form-group " style="height: 20px;">
													<label class="col-sm-${w00} control-label" for="flagPersonalizzazioneCov">
													<spring:message code="codiceversamento.flagPersonalizzazioneCov" /></label>
						              			<div class="col-sm-${w00} controls">
							          				<form:checkbox class="checkbox-inline" path="flagPersonalizzazioneCov" id="flagPersonalizzazioneCov" readonly="true" disabled="true"/>				
						             			</div>
												<div class="col-sm-${w00}"></div>
												
											</div>
										</div>
									</div>
									
									<div class="container-fluid" id  = "opzioniAbilitazioneNotificheId" >
										 <div class="row-fluid">
											<div class="form-group ">	
											<label class="col-sm-${w00} control-label required"   for="descrizioneTextCov"><spring:message code="codiceversamento.descrizioneTextCov" /></label>
												<div class="col-sm-${w01} controls">
												<form:input class="form-control input-descrizione-text-cov" path="descrizioneTextCov"  readonly="true"/>
												</div>
											</div>
										</div> 
										
										
										<sec:authorize access="hasRole('ASSISTENZA') ">
										 <div class="row-fluid">
                                            <div class="form-group ">   
                                            <label class="col-sm-${w00} control-label"   for="passphrase"><spring:message code="codiceversamento.passphrase" /></label>
                                                <div class="col-sm-${w01} controls">
                                                <form:input class="form-control input-passprase" path="passphrase"  readonly="true"/>
                                                </div>
                                            </div>
                                        </div>
                                        </sec:authorize>
                                         
									</div>
									
									
			     </div>
                                </div>
