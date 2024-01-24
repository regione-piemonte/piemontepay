<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="isCategoryEnabled('AGGIORNA_POSDEB')">
	<div class="btnIndexInt">
		<div class="iconBoxModuli" title="">
			<div class="TitleBox">aggiornamento posizioni debitorie</div>
		</div>
		<div class="txtDefModuliInt txtWhite">
			<s:if test="isUseCaseEnabled('INS_AGPD')">
				<s:a action="entry-inserisci-flusso-posizionidebitoriedaaggiornare-da-file-step1">
					<p>
						<span class="bullPointModuli"></span><span class="new-acc-black">inserisci da file</span>
					</p>
					<s:param name="init" value="true" />
				</s:a>
				<p class="DivisBoxModuli"></p>
			</s:if>
			<s:if test="isUseCaseEnabled('INS_AGPD')">
				<s:a action="entry-visualizza-flusso-posizionidebitoriedaaggiornare">
					<p>
						<span class="bullPointModuli"></span><span class="new-acc-black">inserisci</span>
					</p>
					<s:param name="init" value="true" />
					<s:param name="origineHomePerInserimento" value="true" />
					<s:param name="operativita" value="@it.csi.epay.epaypaweb.enumeration.TipoOperativitaEnum@INSERISCI" />
				</s:a>
				<p class="DivisBoxModuli"></p>
			</s:if>
			<s:if test="isUseCaseEnabled('RIC_AGPD')">
				<s:a action="entry-ricerca-listeaggiornamentoposizionidebitorie">
					<p>
						<span class="bullPointModuli"></span><span class="new-acc-black">ricerca flussi</span>
					</p>
					<s:param name="init" value="true" />
					<s:param name="origineHomePerInserimento" value="false" />
				</s:a>
			</s:if>
		</div>
	</div>
</s:if>
