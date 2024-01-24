<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form theme="bootstrap" cssClass="form-horizontal">
	<div id="alert-div"></div>
	
	<h3><s:property value="#pageTitle"/></h3>
	
	<div class="step-content">
		<div class="step-pane active" id="filterPanel">
			<div class="accordion-heading">
				<h4>
					Filtro di ricerca
					<span class="pull-right clickable">
						<s:a
							data-toggle="collapse" href="#collapseFilterPanel"
							data-parent="#filterPanel"
							aria-expanded="true">
							<i class="glyphicon glyphicon-chevron-up"></i>
							<i class="glyphicon glyphicon-chevron-down"></i>
						</s:a>
					</span>
				</h4>
			</div>

			<div id="collapseFilterPanel" class="collapse in">
				<div class="row-fluid">
					<p><i>&Egrave; necessario inserire almeno un criterio di ricerca.</i></p>
				</div>
	
				<div class="container-fluid">
					<div class="row-fluid">
						<s:select name="idCodVersamento"
							label="Codice versamento" labelCssClass="col-sm-2"
							list="sessionContext.codiciVersamentoListeDiCarico" listKey="id"
							listValue="codDes" headerKey="" headerValue="Scegli" />
					</div>

					<div class="row-fluid">
						<s:select name="idStatoFlusso"
							label="Stato flusso" labelCssClass="col-sm-2"
							list="statoFlussoList" listKey="id"
							listValue="option" headerKey="" headerValue="Scegli" />
					</div>

					<div class="row-fluid">
						<s:textfield id="dataStatoFlussoInizio" name="dataStatoFlussoInizio"
							label="Data inizio (Stato flusso)" labelCssClass="col-sm-2" cssClass="span2 datepicker" />
					</div>

					<div class="row-fluid">
						<s:textfield id="dataStatoFlussoFine" name="dataStatoFlussoFine"
							label="Data fine (Stato flusso)" labelCssClass="col-sm-2" cssClass="span2 datepicker" />
					</div>
					<div class="row-fluid">
						<s:textfield id="iuv" name="iuv" label="Id univoco versamento(IUV)" labelCssClass="col-sm-2" cssClass="span2" />
					</div>
					<div class="row-fluid">
						<s:textfield id="cfisc" name="cfisc" label="Codice fiscale/Partita Iva (Soggetto debitore)" labelCssClass="col-sm-2" cssClass="span2" />
					</div>
					<div class="row-fluid">
                        <s:textfield id="idPosizioneDebitoriaEsterna" name="idPosizioneDebitoriaEsterna" label="Posizione debitoria esterna" labelCssClass="col-sm-2" cssClass="span2" />
                    </div>
				</div>
				<div class="row-fluid">
					<div class="margin-medium">
						<a class="btn" id="formSubmitButtonPulisci">Pulisci</a>
						<a class="btn btn-primary pull-right" id="formSubmitButtonCerca">Cerca</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>

<br/>

<s:hidden id="param_idStatoFlusso" value="%{idStatoFlusso}" theme="simple"/>
<s:hidden id="param_idCodVersamento" value="%{idCodVersamento}" theme="simple"/>
<s:hidden id="param_dataStatoFlussoInizio" value="%{dataStatoFlussoInizio}" theme="simple"/>
<s:hidden id="param_dataStatoFlussoFine" value="%{dataStatoFlussoFine}" theme="simple"/>
<s:hidden id="param_iuv" value="%{iuv}" theme="simple"/>
<s:hidden id="param_cfisc" value="%{cfisc}" theme="simple"/>
<s:hidden id="param_idPosizioneDebitoriaEsterna" value="%{idPosizioneDebitoriaEsterna}" theme="simple"/>
<s:hidden id="initTable" name="init" theme="simple"/>
<s:hidden id="executeSearch" name="executeSearch" theme="simple"/>
