<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<script type="text/javascript">

(function(){

	$("#inputDepositCodeSearch").on("keyup change", function() {
	    var value = $(this).val().toLowerCase();

	    $(".search-match").removeClass("search-match");
	    
		if (value !== null && value !== undefined && value !== "") {
			$.each($('.checkbox-tematica'), function(i, e) {
				e = $(e);
				var codice = e.attr("data-codice");
				var container = $("#container-tematica-" + codice);
				var descElement = container.find(".descrizione-tematica");
				var textTematica = descElement.text();
				var textTematicaMatches = (textTematica.toLowerCase().indexOf(value) > -1);
				var child = $('.checkbox-codice-versamento[data-codice-padre=' + codice + ']');
				var numChildMatch = 0;
				var childMap = {};
				
				$.each(child, function(i2, c) {
					c = $(c);
					var id = c.attr("data-id");
					var containerChild = $("#container-codice-versamento-" + id);
					var descElementChild = containerChild.find(".descrizione-codice-versamento");
					var textChild = descElementChild.text();
					var textChildMatches = (textChild.toLowerCase().indexOf(value) > -1);
					
					if (textChildMatches) {
						numChildMatch ++;
					}

					childMap[id] = {
						id : id,
						container : containerChild,
						matches : textChildMatches,
						descrizione : descElementChild
					};
				});
				
				if (textTematicaMatches) {
					container.show();
					descElement.addClass("search-match");
					$.each(childMap, function(i2, c) {
						c.container.show();
						if (c.matches) {
							c.descrizione.addClass("search-match");
						}
					});
				} else if (numChildMatch > 0) {
					container.show();
					$.each(childMap, function(i2, c) {
						if (c.matches) {
							c.container.show();
							c.descrizione.addClass("search-match");
						} else {
							c.container.hide();
						}
					});
				} else {
					container.hide();
				}
			});	
		} else {
			$(".container-tematica").show();
			$(".container-codice-versamento").show();
		}
	});

	var checkboxHandlerObj = {

		syncForm : function(init, sender, parentClicked, childClicked) {
			$.each($('.checkbox-tematica'), function(i, e) {
				e = $(e);
				
				var codice = e.attr("data-codice");
				var container = $("#container-tematica-" + codice);
				var containerFooter = $("#container-tematica-" + codice + "-footer");
				var child = $('.checkbox-codice-versamento[data-codice-padre=' + codice + ']');
				var childSelected = $('.checkbox-codice-versamento[data-codice-padre=' + codice + ']:checked');
				var numChildSelected = childSelected.length;
				var selected = e.prop("checked"); 

				if (selected) {
					
					container.removeClass("selezionata-nessuno");
					container.removeClass("selezionata-parziale");
					container.addClass("selezionata-totale");
					
					child.prop("disabled", true);
					child.prop("checked", true);
					
					if (child.length > 0) {
						containerFooter.html ("Visibilit&agrave; totale concessa su questa tematica");
						
					} else {
						containerFooter.html ("Mancano codici versamento censiti a livello di Ente - Visibilit&agrave; totale concessa su questa tematica");
					}
					
					
				} else if (numChildSelected > 0) {

					container.removeClass("selezionata-nessuno");
					container.addClass("selezionata-parziale");
					container.removeClass("selezionata-totale");
					
					child.prop("disabled", false);

					containerFooter.html("Visibilit&agrave; concessa solo sui codici versamento selezionati");
					
				} else if (child.length == 0) {

					container.addClass("selezionata-nessuno");
					container.removeClass("selezionata-parziale");
					container.removeClass("selezionata-totale");
					
					child.prop("disabled", false);

					containerFooter.html("Mancano codici versamento censiti a livello di Ente");
					
				} else {

					container.addClass("selezionata-nessuno");
					container.removeClass("selezionata-parziale");
					container.removeClass("selezionata-totale");
					
					child.prop("disabled", false);

					containerFooter.html("Nessuna visibilit&agrave; concessa su questa tematica");
					
				}
			});	
		},

	    init: function() {
	    	checkboxHandlerObj.syncForm(true, null, false, false);
	    	
	        $('.checkbox-tematica').click(checkboxHandlerObj.parentClicked);
	        $('.checkbox-codice-versamento').click(checkboxHandlerObj.childClicked)
	    },
	    
	    parentClicked: function() {

	    	// marco a mano il form come 'dirty'
			$("#autorizzazioneCodiciVersamentoForm").setDirty();

			// deseleziona tutti i codici versamento figli
			$("input[data-codice-padre=" + $(this).attr("data-codice") + "]").prop("checked", false);

			// sincronizza aspetto dei vari box
	    	checkboxHandlerObj.syncForm(false, this, true, false);
	    },
	    
	    childClicked: function() {

	    	// marco a mano il form come 'dirty'
			$("#autorizzazioneCodiciVersamentoForm").setDirty();

			// sincronizza aspetto dei vari box
	    	checkboxHandlerObj.syncForm(false, this, false, true);
	    }
	
	}
	
	checkboxHandlerObj.init();

}());
		
</script>
