<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="isUseCaseEnabled('INSERISCI_PROVVISORI') or isUseCaseEnabled('RICERCA_PROVVISORI') or isUseCaseEnabled('RICERCA_ERRORI') or isUseCaseEnabled('RICERCA_FLUSSO') or isUseCaseEnabled('IMPOSTA_ESITO_FLUSSO')">
	<div class="btnIndexInt">
		<div class="iconBoxSim" title="">
			<div class="TitleBox">modulo contabile</div>
		</div>
		<div class="txtDefModuliSim">
			<s:if test="isUseCaseEnabled('INSERISCI_PROVVISORI')">
				<a href='/epaysimweb/provvisori/inserisci?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliSim"></span><span class="new-acc-black">inserisci provvisori</span>
					</p>
				</a>
				<p class="divisBoxModuliSim"></p>
			</s:if>

			<s:if test="isUseCaseEnabled('RICERCA_PROVVISORI')">
				<a href='/epaysimweb/provvisori/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliSim"></span> <span class="new-acc-black">ricerca provvisori</span>
					</p>
				</a>
				<p class="divisBoxModuliSim"></p>
			</s:if>

			<s:if test="isUseCaseEnabled('RICERCA_ERRORI')">
				<a href='/epaysimweb/errori/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliSim"></span> <span class="new-acc-black">ricerca flussi in errore</span>
					</p>
				</a>
				<p class="divisBoxModuliSim"></p>
			</s:if>
			
			<s:if test="isUseCaseEnabled('RICERCA_FLUSSO') or isUseCaseEnabled('IMPOSTA_ESITO_FLUSSO')">
				<a href='/epaysimweb/flussi/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliSim"></span> <span class="new-acc-black">ricerca flussi ok e imposta esito</span>
					</p>
				</a>
			</s:if>
		</div>
	</div>
</s:if>

