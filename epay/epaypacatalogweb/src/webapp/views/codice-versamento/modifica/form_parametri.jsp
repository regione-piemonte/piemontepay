<c:set var="w00" value="2" />
<c:set var="w01" value="10" />
<c:set var="w04" value="4" />
<c:set var="w05" value="2" />

<c:if test="${scenario ne 'modifica'}">
<div class="container-fluid">
	<div class="row-fluid mb-1">
		<div class="form-group ">
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01} controls">
				<a class="btn btn-primary btn-action" onclick="apriModaleVociEntrata()"><spring:message code="codiceversamento.modifica.cercavoceentratappay" /></a>
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}"></div>
		</div>
	</div>
</div>
</c:if>

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-2 control-label" for="codice"><spring:message code="filter.aliasVoceEntrata" /></label>
			<div class="col-sm-10 controls">
			
				<div class="input-group input-group-inline">
			  		<span class="input-group-addon input-codice-prefisso" id="container-codice-codice-versamento"><c:out value='${modifica_codice_versamento.codice}' /></span>
					<form:input class="form-control input-descrizione-codice-versamento" path="descrizione" id="descrizione" maxlength="140" />
				</div>
				
				<form:hidden path="codice" id="codice" />				
			</div>
			
			<div class="col-sm-3">
				<form:errors path="codice" cssClass="error" />
			</div>
			<div class="col-sm-9">
				<form:errors path="descrizione" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-2 control-label required" for="codiceVoceEntrata"><spring:message code="codiceversamento.form.voceentratappay" /></label>
			<div class="col-sm-10 controls">
				<form:hidden path="codiceVoceEntrata" />

				<div class="input-group input-group-inline">
			  		<span class="input-group-addon input-codice-prefisso" id="container-codice-voce-entrata"><c:out value='${modifica_codice_versamento.codiceVoceEntrata}' /></span>
					<form:input class="form-control input-descrizione-voce-entrata" path="descrizioneVoceEntrata" id="descrizioneVoceEntrata" readonly="true" />
				</div>
				
			</div>
			
			<div class="col-sm-2"></div>
			<div class="col-sm-10">
				<form:errors path="codiceVoceEntrata" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="codiceTipoPagamento"><spring:message code="tipopagamento" /></label>
			<div class="col-sm-5 controls">
				<form:hidden path="codiceTipoPagamento" readonly="true"/>
				<form:input class="form-control input-tipo-pagamento input-inline" path="descrizioneTipoPagamento" id="descrizioneTipoPagamento" readonly="true" />
				
				<!-- 
				<form:select path="codiceTipoPagamento" class="form-control input-tipo-pagamento">
					<form:option value=""><spring:message code="form.ricerca.qualsiasi" /></form:option>
					<form:options items="${modelTipiPagamento}" 
						itemLabel="descrizione" itemValue="codice" />
				</form:select>
				-->
				
				<a class="suggest" 
					data-explanation="${fn:escapeXml( MEX_COD_SUGGEST_TIPO_PAGAMENTO )}" 
					data-trigger="hover" 
					data-placement="top"
				>
					<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
				</a>
				
			</div>
			
			
			 <label class="col-xs-12 col-sm-3 col-md-2 control-label visualizzaSportelloClass" for="flagVisualizzaDaSportello"><spring:message code="flagVisualizzaDaSportello" /></label>
            <div class="col-xs-12 col-sm-3 col-md-1 control-label visualizzaSportelloClass">
                <form:checkbox class="checkbox-inline" path="flagVisualizzaDaSportello" id="flagVisualizzaDaSportello" />             
            </div>
            
            
            
            <div class="col-sm-${w00}"></div>
            <div class="col-sm-${w01}">
                <form:errors path="codiceTipoPagamento" cssClass="error" />
            </div>
			
<!-- 			<div class="container-fluid" id  = "visualizzaSportelloContainerId" > -->
			   
<!--             <label class="col-sm-1  control-label" for="flagVisualizzaDaSportello"><spring:message code="flagVisualizzaDaSportello" /></label> -->
<!--             <div class="col-xs-12 col-sm-2  col-md-1 control-label"> -->
<!--                 <form:checkbox class="checkbox-inline" path="flagVisualizzaDaSportello" id="flagVisualizzaDaSportello" />              -->
<!--             </div> -->
			
<!-- 			 <label class="col-sm-1 control-label" for="dataInizioValidita">Data inizio validit&agrave; </label> -->
<!--             <div class="col-sm-3 controls input-append"> -->
<!--                 <form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/> -->
<!--             </div> -->
            
         
            
<!--             <label class="col-sm-1 control-label" for="dataFineValidita">Data fine validit&agrave; </label> -->
<!--             <div class="col-sm-2 controls input-append"> -->
<!--                 <form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/> -->
<!--             </div> -->
<!--              </div> -->
			
<%-- 			<div class="col-sm-${w00}"></div> --%>
<%-- 			<div class="col-sm-${w01}"> --%>
<!-- 				<form:errors path="codiceTipoPagamento" cssClass="error" /> -->
<!-- 			</div> -->
			
