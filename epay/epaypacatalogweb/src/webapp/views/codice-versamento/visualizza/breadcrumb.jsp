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
				<a href="${context}/codici-versamento/ricerca"><spring:message code="codiceversamento.collegato.breadcrumb.ricercacodiciversamento" /></a>
				<span class="divider"></span>
			</li>
			<li class="active">
				<spring:message code="codiceversamento.visualizza.breadcrumb.visualizzacodiceversamento" />
			</li>
		</ul>
	</div>
</div>
