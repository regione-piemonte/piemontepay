<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../include/head.jsp"%>

<body>

	<%@ include file="../include/modal-confirm.jsp"%>
	<%@ include file="../include/modal-pleasewait.jsp"%>

	<p class="nascosto">
		<a title="A-sommario"></a>
	</p>
	<ul id="sommario" class="nascosto">
		<li><s:a href="#A-contenuti">Salta ai contenuti</s:a></li>
	</ul>
	<hr />

	<div class="container-fluid-banner">
		<%@ include file="../include/portal-header.html"%>
		<%@ include file="navbar-login.jsp"%>
		<a title="A-contenuti"></a>
	</div>

	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a id="backToHomeAction1">Home</a><span class="divider"></span></li>
				<s:if test="!origineHomePerInserimento">
					<li><a id="backToRicercaListeAggiornamentoPosizioniDebitorieAction1">Ricerca liste aggiornamento posizioni debitorie</a><span class="divider"></span></li>
				</s:if>
				<li class="active"><s:property value="operativita.des"/> lista aggiornamento posizioni debitorie</li>
			</ul>
		</div>
	</div>

	<s:hidden id="param_idFlusso" value="%{idFlusso}" theme="simple"/>
	<s:hidden id="param_restartFrom" value="%{restartFrom}" theme="simple"/>
	<s:hidden id="param_sortingDir" value="%{sortingDir}" theme="simple"/>
	<s:hidden id="param_sortingIdx" value="%{sortingIdx}" theme="simple"/>
	<s:hidden id="param_pageLength" value="%{pageLength}" theme="simple"/>
	<s:hidden id="initTable" name="init" theme="simple"/>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
