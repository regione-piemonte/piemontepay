<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="3" />
<c:set var="w01" value="9" />
<c:set var="w02" value="6" />
<c:set var="w03" value="5" />

<form:hidden path="id" />

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="descrizioneCodiceVersamento">Codice versamento </label>
		<div class="col-sm-${w01} controls">
			<input type="text" class="form-control input-codice-versamento" id="descrizioneCodiceVersamento" 
				value="<c:out 
				value='${chiudi_riferimento_contabile.codiceCodiceVersamento}' /> - <c:out 
				value='${chiudi_riferimento_contabile.descrizioneCodiceVersamento}' />" disabled="disabled" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="descrizioneVoceEntrata">Voce entrata PPay </label>
		<div class="col-sm-${w01} controls">
			<input type="text" class="form-control input-descrizione-voce-entrata" id="descrizioneVoceEntrata" 
				value="<c:out 
				value='${chiudi_riferimento_contabile.codiceVoceEntrata}' /> - <c:out 
				value='${chiudi_riferimento_contabile.descrizioneVoceEntrata}' />" disabled="disabled" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
	
		<label class="col-sm-${w00} control-label " for="dataInizioValidita">Data inizio validit&agrave; </label>
		
		<div class="col-sm-${w00} controls input-append">
			<form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control datepicker input-data" readonly="true" />
		</div>
		
		<label class="col-sm-2 control-label required" for="dataFineValidita">Data fine validit&agrave; </label>
		
		<div class="col-sm-4 controls input-append">
			<form:input path="dataFineValidita" id="dataFineValidita" class="form-control datepicker input-data" />
		</div>
		
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w00}"></div>
		
		<div class="col-sm-2"></div>
		<div class="col-sm-4">
			<form:errors path="dataFineValidita" cssClass="error" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="annoEsercizio">Anno esercizio </label>
		<div class="col-sm-${w00} controls">
			<form:input type="number" class="form-control input-anno" path="annoEsercizio"  readonly="true"/>
		</div>
		<div class="col-sm-6"></div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="annoAccertamento">Anno accertamento</label>
		<div class="col-sm-${w00} controls">
			<form:input type="number" class="form-control input-anno" path="annoAccertamento"  readonly="true"/>
		</div>
		<label class="col-sm-2 control-label" for="numeroAccertamento">Numero Accertamento</label>
		<div class="col-sm-4 controls">
			<form:input type="number" class="form-control input-numero" path="numeroAccertamento"  readonly="true"/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="numeroCapitolo">Capitolo</label>
		<div class="col-sm-${w00} controls">
			<form:input type="number" class="form-control input-numero" path="numeroCapitolo"  readonly="true"/>
		</div>
		<label class="col-sm-2 control-label" for="numeroArticolo">Numero Articolo</label>
		<div class="col-sm-4 controls">
			<form:input type="number" class="form-control input-numero" path="numeroArticolo"  readonly="true"/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="livelloPdc">V Livello PdC</label>
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-livello-pdc" path="livelloPdc"  readonly="true"/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group">
		<label class="col-sm-${w00} control-label" for="descrizioneDatoSpecificoRiscossione"
			id="labelDescrizioneDatoSpecificoRiscossione"
		>Descrizione dato specifico riscossione</label>
		<div class="col-sm-${w01}">
			<form:input class="form-control input-descrizione-dato-specifico-riscossione" path="descrizioneDatoSpecificoRiscossione" readonly="true" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group">
		<label class="col-sm-${w00} control-label " for="codiceTributo">Codice tributo</label>
		<div class="col-sm-${w00} controls">
			<form:input class="form-control" path="codiceTributo" readonly="true"/>
		</div>
		<a class="suggest"
		   data-explanation="${fn:escapeXml( MEX_SUGGEST_CODICE_TRIBUTO )}"
		   data-trigger="hover"
		   data-placement="top"
		><!--A-->
			<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
		</a>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group">
		<label class="col-sm-${w00} control-label" for="datoSpecificoRiscossione"
			   id="labeldatoSpecificoRiscossione"
		>Dato specifico riscossione tassonomia</label>
		<div class="col-sm-${w01}">
			<form:input class="form-control input-dato-specifico-riscossione" path="datoSpecificoRiscossione" readonly="true"/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="dataInizioValiditaCodiceTassonomico">Data inizio validit&agrave; codice tassonomico </label>
		<div class="col-sm-${w00} controls input-append">
			<form:input path="dataInizioValiditaCodiceTassonomico" id="dataInizioValiditaCodiceTassonomico" class="form-control datepicker input-data" readonly="true" />
		</div>
		<label class="col-sm-2 control-label" for="dataFineValiditaCodiceTassonomico">Data fine validit&agrave; codice tassonomico</label>
		<div class="col-sm-4 controls input-append">
			<form:input path="dataFineValiditaCodiceTassonomico" id="dataFineValiditaCodiceTassonomico" class="form-control datepicker input-data" readonly="true" />
		</div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-${w00}"></div>
		<div class="col-sm-2"></div>
		<div class="col-sm-4"></div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="titolo">Titolo</label>
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-titolo" path="titolo"  readonly="true"/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="codiceTipologia">Tipologia riferimento contabile</label>
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-tipologia-riferimento-contabile" path="tipologia"  readonly="true"/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label" for="codiceCategoria">Categoria riferimento contabile</label>
		<div class="col-sm-${w01} controls">
			<form:input class="form-control input-categoria-riferimento-contabile" path="categoria"  readonly="true"/>
		</div>
	</div>
</div>

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
