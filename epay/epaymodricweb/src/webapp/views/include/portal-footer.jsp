<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div id="portalFooter">
    <div class="col-sm-8">
             
    </div>
    <div class="col-sm-4">
              <p>
               <a href="https://www.regione.piemonte.it/web" target="_blank">
               <img alt="Regione Piemonte" src="/epaymodricweb/ris/css/im/fesr.jpg" title="Regione Piemonte"></a>
             </p>
    </div>
</div>


<script type="text/javascript">
	var baseLocation = "${context}/";

	
	
	<c:if test="${not empty infoMessage}">
		mostraInfo('<c:out value="${infoMessage}" escapeXml="true" />');
	</c:if>
	
	<c:if test="${not empty errorMessage}">
		mostraErrore('<c:out value="${errorMessage}" escapeXml="true" />');
	</c:if>

	$.extend( $.fn.dataTable.defaults, {
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
	        }
	    },
	    searching: false
	} );

	
	$( document ).ready(function() {
		$('table[auto-data-table]').DataTable({});
				
	});

	

	
	
</script>