<%--				/epaypaweb/entry-visualizza-flusso-posizionidebitoriedaaggiornare.do--%>
				<s:form id="formId" action="nuova-posizionedebitoriadaaggiornare.do" method="POST" cssClass="form-horizontal" theme="bootstrap">
					<div id="alert-div"></div>

					<%@ include file="../include/validation-message.jsp"%>

					<h3><s:property value="operativita.des"/> lista aggiornamento posizioni debitorie</h3>
					<s:hidden id="initTable" name="init" theme="simple"/>
					<s:hidden id="origineHomePerInserimento" name="origineHomePerInserimento"/>
					<s:hidden id="operativita" name="operativita"/>
					<s:hidden name="idFlusso"/>
					<s:hidden name="addendoElemento" value="1"/>

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
								<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI || operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@MODIFICA">
									<div class="row-fluid">
										<p><i>I campi contrassegnati con * sono obbligatori.</i></p>
									</div>

									<div class="container-fluid">
										<div class="row-fluid">
											<s:textfield name="idMessaggio" disabled="true"
												label="Lotto" labelCssClass="col-sm-3" cssClass="span3"/>
											<s:hidden name="idMessaggio"/>
										</div>
					
										<div class="row-fluid">
											<s:select id="testata_idCodVersamento" name="idCodVersamento"
												label="Codice versamento*" labelCssClass="col-sm-3" cssClass="span10"
												list="codiciVersamentoListeDiCarico" listKey="id"
												listValue="codDes" headerKey="" />
										</div>

										<div class="row-fluid">
											<s:textfield name="numeroElementi" disabled="true"
												label="Numero posizioni debitorie" labelCssClass="col-sm-3" cssClass="span2"/>
											<s:hidden name="numeroElementi"/>
										</div>
									</div>
					
									<div class="row-fluid">
										<div class="margin-medium">
											<s:submit id="ripristinaTestataAction" value="Ripristina" cssClass="btn"/>
											<div class="pull-right">
												<s:if test="idFlusso != null">
													<s:submit id="salvaTestataAction" value="Salva testata" cssClass="btn btn-primary"/>
												</s:if>
												<s:else>
													<a class="btn btn-primary disabled">Salva testata</a>
												</s:else>
												<s:submit value="Nuova posizione debitoria da aggiornare" cssClass="btn btn-primary"/>
											</div>
										</div>
									</div>
								</s:if>
								<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@VISUALIZZA">
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Lotto:</label>
										<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.idMessaggio"/>
									</div>
					
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Codice versamento:</label>
										<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.codVersamento"/> - <s:property value="flussoSelezionato.desCodVersamento"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Multibeneficiario:</label>
										<p class="col-sm-8 form-control-static"><s:property value="multibeneficiario"/></p>
									</div>

									<div class="row-fluid">
										<label class="col-sm-4 control-label">Numero posizioni debitorie:</label>
										<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.numeroElementi"/></p>
									</div>
					
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Stato flusso:</label>
										<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.desStatoFlusso"/></p>
									</div>
					
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Data:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="flussoSelezionato.dataUltimaVariazione != null">
												<s:text name="format.date">
													<s:param name="value" value="flussoSelezionato.dataUltimaVariazione"/>
												</s:text>
											</s:if>
										</p>
									</div>
					
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Esito:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="flussoSelezionato.codEsito != null">
												<s:property value="flussoSelezionato.codEsito"/>
												<s:if test="flussoSelezionato.detEsito != null">
													-
												</s:if>
											</s:if>
											<s:if test="flussoSelezionato.detEsito != null">
												<s:property value="flussoSelezionato.detEsito"/>
											</s:if>
										</p>
									</div>
								</s:if>
							</div>
						</div>
					</div>
				</s:form>

				<br/>

				<s:if test="idFlusso != null">
					<table class="table table-hover tab_left dataTable" id="tabellaRisultati">
						<thead>
							<tr>
								<th>Tipo aggiornamento</th>
								<th>Posizione debitoria esterna</th>
								<th>Motivazione</th>
								<th>Codice avviso</th>
								<th>Esito</th>
								<th class="tab_Right span1">&nbsp;</th>
							</tr>
						</thead>
						<tbody/>
					</table>
				</s:if>

				<div class="margin-medium">
					<a id="backToHomeAction2" class="btn">HOME</a>
					<s:if test="!origineHomePerInserimento">
						/ <a id="backToRicercaListeAggiornamentoPosizioniDebitorieAction2" class="btn">RICERCA FLUSSI</a>
					</s:if>
					<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI || operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@MODIFICA">
						<s:if test="idFlusso != null">
							<s:a action="pubblica-flusso-posizionidebitoriedaaggiornare" cssClass="btn btn-primary pull-right">Pubblica</s:a>
						</s:if>
						<s:else>
							<a class="btn btn-primary pull-right disabled">Pubblica</a>
						</s:else>
					</s:if>
				</div>
			</div>
		</div>
	</div>

	<div id="tableRowActions" class="btn-group hide">
		<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI || operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@MODIFICA">
			<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
				Azioni <span class="caret"></span>
			</button>
			<ul class="dropdown-menu pull-right">
				<s:if test="isUseCaseEnabled('DET_AGPD')">
					<li><a id="dettaglioPosizioneDebitoriaDaAggiornareAction" data-form-id="dettaglioPosizioneDebitoriaDaAggiornareForm">dettaglio posizione</a></li>
				</s:if>
				<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI || operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@MODIFICA">
					<s:if test="isUseCaseEnabled('INS_AGPD')">
						<li><a id="modificaPosizioneDebitoriaDaAggiornareAction" data-form-id="modificaPosizioneDebitoriaDaAggiornareForm">modifica posizione</a></li>
					</s:if>
					<s:if test="isUseCaseEnabled('ELM_AGPD')">
						<li><a id="eliminaPosizioneDebitoriaDaAggiornareAction" data-form-id="eliminaPosizioneDebitoriaDaAggiornareForm">elimina posizione</a></li>
					</s:if>
				</s:if>
			</ul>
		</s:if>
		<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@VISUALIZZA">
			<s:if test="isUseCaseEnabled('DET_AGPD')">
				<a id="dettaglioPosizioneDebitoriaDaAggiornareAction" data-form-id="dettaglioPosizioneDebitoriaDaAggiornareForm" class="btn">dettaglio</a>
			</s:if>
		</s:if>
	</div>

	<s:form id="backToHomeForm" action="show-main-menu.do" method="POST" theme="simple"/>
	<s:form id="backToRicercaListeAggiornamentoPosizioniDebitorieForm" action="entry-ricerca-listeaggiornamentoposizionidebitorie.do" method="POST" theme="simple"/>
	<s:form id="dettaglioPosizioneDebitoriaDaAggiornareForm" action="dettaglio-posizionedebitoriadaaggiornare.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="idMessaggio"/>
		<s:hidden name="idPosizioneDebitoriaDaAggiornare"/>
		<s:hidden name="idCodVersamento"/>
		<s:hidden name="numeroElementi"/>
		<s:hidden name="addendoElemento" value="0"/>
	</s:form>
	<s:form id="modificaPosizioneDebitoriaDaAggiornareForm" action="modifica-posizionedebitoriadaaggiornare.do" method="POST" theme="simple">
		<s:hidden name="operativita"/>
		<s:hidden name="origineHomePerInserimento"/>
		<s:hidden name="idFlusso"/>
		<s:hidden name="idMessaggio"/>
		<s:hidden name="idPosizioneDebitoriaDaAggiornare"/>
		<s:hidden name="idCodVersamento"/>
		<s:hidden name="numeroElementi"/>
		<s:hidden name="addendoElemento" value="0"/>
	</s:form>
	<s:form id="eliminaPosizioneDebitoriaDaAggiornareForm" action="elimina-posizionedebitoriadaaggiornare.do" method="POST" theme="simple">
		<s:hidden name="init" value="true"/>
		<s:hidden name="idMessaggio"/>
		<s:hidden name="idPosizioneDebitoriaDaAggiornare"/>
	</s:form>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
	<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>

	<script src="js/epaypaweb/visualizza-flusso-posizionidebitoriedaaggiornare.js" type="text/javascript"></script>

</body>
</html>
