<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

function apriModaleInformazioneAbilitazioneGestioneEsterna() {
	var modal = $("#modalInformazioneAbilitazioneGestioneEsterna");
	modal.modal("show");
}

function isAssistenza() {
    <sec:authorize access="hasRole('ASSISTENZA')">
    return true;
    </sec:authorize>
    return false;
}


$( document ).ready(function() {

	function getScenario() {
		return '${scenario}';
	}

	function isNuovo() {
		return getScenario() === 'nuovo';
	}

	function isModifica() {
		return getScenario() === 'modifica';
	}
	
	$('.datepicker').datepicker({
        format: 'dd/mm/yyyy',
        language: 'it'
    }); 
	
	var codiceTipoPagamentob= $("#codiceTipoPagamento").val();
    
    if (codiceTipoPagamentob  == "PS"   )
    {
    $(".visualizzaSportelloClass").show(); 
    
//  da eliminare  - inizio ?
//     var dataFineValiditab= $("#dataFineValidita").val();
    
//     var dataInizioValiditab= $("#dataInizioValidita").val();
    
    
//     if (dataFineValiditab == "" || dataFineValiditab  == null)
//         {

//         var year = new Date().getFullYear();
//          $("#dataFineValidita").datepicker(
//                  {
//                      format: 'dd/mm/yyyy',
//                      language: 'it'
//                  }).
//                  datepicker("setDate", new Date(year, 11,31));
//         }
    
//     if (dataInizioValiditab == "" || dataInizioValiditab  == null)
//     {

//      $("#dataInizioValidita").datepicker(
//              {
//                  format: 'dd/mm/yyyy',
//                  language: 'it'
//              }).
//              datepicker("setDate", new Date());
//     }
//  da eliminare  - fine?
    }
    else
        {
        $(".visualizzaSportelloClass").hide(); 
        $("#dataFineValidita").val("");
        $("#dataInizioValidita").val("");
        $("#flagVisualizzaDaSportello").prop("checked",false);
        $("#visualizzaSportelloContainerId span.error").html("");
        
        }
    


$("#buttonSave").on('click',function(){
        var modalitaAssociazioneMbOld = $("#modalitaAssociazioneMultibeneficiarioOld").val();
        var modalitaAssociazioneMb = $("#modalitaAssociazioneMultibeneficiario").val();

        var covAssociatoAssociazioneMbOld = $("#covAssociatoAssociazioneMultibeneficiarioOld").val();
        var covAssociatoAssociazioneMb = $("#covAssociatoAssociazioneMultibeneficiario").val();
        
        if (modalitaAssociazioneMbOld != modalitaAssociazioneMb)
            {
             
                $("#modalDisabilitazioneGestioneMb").show();
            }
        else if ((null != covAssociatoAssociazioneMbOld && "" != covAssociatoAssociazioneMbOld )
                && covAssociatoAssociazioneMbOld != covAssociatoAssociazioneMb)
            {
              $("#modalDisabilitazioneGestioneMb").show();
            }
        else {
// 					$("#codiceTipoPagamento").val(null);//valorizzato rompe il servizio
                $("#modifica_codice_versamento_collegato").submit();
                }
    });


    
    $("#modalDisabilitazioneGestioneMbOk").on('click',function(){
        $("#modifica_codice_versamento_collegato").submit();
    });

    $("#modalDisabilitazioneGestioneMbKo").on('click',function(){
        $("#modalDisabilitazioneGestioneMb").hide();
    });

    $("#modalDisabilitazioneGestioneMbClose").on('click',function(){
        $("#modalDisabilitazioneGestioneMb").hide();
    });
	
	$("#buttonBack").on('click',function(){
		<c:if test="${pagina_origine_codice_versamento eq 'modifica'}">
		location = "${context}/codici-versamento/modifica/${id_codice_versamento}";
		</c:if>
		<c:if test="${pagina_origine_codice_versamento eq 'visualizza'}">
		location = "${context}/codici-versamento/visualizza/${id_codice_versamento}";
		</c:if>
		<c:if test="${pagina_origine_codice_versamento ne 'visualizza' and pagina_origine_codice_versamento ne 'modifica'}">
		location = "${context}/codici-versamento/ricerca";
		</c:if>
	});
	
	$("#buttonClean").on('click',function(){
		$("#modifica_codice_versamento_collegato").setDirty();
		
		$("#formCollegatoContainer input:not([readonly])").val("");
		$("#formCollegatoContainer select").val("");
		$("#formCollegatoContainer input[type=checkbox]").prop("checked", false);
		$("span.error").html("");
		
		$("#flagElementiMultibeneficiario").prop("checked", false);
		$("#elementiMultibeneficiarioId").hide();
		 $("#modalitaAssociazioneMultibeneficiario").val("");
		 $("#elementiMultibeneficiarioEnteId").hide();
		 $("#enteSecondarioAssociazioneMultibeneficiario").val("");
		 $("#elementiMultibeneficiariorCovAssociatoId").hide();
		 $("#covAssociatoAssociazioneMultibeneficiario").val("");
// 		 $("#labelButtonSave").show();
// 		 $("#labelButtonProceed").hide();

          if (isAssistenza() )
             {
             
             $("#flagPersonalizzazioneCov").prop("checked", false);
             $("#descrizioneTextCov").val("");
             $("#passphrase").val("");
             $("#opzioniAbilitazioneNotificheId").hide();
             
             $("#strCredenzialiPnd").val("");
             $("#urlAttualizzazionePnd").val("");
             
             }
         
         
        

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

	$("#iban,#bic").on('change',function(){
		syncForm();
	});
	
	$("#flagElementiMultibeneficiario").on('change',function(){
		syncForm();
	});
	
	$("#flagPersonalizzazioneCov").on('change',function(){
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
				alert('Si ? verificato un errore.');
			}
		});
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

		var flagCodiceCorrentePostaleAppoggio = $("#flagCodiceCorrentePostaleAppoggio").prop("checked");
		var flagCodiceCorrentePostaleTesoreria = $("#flagCodiceCorrentePostaleTesoreria").prop("checked");
		
		if (flagCodiceCorrentePostaleAppoggio || flagCodiceCorrentePostaleTesoreria) {			
			$("#flagPresenzaBollettinoPostaleBlock").show();
		}else {
			$("#flagPresenzaBollettinoPostaleBlock").hide();
			$("#flagPresenzaBollettinoPostale").prop("checked",false);
		} 

		var tipoCodiceVersamentoIniziale  = $("#codiceTipoPagamento").val();

		if (tipoCodiceVersamentoIniziale == "LDC")
			{
		     var flagElementiMultibeneficiario = $("#flagElementiMultibeneficiario").prop("checked");
		            
		            
		        
		        if (flagElementiMultibeneficiario)
		            {
		        	$("#formElementiMultibeneficiarioContainerId").show();  
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
//		           $("#modalitaAssociazioneMultibeneficiario.errors").html("");
		             $("#elementiMultibeneficiarioEnteId").hide();
		             $("#enteSecondarioAssociazioneMultibeneficiario").val("");
		             $("#elementiMultibeneficiariorCovAssociatoId").hide();
		             $("#covAssociatoAssociazioneMultibeneficiario").val("");
		             $("#formElementiMultibeneficiarioContainerId span.error").html("");
		             $("#alertNoOpzioniEnteSecondarioId").hide();
		            }
			}
		else
			{
			 $("#formElementiMultibeneficiarioContainerId").hide();  
			}
		
		if (tipoCodiceVersamentoIniziale) {
            // RDI 02 2019
            
            if (tipoCodiceVersamentoIniziale  == "LDC" || tipoCodiceVersamentoIniziale  == "REDS"
                    || tipoCodiceVersamentoIniziale  == "PABL" || tipoCodiceVersamentoIniziale  == "REDI")
            {
                $("#formElementiPndContainerId").show(); 
            }
            else
                {
                    $("#formElementiPndContainerId").hide(); 
                    $("#strCredenzialiPnd").val('');
                    $("#urlAttualizzazionePnd").val('');
                    $("#formElementiPndContainerId span.error").html("");
                }
        } 
        else
            {
            $("#formElementiPndContainerId").hide(); 
            $("#strCredenzialiPnd").val('');
            $("#urlAttualizzazionePnd").val('');
            $("#formElementiPndContainerId span.error").html("");
            
            }
        
		
	        
	        if (isAssistenza() )
	            {
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
	            }
	        else
	            {
	             var flagPersonalizzazioneCov = $("#flagPersonalizzazioneCov").val();
	             
	             if (flagPersonalizzazioneCov == 'true')
	                 {
	                 
	                 $("#flagPersonalizzazioneCovHide").prop("checked", true);
	                 }
	                
	            }

	}

	syncForm(true);
	
	scrollToFirstError();
});

</script>
