<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->


<div class="">
	<div class="step-pane active" id="filterPanel">
		<div id="collapseFilterPanel" class="collapse in">
		
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="codiceFiscalePagatore">
							<spring:message code="flusso.riassuntoflussodettaglio.codicefiscalepagatore" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="codiceFiscalePagatore" 
								value="<c:out value="${dettaglio_flusso.codiceFiscalePagatore}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="anagraficaPagatore">
							<spring:message code="flusso.riassuntoflussodettaglio.anagraficapagatore" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="anagraficaPagatore" 
								value="<c:out value="${dettaglio_flusso.anagraficaPagatore}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="causale">
							<spring:message code="flusso.riassuntoflussodettaglio.causale" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="causale" 
								value="<c:out value="${dettaglio_flusso.causale}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="codiceVersamento">
							<spring:message code="flusso.riassuntoflussodettaglio.codiceversamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text"  class="form-control" disabled
								name="codiceVersamento" 
								value="<c:out value="${dettaglio_flusso.codiceVersamento}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="importoSingoloVersamento">
							<spring:message code="flusso.riassuntoflussodettaglio.importosingoloversamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="importoSingoloVersamento" 
								value="<c:if test="${not empty dettaglio_flusso.importoSingoloVersamento}"
								><fmt:formatNumber value="${dettaglio_flusso.importoSingoloVersamento}" type="currency" currencySymbol=""
								/> &euro;</c:if><c:if test="${empty dettaglio_flusso.importoSingoloVersamento}"
								>--</c:if>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="descrizioneVersamento">
							<spring:message code="flusso.riassuntoflussodettaglio.descrizioneversamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="descrizioneVersamento" 
								value="<c:out value="${dettaglio_flusso.descrizioneVersamento}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="identificativoUnicoVersamento">
							<spring:message code="flusso.riassuntoflussodettaglio.identificativounicoversamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="identificativoUnicoVersamento" 
								value="<c:out value="${dettaglio_flusso.identificativoUnicoVersamento}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="indiceSingoloVersamento">
							<spring:message code="flusso.riassuntoflussodettaglio.indicesingoloversamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="indiceSingoloVersamento" 
								value="<c:out value="${dettaglio_flusso.indiceSingoloVersamento}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="esitoPagamento">
							<spring:message code="flusso.riassuntoflussodettaglio.esitopagamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="esitoPagamento" 
								value="<c:out value="${dettaglio_flusso.esitoPagamento}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="dataPagamento">
							<spring:message code="flusso.riassuntoflussodettaglio.datapagamento" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="dataPagamento" 
								value="<c:if test="${not empty dettaglio_flusso.dataPagamento}"
								><fmt:formatDate value="${dettaglio_flusso.dataPagamento}" pattern="dd/MM/yyyy" 
								/></c:if><c:if test="${empty dettaglio_flusso.dataPagamento}"
								>--</c:if>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="idTransazione">
							<spring:message code="flusso.riassuntoflussodettaglio.idtransazione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="idTransazione" 
								value="<c:out value="${dettaglio_flusso.idTransazione}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="datiSpecificiDiRiscossione">
							<spring:message code="flusso.riassuntoflussodettaglio.datispecificidiriscossione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="datiSpecificiDiRiscossione" 
								value="<c:out value="${dettaglio_flusso.datiSpecificiDiRiscossione}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="identificativoUnicoRiscossione">
							<spring:message code="flusso.riassuntoflussodettaglio.identificativounicoriscossione" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="identificativoUnicoRiscossione" 
								value="<c:out value="${dettaglio_flusso.identificativoUnicoRiscossione}" >--</c:out>" 
							/>
						</div>

						<label class="col-sm-2 control-label" for="statoInvioFruitore">
							<spring:message code="flusso.riassuntoflussodettaglio.statoinviofruitore" />
						</label>
						<div class="col-sm-4 controls">
							<input type="text" class="form-control" disabled
								name="statoInvioFruitore" 
								value="<c:out value="${dettaglio_flusso.statoInvioFruitore}" >--</c:out>" 
							/>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
