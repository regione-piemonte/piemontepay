<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="isCategoryEnabled('AVVISI_SCADUTI')">
	<div class="btnIndexInt">
		<div class="iconBoxRisultati" title="">
			<div class="TitleBox">avvisi scaduti</div>
		</div>
		<div class="txtGestRisultatiInt txtWhite">
			<s:if test="isUseCaseEnabled('RIC_AS')">
				<s:a action="entry-ricerca-flussi-avvisiscaduti">
					<p>
						<span class="bullPointRisultati"></span><span class="new-acc-black">ricerca flussi</span>
					</p>
					<s:param name="init" value="true" />
				</s:a>
			</s:if>
		</div>
	</div>
</s:if>

