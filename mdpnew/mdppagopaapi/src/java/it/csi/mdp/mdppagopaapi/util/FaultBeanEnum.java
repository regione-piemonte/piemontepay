/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.util;


public enum FaultBeanEnum {
    PAA_ID_DOMINIO_ERRATO("La PAA non corrisponde al Dominio indicato.", "La PAA non corrisponde al Dominio indicato."),
    PAA_ID_INTERMEDIARIO_ERRATO ("Identificativo intermediario non corrispondente." , "Identificativo intermediario non corrispondente."),
    PAA_STAZIONE_INT_ERRATA ("Stazione intermediario non corrispondente.", "Stazione intermediario non corrispondente."),
    PAA_RPT_SCONOSCIUTA("La RPT risulta sconosciuta.", "La RPT risulta sconosciuta."),
		PAA_RT_DUPLICATA ( "La RT e' gia' stata accettata.", "La RT e' gia' stata accettata." ),
    PAA_TIPOFIRMA_SCONOSCIUTO("Il campo tipoFirma non corrisponde ad alcun valore previsto.", "Il campo tipoFirma non corrisponde ad alcun valore previsto."),
    PAA_ERRORE_FORMATO_BUSTA_FIRMATA("Formato busta di firma errato o non corrispondente al tipoFirma.", "Formato busta di firma errato o non corrispondente al tipoFirma."),
    PAA_FIRMA_ERRATA("Errore di firma.", "Errore di firma."),
    PAA_FIRMA_INDISPONIBILE("Impossibile firmare.", "Impossibile firmare."),
    PAA_SINTASSI_XSD("Errore di sintassi XSD.", "Errore di sintassi XSD."),
    PAA_SINTASSI_EXTRAXSD("Errore di sintassi extra XSD.", "Errore di sintassi extra XSD."),
    PAA_SEMANTICA("Errore semantico.", "Errore semantico."),
    PAA_PAGAMENTO_SCONOSCIUTO("Pagamento in attesa risulta sconosciuto all'Ente Creditore.", "Pagamento in attesa risulta sconosciuto all'Ente Creditore."),
    PAA_PAGAMENTO_DUPLICATO("Pagamento in attesa risulta concluso all'Ente Creditore.", "Pagamento in attesa risulta concluso all'Ente Creditore."),
    PAA_PAGAMENTO_IN_CORSO("Pagamento in attesa risulta in corso all'Ente Creditore.", "Pagamento in attesa risulta in corso all'Ente Creditore."),
    PAA_PAGAMENTO_ANNULLATO("Pagamento in attesa risulta annullato all'Ente Creditore.", "Pagamento in attesa risulta annullato all'Ente Creditore."),
    PAA_PAGAMENTO_SCADUTO("Pagamento in attesa risulta scaduto all'Ente Creditore.", "Pagamento in attesa risulta scaduto all'Ente Creditore."),
    PAA_SYSTEM_ERROR ("Errore generico.", "Errore generico."),
		PAA_TROVATO_POSTAL_CODE_MA_NESSUN_IBAN_ASSOCIATO ( "Errore iban postale non utilizzato dall'ente", "iban postale non utilizzato dall'ente" ),
    PAA_ATTIVA_RPT_IMPORTO_NON_VALIDO ("L'importo del pagamento in attesa non e' congruente con il dato indicato dal PSP", "L'importo del pagamento in attesa non e' congruente con il dato indicato dal PSP"),
        PAA_GETPAYMENT_CONTROLLO_IMPORTI ( "La somma degli importi singolo versamento e il totale da versare non corrispondono",
            "La somma degli importi singolo versamento e il totale da versare non corrispondono" ),
        PAA_GETPAYMENT_ERRORE_DATI_PAGAMENTO ( "Errore nei dati multiversamento per multibenerficiario",
            "Errore nei dati multiversamento per multibenerficiario" );

    private final String faultString;
    private final String description;

    FaultBeanEnum (String faultString, String description) {
        this.faultString = faultString;
        this.description = description;
    }

    public String getFaultString() {
        return faultString;
    }

    public String getDescription() {
        return description;
    }
}
