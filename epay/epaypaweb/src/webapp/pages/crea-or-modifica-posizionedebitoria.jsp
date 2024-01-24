<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ taglib uri="/struts-tags" prefix="s"%>

<%@ include file="../include/head.jsp"%>
<style>
    .trasp {
        border-top: none!important;
    }
</style>
<body>

	<%@ include file="../include/modal-confirm.jsp"%>
	<%@ include file="../include/modal-alert.jsp"%>

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
				<li><a id="backToVisualizzaListadicaricoAction1"><s:property value="operativita.des"/> lista di carico</a><span class="divider"></span></li>
				<li class="active"><s:property value="operativita.des"/> posizione debitoria</li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">
				<s:form id="formId" action="salva-posizionedebitoria.do" method="POST" cssClass="form-horizontal" theme="bootstrap">
					<div id="alert-div"></div>

					<%@ include file="../include/validation-message.jsp"%>

					<h3><s:property value="operativita.des"/> posizione debitoria</h3>
					<s:hidden id="origineHomePerInserimento" name="origineHomePerInserimento"/>
					<s:hidden id="operativita" name="operativita"/>
					<s:hidden name="idFlusso"/>
					<s:hidden name="addendoElemento"/>

					<div class="step-content">
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
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Lotto:</label>
									<p class="col-sm-9 form-control-static"><s:property value="idMessaggio"/>
									<s:hidden name="idMessaggio"/>
								</div>
				
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Codice versamento:</label>
									<p class="col-sm-3 form-control-static">
										<s:iterator value="sessionContext.codiciVersamento" var="item">
											<s:if test="#item.id == idCodVersamento">
												<s:property value="#item.cod"/> - <s:property value="#item.des"/>
											</s:if>
										</s:iterator>
									</p>
									<s:hidden name="idCodVersamento"/>
									<s:if test="multibeneficiario == 'SI'">
										<label class="col-sm-3 control-label">Multibeneficiario:</label>
										<p class="col-sm-3 form-control-static">
											<s:property value="multibeneficiario"/>
										</p>
									</s:if>
									<s:hidden name="multibeneficiario"/>
								</div>
	
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Data inizio validita':</label>
									<p class="col-sm-9 form-control-static">
										<s:text name="format.date">
											<s:param name="value" value="dataInizioValidita"/>
										</s:text>
									</p>
									<s:hidden name="dataInizioValidita"/>
								</div>
	
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Data fine validita':</label>
									<p class="col-sm-9 form-control-static">
										<s:text name="format.date">
											<s:param name="value" value="dataFineValidita"/>
										</s:text>
									</p>
									<s:hidden name="dataFineValidita"/>
								</div>
	
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Numero posizioni debitorie:</label>
									<p class="col-sm-9 form-control-static"><s:property value="numeroElementi"/></p>
									<s:hidden name="numeroElementi"/>
								</div>
	
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Totale importo posizioni debitorie:</label>
									<p class="col-sm-9 form-control-static">
										<s:text name="format.money">
											<s:param name="value" value="importoTotalePosizioniDebitorie"/>
										</s:text>
									</p>
									<s:hidden name="importoTotalePosizioniDebitorie"/>
								</div>
							</div>
						</div>
					</div>
					
					<br/>
	
					<div class="step-content">
						<div class="step-pane active">
							<h4>Posizione debitoria</h4>
							<div class="container-fluid">
								<div class="row-fluid">
									<p><i>I campi contrassegnati con * sono obbligatori.</i></p>
								</div>

								<!-- DEV/CSI_PAG-2411 - BEGIN -->
								<div class="row-fluid">
									<label class="col-sm-3 control-label">Posizione debitoria esterna:</label>
									<p class="col-sm-9 form-control-static"><s:property value="idPosizioneDebitoriaEsterna"/>
									<s:hidden name="idPosizioneDebitoriaEsterna"/>
								</div>
								<!-- DEV/CSI_PAG-2411 - END -->

								<div class="row-fluid">
									<table class="table tab_left dataTable" id="tabellaComponentiImporto">
										<tbody>
										<tr role="row">
											<td class="trasp"><s:textfield label="Importo totale da pagare*" id="strCompimpImportoTotale" name="strCompimpImportoTotale" maxlength="12" cssClass="span6"/></td>
											<s:hidden name="compimpImportoTotaleOriginal"/>
											<s:if test="multibeneficiario == 'SI'">
												<td class="trasp"><s:textfield label="Importo Principale" id="strCompimpImportoTotalePrincipale" name="strCompimpImportoTotalePrincipale" maxlength="12" cssClass="span6"/></td>
												<td class="trasp"><s:textfield label="Importo Secondario" id="strImportoSecondario" name="strImportoSecondario" maxlength="12" cssClass="span6" readonly="true" /></td>
											</s:if>
										</tr>
										</tbody>
									</table>
								</div>

								<div class="row-fluid">
									<s:textfield name="desCausaleVersamento" maxlength="100"
										label="Descrizione causale versamento*" labelCssClass="col-sm-3" cssClass="span10" />
								</div>
	
								<div class="row-fluid">
									<s:textfield name="strDataScadenza" 
										label="Data scadenza" labelCssClass="col-sm-3" cssClass="span2 datepicker"/>
								</div>
	
								<div class="row-fluid">
									<s:textfield name="strAnnoRiferimento" maxlength="4"
										label="Anno riferimento" labelCssClass="col-sm-3" cssClass="span2"/>
								</div>

								<div class="row-fluid">
									<s:textfield name="desRata" maxlength="16"
										label="Descrizione rata" labelCssClass="col-sm-3" cssClass="span2" />
								</div>
	
								<div class="row-fluid">
									<s:textarea name="notePerPagatore" rows="3" maxlength="1000"
										label="Note per pagatore" labelCssClass="col-sm-3" cssClass="span10" />
								</div>
							</div>
							
							<br/>
	
							<div class="step-content">
								<div class="step-pane active">
									<h5>Componenti importo principale</h5>
									<table class="table table-hover tab_left dataTable" id="tabellaComponentiImporto">
										<thead>
											<tr>
												<th>Importo</th>
												<th>Causale</th>
												<th>Anno accertamento</th>
												<th>Numero accertamento</th>
												<th class="tab_Right span1">&nbsp;</th>
											</tr>
										</thead>
										<tbody>
											<tr role="row">
												<s:hidden id="compimpId0" name="compimpId0"/>
												<td><s:textfield id="strCompimpImporto0" name="strCompimpImporto0" maxlength="12" cssClass="span6"/></td>
												<td><s:textfield id="compimpCausale0" name="compimpCausale0" maxlength="80" cssClass="span10" /></td>												
												<td><s:textfield id="strCompimpAnnoAccert0" name="strCompimpAnnoAccert0" maxlength="4" cssClass="span6"/></td>
												<td><s:textfield id="strCompimpNumeroAccert0" name="strCompimpNumeroAccert0" maxlength="35" cssClass="span10" /></td>
												<td><input type="button" id="eliminaComponenteImporto0" class="btn" value="elimina"/></td>
											</tr>
											<tr role="row">
												<s:hidden id="compimpId1" name="compimpId1"/>
												<td><s:textfield id="strCompimpImporto1" name="strCompimpImporto1" maxlength="12" cssClass="span6" /></td>
												<td><s:textfield id="compimpCausale1" name="compimpCausale1" maxlength="80" cssClass="span10" /></td>												
												<td><s:textfield id="strCompimpAnnoAccert1" name="strCompimpAnnoAccert1" maxlength="4" cssClass="span6"/></td>
												<td><s:textfield id="strCompimpNumeroAccert1" name="strCompimpNumeroAccert1" maxlength="35" cssClass="span10" /></td>												
												<td><input type="button" id="eliminaComponenteImporto1" class="btn" value="elimina"/></td>
											</tr>
											<tr role="row">
												<s:hidden id="compimpId2" name="compimpId2"/>
												<td><s:textfield id="strCompimpImporto2" name="strCompimpImporto2" maxlength="12" cssClass="span6" /></td>
												<td><s:textfield id="compimpCausale2" name="compimpCausale2" maxlength="80" cssClass="span10" /></td>
												<td><s:textfield id="strCompimpAnnoAccert2" name="strCompimpAnnoAccert2" maxlength="4" cssClass="span6"/></td>
												<td><s:textfield id="strCompimpNumeroAccert2" name="strCompimpNumeroAccert2" maxlength="35" cssClass="span10" /></td>
												<td><input type="button" id="eliminaComponenteImporto2" class="btn" value="elimina"/></td>
											</tr>
											<tr role="row">
												<s:hidden id="compimpId3" name="compimpId3"/>
												<td><s:textfield id="strCompimpImporto3" name="strCompimpImporto3" maxlength="12" cssClass="span6" /></td>
												<td><s:textfield id="compimpCausale3" name="compimpCausale3" maxlength="80" cssClass="span10" /></td>
												<td><s:textfield id="strCompimpAnnoAccert3" name="strCompimpAnnoAccert3" maxlength="4" cssClass="span6"/></td>
												<td><s:textfield id="strCompimpNumeroAccert3" name="strCompimpNumeroAccert3" maxlength="35" cssClass="span10" /></td>
												<td><input type="button" id="eliminaComponenteImporto3" class="btn" value="elimina"/></td>
											</tr>
											<tr role="row">
												<s:hidden id="compimpId4" name="compimpId4"/>
												<td><s:textfield id="strCompimpImporto4" name="strCompimpImporto4" maxlength="12" cssClass="span6" /></td>
												<td><s:textfield id="compimpCausale4" name="compimpCausale4" maxlength="80" cssClass="span10" /></td>
												<td><s:textfield id="strCompimpAnnoAccert4" name="strCompimpAnnoAccert4" maxlength="4" cssClass="span6"/></td>
												<td><s:textfield id="strCompimpNumeroAccert4" name="strCompimpNumeroAccert4" maxlength="35" cssClass="span10" /></td>
												<td><input type="button" id="eliminaComponenteImporto4" class="btn" value="elimina"/></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<s:if test="multibeneficiario == 'SI'">
							<div class="step-content">
								<div class="step-pane active">
									<h5>Componenti importo secondario</h5>
									<table class="table table-hover tab_left dataTable" id="tabellaComponentiImportoSecondario">
										<thead>
										<tr>
											<th>Importo*</th>
											<th>Causale*</th>
											<th class="tab_Right span1">&nbsp;</th>
										</tr>
										</thead>
										<tbody>
										<tr role="row">
											<s:hidden id="compimpId0Secondario" name="compimpId0Secondario"/>
											<td><s:textfield id="strCompimpImporto0Secondario" name="strCompimpImporto0Secondario" maxlength="12" cssClass="span6"/></td>
											<td><s:textfield id="compimpCausale0Secondario" name="compimpCausale0Secondario" maxlength="80" cssClass="span10" /></td>
											<td><input type="button" id="eliminaComponenteImportoSecondario0" class="btn" value="elimina"/></td>
										</tr>
										</tbody>
									</table>
								</div>
							</div>
							</s:if>
							<div class="step-content">
								<div class="step-pane active">
									<h5>Riferimenti pagamento - Dati aggiuntivi da utilizzare secondo le indicazioni del manuale</h5>
									<table class="table table-hover tab_left dataTable" id="tabellaRiferimentoPagamento">
										<thead>
											<tr>
												<th>Nome</th>
												<th>Valore</th>
												<th class="tab_Right span1">&nbsp;</th>
											</tr>
										</thead>
										<tbody>
											<tr role="row">
												<s:hidden id="rifpagId0" name="rifpagId0"/>
												<td><s:textfield id="rifpagNome0" name="rifpagNome0" maxlength="70" cssClass="span12" /></td>
												<td><s:textfield id="rifpagValore0" name="rifpagValore0" maxlength="70" cssClass="span12" /></td>
												<td><input type="button" id="eliminaRiferimentoPagamento0" class="btn" value="elimina"/></td>
											</tr>											
											<tr role="row">
												<s:hidden id="rifpagId1" name="rifpagId1"/>
												<td><s:textfield id="rifpagNome1" name="rifpagNome1" maxlength="70" cssClass="span12" /></td>
												<td><s:textfield id="rifpagValore1" name="rifpagValore1" maxlength="70" cssClass="span12" /></td>
												<td><input type="button" id="eliminaRiferimentoPagamento1" class="btn" value="elimina"/></td>
											</tr>											
											<tr role="row">
												<s:hidden id="rifpagId2" name="rifpagId2"/>
												<td><s:textfield id="rifpagNome2" name="rifpagNome2" maxlength="70" cssClass="span12" /></td>
												<td><s:textfield id="rifpagValore2" name="rifpagValore2" maxlength="70" cssClass="span12" /></td>
												<td><input type="button" id="eliminaRiferimentoPagamento2" class="btn" value="elimina"/></td>
											</tr>											
											<tr role="row">
												<s:hidden id="rifpagId3" name="rifpagId3"/>
												<td><s:textfield id="rifpagNome3" name="rifpagNome3" maxlength="70" cssClass="span12" /></td>
												<td><s:textfield id="rifpagValore3" name="rifpagValore3" maxlength="70" cssClass="span12" /></td>
												<td><input type="button" id="eliminaRiferimentoPagamento3" class="btn" value="elimina"/></td>
											</tr>											
											<tr role="row">
												<s:hidden id="rifpagId4" name="rifpagId4"/>
												<td><s:textfield id="rifpagNome4" name="rifpagNome4" maxlength="70" cssClass="span12" /></td>
												<td><s:textfield id="rifpagValore4" name="rifpagValore4" maxlength="70" cssClass="span12" /></td>
												<td><input type="button" id="eliminaRiferimentoPagamento4" class="btn" value="elimina"/></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							
							<br/>
							<div class="step-pane active">
								<h5>Soggetto debitore</h5>
						
								<div class="container-fluid">
									<div class="row-fluid" style="padding-bottom:13px">
										<s:radio name="soggettoIdTipo" label="Tipo soggetto"
											labelposition="inline" labelCssClass="col-sm-3"
											elementCssClass="col-sm-3" list="soggettoIdTipoList" listKey="cod"
											listValue="option"/>
									</div>

									<div id="divPersonaFisica" class="hide">
										<div class="row-fluid">
											<s:textfield id="soggettoIdUnivocoFiscalePersonaFisica" name="soggettoIdUnivocoFiscalePersonaFisica" maxlength="35"
												label="Codice fiscale*" labelCssClass="col-sm-3" cssClass="span3" onblur="controllaCF()"/>
										</div>
	
										<div class="row-fluid">
											<s:textfield name="soggettoCognome" maxlength="70"
												label="Cognome*" labelCssClass="col-sm-3" cssClass="span3" />
												
										</div>
	
										<div class="row-fluid">
											<s:textfield name="soggettoNome" maxlength="70"
												label="Nome*" labelCssClass="col-sm-3" cssClass="span3" />
										</div>
									</div>
									<div id="divPersonaGiuridica" class="hide">
										<div class="row-fluid">
											<s:textfield id="soggettoIdUnivocoFiscalePersonaGiuridica" name="soggettoIdUnivocoFiscalePersonaGiuridica" maxlength="35"
												label="Codice fiscale/Partita Iva*" labelCssClass="col-sm-3" cssClass="span3" onblur="controllaPIVA()" />
										</div>
	
										<div class="row-fluid">
											<s:textfield name="soggettoRagioneSociale" maxlength="70"
												label="Ragione sociale*" labelCssClass="col-sm-3" cssClass="span3" />
										</div>
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoIndirizzo" maxlength="70"
											label="Indirizzo" labelCssClass="col-sm-3" cssClass="span4" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoCivico" maxlength="16"
											label="Civico" labelCssClass="col-sm-3" cssClass="span1" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoCap" maxlength="16"
											label="CAP" labelCssClass="col-sm-3" cssClass="span2" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoLocalita" maxlength="35"
											label="Localita'" labelCssClass="col-sm-3" cssClass="span2" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoProvincia" maxlength="35"
											label="Provincia" labelCssClass="col-sm-3" cssClass="span2" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoNazione" maxlength="2"
											label="Nazione" labelCssClass="col-sm-3" cssClass="span1" />
									</div>

									<div class="row-fluid">
										<s:textfield name="soggettoEmail" maxlength="256"
											label="E-mail" labelCssClass="col-sm-3" cssClass="span4" />
									</div>
								</div>
							</div>
	
							<div class="row-fluid">
								<div class="margin-medium">
									<a id="ripristinaAction" class="btn">Ripristina</a>
								</div>
							</div>
						</div>
					</div>

					<div class="margin-medium">
						<a id="backToHomeAction2" class="btn">HOME</a>
						<s:if test="!origineHomePerInserimento">
							/ <a id="backToRicercaListadicaricoAction2" class="btn">RICERCA FLUSSI</a>
						</s:if>
						<a id="backToVisualizzaListadicaricoAction2" class="btn"><span style="text-transform:uppercase"><s:property value="operativita.des"/></span> LISTA DI CARICO</a>
						<s:submit id="salvaAction" value="Salva" cssClass="btn btn-primary pull-right"/>
					</div>
				</s:form>
			</div>
		</div>
	</div>

	<s:form id="backToHomeForm" action="show-main-menu.do" method="POST" theme="simple"/>
	<s:form id="backToRicercaListadicaricoForm" action="entry-ricerca-listedicarico.do" method="POST" theme="simple"/>
	<s:form id="backToVisualizzaListadicaricoForm" action="entry-visualizza-listadicarico.do" method="POST" theme="simple"/>
	<s:form id="ripristinaForm" action="ripristina_posizionedebitoria.do" method="POST" theme="simple">
		<s:hidden name="origineHomePerInserimento"/>
		<s:hidden name="operativita"/>
		<s:hidden name="idPosizioneDebitoria"/>
	</s:form>

	<%@ include file="../include/portal-footer.html"%>
	<%@ include file="../include/javascript.html"%>

	<s:hidden name="esitoElaborazione" value="%{esitoElaborazione}"/>
	<s:hidden name="messaggioEsitoElaborazione" value="%{messaggioEsitoElaborazione}"/>

	<script src="js/epaypaweb/crea-or-modifica-posizionedebitoria.js" type="text/javascript"></script>
	<script>

	function controllaPIVA()
	{
		var error = '';
		var pi = document.getElementById("soggettoIdUnivocoFiscalePersonaGiuridica");
		pi.value = pi.value.toUpperCase();
		if( ! /^[0-9]{11}$/.test(pi.value) )
			error = 'La partita IVA deve contenere 11 cifre.';
		else {
			var s = 0;
			for( i = 0; i <= 9; i += 2 )
				s += pi.value.charCodeAt(i) - '0'.charCodeAt(0);
			for(var i = 1; i <= 9; i += 2 ){
				var c = 2*( pi.value.charCodeAt(i) - '0'.charCodeAt(0) );
				if( c > 9 )  c = c - 9;
				s += c;
			}
			var atteso = ( 10 - s%10 )%10;
			if( atteso != pi.value.charCodeAt(10) - '0'.charCodeAt(0) )
				error = 'La partita IVA non ? valida: il codice di controllo non corrisponde.';
			else error ='';
					}
		 if (pi.value =='ANONIMO')
			 error='';
		 if (error!='')
			 alertAction(error);
	}

		function controllaCF()
		{
			 var error = '';
			 var cf = document.getElementById("soggettoIdUnivocoFiscalePersonaFisica");
			 error = validaCodiceFiscale(cf.value);
			 if (error!='')
				 alertAction(error);
		}


		function validaCodiceFiscale(cf)
		      {
		          var validi, i, s, set1, set2, setpari, setdisp;
		          if( cf == '' )  return '';
		          cf = cf.toUpperCase();
		          if (cf == 'ANONIMO') return '';
		          if( cf.length != 16 )
		              return 'Il codice fiscale deve essere composto da 16 caratteri.';
		          validi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		          for( i = 0; i < 16; i++ ){
		              if( validi.indexOf( cf.charAt(i) ) == -1 )
		                  return 'Il codice fiscale contiene caratteri non validi. ';
		          }
		          set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		          set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
		          setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		          setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
		          s = 0;
		          for( i = 1; i <= 13; i += 2 )
		              s += setpari.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
		          for( i = 0; i <= 14; i += 2 )
		              s += setdisp.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
		          if( s%26 != cf.charCodeAt(15)-'A'.charCodeAt(0) )
		              return 'Il codice fiscale non ? valido: il codice di controllo non corrisponde. ';
		          return '';
		      }
		
	</script>

</body>
</html>
