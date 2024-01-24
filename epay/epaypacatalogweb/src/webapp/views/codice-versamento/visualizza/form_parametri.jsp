<c:set var="w00" value="2" />
<c:set var="w01" value="10" />
<c:set var="w04" value="4" />
<c:set var="w05" value="2" />

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-2 control-label" for="codice"><spring:message code="filter.aliasVoceEntrata" /></label>
			<div class="col-sm-10 controls">
				<div class="input-group input-group-inline">
			  		<span class="input-group-addon input-codice-prefisso" id="container-codice-codice-versamento"><c:out value='${modifica_codice_versamento.codice}' /></span>
					<form:input class="form-control input-descrizione-codice-versamento" path="descrizione" id="descrizione" maxlength="140" readonly="true" />
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-2 control-label " for="codiceVoceEntrata"><spring:message code="codiceversamento.form.voceentratappay" /></label>
			<div class="col-sm-10 controls">
				<div class="input-group input-group-inline">
			  		<span class="input-group-addon input-codice-prefisso" id="container-codice-voce-entrata"><c:out value='${modifica_codice_versamento.codiceVoceEntrata}' /></span>
					<form:input class="form-control input-descrizione-voce-entrata" path="descrizioneVoceEntrata" id="descrizioneVoceEntrata" readonly="true" />
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="codiceTipoPagamento"><spring:message code="tipopagamento" /></label>
			<div class="col-xs-12 col-sm-9 col-md-5 controls">
				<form:select path="codiceTipoPagamento" class="form-control input-tipo-pagamento input-inline" readonly="true" disabled="true">
					<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
					<form:options items="${modelTipiPagamento}" 
						itemLabel="descrizione" itemValue="codice" />
				</form:select>
				
				<a class="suggest" 
					data-explanation="${fn:escapeXml( MEX_COD_SUGGEST_TIPO_PAGAMENTO )}" 
					data-trigger="hover" 
					data-placement="top"
				>
					<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
				</a>
			</div>
			<c:if test="${modifica_codice_versamento.codiceTipoPagamento eq 'PS'}">
			
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="flagVisualizzaDaSportello"><spring:message code="flagVisualizzaDaSportello" /></label>
            <div class="col-xs-12 col-sm-3 col-md-1 control-label">
                <form:checkbox class="checkbox-inline" path="flagVisualizzaDaSportello" id="flagVisualizzaDaSportello"  readonly="true" disabled="true"/> 
                </div> 
                <div class="col-sm-${w00}"></div>
            <div class="col-sm-${w01}"></div>
			
			</c:if>
			
			
		</div>
	</div>
</div>

<c:if test="${modifica_codice_versamento.codiceTipoPagamento eq 'PS'}">

<div class="container-fluid ">
    <div class="row-fluid">
        <div class="form-group ">
        
            <label class="col-xs-12 col-sm-3 col-md-2 control-label" for="dataInizioValidita">Data inizio validit&agrave; </label>
            
           
            <div class="col-xs-12 col-sm-9 col-md-5 controls">
                <form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;" readonly="true" disabled="true"/>
            </div>
            
            <label class="col-xs-12 col-sm-3 col-md-2 control-label" for="dataFineValidita">Data fine validit&agrave; </label>
            
                <div class="col-xs-12 col-sm-3 col-md-2">
                <form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important; " readonly="true" disabled="true"/>
            </div>
            
           
        </div>
    </div>
</div>

</c:if>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="iban"><spring:message code="iban" /></label>
			<div class="col-xs-12 col-sm-9 col-md-5 controls">
				<form:input class="form-control view-uppercase input-iban" path="iban" maxlength="35" readonly="true"/>
			</div>
			
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="flagCodiceCorrentePostaleTesoreria"><spring:message code="flagCodiceCorrentePostaleTesoreria" /></label>
			<div class="col-xs-12 col-sm-3 col-md-1 control-label">
			<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleTesoreria" id="flagCodiceCorrentePostaleTesoreria" readonly="true" disabled="true" />		
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
			
			</div>		
		</div>
	</div>
</div>
</div>







<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="bic"><spring:message code="bic" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control view-uppercase input-bic" path="bic" maxlength="11" readonly="true"/>
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="ibanAppoggio"><spring:message code="ibanAppoggio" /></label>
			<div class="col-xs-12 col-sm-9 col-md-5 controls">
				<form:input class="form-control view-uppercase input-iban" path="ibanAppoggio" maxlength="35" readonly="true"/>
			</div>
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="flagCodiceCorrentePostaleAppoggio"><spring:message code="flagCodiceCorrentePostaleAppoggio" /></label>
			<div class="col-xs-12 col-sm-3 col-md-1 control-label">
				<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleAppoggio" id="flagCodiceCorrentePostaleAppoggio" readonly="true" disabled="true"/>				
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
			</div>
		</div>
	</div>
</div>



<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="bicAppoggio"><spring:message code="bicAppoggio" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control view-uppercase input-bic" path="bicAppoggio" maxlength="11" readonly="true"/>
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagPresenzaBollettinoPostale"><spring:message code="flagPresenzaBollettinoPostale" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagPresenzaBollettinoPostale" id="flagPresenzaBollettinoPostale" readonly="true" disabled="true"/>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label " for="codiceModalitaIntegrazione"><spring:message code="ente.forms.formmodificaente.modalitaintegrazione" /></label>
			<div class="col-sm-${w01} controls">
				<form:select path="codiceModalitaIntegrazione" class="form-control input-modalita-integrazione input-inline" readonly="true" disabled="true">
					<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
					<form:options items="${listaOpzioniModalitaIntegrazione}" 
						itemLabel="descrizione" itemValue="codice" />
				</form:select>
				<a class="suggest" 
					data-explanation="${fn:escapeXml( MEX_SUGGEST_MOD_INTEGRAZIONE )}" 
					data-trigger="hover" 
					data-placement="top"
				>
					<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
				</a>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagInvioFlussi"><spring:message code="flaginvioflussi" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagInvioFlussi" id="flagInvioFlussi" readonly="true" disabled="true"/>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="email" id="eMailLabel"><spring:message code="filter.email" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-email" path="email" maxlength="250" readonly="true"/>
			</div>
		</div>
	</div>
</div>

<!-- evolutiva RD10_2 SB    -->
<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">	<label class="col-sm-${w00} control-label" for="fattura"><spring:message code="fattura" /></label>
			<div class="col-sm-${w01} controls">
				<form:select path="fattura"
					class="form-control input-modalita-integrazione" readonly="true" disabled="true">
					<form:option value=""> NO </form:option>
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

<!-- RDI-027.15: TOGLIERE IL CAMPO Stato Attività Aggiornamento -->  
<%-- 
<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="descrizioneStatoAggiornamento"><spring:message code="statoattivitaaggiornamento" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-stato-aggiornamento" path="descrizioneStatoAggiornamento" readonly="true" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="descrizioneStatoAggiornamento" cssClass="error" />
			</div>
		</div>
	</div>
</div>
--%>