/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.common;

public abstract class Constants {

    public final static String ROUTING_SEPARATOR = "/";

    public final static String SEARCH_RESULTS = "results";

    public final static String ESTENSIONE_FILE_CSV = "csv";

    public final static String ESTENSIONE_FILE_XLS = "xlsx";
    
    public final static String FILTRO_IDENTIFICATIVO_FLUSSO= "filtro_identificativoFlusso";
    
    public final static String FILTRO_IUV= "filtro_iuv";
    
    public final static String FILTRO_ID_COD_VERSAMENTO= "filtro_idCodVersamento";
    
    public final static String FILTRO_STATO_FLUSSO= "filtro_statoFlusso";
    
    public final static String FILTRO_DATA_ELABORAZIONE_A= "filtro_dataElaborazioneA";
    
    public final static String FILTRO_DATA_ELABORAZIONE_DA= "dataElaborazioneDa";
    
    public final static String FILTRO_DATA_RICEZIONE_A= "filtro_dataRicezioneA";
    
    public final static String FILTRO_DATA_RICEZIONE_DA= "filtro_dataRicezioneDa";
    
    public final static String FILTRO_PSP= "filtro_psp";
    
    public final static String FILTRO_TIPO_REPORT= "filtro_tipo_report";
    
    public final static String FILTRO_TIPO_FILE= "filtro_tipo_file";
    
    public final static String FLUSSO_INTERMEDIATO_PRESENTE= "SI";
    
    public final static String TIPO_REPORT_CONTABILE= "CONT";
    
    public final static String TIPO_REPORT_FLUSSI_COMPLETI= "RICON";
    
    public final static String TIPO_FILE_CSV= "1";
    
    public final static String TIPO_FILE_XLS= "2";
    
    

    public static class USE_CASES {

        public final static String LOGIN = "LOGIN";

        public final static String RICERCA_FLUSSI = "RICERCA_FLUSSI";

        public final static String RICERCA_ELABORAZIONE = "RICERCA_ELABORAZIONE";

        public final static String RICERCA_PROVVISORIO = "RICERCA_PROVVISORIO";
        
        public final static String INSERISCI_PROVVISORI_GUI = "INSERISCI_PROVVISORI_GUI";
        
        public final static String PRENOTAZIONE_EXPORT = "PRENOTAZIONE_EXPORT";
    }

    static {
    }
}
