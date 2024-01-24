<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script>

$( document ).ready(function() {

	$('.datepicker').datepicker({
	    format: 'dd/mm/yyyy',
	    language: 'it'
	});

	function getScenario() {
		return '${scenario}';
	}
	
	function isDaCodiceVersamento() {
		return'${flagValoreInizialeIdCodiceVersamento}' === 'true';
	}


	function isNuovo() {
		return getScenario() === 'nuovo';
	}

	function isModifica() {
		return getScenario() === 'modifica';
	}
	
	function isDuplica() {
		return getScenario() === 'duplica';
	}

	function isNuovoDaCodiceVersamento() {
        return getScenario() === 'nuovoDaCodiceVersamento';
    }
	
	$("#buttonSave").on('click',function(){
		const idId=  window.location.pathname.split("/").pop() ;
		
		if (!isNaN(idId))
			{
			$("#id_duplica").val( window.location.pathname.split("/").pop() );
			}
		
		$("#modifica_riferimento_contabile").submit();
	});
	
	$("#buttonBack").on('click',function(){
		location = baseLocation + "riferimenti-contabili/ricerca";
	});
	
	$("#buttonClean").on('click',function(){

		if (isDuplica())
			{
			 $("#formParametriContainer input:not([readonly]):not(.codiceVersamento)").val("");
			}
		else
			{
			  $("#formParametriContainer input:not([readonly])").val("");
			}
		
		
		$("#formParametriContainer select").val("");
		
// 		$("#formParametriContainer input[type=checkbox]").prop("checked", false);
		$("span.error").html("");

		$("#modifica_riferimento_contabile").setDirty();
		if (isNuovo())
			{
			nascondiMultibeneficiario()
			}
		
		syncForm();
	});

	$("#flagAssociaRifContSecondario").change(function(){


        if ($("#flagAssociaRifContSecondario").prop("checked"))
            {
            $("#divEnteSecondarioAssociato").show();
            $("#divCovEnteSecondarioAssociato").show();
            $("#divRifContabileSecondarioAssociato").show();
            $("#flagAssociaRifContSecondarioValue").val("true" );
            $("#flagElementiMultibeneficiario").val("true" );
            
            }
        else
            {
            $("#divEnteSecondarioAssociato").hide();
            $("#divCovEnteSecondarioAssociato").hide();
            $("#divRifContabileSecondarioAssociato").hide();
            $("#flagAssociaRifContSecondarioValue").val("false" );
            $("#flagElementiMultibeneficiario").val("false" );
            $("#idRifContabileSecondarioAssociato").val("" );
            }
    });


	function syncForm(initial) {
		if (isNuovo() ||isDuplica() ||isNuovoDaCodiceVersamento() ) {

			const optionSelected = $("#idTassonomia option:selected").val();
			var descrizioneSelected = $("#idTassonomia option:selected").text();
            const cv2 = <c:out value="${model_descrizione_dato_specifico_riscossione_JS}" escapeXml="false"  />;
                
            	if (optionSelected) {
                    for (let i = 0; i < cv2.length; i ++) {
                        if ( optionSelected == cv2[i].id )
                        	{
	                        	 if ((descrizioneSelected != null && descrizioneSelected.replace(/\s\s+/g, ' ').trim() == cv2[i].tipoServizio.replace(/\s\s+/g, ' ').trim() )
	                        			 ||descrizioneSelected == cv2[i].tipoServizio.replace(/\s\s+/g, ' ').trim() )
	                            {
		                             $("#datoSpecificoRiscossione").val(cv2[i].datiSpecificiIncasso);
		                             $("#dataInizioValiditaCodiceTassonomico").val(cv2[i].dataInizioValiditaFormat);
		                             $("#dataFineValiditaCodiceTassonomico").val(cv2[i].dataFineValiditaFormat);
		                             $("#descrizioneDatoSpecificoRiscossione").val(cv2[i].tipoServizio);
		                             $("#codiceDatoSpecificoRiscossione").val(cv2[i].codTipologiaServizio);
		                             
		                             $("#idTassonomia").val(cv2[i].id);
	                             }
                        	}
                    }
            	} else {
                    $("#datoSpecificoRiscossione").val("");
                    $("#dataInizioValiditaCodiceTassonomico").val("");
                    $("#dataFineValiditaCodiceTassonomico").val("");
                    $("#descrizioneDatoSpecificoRiscossione").val();
                    $("#codiceDatoSpecificoRiscossione").val("");
                    $("#idTassonomia").val("");
               }
 			modify = false;
		}
	} 
	
	if(isModifica()){
		var numeroArticolo = $("#numeroArticolo").val();
		var numeroCapitolo = $("#numeroCapitolo").val();
		var datoSpecificoRiscossione = $("#datoSpecificoRiscossione").val();

		if('${modifica_riferimento_contabile.codiceTipologiaDatoSpecificoRiscossione}'=="${tipologia_dato_specifico_riscossione}"){
			if(datoSpecificoRiscossione==numeroCapitolo || datoSpecificoRiscossione==numeroCapitolo+"/"+numeroArticolo ){
				$("#numeroArticolo").attr("readonly", true);
				$("#numeroCapitolo").attr("readonly", true);
			}
		}
	}
	
	$("#codiceTipologiaDatoSpecificoRiscossione").change(function() {
		var optionSelected = $("#codiceTipologiaDatoSpecificoRiscossione option:selected").val();
		if (optionSelected) {
			$("#prefissoDatoSpecificoRiscossione").html(optionSelected + " / ");
			$("#descrizioneDatoSpecificoRiscossione").val('');
			$("#datoSpecificoRiscossione").val('');
 			setDatoSpecificoRiscossione(optionSelected);
		} else {
			$("#prefissoDatoSpecificoRiscossione").html(" -- / ");
			$("#descrizioneDatoSpecificoRiscossione").val('');
			$("#datoSpecificoRiscossione").val('');
		}
		syncForm(true);
	});

	$("#idTassonomia").change(function() {
        var optionSelected = $("#idTassonomia option:selected").val();
        var cv = <c:out value="${modelCodiciVersamento_JS}" escapeXml="false"  />;
        
        syncForm(true);
    });

	
	$("#numeroCapitoloAndArticolo").change(function() {
		setDatoSpecificoRiscossione($("#codiceTipologiaDatoSpecificoRiscossione option:selected").val());
	});
	
	var modify = false;
	$("#datoSpecificoRiscossione").change(function() {
		if (!isDuplica() ) {
			var codiceTipologiaDatoSpecificoRiscossione = $("#codiceTipologiaDatoSpecificoRiscossione option:selected").val();
			if(codiceTipologiaDatoSpecificoRiscossione=="${tipologia_dato_specifico_riscossione}"){
				var numArticolo = $("#numeroArticolo").val();
				var datoSpecificoRiscossione = $("#datoSpecificoRiscossione").val();
				if(numArticolo === ""){
					datoSpecificoRiscossione==$("#numeroCapitolo").val()? modify=false :modify = true;
				}
				else{
					datoSpecificoRiscossione==($("#numeroCapitolo").val() + '/' + numArticolo)? modify=false :modify = true;
				}
			}
			else{
				modify = false;
			}
		}
	});
	
	function setDatoSpecificoRiscossione(codiceTipologiaDatoSpecificoRiscossione){
		if(codiceTipologiaDatoSpecificoRiscossione=="${tipologia_dato_specifico_riscossione}" && !modify && !isDuplica()){
			var numArticolo = $("#numeroArticolo").val();
			if(numArticolo === ""){
				$("#datoSpecificoRiscossione").val($("#numeroCapitolo").val());
			}
			else{
				$("#datoSpecificoRiscossione").val($("#numeroCapitolo").val() + '/' + numArticolo);
			}
		}
	}

	function nascondiMultibeneficiario(){
		 $("#titleElmentiMultibeneficiario").hide();
         $("#divflagAssociaRifContSecondario").hide();
         $("#divflagAssociaRifContPrimario").hide();
         $("#divEnteSecondarioAssociato").hide();
         $("#divCovEnteSecondarioAssociato").hide();
         $("#divRifContabileSecondarioAssociato").hide();
         
         $("#covSecondarioAssociato").val("" );
         $("#enteSecondarioAssociato").val("" ); 
         $("#idRifContabileSecondarioAssociato").val("" );
         $("#flagAssociaRifContSecondario").prop("checked", false);
         $("#flagAssociaRifContPrimario").prop("checked", false);
         $("#flagAssociaRifContSecondarioValue").val("" );
         $("#flagAssociaRifContPrimarioValue").val("" );
         $("#flagElementiMultibeneficiario").val(false);
    }
	
	
		
	if (isNuovo()) {
		$("#descrizioneCodiceVersamento").autocomplete({
			minLength : 1,
			source : getAutocompleteCodiciVersamento(),
			select : function(event, ui) {
				if (ui.item.val == -1) {
	                $(this).val("");
	                $("#idCodiceVersamento").val("");
	                $("#tipoPagamento").val("");
	                $("#titleElmentiMultibeneficiario").hide();
	                
	                return false;
				} else {
					var voce = getCodiceVersamentoByCodice(ui.item.object.codice);
	               	$("#idCodiceVersamento").val(voce.id);
	               	$("#tipoPagamento").val(voce.tipoPagamento);
	               	$(this).val(voce.codice + " - " + voce.descrizione);
                    
// 	               	if (voce.flagMbPrimario )
	               	 if (voce.flagMbPrimario && null!= voce.codiceVersamentoSecondario )
		             {
		                var urlo= '${context}/riferimenti-contabili/ajax/ricerca-riferimenti-contabili-secondari-da-associare';
		                $
		                .ajax({
		                    type : "GET",
		                    url : urlo,
		                    data :   "idRifContabileSecondario=" + voce.idCodiceVersamentoSecondario,
		                    dataType : 'json',
		                    success : function(data) {
		                        if (data.length < 1) {
		                            $("#idRifContabileSecondarioAssociato")
		                                    .text(
		                                            "Nessun risultato");
		                            nascondiMultibeneficiario();
                                    
		                        } else {
		                        	$("#titleElmentiMultibeneficiario").show();
		                            $("#divflagAssociaRifContPrimario").show();
		                            $("#flagAssociaRifContPrimario").prop("checked", false);
		                            $("#flagAssociaRifContPrimarioValue").val(false );
		                            
		                            
		                            $("#divflagAssociaRifContSecondario").show();
		                            $("#flagAssociaRifContSecondario").removeAttr("disabled");
		                            $("#flagAssociaRifContSecondario").removeAttr("readonly");
		                            $("#flagAssociaRifContSecondario").prop("checked", true);
		                            $("#flagAssociaRifContSecondarioValue").val(true );
		                            $("#covSecondarioAssociato").val(voce.codiceVersamentoSecondario+" - "+voce.descrizioneCodiceVersamentoSecondario );
		                            $("#enteSecondarioAssociato").val(voce.enteSecondario );
		                            
		                            $("#divEnteSecondarioAssociato").show();
		                            $("#divCovEnteSecondarioAssociato").show();
		                            $("#divRifContabileSecondarioAssociato").show();
		                            $("#flagElementiMultibeneficiario").val(true);
		                            
		                            $("#idRifContabileSecondarioAssociato")
		                                    .empty();
		                            $("#idRifContabileSecondarioAssociato")
		                            .append(
		                                    '<option value="">'
		                                            + '<spring:message code="form.ricerca.scegli" />'
		                                            + '</option>');
		                            $
		                                    .each(
		                                            data,
		                                            function(
		                                                    index,
		                                                    riferimentoContabileDaAssociare) {
		                                                        $("#idRifContabileSecondarioAssociato")
		                                                        .append(
		                                                                '<option value="'
		                                                                        + riferimentoContabileDaAssociare.id
		                                                                        + '" '
		                                                                        +  ''
		                                                                        + ' >'
		                                                                        + riferimentoContabileDaAssociare.descrizione
		                                                                        + '</option>');
		                                            });
		                        }
		                    },

		                            
		                    error : function(xhr, status,
		                            error) {
		                        alert('Si ? verificato un errore.');
		                    }
		                });
		             }
	               	else if (voce.flagMbSecondario )
	               	{
                        $("#titleElmentiMultibeneficiario").show();
                        
                        $("#divflagAssociaRifContSecondario").show  ();
                        $("#flagAssociaRifContSecondario").attr("disabled", "disabled");
                        $("#flagAssociaRifContSecondario").attr("readonly", "readonly");
                        $("#flagAssociaRifContSecondario").prop("checked", false);
                        $("#flagAssociaRifContSecondarioValue").val(false );

                        $("#divEnteSecondarioAssociato").hide();
                        $("#divCovEnteSecondarioAssociato").hide();
                        $("#divRifContabileSecondarioAssociato").hide();
                        $("#covSecondarioAssociato").val("" );
                        $("#enteSecondarioAssociato").val("" );
                        $("#idRifContabileSecondarioAssociato").val("" );
                        
                        $("#divflagAssociaRifContPrimario").show();
                        $("#flagAssociaRifContPrimario").prop("checked", true);
                        $("#flagAssociaRifContPrimarioValue").val(true );
                        $("#flagElementiMultibeneficiario").val(true);
                     }
	               	else
		               	{ 
	               		nascondiMultibeneficiario();
		               	}
				}
			},
			change: function (e, u) {
	            if (u.item == null) {
	                $(this).val("");
	                $("#idCodiceVersamento").val("");
	                $("#tipoPagamento").val("");

	                nascondiMultibeneficiario();
                    
// 	                return false;
	            }
	        }
		});
	}

	

	
	
	$("#TODO").on('change',function(){
		syncForm();
	});

	syncForm(true);

	/* dati da controller */

	function getCodiciVersamento() {
		var options = <c:out value="${modelCodiciVersamento_JS}" escapeXml="false"  />;
		
		return options;
	};

	function getCodiciCodiciVersamento() {
		var input = getCodiciVersamento();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push(input[i].codice);
		}
		
		return output;
	}

	function getAutocompleteCodiciVersamento() {

		var input = getCodiciVersamento();
		var output = [];

		for (var i = 0; i < input.length; i ++) {
			output.push({
				value: input[i].codice + " - " + input[i].descrizione,
				label: input[i].codice + " - " + input[i].descrizione,
				object: input[i]
			});
		}
		
		return output;
	}
	
	function getCodiceVersamentoByCodice(codice) {
		var input = getCodiciVersamento();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].codice == codice) {
				return input[i];
			}
		}
		
		return null;
	}

	function getCodiceVersamentoById(id) {
		var input = getCodiciVersamento();

		for (var i = 0; i < input.length; i ++) {
			if (input[i].id == id) {
				return input[i];
			}
		}
		
		return null;
	}

	
	if (isNuovo() ||isDuplica() ||isNuovoDaCodiceVersamento())
    {	
	var valoreInizialeIdCodiceVersamento = $("#idCodiceVersamento").val();

	<c:if test="${not empty codice_versamento_default}">
	valoreInizialeIdCodiceVersamento = '<c:out value="${codice_versamento_default}" />';
	</c:if>
	
	if (valoreInizialeIdCodiceVersamento) {
	var voceInizialeIdCodiceVersamento = getCodiceVersamentoById(valoreInizialeIdCodiceVersamento);
    $("#descrizioneCodiceVersamento").val(voceInizialeIdCodiceVersamento.codice + " - " + voceInizialeIdCodiceVersamento.descrizione);
    $("#idCodiceVersamento").val(valoreInizialeIdCodiceVersamento);
    $("#tipoPagamento").val(voceInizialeIdCodiceVersamento.tipoPagamento);
    $("#covSecondarioAssociato").val(voceInizialeIdCodiceVersamento.codiceVersamentoSecondario );
    $("#enteSecondarioAssociato").val(voceInizialeIdCodiceVersamento.enteSecondario );

    <c:if test="${not empty codice_versamento_default}">
    $("#flagAssociaRifContPrimarioValue").val(voceInizialeIdCodiceVersamento.flagMbSecondario );
    $("#flagAssociaRifContSecondarioValue").val(voceInizialeIdCodiceVersamento.flagMbPrimario );
    $("#flagElementiMultibeneficiario").val(voceInizialeIdCodiceVersamento.flagMbPrimario ||voceInizialeIdCodiceVersamento.flagMbSecondario );
    
    </c:if>
	
	
	   $("#flagAssociaRifContPrimario").prop("checked", "true"== $("#flagAssociaRifContPrimarioValue").val());
       $("#flagAssociaRifContSecondario").prop("checked","true"== $("#flagAssociaRifContSecondarioValue").val());


       if ( voceInizialeIdCodiceVersamento.flagMbPrimario && null!= voceInizialeIdCodiceVersamento.codiceVersamentoSecondario)
        {
           $("#titleElmentiMultibeneficiario").show();
           $("#divflagAssociaRifContPrimario").show();
           $("#flagAssociaRifContPrimario").attr("disabled", "disabled");
           $("#flagAssociaRifContPrimario").attr("readonly", "readonly");
           $("#divflagAssociaRifContSecondario").show();

           if (isNuovo())
           {
        	   $("#flagAssociaRifContSecondario").removeAttr("disabled");
               $("#flagAssociaRifContSecondario").removeAttr("readonly");
           }
         

           if ($("#flagAssociaRifContSecondario").prop("checked"))
               {
        	   $("#divEnteSecondarioAssociato").show();
               $("#divCovEnteSecondarioAssociato").show();
               $("#divRifContabileSecondarioAssociato").show();
               }
           }
       else if (voceInizialeIdCodiceVersamento.flagMbSecondario  )
       {
           $("#titleElmentiMultibeneficiario").show();
           
           $("#divflagAssociaRifContSecondario").show  ();
           $("#flagAssociaRifContSecondario").attr("disabled", "disabled");
           $("#flagAssociaRifContSecondario").attr("readonly", "readonly");
           
           $("#divflagAssociaRifContPrimario").show();
           $("#divEnteSecondarioAssociato").hide();
           $("#divCovEnteSecondarioAssociato").hide();
           $("#divRifContabileSecondarioAssociato").hide();
           
        }
       else
       { 
       nascondiMultibeneficiario();
       }
    	
       	setTimeout( function() {
           	$("#dataInizioValidita").focus();
        }, 500 );
       	}
    }

    if (isModifica()) {
        
    	 $("#flagAssociaRifContPrimario").prop("checked", "true"== $("#flagAssociaRifContPrimarioValue").val());
         $("#flagAssociaRifContSecondario").prop("checked","true"== $("#flagAssociaRifContSecondarioValue").val());
    }
	
	
	scrollToFirstError();

});

</script>
