<#ftl encoding="utf-8">
<#include "settings.ftl">

<html>

<head>
<#include "styles.ftl">
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>

<body>

	<p>Segnalazioni rilevate alle ore ${.now?time} del giorno ${.now?date}</p>

<#if activateAMQEvent>
	<h2>Il servizio ActiveMQ &egrave; nuovamente disponibile.</h2>
</#if>
<#if deactivateAMQEvent>
	<h2>Impossibile ottenere le info ActiveMQ</h2>
	<p>A causa di un errore non &egrave; stato possibile collegarsi ad ActiveMQ per ottenere le informazioni sullo stato delle code.</p>
	<p>Controllare lo stato del servizio ActiveMQ.</p>
</#if>

<#if queues??>
	<#list queues as queue>
		<h2>Coda richieste ${queue.status.name}</h2>
		<#if queue.enabledEvent>
			<p><b>Il Message Processor WSO2 relativo alla coda &egrave; stato RIATTIVATO.</b></p>
		</#if>
		<#if queue.disabledEvent>
			<p><b>Il Message Processor WSO2 relativo alla coda risulta DISATTIVATO.</b></p>
		</#if>
		<#if queue.underThresholdEvent>
			<p>Il numero di messaggi da elaborare &egrave; tornato sotto la soglia di allerta impostata a ${queue.sizeThreshold} </p>
		</#if>
		<#if queue.overThresholdEvent>
			<p>Il numero di messaggi da elaborare ha superato la soglia di allerta impostata a ${queue.sizeThreshold} </p>
		</#if>
		<table>
		  <thead>
		    <tr>
		      <th>Messaggi in attesa</th>
		      <th>Messaggi accodati</th>
		      <th>Messaggi elaborati</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr class="even">
		      <td class="right">${queue.status.queueSize}</td>
		      <td class="right">${queue.status.enqueueCount}</td>
		      <td class="right">${queue.status.dequeueCount}</td>
		    </tr>
		  </tbody>
		</table>
		
	</#list>
</#if>
	
	<p><small>Questo messaggio &egrave; stato generato automaticamente.</small></p>

</body>
</html>
