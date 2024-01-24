<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="3" />
<c:set var="w01" value="9" />
<c:set var="w02" value="6" />
<c:set var="w03" value="5" />

<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
	<form:hidden path="codiceCodiceVersamento" />
	<form:hidden path="descrizioneCodiceVersamento" />

	<div class="container-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="descrizioneCodiceVersamento">Codice versamento </label>
			<div class="col-sm-${w01} controls">
				<input type="text" class="form-control input-codice-versamento" id="descrizioneCodiceVersamento" 
					value="<c:out 
					value='${modifica_riferimento_contabile.codiceCodiceVersamento}' /> - <c:out 
					value='${modifica_riferimento_contabile.descrizioneCodiceVersamento}' />" disabled="disabled" readonly="true"/>
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="idCodiceVersamento" cssClass="error" />
			</div>
		</div>
	</div>
	<form:hidden path="idCodiceVersamento" />
</c:if>

<c:if test="${scenario.equals('nuovo') && not scenario_alt.equals('duplica')}">
	<div class="container-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="descrizioneCodiceVersamento">Codice versamento </label>
			<div class="col-sm-${w01} controls">
				<input type="text" class="form-control input-codice-versamento" id="descrizioneCodiceVersamento" />
				</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="idCodiceVersamento" cssClass="error" />
			</div>
		</div>
	</div>
	<form:hidden path="idCodiceVersamento" />
	<form:hidden path="tipoPagamento" />
</c:if>

<c:if test="${scenario_alt.equals('duplica')}">
    <form:hidden path="codiceCodiceVersamento" />
    <form:hidden path="descrizioneCodiceVersamento" />
    <form:hidden path="idCodiceVersamento" />
    <form:hidden path="tipoPagamento" />

    <div class="container-fluid">
        <div class="form-group ">
            <label class="col-sm-${w00} control-label required" for="descrizioneCodiceVersamento">Codice versamento </label>
            <div class="col-sm-${w01} controls">
                <input type="text" class="form-control input-codice-versamento" id="descrizioneCodiceVersamento" 
                    value="<c:out 
                    value='${modifica_riferimento_contabile.codiceCodiceVersamento}' /> - <c:out 
                    value='${modifica_riferimento_contabile.descrizioneCodiceVersamento}' />" disabled="disabled" readonly="true" />
            </div>
            <div class="col-sm-${w00}"></div>
            <div class="col-sm-${w01}">
                <form:errors path="idCodiceVersamento" cssClass="error" />
            </div>
        </div>
    </div>
</c:if>
	<c:if test="${scenario.equals('nuovoDaCodiceVersamento')}">
	<div class="container-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="descrizioneCodiceVersamento">Codice versamento </label>
			<div class="col-sm-${w01} controls">
				<input type="text" class="form-control input-codice-versamento" id="descrizioneCodiceVersamento"  disabled="disabled"  readonly="true"/>
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="idCodiceVersamento" cssClass="error" />
			</div>
		</div>
	</div>
	
	<form:hidden path="idCodiceVersamento"  readonly="true"/>
</c:if>

<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
	<form:hidden path="codiceVoceEntrata" />
	<div class="container-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label" for="descrizioneVoceEntrata">Voce entrata PPay </label>
			<div class="col-sm-${w01} controls">
				<input type="text" class="form-control input-descrizione-voce-entrata" id="descrizioneVoceEntrata" 
					value="<c:out 
					value='${modifica_riferimento_contabile.codiceVoceEntrata}' /> - <c:out 
					value='${modifica_riferimento_contabile.descrizioneVoceEntrata}' />" disabled="disabled" readonly="true" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="codiceVoceEntrata" cssClass="error" />
			</div>
		</div>
	</div>
</c:if>

