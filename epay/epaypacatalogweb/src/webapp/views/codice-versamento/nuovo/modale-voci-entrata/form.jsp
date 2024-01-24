<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<form id="entry-ricerca-vocientrata" name="entry-ricerca-vocientrata" >
	<fieldset>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-sm-12 control-label">
					<form:errors path="id" cssClass="boxedError" /></label>    
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">

				<div class="form-group ">
					<label class="col-sm-2 control-label" for="codice"><spring:message code="form.ricerca.codice" />
					</label>
					<div class="col-sm-9 controls">
						<input type="text" name="codice" class="form-control input-filtro-codice-voce-entrata" id="codice" />
					</div>
				</div>
			</div>
		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">

				<div class="form-group ">
					<label class="col-sm-2 control-label" for="descrizione"><spring:message code="form.ricerca.descrizione" />
					</label>
					<div class="col-sm-9 controls">
						<input type="text" class="form-control input-filtro-descrizione-voce-entrata" id="descrizione" name="descrizione" />
					</div>
				</div>
			</div>
		</div>


		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-sm-2 control-label"
						for="entry-ricerca-listedicarico_idCodVersamento"><spring:message code="form.ricerca.tematica" />
					</label>
					<div class="col-sm-9 controls">
						<select name="codiceTematica" class="form-control input-filtro-tematica">
							<option value=""><spring:message code="form.ricerca.qualsiasi" /></option>
							<c:forEach var="opzione" items="${modelTematiche}">
								<option value="${opzione.codice}">
									<c:out value="${opzione.codiceDescrizione}" />
								</option>
							</c:forEach>
						</select>

					</div>
				</div>
			</div>
		</div>
				
		<div class="container-fluid">
			<div class="row-fluid">

				<div class="form-group ">
					<label class="col-sm-2 control-label"
						for="entry-ricerca-listedicarico_idCodVersamento"><spring:message code="form.ricerca.macrotipo" />
					</label>
					<div class="col-sm-9 controls">
						<select name="codiceMacrotipo" class="form-control input-filtro-macrotipo">
							<option value=""><spring:message code="form.ricerca.qualsiasi" /></option>
							<c:forEach var="opzione" items="${modelMacrotipi}">
								<option value="${opzione.codice}">
									<c:out value="${opzione.codiceDescrizione}" />
								</option>
							</c:forEach>
						</select>

					</div>
				</div>

			</div>
		</div>

		<div class="row-fluid">
			<p class="margin-medium">
				<a id="formSubmitButtonPulisciVE" class="btn btn-secondary btn-action" onclick="pulisciRicercaVE()"><spring:message code="button.cleanup" /></a> 
				<a class="btn btn-primary btn-action pull-right" id="formSubmitButtonCercaVE" onclick="eseguiRicercaVE()"><spring:message code="button.search" /></a>
			</p>
		</div>
	</fieldset>
</form>
