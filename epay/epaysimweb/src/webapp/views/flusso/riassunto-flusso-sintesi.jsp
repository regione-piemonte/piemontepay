<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
    <div class="step-pane active" id="filterPanel">
        <div class="accordion-heading">
            <h4>
				<spring:message code="home.flussi.ricerca.dettagli.sintesi" />
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
                        <label class="col-sm-2 control-label" for="codiceVersamento">
                            <spring:message code="home.flussi.ricerca.tabella.sintesi.codversamento" />
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="codiceVersamento" value="<c:out value=" ${dettaglio_flusso.flussoSintesi.codiceVersamento} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="descrizioneCodiceVersamento">
                            <spring:message code="home.flussi.ricerca.tabella.sintesi.descversamento" />
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="descrizioneCodiceVersamento" value="<c:out value=" ${dettaglio_flusso.flussoSintesi.descrizioneCodiceVersamento} " >--</c:out>" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="datiSpecificiDiRiscossione">
                            <spring:message code="home.flussi.ricerca.tabella.sintesi.datispecriscoss" />
                        </label>
                        <div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled name="datiSpecificiDiRiscossione" value="<c:out value=" ${dettaglio_flusso.flussoSintesi.datiSpecificiDiRiscossione} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="importoQuotaAggregazione">
                            <spring:message code="home.flussi.ricerca.tabella.sintesi.impquotaagg" />
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="importoQuotaAggregazione" value="<c:if test="${not empty dettaglio_flusso.flussoSintesi.importoQuotaAggregazione}"
								><fmt:formatNumber value="${dettaglio_flusso.flussoSintesi.importoQuotaAggregazione}" type="currency" currencySymbol=""
								/> &euro;</c:if><c:if test="${empty dettaglio_flusso.flussoSintesi.importoQuotaAggregazione}"
								>--</c:if>" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="numeroPagamentiAggregazione">
                            <spring:message code="home.flussi.ricerca.tabella.sintesi.numversaquotagg" />
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="numeroPagamentiAggregazione" value="<c:out value=" ${dettaglio_flusso.flussoSintesi.numeroPagamentiAggregazione} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="accertamentoAnno">
                            <spring:message code="home.flussi.ricerca.tabella.sintesi.annoaccert" />
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="accertamentoAnno" value="<c:out value=" ${dettaglio_flusso.flussoSintesi.accertamentoAnno} " >--</c:out>" />
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="accertamentoNumero">
                            <spring:message code="home.flussi.ricerca.tabella.sintesi.numaccert" />
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="accertamentoNumero" value="<c:out value=" ${dettaglio_flusso.flussoSintesi.accertamentoNumero} " >--</c:out>" />
                        </div>
                        
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