<div class="container-fluid">
	<div class="form-group ">
		<c:if test="${scenario.equals('nuovo') && not scenario_alt.equals('duplica') ||scenario.equals('nuovoDaCodiceVersamento')}">
			<label class="col-sm-${w00} control-label required" for="dataInizioValidita">Data inizio validit&agrave; </label>
			<div class="col-sm-${w00} controls input-append">
				<form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/>
			</div>
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<label class="col-sm-${w00} control-label" for="dataInizioValidita">Data inizio validit&agrave; </label>
			<div class="col-sm-${w00} controls input-append">
				<form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" readonly="true" />
			</div>
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
			<label class="col-sm-${w00} control-label required" for="dataInizioValidita">Data inizio validit&agrave; </label>
            <div class="col-sm-${w00} controls input-append">
                <form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;"/>
            </div>
        </c:if>
		<label class="col-sm-2 control-label required" for="dataFineValidita">Data fine validit&agrave; </label>
		<c:if test="${scenario.equals('nuovo') && not scenario_alt.equals('duplica') ||scenario.equals('nuovoDaCodiceVersamento')}">
			<div class="col-sm-4 controls input-append">
				<form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;" />
			</div>
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<div class="col-sm-4 controls input-append">
				<form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;" />
			</div>
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
            <div class="col-sm-4 controls input-append">
                <form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" cssStyle="z-index: 0!important;" />
            </div>
        </c:if>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w00}">
			<form:errors path="dataInizioValidita" cssClass="error" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-4">
			<form:errors path="dataFineValidita" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group">
		<c:if test="${scenario.equals('nuovo') && not scenario_alt.equals('duplica') || scenario.equals('duplica')||scenario.equals('nuovoDaCodiceVersamento')}">
			<label class="col-sm-${w00} control-label required" for="annoEsercizio">Anno esercizio </label>
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<label class="col-sm-${w00} control-label" for="annoEsercizio">Anno esercizio </label>
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
			<label class="col-sm-${w00} control-label required" for="annoEsercizio">Anno esercizio </label>
		</c:if>
		<div class="col-sm-${w00} controls">
			<c:if test="${scenario.equals('nuovo') && not scenario_alt.equals('duplica') || scenario.equals('duplica')||scenario.equals('nuovoDaCodiceVersamento')}">
				<form:input type="number" class="form-control input-anno" path="annoEsercizio" min="0" />
			</c:if>
			<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
				<form:input type="number" class="form-control input-anno" path="annoEsercizio" readonly="true" />
			</c:if>
			<c:if test="${scenario_alt.equals('duplica')}">
                <form:input type="number" class="form-control input-anno" path="annoEsercizio" />
            </c:if>
            <br/>
			<form:errors path="annoEsercizio" cssClass="error" />
		</div>
		<div class="col-sm-6"></div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
	<c:if test="${scenario.equals('nuovo') ||scenario.equals('modifica') ||scenario.equals('nuovoDaCodiceVersamento') }">
		<label class="col-sm-${w00} control-label" for="annoAccertamento">Anno accertamento</label>
		<div class="col-sm-${w00} controls">
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
				<form:input type="number" class="form-control input-anno" path="annoAccertamento" min="0" />
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
				<form:input type="number" class="form-control input-anno" path="annoAccertamento" min="0"  readonly="true"  />
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
                <form:input type="number" class="form-control input-anno" path="annoAccertamento" min="0"  />
        </c:if>
		</div>
		<label class="col-sm-2 control-label" for="numeroAccertamento">Numero Accertamento</label>
		<div class="col-sm-4 controls">
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
			<form:input type="number" class="form-control input-numero" path="numeroAccertamento" min="0" />
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
				<form:input type="number" class="form-control input-numero" path="numeroAccertamento" min="0"  readonly="true"  />
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
                <form:input type="number" class="form-control input-numero" path="numeroAccertamento" min="0" />
        </c:if>
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w00}">
			<form:errors path="annoAccertamento" cssClass="error" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-4">
			<form:errors path="numeroAccertamento" cssClass="error" />
		</div>
	</c:if>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group " id="numeroCapitoloAndArticolo">
		<label class="col-sm-${w00} control-label" for="numeroCapitolo">Capitolo</label>
		<div class="col-sm-${w00} controls">
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
			<form:input type="number" class="form-control input-numero" path="numeroCapitolo" min="0" />
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<form:input type="number" class="form-control input-numero" path="numeroCapitolo" min="0" />
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
			<form:input type="number" class="form-control input-numero" path="numeroCapitolo" min="0" />
        </c:if>
		</div>
		<label class="col-sm-2 control-label" for="numeroArticolo">Numero Articolo</label>
		<div class="col-sm-4 controls">
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
			<form:input type="number" class="form-control input-numero" path="numeroArticolo" min="0" />
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<form:input type="number" class="form-control input-numero" path="numeroArticolo" min="0" />
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
			<form:input type="number" class="form-control input-numero" path="numeroArticolo" min="0"  />
        </c:if>
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w00}">
			<form:errors path="numeroCapitolo" cssClass="error" />
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-4">
			<form:errors path="numeroArticolo" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="livelloPdc">V Livello PdC</label>
		<div class="col-sm-${w01} controls">
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
			<form:input class="form-control input-livello-pdc" path="livelloPdc" />
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<form:input class="form-control input-livello-pdc" path="livelloPdc" />
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
			<form:input class="form-control input-livello-pdc" path="livelloPdc" />
        </c:if>
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w01}">
			<form:errors path="livelloPdc" cssClass="error" />
		</div>
	</div>
