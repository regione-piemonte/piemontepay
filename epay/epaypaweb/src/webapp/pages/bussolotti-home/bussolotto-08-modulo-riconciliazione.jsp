<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="isUseCaseEnabled('RICERCA_ELABORAZIONE') or isUseCaseEnabled('RICERCA_PROVVISORIO') or isUseCaseEnabled('INSERISCI_PROVVISORI_GUI')">
	<div class="btnIndexInt">
		<div class="iconBoxMod" title="">
			<div class="TitleBox">modulo di riconciliazione</div>
		</div>
		<div class="txtDefModuliMod">
			<s:if test="isUseCaseEnabled('RICERCA_ELABORAZIONE')">
				<a href='/epaymodricweb/elaborazione/ricercaElaborazionePrecedente?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliMod"></span> <span class="new-acc-black">ricerca</span><span class="new-acc-black"> elaborazioni</span>
					</p>
				</a>
				<p class="divisBoxModuliMod"></p>
			</s:if>

			<s:if test="isUseCaseEnabled('RICERCA_PROVVISORIO') or isUseCaseEnabled('INSERISCI_PROVVISORI_GUI')"> 
				<a href='/epaymodricweb/provvisori/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliMod"></span> <span class="new-acc-black">gestione</span><span class="new-acc-black"> provvisori</span>
					</p>
				</a>
			</s:if>
			
			
		</div>
	</div>
</s:if>

