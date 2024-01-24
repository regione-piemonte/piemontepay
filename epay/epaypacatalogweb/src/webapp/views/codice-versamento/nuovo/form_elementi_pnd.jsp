<c:set var="w00" value="2" />
<c:set var="w01" value="10" />
<c:set var="w04" value="4" />
<c:set var="w05" value="2" />

<c:if test="${scenario ne 'modifica'}">
<div id = "formElementiPndContainerId"> 


<sec:authorize access="hasRole('ASSISTENZA')">
                            <h4><spring:message code="index.attualizzazione.pnd" /></h4>
                                <div class="container-fluid">
                                    <div class="row-fluid"  >
									
									<div class="container-fluid">
										 <div class="row-fluid">
											<div class="form-group ">	
											<label class="col-sm-2 control-label"   for="urlAttualizzazionePnd"><spring:message code="codiceversamento.urlAttualizzazionePnd" /></label>
												<div class="col-sm-10 controls">
												
												<form:input class="form-control input-descrizione-text-cov" path="urlAttualizzazionePnd" />
												
												</div>
												<div class="col-sm-2"></div>
                                                <div class="col-sm-10">
                                                    <form:errors path="urlAttualizzazionePnd" cssClass="error" />
                                                </div>
											</div>
										</div> 
										</div> 
										
										<div class="container-fluid">
										 <div class="row-fluid">
                                            <div class="form-group ">   
                                            <label class="col-sm-2 control-label"   for="strCredenzialiPnd"><spring:message code="codiceversamento.strCredenzialiPnd" /></label>
                                                <div class="col-sm-10 controls">
                                                <form:input class="form-control input-passprase" path="strCredenzialiPnd" />
                                                </div>
                                                <div class="col-sm-2"></div>
                                                <div class="col-sm-10">
                                                    <form:errors path="strCredenzialiPnd" cssClass="error" />
                                                </div>
                                            </div>
                                        </div> 
									 </div>
									 </div>
                                </div>
									
									 </sec:authorize>
									 
									  <sec:authorize access="not (hasRole('ASSISTENZA') )">
                                                <form:hidden id="strCredenzialiPnd" path="strCredenzialiPnd" />    
                                                <form:hidden id="urlAttualizzazionePnd" path="urlAttualizzazionePnd" />    
                                                </sec:authorize>
									
			

</div>

</c:if>

