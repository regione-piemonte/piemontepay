/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.business.manager;

import java.util.List;

import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsFlussoOrigine;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteElaborazioneReport;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsRicercaLimiteEsportazione;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.EpaymodricException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.TipoFileReportEnum;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.model.GenericVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussiSintesiVO;
import it.csi.epay.epaymodricweb.model.flusso.FlussoDettaglioVO;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;
import it.csi.epay.epaymodricweb.model.report.FileReportVO;

public interface FlussoManager {

    DtoOutputWsFlussoOrigine ricercaFlussoOrigine(RicercaFlussoFiltroVO filtro, String sortingCol, String sortingDir) throws OperationFailedException;

    FlussiSintesiVO ricercaFlussiSintesi(Long idFlussoOrigine) throws OperationFailedException;

    FlussiDettaglioVO ricercaFlussiDettaglio(Long idFlussoSintesi) throws OperationFailedException;

    FlussoDettaglioVO ricercaFlussoDettaglio(Long idFlussoSintesi, Long idFlussoDettaglio) throws OperationFailedException;

    FileReportVO ricercaFlussiDaEsportare ( RicercaFlussoFiltroVO filtro, TipoFileReportEnum tipoFileReport ) throws OperationFailedException;

    GenericVO inserisciFlussiDaRielaborare ( List<String> identificativiFlussoDaRielaborare ) throws OperationFailedException;

	DtoOutputWsRicercaLimiteEsportazione ricercaLimiteEsportazione() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;
    
	DtoOutputWsRicercaLimiteElaborazioneReport ricercaLimiteElaborazioneReport() throws UnrecoverableException_Exception, Exception_Exception, EpaymodricException_Exception;

}
