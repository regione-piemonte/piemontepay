/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager;

import java.util.List;

import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsAggiornaStatoReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.report.FileReportVO;
import it.csi.epay.epaymodricweb.model.report.PrenotazioneRicercaReportFormVO;
import it.csi.epay.epaymodricweb.model.report.ReportVO;
import it.csi.epay.epaymodricweb.model.report.RisultatiRicercaReportVO;


public interface PrenotazioneReportManager {
	public List<RisultatiRicercaReportVO> ricercaReport () throws OperationFailedException;
	
	
	public void inserisciPrenotazioneReport ( PrenotazioneRicercaReportFormVO filtro ) throws OperationFailedException;
	
	public FileReportVO aggiornaStato (Long idExport) throws OperationFailedException, UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;
	
	
}
