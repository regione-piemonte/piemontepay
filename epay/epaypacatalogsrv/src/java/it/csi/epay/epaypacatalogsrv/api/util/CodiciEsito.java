/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.api.util;

import org.apache.commons.lang3.StringUtils;


public enum CodiciEsito {
        ESITO_OK ( "000", "Operazione completata con successo" ),
        CODICE_ERRORE_GENERICO ( "100", "Errore gerico" ),
        DATI_NON_TROVATI ( "101", "Nessun dato trovato" ),
        DATI_INPUT_MANCANTI ( "102", "Dati in input mancanti" ),
        ERRORE_REPERIMENTO_ENTI ( "103", "Errore in fase di reperimento degli enti con riferimenti contabili in scadenza." ),
        ERRORE_REPERIMENTO_PARAM_JOB_ALERT ( "104", "Errore in fase di reperimento del parametro JOB_ALERT_SCADENZA_RIF_CONTAB_RANGE_GIORNI." ),
        ERRORE_REPERIMENTO_RIFERIMENTI_CONTAB_ENTE ( "105", "Errore in fase di reperimento di reperimento dei riferimenti contabili per l'ente %s." ),
        ERRORE_DATI_NON_UNIVOCI ( "106", "Il dato specifico riscossione non e' univoco per codice versamento: %s" ),
        /* DEV/CSI_PAG-2416 - BEGIN1: corretta la frase in modo da essere piu significativa per l'utente */
        ERRORE_DATI_NON_PRESENTI ( "107", "Il pagamento richiesto per %s, non e' attualmente disponibile. E' necessario contattare l'Ente per chiarimenti." ),
        /* DEV/CSI_PAG-2416 - END1 */
        CODICE_ERRORE_ENTE_NON_TROVATO ( "108", "Ente non trovato per CF %s" ),
        CODICE_ERRORE_COV_NON_TROVATO ( "109", "Il codice versamento %s non esiste o non e' piu' valido" ),
        /* DEV/CSI_PAG-2416 - BEGIN2: correzione del segnaposto che prima era $s invece di %s */
        CODICE_ERRORE_REPERIMENTO_DSR ( "110", "Non e' stato possibile reperire i dati specifici riscossione per: %s" );
        /* DEV/CSI_PAG-2416 - END2 */

    private final String codice;

    private final String messaggio;

    CodiciEsito ( final String codice, String messaggio ) {
        this.codice = codice;
        this.messaggio = messaggio;
    }

    public String getCodice () {
        return codice;
    }

    public String getMessaggio () {
        return messaggio;
    }

    private String getMessaggio ( String... parametri ) {
        String msg = messaggio;
        try {
            int i = 0;
            for ( String parametro: parametri ) {
                msg = msg.replace ( "$" + i++, parametro );
            }
            return msg;
        } catch ( Exception e ) {
            throw new RuntimeException ( "Errore preparazione messaggio di errore", e.getCause () );
        }
    }

    public String getMessaggio ( int maxWidth ) {
        return StringUtils.abbreviate ( getMessaggio (), maxWidth );
    }

    public String getMessaggio ( int maxWidth, String... parametri ) {
        return StringUtils.abbreviate ( getMessaggio ( parametri ), maxWidth );
    }
}
