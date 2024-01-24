<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="urlPagamentoSpontaneo" value="/ajax/pagamentiSpontanei" />
<c:url var="urlPagamentoConIuv" value="/ajax/pagamentiConIuv" />

<title>Piemontepay</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3.0, user-scalable=yes" />
<meta name="author" content="CSI-Piemonte" />
<meta name="description" content="Sistema Piemonte il portale per cittadini ed imprese" />
<meta name="keywords" content="piemonte, servizi, cittadini, imprese, sistema, amministrazione" />

<link href="${risorseStatiche}/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />

<link rel="stylesheet" type="text/css" href="${risorseStatiche}/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/bootstrap-yeti-accessibilita.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/google_fonts_imported.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/fonts/fontawesome-free-5.13.0-web/css/all.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/bootstrap/css/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/DataTables-1.10.12/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${risorseStatiche}/css/flag-icon/css/flag-icon.min.css" />

<script type="text/javascript" src="${risorseStatiche}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${risorseStatiche}/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${risorseStatiche}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${risorseStatiche}/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${risorseStatiche}/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${risorseStatiche}/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${risorseStatiche}/DataTables-1.10.12/js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
		var pagamentiSpontanei = '#pagamentiSpontanei';
		var pagamentiConIuv = '#pagamentiConIuv';

		$.ajax({
			type : "GET",
			url : "${urlPagamentoSpontaneo}",
			dataType : 'json',
			async : false,
			success : function(data) {
				$(pagamentiSpontanei).append(
						"<option value=''>-- Seleziona ente --</option>");
				$.each(data, function(index, ente) {
					$(pagamentiSpontanei).append(
							'<option value="' + ente.nome + '">' + ente.nome
									+ '</option>');
				});

			},
			error : function(xhr, status, error) {
				alert('Si e verificato un errore.');
			}
		});

		$.ajax({
			type : "GET",
			url : "${urlPagamentoConIuv}",
			dataType : 'json',
			async : false,
			success : function(data) {
				$(pagamentiConIuv).append(
						"<option value=''>-- Seleziona ente --</option>");
				$.each(data, function(index, ente) {
					$(pagamentiConIuv).append(
							'<option value="' + ente.nome + '">' + ente.nome
									+ '</option>');
				});
			},
			error : function(xhr, status, error) {
				alert('Si e verificato un errore.');
			}
		});
	});
</script>
<!-- Matomo Tracking Code -->
<script type="text/plain" data-consent="piwik">
      var _paq = window._paq = window._paq || [];
      / tracker methods like "setCustomDimension" should be called before "trackPageView" /
      _paq.push(['trackPageView']);
      _paq.push(['enableLinkTracking']);
      (function() {
        var u="https://ingestion.webanalytics.italia.it/";
        _paq.push(['setTrackerUrl', u+'matomo.php']);
        _paq.push(['setSiteId', 'wGpbKwAqYb']);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.src=u+'matomo.js'; s.parentNode.insertBefore(g,s);
      })();
</script>
<!-- End Matomo Code -->
