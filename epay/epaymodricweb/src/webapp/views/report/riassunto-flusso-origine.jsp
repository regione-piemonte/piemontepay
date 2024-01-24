<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
	<div class="step-pane active" id="filterPanel">
		<div class="accordion-heading">
			<h4>
				<spring:message code="flusso.riassuntoflussoorigine.flussodiorigine" />
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
						<label class="col-sm-2 control-label" for="identificativoFlusso">
							<spring:message code="flusso.riassuntoflussoorigine.identificativoflusso" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="identificativoFlusso" 
								value="<c:out value="${sintesi_flusso.flussoOrigine.identificativoFlusso}" >--</c:out>" 
							/>
						</div>
						
						<label class="col-sm-2 control-label" for="descrizioneStato">
							<spring:message code="flusso.riassuntoflussoorigine.statoflusso" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="descrizioneStato" 
								value="<c:out value="${sintesi_flusso.flussoOrigine.descrizioneStato}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="identificativoIstitutoRicevente">
							<spring:message code="flusso.riassuntoflussoorigine.istitutoricevente" />
						</label>
						<div class="col-sm-4 controls">
							<textarea class="form-control" disabled name="identificativoIstitutoRicevente" maxlength="70" row="2"><c:out value="${sintesi_flusso.flussoOrigine.identificativoIstitutoRicevente}" >--</c:out></textarea>
						</div>

						<label class="col-sm-2 control-label" for="identificativoPsp">
							<spring:message code="flusso.riassuntoflussoorigine.psp" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="identificativoPsp" 
								value="<c:out value="${sintesi_flusso.flussoOrigine.identificativoPsp}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ibanRiversamentoFlusso">
							<spring:message code="flusso.riassuntoflussoorigine.ibanriversamentoflusso" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="ibanRiversamentoFlusso" 
								value="<c:out value="${sintesi_flusso.flussoOrigine.ibanRiversamentoFlusso}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="contatoreTentativi">
							<spring:message code="flusso.riassuntoflussoorigine.contatoretentativi" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="contatoreTentativi" 
								value="<c:out value="${sintesi_flusso.flussoOrigine.contatoreTentativi}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="importoTotalePagamenti">
							<spring:message code="flusso.riassuntoflussoorigine.importototalepagamenti" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="importoTotalePagamenti" 
								value="<c:if test="${not empty sintesi_flusso.flussoOrigine.importoTotalePagamenti}"
								><fmt:formatNumber value="${sintesi_flusso.flussoOrigine.importoTotalePagamenti}" type="currency" currencySymbol=""
								/> &euro;</c:if><c:if test="${empty sintesi_flusso.flussoOrigine.importoTotalePagamenti}"
								>--</c:if>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="numeroTotalePagamenti">
							<spring:message code="flusso.riassuntoflussoorigine.numerototalepagamenti" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="numeroTotalePagamenti" 
								value="<c:out value="${sintesi_flusso.flussoOrigine.numeroTotalePagamenti}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="dataInserimento">
							<spring:message code="flusso.riassuntoflussoorigine.datainserimento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="dataInserimento" 
								value="<c:if test="${not empty sintesi_flusso.flussoOrigine.dataInserimento}"
								><fmt:formatDate value="${sintesi_flusso.flussoOrigine.dataInserimento}" pattern="dd/MM/yyyy HH:mm:ss" 
								/></c:if><c:if test="${empty sintesi_flusso.flussoOrigine.dataInserimento}"
								>--</c:if>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="dataOraFlusso">
							<spring:message code="flusso.riassuntoflussoorigine.dataoraflusso" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="dataOraFlusso" 
								value="<c:if test="${not empty sintesi_flusso.flussoOrigine.dataOraFlusso}"
								><fmt:formatDate value="${sintesi_flusso.flussoOrigine.dataOraFlusso}" pattern="dd/MM/yyyy HH:mm:ss" 
								/></c:if><c:if test="${empty sintesi_flusso.flussoOrigine.dataOraFlusso}"
								>--</c:if>" 
							/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
