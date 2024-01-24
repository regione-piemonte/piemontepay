<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<c:if test ="${not empty lista_risultati_af}">
		<div class="accordion-heading">
			<h4>
				<spring:message code="filter.search" />
				<span class="pull-right clickable">
					<a href="#collapseFilterPanel" data-toggle="collapse" aria-expanded="false" 
					data-parent="#filterPanel" class="anch">
					<i class="glyphicon glyphicon-chevron-up"></i>
					<i class="glyphicon glyphicon-chevron-down"></i></a>
				</span>
			</h4>
		</div>
		<div id="collapseFilterPanel" class="collapse">
		</c:if>
		<c:if test ="${empty lista_risultati_af}">
		<div class="accordion-heading">
			<h4>
				<spring:message code="filter.search" />
				<span class="pull-right clickable">
					<a href="#collapseFilterPanel" data-toggle="collapse" aria-expanded="true" 
					data-parent="#filterPanel" class="anch">
					<i class="glyphicon glyphicon-chevron-up"></i>
					<i class="glyphicon glyphicon-chevron-down"></i></a>
				</span>
			</h4>
		</div>
		<div id="collapseFilterPanel" class="collapse in">
		</c:if>
			<div class="row-fluid">
				<p><spring:message code="text.atleastonecondition" /></p>
			</div>

			<%@ include file="form.jsp"%>
		</div>
	</div>
</div>
