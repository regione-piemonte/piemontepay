<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<%@taglib prefix="s" uri="/struts-tags" %>

	
<div id="mainMenu">
	<h3>Menu di navigazione</h3>
	<ul>
		
			
<s:if test="isMenuVisible('mnuUno')">
	<s:if test="isAtLeastOneSubMenuVisible('mnuUno')">
	
		<li class="parent<s:property value="%{(isMenuActive('mnuUno') || isSubMenuActive('mnuUno')) ? ' active' : ''}" />"<s:if test="isMenuActive('mnuUno')"> id="current"</s:if>><span><s:text name="mnuUno.label" /></span>
			<ul>
				

<s:if test="isMenuVisible('mnuApplicazioni')">
	<s:if test="isMenuEnabled('mnuApplicazioni')">
		<s:url id="targetUrlMnuApplicazioni" action="goToMnuApplicazioniFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuApplicazioni')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuApplicazioni}" id="menu_items_mnuApplicazioni">
				<s:text name="mnuApplicazioni.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuApplicazioni.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuGWModApp')">
	<s:if test="isMenuEnabled('mnuGWModApp')">
		<s:url id="targetUrlMnuGWModApp" action="goToMnuGWModAppFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuGWModApp')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuGWModApp}" id="menu_items_mnuGWModApp">
				<s:text name="mnuGWModApp.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuGWModApp.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuEnti')">
	<s:if test="isMenuEnabled('mnuEnti')">
		<s:url id="targetUrlMnuEnti" action="goToMnuEntiFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuEnti')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuEnti}" id="menu_items_mnuEnti">
				<s:text name="mnuEnti.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuEnti.label" /></li>
	</s:else>
	

</s:if>



			</ul>
		</li>
	
	</s:if>
	<s:else>
	
		<li><s:text name="mnuUno.label" /></li>
	
	</s:else>
</s:if>

		
		
			
<s:if test="isMenuVisible('mnuDue')">
	<s:if test="isAtLeastOneSubMenuVisible('mnuDue')">
	
		<li class="parent<s:property value="%{(isMenuActive('mnuDue') || isSubMenuActive('mnuDue')) ? ' active' : ''}" />"<s:if test="isMenuActive('mnuDue')"> id="current"</s:if>><span><s:text name="mnuDue.label" /></span>
			<ul>
				

<s:if test="isMenuVisible('mnuTransazioni')">
	<s:if test="isMenuEnabled('mnuTransazioni')">
		<s:url id="targetUrlMnuTransazioni" action="goToMnuTransazioniFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuTransazioni')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuTransazioni}" id="menu_items_mnuTransazioni">
				<s:text name="mnuTransazioni.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuTransazioni.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuStatistiche')">
	<s:if test="isMenuEnabled('mnuStatistiche')">
		<s:url id="targetUrlMnuStatistiche" action="goToMnuStatisticheFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuStatistiche')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuStatistiche}" id="menu_items_mnuStatistiche">
				<s:text name="mnuStatistiche.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuStatistiche.label" /></li>
	</s:else>
	

</s:if>



			</ul>
		</li>
	
	</s:if>
	<s:else>
	
		<li><s:text name="mnuDue.label" /></li>
	
	</s:else>
</s:if>

		
		
			
<s:if test="isMenuVisible('mnuTre')">
	<s:if test="isAtLeastOneSubMenuVisible('mnuTre')">
	
		<li class="parent<s:property value="%{(isMenuActive('mnuTre') || isSubMenuActive('mnuTre')) ? ' active' : ''}" />"<s:if test="isMenuActive('mnuTre')"> id="current"</s:if>><span><s:text name="mnuTre.label" /></span>
			<ul>
				

<s:if test="isMenuVisible('mnuAuditing')">
	<s:if test="isMenuEnabled('mnuAuditing')">
		<s:url id="targetUrlMnuAuditing" action="goToMnuAuditingFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuAuditing')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuAuditing}" id="menu_items_mnuAuditing">
				<s:text name="mnuAuditing.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuAuditing.label" /></li>
	</s:else>
	

</s:if>



			</ul>
		</li>
	
	</s:if>
	<s:else>
	
		<li><s:text name="mnuTre.label" /></li>
	
	</s:else>
</s:if>

		
		
			
<s:if test="isMenuVisible('mnuConfigurazione')">
	<s:if test="isAtLeastOneSubMenuVisible('mnuConfigurazione')">
	
		<li class="parent<s:property value="%{(isMenuActive('mnuConfigurazione') || isSubMenuActive('mnuConfigurazione')) ? ' active' : ''}" />"<s:if test="isMenuActive('mnuConfigurazione')"> id="current"</s:if>><span><s:text name="mnuConfigurazione.label" /></span>
			<ul>
				

<s:if test="isMenuVisible('mnuGestConfig')">
	<s:if test="isMenuEnabled('mnuGestConfig')">
		<s:url id="targetUrlMnuGestConfig" action="goToMnuGestConfigFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuGestConfig')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuGestConfig}" id="menu_items_mnuGestConfig">
				<s:text name="mnuGestConfig.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuGestConfig.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuGestioneGruppi')">
	<s:if test="isMenuEnabled('mnuGestioneGruppi')">
		<s:url id="targetUrlMnuGestioneGruppi" action="goToMnuGestioneGruppiFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuGestioneGruppi')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuGestioneGruppi}" id="menu_items_mnuGestioneGruppi">
				<s:text name="mnuGestioneGruppi.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuGestioneGruppi.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuGestioneUtenti')">
	<s:if test="isMenuEnabled('mnuGestioneUtenti')">
		<s:url id="targetUrlMnuGestioneUtenti" action="goToMnuGestioneUtentiFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuGestioneUtenti')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuGestioneUtenti}" id="menu_items_mnuGestioneUtenti">
				<s:text name="mnuGestioneUtenti.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuGestioneUtenti.label" /></li>
	</s:else>
	

