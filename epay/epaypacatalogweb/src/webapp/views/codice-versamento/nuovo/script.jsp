<script>
var table_selector_ve = "#results-ve";

function eliminaCodiceVersamentoCollegato(target) {
	chiediConfermaEliminazione("Si e' sicuri di eliminare il codice versamento collegato selezionato?", function() {
		window.location.href = '${context}/codici-versamento/${modifica_codice_versamento.id}/codici-versamento-collegati/elimina/' + target + '?source=modifica';
	});	
}

function apriModaleVociEntrata() {
	var modal = $("#modalRicercaVoceEntrata");
	modal.modal("show");
}

function apriModaleInformazioneAbilitazioneGestioneEsterna() {
	var modal = $("#modalInformazioneAbilitazioneGestioneEsterna");
	modal.modal("show");
}


function selezionaVoceEntrata(codice) {
	var voce = getVoceEntrataByCodice(codice);

	$("#codice").val(voce.codice);
	$("#descrizione").val(voce.descrizione);
	$("#codiceVoceEntrata").val(voce.codice);
	$("#descrizioneVoceEntrata").val(voce.descrizione);
	$("#descrizioneCompletaVoceEntrata").val(voce.codice + " - " + voce.descrizione);
	$("#container-codice-codice-versamento").html(voce.codice);
	$("#container-codice-voce-entrata").html(voce.codice);

	var modal = $("#modalRicercaVoceEntrata");
	modal.modal("hide");

	setTimeout(function() {
		$("#descrizione").focus()
	}, 250);
}

function pulisciRicercaVE() {
	clearPage(table_selector_ve);
	$("#modalRicercaVoceEntrata input:not([readonly])").val("");
	$("#modalRicercaVoceEntrata select").val("");
	$("#modalRicercaVoceEntrata input[type=checkbox]").prop("checked", false);
	$("#row-risultati-ve").html("");
}

function eseguiRicercaVE() {
	clearPage(table_selector_ve);
	$("#row-risultati-ve").html("<div class='container-fluid'> <div class='row'> <div class='col-sm-12' style='text-align:center;'> ricerca in corso ... </div> </div> </div>");
	$("#row-risultati-ve").load(
		'${context}/codici-versamento/ajax/ricerca-voci-entrata-disponibili #rendered', 
		$("#entry-ricerca-vocientrata").serialize(),
		function() {
			
			$(table_selector_ve).DataTable({
				order: [],
				columnDefs: [ 
					{ orderable: false, targets: [$("#results th").size() - 1] } 
				]
			});

			 $("#modalRicercaVoceEntrata").find('.modal-body').css({
	              'width': 'auto',
	              'height': 'auto', 
	              'max-height': '100%'
	         });
		}
	);
}

function getVociEntrata() {
	var options = <c:out value="${modelVociEntrata_JS}" escapeXml="false"  />;
	
	return options;
};

function getVoceEntrataByCodice(codice) {
	var input = getVociEntrata();

	for (var i = 0; i < input.length; i ++) {
		if (input[i].codice == codice) {
			return input[i];
		}
	}
	
	return null;
}