<!-- 			 <div class="col-sm-2"> -->
<!--                 <form:errors path="flagVisualizzaDaSportello" cssClass="error" /> -->
<!--             </div> -->
            
<!--             <div class="col-sm-1"></div> -->
<!--             <div class="col-sm-2"> -->
<!--                 <form:errors path="dataInizioValidita" cssClass="error" /> -->
<!--             </div> -->
            
            
<!--             <div class="col-sm-1"></div> -->
<!--             <div class="col-sm-2"> -->
<!--                 <form:errors path="dataFineValidita" cssClass="error" /> -->
<!--             </div> -->
		</div>
	</div>
</div>


<div class="container-fluid visualizzaSportelloClass">
    <div class="row-fluid">
        <div class="form-group ">
        
            <label class="col-sm-2 control-label" for="dataInizioValidita">Data inizio validit&agrave; </label>
            
           
            <div class="col-sm-4 controls input-append">
                <form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/>
            </div>
            
            <label class="col-sm-3  control-label" for="dataFineValidita">Data fine validit&agrave; </label>
            
                <div class="col-sm-3 controls input-append">
                <form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/>
            </div>
            
            <div class="col-sm-${w00}"></div>
            <div class="col-sm-3">
                <form:errors path="dataInizioValidita" cssClass="error" />
            </div>
            
            <div class="col-sm-3"></div>
            <div class="col-sm-3 ">
                <form:errors path="dataFineValidita" cssClass="error" />
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="iban"><spring:message code="ibanDettaglio" /></label>
			<div class="col-xs-12 col-sm-9 col-md-5 controls">
				<form:input class="form-control view-uppercase input-iban" path="iban" maxlength="35" />
			</div>
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="flagCodiceCorrentePostaleTesoreria"><spring:message code="flagCodiceCorrentePostaleTesoreria" /></label>
			<div class="col-xs-12 col-sm-3 col-md-1 control-label">
				<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleTesoreria" id="flagCodiceCorrentePostaleTesoreria" />				
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="iban" cssClass="error" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="bic"><spring:message code="bic" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control view-uppercase input-bic" path="bic" maxlength="11" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="bic" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
					<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="ibanAppoggio"><spring:message code="ibanAppoggio" /></label>
			<div class="col-xs-12 col-sm-9 col-md-5 controls">
				<form:input class="form-control view-uppercase input-iban" path="ibanAppoggio" maxlength="35" />
			</div>
			<label class="col-xs-12 col-sm-3 col-md-2 control-label" for="flagCodiceCorrentePostaleAppoggio"><spring:message code="flagCodiceCorrentePostaleAppoggio" /></label>
			<div class="col-xs-12 col-sm-3 col-md-1 control-label">
				<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleAppoggio" id="flagCodiceCorrentePostaleAppoggio" />				
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="ibanAppoggio" cssClass="error" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="bicAppoggio"><spring:message code="bicAppoggio" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control view-uppercase input-bic" path="bicAppoggio" maxlength="11" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="bicAppoggio" cssClass="error" />
			</div>
		</div>
	</div>
</div>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" id="flagPresenzaBollettinoPostaleLabel"><spring:message code="flagPresenzaBollettinoPostale"  /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagPresenzaBollettinoPostale" id="flagPresenzaBollettinoPostale" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="flagPresenzaBollettinoPostale" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="codiceModalitaIntegrazione"><spring:message code="ente.forms.formmodificaente.modalitaintegrazione" /></label>
			<div class="col-sm-${w01} controls">
				<form:select path="codiceModalitaIntegrazione"
					class="form-control input-modalita-integrazione input-inline">
					<form:option value="" label="--- scegli ---" />
					<form:options items="${listaOpzioniModalitaIntegrazione}"
						itemValue="codice" itemLabel="descrizione" />
				</form:select>
				<a class="suggest" 
					data-explanation="${fn:escapeXml( MEX_SUGGEST_MOD_INTEGRAZIONE )}" 
					data-trigger="hover" 
					data-placement="top"
				>
					<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
				</a>
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="codiceModalitaIntegrazione" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagInvioFlussi"><spring:message code="flaginvioflussi" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagInvioFlussi" id="flagInvioFlussi" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="flagInvioFlussi" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="email" id="eMailLabel"><spring:message code="filter.email" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-email" path="email" maxlength="250" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="email" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<!-- evolutiva RD10_2 SB    -->
<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">	<label class="col-sm-${w00} control-label" for="fattura"><spring:message code="fattura" /></label>
			<div class="col-sm-${w01} control-label required">
				<form:select path="fattura"
					class="form-control input-modalita-integrazione">
					<!-- <form:option value='${modifica_codice_versamento.fattura}'> prova  </form:option>	-->
					<form:option value=""><spring:message code="form.ricerca.scegli" /></form:option>	
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
<c:if test="${scenario.equals('modifica')}">
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
</c:if>
--%>