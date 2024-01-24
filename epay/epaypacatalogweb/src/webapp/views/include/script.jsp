<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script type="text/javascript">

	var baseLocation = "${context}/";

	showServerMessages();
	
	setupDatatablesDefault();

	$( document ).ready(function() {
		setupDataTables();
		setupExplanationPopovers();
	});

	function setupDatatablesDefault() {

		$.extend( $.fn.dataTable.defaults, {
			stateSave: true,
			stateDuration: -1,
			pageLength: 10,
			lengthMenu: [ 5, 10, 25, 50, 100 ],
			language: {
				lengthMenu: "_MENU_ record per pagina",
	            zeroRecords: "Nessun risultato trovato",
	            info: "Pagina _PAGE_ di _PAGES_",
	            infoEmpty: "Nessun risultato trovato",
		        paginate: {
		            first:    '<<',
		            previous: '<',
		            next:     '>',
		            last:     '>>'
		        },
		        aria: {
		            paginate: {
		                first:    'Prima',
		                previous: 'Precedente',
		                next:     'Successiva',
		                last:     'Ultima'
		            }
		        },
		        emptyTable : "Nessun record."
		    },
		    searching: false
		} );
	}
	
	function showServerMessages() {
		<c:if test="${not empty infoMessage}">
		mostraInfo('<c:out value="${infoMessage}" escapeXml="true" />');
		</c:if>
		
		<c:if test="${not empty errorMessage}">
		mostraErrore('<c:out value="${errorMessage}" escapeXml="true" />');
		</c:if>
	}
	
	function setupDataTables() {
		$('table[auto-data-table]').DataTable({
			/* no custom options */
		});
	}
	
	function setupExplanationPopovers() {
		$('[data-explanation]').each(function() {
			var element = $(this);
			if (!element.attr("data-toggle")) {
				element.attr("data-toggle", "popover");
			}
			if (!element.attr("data-trigger")) {
				element.attr("data-trigger", "focus");
			}
			if (!element.attr("data-content")) {
				element.attr("data-content", element.attr("data-explanation"));
			}
			if (!element.attr("data-placement")) {
				element.attr("data-placement", "bottom");
			}
			
			if (!element.attr("title")) {

				// if (false) perche' lollo non vuole il titolo della label nel popup,
				// quindi il titolo spuntera' solo se esplicitamente specificato
				// TODO: valutare se utilizzare un attributo differente,
				// tipo 'data-explanation-title' o robe simili
				
				if (false) {
					// se l'elemento e' un input, cerco la label corrispondente 
					var elementId = element.attr("id") || element.attr("name");
					if (elementId) {
						var label = $("label[for='" + elementId + "']");
						if (label) {
							// se ho trovato la label, ne uso il contenuto come titolo del popover
							element.attr( "title", label.text() );
						}
					}
				}
			}
			
			if (!element.attr("href")) {
				element.attr("href", "#");
			}
			
			element.addClass("explained");

			element.popover();
		});
	}
		
	function scrollToFirstError() {
		var elWithError = $(".error:visible");
		if (elWithError.size() > 0) {
			$([document.documentElement, document.body]).animate({
		        scrollTop: elWithError.offset().top - 50
		    }, 500);
		}
	}

	function clearPage(tableSelector) {
		var table = $(tableSelector);
		var datatable = table.DataTable({ "bRetrieve": true});
		datatable.page("first");
		datatable.state.save();
	}

</script>
