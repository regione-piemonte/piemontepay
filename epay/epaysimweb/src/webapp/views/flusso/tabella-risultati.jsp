<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<table id="results" class="table table-hover tab_left dataTable no-footer">
    <thead>
        <tr>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.idflusso" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.istitricevente" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.psp" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.imptotpagam" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.numtotpagam" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.imptotpagaminterm" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.numtotpagaminterm" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.annoprovv" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.numprovv" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.origine.statoflusso" />
            </th>
            <th>
                <spring:message code="table.actions" />
            </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="result" items="${lista_risultati}">
            <tr>
                <td data-key="identificativoFlusso">
                    <c:out value="${result.identificativoFlusso}">--</c:out>
                </td>
                <td data-key="istitutoRicevente">
                    <c:out value="${result.cfEnteCreditore.concat(' - ').concat(result.denominazioneEnte)}">--</c:out>
                </td>
                <td data-key="psp">
                    <c:out value="${result.identificativoPsp.concat(' - ').concat(result.denominazionePsp)}">--</c:out>
                </td>
                <td data-order="${not empty result.importoTotalePagamenti ? result.importoTotalePagamenti : '0'}">
                    <c:if test="${not empty result.importoTotalePagamenti}">
                        <fmt:formatNumber value="${result.importoTotalePagamenti}" type="currency" currencySymbol="" /> &euro;
                    </c:if>
                    <c:if test="${empty result.importoTotalePagamenti}">
                        --
                    </c:if>
                </td>
                <td data-key="numeroTotalePagamenti">
                    <c:out value="${result.numeroTotalePagamenti}">--</c:out>
                </td>
                <td data-order="${not empty result.importoTotalePagamentiIntermediati ? result.importoTotalePagamentiIntermediati : '0'}">
                    <c:if test="${not empty result.importoTotalePagamentiIntermediati}">
                        <fmt:formatNumber value="${result.importoTotalePagamentiIntermediati}" type="currency" currencySymbol="" /> &euro;
                    </c:if>
                    <c:if test="${empty result.importoTotalePagamentiIntermediati}">
                        --
                    </c:if>
                </td>
                <td data-key="numeroTotalePagamentiIntermediati">
                    <c:out value="${result.numeroTotalePagamentiIntermediati}">--</c:out>
                </td>
                <td data-key="provvisorioAnno">
                    <c:out value="${result.provvisorioAnno}">--</c:out>
                </td>
                <td data-key="provvisorioNumero">
                    <c:out value="${result.provvisorioNumero}">--</c:out>
                </td>
                <td data-key="statoElaborazioneFlusso">
                    <c:out value="${result.statoElaborazioneFlusso}">--</c:out>
                </td>
                <td>
                    <div id="tableRowActions" class="btn-group">
                        <button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
                            <spring:message code="table.actions" /> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right">
                        	<sec:authorize access="hasRole('IMPOSTA_ESITO_FLUSSO')">
	                            <c:if test="${'Documento generato' != result.statoElaborazioneFlusso}">
	                                <li>
	                                    <a href="${context}/flusso/${result.id}/updateFlussiElaborato" id="updateFlussiElaboratoAction">
	                                        <spring:message code="home.flussi.ricerca.tabella.origine.stato.docgenerato" />
	                                    </a>
	                                </li>
	                            </c:if>
	                            <c:if test="${'Documento non generato' != result.statoElaborazioneFlusso}">
	                                <li>
	                                    <a href="${context}/flusso/${result.id}/updateFlussiErrore" id="updateFlussiErroreAction">
	                                        <spring:message code="home.flussi.ricerca.tabella.origine.stato.docnotgenerato" />
	                                    </a>
	                                </li>
	                            </c:if>
                            </sec:authorize>
                            <li>
                                <a href="${context}/flusso/${result.id}/sintesi" id="visualizzaFlussoSintesiAction">
                                    <spring:message code="home.flussi.ricerca.visualflusso" />
                                </a>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
