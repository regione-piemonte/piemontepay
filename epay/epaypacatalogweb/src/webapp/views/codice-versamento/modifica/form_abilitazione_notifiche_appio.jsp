<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="2"/>
<c:set var="w01" value="10"/>
<c:set var="w04" value="4"/>
<c:set var="w05" value="2"/>

                                
 <c:choose>
    <c:when test="${modifica_codice_versamento.codiceTipoPagamento.equals('LDC') or
                     modifica_codice_versamento.codiceTipoPagamento.equals('REDS') or
                      modifica_codice_versamento.codiceTipoPagamento.equals('PABL') or
                     modifica_codice_versamento.codiceTipoPagamento.equals('REDI')}">
                                      

	<sec:authorize access="hasRole('ASSISTENZA')">
	 <h4><spring:message code="index.abilitazione.appio"/></h4>
	<div class="container-fluid">
     <div class="row-fluid">
                                        
                                        
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="form-group " style="height: 20px;">
					<label class="col-sm-2 control-label" style="text-align: left" for="flagPersonalizzazioneCov">
						<spring:message code="codiceversamento.flagPersonalizzazioneCov"/></label>
					<div class="col-sm-2 controls">
						<sec:authorize access="hasRole('ASSISTENZA')">
							<form:checkbox class="checkbox-inline" path="flagPersonalizzazioneCov" id="flagPersonalizzazioneCov"/>
						</sec:authorize>
	
						<sec:authorize access="not (hasRole('ASSISTENZA') )">
	
							<input type="checkbox" id="flagPersonalizzazioneCovHide" class="checkbox-inline" readonly="true" disabled="true"/>
						</sec:authorize>
	
	
					</div>
					<div class="col-sm-2"></div>
	
					<sec:authorize access="not (hasRole('ASSISTENZA') )">
						<form:hidden id="flagPersonalizzazioneCov" path="flagPersonalizzazioneCov"/>
					</sec:authorize>
	
	
				</div>
			</div>
		</div>
	
		<div class="container-fluid" id="opzioniAbilitazioneNotificheId">
			<div class="row-fluid">
				<div class="form-group ">
					<label class="col-sm-2 control-label required" for="descrizioneTextCov"><spring:message code="codiceversamento.descrizioneTextCov"/></label>
					<div class="col-sm-10 controls">
	
						<sec:authorize access="hasRole('ASSISTENZA')">
							<form:input class="form-control input-descrizione-text-cov" path="descrizioneTextCov"/>
						</sec:authorize>
	
						<sec:authorize access="not (hasRole('ASSISTENZA') )">
							<form:input class="form-control input-descrizione-text-cov" path="descrizioneTextCov" readonly="true"/>
						</sec:authorize>
	
	
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<form:errors path="descrizioneTextCov" cssClass="error"/>
					</div>
				</div>
			</div>
	
	
			<sec:authorize access="hasRole('ASSISTENZA')">
				<div class="row-fluid">
					<div class="form-group ">
						<label class="col-sm-2 control-label" for="passphrase"><spring:message code="codiceversamento.passphrase"/></label>
						<div class="col-sm-10 controls">
							<form:input class="form-control input-passprase" path="passphrase"/>
						</div>
						<div class="col-sm-2"></div>
						<div class="col-sm-10">
							<form:errors path="passphrase" cssClass="error"/>
						</div>
					</div>
				</div>
			</sec:authorize>
	
		</div>
  </div>
</div>
                                       

    </sec:authorize>

<sec:authorize access="not (hasRole('ASSISTENZA') )">
	<form:hidden id="flagPersonalizzazioneCov" path="flagPersonalizzazioneCov"/>
	<form:hidden path="descrizioneTextCov" readonly="true"/>
</sec:authorize>

    </c:when>
                                       
                                   
 <c:otherwise>
 <form:hidden id="flagPersonalizzazioneCov" path="flagPersonalizzazioneCov"/>
    <form:hidden path="descrizioneTextCov" readonly="true"/>
 </c:otherwise>
                                        
</c:choose>