</div>

<c:if test="${scenario.equals('nuovo') && not scenario_alt.equals('duplica')||scenario.equals('nuovoDaCodiceVersamento')}">
<div class="container-fluid">
	<div class="form-group">
		<label class="col-sm-${w00} control-label required" for="idTassonomia">Descrizione dato specifico riscossione</label>
		<div class="col-sm-${w01} controls">
			<form:select path="idTassonomia" class="form-control input-descrizione-dato-specifico-riscossione" >
				<form:option value="">-- Seleziona --</form:option>
				<form:options items="${model_descrizione_dato_specifico_riscossione}" itemLabel="tipoServizio" itemValue="id" />
			</form:select>
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w01}">
			<form:errors path="idTassonomia" cssClass="error" />
		</div>
	</div>
</div>
<form:hidden path="descrizioneDatoSpecificoRiscossione" id="descrizioneDatoSpecificoRiscossione"/>
<form:hidden path="codiceDatoSpecificoRiscossione" id="codiceDatoSpecificoRiscossione"/>
<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="codiceTributo">Codice tributo</label>
		<div class="col-sm-${w00} controls">
			<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
				<form:input class="form-control" path="codiceTributo" />
			</c:if>
			<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
				<form:input class="form-control" path="codiceTributo" />
			</c:if>
			<c:if test="${scenario_alt.equals('duplica')}">
				<form:input class="form-control" path="codiceTributo" />
			</c:if>
		</div>
		<a class="suggest"
		   data-explanation="${fn:escapeXml( MEX_SUGGEST_CODICE_TRIBUTO )}"
		   data-trigger="hover"
		   data-placement="top"
		><!--B-->
			<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
		</a>
		<div class="row">
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01} col-md-offset-${w00}">
				<form:errors path="codiceTributo" cssClass="error" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
        <div class="form-group ">
            <label class="col-sm-${w00} control-label" for="datoSpecificoRiscossione"
                id="labeldatoSpecificoRiscossione"
            >Dato specifico riscossione tassonomia</label>
            <div class="col-sm-9">
                    <c:if test="${not scenario_alt.equals('duplica')}">
                       <form:input class="form-control input-dato-specifico-riscossione" path="datoSpecificoRiscossione" readonly="true" />
                    </c:if>
                    <c:if test="${scenario_alt.equals('duplica')}">
                    <div class="input-group" style="display:table;margin-bottom:0.5em;">
                        <span class="input-group-addon" id="prefissoDatoSpecificoRiscossione">
                         <c:out value='${modifica_riferimento_contabile.codiceTipologiaDatoSpecificoRiscossione}' > </c:out> 
                      </span>
                    </c:if>
                </div>
            </div>
            <div class="col-sm-${w00}"></div>
            <div class="col-sm-9">
                <form:errors path="datoSpecificoRiscossione" cssClass="error" />
            </div>
        </div>
    
    <div class="container-fluid">
    <div class="form-group " id="dateValiditaDatoSpecificoRiscossione">

     <c:if test="${scenario.equals('modifica')}">
        <label class="col-sm-${w00} control-label" for="dataInizioValiditaCodiceTassonomico">Data inizio validit&agrave; codice tassonomico</label>
        </c:if>
         <c:if test="${not scenario.equals('modifica')}">
        <label class="col-sm-${w00} control-label required" for="dataInizioValiditaCodiceTassonomico">Data inizio validit&agrave; codice tassonomico</label>
        </c:if>
        <div class="col-sm-${w00} controls  input-append">
             <form:input path="dataInizioValiditaCodiceTassonomico" id="dataInizioValiditaCodiceTassonomico" class="form-control input-data" pattern="dd/MM/yyyy" readonly="true" />
        </div>
        <label class="col-sm-2 control-label" for="dataFineValiditaCodiceTassonomico">Data fine validit&agrave; codice tassonomico</label>
        <div class="col-sm-4 controls">
             <form:input path="dataFineValiditaCodiceTassonomico" id="dataFineValiditaCodiceTassonomico" class="form-control input-data" pattern="dd/MM/yyyy" readonly="true" />
        </div>
        <div class="col-sm-${w00}"></div>
        <div class="col-sm-${w00}">
            <form:errors path="dataInizioValiditaCodiceTassonomico" cssClass="error" />
        </div>
        <div class="col-sm-2"></div>
        <div class="col-sm-4">
            <form:errors path="dataFineValiditaCodiceTassonomico" cssClass="error" />
        </div>
    </div>
