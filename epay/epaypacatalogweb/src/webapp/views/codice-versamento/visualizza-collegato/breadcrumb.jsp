<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="row-fluid">
	<div class="span12">
		<ul class="breadcrumb">
			<li>
				<a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
			</li>
			<li>
				<a href="${context}/codici-versamento/ricerca"><spring:message code="codiceversamento.breadcrumb.ricercacodiciversamento" /></a>
				<span class="divider"></span>
			</li>
			
			<c:if test="${pagina_origine_codice_versamento eq 'modifica'}">
			<li>
				<a href="${context}/codici-versamento/modifica/${id_codice_versamento}">
				<spring:message code="codiceversamento.collegato.breadcrumb.modificacodiceversamento" /></a>
				<span class="divider"></span>
			</li>
			</c:if>
			
			<c:if test="${pagina_origine_codice_versamento eq 'visualizza'}">
			<li>
				<a href="${context}/codici-versamento/visualizza/${id_codice_versamento}">
				<spring:message code="codiceversamento.visualizza.breadcrumb.visualizzacodiceversamento" /></a>
				<span class="divider"></span>
			</li>
			</c:if>
			
			<li class="active"><spring:message code="codiceversamento.ricerca.tabellarisultati.visualizzacodiceversamentoassociato" /></li>
		</ul>
	</div>
</div>
