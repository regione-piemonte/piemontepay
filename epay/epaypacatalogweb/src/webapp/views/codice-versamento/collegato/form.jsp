<c:set var="w00" value="3" />
<c:set var="w01" value="9" />

<input type="hidden" name="descrizionePadre" value="<c:out value='${codice_versamento_padre.descrizione}' />" />

<div class="container-fluid mb-1">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="descrizione"
			><spring:message code="codiceversamento.collegato.form.descrizionecodiceversamentocollegato" /></label>
			<div class="col-sm-${w01} controls">

				<div class="input-group input-group-inline">
					<c:if test="${scenario.equals('modifica')}">
			  			<span class="input-group-addon input-codice-prefisso" id="container-codice-codice-versamento"><c:out value='${modifica_codice_versamento_collegato.codice}' /></span>
			  		</c:if>
					<form:input class="form-control input-descrizione-codice-versamento" path="descrizione" id="descrizione" maxlength="140" />
				</div>
				
				<c:if test="${scenario.equals('modifica')}">
		  			<form:hidden path="codice" id="codice" />
		  		</c:if>
		  		
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="descrizione" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="iban"><spring:message code="iban" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-iban" path="iban" maxlength="35" />
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
				<form:input class="form-control input-bic" path="bic" maxlength="11" />
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
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagCodiceCorrentePostaleTesoreria"><spring:message code="flagCodiceCorrentePostaleTesoreria" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleTesoreria" id="flagCodiceCorrentePostaleTesoreria" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="flagCodiceCorrentePostaleTesoreria" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="ibanAppoggio"><spring:message code="ibanAppoggio" /></label>
			<div class="col-sm-${w01} controls">
				<form:input class="form-control input-iban" path="ibanAppoggio" maxlength="35" />
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
				<form:input class="form-control input-bic" path="bicAppoggio" maxlength="11" />
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
			<label class="col-sm-${w00} control-label" for="flagCodiceCorrentePostaleAppoggio"><spring:message code="flagCodiceCorrentePostaleAppoggio" /></label>
			<div class="col-sm-${w01} controls">
				<form:checkbox class="checkbox-inline" path="flagCodiceCorrentePostaleAppoggio" id="flagCodiceCorrentePostaleAppoggio" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="flagCodiceCorrentePostaleAppoggio" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid" id="flagPresenzaBollettinoPostaleBlock">
	<div class="row-fluid">
		<div class="form-group " style="height: 20px;">
			<label class="col-sm-${w00} control-label" for="flagPresenzaBollettinoPostale"><spring:message code="flagPresenzaBollettinoPostale" /></label>
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
			<div class="col-sm-${w01} controls">
				<form:select path="fattura"
					class="form-control input-modalita-integrazione">
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

<!-- evolutiva Visualizzazione da sportello   -->
<div class="container-fluid visualizzaSportelloClass"  id  = "visualizzaSportelloContainerId">
    <div class="row-fluid">
        
        <div class="form-group ">
        
        <label class="col-sm-3  control-label" for="flagVisualizzaDaSportello"><spring:message code="flagVisualizzaDaSportello" /></label>
            <div class="col-xs-12 col-sm-9  col-md-1 controls">
                <form:checkbox class="checkbox-inline" path="flagVisualizzaDaSportello" id="flagVisualizzaDaSportello" />             
            </div>
            
            
            
<!--             <label class="col-sm-1 control-label" for="dataInizioValidita">Data inizio validit&agrave; </label> -->
<!--             <div class="col-sm-2 controls input-append"> -->
<!--                 <form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/> -->
<!--             </div> -->
            
<!--             <label class="col-sm-1 control-label" for="dataFineValidita">Data fine validit&agrave; </label> -->
<!--             <div class="col-sm-2 controls input-append"> -->
<!--                 <form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/> -->
<!--             </div> -->
            
            <div class="col-sm-${w00}"></div>
           <div class="col-sm-${w01}">
                <form:errors path="flagVisualizzaDaSportello" cssClass="error" />
            </div>
            
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


<div class="container-fluid visualizzaSportelloClass"  >
    <div class="row-fluid">
        
        <div class="form-group ">
        
         <label class="col-sm-3  control-label" for="dataInizioValidita">Data inizio validit&agrave; </label>
                    <div class="col-sm-9 controls input-append">
                <form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/>
            </div>
            <div class="col-sm-${w00}"></div>
             <div class="col-sm-${w01}">
                <form:errors path="dataInizioValidita" cssClass="error" />
            </div>
        
    </div>
     </div>
</div>

<div class="container-fluid visualizzaSportelloClass"  >
    <div class="row-fluid">
        
        <div class="form-group ">
        
         <label class="col-sm-3  control-label" for="dataFineValidita">Data fine validit&agrave; </label>
                    <div class="col-sm-9 controls input-append">
                <form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/>
            </div>
            
            <div class="col-sm-${w00}"></div>
             <div class="col-sm-${w01}">
                <form:errors path="dataFineValidita" cssClass="error" />
            </div>
        
    </div>
     </div>
</div>


<form:hidden path="codiceTipoPagamento" readonly="true" />

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