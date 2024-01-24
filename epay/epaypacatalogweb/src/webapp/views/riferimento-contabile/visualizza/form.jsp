<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<c:set var="w00" value="3" />
<c:set var="w01" value="9" />
<c:set var="w02" value="6" />
<c:set var="w03" value="5" />

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="descrizioneCodiceVersamento">Codice versamento </label>
		<div class="col-sm-${w01} controls">
			<input type="text" class="form-control input-codice-versamento" id="descrizioneCodiceVersamento" 
				value="<c:out 
				value='${modifica_riferimento_contabile.codiceCodiceVersamento}' /> - <c:out 
				value='${modifica_riferimento_contabile.descrizioneCodiceVersamento}' />" disabled="disabled" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="descrizioneVoceEntrata">Voce entrata PPay </label>
		<div class="col-sm-${w01} controls">
			<input type="text" class="form-control input-descrizione-voce-entrata" id="descrizioneVoceEntrata" 
				value="<c:out 
				value='${modifica_riferimento_contabile.codiceVoceEntrata}' /> - <c:out 
				value='${modifica_riferimento_contabile.descrizioneVoceEntrata}' />" disabled="disabled" />
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group ">
		<label class="col-sm-${w00} control-label " for="dataInizioValidita">Data inizio validit&agrave; </label>
		<div class="col-sm-${w00} controls input-append">
			<form:input path="dataInizioValidita" id="dataInizioValidita" class="form-control input-data" readonly="true" />
		</div>
		<label class="col-sm-2 control-label " for="dataFineValidita">Data fine validit&agrave; </label>
		<div class="col-sm-4 controls input-append">
			<form:input path="dataFineValidita" id="dataFineValidita" class="form-control input-data" readonly="true" />
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
		<label class="col-sm-2 control-label " for="numeroArticolo">Numero Articolo</label>
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
            id="labelDescrizioneDatoSpecificoRiscossione">Descrizione dato specifico riscossione</label>
        <div class="col-sm-${w01}">
            <form:input class="form-control input-descrizione-dato-specifico-riscossione" path="tipoServizioTassonomia" readonly="true" />
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
		><!--C-->
			<span class="glyphicon glyphicon-question-sign glyphicon-suggest"></span>
		</a>
	</div>
</div>

<div class="container-fluid">
	<div class="form-group">
		<label class="col-sm-${w00} control-label" for="prefissoDatoSpecificoRiscossione" id="labelPrefissoDatoSpecificoRiscossione">Dato specifico riscossione tassonomia</label>
		<div class="col-sm-${w01}">
			<div>
				<form:input class="form-control input-dato-specifico-riscossione" path="datoSpecificoRiscossione" value="${modifica_riferimento_contabile.datoSpecificoRiscossioneTassonomia}" readonly="true" />
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
    <div class="form-group " id="dateValiditaDatoSpecificoRiscossione">
        <label class="col-sm-${w00} control-label" for="dataInizioValiditaCodiceTassonomico">Data inizio validit&agrave; codice tassonomico</label>
        <div class="col-sm-${w00} controls input-append">
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
<!-- RDI-027.15: TOGLIERE IL CAMPO Stato Attivit? Aggiornamento -->  
<%-- <div class="container-fluid"> 
 	<div class="form-group "> 
 		<label class="col-sm-${w00} control-label" for="descrizioneStatoAggiornamento">Stato attivit&agrave; aggiornamento </label> 
		<div class="col-sm-${w01} controls"> 
 			<form:input class="form-control input-stato-aggiornamento" path="descrizioneStatoAggiornamento" readonly="true" /> 
 		</div> 
 	</div> 
 </div> --%>

<%@ include file="form_elementi_multibeneficiario.jsp"%>
