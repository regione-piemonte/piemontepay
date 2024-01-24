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
			<li class="active">
				${scenario == 'modifica' ? 'Modifica' : 'Nuovo' } 
				<spring:message code="filter.codiceversamento" />
			</li>
		</ul>
	</div>
</div>
