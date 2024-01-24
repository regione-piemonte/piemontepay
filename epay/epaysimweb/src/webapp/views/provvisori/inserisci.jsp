<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../include/head.jsp"%>

<body>

	<p class="nascosto">
		<a title="A-sommario"></a>
	</p>
	<ul id="sommario" class="nascosto">
		<li><a href="#A-contenuti"><spring:message code="sommario.saltacontenuti"/></a></li>
	</ul>
	<hr />


	<%@ include file="../include/portal-header.jsp"%>

	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
				</li>
				<li class="active"><spring:message code="provvisori.inserisciprovvisorio"/></li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<form:form id="formInserisciProvvisorio" method="post" commandName="inserisci_provvisorio" action="${context}/provvisori/inserisci" class="form-horizontal" enctype="multipart/form-data">
					<fieldset>
						<div id="alert-div"></div>
						<h3><spring:message code="provvisori.inserisciprovvisorio"/></h3>
						
						<c:if test="${msgInfo != null}">
				            <div class="stdMessagePanel">
				                <div class="alert alert-success" role="alert">
				                    <p>
				                        <c:out value="${msgInfo}"></c:out>
				                    </p>
				                </div>
				            </div>
				        </c:if>
				        <c:if test="${msgError != null}">
				        	<div class="stdMessagePanel">
				                <div class="alert alert-error" role="error">
				                    <p>
				                        <c:out value="${msgError}"></c:out>
				                        <c:forEach items="${outputList}" var="output">
				                        	<c:if test="${ not empty output.descrizioneEsito }">
				                        		<c:out value="${ output.descrizioneEsito }"></c:out><br/>
				                        	</c:if>
				                        </c:forEach>
				                    </p>
				                </div>
				            </div>
				        </c:if>
						
						<div class="step-content">
							<div class="step-pane active" id="filterPanel">
								<c:if test="${not empty lista_risultati}">	
								<div class="accordion-heading">
									<h4>
										<spring:message code="provvisori.inserisci.nuovoprovvisorio"/> <span class="pull-right clickable"> <a
											href="#collapseFilterPanel" data-toggle="collapse"
											aria-expanded="false" data-parent="#filterPanel"><i
												class="glyphicon glyphicon-chevron-up"></i> <i
												class="glyphicon glyphicon-chevron-down"></i></a>
										</span>
									</h4>
								</div>
								
								<div id="collapseFilterPanel" class="collapse ">
								</c:if>
								<c:if test="${empty lista_risultati}">	
								<div class="accordion-heading">
									<h4>
										<spring:message code="provvisori.inserisci.nuovoprovvisorio"/> <span class="pull-right clickable"> <a
											href="#collapseFilterPanel" data-toggle="collapse"
											aria-expanded="true" data-parent="#filterPanel"><i
												class="glyphicon glyphicon-chevron-up"></i> <i
												class="glyphicon glyphicon-chevron-down"></i></a>
										</span>
									</h4>
								</div>
								
								<div id="collapseFilterPanel" class="collapse in">
								</c:if>
									<div class="container-fluid">
										<div class="row-fluid">
											<div class="form-group">
												<label class="col-sm-3 control-label" for="xmlFlussoProvvisorio">
												<spring:message code="provvisori.inserisci.xmlprovvisorio"/></label>
												<input type="file" name="xmlFlussoProvvisorio" id="xmlFlussoProvvisorio" accept="text/xml">
											</div>
											<div class="col-sm-3"></div><div class="col-sm-5">
												<form:errors path="xmlFlussoProvvisorio" cssClass="error" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</fieldset>
				</form:form>
				
				<div class="step-pane active" id="buttonsPanel">
						<!--button class="btn btn-secondary" id="form-cancel"><spring:message code="home" /></button>-->
						<a id="buttonHome" class="btn btn-secondary" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a>
						<button class="btn btn-primary pull-right" id="form-save-submit"><spring:message code="button.save"/></button>
				</div>
				
				<p>&nbsp;</p>
			</div>
		</div>
	</div>
	<%@ include file="../include/portal-footer.jsp"%>
	
	<script>
		$("#form-cancel").on('click',function(){
			location = baseLocation + "/home" + "${webappConfig.requestParam}" + '<sec:authentication property="principal.ente.codiceFiscale" />';
		});
		
		$("#form-save-submit").on('click',function(){
			$("#formInserisciProvvisorio").submit();
		});
		
		<c:if test="${msgPopup != null}">
			chiediConfermaProcedi('<c:out value="${msgPopup}" escapeXml="true" />', function() {
				$("#formInserisciProvvisorio").attr("method","GET");
				$("#formInserisciProvvisorio").attr("action","${context}/provvisori/inserisci-provvisorio");
				$("#formInserisciProvvisorio").submit();
			}, 
			function() {
				$('#formInserisciProvvisorio').attr('action', "${context}/provvisori/inserisci");
			}, 
			);
		</c:if>
	</script>
</body>
