<#ftl encoding="utf-8">
<#include "settings.ftl">

<html>

<head>
	<#include "styles.ftl">
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>

<body>
	<p>Flusso estratto dallo sportello dei pagamenti alle ore ${flusso.dataInserimento?time} del giorno ${flusso.dataInserimento?date}.</p>

	<p>Il file <b>${filename}</b>, allegato alla presente e-mail,
	contiene n. <b>${flusso.numeroElementi}</b>
	notifiche di pagamento relative al codice versamento <b>${flusso.codVersamento} - ${flusso.desCodVersamento}</b>,
	per un totale complessivo di &euro; <#setting number_format=",##0.00">
	<b>${flusso.importoTotale}</b>.</p>

	<p>Questo messaggio &egrave; stato generato automaticamente.</p>
</body>
</html>
