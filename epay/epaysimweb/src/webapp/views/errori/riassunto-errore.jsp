<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="">
	<div class="step-pane active" id="filterPanel">
		<div id="collapseFilterPanel" class="collapse in">

			<form:hidden path="id" id="id" name="id" />
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="identificativoFlusso"> Identificativo flusso </label>
						<div class="col-sm-4 controls">
							<form:input id="identificativoFlusso" name="identificativoFlusso"
								path="identificativoFlusso" class="form-control"
								placeholder="identificativo flusso"
								disabled="${ disable_input }" />
							<form:errors path="identificativoFlusso" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="dataInserimento"> Data Inserimento </label>
						<div class="col-sm-4 controls">
							<form:input id="dataInserimento" name="dataInserimento"
								path="dataInserimento" class="form-control datepicker"
								placeholder="Data Inserimento" disabled="${ disable_input }" />
							<form:errors path="dataInserimento" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="dataRegolamento"> Data Regolamento </label>
						<div class="col-sm-4 controls">
							<form:input id="dataRegolamento" name="dataRegolamento"
								path="dataRegolamento" class="form-control datepicker"
								placeholder="Data regolamento" disabled="${ disable_input }" />
							<form:errors path="dataRegolamento" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required" for="dataoraFlusso">
							Data ora flusso</label>
						<div class="col-sm-4 controls">
							<form:input id="dataoraFlusso" name="dataoraFlusso"
								path="dataoraFlusso" class="form-control datepicker"
								placeholder="Data ora flusso" disabled="${ disable_input }" />
							<form:errors path="dataoraFlusso" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="descrizioneErrore"> Descrizione errore </label>
							
						<div class="col-sm-4 controls">
                            <form:textarea id="descrizioneErrore" name="descrizioneErrore"
								path="descrizioneErrore" class="form-control"
								placeholder="Descrizione errore" disabled="${ disable_input }" row="2"/>
							<form:errors path="descrizioneErrore" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="identificativoPsp"> Identificativo PSP</label>
						<div class="col-sm-4 controls">
							<form:input id="identificativoPsp" name="identificativoPsp"
								path="identificativoPsp" class="form-control"
								placeholder="Identificativo PSP" disabled="${ disable_input }" />
							<form:errors path="identificativoPsp" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="numeroTotalePagamenti"> Num. totale pagamenti</label>
						<div class="col-sm-4 controls">
							<form:input id="numeroTotalePagamenti"
								name="numeroTotalePagamenti" path="numeroTotalePagamenti"
								class="form-control" placeholder="Num. totale pagamenti"
								disabled="${ disable_input }" />
							<form:errors path="numeroTotalePagamenti" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="numeroTotalePagamentiIntermediati"> Tot.
							pag. interm.</label>
						<div class="col-sm-4 controls">
							<form:input id="numeroTotalePagamentiIntermediati"
								name="numeroTotalePagamentiIntermediati"
								path="numeroTotalePagamentiIntermediati" class="form-control"
								placeholder="Num. totale pagamenti intermediati"
								disabled="${ disable_input }" />
							<form:errors path="numeroTotalePagamentiIntermediati"
								cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="importoTotalePagamenti"> Totale pagamenti</label>
						<div class="col-sm-4 controls">
							<form:input id="importoTotalePagamenti"
								name="importoTotalePagamenti" path="importoTotalePagamenti"
								class="form-control" placeholder="Importo totale pagamenti"
								disabled="${ disable_input }" />
							<form:errors path="importoTotalePagamenti" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="importoTotalePagamentiIntermediati"> Tot.
							pagam. interm.</label>
						<div class="col-sm-4 controls">
							<form:input id="importoTotalePagamentiIntermediati"
								name="importoTotalePagamentiIntermediati"
								path="importoTotalePagamentiIntermediati" class="form-control"
								placeholder="Importo totale pagamenti intermediati"
								disabled="${ disable_input }" />
							<form:errors path="importoTotalePagamentiIntermediati"
								cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="ibanRiversamentoFlusso"> Iban riversamento</label>
						<div class="col-sm-4 controls">
							<form:input id="ibanRiversamentoFlusso"
								name="ibanRiversamentoFlusso" path="ibanRiversamentoFlusso"
								class="form-control" placeholder="Iban riversamento"
								disabled="${ disable_input }" />
							<form:errors path="ibanRiversamentoFlusso" cssClass="boxedError" />
						</div>
					</div>

					<!-- SEZIONE RELATIVA AGLI ID -->

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="idElaborazione"> ID Elaborazione</label>
						<div class="col-sm-4 controls">
							<form:input id="idElaborazione" name="idElaborazione"
								path="idElaborazione" class="form-control"
								placeholder="ID Elaborazione" disabled="${ disable_input }" />
							<form:errors path="idElaborazione" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="idStatoFlussolusso"> ID Stato Flusso</label>
						<div class="col-sm-4 controls">
							<form:input id="idStatoFlussolusso" name="idStatoFlussolusso"
								path="idStatoFlussolusso" class="form-control"
								placeholder="ID Stato flusso" disabled="${ disable_input }" />
							<form:errors path="idStatoFlussolusso" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="codiceVersamento"> Codice Versamento</label>
						<div class="col-sm-4 controls">
							<form:input id="codiceVersamento" name="codiceVersamento"
								path="codiceVersamento" class="form-control"
								placeholder="Codice versamento" disabled="${ disable_input }" />
							<form:errors path="codiceVersamento" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required" for="codiceErrore">
							Codice Errore</label>
						<div class="col-sm-4 controls">
							<form:input id="codiceErrore" name="codiceErrore"
								path="codiceErrore" class="form-control"
								placeholder="Codice errore" disabled="${ disable_input }" />
							<form:errors path="codiceErrore" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required" for="idMessaggio">
							ID Messaggio</label>
						<div class="col-sm-4 controls">
							<form:input id="idMessaggio" name="idMessaggio"
								path="idMessaggio" class="form-control"
								placeholder="ID Messaggio" disabled="${ disable_input }" />
							<form:errors path="idMessaggio" cssClass="boxedError" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label required"
							for="identificativoUnivocoRegolamento"> IUR</label>
						<div class="col-sm-4 controls">
							<form:input id="identificativoUnivocoRegolamento"
								name="identificativoUnivocoRegolamento"
								path="identificativoUnivocoRegolamento" class="form-control"
								placeholder="IUR" disabled="${ disable_input }" />
							<form:errors path="identificativoUnivocoRegolamento"
								cssClass="boxedError" />
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
