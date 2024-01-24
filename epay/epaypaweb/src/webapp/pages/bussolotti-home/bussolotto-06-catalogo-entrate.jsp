<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="(isUseCaseEnabled('MODIFICA_ENTE') and isUseCaseEnabled('AMMINISTRATORE_ENTE')) or isUseCaseEnabled('RICERCA_VOCE_ENTRATA') or isUseCaseEnabled('RICERCA_CODICE_VERSAMENTO') or isUseCaseEnabled('RICERCA_RIFERIMENTO_CONTABILE') or isUseCaseEnabled('RIC_TASSONOMIA')">
	<div class="btnIndexInt">
		<div class="iconBoxCata" title="">
			<div class="TitleBox">catalogo entrate</div>
		</div>
		<div class="txtDefCata txtWhite">
		
			<s:if test="isUseCaseEnabled('MODIFICA_ENTE') and isUseCaseEnabled('AMMINISTRATORE_ENTE')">
				<a href='/epaypacatalogweb/enti/modifica?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span><span class="new-acc-black">ente</span>
					</p>
				</a>
				<p class="divisBoxModuliCata"></p>
			</s:if>
			
			<s:if test="isUseCaseEnabled('RICERCA_VOCE_ENTRATA')">
				<a href='/epaypacatalogweb/voci-entrata/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span> <span class="new-acc-black">catalogo</span><span class="new-acc-black"> voci ppay</span>
					</p>
				</a>
				<p class="divisBoxModuliCata"></p>
			</s:if>

			<s:if test="isUseCaseEnabled('RICERCA_CODICE_VERSAMENTO')">
				<a href='/epaypacatalogweb/codici-versamento/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span> <span class="new-acc-black">codici</span><span class="new-acc-black"> versamento</span>
					</p>
				</a>
				<p class="divisBoxModuliCata"></p>
			</s:if>
			
			<s:if test="isUseCaseEnabled('RICERCA_RIFERIMENTO_CONTABILE')">
				<a href='/epaypacatalogweb/riferimenti-contabili/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span> <span class="new-acc-black">riferimenti</span><span class="new-acc-black"> contabili</span>
					</p>
				</a>
				<p class="divisBoxModuliCata"></p>
			</s:if>
			
			<s:if test="isUseCaseEnabled('RIC_TASSONOMIA')">
				<a href='/epaypacatalogweb/tassonomia-pagopa/ricerca?CF_ENTE=<s:property value="cfEnte"/>'>
					<p>
						<span class="bullPointModuliCata"></span> <span class="new-acc-black">tassonomia</span><span class="new-acc-black"> pagopa</span>
					</p>
				</a>
			</s:if>
		</div>
	</div>
</s:if>