</div>
    
</c:if>


<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
	   <div class="container-fluid">
        <div class="form-group ">
    
            <label class="col-sm-${w00} control-label" for="descrizioneDatoSpecificoRiscossione"
                id="labelDescrizioneDatoSpecificoRiscossione"
            >Descrizione dato specifico riscossione</label>
            <div class="col-sm-9">

                     <input type="text" class="form-control input-dato-specifico-riscossione" id="descrizioneDatoSpecificoRiscossione" 
                    value="<c:out 
                    value='${modifica_riferimento_contabile.tipoServizioTassonomia}' />" disabled="disabled" readonly= "true" />
                   
            </div>
        </div>
    </div>
    
    
    <div class="container-fluid">
    <div class="form-group ">
        <label class="col-sm-${w00} control-label" for="codiceTributo">Codice tributo</label>
        <div class="col-sm-${w00} controls">
                <form:input class="form-control" path="codiceTributo" readonly="true"/>
        </div>
        <a class="suggest"
           data-explanation="${fn:escapeXml( MEX_SUGGEST_CODICE_TRIBUTO )}"
           data-trigger="hover"
           data-placement="top"
        ><!--B-->
            <span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
        </a>
        <div class="row">
            <div class="col-sm-${w00}"></div>
            <div class="col-sm-${w01} col-md-offset-${w00}">
                <form:errors path="codiceTributo" cssClass="error" />
            </div>
        </div>
    </div>
</div>
    
    
    <div class="container-fluid">
        <div class="form-group ">
            
            <label class="col-sm-${w00} control-label" for="prefissoDatoSpecificoRiscossione"
                id="labelPrefissoDatoSpecificoRiscossione"
            >Dato specifico riscossione tassonomia</label>
            <div class="col-sm-9">
                
                    
                <input type="text" class="form-control input-dato-specifico-riscossione" id="datoSpecificoRiscossione" 
                    value="<c:out 
                    value='${modifica_riferimento_contabile.datoSpecificoRiscossioneTassonomia}' />" disabled="disabled" readonly= "true" />
                    </div>
            </div>
        </div>
<!--         </div> -->
        
            <div class="container-fluid">
    <div class="form-group " id="dateValiditaDatoSpecificoRiscossione">
    
    
        <label class="col-sm-${w00} control-label required" for="dataInizioValiditaCodiceTassonomico">Data inizio validit&agrave; codice tassonomico</label>
        
        <div class="col-sm-${w00} controls  input-append">
        
            
             <form:input 
                    path="dataInizioValiditaCodiceTassonomico" id="dataInizioValiditaCodiceTassonomico" class="form-control datepicker input-data"
                  readonly="true" />
                    
        </div>
        <label class="col-sm-2 control-label" for="dataFineValiditaCodiceTassonomico">Data fine validit&agrave; codice tassonomico</label>
        <div class="col-sm-4 controls">
            
             
             <form:input 
                    path="dataFineValiditaCodiceTassonomico" id="dataFineValiditaCodiceTassonomico" class="form-control datepicker input-data"
                  readonly="true" />
        </div>
        <div class="col-sm-${w00}"></div>
        <div class="col-sm-${w00}">
            <form:errors path="dataInizioValiditaCodiceTassonomico" cssClass="error" />
        </div>
        <div class="col-sm-2"></div>
        <div class="col-sm-4">
            <form:errors path="dataFineValiditaCodiceTassonomico" cssClass="error" />
        </div>
        
                
    </div>
