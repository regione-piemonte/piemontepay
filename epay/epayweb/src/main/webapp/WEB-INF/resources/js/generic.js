/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

$(function($) {

	$("a.offcanvas-toggle").click(function() {
		var opened = $("#offcanvas-sidebar").hasClass("on");
		console.log(opened);
		if (opened) {

			$("#offcanvas-sidebar").animate({
				"left" : '-400px'
			}, 500, function() {
				$(this).removeClass("on");
				$(this).addClass("collapse");
				$(this).hide();
			});

			$(this).find("i.fa").removeClass("fa-times").addClass("fa-bars");
			console.log("Closed!");
		} else {
			$("#offcanvas-sidebar").removeClass("collapse");
			$("#offcanvas-sidebar").show().animate({
				"left" : '-15px'
			}, 500, function() {
				$(this).addClass("on");
			});
			$(this).find("i.fa").removeClass("fa-bars").addClass("fa-times");
			console.log("Opened!");
		}
	});

	//DATATABLES i18n
	
	var languageSet = {
		emptyTable : 'Nessun dato presente.',
		//info : "Vista da _START_ a _END_ di _TOTAL_ elementi",
		info : 'Hai _MAX_. Visualizzati dal _START_ al _END_.',
		infoEmpty : "Nessun dato",
		//infoFiltered : "(filtrati da _MAX_ elementi totali)",
		infoFiltered : ' (_TOTAL_ corrispondenti alla ricerca)',
		infoPostFix : "",
		infoThousands : ".",
		lengthMenu : "Visualizza _MENU_ elementi",
		//loadingRecords : "Caricamento...",
		loadingRecords : 'Un attimo di pazienza - sto caricando i dati...',
		processing : "Elaborazione...",
		search : "Cerca:",
		zeroRecords : "La ricerca non ha portato alcun risultato.",
		paginate : {
			first : "Inizio",
			previous : '&laquo;',
			next : '&raquo;',
			last : "Fine"
		},
		aria : {
			sortAscending : ": attiva per ordinare la colonna in ordine crescente",
			sortDescending : ": attiva per ordinare la colonna in ordine decrescente"
		}
	}

	//DATATABLES Setting defaults
	$.extend(
		$.fn.dataTable.defaults,
			{
				renderer : 'bootstrap',
				dom : '<"row"<"col-sm-12 risultati-tabella"i>>'
						+ '<"row"<"col-sm-6"p><"col-sm-6"f>>'
						+ '<"row"<"col-sm-12"tr>>'
						+ '<"row"<"col-sm-12"p>>',
				pageLength : 5,
				pagingType : 'simple_numbers',

				language : languageSet
			});
});

function stampaStreaming(urlDataRequest) {
	$.get( 
		urlDataRequest,
		function(data) {
			var thePopup = window.open( '', "Stampa", "menubar=0,location=0,height=10,width=10" );
			$(thePopup.document.body).text(data);
		 	thePopup.print();
			thePopup.close();
		}
	);
}

(function($) {
	  $.fn.inputFilter = function(inputFilter) {
	    return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function() {
	      if (inputFilter(this.value)) {
	        this.oldValue = this.value;
	        this.oldSelectionStart = this.selectionStart;
	        this.oldSelectionEnd = this.selectionEnd;
	      } else if (this.hasOwnProperty("oldValue")) {
	        this.value = this.oldValue;
	        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
	      }
	    });
	  };
	}(jQuery));