<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<div class="accordion-heading">
			<h4>
				<spring:message code="text.utenteselezionato" />
				<span class="pull-right clickable"> 
					<a href="#collapseFilterPanel" data-toggle="collapse" aria-expanded="true" data-parent="#filterPanel">
						<i class="glyphicon glyphicon-chevron-down"></i> 
						<i class="glyphicon glyphicon-chevron-up"></i>
					</a>
				</span>
			</h4>
		</div>
		<div id="collapseFilterPanel" class="">
			<input type="hidden" name="selectedUserId" value='<c:out value="${selectedUser.id}" />'  />
			<div class="form-group ">
				<label class="col-sm-3 control-label" for="strCodicefiscale"><spring:message code="filter.ssnNumber" /></label>
				<div class="col-sm-9 controls">
					<input disabled type="text" name="codicefiscale" value='<c:out value="${selectedUser.codiceFiscale}" />' id="strCodicefiscale" 
						class="form-control input-codice-fiscale" />
				</div>
			</div>
			<div class="form-group ">
				<label class="col-sm-3 control-label" for="strNominativo"><spring:message code="filter.name" /> </label>
				<div class="col-sm-9 controls">
					<input disabled type="text" name="nominativo" value='<c:out value="${selectedUser.nome} ${selectedUser.cognome}" />' 
					id="strNominativo" class="form-control input-nome-cognome" />
				</div>
			</div>
		</div>
	</div>
</div>