</div>
        
</div>
</c:if>

<c:if test="${scenario_alt.equals('duplica')}">
	<div class="container-fluid">
		<div class="form-group ">
			<label class="col-sm-${w00} control-label required" for="idTassonomia">Descrizione dato specifico riscossione</label>
			<div class="col-sm-${w01} controls">
				<form:select path="idTassonomia"  id="idTassonomia"  class="form-control input-descrizione-dato-specifico-riscossione" >
					<form:option value="">-- Seleziona --</form:option>
					<form:options items="${model_descrizione_dato_specifico_riscossione}" itemLabel="tipoServizio" itemValue="id" />
				</form:select>
				
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w01}">
				<form:errors path="idTassonomia" cssClass="error" />
			</div>
		</div>
	</div>
	
	<form:hidden path="descrizioneDatoSpecificoRiscossione" id="descrizioneDatoSpecificoRiscossione"/>
    <form:hidden path="codiceDatoSpecificoRiscossione" id="codiceDatoSpecificoRiscossione"/>


<div class="container-fluid">
    <div class="form-group ">
        <label class="col-sm-${w00} control-label" for="codiceTributo">Codice tributo</label>
        <div class="col-sm-${w00} controls">
                <form:input class="form-control" path="codiceTributo" />
        </div>
        <a class="suggest"
           data-explanation="${fn:escapeXml( MEX_SUGGEST_CODICE_TRIBUTO )}"
           data-trigger="hover"
           data-placement="top"
        ><!--B-->
            <span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
        </a>
        <div class="row">
            <div class="col-sm-${w00}"></div>
            <div class="col-sm-${w01} col-md-offset-${w00}">
                <form:errors path="codiceTributo" cssClass="error" />
            </div>
        </div>
    </div>
</div>
    <div class="container-fluid">
        <div class="form-group ">
            <label class="col-sm-${w00} control-label" for="prefissoDatoSpecificoRiscossione" id="labelPrefissoDatoSpecificoRiscossione">Dato specifico riscossione tassonomia</label>
            <div class="col-sm-9">
				 <form:input class="form-control input-dato-specifico-riscossione" path="datoSpecificoRiscossione" readonly="true" />
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="form-group " id="dateValiditaDatoSpecificoRiscossione">
			<label class="col-sm-${w00} control-label required" for="dataInizioValiditaCodiceTassonomico">Data inizio validit&agrave; codice tassonomico</label>
			<div class="col-sm-${w00} controls input-append">
				 <form:input path="dataInizioValiditaCodiceTassonomico" id="dataInizioValiditaCodiceTassonomico" class="form-control input-data" readonly="true"/>
			</div>
			<label class="col-sm-2 control-label" for="dataFineValiditaCodiceTassonomico">Data fine validit&agrave; codice tassonomico</label>
			<div class="col-sm-4 controls">
				 <form:input path="dataFineValiditaCodiceTassonomico" id="dataFineValiditaCodiceTassonomico" class="form-control input-data" readonly="true" />
			</div>
			<div class="col-sm-${w00}"></div>
			<div class="col-sm-${w00}">
				<form:errors path="dataInizioValiditaCodiceTassonomico" cssClass="error" />
			</div>
			<div class="col-sm-2"></div>
			<div class="col-sm-4">
				<form:errors path="dataFineValiditaCodiceTassonomico" cssClass="error" />
			</div>
		</div>
	</div>
	
	
