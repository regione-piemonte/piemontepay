<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="3" />
<c:set var="w01" value="9" />

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label " for="descrizione"><spring:message code="codiceversamento.collegato.form.codicevoceentratacollegata" /></label>
			<div class="col-sm-${w01} controls">
				<div class="input-group input-group-inline">
		  			<span class="input-group-addon input-codice-prefisso" id="container-codice-codice-versamento"><c:out value='${modifica_codice_versamento_collegato.codice}' /></span>
					<form:input class="form-control input-descrizione-codice-versamento" path="descrizione" id="descrizione" readonly="true" />
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="iban"><spring:message code="iban" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-iban" path="iban" maxlength="35" readonly="true" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagCodiceCorrentePostaleTesoreria"><spring:message code="flagCodiceCorrentePostaleTesoreria" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleTesoreria" id="flagCodiceCorrentePostaleTesoreria" readonly="true" disabled="true" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="bic"><spring:message code="bic" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-bic" path="bic" maxlength="11" readonly="true" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="ibanAppoggio"><spring:message code="ibanAppoggio" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control view-uppercase input-iban" path="ibanAppoggio" maxlength="35" readonly="true"/>
			</div>
			
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="bicAppoggio"><spring:message code="bicAppoggio" /></label>
			<div class="col-sm-${w00} controls">
				<form:input class="form-control input-bic" path="bicAppoggio" maxlength="11" readonly="true" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagCodiceCorrentePostaleAppoggio"><spring:message code="flagCodiceCorrentePostaleAppoggio" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleAppoggio" id="flagCodiceCorrentePostaleAppoggio" readonly="true" disabled="true" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagPresenzaBollettinoPostale"><spring:message code="flagPresenzaBollettinoPostale" /></label>
			<div class="col-sm-${w00} controls">
				<form:checkbox class="checkbox-inline" path="flagPresenzaBollettinoPostale" id="flagPresenzaBollettinoPostale" readonly="true" disabled="true" />
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagInvioFlussi"><spring:message code="flaginvioflussi" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagInvioFlussi" id="flagInvioFlussi" readonly="true" disabled="true" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="email" id="eMailLabel"><spring:message code="filter.email" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-email" path="email" maxlength="250" readonly="true" />
			</div>
		</div>
	</div>
</div>

<!-- evolutiva RD10_2 SB    -->
<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">	
		<label class="col-sm-${w00} control-label" for="fattura"><spring:message code="fattura" /></label>
			<div class="col-sm-${w01} controls">
				<form:select path="fattura"
					class="form-control input-modalita-integrazione" readonly="true" disabled="true">
					<!-- <form:option value='${modifica_codice_versamento.fattura}'> prova  </form:option>	-->
					<form:option value="true"> SI </form:option>
					<form:option value="false"> NO </form:option>							
				</form:select>
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="fattura" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<c:if test="${modifica_codice_versamento_collegato.codiceTipoPagamento eq 'PS'}">
<div class="container-fluid"  >
    <div class="row-fluid">
        
        <div class="form-group ">
        
        <label class="col-sm-3  control-label" for="flagVisualizzaDaSportello"><spring:message code="flagVisualizzaDaSportello" /></label>
            <div class="col-sm-${w01} controls">
                <form:checkbox class="checkbox-inline" path="flagVisualizzaDaSportello" id="flagVisualizzaDaSportello" readonly="true" disabled="true"/>             
            </div>
            
    </div>
     </div>
</div>

<div class="container-fluid"  >
<div class="row-fluid">
        
        <div class="form-group ">
        
         <label class="col-sm-3 control-label" for="dataInizioValidita">Data inizio validit&agrave; </label>
            <div class="col-sm-${w01} col-md-2  controls input-append">
                <form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;" readonly="true" disabled="true"/>
            </div>
        
        
        
                  
    </div>
     </div>
</div>

<div class="container-fluid"  >
<div class="row-fluid">
        
        <div class="form-group ">
        
          <label class="col-sm-3 control-label" for="dataFineValidita">Data fine validit&agrave; </label>
            <div class="col-xs-12 col-sm-2  col-md-2  controls input-append">
                <form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;" readonly="true" disabled="true"/>
            </div>
        
        
                  
    </div>
     </div>
</div>

</c:if>


<%-- 
<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="descrizioneStatoAggiornamento"><spring:message code="statoattivitaaggiornamento" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-stato-aggiornamento" path="descrizioneStatoAggiornamento" readonly="true" />
			</div>
		</div>
	</div>
</div>
--%>

<%@ include file="../visualizza/form_elementi_multibeneficiario.jsp"%> 
<sec:authorize access="hasRole('ASSISTENZA')">
                   <%@ include file="../visualizza/form_abilitazione_notifiche_appio.jsp"%>
 </sec:authorize>
<%@ include file="form_elementi_pnd.jsp"%> 
