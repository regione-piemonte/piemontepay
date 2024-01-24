/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.utilities;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public enum CodiciEsito {
    ESECUZIONE_OK("000", "Operazione completata con esito positivo"),
//    ESECUZIONE_OK_V1("200", "Operazione completata con esito positivo"),

    ESECUZIONE_PARZIALMENTE_OK("050","Operazione eseguita parzialmente"),
    ESECUZIONE_TUTTA_KO("051","Nessuna posizione debitoria inserita"),

    NUMERO_TRASMISSIONE_ERRORE("150", "Messaggio con id $0 gia' elaborato!"),
    CF_ENTE_ERRORE("151", "Codice fiscale ente creditore non trovato ($0)"),
    CODICE_VERSAMENTO_NON_TROVATO_ERRORE("152", "Codice versamento $0 non trovato"),
    CODICE_VERSAMENTO_NON_UNIVOCO_ERRORE("153", "Codice versamento $0 non univoco"),
    POSIZIONE_DEBITORIE_NUMERO_ERRORE("154","Non corrisponde il numero di posizioni debitorie"),
    POSIZIONI_DEBITORIE_IMPORTO_ERRORE("155","Non corrisponde l'importo totale delle posizioni debitorie"),
    POSIZIONI_DEBITORIE_IMPORTO_NULLO("156", "Impossibile eseguire l'operazione: in una o piu' posizioni debitorie l'elemento ImportoTotale non e' presente oppure non e' valorizzato"),
    TIPO_PAGAMENTO_NON_ATTIVO_ERRORE("157", "Tipo pagamento del versamento non attivo"),
    TIPO_PAGAMENTO_NON_PERMESSO_ERRORE ( "158", "Tipo pagamento del versamento non permesso" ),
    TIPO_PAGAMENTO_MARCA_ERRORE ( "159", "Tipo pagamento del versamento non permesso in quanto afferente a marca da bollo" ),
    TIPO_PAGAMENTO_SPONTANEO_ERRORE ( "197", "Tipo pagamento del versamento non permesso in quanto afferente a pagamento spontaneo" ),
    INSERIMENTO_PAGAMENTO_FALLITO("160", "Errore durante il caricamento di una posizione debitoria."),
    CODICE_PAGAMENTO_RIF_ENTE_DUPLICATO("161", "IdPosizioneDebitoria $2 gia' utilizzato per ente con C.F. $0 e CodiceVersamento $1 "),
    CODICE_PAGAMENTO_RIF_ENTE_OBBLIGATORIO("162", "Elemento IdPosizioneDebitoria mancante. L'elemento e' obbligatorio"),
    IMPORTO_OBBLIGATORIO("163", "Il valore dell'elemento ImportoTotale deve essere maggiore di zero."),
    CAUSALE_OBBLIGATORIA("164", "Elemento DescrizioneCausaleVersamento mancante. L'elemento e' obbligatorio"),
    IMPORTO_COMPONENTE_OBBLIGATORIO("198", "Elemento Importo della componente mancante. L'elemento e' obbligatorio"),
    ANAGRAFICA_OBBLIGATORIA("165", "Elemento SoggettoPagatore mancante. L'elemento e' obbligatorio"),
    FLAG_PERSONA_FISICA_OBBLIGATORIO("166", "Inserire l'elemento PersonaFisica oppure l'elemento PersonaGiuridica. L'elemento e' obbligatorio"),
    CODICE_FISCALE_OBBLIGATORIO("167", "Elemento IdentificativoUnivocoFiscale mancante. L'elemento e' obbligatorio"),
    NOME_OBBLIGATORIO("168","Elemento Nome mancante. L'elemento e' obbligatorio"),
    COGNOME_OBBLIGATORIO("169","Elemento Cognome mancante.L'elemento e' obbligatorio"),
    RAGIONESOCIALE_OBBLIGATORIA("170","Elemento RagioneSociale mancante. L'elemento e' obbligatorio"),
    CODICE_IUV_OBBLIGATORIO("171", "IUV mancante. L'elemento e' obbligatorio"),
    MOTIVAZIONE_OBBLIGATORIO("172", "Motivazione mancante. L'elemento e' obbligatorio"),
    TIPOAGGIORNAMENTO_OBBLIGATORIO("173", "Tipo Aggiornamento mancante. L'elemento e' obbligatorio"),
    TIPOAGGIORNAMENTO_SCONOSCIUTO("174", "Tipo Aggiornamento sconosciuto. Operazione non prevista."),
    AGGIORNAMENTO_PAGAMENTO_FALLITO("175", "Errore durante l'aggiornamento di una posizione debitoria."),
    PAGAMENTO_NON_TROVATO_PER_POSIZIONE_DEBITORIA("176", "Posizione debitoria non trovata per C.F ente $0, codice versamento $1, id posizione debitoria $2."),
    PAGAMENTO_NON_TROVATO_PER_IDENTIFICATIVO_PAGAMENTO("176", "Posizione debitoria non trovata per C.F ente $0, codice versamento $1, identificativo pagamento $2."),
    PAGAMENTO_IN_ATTESA("177", "Posizione debitoria in corso di pagamento"),
    PAGAMENTO_GIA_EFFETTUATO("178", "Posizione debitoria gia' pagata"),
    PAGAMENTO_GIA_ANNULLATO("179", "Posizione debitoria gia' annullata"),
    CODICE_ID_POSIZIONE_DEBITORIA_OBBLIGATORIO("180", "IdPosizioneDebitoria mancante. L'elemento e' obbligatorio"),
    PAGAMENTO_NON_MODIFICABILE("181", "Pagamento (id Posizione Debitoria : $0) non modificabile."),
    CODICE_ID_POSIZIONE_DEBITORIA_DUPLICATA("182", "IdPosizioneDebitoria non univoca. (Ente: $0 - Codice Versamento $1 - IdPosizioneDebitori $2"),
    TROPPI_DETTAGLI_PAGAMENTO("183", "Il pagamento ha troppi dettagli (piu' di 5). (Ente: $0 - Codice Versamento $1 - IdPosizioneDebitori $2"),
    ERRORE_TOTALE_IMPORTO_COMPONENTI("184", "ImportoTotale ($3 Euro) differente dalla somma degli importi delle componenti ($4 Euro) del pagamento per IdPosizioneDebitoria $2, ente con C.F. $0 e CodiceVersamento $1 "),
    ERRORE_DATE_VALIDITA("185", "Incongruenza nelle date di validita' per il pagamento con IdPosizioneDebitoria $2, ente con C.F. $0 e CodiceVersamento $1 "),
	ERRORE_DATI_SPECIFICI_RISCOSSIONE_COMPONENTI("186", "Dati Specifici Riscossione ImportoTotale obbligatori per la componente dell'importo."),
    PAGAMENTO_SCADUTO("187", "Pagamento gia' scaduto"),
    PAGAMENTO_MAI_ATTIVO("188", "Pagamento mai attivo"),
    COMPONENTE_PAGAMENTO_ANNO_ACCERTAMENTO ( "191", "Anno accertamento della componente importo mancante" ),
    COMPONENTE_PAGAMENTO_NUMERO_ACCETAMENTO ( "192", "Numero accertamento della componente importo mancante" ),
    RIFERIMENTO_PAGAMENTO_VUOTO ( "193", "Dati mancanti nel riferimento pagamento" ),
    RIFERIMENTO_PAGAMENTO_PERSONA_FISICA_ERRATA ( "194", "Dati anagrafici della persona fisica errati" ),
    RIFERIMENTO_PAGAMENTO_PERSONA_GIURIDICA_ERRATA ( "195", "Dati anagrafici della persona giuridica errati" ),

    SOGGETTO_PAGATORE_NON_ANONIMO ( "218", "Valore originario dei campi: Cognome, Nome o CF diverso da 'ANONIMO'" ),
    IMPORTO_MAGGIORE_DI_ZERO ( "196", "Anagrafica non aggiornabile per importo diverso da 0 (mod.Multe)" ),
    

    POSIZIONI_DEBITORIE_IMPORTO_TOTALE_OBBLIGATORIO("300", "Impossibile eseguire l'operazione: in una o piu' posizioni debitorie l'elemento ImportoTotale e' obbligatorio in presenza di altri importi"),
    POSIZIONI_DEBITORIE_IMPORTO_PRINCIPALE_OBBLIGATORIO("300", "Impossibile eseguire l'operazione: in una o piu' posizioni debitorie l'elemento ImportoPrincipale e' obbligatorio in presenza di altri importi"),
    POSIZIONI_DEBITORIE_IMPORTO_SECONDARIO_OBBLIGATORIO("300", "Impossibile eseguire l'operazione: in una o piu' posizioni debitorie l'elemento ImportoSecondario e' obbligatorio in presenza di altri importi"),
    

    IMPORTI_OBBLIGATORI("300", "I valori di importo totale, importo principale e importo sescondario devono essere maggiori di zero."),
    
    //RDI-41 - RDI-45
    ERRORE_DATI_SPECIFICI_RISCOSSIONE ( "157", "Errore reperimento dati specifici riscossione" ),
    ERRORE_TIPO_PAGAMENTO_NON_MULTIBENEFICIARIO ( "189",
                    "Il codice versamento $0 non e' configurato come Multi-beneficiario" ),
    ERRORE_TIPO_PAGAMENTO_MULTIBENEFICIARIO ( "189",
            "Il codice versamento $0 e' configurato come Multi-beneficiario" ),

    SERVIZIO_DISABILITATO ( "189", "Funzione disabilitata. Si prega di utilizzare la nuova versione del servizio."),
    PAGAMENTO_SECONDARIO_NON_TROVATO ( "189", "Errore in fase di reperimento del pagamento secondario relativo al pagamento: $0"),
	PAGAMENTO_SECONDARIO_ENTE_NON_TROVATO ( "189", "Ente non trovato per il pagamento secondario"),
    TIPO_PAGAMENTO_SECONDARIO_NON_TROVATO_ERRORE ( "189", "Non esiste alcun Codice Versamento secondario correttamente associato al codice versamento $0" ),
    TIPO_PAGAMENTO_SECONDARIO_NON_UNIVOCO_ERRORE ( "189", "Il codice versamento associato al codice versamento $0 non e' univoco" ),
    ERRORE_IMPORTO_SECONDARIO_ALTRO_ENTE_ASSENTE ( "189",
                    "Posizione debitoria Multi-beneficiario, necessario valorizzare il campo ImportoSecondarioAltroEnte" ),
    ERRORE_COMPONENTE_IMPORTO_SECONDARIO_ASSENTE ( "189",
                    "Posizione debitoria Multi-beneficiario, necessario valorizzare il blocco ComponentiImportoSecondario" ),
    ERRORE_UN_SOLO_IMPORTO_SECONDARIO ( "189",
                    "Posizione debitoria Multi-beneficiario, necessario valorizzare solo un ComponenteImporto per il blocco ComponentiImportoSecondario" ),
    ERRORE_SOMMA_IMPORTI_PRINCIPALE_E_SECONDARIO ( "189",
                    "Posizione debitoria Multi-beneficiario, la somma degli importi indicati in ImportoSecondarioAltroEnte e ImportoPrincipale non corrisponde ad ImportoTotale" ),
    ERRORE_SOMMA_IMPORTI_PRINCIPALE ( "189",
                    "Posizione debitoria Multi-beneficiario, la somma degli importi indicati in ComponenteImporto non corrisponde al valore ImportoPrincipale" ),
    ERRORE_SOMMA_IMPORTI_SECONDARIO ( "189",
                    "Posizione debitoria Multi-beneficiario, la somma degli importi indicati in ComponentiImportoSecondario non corrisponde al valore ImportoSecondarioAltroEnte" ),
    ERRORE_IMPORTO_PRINCIPALE_ASSENTE ( "189",
                    "Posizione debitoria Multi-beneficiario, necessario valorizzare il campo importoPrincipale" ),
    //RDI-41 - RDI-45 FINE



    CARICAMENTO_ERRORE("200", "Errore caricamento."),
    MDP_SERVICES_ERRORE ( "250", "Errore caricamento. Problemi tecnici nella generazione degli iuv. [$0]" ),

    COOP_ERRORE_GENERICO ( "300", "Errore generico: $0" ),
    COOP_ERRORE_NON_PRONTO ( "301", "Il servizio non e' pronto" ),
    COOP_ERRORE_INTERNO ( "399", "Errore interno (CoopApplicativaPEC)" );
    ;

	private final String codice;
    private final String messaggio;


    private final LogUtil log = new LogUtil(CodiciEsito.class);

    CodiciEsito(final String codice, String messaggio) {
        this.codice = codice;
        this.messaggio = messaggio;
    }

    public String getCodice() {
        return codice;
    }

    private String getMessaggio() {
        return messaggio;
    }

    private String getMessaggio(String ... parametri) {
        String msg = messaggio;
        try {
            int i = 0;
            for (String parametro : parametri) {
                msg = msg.replace("$" + i++, parametro);
            }
            return msg;
        } catch (Exception e) {
			String methodName = "CodiciErrore";
			log.error( methodName, ExceptionUtils.getStackTrace(e));
            throw new RuntimeException("Errore preparazione messaggio di errore", e.getCause());
        }
    }

    public String getMessaggio(int maxWidth) {
        return StringUtils.abbreviate(getMessaggio(), maxWidth);
    }

    public String getMessaggio(int maxWidth, String ... parametri) {
        return  StringUtils.abbreviate(getMessaggio(parametri), maxWidth);
    }


}
