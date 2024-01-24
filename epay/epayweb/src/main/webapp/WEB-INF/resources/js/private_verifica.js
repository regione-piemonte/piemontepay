/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

/* GESTIONE PARAMETRI DI RICERCA*/

// gestione delle date di inizio e fine ricerda
$(function() {
	var nowTemp = new Date();
	var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
	 
	var datada = $('#datada').datepicker({
		format:'dd/mm/yyyy',
		onRender: function(date) {
			return date.valueOf() > now.valueOf() ? 'disabled' : '';
		}
	}).on('changeDate', function(ev) {
		  if (ev.date.valueOf() > checkout.date.valueOf()) {
			    var newDate = new Date(ev.date)
			    newDate.setDate(newDate.getDate() + 1);
			    dataa.setValue(newDate);
			  }
	}).data('datepicker');
		
	var dataa = $('#dataa').datepicker({
		format:'dd/mm/yyyy',
		onRender: function(date) {
			return date.valueOf() < datada.date.valueOf() && date.valueOf() > now.valueOf() ? 'disabled' : '';
		}
	}).data('datepicker');
});


// funzione per gestire del pulsante premuto
function getPagamenti(mesi, urlVerifica) {
	var periodo = document.createElement('input');
	var intervalloInizio = document.createElement('input');
	var intervalloFine = document.createElement('input');
	
	periodo.name='periodo';
	intervalloInizio.name='intervalloInizio';
	intervalloFine.name='intervalloFine';
	
	switch (mesi) {
	case 0:
		periodo.value='intervallo';
		if ( $('#datada').val() === '') {
			alert('Impostare la data di inizio periodo!');
			return false;
		} 
			
		if ($('#dataa').val() === '') {
			alert('Impostare la data di fine periodo!');
			return false;
		}
		
		intervalloInizio.value=$('#datada').val();
		intervalloFine.value=$('#dataa').val();
		break;
	case 2:
		periodo.value='ultimi2mesi';
		intervalloInizio.value='';
		intervalloFine.value='';
		break;
	case 4:
		periodo.value='ultimi4mesi';
		intervalloInizio.value='';
		intervalloFine.value='';
		break;
	case 6:
		periodo.value='ultimi6mesi';
		intervalloInizio.value='';
		intervalloFine.value='';
		break;
	default:
		periodo.value='';
		intervalloInizio.value='';
		intervalloFine.value='';
		break;
	}   
	
	var form = document.createElement('form');
	form.style = 'display:none';
    form.method = 'POST';
    form.action = urlVerifica;   
	form.appendChild(periodo);  
	form.appendChild(intervalloInizio);
	form.appendChild(intervalloFine);

    document.body.appendChild(form);

    form.submit();
}

//gestione IonRange Slider (importo)
$(function() {
	var $slider = $("#sliderImporto");
	if ($slider == '') {
		return;
	} 
	
	var da =+ $slider.data('da') || 0;
	var a =+ $slider.data('a') || 0;
	
	$slider.ionRangeSlider({
		type: 'double',
		min: da,
	    max: a,
	    from: da,
	    to: a,
	    prefix: '€',
	    hide_min_max: false,
	    hide_from_to: false,
	    grid: true
	});
	
	$slider.on("change", function () {
		$('#dataTable').DataTable().draw();
	});
});



/* GESTIONE DATATABLES */

// gestione dell'impostazione del dataTables 
$(function() {
	options = {
		info : false,
		searching: true,
		dom: '<"row"<"col-sm-6"p>><"row"<"col-sm-12"tr>><"row"<"col-sm-12"p>>',
		columns : [ 
		    {
		    	type : "date-eu"
		    }, 
		    null, 
		    null, 
		    null, 
		    null,
		    {
		    	orderable : false
		    } 
		],
		order : [[0,"desc"]] 
	};
	
	$.fn.dataTable.ext.search.push(
		function doFilterTable( settings, data, index) {
			var $slider = $("#sliderImporto");
			var importi = $slider.prop("value").split(";");
			var importoMin = importi[0] ? importi[0] + '.00' : $slider.data('da');
			var importoMax = importi[1] ? importi[1] + '.00' : $slider.data('a');
			var esito = $('#esito').val();
			var ente = $('#ente').val();
			var dettaglio = $('#dettaglio').val();

			
			//Importo Minimo
			if ( !(importoMin == '' || normalizeNumber(importoMin) <= normalizeNumber(data[1])) ) {
				return false;
			}
			
			//Importo Massimo
			if ( !(importoMax == '' || normalizeNumber(importoMax) >= normalizeNumber(data[1])) ) {
				return false;
			}
						
			//Esito
			if ( !(esito == '' || esito == data[2]) ) {
				return false;
			}

			//Ente
			if ( !(ente == '' || ente == data[3]) ) {
				return false;
			}
			
			//Dettaglio
			if ( !(dettaglio == '' || dettaglio == data[4]) ) {
				return false;
			}

			return true;
		}
	);
	
	dt = $('#dataTable').DataTable(options);

	$('#esito, #ente, #dettaglio').change( function(e) {
	    e.preventDefault();
	    dt.draw();
	  });
});

function normalizeNumber(num) {
	return Number(num.replace('€','').replace(',','').replace('\.','').trim())/100 ;
}

                    
/* GESTIONE DELLE STAMPE */

//funzione per gestire i pulsanti di stampa
function printRt(urlAction, codiceFiscale, iuv, idStatoPagamento) {
	var p1 = document.createElement("input");
	p1.name="codiceFiscale";
	p1.value=codiceFiscale;
	
	var p2 = document.createElement("input");
	p2.name="iuv";
	p2.value=iuv;
	
	var p3 = document.createElement("input");
	p3.name="idStatoPagamento";
	p3.value=idStatoPagamento;
	
	var form = document.createElement("form");
	form.style = 'display:none';
	form.method = "POST";
    form.target="_blank";
	form.action = urlAction;
    form.appendChild(p1);  
	form.appendChild(p2);
	form.appendChild(p3);

    document.body.appendChild(form);

    form.submit();
}
