<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="(isUseCaseEnabled('RICERCA_UTENTE') or isUseCaseEnabled('AUTORIZZA_CODICE_VERSAMENTO') or isUseCaseEnabled('AUTORIZZA_FUNZIONE')) and isUseCaseEnabled('AMMINISTRATORE_ENTE')">
	<div class="btnIndexInt">
		<div class="iconBoxUsrCata" title="">
			<div class="TitleBox">profilazione utente</div>
		</div>
		<div class="txtDefCata txtWhite">
			<s:if test="isUseCaseEnabled('RICERCA_UTENTE')">
				<a href='/epaypacatalogweb/utenti/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span><span class="new-acc-black">utente</span>
					</p>
				</a>
				<p class="divisBoxModuliCata"></p>
			</s:if>

			<s:if test="isUseCaseEnabled('AUTORIZZA_CODICE_VERSAMENTO')">
				<a href='/epaypacatalogweb/autorizzazione-codici-versamento/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span> <span class="new-acc-black">aut.</span><span class="new-acc-black"> cod. versamento</span>
					</p>
				</a>
				<p class="divisBoxModuliCata"></p>
			</s:if>

			<s:if test="isUseCaseEnabled('AUTORIZZA_FUNZIONE')">
				<a href='/epaypacatalogweb/autorizzazione-funzioni/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span> <span class="new-acc-black">aut.</span><span class="new-acc-black"> funzione</span>
					</p>
				</a>
			</s:if>
		</div>
	</div>
</s:if>
