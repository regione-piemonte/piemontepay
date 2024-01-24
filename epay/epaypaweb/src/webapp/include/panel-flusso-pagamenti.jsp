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
					<label class="col-sm-4 control-label">Codice versamento:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.codVersamento"/> - <s:property value="flussoSelezionato.desCodVersamento"/></p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Numero versamenti:</label>
					<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.numeroElementi"/></p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Importo totale:</label>
					<p class="col-sm-8 form-control-static">
						<s:if test="flussoSelezionato.importoTotale != null">
							<s:text name="format.money">
								<s:param name="value" value="flussoSelezionato.importoTotale"/>
							</s:text>
						</s:if>
					</p>
				</div>

				<div class="row-fluid">
					<label class="col-sm-4 control-label">Data ricezione:</label>
					<p class="col-sm-8 form-control-static">
						<s:if test="flussoSelezionato.dataInserimento != null">
							<s:text name="format.date">
								<s:param name="value" value="flussoSelezionato.dataInserimento"/>
							</s:text>
						</s:if>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