</c:if>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="titolo">Titolo</label>
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-titolo" path="titolo" />
			</div>
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
		<div class="col-sm-${w01} ">
			<form:input class="form-control input-titolo" path="titolo" />
		</div>
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-titolo" path="titolo"/>
		</div>
		</c:if>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w01}">
			<form:errors path="titolo" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="codiceTipologia">Tipologia riferimento contabile</label>
		<div class="col-sm-${w01} controls">
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
			<form:input class="form-control input-tipologia-riferimento-contabile" path="tipologia" />
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<form:input class="form-control input-tipologia-riferimento-contabile" path="tipologia" />
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
			<form:input class="form-control input-tipologia-riferimento-contabile" path="tipologia" />
		</c:if>
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w01}">
			<form:errors path="tipologia" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="codiceCategoria">Categoria riferimento contabile</label>
		<div class="col-sm-${w01} controls">
		<c:if test="${(scenario.equals('nuovo') && not scenario_alt.equals('duplica')) ||scenario.equals('nuovoDaCodiceVersamento')}">
			<form:input class="form-control input-categoria-riferimento-contabile" path="categoria" />
		</c:if>
		<c:if test="${scenario.equals('modifica') && not scenario_alt.equals('duplica')}">
			<form:input class="form-control input-categoria-riferimento-contabile" path="categoria" />
		</c:if>
		<c:if test="${scenario_alt.equals('duplica')}">
			<form:input class="form-control input-categoria-riferimento-contabile" path="categoria" />
		</c:if>
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w01}">
			<form:errors path="categoria" cssClass="error" />
		</div>
	</div>
</div>


<c:if test="${scenario.equals('nuovoDaCodiceVersamento')  || scenario.equals('nuovo') }">
<div id= "titleElmentiMultibeneficiario" style='display: none;'><h4><spring:message code="index.elementi.multibeneficiario" /></h4></div> 


<div class="container-fluid" id= "divflagAssociaRifContPrimario" style='display: none;'>
	<div class="form-group">
		<label class="col-sm-${w00} control-label" for="flagAssociaRifContPrimario"><spring:message code="flagAssociaRifContPrimario" /></label>
		<div class="col-sm-${w01} controls">
			<form:checkbox class="checkbox-inline" path="flagAssociaRifContPrimario" id="flagAssociaRifContPrimario" disabled="true" readonly="true" />
		</div>
		<div class="col-sm-${w00}"></div>
	</div>
</div>

<div class="container-fluid" id= "divflagAssociaRifContSecondario" style='display: none;'>
	<div class="form-group">
		<label class="col-sm-${w00} control-label" for="flagAssociaRifContSecondario"><spring:message code="flagAssociaRifContSecondario" /></label>
		<div class="col-sm-${w01} controls">
			<form:checkbox class="checkbox-inline" path="flagAssociaRifContSecondario" id="flagAssociaRifContSecondario" disabled="true" readonly="true" />
		</div>
		<div class="col-sm-${w00}"></div>
	</div>
</div>

<div class="container-fluid" id= "divEnteSecondarioAssociato" style='display: none;'>
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="enteSecondarioAssociato"><spring:message code="enteSecondarioAssociato" /></label>
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-tipologia-riferimento-contabile" path="enteSecondarioAssociato" disabled="true" readonly="true" />
		</div>
		<div class="col-sm-${w00}"></div>
	</div>
</div>

<div class="container-fluid" id= "divCovEnteSecondarioAssociato" style='display: none;'>
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="covSecondarioAssociato"><spring:message code="covSecondarioAssociato" /></label>
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-tipologia-riferimento-contabile" path="covSecondarioAssociato" disabled="true" readonly="true" />
		</div>
		<div class="col-sm-${w00}"></div>
	</div>
</div>

<div class="container-fluid" id= "divRifContabileSecondarioAssociato" style='display: none;'>
	<div class="form-group ">
		<label class="col-sm-${w00} control-label required" for="idRifContabileSecondarioAssociato"><spring:message code="idRifContabileSecondarioAssociato" /></label>
		<div class="col-sm-${w01} controls">
			<form:select path="idRifContabileSecondarioAssociato" id="idRifContabileSecondarioAssociato"
				class="form-control input-tipologia-riferimento-contabile">
				<form:option value=""><spring:message code="form.ricerca.scegli" /></form:option>
				<form:options items="${modelRiferimentoContabileSecondario}" itemLabel="descrizione" itemValue="id" />
			</form:select>
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w01}">
			<form:errors path="idRifContabileSecondarioAssociato" cssClass="error" />
		</div>
	</div>
</div>

<form:hidden path="flagAssociaRifContSecondarioValue"  readonly="true"/>
<form:hidden  path="flagAssociaRifContPrimarioValue"  readonly="true"/>
<form:hidden  path="flagElementiMultibeneficiario"  readonly="true"/>
</c:if>

<c:if test="${scenario.equals('modifica') }">
<div id= "titleElmentiMultibeneficiario" ><h4><spring:message code="index.elementi.multibeneficiario" /></h4></div> 


