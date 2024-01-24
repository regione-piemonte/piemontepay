<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script type="text/javascript">

function getFunzioniAttivate() {
	var attivate = [];

	$.each($("input[name=active]"), function(i,e) {
		attivate.push($(e).attr("value"));
	});
	
	return attivate;
}

function syncForm(initial) {

	$(".category-row").show();
	$.each($(".category-row"), function(i, e) {
		e = $(e);
		if (e.find(".function-row:visible").length > 0) {
			e.show();
		} else {
			e.hide();
		}
	});

}

function listenToButtonsClick() {
	$(".function-toggle-button").unbind();
	$(".function-toggle-button").on('click', function(){

		$("#autorizzazioneFunzioniForm").setDirty();

		var toggleButton = $(this);
		var codice = toggleButton.attr("data-codice");
		var codiceCategoria = toggleButton.attr("data-codice-categoria");
		var row = $("#function-row-" + codice);
		var inputElement = $("#" + codice);
		var attivata = (inputElement.attr("name") == "active");
		var rowCategoria = $("#category-row-" + codiceCategoria + "-" + (attivata ? "attivate" : "attivabili") );
		var glyphicon = toggleButton.find(".glyphicon");
		var descrizioneAzione = toggleButton.find(".descrizione-azione");

		if (attivata) {
			toggleButton.removeClass("btn-danger");
			toggleButton.addClass("btn-success");
			row.removeClass("function-row-attivata");
			row.addClass("function-row-attivabile");
			inputElement.attr("name", "unactive");
			glyphicon.removeClass("glyphicon-remove");
			glyphicon.addClass("glyphicon-plus");
			descrizioneAzione.html("autorizza");

			var listaDest = $("#category-row-" + codiceCategoria 
					+ "-attivabili").find(".list-group");
			listaDest.append('<li class="list-group-item function-row function-row-attivabile" id="function-row-' + codice + '">' 
					+ row.html() + "</li>");
			row.remove();
		} else {
			toggleButton.removeClass("btn-success");
			toggleButton.addClass("btn-danger");
			row.removeClass("function-row-attivabile");
			row.addClass("function-row-attivata");
			inputElement.attr("name", "active");
			glyphicon.removeClass("glyphicon-plus");
			glyphicon.addClass("glyphicon-remove");
			descrizioneAzione.html("revoca");
			
			var listaDest = $("#category-row-" + codiceCategoria 
					+ "-attivate").find(".list-group");
			listaDest.append('<li class="list-group-item function-row function-row-attivata" id="function-row-' + codice + '">' 
					+ row.html() + "</li>");
			row.remove();	
		}

		syncForm();
		listenToButtonsClick();
	});
}

$(document).ready( function() {
	
	listenToButtonsClick();
	
	$("#inputAttivate").on("change keyup", function() {
	    var value = $(this).val().toLowerCase();
	    
	    if (value !== null && value !== undefined && value !== "") {
		    $("#container-attivate .function-row").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
		    });
	    } else {
	    	$("#container-attivate .function-row").show();
		}
		
	    syncForm();
	  });
	
	$("#inputAttivabili").on("change keyup", function() {
		var value = $(this).val().toLowerCase();
	    
	    if (value !== null && value !== undefined && value !== "") {
		    $("#container-attivabili .function-row").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
		    });
	    } else {
	    	$("#container-attivabili .function-row").show();
		}
		
	    syncForm();
	  });

	$(".category-revoke-button").on('click', function(){

		var codice = $(this).attr("data-codice");
		var rowCategoria = $("#category-row-" + codice + "-attivate" );
		rowCategoria.find(".function-toggle-button").click();
	});
	  
	$(".category-grant-button").on('click', function(){

		var codice = $(this).attr("data-codice");
		var rowCategoria = $("#category-row-" + codice + "-attivabili" );
		rowCategoria.find(".function-toggle-button").click();
	});

	syncForm(true);

	function salva(skipLogin, skipAdmin) {
		var attivate = getFunzioniAttivate();
			
		if (!skipLogin) {
			if (attivate.indexOf("LOGIN") < 0) {
				return chiediConfermaProcedi("Attenzione! <br/> Non hai concesso all'utente la funzionalit&agrave; di accesso all'applicativo." +
						"<br/>L'utente non sar&agrave; in grado di accedere al sistema. <br/><br/> Desideri procedere comunque ? ", function() {
					salva(true, skipAdmin);
				});
			}
		}
		
		if (!skipAdmin) {
			if (attivate.indexOf("ADMIN") >= 0 || attivate.indexOf("AUTORIZZA_FUNZIONE") >= 0) {
				return chiediConfermaWarning("Attenzione! <br/> Stai per concedere all'utente alcune funzionalit&agrave; " +
						"di amministrazione che potrebbero consentirgli di compiere azioni indesiderate." + 
						"<br/>L'utente sar&agrave; in grado di accedere al sistema in ogni sua parte. <br/><br/> Desideri procedere comunque ? ", function() {
					salva(skipLogin, true);
				});
			}
		}

		$("#autorizzazioneFunzioniForm").submit();
	}
	
	$("#form-save-submit").on('click',function(){
		salva(false, false);
	});
	
});

</script>
