/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function() {
	$("#backToHomeAction1").click(function() {
		confirmBackAction(true, "#backToHomeForm");
	});
	$("#backToHomeAction2").click(function() {
		confirmBackAction(true, "#backToHomeForm");
	});
	$("#backToRicercaListeAggiornamentoPosizioniDebitorieAction1").click(function() {
		confirmBackAction(true, "#backToRicercaListeAggiornamentoPosizioniDebitorieForm");
	});
	$("#backToRicercaListeAggiornamentoPosizioniDebitorieAction2").click(function() {
		confirmBackAction(true, "#backToRicercaListeAggiornamentoPosizioniDebitorieForm");
	});
	$("#backToVisualizzaFlussoPosizioniDebitorieDaAggiornare1").click(function() {
		confirmBackAction(true, "#backToVisualizzaFlussoPosizioniDebitorieDaAggiornareForm");
	});
	$("#backToVisualizzaFlussoPosizioniDebitorieDaAggiornare2").click(function() {
		confirmBackAction(true, "#backToVisualizzaFlussoPosizioniDebitorieDaAggiornareForm");
	});
	$("#ripristinaAction").click(function() {
		confirmBackAction(true, "#ripristinaForm");
	});
	$("#eliminaComponenteImporto0").click(function() {
		eliminaComponenteImporto(0);
	});
	$("#eliminaComponenteImporto0Secondario").click(function() {
		eliminaComponenteImportoSecondario();
	});
	$("#eliminaComponenteImporto1").click(function() {
		eliminaComponenteImporto(1);
	});
	$("#eliminaComponenteImporto2").click(function() {
		eliminaComponenteImporto(2);
	});
	$("#eliminaComponenteImporto3").click(function() {
		eliminaComponenteImporto(3);
	});
	$("#eliminaComponenteImporto4").click(function() {
		eliminaComponenteImporto(4);
	});
	$("#eliminaRiferimentoPagamento0").click(function() {
		eliminaRiferimentoPagamento(0);
	});
	$("#eliminaRiferimentoPagamento1").click(function() {
		eliminaRiferimentoPagamento(1);
	});
	$("#eliminaRiferimentoPagamento2").click(function() {
		eliminaRiferimentoPagamento(2);
	});
	$("#eliminaRiferimentoPagamento3").click(function() {
		eliminaRiferimentoPagamento(3);
	});
	$("#eliminaRiferimentoPagamento4").click(function() {
		eliminaRiferimentoPagamento(4);
	});
	

	
	function eliminaComponenteImporto(start) {
		for (r = start; r < 4; r++) {
			$("#compimpId" + r).val($("#compimpId" + (r + 1)).val());
			$("#compimpImporto" + r).val($("#compimpImporto" + (r + 1)).val());
			$("#strCompimpImporto" + r).val($("#strCompimpImporto" + (r + 1)).val());
			$("#compimpCausale" + r).val($("#compimpCausale" + (r + 1)).val());
			$("#compimpAnnoAccert" + r).val($("#compimpAnnoAccert" + (r + 1)).val());
			$("#strCompimpAnnoAccert" + r).val($("#strCompimpAnnoAccert" + (r + 1)).val());
			$("#compimpNumeroAccert" + r).val($("#compimpNumeroAccert" + (r + 1)).val());
			$("#strCompimpNumeroAccert" + r).val($("#strCompimpNumeroAccert" + (r + 1)).val());
		}
		$("#compimpId4").val(null);
		$("#compimpImporto4").val(null);
		$("#strCompimpImporto4").val(null);
		$("#compimpCausale4").val(null);
		$("#compimpAnnoAccert4").val(null);
		$("#strCompimpAnnoAccert4").val(null);
		$("#compimpNumeroAccert4").val(null);
		$("#strCompimpNumeroAccert4").val(null);
		return false;
	}

	function eliminaComponenteImportoSecondario() {
		$("#compimpId0Secondario").val("");
		$("#compimpCausale0Secondario").val("");
		$("#strCompimpImporto0Secondario").val("");
	}

	function eliminaRiferimentoPagamento(start) {
		for (r = start; r < 4; r++) {
			$("#rifpagId" + r).val($("#rifpagId" + (r + 1)).val());
			$("#rifpagNome" + r).val($("#rifpagNome" + (r + 1)).val());
			$("#rifpagValore" + r).val($("#rifpagValore" + (r + 1)).val());
		}
		$("#rifpagId4").val(null);
		$("#rifpagNome4").val(null);
		$("#rifpagValore4").val(null);
		return false;
	}
	
	function eliminaSoggetto() {
		
		$("#soggettoIdUnivocoFiscalePersonaFisica").val(null);
		$("#soggettoIdUnivocoFiscalePersonaGiuridica").val(null);
		$("#soggettoCognome").val(null);
		$("#soggettoNome").val(null);
		$("#soggettoRagioneSociale").val(null);
		
	}

	$("#aggiornamentoIdTipo-1").click(hideAggiornamentoModifica);
	$("#aggiornamentoIdTipo-2").click(hideAggiornamentoAnnullamento);
	$("#caricaAction").click(caricaAction);

	function hideAggiornamentoAnnullamento() {
		$("#divAggiornamentoAnnullamento").addClass("hide");
		$("#div1AggiornamentoModifica").addClass("hide");
		$("#div2AggiornamentoModifica").addClass("hide");
		$("#div3AggiornamentoModifica").addClass("hide");
		$("#div4AggiornamentoModifica").addClass("hide");
		$("#div_motivazione").addClass("hide");
		$("#salvaAction").addClass("hide");
		$("#caricaAction").removeClass("hide")
		$("#iuv-container").removeClass("hide");
		$("#label-container").removeClass("hide");
	}
	function hideAggiornamentoModifica() {
		$("#divAggiornamentoAnnullamento").removeClass("hide");
		$("#div1AggiornamentoModifica").addClass("hide");
		$("#div2AggiornamentoModifica").addClass("hide");
		$("#div3AggiornamentoModifica").addClass("hide");
		$("#div4AggiornamentoModifica").addClass("hide");
		$("#div_motivazione").removeClass("hide");
		$("#salvaAction").removeClass("hide");
		$("#caricaAction").addClass("hide");
		$("#iuv-container").addClass("hide");
		$("#label-container").addClass("hide");
	}

	function caricaAction() {
		const idPosizioneDebitoriaEsterna = document.getElementById("idPosizioneDebitoriaEsterna").value;
		const iuv = document.getElementById("iuv").value;
		if ("" === idPosizioneDebitoriaEsterna && "" === iuv) {
			return;
		}
		const params = new URLSearchParams({
			idPosDebDaAgg: idPosizioneDebitoriaEsterna,
			iuv: iuv,
		});
		cleanAllFields();
		$("#s-nf").addClass("hide");
		$("#s-nu").addClass("hide");
		$.ajax({
			type: "GET",
			url: "caricaPosizione.do?" + params.toString(),
			success: function (data) {
				if ( null === data.idPosizioneDebitoriaEsterna ) {
					errCarica(0, data);
					return;
				}
				if ( !data.updatable ) {
					errCarica(1, data);
					return;
				}
				$("#div_motivazione").removeClass("hide");
				$("#div1AggiornamentoModifica").removeClass("hide");
				$("#div2AggiornamentoModifica").removeClass("hide");
				$("#div3AggiornamentoModifica").removeClass("hide");
				$("#div4AggiornamentoModifica").removeClass("hide");
				$("#div5AggiornamentoModifica").removeClass("hide");
				$("#salvaAction").removeClass("hide");
				cleanAllFields();
				$('.basicAutoComplete').autoComplete('set', {value: data.idPosizioneDebitoriaEsterna, text: data.idPosizioneDebitoriaEsterna});
				if (data.iuv != null) {
					$('.basicAutoCompleteIUV').autoComplete('set', {value: data.iuv, text: data.iuv});
				}
				//gestione multi
				if (data.importoPrincipale != null) {
					$("#formId_strImportoPrincipale").val( data.importoPrincipale );
				}
				if (data.importoPrincipale != null) {
					$("#formId_strImportoSecondario").val( data.importoSecondario );
				}
				//
				if (data.dataInizioValidita != null && data.dataInizioValidita != undefined) {
					$("#formId_strDataInizioValidita").val(data.dataInizioValidita.substring(8,10) + "/" + data.dataInizioValidita.substring(5,7) + "/" + data.dataInizioValidita.substring(0,4));
				}
				if (data.dataFineValidita != null && data.dataFineValidita != undefined) {
					$("#formId_strDataFineValidita").val(data.dataFineValidita.substring(8,10) + "/" + data.dataFineValidita.substring(5,7) + "/" + data.dataFineValidita.substring(0,4));
				}
				if (data.dataScadenza != null && data.dataScadenza != undefined) {
					$("#formId_strDataScadenza").val(data.dataScadenza.substring(8,10) + "/" + data.dataScadenza.substring(5,7) + "/" + data.dataScadenza.substring(0,4));
				}
				$("#formId_strCompimpImportoTotale").val(data.importoTotale);
				$("#formId_desCausaleVersamento").val(data.desCausaleVersamento);
				let primoSlot = 0;
				if (data.componenteImportoDtoList[0] != undefined) {
					if (data.componenteImportoDtoList[0].flagComponenteSecondario) {
						$("#compimpId0Secondario").val(data.componenteImportoDtoList[0].id);
						$("#strCompimpImporto0Secondario").val(data.componenteImportoDtoList[0].importo);
						$("#compimpCausale0Secondario").val(data.componenteImportoDtoList[0].causale);
					} else {
						$("#compimpId" + primoSlot).val(data.componenteImportoDtoList[0].id);
						$("#strCompimpImporto" + primoSlot).val(data.componenteImportoDtoList[0].importo);
						$("#compimpCausale" + primoSlot).val(data.componenteImportoDtoList[0].causale);
						$("#compimpDatiSpec" + primoSlot).val(data.componenteImportoDtoList[0].datiSpecificiRiscossione);
						if (null != data.componenteImportoDtoList[0].annoAccertamento) {
							$("#strCompimpAnnoAccert" + primoSlot).val(data.componenteImportoDtoList[0].annoAccertamento);
						}
						if (null != data.componenteImportoDtoList[0].numeroAccertamento) {
							$("#strCompimpNumeroAccert" + primoSlot).val(data.componenteImportoDtoList[0].numeroAccertamento);
						}
						primoSlot++;
					}
				}
				if (data.componenteImportoDtoList[1] != undefined) {
					if (data.componenteImportoDtoList[1].flagComponenteSecondario) {
						$("#compimpId0Secondario").val(data.componenteImportoDtoList[1].id);
						$("#strCompimpImporto0Secondario").val(data.componenteImportoDtoList[1].importo);
						$("#compimpCausale0Secondario").val(data.componenteImportoDtoList[1].causale);
					} else {
						$("#compimpId" + primoSlot).val(data.componenteImportoDtoList[1].id);
						$("#strCompimpImporto" + primoSlot).val(data.componenteImportoDtoList[1].importo);
						$("#compimpCausale" + primoSlot).val(data.componenteImportoDtoList[1].causale);
						$("#compimpDatiSpec" + primoSlot).val(data.componenteImportoDtoList[1].datiSpecificiRiscossione);
						if (null != data.componenteImportoDtoList[1].annoAccertamento) {
							$("#strCompimpAnnoAccert" + primoSlot).val(data.componenteImportoDtoList[1].annoAccertamento);
						}
						if (null != data.componenteImportoDtoList[1].numeroAccertamento) {
							$("#strCompimpNumeroAccert" + primoSlot).val(data.componenteImportoDtoList[1].numeroAccertamento);
						}
						primoSlot++;
					}
				}
				if (data.componenteImportoDtoList[2] != undefined) {
					if (data.componenteImportoDtoList[2].flagComponenteSecondario) {
						$("#compimpId0Secondario").val(data.componenteImportoDtoList[2].id);
						$("#strCompimpImporto0Secondario").val(data.componenteImportoDtoList[2].importo);
						$("#compimpCausale0Secondario").val(data.componenteImportoDtoList[2].causale);
					} else {
						$("#compimpId" + primoSlot).val(data.componenteImportoDtoList[2].id);
						$("#strCompimpImporto" + primoSlot).val(data.componenteImportoDtoList[2].importo);
						$("#compimpCausale" + primoSlot).val(data.componenteImportoDtoList[2].causale);
						$("#compimpDatiSpec" + primoSlot).val(data.componenteImportoDtoList[2].datiSpecificiRiscossione);
						if (null != data.componenteImportoDtoList[2].annoAccertamento) {
							$("#strCompimpAnnoAccert" + primoSlot).val(data.componenteImportoDtoList[2].annoAccertamento);
						}
						if (null != data.componenteImportoDtoList[2].numeroAccertamento) {
							$("#strCompimpNumeroAccert" + primoSlot).val(data.componenteImportoDtoList[2].numeroAccertamento);
						}
						primoSlot++;
					}
				}
				if (data.componenteImportoDtoList[3] != undefined) {
					if (data.componenteImportoDtoList[3].flagComponenteSecondario) {
						$("#compimpId0Secondario").val(data.componenteImportoDtoList[3].id);
						$("#strCompimpImporto0Secondario").val(data.componenteImportoDtoList[3].importo);
						$("#compimpCausale0Secondario").val(data.componenteImportoDtoList[3].causale);
					} else {
						$("#compimpId" + primoSlot).val(data.componenteImportoDtoList[3].id);
						$("#strCompimpImporto" + primoSlot).val(data.componenteImportoDtoList[3].importo);
						$("#compimpCausale" + primoSlot).val(data.componenteImportoDtoList[3].causale);
						$("#compimpDatiSpec" + primoSlot).val(data.componenteImportoDtoList[3].datiSpecificiRiscossione);
						if (null != data.componenteImportoDtoList[3].annoAccertamento) {
							$("#strCompimpAnnoAccert" + primoSlot).val(data.componenteImportoDtoList[3].annoAccertamento);
						}
						if (null != data.componenteImportoDtoList[3].numeroAccertamento) {
							$("#strCompimpNumeroAccert" + primoSlot).val(data.componenteImportoDtoList[3].numeroAccertamento);
						}
						primoSlot++;
					}
				}
				if (data.componenteImportoDtoList[4] != undefined) {
					if (data.componenteImportoDtoList[4].flagComponenteSecondario) {
						$("#compimpId0Secondario").val(data.componenteImportoDtoList[4].id);
						$("#strCompimpImporto0Secondario").val(data.componenteImportoDtoList[4].importo);
						$("#compimpCausale0Secondario").val(data.componenteImportoDtoList[4].causale);
					} else {
						$("#compimpId" + primoSlot).val(data.componenteImportoDtoList[4].id);
						$("#strCompimpImporto" + primoSlot).val(data.componenteImportoDtoList[4].importo);
						$("#compimpCausale" + primoSlot).val(data.componenteImportoDtoList[4].causale);
						$("#compimpDatiSpec" + primoSlot).val(data.componenteImportoDtoList[4].datiSpecificiRiscossione);
						if (null != data.componenteImportoDtoList[4].annoAccertamento) {
							$("#strCompimpAnnoAccert" + primoSlot).val(data.componenteImportoDtoList[4].annoAccertamento);
						}
						if (null != data.componenteImportoDtoList[4].numeroAccertamento) {
							$("#strCompimpNumeroAccert" + primoSlot).val(data.componenteImportoDtoList[4].numeroAccertamento);
						}
						primoSlot++;
					}
				}
				if (data.riferimentoPagamentoDtoList[0] != undefined) {
					$("#rifpagId0").val(data.riferimentoPagamentoDtoList[0].id);
					$("#rifpagNome0").val(data.riferimentoPagamentoDtoList[0].nome);
					$("#rifpagValore0").val(data.riferimentoPagamentoDtoList[0].valore);
				}
				if (data.riferimentoPagamentoDtoList[1] != undefined) {
					$("#rifpagId1").val(data.riferimentoPagamentoDtoList[1].id);
					$("#rifpagNome1").val(data.riferimentoPagamentoDtoList[1].nome);
					$("#rifpagValore1").val(data.riferimentoPagamentoDtoList[1].valore);
				}
				if (data.riferimentoPagamentoDtoList[2] != undefined) {
					$("#rifpagId2").val(data.riferimentoPagamentoDtoList[2].id);
					$("#rifpagNome2").val(data.riferimentoPagamentoDtoList[2].nome);
					$("#rifpagValore2").val(data.riferimentoPagamentoDtoList[2].valore);
				}
				if (data.riferimentoPagamentoDtoList[3] != undefined) {
					$("#rifpagId3").val(data.riferimentoPagamentoDtoList[3].id);
					$("#rifpagNome3").val(data.riferimentoPagamentoDtoList[3].nome);
					$("#rifpagValore3").val(data.riferimentoPagamentoDtoList[3].valore);
				}
				if (data.riferimentoPagamentoDtoList[4] != undefined) {
					$("#rifpagId4").val(data.riferimentoPagamentoDtoList[4].id);
					$("#rifpagNome4").val(data.riferimentoPagamentoDtoList[4].nome);
					$("#rifpagValore4").val(data.riferimentoPagamentoDtoList[4].valore);
				}
				if (data.soggettoDebitore.tipologiaSoggettoEnum === "PERSONA_GIURIDICA") {
					$("#divPersonaFisica").addClass("hide");
					$("#divPersonaGiuridica").removeClass("hide");
					$("#divPersonaDatiFacoltativi").removeClass("hide");
					$("#soggettoIdTipo_id").val("G");
					$("#soggettoIdUnivocoFiscalePersonaGiuridica").val(data.soggettoDebitore.idUnivocoFiscale);
					$("#soggettoRagioneSociale").val(data.soggettoDebitore.cognomeNomeOrRagioneSociale);
					$("#soggettoIndirizzo").val(data.soggettoDebitore.indirizzo);
				} else {
					$("#divPersonaFisica").removeClass("hide");
					$("#divPersonaGiuridica").addClass("hide");
					$("#divPersonaDatiFacoltativi").removeClass("hide");
					$("#soggettoIdTipo_id").val("F");
					$("#soggettoIdUnivocoFiscalePersonaFisica").val(data.soggettoDebitore.idUnivocoFiscale);
					$("#soggettoCognome").val(data.soggettoDebitore.cognome);
					$("#soggettoNome").val(data.soggettoDebitore.nome);
				}
				$("#soggettoIndirizzo").val(data.soggettoDebitore.indirizzo);
				$("#soggettoCivico").val(data.soggettoDebitore.civico);
				$("#soggettoCap").val(data.soggettoDebitore.cap);
				$("#soggettoLocalita").val(data.soggettoDebitore.localita);
				$("#soggettoProvincia").val(data.soggettoDebitore.provincia);
				$("#soggettoNazione").val(data.soggettoDebitore.nazione);
				$("#soggettoEmailId").val(data.soggettoDebitore.email);
				const importo = parseInt ( data.importoTotale );
				const anonimo = "ANONIMO";
				if ( 0 === importo && data.soggettoDebitore.cognome.toUpperCase () === anonimo
					&& data.soggettoDebitore.nome.toUpperCase () === anonimo
					&& data.soggettoDebitore.idUnivocoFiscale.toUpperCase () === anonimo ) {
					// non disablitare
					$("#soggettoIdTipo_id").prop( "disabled", false );
					$("#soggettoIdUnivocoFiscalePersonaFisica").prop( "disabled", false );
					$("#soggettoCognome").prop( "disabled", false );
					$("#soggettoNome").prop( "disabled", false );
					$("#soggettoIndirizzo").prop( "disabled", false );
					$("#soggettoCivico").prop( "disabled", false );
					$("#soggettoCap").prop( "disabled", false );
					$("#soggettoLocalita").prop( "disabled", false );
					$("#soggettoProvincia").prop( "disabled", false );
					$("#soggettoNazione").prop( "disabled", false );
					$("#soggettoEmailId").prop( "disabled", false );
				} else { // disabilita
					$("#soggettoIdTipo_id").prop( "disabled", true );
					$("#soggettoIdUnivocoFiscalePersonaFisica").prop( "disabled", true );
					$("#soggettoCognome").prop( "disabled", true );
					$("#soggettoNome").prop( "disabled", true );
					$("#soggettoIndirizzo").prop( "disabled", true );
					$("#soggettoCivico").prop( "disabled", true );
					$("#soggettoCap").prop( "disabled", true );
					$("#soggettoLocalita").prop( "disabled", true );
					$("#soggettoProvincia").prop( "disabled", true );
					$("#soggettoNazione").prop( "disabled", true );
					$("#soggettoEmailId").prop( "disabled", true );
				}
			},
			error: function (data) {
				errCarica(0, data);
			}
		});
	}

	function errCarica(errorType, data) {
		console.log ("Error on fetch");
		switch (errorType) {
			case 0:
				document.getElementById("s-nf-cov").textContent = $("input[name='idCodVersamento']").val() + " - " + data.codDescription;
				$("#s-nf").removeClass("hide");
				break;
			case 1:
				let stato = "Non trovato";
				if (undefined !== data.stato) {
					stato = data.stato;
				}
				document.getElementById("s-nu-stato").textContent = stato;
				$("#s-nu").removeClass("hide");
				break;
		}
		$("#div_motivazione").addClass("hide");
		$("#div1AggiornamentoModifica").addClass("hide");
		$("#div2AggiornamentoModifica").addClass("hide");
		$("#div3AggiornamentoModifica").addClass("hide");
		$("#div4AggiornamentoModifica").addClass("hide");
		$("#div5AggiornamentoModifica").addClass("hide");
		$("#salvaAction").addClass("hide");
	}

	const radioAggiornamentoIdTipo = $("input[name='aggiornamentoIdTipo']");
	const aggiornamentoChecked = radioAggiornamentoIdTipo.filter(":checked").val();
	if (aggiornamentoChecked === 'A') {
		hideAggiornamentoModifica();
	}
	else {
		hideAggiornamentoAnnullamento();
	}
	
	
	function hideSoggetto(tipoPersonaSelected) {
		
		if (tipoPersonaSelected == 'F')
			hidePersonaGiuridica();
		else if (tipoPersonaSelected == 'G')
			hidePersonaFisica();
		else
			{
				$("#divPersonaFisica").addClass("hide");
				$("#divPersonaGiuridica").addClass("hide");
				$("#divPersonaDatiFacoltativi").addClass("hide");
			}
	
	}
	
	function hidePersonaFisica() {
		$("#divPersonaFisica").addClass("hide");
		$("#divPersonaGiuridica").removeClass("hide");
		$("#divPersonaDatiFacoltativi").removeClass("hide");
		
	}
	function hidePersonaGiuridica() {
		$("#divPersonaFisica").removeClass("hide");
		$("#divPersonaGiuridica").addClass("hide");
		$("#divPersonaDatiFacoltativi").removeClass("hide");
	}
	
	hideSoggetto($("#soggettoIdTipo_id").find(":selected").val()) ;
	
	
	$("#soggettoIdTipo_id").change(function() {
	});
	// messaggio di esito di una elaborazione
	if ($("#esitoElaborazione").val().length > 0) {
		displayAlert($("#messaggioEsitoElaborazione").val(), $("#esitoElaborazione").val());
	}
})
