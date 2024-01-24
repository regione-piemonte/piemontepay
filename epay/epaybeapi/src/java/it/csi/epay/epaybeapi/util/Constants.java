/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.util;

/**
 * Classe di utility contenente tutte le costanti
 */
public abstract class Constants {

    /**
     * Nome della componente da utilizzare per il logging
     */
    public final static String COMPONENT_NAME = "epaybeapi";

    /**
     * Classe di utility contenente le cache predisposte
     */
    public static class CACHES {

        /**
         * Cache di default
         */
        public final static String DEFAULT = "defaultCache";

        /**
         * Cache per i parametri di configurazione
         */
        public final static String CONFIGURATION = "configurationCache";

        /**
         * Cache per i messaggi
         */
        public final static String MESSAGES = "messageCache";

    }

    public final static String DEFAULT_PROTOCOL = "application/json";

    public static final String TRUE = "S";

    public static final String FALSE = "N";

    public static final String EJB_RETURN_CODE_KO_BAD_INPUT = "400";

    public static final String EJB_RETURN_CODE_KO_UNAUTHORIZED = "401";

    public static final String RETURN_OK = "000";

    public static final String RETURN_SERVER_PROBLEM = "500";

    /**
     * Generazione PDF
     * 
     */
    public static final String UNA_UNICA_RATA = "con <b>una unica rata</b>";

    public static final String UNICA_RATA = "Utilizza la porzione di avviso relativa al canale di pagamento che preferisci.";

    public static final String DEL_TUO_ENTE_CREDITORE = "del tuo Ente Creditore ";

    public static final String URL_ENTE_CREDITORE = "(https://servizi.regione.piemonte.it/catalogo/portale-dei-pagamenti), ";

    public static final String DI_POSTE_ITALIANE = "di Poste Italiane, ";

    public static final String DELLA_TUA_BANCA = "della tua Banca o degli altri canali di pagamento. Potrai pagare con carte, conto corrente, CBILL.";

    public static final Double MAX_IMPORTO = 999999999.99;

    public static final String PATH_PAGOPA = "jasperReports/images/logo-pagopa@3x.png";

    public static final String PATH_AVVISO_DI_PAGAMENTO = "jasperReports/images/scritta-avviso-di-pagamento@3x.png";

    public static final String PATH_CANALI_DIGITALI = "jasperReports/images/canali-digitali@3x.png";

    public static final String PATH_CANALI_FISICI = "jasperReports/images/canali-fisici@3x.png";

    public static final String PATH_POSTE_ITALIANE = "jasperReports/images/logo-poste-italiane@3x.png";

    public static final String PATH_BOLLETTINO = "jasperReports/images/logo-bollettino-postale@3x.png";

    public static final String PATH_EURO = "jasperReports/images/logo-euro-bollettino@3x.png";

    public static final String PATH_FORBICI = "jasperReports/images/forbici.png";

    public static final String DATAMATRIX_INDIRIZZAMENTO_FASE = "codfase=";

    public static final String DATAMATRIX_CODICE_FASE_ACCETTAZIONE = "NBPA";

    public static final String DATAMATRIX_SEPARATORE = ";";

    public static final String DATAMATRIX_ID_DATAMATRIX = "1";

    public static final String DATAMATRIX_FASE_PAGAMENTO = "P1";

    public static final String DATAMATRIX_VALORE_FINALE_DATAMATRIX = "A";

    public static final String DATAMATRIX_LUNGHEZZA_CODICE_AVVISO = "18";

    public static final int DATAMATRIX_LUNGHEZZA_CONTO = 12;

    public static final String DATAMATRIX_LUNGHEZZA_IMPORTO = "11";

    public static final String DATAMATRIX_LUNGHEZZA_TIPO_DOCUMENTO = "3";

    public static final String DATAMATRIX_TIPO_DOCUMENTO = "896";

    public static final int DATAMATRIX_FILLER = 12;

    public static final int DATAMATRIX_ID_UNIVOCO_DIM = 16;

    public static final int DATAMATRIX_ANAGRAFICA_DIM = 40;

    public static final int DATAMATRIX_CAUSALE_DIM = 110;

    public static final int CODELINE_LENGTH = 51;

    public static final int DATAMATRIX_LENGTH = 257;

    public static final String QRCODE_SEPARATOR = "|";

    public static final String QRCODE_IDENTIFICATIVO = "PAGOPA";

    public static final String QRCODE_VERSIONE = "002";

    public static final int QRCODE_MODULES = 33;

    public static final int SCALED_DIMENSION = 256;
    
    public static final String ANONIMO = "ANONIMO";
}
