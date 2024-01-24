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
                        <li class="active">
	                        <sec:authorize access="hasRole('IMPOSTA_ESITO_FLUSSO')">
	                        	<spring:message code="home.flussi.imposta.page" />
	                        </sec:authorize>
	                        <sec:authorize access="hasRole('RICERCA_FLUSSO')">
	                        	<spring:message code="home.flussi.ricerca.page" />
	                        </sec:authorize>    
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

                            <%@ include file="form-ricerca.jsp" %>

                                <div class="container-fluid" id="divResults">
                                    <div class="row">

                                        <c:if test="${lista_risultati_vuota}">
                                            <div class="col-sm-12">
                                                <p class="alert alert-warning">
                                                    <spring:message code="validation.noResult" />
                                                </p>
                                            </div>
                                        </c:if>

                                        <c:if test="${not empty lista_risultati}">
                                            <div class="col-sm-12">
                                                <div class="row-fluid">
                                                    <p class="margin-medium">
                                                        <a id="formButtonExportCsv" name="EsportaCsv" class="btn btn-default btn-action pull-right" href="${context}/flussi/esporta-csv" style="margin-right: 0em;"> <span class="fas fa-file-alt"></span>
                                                            <spring:message code="button.exportCSV" />
                                                        </a>
                                                        <a id="formButtonExportExcel" name="EsportaExcel" class="btn btn-default btn-action pull-right" href="${context}/flussi/esporta-excel" style="margin-right: 1em;"> <span class="fas fa-file-excel"></span>
                                                            <spring:message code="button.exportXLS" />
                                                        </a>
                                                    </p>
                                                </div>
                                            </div>

                                            <div class="col-sm-12">
                                                <%@ include file="tabella-risultati.jsp" %>
                                            </div>

                                            <div class="col-sm-12">
                                                <div class="row-fluid">
                                                    <p class="margin-medium">
                                                        <a id="formButtonExportCsv" name="EsportaCsv" class="btn btn-default btn-action pull-right" href="${context}/flussi/esporta-csv" style="margin-right: 0em;"> <span class="fas fa-file-alt"></span></span>
                                                            <spring:message code="button.exportCSV" />
                                                        </a>
                                                        <a id="formButtonExportExcel" name="EsportaExcel" class="btn btn-default btn-action pull-right" href="${context}/flussi/esporta-excel" style="margin-right: 1em;"> <span class="fas fa-file-excel"></span></span>
                                                            <spring:message code="button.exportXLS" />
                                                        </a>
                                                    </p>
                                                </div>
                                            </div>

                                        </c:if>
                                    </div>
                                </div>
                        </div>
                    </div>
                    <div class="row-fluid" style="padding-bottom: 2em;">
                        <p class="margin-medium">
                            <a id="buttonHome" class="btn btn-secondary" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a>
                        </p>
                    </div>
                </div>
            </div>
            <%@ include file="../include/portal-footer.jsp" %>
                <%@ include file="script-ricerca.jsp" %>
    </body>
