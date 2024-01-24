<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<table id="results" class="table table-hover tab_left dataTable no-footer">
    <thead>
        <tr>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.sintesi.codversamento" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.sintesi.descversamento" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.sintesi.datispecriscoss" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.sintesi.impquotaagg" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.sintesi.numversaquotagg" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.sintesi.annoaccert" />
            </th>
            <th>
                <spring:message code="home.flussi.ricerca.tabella.sintesi.numaccert" />
            </th>
            <th>
                <spring:message code="table.actions" />
            </th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="result" items="${sintesi_flusso.flussiSintesi}">
            <tr>
                <td data-key="codiceVersamento">
                    <c:out value="${result.codiceVersamento}">--</c:out>
                </td>
                <td data-key="descrizioneCodiceVersamento">
                	<c:out value="${result.descrizioneCodiceVersamento}">--</c:out>
                </td>
                <td data-key="datiSpecificiDiRiscossione">
                    <c:out value="${result.datiSpecificiDiRiscossione}">--</c:out>
                </td>
                
                <td data-order="${not empty result.importoQuotaAggregazione ? result.importoQuotaAggregazione : '0'}">
                    <c:if test="${not empty result.importoQuotaAggregazione}">
                        <fmt:formatNumber value="${result.importoQuotaAggregazione}" type="currency" currencySymbol="" /> &euro;
                    </c:if>
                    <c:if test="${empty result.importoQuotaAggregazione}">
                        --
                    </c:if>
                </td>
                <td data-key="numeroPagamentiAggregazione">
                    <c:out value="${result.numeroPagamentiAggregazione}">--</c:out>
                </td>
                <td data-key="accertamentoAnno">
                    <c:out value="${result.accertamentoAnno}">--</c:out>
                </td>
                <td data-key="accertamentoNumero">
                    <c:out value="${result.accertamentoNumero}">--</c:out>
                </td>
                <td>
                    <div id="tableRowActions" class="btn-group">
                        <button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
                            <spring:message code="table.actions" /> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <a href="${context}/flusso/${sintesi_flusso.flussoOrigine.id}/sintesi/${result.id}/dettagli" id="visualizzaFlussoSintesiAction">
								<spring:message code="home.flussi.ricerca.visualdettagli" />
							</a>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
