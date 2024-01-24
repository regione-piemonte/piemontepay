<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
    <div class="step-pane active" id="filterPanel">
        <div class="accordion-heading">
            <h4>
				<th><spring:message code="home.flussi.ricerca.sintesi.origine" /></th> 
				<span class="pull-right clickable"> 
					<a href="#collapseFilterPanel" data-toggle="collapse"
						aria-expanded="true" data-parent="#filterPanel"
					>
						<i class="glyphicon glyphicon-chevron-up"></i> 
						<i class="glyphicon glyphicon-chevron-down"></i>
					</a>
				</span>
			</h4>
        </div>
        <div id="collapseFilterPanel" class="collapse in">

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="identificativoFlusso">
                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.idflusso" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="identificativoFlusso" value="<c:out value=" ${sintesi_flusso.flussoOrigine.identificativoFlusso} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="istitutoRicevente">
                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.istitricevente" />
                            </th>
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="istitutoRicevente" value="<c:out value=" ${sintesi_flusso.flussoOrigine.cfEnteCreditore.concat( ' - ').concat(sintesi_flusso.flussoOrigine.denominazioneEnte)} " >--</c:out>" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="psp">

                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.psp" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="psp" value="<c:out value="${sintesi_flusso.flussoOrigine.identificativoPsp.concat( ' - ').concat(sintesi_flusso.flussoOrigine.denominazionePsp)} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="importoTotalePagamenti">

                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.imptotpagam" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="importoTotalePagamenti" value="<c:if test="${not empty sintesi_flusso.flussoOrigine.importoTotalePagamenti}"
								><fmt:formatNumber value="${sintesi_flusso.flussoOrigine.importoTotalePagamenti}" type="currency" currencySymbol=""
								/> &euro;</c:if><c:if test="${empty sintesi_flusso.flussoOrigine.importoTotalePagamenti}"
								>--</c:if>" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="numeroTotalePagamenti">
                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.numtotpagam" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="numeroTotalePagamenti" value="<c:out value=" ${sintesi_flusso.flussoOrigine.numeroTotalePagamenti} " >--</c:out>" />
                        </div>
                        <label class="col-sm-2 control-label" for="importoTotalePagamentiIntermediati">
                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.imptotpagaminterm" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="importoTotalePagamentiIntermediati" value="<c:if test="${not empty sintesi_flusso.flussoOrigine.importoTotalePagamentiIntermediati}"
								><fmt:formatNumber value="${sintesi_flusso.flussoOrigine.importoTotalePagamentiIntermediati}" type="currency" currencySymbol=""
								/> &euro;</c:if><c:if test="${empty sintesi_flusso.flussoOrigine.importoTotalePagamentiIntermediati}"
								>--</c:if>" />
                        </div>

                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">


                        <label class="col-sm-2 control-label" for="numeroTotalePagamentiIntermediati">
                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.numtotpagaminterm" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="numeroTotalePagamentiIntermediati" value="<c:out value=" ${sintesi_flusso.flussoOrigine.numeroTotalePagamentiIntermediati} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="provvisorioAnno">
                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.annoprovv" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="provvisorioAnno" value="<c:out value=" ${sintesi_flusso.flussoOrigine.provvisorioAnno} " >--</c:out>" />
                        </div>



                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="provvisorioNumero">
                            <th>
                                <spring:message code="home.flussi.ricerca.tabella.origine.numprovv" />
                            </th>

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="provvisorioNumero" value="<c:out value=" ${sintesi_flusso.flussoOrigine.provvisorioNumero} " >--</c:out>" />
                        </div>
                        
                        <label class="col-sm-2 control-label" for="statoElaborazioneFlusso">
                            <th>
								<spring:message code="home.flussi.ricerca.tabella.origine.statoflusso" />
                            </th>
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="statoElaborazioneFlusso" value="<c:out value=" ${sintesi_flusso.flussoOrigine.statoElaborazioneFlusso} " >--</c:out>" />
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
