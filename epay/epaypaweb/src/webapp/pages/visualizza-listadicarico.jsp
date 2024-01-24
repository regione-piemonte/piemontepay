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
					<li><a id="backToRicercaListadicaricoAction1">Ricerca liste di carico</a><span class="divider"></span></li>
				</s:if>
				<li class="active"><s:property value="operativita.des"/> lista di carico</li>
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
				<s:form id="formId" action="nuova-posizionedebitoria.do" method="POST" cssClass="form-horizontal" theme="bootstrap">
					<div id="alert-div"></div>

					<%@ include file="../include/validation-message.jsp"%>
	
					<h3><s:property value="operativita.des"/> lista di carico</h3>
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
												listValue="codDes" headerKey="" headerValue="Scegli"/>
										</div>

										<div class="row-fluid">
											<s:textfield id="testata_strDataInizioValidita" name="strDataInizioValidita" 
												label="Data inizio validità*" labelCssClass="col-sm-3" cssClass="span2 datepicker"/>
										</div>
					
										<div class="row-fluid">
											<s:textfield id="testata_strDataFineValidita" name="strDataFineValidita"
												label="Data fine validità*" labelCssClass="col-sm-3" cssClass="span2 datepicker"/>
										</div>
					
										<div class="row-fluid">
											<s:textfield name="numeroElementi" disabled="true"
												label="Numero posizioni debitorie" labelCssClass="col-sm-3" cssClass="span2"/>
											<s:hidden name="numeroElementi"/>
										</div>
					
										<div class="row-fluid">
											<s:textfield name="importoTotalePosizioniDebitorie" disabled="true"
												label="Totale importo posizioni debitorie" labelCssClass="col-sm-3" cssClass="span2"/>
											<s:hidden name="importoTotalePosizioniDebitorie"/>
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
												<s:submit value="Nuova posizione debitoria" cssClass="btn btn-primary"/>
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
										<p class="col-sm-8 form-control-static">
											<s:property value="multibeneficiario"/>
										</p>
									</div>
					
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Numero posizioni debitorie:</label>
										<p class="col-sm-8 form-control-static"><s:property value="flussoSelezionato.numeroElementi"/></p>
									</div>
					
									<div class="row-fluid">
										<label class="col-sm-4 control-label">Totale importo posizioni debitorie:</label>
										<p class="col-sm-8 form-control-static">
											<s:if test="flussoSelezionato.importoTotale != null">
												<s:text name="format.money">
													<s:param name="value" value="flussoSelezionato.importoTotale"/>
												</s:text>
											</s:if>
										</p>
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
								<th>Id univoco versamento (IUV)</th>
								<th>Importo totale da pagare</th>
								<th>Descrizione causale versamento</th>
								<th>Data scadenza</th>
								<th>Codice fiscale/Partita Iva (Soggetto debitore)</th>
								<th class="tab_Right span1">&nbsp;</th>
							</tr>
						</thead>
						<tbody/>
					</table>
				</s:if>

				<div class="margin-medium">
					<a id="backToHomeAction2" class="btn">HOME</a>
					<s:if test="!origineHomePerInserimento">
						/ <a id="backToRicercaListadicaricoAction2" class="btn">RICERCA FLUSSI</a>
					</s:if>
					<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI || operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@MODIFICA">
						<s:if test="idFlusso != null">
							<s:a action="pubblica-listedicarico" cssClass="btn btn-primary pull-right">Pubblica</s:a>
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
		<button class="btn btn-sm dropdown-toggle" data-toggle="dropdown">
			Azioni <span class="caret"></span>
		</button>
		<ul class="dropdown-menu pull-right">
			<s:if test="isUseCaseEnabled('DET_LDC')">
				<li><a id="dettaglioPosizioneDebitoriaAction" data-form-id="dettaglioPosizioneDebitoriaForm">visualizza posizione</a></li>
			</s:if>
			<s:if test="operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI || operativita == @it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@MODIFICA">
				<s:if test="isUseCaseEnabled('INS_LDC')">
					<li><a id="modificaPosizioneDebitoriaAction" data-form-id="modificaPosizioneDebitoriaForm">modifica posizione</a></li>
				</s:if>
				<s:if test="isUseCaseEnabled('ELM_LDC')">
					<li><a id="eliminaPosizioneDebitoriaAction" data-form-id="eliminaPosizioneDebitoriaForm">elimina posizione</a></li>
				</s:if>
			</s:if>
			<s:if test="isUseCaseEnabled('PRN_AP')">
				<li><a id="stampaAvvisoPagamentoAction" data-form-id="stampaAvvisoPagamentoForm">stampa avviso pagamento</a></li>
			</s:if>
		</ul>
	</div>

	<s:form id="backToHomeForm" action="show-main-menu.do" method="POST" theme="simple"/>
	<s:form id="backToRicercaListadicaricoForm" action="entry-ricerca-listedicarico.do" method="POST" theme="simple"/>
	<s:form id="dettaglioPosizioneDebitoriaForm" action="dettaglio-posizionedebitoria.do" method="POST" theme="simple">
		<s:hidden name="idFlusso"/>
		<s:hidden name="idMessaggio"/>
		<s:hidden name="idPosizioneDebitoria"/>
		<s:hidden name="idCodVersamento"/>
		<s:hidden name="dataInizioValidita"/>
		<s:hidden name="dataFineValidita"/>
		<s:hidden name="numeroElementi"/>
		<s:hidden name="addendoElemento" value="0"/>
		<s:hidden name="importoTotalePosizioniDebitorie"/>
	</s:form>
	<s:form id="modificaPosizioneDebitoriaForm" action="modifica-posizionedebitoria.do" method="POST" theme="simple">
		<s:hidden name="operativita"/>
		<s:hidden name="origineHomePerInserimento"/>
		<s:hidden name="idFlusso"/>
		<s:hidden name="idMessaggio"/>
		<s:hidden name="idPosizioneDebitoria"/>
		<s:hidden name="idCodVersamento"/>
		<s:hidden name="strDataInizioValidita"/>
		<s:hidden name="strDataFineValidita"/>
		<s:hidden name="numeroElementi"/>
		<s:hidden name="addendoElemento" value="0"/>
		<s:hidden name="importoTotalePosizioniDebitorie"/>
	</s:form>
	<s:form id="eliminaPosizioneDebitoriaForm" action="elimina-posizionedebitoria.do" method="POST" theme="simple">
		<s:hidden name="init" value="true"/>
		<s:hidden name="idMessaggio"/>
		<s:hidden name="idPosizioneDebitoria"/>
	</s:form>
	<s:form id="stampaAvvisoPagamentoForm" action="stampa-avviso-pagamento.do" method="POST" theme="simple">
		<s:hidden name="iuv"/>
		<s:hidden name="idPosizioneDebitoria"/>
		<s:hidden name="pleaseWaitTokenValue"/>
	</s:form>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
	<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>

	<script src="js/epaypaweb/visualizza-listadicarico.js" type="text/javascript"></script>

</body>
</html>