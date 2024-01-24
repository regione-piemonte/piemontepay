<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ include file="../include/head.jsp"%>

<body>
	<%@ include file="../include/portal-header.jsp"%>
	<div class="row-fluid">
		<div class="span12">
			<ul class="breadcrumb">
				<li><a href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a> <span class="divider"></span>
				</li>
				<li class="active"><spring:message code="ente.modifica.modificaente" /></li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="contentPage">

				<form:form id="formSaveEnti" method="post"
					commandName="modifica_ente" enctype="multipart/form-data"
					action="${context}/enti/modifica" class="form-horizontal"
					track-changes="true">
					<h3><spring:message code="ente.modifica.modificaente" /></h3>
					<%@ include file="./forms/form_modifica_ente.jsp"%>
				</form:form>

				<br>

				<div class="row-fluid">
					<!-- <button class="btn btn-secondary btn-action" id="form-cancel"><spring:message code="text.home" /></button>-->
					<a id="buttonHome" class="btn btn-secondary btn-action" href="${context}${webappConfig.epaypaHome}${webappConfig.requestParam}<sec:authentication property="principal.ente.codiceFiscale" />"><spring:message code="home" /></a>
					<button class="btn btn-primary btn-action pull-right"
						id="form-save-submit"><spring:message code="button.save" /></button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../include/portal-footer.jsp"%>

	<script type="text/javascript">
		$(function(){

			function clearLogo() {
				$("#formSaveEnti").setDirty();
				$("#newLogo").val("");
				$("#logoDetailsContainer").hide();
				$("#noLogoDetailsContainer").show();
				$("#clearLogoButton").hide();
				document.querySelector('#logoDetailsThumbnail').src = '';
			}
			
			$("#newLogoButton").on('click',function(e){
				$("#newLogo").click();
			});

			/*
			$("#clearLogoButton").on('click',function(e){
				clearLogo();
				$("#cancellaLogo").val("true");
			});
			*/

			$("#newLogo").on('change',function(){
				var fileList = $("#newLogo")[0].files;
				if (fileList.length > 0) {
					var f = fileList[0];
					var size = f.size;
					var validSize = (size <= 2048000);
					var fnamelc = f.name.toLowerCase();
					var validExt = fnamelc.endsWith(".jpg") || fnamelc.endsWith(".jpeg")
 						|| fnamelc.endsWith(".png") || fnamelc.endsWith(".bmp") || fnamelc.endsWith(".gif");
					
					if (!validSize || !validExt) {
						$("#newLogo").val("");
						
						if (!fnamelc) {
							alert("Il file selezionato e' troppo grosso.");
						} else if (!validExt) {
							alert("Il file selezionato non e' valido. Devi selezionare un file di tipo immagine");
						}
						
					} else {
						$("#logoDetailsContainerName").html(f.name);
						$("#logoDetailsContainerSize").html(Math.round(size / 1024) + " kB");
						
						$("#logoDetailsContainer").show();
						$("#noLogoDetailsContainer").hide();
						
						$("#clearLogoButton").show();
						$("#cancellaLogo").val("false");

						var preview = document.querySelector('#logoDetailsThumbnail');
						var reader  = new FileReader();
						
						reader.onloadend = function () {
						    preview.src = reader.result;
						}
						
						if (f) {
						    reader.readAsDataURL(f);
						} else {
						    preview.src = "";
						}
					}
					
				} else {
					clearLogo();
				}
			});
			
			$("#form-save-submit").on('click',function(){
				$("#formSaveEnti").submit();
			});
			
			$("#form-cancel").on('click',function(){
				location = baseLocation + "home" + "${webappConfig.requestParam}" + '<sec:authentication property="principal.ente.codiceFiscale" />';
			});
			
			$("#flagRiconciliazione").on('change', function(){
				if ($("#flagRiconciliazione").prop("checked")) {
					$("#conditionalControls0").show();
				} else {
					$("#conditionalControls0").hide();
				}
			});
			
			$("#flagRicezioneFlussoBaseRendicontazione").on('change', function(){
				if ($("#flagRicezioneFlussoBaseRendicontazione").prop("checked")) {
					$("#conditionalControls1").show();
				} else {
					$("#conditionalControls1").hide();
				}
			});

			$("#flagQualsiasiCodiceVersamento").on('change', function(){
				if (! $("#flagQualsiasiCodiceVersamento").prop("checked")) {
					$("#btnGestioneFlussiSpecifici").removeClass('disabled');
				} else {
					$("#btnGestioneFlussiSpecifici").addClass('disabled');
				}
			});

			$("#codicePeriodicitaSchedulazioneRiconciliazione").on('change', function(){
				aggiornaCampoGiornoSchedulazione(false);
			});

			// GESTIONE CODICI VERSAMENTO
			$("#codiciVersamentoNonSelezionati, #codiciVersamentoSelezionati").on("change", function(a) {
				modificaEnteSyncCodiciVersamentoValue();
			});
			
			$("#buttonAddCodiceVersamento").on('click',function(){
				$("#formSaveEnti").setDirty();
				$("#codiciVersamentoNonSelezionati option:selected").remove().appendTo("#codiciVersamentoSelezionati");
				modificaEnteSyncCodiciVersamentoValue(true);
			});

			$("#buttonAddCodiciVersamento").on('click',function(){
				$("#formSaveEnti").setDirty();
				$("#codiciVersamentoNonSelezionati option").remove().appendTo("#codiciVersamentoSelezionati");
				modificaEnteSyncCodiciVersamentoValue(true);
			});

			$("#buttonSubCodiceVersamento").on('click',function(){
				$("#formSaveEnti").setDirty();
				$("#codiciVersamentoSelezionati option:selected").remove().appendTo("#codiciVersamentoNonSelezionati");
				modificaEnteSyncCodiciVersamentoValue(true);
			});

			$("#buttonSubCodiciVersamento").on('click',function(){
				$("#formSaveEnti").setDirty();
				$("#codiciVersamentoSelezionati option").remove().appendTo("#codiciVersamentoNonSelezionati");
				modificaEnteSyncCodiciVersamentoValue(true);
			});
			
			
			$("#flagAdesioneCittaFacile").on('change', function(){
                if ($("#flagAdesioneCittaFacile").prop("checked")) {
                    $("#templateEmailIdClass").show();
                    $("#urlDominioClass").show();
                    $("#codiceIpaClass").show();
                } else {
                    $("#templateEmailId").val("");
                    $("#urlDominio").val("");
                    $("#codiceIpa").val("");
                    
                    $("#templateEmailIdClass").hide();
                    $("#urlDominioClass").hide();
                    $("#codiceIpaClass").hide();
                }
            });

			function modificaEnteSortMultiSelect(selector) {
				var sel = $(selector);
				var selected = sel.val(); // cache selected value, before reordering
				var opts_list = sel.find('option');
				opts_list.sort(function(a, b) { return $(a).text() > $(b).text() ? 1 : -1; });
				sel.html('').append(opts_list);
				sel.val(selected);
			}

			function modificaEnteSyncCodiciVersamentoValue(somethingMoved) {
				if (somethingMoved) {
					$.each($("#codiciVersamentoSelezionati option"), function(index, option) {
						$("#idCodiciVersamentoSelezionati" + $(option).prop('value')).val("1");
					});
					$.each($("#codiciVersamentoNonSelezionati option"), function(index, option) {
						$("#idCodiciVersamentoSelezionati" + $(option).prop('value')).val("0");
					});

					if ($("#codiciVersamentoNonSelezionati option").size()) {
	 					$("#buttonAddCodiciVersamento").removeClass("disabled");
					} else {
	 					$("#buttonAddCodiciVersamento").addClass("disabled");
					}
	
					if ($("#codiciVersamentoSelezionati option").size()) {
	 					$("#buttonSubCodiciVersamento").removeClass("disabled");
					} else {
	 					$("#buttonSubCodiciVersamento").addClass("disabled");
					}

					modificaEnteSortMultiSelect('#codiciVersamentoSelezionati');
					modificaEnteSortMultiSelect('#codiciVersamentoNonSelezionati');
	
					$("#codiciVersamentoSelezionati option:selected").prop("selected", false);
					$("#codiciVersamentoNonSelezionati option:selected").prop("selected", false);
				}

				if ($("#codiciVersamentoNonSelezionati option:selected").size()) {
					$("#buttonAddCodiceVersamento").removeClass("disabled");
				} else {
					$("#buttonAddCodiceVersamento").addClass("disabled");
				}
				
				if ($("#codiciVersamentoSelezionati option:selected").size()) {
					$("#buttonSubCodiceVersamento").removeClass("disabled");
				} else {
					$("#buttonSubCodiceVersamento").addClass("disabled");
				}
			}

			function aggiornaCampoGiornoSchedulazione(init) {
				var val = $("#codicePeriodicitaSchedulazioneRiconciliazione").val();
				var container = $("#containerGiornoSchedulazione");
				var label = $("#labelGiornoSchedulazione");
				var input = $("#giornoSchedulazione");
				var dpinitialized = input.attr("dpinitialized");
				var hide = false;
				var showDatePicker = false;
				var showInput = false;
				var min = 1;
				var max = null;

				if (!val) {
					hide = true;
				} else if (val == "GIO") {
					hide = true;
				} else if (val == "SET") {
					showInput = true;
					max = 7;
				} else if (val == "MEN") {
					showInput = true;
					max = 30;
				} else if (val == "BIM") {
					showInput = true;
					max = 30;
				} else if (val == "TRI") {
					showInput = true;
					max = 30;
				} else if (val == "QUA") {
					showInput = true;
					max = 30;
				} else if (val == "SEM") {
					showInput = true;
					max = 30;
				} else if (val == "ANN") {
					showDatePicker = true;
				} else if (val == "SGF") {
					hide = true;
				} else if (val == "NOS") {
					hide = true;
				} else {
					container.hide();
					console.error("codicePeriodicitaSchedulazioneRiconciliazione non attesa");
					return;
				}

				if (hide) {
					if (dpinitialized) {
						input.datepicker( "remove" );
						input.attr("dpinitialized", false);
					}

					container.hide();
					input.prop("type", "text");
					input.prop("min", "");
					input.prop("max", "");
					if (!init) input.val("");

				} else if (showInput) {
					if (dpinitialized) {
						input.datepicker( "remove" );
						input.attr("dpinitialized", false);
					}

					label.html("Scegli il giorno (" + min + " - " + max + ")");
					input.prop("type", "number");
					input.prop("min", min);
					input.prop("max", max);
					if (!init) input.val("");
					container.show();
					
				} else {
					label.html("Scegli la data");
					input.prop("type", "text");
					input.prop("min", "");
					input.prop("max", "");
					if (!init) input.val("");

					input.datepicker({
					    format: 'dd/mm',
					    language: 'it'
					});
					input.attr("dpinitialized", true);

					container.show();
				}

				if (!init) {
					$("#containerGiornoSchedulazioneErrors").html("");
				}
			}
			
			modificaEnteSyncCodiciVersamentoValue(true);

			aggiornaCampoGiornoSchedulazione(true);
			
			scrollToFirstError();

		});

	</script>

</body>
