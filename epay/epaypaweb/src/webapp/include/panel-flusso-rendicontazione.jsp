<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="step-content form-horizontal">
	<div class="step-pane active" id="memoParameters">
		<div class="accordion-heading">
			<h4>
				Testata
				<span class="pull-right clickable">
					<s:a
						data-toggle="collapse" href="#collapseMemoPanel"
						data-parent="#memoParameters"
						aria-expanded="true">
						<i class="glyphicon glyphicon-chevron-up"></i>
						<i class="glyphicon glyphicon-chevron-down"></i>
					</s:a>
				</span>
			</h4>
		</div>						
		
		<div id="collapseMemoPanel" class="collapse in">
			<div class="container-fluid">
				<div class="row-fluid">
					<label class="col-sm-4 control-label">Lotto:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.idMessaggio"/>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Id flusso rendicontazione:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.idFlussoRendicontazione"/>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Numero totale pagamenti:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.numeroElementi"/></p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Importo totale pagamenti:</label>
					<p class="col-sm-8 form-control-static">
						<s:if test="flussoSelezionato.importoTotale != null">
							<s:text name="format.money">
								<s:param name="value" value="flussoSelezionato.importoTotale"/>
							</s:text>
						</s:if>
					</p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Data e ora creazione flusso:</label>
					<p class="col-sm-8 form-control-static">
						<s:if test="flussoSelezionato.dataOraCreazioneFlusso != null">
							<s:text name="format.dateTime">
								<s:param name="value" value="flussoSelezionato.dataOraCreazioneFlusso"/>
							</s:text>
						</s:if>
					</p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Id univoco regolamento:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.idUnivocoRegolamento"/></p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Data regolamento:</label>
					<p class="col-sm-8 form-control-static">
						<s:if test="flussoSelezionato.dataRegolamento != null">
							<s:text name="format.date">
								<s:param name="value" value="flussoSelezionato.dataRegolamento"/>
							</s:text>
						</s:if>
					</p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Tipo mittente:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.idAndDesTipoMittente"/></p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Cod. mittente:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.codIdUnivocoMittente"/></p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Denominazione mittente:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.denominazioneMittente"/></p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Cod. BIC PSP:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.codBicPsp"/></p>
				</div>
			</div>
		</div>
	</div>
</div>
