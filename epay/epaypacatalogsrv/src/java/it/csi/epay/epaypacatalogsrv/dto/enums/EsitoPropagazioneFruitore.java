/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.enums;

public enum EsitoPropagazioneFruitore {

        /* la transazione non e' ancora stata avviata */
        WAITING,

        /* il fruitore non e' disponibile */
        UNAVAILABLE,

        /* la transazione non e' riuscita a raggiungere il sottoscrittore */
        FAILED,

        /* il sottoscrittore ha rifiutato la transazione preventivamente */
        REJECTED,

        /* il sottoscrittore ha accettato la transazione ed e' in attesa di conferma */
        COMMIT_PENDING,

        /* il sottoscrittore ha accettato la transazione, ma la richiesta di rollback e' fallita senza essere processata */
        ROLLBACK_FAILED,

        /* il sottoscrittore ha accettato la transazione, ma ha rifiutato la richiesta di rollback */
        ROLLBACK_REJECTED,

        /* il sottoscrittore ha accettato la transazione, ha ricevuto la richiesta di rollback ed ha eseguito il rollback */
        ROLLED_BACK,

        /* il sottoscrittore ha accettato la transazione, ma la richiesta di commit e' fallita senza essere processata */
        COMMIT_FAILED,

        /* il sottoscrittore ha accettato la transazione, ma ha rifiutato la richiesta di commit */
        COMMIT_REJECTED,

        /* il sottoscrittore ha accettato la transazione, ha ricevuto la richiesta di commit ed ha eseguito il commit */
        COMMITTED

}
