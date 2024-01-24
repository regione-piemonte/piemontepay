<!--
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2
-->

<style>

.input-voce-entrata {
	max-width: 30em;
}

.input-codice-versamento {}

.input-tematica {
	max-width: 30em;
}

.input-macrotipo {
	max-width: 30em;
}

.input-bic {
	max-width: 9em;
	text-transform: uppercase;
}

.input-iban {
	max-width: 22em;
	text-transform: uppercase;
}

.input-multibeneficiario {
	max-width: 700px;
}

.input-email {
	max-width: 22em;
}

.input-tipo-pagamento {
	max-width: 22em;
}

.input-nome {
	max-width: 22em;
}

.input-cognome {
	max-width: 22em;
}

.input-codice-fiscale {
	max-width: 12em;
	text-transform: uppercase;
}

.input-categoria-funzione {}

.input-funzione {}

.input-nome-cognome {
	max-width: 30em;
}

.input-filtro-codice-versamento {}

.input-codice {}

.input-descrizione {}

.input-codice-codice-versamento {
	max-width: 7em;
}

.input-descrizione-codice-versamento {}

.input-codice-voce-entrata {
	max-width: 7em;
}

.input-descrizione-voce-entrata {}

.input-data {
	max-width: 7em;
}

.input-descrizione-tipo-dato-specifico-riscossione {}

.input-codice-tipo-dato-specifico-riscossione {
	max-width: 7em;
}

.input-dato-specifico-riscossione {
	max-width: 250em;
}

.input-descrizione-dato-specifico-riscossione {
	max-width: 250em;
}

.input-tipo-servizio {
	max-width: 250em;
}

.input-nome-macro-area {
	max-width: 250em;
}

.input-anno {
	max-width: 7em;
}

.input-numero {
	max-width: 7em;
}

.input-livello-pdc {}

.input-titolo {}

.input-tipologia-riferimento-contabile {}

.input-categoria-riferimento-contabile {}

.input-stato-aggiornamento {}

.input-codice-ente {
	max-width: 12em;
}

.input-denominazione-ente {}

.input-indirizzo {}

.input-civico {
	max-width: 5em;
}

.input-localita { }

.input-cap {
	max-width: 5em;
}

.input-sigla-provincia {
	max-width: 5em;
}

.input-cbill {
	max-width: 5em;
}

.input-gs1gln {
	max-width: 8em;
}

.input-modalita-integrazione {
	max-width: 22em;
}

.input-periodicita-schedulazione-riconciliazione {}

.input-giorno-schedulazione {
	max-width: 5em;
}

.input-modalita-associazione-multibeneficiario {
	max-width: 700px;
}


.input-descrizione-text-cov {
    max-width: 22em;
}

.input-passprase {
    max-width: 22em;
}



.input-tipologia-accertamento {}
.input-modalita-acquisizione-provvisori {}
.input-utente-amministratore {}

.input-codice-prefisso {
	width: 5em;
}

.input-group-inline {
	display: table;
	width: 100%;
}

select, input[type=text], input[type=password], input[type=datetime],
	input[type=datetime-local], input[type=date], input[type=month], input[type=time],
	input[type=week], input[type=number], input[type=email], input[type=url],
	input[type=search], input[type=tel], input[type=color],
	.uneditable-input {
    padding: 6px 12px;
}

.hidden {
	display: none;
}

.popover {
	color: #222222;
	z-index: 10000;
}

.input-inline {
	display: inline;
}

.suggest {
	display: inline;
	font-size: 1.00em;
	background-color: gray;
	border-radius: 50%;
	padding: 0.75em 1em;
    color: white;
    cursor: pointer;
    margin-left: 1em;
}

.glyphicon-suggest {
	/* NOP */
}

/* Popover */
.popover.popover-warning {
  top: 0px !important; 
  left: 34% !important;
  display: block !important;
  border-color: red !important;
  width:500px !important;
  max-width: 600px !important;
}
/* Popover Header */
.popover-warning > .popover-title {
  font-size: 28px !important;
  text-align:center !important;
}
/* Popover Body */
.popover-warning > .popover-content {
  padding: 5px !important;
}
/* Popover Arrow */
.popover-warning > .arrow {
  border-right-color: red !important;
  top: 15px  !important;
}

</style>