</s:if>



			</ul>
		</li>
	
	</s:if>
	<s:else>
	
		<li><s:text name="mnuConfigurazione.label" /></li>
	
	</s:else>
</s:if>

		
		
			
<s:if test="isMenuVisible('mnuStoErrori')">
	<s:if test="isAtLeastOneSubMenuVisible('mnuStoErrori')">
	
		<li class="parent<s:property value="%{(isMenuActive('mnuStoErrori') || isSubMenuActive('mnuStoErrori')) ? ' active' : ''}" />"<s:if test="isMenuActive('mnuStoErrori')"> id="current"</s:if>><span><s:text name="mnuStoErrori.label" /></span>
			<ul>
				

<s:if test="isMenuVisible('mnuStoricoErrori')">
	<s:if test="isMenuEnabled('mnuStoricoErrori')">
		<s:url id="targetUrlMnuStoricoErrori" action="goToMnuStoricoErroriFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuStoricoErrori')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuStoricoErrori}" id="menu_items_mnuStoricoErrori">
				<s:text name="mnuStoricoErrori.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuStoricoErrori.label" /></li>
	</s:else>
	

</s:if>



			</ul>
		</li>
	
	</s:if>
	<s:else>
	
		<li><s:text name="mnuStoErrori.label" /></li>
	
	</s:else>
</s:if>

		
		
			
<s:if test="isMenuVisible('mnuNodoNazionale')">
	<s:if test="isAtLeastOneSubMenuVisible('mnuNodoNazionale')">
	
		<li class="parent<s:property value="%{(isMenuActive('mnuNodoNazionale') || isSubMenuActive('mnuNodoNazionale')) ? ' active' : ''}" />"<s:if test="isMenuActive('mnuNodoNazionale')"> id="current"</s:if>><span><s:text name="mnuNodoNazionale.label" /></span>
			<ul>
				

<s:if test="isMenuVisible('mnuGiornaleEventi')">
	<s:if test="isMenuEnabled('mnuGiornaleEventi')">
		<s:url id="targetUrlMnuGiornaleEventi" action="goToMnuGiornaleEventiFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuGiornaleEventi')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuGiornaleEventi}" id="menu_items_mnuGiornaleEventi">
				<s:text name="mnuGiornaleEventi.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuGiornaleEventi.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuRichiestaPagamentoTelematico')">
	<s:if test="isMenuEnabled('mnuRichiestaPagamentoTelematico')">
		<s:url id="targetUrlMnuRichiestaPagamentoTelematico" action="goToMnuRichiestaPagamentoTelematicoFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuRichiestaPagamentoTelematico')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuRichiestaPagamentoTelematico}" id="menu_items_mnuRichiestaPagamentoTelematico">
				<s:text name="mnuRichiestaPagamentoTelematico.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuRichiestaPagamentoTelematico.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuRicevutaTelematica')">
	<s:if test="isMenuEnabled('mnuRicevutaTelematica')">
		<s:url id="targetUrlMnuRicevutaTelematica" action="goToMnuRicevutaTelematicaFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuRicevutaTelematica')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuRicevutaTelematica}" id="menu_items_mnuRicevutaTelematica">
				<s:text name="mnuRicevutaTelematica.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuRicevutaTelematica.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuInformativaPsp')">
	<s:if test="isMenuEnabled('mnuInformativaPsp')">
		<s:url id="targetUrlMnuInformativaPsp" action="goToMnuInformativaPspFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuInformativaPsp')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuInformativaPsp}" id="menu_items_mnuInformativaPsp">
				<s:text name="mnuInformativaPsp.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuInformativaPsp.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuFlussoRiversamento')">
	<s:if test="isMenuEnabled('mnuFlussoRiversamento')">
		<s:url id="targetUrlMnuFlussoRiversamento" action="goToMnuFlussoRiversamentoFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuFlussoRiversamento')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuFlussoRiversamento}" id="menu_items_mnuFlussoRiversamento">
				<s:text name="mnuFlussoRiversamento.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuFlussoRiversamento.label" /></li>
	</s:else>
	

</s:if>




<s:if test="isMenuVisible('mnuSingoloFlussoPagamento')">
	<s:if test="isMenuEnabled('mnuSingoloFlussoPagamento')">
		<s:url id="targetUrlMnuSingoloFlussoPagamento" action="goToMnuSingoloFlussoPagamentoFromMenu" includeParams="none" />
		<li<s:if test="isMenuActive('mnuSingoloFlussoPagamento')"> id="current" class="active"</s:if>>
			<s:a href="%{targetUrlMnuSingoloFlussoPagamento}" id="menu_items_mnuSingoloFlussoPagamento">
				<s:text name="mnuSingoloFlussoPagamento.label" />
			</s:a>
		</li>
	</s:if>
	<s:else>
		<li><s:text name="mnuSingoloFlussoPagamento.label" /></li>
	</s:else>
	

</s:if>



			</ul>
		</li>
	
	</s:if>
	<s:else>
	
		<li><s:text name="mnuNodoNazionale.label" /></li>
	
	</s:else>
</s:if>

		

	</ul>
</div>


