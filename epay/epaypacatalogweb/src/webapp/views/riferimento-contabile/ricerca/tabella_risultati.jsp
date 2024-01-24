<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<style>
	.header {
		cursor: pointer;
	}
</style>
<div class ="col-sm-12">
	<table id="results" class="table table-hover tab_left no-footer">
		<thead>
		<tr>
			<th> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.codiceversamento" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.tematica" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.macrotipo" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.voceentrata" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.dsr" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.annoesercizio" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.periodovalidita" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.stato" /> </th>
			<th class=""> <spring:message code="riferimenticontabili.ricerca.risultati.tabella.multibeneficiario" /> </th>
			<th class=""> </th>
			<th class="hidden"></th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="riga_codice_versamento" items="${lista_risultati}">
			<c:if test="${ empty riga_codice_versamento.riferimentiContabili and riga_codice_versamento.numeroRiferimentiTotali eq 0 }">
			<tr 
				id='view_cv_<c:out value="${riga_codice_versamento.id}" />' 
				class="riferimento-contabile riga-messaggio-nessun-riferimento"
				custom-id-codice-versamento="${ riga_codice_versamento.id }"
				custom-numero-riferimenti="${ riga_codice_versamento.numeroRiferimentiInVita }"
				custom-ps-rif-cont="${riga_codice_versamento.codiceTipoPagamento == 'PS' 
				and not empty riga_codice_versamento.riferimentiContabili
				and riga_codice_versamento.riferimentiContabili.size() gt 0 ? 0 : 1}";
			>
				<td data-key="descrizioneCodiceVersamento" data-order="<c:out value="${riga_codice_versamento.codice}" /> - <c:out value="${riga_codice_versamento.descrizione}" />">
				</td>
				<td class=" " data-key="descrizioneTematica" data-order="<c:out value="${riga_codice_versamento.codiceTematica}" /> - <c:out value="${riga_codice_versamento.descrizioneTematica}" />">
				</td>
				<td class=" " data-key="descrizioneMacrotipo" data-order="<c:out value="${riga_codice_versamento.codiceMacrotipo}" /> - <c:out value="${riga_codice_versamento.descrizioneMacrotipo}" />">
				</td>
				<td class=" " data-key="descrizioneVoceEntrata" data-order="<c:out value="${riga_codice_versamento.codiceVoceEntrata}" /> - <c:out value="${riga_codice_versamento.descrizioneVoceEntrata}" />">
				</td>
				<td  data-order="ZZZZZZZZZZ" colspan="1">
					<span class="glyphicon glyphicon-warning-sign"></span>
					 <spring:message code="riferimenticontabili.ricerca.risultati.tabella.nessunriferimento" /> 
				</td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td></td>
				<td class="hidden"  data-key="groupkey">
					<c:out value="${riga_codice_versamento.codice}" /> - <c:out value="${riga_codice_versamento.descrizione}" />
				</td>
			</tr>
			</c:if>
		
			<c:forEach var="riga" items="${riga_codice_versamento.riferimentiContabili}">
			<tr 
				id='view_<c:out value="${riga.id}" />' 
				class="riga-${riga.inVita ? 'in-vita' : 'non-in-vita'} riferimento-contabile"
				custom-id-codice-versamento="${ riga_codice_versamento.id }"
				custom-numero-riferimenti="${ riga_codice_versamento.numeroRiferimentiInVita }"
				custom-ps-rif-cont="${riga_codice_versamento.codiceTipoPagamento == 'PS' 
				and not empty riga_codice_versamento.riferimentiContabili
				and riga_codice_versamento.riferimentiContabili.size() gt 0 ? 0 : 1}";
			>
			
				<td data-key="descrizioneCodiceVersamento" data-order="<c:out value="${riga_codice_versamento.codice}" /> - <c:out value="${riga_codice_versamento.descrizione}" /> ">
				</td>
				<td class=" " data-key="descrizioneTematica" data-order="<c:out value="${riga.codiceTematica}" /> - <c:out value="${riga.descrizioneTematica}" />">
				</td>
				<td class=" " data-key="descrizioneMacrotipo" data-order="<c:out value="${riga.codiceMacrotipo}" /> - <c:out value="${riga.descrizioneMacrotipo}" />">
				</td>
				<td class=" " data-key="descrizioneVoceEntrata" data-order="<c:out value="${riga.codiceVoceEntrata}" /> - <c:out value="${riga.descrizioneVoceEntrata}" />">
				</td>
				<td class="" data-key="descrizioneDatoSpecificoRiscossione">
					<c:out value="${riga.codiceTipologiaDatoSpecificoRiscossione}" >--</c:out>/<c:out value="${riga.datoSpecificoRiscossione}" >--</c:out> 
					<c:out value="${riga.tipoServizio}" >--</c:out>
				</td>
				<td class="" data-key="annoEsercizio">
					<c:out value="${riga.annoEsercizio}" >--</c:out>
				</td>
				<td class="" data-order="${not empty riga.dataInizioValidita ? riga.getDataInizioValidita().getTime() : '0'}">
					<c:if test="${not empty riga.dataInizioValidita}">
						dal <fmt:formatDate value="${riga.dataInizioValidita}" pattern="dd/MM/yyyy" />
					</c:if>
					<c:if test="${not empty riga.dataFineValidita}">
						al <fmt:formatDate value="${riga.dataFineValidita}" pattern="dd/MM/yyyy" />
					</c:if>
				</td>
				<td class="" data-key="inVita">
					<c:out value="${riga.inVita ? 'In vita' : 'Non in vita'}" />
				</td>
				<td class="" data-key="multibeneficiario">
					<c:out value="${riga.flagMbPrimario ? 'Primario' : riga.flagMbSecondario ? 'Secondario' : '-'}" />
				</td>
				<td>
					<sec:authorize access="hasRole('MODIFICA_RIFERIMENTO_CONTABILE') or hasRole('ELIMINA_RIFERIMENTO_CONTABILE') or hasRole('RICERCA_RIFERIMENTO_CONTABILE')">
						<div id="tableRowActions" class="btn-group">
							<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
								 <spring:message code="table.actions" />  <span class="caret"></span>
							</button>
							<ul class="dropdown-menu pull-right">
								<li>
									<c:if test="${riga.inVita}">
										
										<c:if test="${not riga.isStorico()}">
											<sec:authorize access="hasRole('RICERCA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/visualizza/${riga.id}"  >
													 <spring:message code="riferimenticontabili.action.visualizza" />
												</a>
											</sec:authorize>
										</c:if>
										
										<c:if test="${riga.isStorico()}">
											<sec:authorize access="hasRole('RICERCA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/visualizza-storico/${riga.idRiferimentoContabilePadre}/${riga.id}"  >
													 <spring:message code="riferimenticontabili.action.visualizza" /> 
												</a>
											</sec:authorize>
										</c:if>
										
										<c:if test="${not riga.isStorico()}">
											<sec:authorize access="hasRole('MODIFICA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/modifica/${riga.id}"  >
													  <spring:message code="riferimenticontabili.action.modifica" /> 
												</a>
											</sec:authorize>
											<sec:authorize access="hasRole('MODIFICA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/chiudi/${riga.id}"  >
													  <spring:message code="riferimenticontabili.action.chiudi" /> 
												</a>
											</sec:authorize>
											<sec:authorize access="hasRole('MODIFICA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/duplica/${riga.id}"  >
													<spring:message code="riferimenticontabili.action.duplica" />
												</a>
											</sec:authorize>
											<sec:authorize access="hasRole('ELIMINA_RIFERIMENTO_CONTABILE')">
												<a class="eliminaAction" onclick="eliminaRiferimentoContabile(${riga.id})">
													 <spring:message code="riferimenticontabili.action.elimina" /> 
												</a>
											</sec:authorize>
										</c:if>
									</c:if>
									<c:if test="${not riga.inVita}">

										<c:if test="${not riga.isStorico()}">
											<sec:authorize access="hasRole('RICERCA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/visualizza/${riga.id}"  >
													<spring:message code="riferimenticontabili.action.visualizza" />
												</a>
											</sec:authorize>
											<sec:authorize access="hasRole('MODIFICA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/duplica/${riga.id}"  >
													<spring:message code="riferimenticontabili.action.duplica" />
												</a>
											</sec:authorize>
										</c:if>

										<c:if test="${riga.isStorico()}">
											<sec:authorize access="hasRole('RICERCA_RIFERIMENTO_CONTABILE')">
												<a class="modificaAction" href="${context}/riferimenti-contabili/visualizza-storico/${riga.idRiferimentoContabilePadre}/${riga.id}"  >
													<spring:message code="riferimenticontabili.action.visualizza" />
												</a>
											</sec:authorize>
										</c:if>

									</c:if>
								</li>
							</ul>
						</div>
					</sec:authorize>
				</td>
				<td class="hidden" data-key="groupkey">
					<c:out value="${riga_codice_versamento.codice}" /> - <c:out value="${riga_codice_versamento.descrizione}" />
				</td>
			</tr>
			</c:forEach>
			
			<c:if test="${ riga_codice_versamento.numeroRiferimentiNonInVita gt 0 and riga_codice_versamento.riferimentiContabili.size() lt riga_codice_versamento.numeroRiferimentiTotali }">
			<tr 
				id='view_cv_<c:out value="${riga_codice_versamento.id}" />' 
				class="riferimento-contabile riga-messaggio-non-mostrati"
				custom-id-codice-versamento="${ riga_codice_versamento.id }"
				custom-numero-riferimenti="${ riga_codice_versamento.numeroRiferimentiInVita }"
				custom-ps-rif-cont="${riga_codice_versamento.codiceTipoPagamento == 'PS' 
				and not empty riga_codice_versamento.riferimentiContabili
				and riga_codice_versamento.riferimentiContabili.size() gt 0 ? 0 : 1}";
			>
				<td data-key="descrizioneCodiceVersamento" data-order="<c:out value="${riga_codice_versamento.codice}" /> - <c:out value="${riga_codice_versamento.descrizione}" />">
				</td>
				<td class=" " data-key="descrizioneTematica" data-order="<c:out value="${riga_codice_versamento.codiceTematica}" /> - <c:out value="${riga_codice_versamento.descrizioneTematica}" />">
				</td>
				<td class=" " data-key="descrizioneMacrotipo" data-order="<c:out value="${riga_codice_versamento.codiceMacrotipo}" /> - <c:out value="${riga_codice_versamento.descrizioneMacrotipo}" />">
				</td>
				<td class=" " data-key="descrizioneVoceEntrata" data-order="<c:out value="${riga_codice_versamento.codiceVoceEntrata}" /> - <c:out value="${riga_codice_versamento.descrizioneVoceEntrata}" />">
				</td>
				<td data-order="ZZZZZZZZZZ" colspan="1">
					<span class="glyphicon glyphicon-eye-close"></span>
					${ riga_codice_versamento.numeroRiferimentiNonInVita } 
					 <spring:message code="riferimenticontabili.ricerca.risultati.tabella.riferimentinoninvita" /> 
				</td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td data-order="ZZZZZZZZZZ"> </td>
				<td></td>
				<td class="hidden"  data-key="groupkey">
					<c:out value="${riga_codice_versamento.codice}" /> - <c:out value="${riga_codice_versamento.descrizione}" />
				</td>
			</tr>
			</c:if>
			
			</c:forEach>
		</tbody>
	</table>
</div>
