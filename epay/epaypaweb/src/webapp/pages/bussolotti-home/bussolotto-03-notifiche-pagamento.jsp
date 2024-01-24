<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<s:if test="isCategoryEnabled('ESITO_PAGTI')">
	<div class="btnIndexInt">
		<div class="iconBoxRisultati" title="">
			<div class="TitleBox">notifiche pagamento</div>
		</div>
		<div class="txtGestRisultatiInt txtWhite">
			<s:if test="isUseCaseEnabled('RIC_ESP')">
				<s:a action="entry-ricerca-flussi-pagamenti">
					<p>
						<span class="bullPointRisultati"></span><span class="new-acc-black">ricerca flussi</span>
					</p>
					<s:param name="init" value="true" />
				</s:a>
				<p class="DivisBoxNotifichePagamento"></p>
			</s:if>
            <!-- CSI_PAG-184 -->
<!-- 			<s:if test="isUseCaseEnabled('RIC_REV')"> -->
<!-- 				<s:a action="entry-ricerca-richieste-revoca"> -->
<!-- 					<p> -->
<!-- 						<span class="bullPointRisultati"></span><span>ricerca richieste di revoca</span> -->
<!-- 					</p> -->
<!-- 					<s:param name="init" value="true" /> -->
<!-- 				</s:a> -->
<!--                 <p class="DivisBoxNotifichePagamento"></p> -->
<!-- 			</s:if> -->
			<!-- CSI_PAG-184 -->
			<s:if test="isUseCaseEnabled('RIC_ENTI')">
				<s:a action="entry-ricerca-report-enti">
					<p>
						<span class="bullPointRisultati"></span><span class="new-acc-black">report enti</span>
					</p>
					<s:param name="init" value="true" />
				</s:a>
				<p class="DivisBoxNotifichePagamento"></p>
				<s:a action="entry-prenota-ricerca-report-enti">
					<p>
						<span class="bullPointRisultati"></span><span class="new-acc-black">prenotazione</span><span class="new-acc-black"> report enti</span>
					</p>
					<s:param name="init" value="true" />
				</s:a>
				
			</s:if>
		</div>
	</div>
</s:if>
