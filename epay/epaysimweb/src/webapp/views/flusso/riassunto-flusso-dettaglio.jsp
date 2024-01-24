<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="">
    <div class="step-pane active" id="filterPanel">
        <div id="collapseFilterPanel" class="collapse in">

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="anagraficaPagatore">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.anagpagatore" />

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="anagraficaPagatore" value="<c:out value=" ${dettaglio_flusso.anagraficaPagatore} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="causale">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.causale" />

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="causale" value="<c:out value=" ${dettaglio_flusso.causale} " >--</c:out>" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="descrizioneCausaleVersamento">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.desccausale" />

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="descrizioneCausaleVersamento" value="<c:out value=" ${dettaglio_flusso.descrizioneCausaleVersamento} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="identificativoUnicoVersamento">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.iuv" />

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="identificativoUnicoVersamento" value="<c:out value=" ${dettaglio_flusso.identificativoUnicoVersamento} " >--</c:out>" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="identificativoUnicoRiscossione">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.iur" />

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="identificativoUnicoRiscossione" value="<c:out value=" ${dettaglio_flusso.identificativoUnicoRiscossione} " >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="descrizioneVersamento">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.impversamento" />

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="importoSingoloVersamento" value="<c:if test="${not empty dettaglio_flusso.importoSingoloVersamento}"
								><fmt:formatNumber value="${dettaglio_flusso.importoSingoloVersamento}" type="currency" currencySymbol=""
								/> &euro;</c:if><c:if test="${empty dettaglio_flusso.importoSingoloVersamento}"
								>--</c:if>" />
                        </div>
                        
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="indiceSingoloVersamento">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.indiceversam" />

                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="indiceSingoloVersamento" value="<c:out value="${dettaglio_flusso.indiceSingoloVersamento}" >--</c:out>" />
                        </div>

                        <label class="col-sm-2 control-label" for="dataPagamento">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.datapaga" />

                        </label>
                        <div class="col-sm-4 controls">
                                  <input type="text" class="form-control" disabled name="dataPagamento" value="<c:if test="${not empty dettaglio_flusso.dataPagamento}"
								><fmt:formatDate value="${dettaglio_flusso.dataPagamento}" pattern="dd/MM/yyyy " 
								/></c:if><c:if test="${empty dettaglio_flusso.dataPagamento}"
								>--</c:if>" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" for="esitoPagamento">
                            <spring:message code="home.flussi.ricerca.tabella.dettagli.esitopaga" />
                        </label>
                        <div class="col-sm-4 controls">
                            <input type="text" class="form-control" disabled name="esitoPagamento" value="<c:out value=" ${dettaglio_flusso.esitoPagamento} " >--</c:out>" />
                        </div>
                    </div>
                </div>
            </div>

            

        </div>
    </div>
</div>