$( document ).ready(function() {
// 	console.log("ciao");
	$('.datepicker').datepicker({
        format: 'dd/mm/yyyy',
        language: 'it'
    });
	
	$('#results').DataTable({
		order: [],
		columnDefs: [ 
			{ orderable: false, targets: [$("#results th").size() - 1] } 
		]		
	});

	function getNumeroCodiciCollegati() {
		return ${scenario.equals('modifica') ? modifica_codice_versamento.getCodiciVersamentoCollegati().size() : 0};
	}
	
	function getScenario() {
		return '${scenario}';
	}

	function isNuovo() {
		return getScenario() === 'nuovo';
	}

	function isModifica() {
		return getScenario() === 'modifica';
	}
	
	$("#buttonSave").on('click',function(){
		$("#modifica_codice_versamento").submit();
	});
	
	$("#buttonBack").on('click',function(){
		location = baseLocation + "codici-versamento/ricerca";
	});
	
	$("#buttonClean").on('click',function(){
		$("#modifica_codice_versamento").setDirty();
		
		$("#formParametriContainer input:not([readonly]):not(.voceEntrata)").val("");

// 		if (isNuovo() || getNumeroCodiciCollegati() == 0) {
// 			$("#formParametriContainer .voceEntrata").val("");	
// 		}
		
		$("#formParametriContainer select").val("");
		$("#formParametriContainer input[type=checkbox]").prop("checked", false);
		
		 $("#formElementiMultibeneficiarioContainerId").show();      
		$("#flagElementiMultibeneficiario").prop("checked", false);
		$("#elementiMultibeneficiarioId").hide();
		 $("#modalitaAssociazioneMultibeneficiario").val("");
		 $("#elementiMultibeneficiarioEnteId").hide();
		 $("#enteSecondarioAssociazioneMultibeneficiario").val("");
		 $("#elementiMultibeneficiariorCovAssociatoId").hide();
		 $("#covAssociatoAssociazioneMultibeneficiario").val("");
		 
		 $("#flagPersonalizzazioneCov").prop("checked", false);
		 $("#descrizioneTextCov").val("");
		 $("#passphrase").val("");
         $("#opzioniAbilitazioneNotificheId").hide();
		 
		 
		 
		 $("#labelButtonSave").show();
			$("#labelButtonProceed").hide();
		// $("#descrizioneVoceEntrata").val("");
		$("span.error").html("");

		if (isNuovo()) {
			$("#container-codice-codice-versamento").html("");
			$("#container-codice-voce-entrata").html("");
			$("#codiceVoceEntrata").val("");
			$("#descrizioneVoceEntrata").val("");
			$("#codiceModalitaIntegrazione").removeAttr("disabled");
		}
		
		$("#formElementiPndContainerId").hide(); 
        $("#strCredenzialiPnd").val("");
        $("#urlAttualizzazionePnd").val("");
		
	});

	$("#flagInvioFlussi").on('change',function(){
		syncForm();
	});

	$("#flagCodiceCorrentePostaleAppoggio").on('change',function(){
		syncForm();
	});
	
	$("#flagCodiceCorrentePostaleTesoreria").on('change',function(){
		syncForm();
	});

	$("#flagPresenzaBollettinoPostale").on('change',function(){
		syncForm();
	});
	
	$("#flagElementiMultibeneficiario").on('change',function(){
		syncForm();
	});
	
	$("#flagPersonalizzazioneCov").on('change',function(){
        syncForm();
    });
	
	


	if (false && (isNuovo() || getNumeroCodiciCollegati() == 0)) {
		$("#codiceVoceEntrata").autocomplete({
			minLength : 1,
			source : getAutocompleteVociEntrata(),
			select : function(event, ui) {
				if (ui.item.val == -1) {
	                $(this).val("");
	                $("#descrizioneVoceEntrata").val("");
	                $("#descrizione").val("");
	                $("#codice").val("");
	                return false;
				} else {
					var voce = getVoceEntrataByCodice(ui.item.label);
	               	$("#descrizioneVoceEntrata").val(voce.descrizione);
	               	$("#descrizione").val(voce.descrizione);
	               	$("#codice").val(voce.codice);
				}
			},
			change: function (e, u) {
	            if (u.item == null) {
	                $(this).val("");
	                $("#descrizioneVoceEntrata").val("");
	                $("#descrizione").val("");
	                $("#codice").val("");
	                return false;
	            }
	        }
		});
	} else {
		$("#codiceVoceEntrata").attr("readonly", "readonly");
	}
	
	function getVociEntrata() {
		var options = <c:out value="${modelVociEntrata_JS}" escapeXml="false"  />;
		
		return options;
	};

	function getCodiciVociEntrata() {
		var input = getVociEntrata();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push(input[i].codice);
		}
		
		return output;
	}

	function getAutocompleteVociEntrata() {
		if (true) {
			return getCodiciVociEntrata() ;
		}
	}
	
	function getVoceEntrataByCodice(codice) {
		var input = getVociEntrata();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].codice == codice) {
				return input[i];
			}
		}
		
		return null;
	}
	
	function gestisciTipoCodiceVersamento(codiceTipoPagamentoInput) {
		if (codiceTipoPagamentoInput) {
	        // RDI 02 2019
	        if (codiceTipoPagamentoInput == "REDS") {
	            $("#codiceModalitaIntegrazione").val("SRV");
	            $("#codiceModalitaIntegrazione").attr("disabled", "disabled");
	        }
	        
	        else
	        	{
	                $("#codiceModalitaIntegrazione").removeAttr("disabled");;
	        	}

	        if (codiceTipoPagamentoInput  == "LDC" || codiceTipoPagamentoInput  == "" )
	        {
	        $("#formElementiMultibeneficiarioContainerId").show();          
	        }
	    else
	        {
	        $("#formElementiMultibeneficiarioContainerId").hide();
	        $("#flagElementiMultibeneficiario").prop("checked", false);
	        
	        }
	        if (codiceTipoPagamentoInput  == "LDC" || codiceTipoPagamentoInput  == "REDS"
	                || codiceTipoPagamentoInput  == "PABL" || codiceTipoPagamentoInput  == "REDI")
	        {
	            $("#formElementiPndContainerId").show(); 
	        }
	        else
	            {
	                $("#formElementiPndContainerId").hide(); 
	                $("#strCredenzialiPnd").val("");
	                $("#urlAttualizzazionePnd").val("");
	                $("#formElementiPndContainerId span.error").html("");
	            }
	        
	    } 
	    else
	        {
	        $("#formElementiPndContainerId").hide(); 
	        $("#strCredenzialiPnd").val("");
	        $("#urlAttualizzazionePnd").val("");
	        $("#formElementiPndContainerId span.error").html("");
	        }
	    
	
	}

	$("#iban,#bic").on('change',function(){
		syncForm();
	});
	
	function syncForm(initial) {
		var sourceElement = $("#flagInvioFlussi");
		var targetElement = $("#eMailLabel");
		if (sourceElement.prop("checked")) {
			targetElement.addClass("required");
		} else {
			targetElement.removeClass("required");
		}

		var ibanField = $("#iban");
		var bicField = $("#bic");
		
		var ibanEmpty = !ibanField.val();
		var bicEmpty = !bicField.val();
		if (!ibanEmpty || !bicEmpty) {
			$("label[for='iban']").addClass("required");
			$("label[for='bic']").addClass("required");
		} else {
			$("label[for='iban']").removeClass("required");
			$("label[for='bic']").removeClass("required");
		}
		
		//RDI 10 
		var flagCodiceCorrentePostaleAppoggio = $("#flagCodiceCorrentePostaleAppoggio").prop("checked");
		var flagCodiceCorrentePostaleTesoreria = $("#flagCodiceCorrentePostaleTesoreria").prop("checked");
		
		if (flagCodiceCorrentePostaleAppoggio || flagCodiceCorrentePostaleTesoreria) {			
			$("#flagPresenzaBollettinoPostale").show();
			$("#flagPresenzaBollettinoPostaleLabel").show();		
		}else {
			$("#flagPresenzaBollettinoPostale").hide();
			$("#flagPresenzaBollettinoPostaleLabel").hide();
			$("#flagPresenzaBollettinoPostale").prop("checked",false);
		} 
		
		var flagElementiMultibeneficiario = $("#flagElementiMultibeneficiario").prop("checked");
			
			
		
		if (flagElementiMultibeneficiario)
			{
			$("#elementiMultibeneficiarioId").show();
			var modalitaAssociazioneMultibeneficiarioSelected = $("#modalitaAssociazioneMultibeneficiario").val();
			$("#labelButtonSave").hide();
			$("#labelButtonProceed").show();
			$("#alertNoOpzioniEnteSecondarioId").hide();
			if (modalitaAssociazioneMultibeneficiarioSelected == '1') 
			{
				$("#elementiMultibeneficiarioEnteId").hide();
				$("#enteSecondarioAssociazioneMultibeneficiario").val("");
				$("#elementiMultibeneficiariorCovAssociatoId").hide();
				$("#covAssociatoAssociazioneMultibeneficiario").val("");
				
				
				
			
			}
			if (modalitaAssociazioneMultibeneficiarioSelected == '2') 
				{
				$("#elementiMultibeneficiarioEnteId").show();
				$("#elementiMultibeneficiariorCovAssociatoId").show();
				if ($("#enteSecondarioAssociazioneMultibeneficiario option").length <1 )
					{
					$("#alertNoOpzioniEnteSecondarioId").show();
					}
				
				}
			else
				{
				$("#elementiMultibeneficiarioEnteId").hide();
				$("#enteSecondarioAssociazioneMultibeneficiario").val("");
				$("#elementiMultibeneficiariorCovAssociatoId").hide();
				$("#covAssociatoAssociazioneMultibeneficiario").val("");
				}
			
			}
		else
			{
			$("#labelButtonSave").show();
			$("#labelButtonProceed").hide();
			 $("#elementiMultibeneficiarioId").hide();
			 $("#modalitaAssociazioneMultibeneficiario").val("");
// 			 $("#modalitaAssociazioneMultibeneficiario.errors").html("");
			 $("#elementiMultibeneficiarioEnteId").hide();
			 $("#enteSecondarioAssociazioneMultibeneficiario").val("");
			 $("#elementiMultibeneficiariorCovAssociatoId").hide();
			 $("#covAssociatoAssociazioneMultibeneficiario").val("");
			 $("#formElementiMultibeneficiarioContainerId span.error").html("");
			 $("#alertNoOpzioniEnteSecondarioId").hide();
			 
			}
		
		var flagPersonalizzazioneCov = $("#flagPersonalizzazioneCov").prop("checked");
		if (flagPersonalizzazioneCov)
			{
			 $("#opzioniAbilitazioneNotificheId").show();
			
			}
		else
			{
			 $("#opzioniAbilitazioneNotificheId").hide();
			 $("#descrizioneTextCov").val("");
		        $("#passphrase").val("");
			}
		
		// Visualizzazione da sportello
		var codiceTipoPagamentob= $("#codiceTipoPagamento").val();
		
		if (codiceTipoPagamentob  == "PS"   )
        {
        $(".visualizzaSportelloClass").show(); 
        
//         da eliminare? 
//         var dataFineValiditab= $("#dataFineValidita").val();
//         var dataInizioValiditab= $("#dataInizioValidita").val();
        
//         if (dataFineValiditab == "" || dataFineValiditab  == null)
//         	{
//         	   var year = new Date().getFullYear();
//         	    $("#dataFineValidita").datepicker(
//         	    		{
//         	    	        format: 'dd/mm/yyyy',
//         	    	        language: 'it'
//         	    	    }).
//         	    	    datepicker("setDate", new Date(year, 11,31));
//         	}
        
//         if (dataInizioValiditab == "" || dataInizioValiditab  == null)
//         {

//          $("#dataInizioValidita").datepicker(
//                  {
//                      format: 'dd/mm/yyyy',
//                      language: 'it'
//                  }).
//                  datepicker("setDate", new Date());
//         }
    //  da eliminare  - fine?
        }
        else
            {
            
            $(".visualizzaSportelloClass").hide(); 
            $("#dataFineValidita").val("");
            $("#dataInizioValidita").val("");
            $("#flagVisualizzaDaSportello").prop("checked",false);
            $(".visualizzaSportelloClass span.error").html("");
            
            }
		
	} 
	
	var valoreInizialeCodiceVoceEntrata = $("#codiceVoceEntrata").val();
	if (valoreInizialeCodiceVoceEntrata) {
		var voceInizialeCodiceVoceEntrata = getVoceEntrataByCodice(valoreInizialeCodiceVoceEntrata);
       	$("#descrizioneVoceEntrata").val(voceInizialeCodiceVoceEntrata.descrizione);
       	$("#codice").val(voceInizialeCodiceVoceEntrata.codice);
	}

	var valoreInizialeCodice = $("#codice").val();
	if (valoreInizialeCodice) {
       	$("#container-codice-codice-versamento").html(valoreInizialeCodice);
	}

	var codiceTipoPagamentoIniziale = $("#codiceTipoPagamento").val();
	gestisciTipoCodiceVersamento(codiceTipoPagamentoIniziale) ;
		
		
	
	syncForm(true);
	
	scrollToFirstError();
	
	$("#codiceTipoPagamento").on('change',function(){
		var codiceTipoPagamentoSelected = $("#codiceTipoPagamento").val(); 
		

		// RDI 02 2019
		gestisciTipoCodiceVersamento(codiceTipoPagamentoSelected) ;
		
		
		
		if (codiceTipoPagamentoSelected  == "LDC" || codiceTipoPagamentoSelected  == "REDS"
            || codiceTipoPagamentoSelected  == "PABL" || codiceTipoPagamentoSelected  == "REDI")
        
        
         {
            $("#formAbilitazioneIoappId").show(); 
        }
        else
            {
                $("#formAbilitazioneIoappId").hide(); 
                $("#flagPersonalizzazioneCov").prop("checked", false);
                $("#descrizioneTextCov").val("");
                $("#passphrase").val("");
            }
		
		
		
	if (codiceTipoPagamentoSelected  == "LDC" || codiceTipoPagamentoSelected  == "REDS"
           || codiceTipoPagamentoSelected  == "PABL" || codiceTipoPagamentoSelected  == "REDI")        
         {
            $("#formAbilitazioneIoappId").show(); 
        }
        else
            {
                $("#formAbilitazioneIoappId").hide(); 
                $("#flagPersonalizzazioneCov").prop("checked", false);
                $("#descrizioneTextCov").val("");
                $("#passphrase").val("");
            }
		
		
		
		syncForm();

	    
	});
	$("#modalitaAssociazioneMultibeneficiario").on('change',function(){
		
		var modalitaAssociazioneMultibeneficiarioSelected = $("#modalitaAssociazioneMultibeneficiario").val();
		if (modalitaAssociazioneMultibeneficiarioSelected == '1') 
		{
			$("#elementiMultibeneficiarioEnteId").hide();
			$("#enteSecondarioAssociazioneMultibeneficiario").val("");
			$("#elementiMultibeneficiariorCovAssociatoId").hide();
			$("#covAssociatoAssociazioneMultibeneficiario").val("");
			apriModaleInformazioneAbilitazioneGestioneEsterna();
		
		}
		if (modalitaAssociazioneMultibeneficiarioSelected == '2') 
			{
			$("#elementiMultibeneficiarioEnteId").show();
			$("#elementiMultibeneficiariorCovAssociatoId").show();
			if ($("#enteSecondarioAssociazioneMultibeneficiario option").length <1 )
				{
				$("#alertNoOpzioniEnteSecondarioId").show();
				}
			}
		else
			{
			$("#elementiMultibeneficiarioEnteId").hide();
			$("#enteSecondarioAssociazioneMultibeneficiario").val("");
			$("#elementiMultibeneficiariorCovAssociatoId").hide();
			$("#covAssociatoAssociazioneMultibeneficiario").val("");
			}
		
	
	});
	
	$("#enteSecondarioAssociazioneMultibeneficiario").on('change',function(){
		var idEnte= this.value;
		var urlo= '${context}/codici-versamento/ajax/ricerca-codici-versamento-da-associare';
		$
		.ajax({
			type : "GET",
			url : urlo,
			data :   "idEnte=" + idEnte,
			dataType : 'json',
			success : function(data) {
				if (data.length < 1) {
					$("#covAssociatoAssociazioneMultibeneficiario")
							.text(
									"Nessun risultato");
				} else {
					$("#covAssociatoAssociazioneMultibeneficiario")
							.empty();
					$("#covAssociatoAssociazioneMultibeneficiario")
					.append(
							'<option value="">'
									+ '<spring:message code="form.ricerca.scegli" />'
									+ '</option>');
					$
							.each(
									data,
									function(
											index,
											codiceDaAssociare) {
												$("#covAssociatoAssociazioneMultibeneficiario")
												.append(
														'<option value="'
																+ codiceDaAssociare.id
																+ '" '
																+  ''
																+ ' >'
																+ codiceDaAssociare.descrizione
																+ '</option>');
									});
				}
			},

					
			error : function(xhr, status,
					error) {
				alert('Si è verificato un errore.');
			}
		});
		syncForm();
	});
	

	
});


</script>