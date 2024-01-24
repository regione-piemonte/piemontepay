<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="isCategoryEnabled('FLUSSI_RENDICN')">
	<div class="btnIndexInt">
		<div class="iconBoxFlussiRendicontazione" title="">
			<div class="TitleBox">riepilogo ricezione flussi</div>
		</div>
		<div class="txtGestFlussiRendicontazioneInt txtWhite">
			<s:if test="isUseCaseEnabled('RIC_FR')">
				<s:a action="entry-ricerca-flussi-rendicontazione-base">
					<p style="margin-bottom: 4px">
						<span class="bullPointFlussiRendicontazione"></span><span class="new-acc-white">ricerca flussi base</span>
					</p>
					<s:param name="init" value="true" />
				</s:a>
				<p class="divisBoxModuliFlussi"></p>
			</s:if>
			<s:if test="isUseCaseEnabled('RICERCA_FLUSSI')">
				<a href='/epaymodricweb/flussi/ricerca?CF_ENTE=<s:property value="cfEnte"/>&init=true'>
					<p style="margin-bottom: 4px">
						<span class="bullPointFlussiRendicontazione"></span><span class="new-acc-white">ricerca flussi completi / riconciliati</span>
					</p>
					<s:param name="init" value="true" />
				</a>	
				<p class="divisBoxModuliFlussi"></p>
			</s:if>
			<s:if test="isUseCaseEnabled('RIC_FR')">
				<s:a action="entry-prenota-ricerca-report-flussi-rendicontazione-base">
					<p style="margin-bottom: 4px">
						<span class="bullPointFlussiRendicontazione"></span><span class="new-acc-white">report flussi base</span>
					</p>
					<s:param name="init" value="true" />
				</s:a>
				<p class="divisBoxModuliFlussi"></p>
			</s:if>
			<s:if test="isUseCaseEnabled('RICERCA_FLUSSI')">
				<a href='/epaymodricweb/report/prenota-report-ricerca-flussi?CF_ENTE=<s:property value="cfEnte"/>&init=true'>
					<p style="margin-bottom: 4px">
						<span class="bullPointFlussiRendicontazione"></span><span class="new-acc-white">report flussi completi / riconciliati</span>
					</p>
					<s:param name="init" value="true" />
				</a>
			</s:if>
		</div>
	</div>
</s:if>

