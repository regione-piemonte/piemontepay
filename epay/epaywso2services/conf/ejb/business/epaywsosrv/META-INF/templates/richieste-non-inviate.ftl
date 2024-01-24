<#ftl encoding="utf-8">
<#include "settings.ftl">

<html>

<head>
	<#include "styles.ftl">
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
</head>

<body>
	<p>Segnalazioni rilevate alle ore ${.now?time} del giorno ${.now?date}</p>

	<h2>Richieste in attesa di essere elaborate</h2>

	<table>
		<thead>
			<tr>
		      <th>Id</th>
		      <th>Tipo richiesta</th>
		      <th>Ente</th>
		      <th>Riferimenti dell'operazione</th>
		      <th>Ultima variazione</th>
		      <th>Esito</th>
		      <th>Stato</th>
			</tr>
		</thead>	  
		<tbody>
			<#if elencoRichieste??>
				<#list elencoRichieste as richiesta>
					<#assign trCss = (richiesta_index % 2 == 0)?string("even","odd")>
					<tr class="${trCss}">
						<td class="right">
							${richiesta.id?c}
						</td>
						<td>
							${richiesta.tipoRichiestaEnum.name()?replace("_", " ")?lower_case}
						</td>
						<td>
							<@escapeHtmlEntity>${richiesta.denominazioneEnte}</@escapeHtmlEntity>
						</td>
						<td>
							<b>Numero lotto:</b> ${(richiesta.idMessaggio)!"ASSENTE"}. <b>Codice versamento:</b> ${(richiesta.codVersamento)!"ASSENTE"} 
						</td>
						<td>
							<#if richiesta.dataUltimaVariazione??>
								${richiesta.dataUltimaVariazione?string('dd/MM/yyyy HH:mm:ss')}
							</#if>
						</td>
						<td>
							${(richiesta.esitoRichiestaDto.cod)!}
							<#if (richiesta.esitoRichiestaDto.det)??>
								-
								<@escapeHtmlEntity>${richiesta.esitoRichiestaDto.det}</@escapeHtmlEntity>
							</#if>
						</td>
						<td>
							${richiesta.statoRichiestaEnum.name()?replace("_", " ")?lower_case}
						</td>
					</tr>
				</#list>
			</#if>
		</tbody>
	</table>

	<p>Questo messaggio &egrave; stato generato automaticamente.</p>
</body>
</html>
