<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<div class="step-content">
	<div class="container-fluid" style="margin-bottom: 1em; margin-top: 1em;">
		<div class="row">
			<div class="col-sm-12">
				<h4>
					<spring:message code="breadcrumbs.autorizzazionicodiciversamento" />
				</h4>
			</div>
		</div>
	</div>
	<div class="container-fluid" style="margin-bottom: 1em;">
		<div class="row">
			<div class="col-sm-12">
				<input class="form-control input-filtro-codice-versamento" id="inputDepositCodeSearch" type="text" 
					placeholder='ricerca nei codici versamento' 
				/>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<c:forEach var="tematica" items="${albero_tematiche}" varStatus="i" >
			<div class="row">
				<div class="col-sm-12">
					<div class="panel container-tematica" id="container-tematica-${tematica.codice}" >
						<div class="panel-heading">
							<input type="hidden" name="associazioni[${i.index}].codice" value="${tematica.codice}" />
							<input 
								data-codice="${tematica.codice}"
								type="checkbox" 
								id="checkbox-tematica-${tematica.codice}" 
								name="associazioni[${i.index}].flagVisibilitaTotale" 
								class="parent checkbox-tematica" 
								<c:if test="${tematica.flagVisibilitaTotale}"> checked</c:if>
							/>
		   					<h3 class="panel-title descrizione-tematica">
								<c:out value="${tematica.codice} - ${tematica.descrizione}" />
		   					</h3>
		  				</div>
						<div class="panel-body" ${ empty tematica.codiciVersamento ? "style='display: none;'" : "" }>
							<c:forEach var="codiceVersamento" items="${ tematica.codiciVersamento }" varStatus="j" >
								<span id="container-codice-versamento-${codiceVersamento.id}"
									class="container-codice-versamento"
								>
									<!-- child -->
									<input type="hidden" name="associazioni[${i.index}].codiciVersamento[${j.index}].id" value="${codiceVersamento.id}"  />
									<input 
										data-codice-padre="${tematica.codice}"
										data-id="${codiceVersamento.id}"
										type="checkbox" 
										name='associazioni[${i.index}].codiciVersamento[${j.index}].selezionato' 
										class="parent-${i.index} checkbox-codice-versamento" 
										id="checkbox-codice-versamento-${codiceVersamento.id}" 
										<c:if test="${codiceVersamento.selezionato}">checked</c:if> 
									/>
									<span class="descrizione-codice-versamento">
										<c:out value="${codiceVersamento.codice} - ${codiceVersamento.descrizione}" />
									</span>
									<br>
								</span>
							</c:forEach>
						</div>
						<div class="panel-footer" id="container-tematica-${tematica.codice}-footer">
						<!-- riempito dinamicamente-->
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