<div class="container-fluid" id= "divflagAssociaRifContPrimario">
    <div class="form-group">
        <label class="col-sm-${w00} control-label" for="flagAssociaRifContPrimario"><spring:message code="flagAssociaRifContPrimario" /></label>
        <div class="col-sm-${w01} controls">
            <form:checkbox class="checkbox-inline" path="flagAssociaRifContPrimario" id="flagAssociaRifContPrimario" disabled="true" readonly="true" />
        </div>
        <div class="col-sm-${w00}"></div>
    </div>
</div>

<div class="container-fluid" id= "divflagAssociaRifContSecondario" >
    <div class="form-group">
        <label class="col-sm-${w00} control-label" for="flagAssociaRifContSecondario"><spring:message code="flagAssociaRifContSecondario" /></label>
        <div class="col-sm-${w01} controls">
            <form:checkbox class="checkbox-inline" path="flagAssociaRifContSecondario" id="flagAssociaRifContSecondario" disabled="true" readonly="true" />
        </div>
        <div class="col-sm-${w00}"></div>
    </div>
</div>

<div class="container-fluid" id= "divEnteSecondarioAssociato" >
    <div class="form-group ">
        <label class="col-sm-${w00} control-label" for="enteSecondarioAssociato"><spring:message code="enteSecondarioAssociato" /></label>
        <div class="col-sm-${w01} controls">
            <form:input class="form-control input-tipologia-riferimento-contabile" path="enteSecondarioAssociato"  readonly="true" />
        </div>
        <div class="col-sm-${w00}"></div>
    </div>
</div>

<!-- <div class="container-fluid" id= "divCovEnteSecondarioAssociato" > -->
<!--     <div class="form-group "> -->
<%--         <label class="col-sm-${w00} control-label" for="covSecondarioAssociato"><spring:message code="covSecondarioAssociato" /></label> --%>
<%--         <div class="col-sm-${w01} controls"> --%>
<!--             <form:input class="form-control input-tipologia-riferimento-contabile" path="covSecondarioAssociato" disabled="true" readonly="true" /> -->
<!--         </div> -->
<%--         <div class="col-sm-${w00}"></div> --%>
<!--     </div> -->
<!-- </div> -->

<div class="container-fluid" id= "divRifContabileSecondarioAssociato" >
    <div class="form-group ">
        <label class="col-sm-${w00} control-label" for="rifContabileSecondarioAssociato"><spring:message code="idRifContabileSecondarioAssociato" /></label>
        <div class="col-sm-${w01} controls">
           <form:input class="form-control input-tipologia-riferimento-contabile" path="rifContabileSecondarioAssociato"  readonly="true" />
    
        </div>
        <div class="col-sm-${w00}"></div>
        <div class="col-sm-${w01}">
            <form:errors path="rifContabileSecondarioAssociato" cssClass="error" />
        </div>
    </div>
</div>

<form:hidden path="flagAssociaRifContSecondarioValue"  readonly="true"/>
<form:hidden  path="flagAssociaRifContPrimarioValue"  readonly="true"/>
<form:hidden  path="flagElementiMultibeneficiario"  readonly="true"/>

</c:if>
<input type="hidden" name="tipologiaModifica" value="${scenario_alt}" />
<script type="text/javascript">
$( document ).ready(function () {
	function sortSelect( selElem, selectedVal ) {
		let tmpAry = [];
		for ( let i = 0; i < selElem.options.length; i++ ) {
			tmpAry[i] = [];
			tmpAry[i][0] = selElem.options[i].text;
			tmpAry[i][1] = selElem.options[i].value;
		}
		tmpAry.sort();
		while ( selElem.options.length > 0 ) {
			selElem.options[0] = null;
		}
		for ( let i = 0; i < tmpAry.length; i++ ) {
			selElem.options[i] = new Option(tmpAry[i][0], tmpAry[i][1]);
			var temp= tmpAry[i][1]
			if (temp == selectedVal)
				{
				selElem.options[i].selected=true;
				}
		}
	}
	const select = document.getElementById( "idTassonomia" );
	if ( select ) {
		const selected = select.value;
		if ( selected ) {
			sortSelect(select, selected);
		}
	}
});
</script>
