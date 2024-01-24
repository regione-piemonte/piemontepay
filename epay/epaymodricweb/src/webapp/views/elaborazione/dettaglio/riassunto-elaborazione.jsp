<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<div class="accordion-heading">
			<h4>
				<spring:message code="elaborazione.dettaglio.riassuntoelaborazione.elaborazione" />
				<span class="pull-right clickable"> 
					<a href="#collapseFilterPanel" data-toggle="collapse"
						aria-expanded="true" data-parent="#filterPanel"
					>
						<i class="glyphicon glyphicon-chevron-up"></i> 
						<i class="glyphicon glyphicon-chevron-down"></i>
					</a>
				</span>
			</h4>
		</div>
		<div id="collapseFilterPanel" class="collapse in">
		
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="dataInserimentoElaborazione">
							<spring:message code="elaborazione.dettaglio.riassuntoelaborazione.datainserimentoelaborazione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="dataInserimentoElaborazione" 
								value="<c:if test="${not empty elaborazione_selezionata.dataElaborazione}"
								><fmt:formatDate value="${elaborazione_selezionata.dataElaborazione}" pattern="dd/MM/yyyy HH:mm:ss" 
								/></c:if><c:if test="${empty elaborazione_selezionata.dataElaborazione}"
								>--</c:if>" 
							/>
						</div>
						
						<div class="col-sm-6 controls">
						</div>

					</div>
				</div>
			</div>

			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="dataInizioElaborazione">
							<spring:message code="elaborazione.dettaglio.riassuntoelaborazione.datainizioelaborazione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="dataInizioElaborazione" 
								value="<c:if test="${not empty elaborazione_selezionata.dataInizio}"
								><fmt:formatDate value="${elaborazione_selezionata.dataInizio}" pattern="dd/MM/yyyy HH:mm:ss" 
								/></c:if><c:if test="${empty elaborazione_selezionata.dataInizio}"
								>--</c:if>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="dataFineElaborazione">
							<spring:message code="elaborazione.dettaglio.riassuntoelaborazione.datafineelaborazione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="dataFineElaborazione" 
								value="<c:if test="${not empty elaborazione_selezionata.dataFine}"
								><fmt:formatDate value="${elaborazione_selezionata.dataFine}" pattern="dd/MM/yyyy HH:mm:ss" 
								/></c:if><c:if test="${empty elaborazione_selezionata.dataFine}"
								>--</c:if>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="statoElaborazione">
							<spring:message code="elaborazione.dettaglio.riassuntoelaborazione.statoelaborazione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="statoElaborazione" 
								value="<c:out value="${elaborazione_selezionata.statoElaborazione}" >--</c:out>" 
							/>
						</div>
						
						<div class="col-sm-6 controls">
						</div>

					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
