/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;


public enum StatoPagamento {
	NON_DEFINITO(-1, "Non definito", Boolean.FALSE),
	DA_PAGARE(0, "Da pagare", Boolean.TRUE),
	COMPILATO(1, "Compilato", Boolean.TRUE),
	IN_ATTESA(2, "In attesa", Boolean.FALSE),
	FALLITO(3, "Fallito", Boolean.TRUE),
	SUCCESSO(4, "Successo", Boolean.FALSE),
	ANNULLATO(5, "Annullato", Boolean.TRUE),
	TRANSAZIONE_INIZIALIZZATA(6, "Transazione inizializzata", Boolean.TRUE),
	TRANSAZIONE_AVVIATA(7, "Transazione avviata", Boolean.FALSE),
	TRANSAZIONE_ERRORE(8, "Transazione errore", Boolean.TRUE),
    INVALIDATO ( 9, "Invalidato dall'ente", Boolean.FALSE ),
    REVOCATO ( 10, "Revocato", Boolean.FALSE ),
    IN_ATTESA_SECONDA_RT ( 11, "In attesa seconda RT", Boolean.FALSE );

	private Integer id;
	private String descrizione;
	private Boolean pagabile;

	private StatoPagamento(Integer id, String descrizione, Boolean pagabile) {
		this.id = id;
		this.descrizione = descrizione;
		this.pagabile = pagabile;
	}

	public static StatoPagamento findById(Integer id) throws IllegalArgumentException {
		for (StatoPagamento r : StatoPagamento.values()) {
			if (r.id.equals(id)) {
				return r;
			}
		}
		throw new IllegalArgumentException("\"id\" non corrisponde a nessun Risultato.");
	}

	public static StatoPagamento findByDescr(String descrizione) throws IllegalArgumentException {
		for (StatoPagamento r : StatoPagamento.values()) {
			if (r.descrizione.equalsIgnoreCase(descrizione)) {
				return r;
			}
		}
		throw new IllegalArgumentException("\"id\" non corrisponde a nessun Risultato.");
	}

	public Integer getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Boolean isPagabile() {
		return pagabile;
	}
}
