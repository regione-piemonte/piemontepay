/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsAggiornaStatoReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsInserisciPrenotazioneReport;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaFlussiDaEsportare;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsRicercaPrenotazioneReport;

public interface PrenotazioneReportManager {
    
	public  List<DTOOutputWsRicercaPrenotazioneReport> ricercaReport (DTOInputWsRicercaPrenotazioneReport input);
    /**
     * 
     * 
     * @param 
     * @return 
     */
    public DTOOutputWsInserisciPrenotazioneReport inserisciPrenotazioneReport(DTOInputWsInserisciPrenotazioneReport input);
    public DTOOutputWsAggiornaStatoReport aggiornaStatoReport(DTOInputWsAggiornaStatoReport input);
    public void cancellazioneReport(List<String> reportStatusCodes, int thresholdDays);
    List<CblTReport> readReportDacancellare(List<String> reportStatusCodes, int thresholdDays);
    void cancellaReport (CblTReport report);
    public void crateReport ( int maxReportNumber, int recordsPageSize );
    boolean isRunning();
    public DTOOutputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare ( DTOInputWsRicercaFlussiDaEsportare ricercaFlussiDaEsportare );
}
