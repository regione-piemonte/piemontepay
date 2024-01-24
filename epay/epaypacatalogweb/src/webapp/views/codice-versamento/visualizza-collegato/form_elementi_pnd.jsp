<c:set var="w00" value="2" />
<c:set var="w01" value="10" />
<c:set var="w04" value="4" />
<c:set var="w05" value="2" />

<div id = "formElementiPndContainerId"> 

<c:if test="${modifica_codice_versamento_collegato.codiceTipoPagamento.equals('LDC') or
                                modifica_codice_versamento_collegato.codiceTipoPagamento.equals('REDS') or
                                modifica_codice_versamento_collegato.codiceTipoPagamento.equals('PABL') or
                                modifica_codice_versamento_collegato.codiceTipoPagamento.equals('REDI')}">

                                    
<sec:authorize access="hasRole('ASSISTENZA')">
                            <h4><spring:message code="index.attualizzazione.pnd" /></h4>
                                <div class="container-fluid">
                                    <div class="row-fluid"  >
									
									<div class="container-fluid">
										 <div class="row-fluid">
											<div class="form-group ">	
											<label class="col-sm-${w00} control-label"   for="urlAttualizzazionePnd"><spring:message code="codiceversamento.urlAttualizzazionePnd" /></label>
												<div class="col-sm-${w01} controls">
												
												<form:input class="form-control input-descrizione-text-cov" path="urlAttualizzazionePnd" readonly="true" disabled="true"/>
												
												</div>
												
											</div>
										</div> 
										</div> 
										
										<div class="container-fluid">
										 <div class="row-fluid">
                                            <div class="form-group ">   
                                            <label class="col-sm-${w00} control-label"   for="strCredenzialiPnd"><spring:message code="codiceversamento.strCredenzialiPnd" /></label>
                                                <div class="col-sm-${w01} controls">
                                                <form:input class="form-control input-passprase" path="strCredenzialiPnd" readonly="true" disabled="true"/>
                                                </div>
                                               
                                            </div>
                                        </div> 
									 </div>
									 
									 </div>
                                </div>
									
									 </sec:authorize>
									 
									
			

</div>
</c:if>


