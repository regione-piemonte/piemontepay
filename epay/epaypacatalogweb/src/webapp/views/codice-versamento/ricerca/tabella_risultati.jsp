<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="col-sm-12">
	<table id="results" class="table table-hover tab_left dataTable no-footer">
		<thead>
		<tr>
			<th class="hidden"></th>
			<th><spring:message code="filter.codiceversamento"/></th>
			<th class="hidden-sm hidden-xs"><spring:message code="form.ricerca.tematica"/></th>
			<th class="hidden-sm hidden-xs"><spring:message code="form.ricerca.macrotipo"/></th>
			<th class="hidden-sm hidden-xs"><spring:message code="voceentrata"/></th>
			<th class="hidden-xs"><spring:message code="tipopagamento"/></th>
			<th class="hidden-xs"><spring:message code="iban"/></th>
			<th class="hidden-xs"><spring:message code="multibeneficiario"/></th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="riga" items="${lista_risultati}">
			<tr id='view_<c:out value="${riga.id}" />' class='base group group-start'>
				<td class="hidden" data-key="codice_gruppo">
					<spring:message code="codiceversamento.ricerca.tabellarisultati.noparent"/>
				</td>
				<td data-key="codice">
					<c:out value="${riga.codice}"/> - <c:out value="${riga.descrizione}"/>
					<div class="visible-xs-block">
						<small>
							<spring:message code="codiceversamento.ricerca.tabellarisultati.iban"/> <c:out
								value="${riga.iban}"> <spring:message code="codiceversamento.ricerca.tabellarisultati.nd"/> </c:out>
							<br/>
							<spring:message code="codiceversamento.ricerca.tabellarisultati.pagamento"/> <c:out value="${riga.descrizioneTipoPagamento}"></c:out>
						</small>
					</div>
				</td>
				<td class="hidden-sm hidden-xs" data-key="codiceTematica">
					<c:out value="${riga.codiceTematica}"/> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattino"/>
					<c:out value="${riga.descrizioneTematica}"/>
				</td>
				<td class="hidden-sm hidden-xs" data-key="codiceMacrotipo">
					<c:out value="${riga.codiceMacrotipo}"/> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattino"/>
					<c:out value="${riga.descrizioneMacrotipo}"/>
				</td>
				<td class="hidden-sm hidden-xs" data-key="codiceVoceEntrata">
					<c:out value="${riga.codiceVoceEntrata}"/> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattino"/> <c:out value="${riga.descrizioneVoceEntrata}"/>
				</td>
				<td class="hidden-xs" data-key="descrizioneTipoPagamento">
					<c:out value="${riga.descrizioneTipoPagamento}"> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattini"/> </c:out>
				</td>
				<td class="hidden-xs" data-key="iban">
					<c:out value="${riga.iban}"> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattini"/> </c:out>
				</td>
				<td class="hidden-xs" data-key="multibeneficiario">
					<c:out value="${riga.multibeneficiario}"> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattini"/> </c:out>
				</td>
				<td>
					<sec:authorize
							access="hasRole('MODIFICA_CODICE_VERSAMENTO') or hasRole('ELIMINA_CODICE_VERSAMENTO') or hasRole('INSERISCI_CODICE_VERSAMENTO')">
						<div id="tableRowActions" class="btn-group">
							<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
								<spring:message code="table.actions"/> <span class="caret"></span>
							</button>
							<ul class="dropdown-menu pull-right">
								<c:choose>
									<c:when test="${empty riga.idPadre}">
										<li>
											<a class="modificaAction"
											   href="${context}/codici-versamento/visualizza/${riga.id}?source=ricerca">
												<spring:message code="codiceversamento.visualizza.breadcrumb.visualizzacodiceversamento"/>
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a class="modificaAction"
											   href="${context}/codici-versamento/${riga.idPadre}/codici-versamento-collegati/visualizza/${riga.id}?source=ricerca">
												<spring:message code="codiceversamento.visualizza.breadcrumb.visualizzacodiceversamento"/>
											</a>
										</li>
									</c:otherwise>
								</c:choose>
								<sec:authorize access="hasRole('MODIFICA_CODICE_VERSAMENTO')">
									<c:choose>
										<c:when test="${empty riga.idPadre}">
											<li>
												<a class="modificaAction"
												   href="${context}/codici-versamento/modifica/${riga.id}?source=ricerca">
													<spring:message code="codiceversamento.collegato.breadcrumb.modificacodiceversamento"/>
												</a>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a class="modificaAction"
												   href="${context}/codici-versamento/${riga.idPadre}/codici-versamento-collegati/modifica/${riga.id}?source=ricerca">
													<spring:message code="codiceversamento.collegato.breadcrumb.modificacodiceversamento"/>
												</a>
											</li>
										</c:otherwise>
									</c:choose>
								</sec:authorize>
								<c:choose>
									<c:when test="${empty riga.idPadre}">
										<li>
										<c:if test="${empty riga.codiciVersamentoCollegati}">
											<sec:authorize access="hasRole('ELIMINA_CODICE_VERSAMENTO')">
												<li>
													<a class="eliminaAction"
													   onclick="eliminaCodiceVersamento(${riga.id})">
														<spring:message code="codiceversamento.ricerca.tabellarisultati.eliminacodiceversamento"/>
													</a>
												</li>
											</sec:authorize>
										</c:if>
										<c:if test="${riga.inserimentoConsentito}">
											<sec:authorize access="hasRole('INSERISCI_CODICE_VERSAMENTO')">
												<li>
													<a id="insert" class="modificaAction"
													   href="${context}/codici-versamento/${riga.id}/codici-versamento-collegati/nuovo?source=ricerca">
														<spring:message code="codiceversamento.modifica.index.associanuovocodiceversamento"/>
													</a>
												</li>
											</sec:authorize>
										</c:if>
									</c:when>
									<c:otherwise>
										<li>
											<a class="eliminaAction"
											   onclick="eliminaCodiceVersamento(${riga.id}, ${riga.idPadre})">
												<spring:message code="codiceversamento.ricerca.tabellarisultati.eliminacodiceversamento"/>
											</a>
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</sec:authorize>
				</td>
			</tr>
			<c:forEach var="riga_collegata" items="${riga.codiciVersamentoCollegati}">
				<tr id='view_<c:out value="${riga.id}" />_<c:out value="${riga_collegata.id}" />' class='collegato'
					custom-data-tipo-pagamento="<c:out value='${riga.descrizioneTipoPagamento}' />"
					custom-data-iban="<c:out value='${riga.iban}' />"
				>
					<td class="hidden" data-key="codice_gruppo">
						<c:out value="${riga.codice}"/> - <c:out value="${riga.descrizione}"/>
					</td>
					<td data-key="codice" style="padding-left: 2em;">
						<c:out value="${riga_collegata.codice}"/> - <c:out value="${riga_collegata.descrizione}"/>
						<div class="visible-xs-block">
							<small>
								<spring:message code="codiceversamento.ricerca.tabellarisultati.iban"/> <c:out value="${riga_collegata.iban}">
								<spring:message code="codiceversamento.ricerca.tabellarisultati.nd"/> </c:out>
								<br/>
								<spring:message code="codiceversamento.ricerca.tabellarisultati.pagamento"/> <c:out value="${riga_collegata.descrizioneTipoPagamento}"></c:out>
							</small>
						</div>
					</td>
					<td class="hidden-sm hidden-xs" data-key="codiceTematica">
						<c:out value="${riga_collegata.codiceTematica}"/> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattino"/>
						<c:out value="${riga_collegata.descrizioneTematica}"/>
					</td>
					<td class="hidden-sm hidden-xs" data-key="codiceMacrotipo">
						<c:out value="${riga_collegata.codiceMacrotipo}"/> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattino"/>
						<c:out value="${riga_collegata.descrizioneMacrotipo}"/>
					</td>
					<td class="hidden-sm hidden-xs" data-key="codiceVoceEntrata">
						<c:out value="${riga_collegata.codiceVoceEntrata}"/> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattino"/> <c:out
							value="${riga_collegata.descrizioneVoceEntrata}"/>
					</td>
					<td class="hidden-xs" data-key="descrizioneTipoPagamento">
						<c:out value="${riga_collegata.descrizioneTipoPagamento}"> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattini"/> </c:out>
					</td>
					<td class="hidden-xs" data-key="iban">
						<c:out value="${riga_collegata.iban}"> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattini"/> </c:out>
					</td>
					<td class="hidden-xs" data-key="multibeneficiario">
						<c:out value="${riga_collegata.multibeneficiario}"> <spring:message code="codiceversamento.ricerca.tabellarisultati.trattini"/> </c:out>
					</td>
					<td>
						<sec:authorize access="hasRole('MODIFICA_CODICE_VERSAMENTO') or hasRole('ELIMINA_CODICE_VERSAMENTO') or hasRole('INSERISCI_CODICE_VERSAMENTO')">
							<div id="tableRowActions" class="btn-group">
								<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
									<spring:message code="table.actions"/> <span class="caret"></span>
								</button>
								<ul class="dropdown-menu pull-right">
									<li>
										<a class="modificaAction"
										   href="${context}/codici-versamento/${riga.id}/codici-versamento-collegati/visualizza/${riga_collegata.id}?source=ricerca">
											<spring:message code="codiceversamento.ricerca.tabellarisultati.visualizzacodiceversamentoassociato"/>
										</a>
										<sec:authorize access="hasRole('MODIFICA_CODICE_VERSAMENTO')">
											<a class="modificaAction"
											   href="${context}/codici-versamento/${riga.id}/codici-versamento-collegati/modifica/${riga_collegata.id}?source=ricerca">
												<spring:message code="codiceversamento.ricerca.tabellarisultati.modificacodiceversamentoassociato"/>
											</a>
										</sec:authorize>
										<sec:authorize access="hasRole('ELIMINA_CODICE_VERSAMENTO')">
											<a class="eliminaAction"
											   onclick="eliminaCodiceVersamento(${riga_collegata.id}, ${riga.id})">
												<spring:message code="codiceversamento.ricerca.tabellarisultati.eliminacodiceversamentoassociato"/>
											</a>
										</sec:authorize>
									</li>
								</ul>
							</div>
						</sec:authorize>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
</div>
