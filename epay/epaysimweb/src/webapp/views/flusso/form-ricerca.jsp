<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<form:form id="filtro_ricerca_flussi" name="filtro_ricerca_flussi" action="${context}/flussi/ricerca" method="post" class="" modelAttribute="filtro_ricerca_flussi">
    <fieldset>

        <div id="alert-div"></div>

        <h3><spring:message code="home.flussi.ricerca.page" /></h3>

        <div class="step-content">
            <div class="step-pane active" id="filterPanel">
            	<c:if test="${not empty lista_risultati}">
                <div class="accordion-heading">
								<h4>
									Filtro di ricerca
									<span class="pull-right clickable">
										<a href="#collapseFilterPanel" data-toggle="collapse" 
											aria-expanded="false" data-parent="#filterPanel" class="anch">
											<i class="glyphicon glyphicon-chevron-down"></i>
											<i class="glyphicon glyphicon-chevron-up"></i></a>
									</span>
								</h4>
							</div>
                <div id="collapseFilterPanel" class="collapse">
                </c:if>
                <div class="step-pane active" id="filterPanel">
            	<c:if test="${empty lista_risultati}">
                <div class="accordion-heading">
								<h4>
									Filtro di ricerca
									<span class="pull-right clickable">
										<a href="#collapseFilterPanel" data-toggle="collapse" 
											aria-expanded="true" data-parent="#filterPanel" class="anch">
											<i class="glyphicon glyphicon-chevron-down"></i>
											<i class="glyphicon glyphicon-chevron-up"></i></a>
									</span>
								</h4>
							</div>
                <div id="collapseFilterPanel" class="collapse in">
                </c:if>
                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="form-group ">
                                <label class="col-sm-12 control-label">
                                    <form:errors path="validitaGenerica" cssClass="boxedError" />
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="identificativoFlusso">
                                    <spring:message code="home.flussi.ricerca.filtro.idflusso" />
                                </label>
                                <div class="col-sm-10 controls">
                                    <form:input id="identificativoFlusso" name="identificativoFlusso" path="identificativoFlusso" class="form-control" placeholder="identificativo flusso" />
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="form-group ">
                                <label class="col-sm-2 control-label" for="statoFlusso">
                                    <spring:message code="home.flussi.ricerca.filtro.statoflusso" />
                                </label>
                                <div class="col-sm-10 controls">
                                    <form:select path="statoFlusso" class="form-control">
                                        <form:option value="all"><spring:message code="flusso.formricerca.tutti"/></form:option>
                                        <form:option value=""><spring:message code="flusso.formricerca.documentononelaborato"/></form:option>
                                        <form:option value="true"><spring:message code="flusso.formricerca.documentogenerato"/></form:option>
                                        <form:option value="false"><spring:message code="flusso.formricerca.documentonongenerato"/></form:option>
                                    </form:select>
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10">
                                    <form:errors path="statoFlusso" cssClass="boxedError" />
                                    </label>
                                </div>
                            </div>
                        </div>
                        
                    </div>

                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for=dataElaborazioneDa>
                                    <spring:message code="home.flussi.ricerca.filtro.dataelabfrom" />
                                </label>
                                <div class="col-sm-10 controls">
                                    <form:input id="dataElaborazioneDa" name="dataElaborazioneDa" path="dataElaborazioneDa" class="form-control datepicker" placeholder="data elaborazione da" />
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10">
                                    <form:errors path="dataElaborazioneDa" cssClass="boxedError" />
                                    </label>
                                </div>
                            </div>
                        </div>
                        
                    </div>

                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for=dataElaborazioneA>
                                    <spring:message code="home.flussi.ricerca.filtro.dataelabto" />
                                </label>
                                <div class="col-sm-10 controls">
                                    <form:input id="dataElaborazioneA" name="dataElaborazioneA" path="dataElaborazioneA" class="form-control datepicker" placeholder="data elaborazione a" />
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="col-sm-10">
                                    <form:errors path="dataElaborazioneA" cssClass="boxedError" />
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <p class="margin-medium">
                            <a id="formSubmitButtonPulisci" href="${context}/flussi/pulisci" class="btn btn-secondary">
                                <spring:message code="button.cleanup" />
                            </a>

                            <input id="formSubmitButtonCerca" type="submit" value="Cerca" name="Cerca" class="btn btn-primary pull-right" />
                        </p>
                    </div>
                </div>
              
            </div>
        </div>
    </fieldset>
</form:form>
