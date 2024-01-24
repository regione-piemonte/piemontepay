<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../include/head.jsp" %>

    <body>
        <%@ include file="../include/portal-header.jsp" %>
            <div class="row-fluid">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li>
                            <a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
                        </li>
                        <li>
                            <a href="${context}/flussi/ricerca">
                                <spring:message code="home.flussi.ricerca" />
                            </a>
                            <span class="divider"></span>
                        </li>
                        <li>
                            <a href="${context}/flusso/${flusso_origine_id}/sintesi">
                                <spring:message code="home.flussi.ricerca.sintesi" />
                            </a>
                            <span class="divider"></span>
                        </li>
                        <li>
                            <a href="${context}/flusso/${flusso_origine_id}/sintesi/${flusso_sintesi_id}/dettagli">
                                <spring:message code="home.flussi.ricerca.dettagli" />
                            </a>
                            <span class="divider"></span>
                        </li>
                        <li class="active">
                            <spring:message code="home.flussi.ricerca.dettaglio" />
                        </li>
                    </ul>
                </div>
            </div>

            <div>
                <div class="container-fluid">
                    <div class="row-fluid">
                        <div class="contentPage">
                            <div class="container-fluid">
                                <a title="A-contenuti"></a>
                            </div>

                            <div class="container-fluid">
                                <div class="row">
                                    <c:if test="${not empty dettaglio_flusso_errore}">
                                        <p class="alert alert-danger">
                                            <spring:message code="home.flussi.ricerca.dettagli.error" />
                                            <br/>
                                            <br/>
                                            <c:out value="${dettaglio_flusso_errore}" />
                                        </p>
                                    </c:if>
                                    <c:if test="${empty dettaglio_flusso_errore}">

                                        <h3><spring:message code="home.flussi.ricerca.dettaglio" /></h3>

                                        <%@ include file="riassunto-flusso-dettaglio.jsp" %>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row-fluid" style="padding-bottom: 2em;">
                        <p class="margin-medium">
                            <a id="buttonHome" href="${context}/flusso/${flusso_origine_id}/sintesi/${flusso_sintesi_id}/dettagli" class="btn btn-secondary">
                                <spring:message code="button.back" />
                            </a>
                        </p>
                    </div>
                </div>
            </div>
            <%@ include file="../include/portal-footer.jsp" %>
    </body>
