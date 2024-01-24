<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<div class="accordion-heading">
			<h4>
				<spring:message code="flusso.riassuntoflussosintesi.flussodisintesi" /> 
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
						<label class="col-sm-2 control-label" for="codiceVersamento">
							<spring:message code="flusso.riassuntoflussosintesi.codiceversamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="codiceVersamento" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.codiceVersamento}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="datiSpecificiDiRiscossione">
							<spring:message code="flusso.riassuntoflussosintesi.datispecificidiriscossione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="datiSpecificiDiRiscossione" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.datiSpecificiDiRiscossione}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="descrizioneVersamento">
							<spring:message code="flusso.riassuntoflussosintesi.descrizioneversamento" />
						</label>
						<div class="col-sm-10 controls">
							<input type="text"  class="form-control" disabled
								name="descrizioneVersamento" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.descrizioneVersamento}" >--</c:out>" 
							/>
						</div>

					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="importoQuotaAggregazione">
							<spring:message code="flusso.riassuntoflussosintesi.importoquotaaggregazione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="importoQuotaAggregazione" 
								value="<c:if test="${not empty dettaglio_flusso.flussoSintesi.importoQuotaAggregazione}"
								><fmt:formatNumber value="${dettaglio_flusso.flussoSintesi.importoQuotaAggregazione}" type="currency" currencySymbol=""
								/> &euro;</c:if><c:if test="${empty dettaglio_flusso.flussoSintesi.importoQuotaAggregazione}"
								>--</c:if>" 
							/>
						</div>
						
						<label class="col-sm-2 control-label" for="numeroVersamentoQuotaAggregazione">
							<spring:message code="flusso.riassuntoflussosintesi.numeroversamentoquotaaggregazione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="numeroVersamentoQuotaAggregazione" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.numeroVersamentoQuotaAggregazione}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="provvisorioAnno">
							<spring:message code="flusso.riassuntoflussosintesi.provvisorioanno" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="provvisorioAnno" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.provvisorioAnno}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="provvisorioNumero">
							<spring:message code="flusso.riassuntoflussosintesi.provvisorionumero" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="provvisorioNumero" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.provvisorioNumero}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			
				<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="numeroAccertamento">
							<spring:message code="flusso.riassuntoflussosintesi.numeroaccertamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="provvisorioAnno" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.numeroAccertamento}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="annoAccertamento">
							<spring:message code="flusso.riassuntoflussosintesi.annoaccertamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="annoAccertamento" 
								value="<c:out value="${dettaglio_flusso.flussoSintesi.annoAccertamento}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
