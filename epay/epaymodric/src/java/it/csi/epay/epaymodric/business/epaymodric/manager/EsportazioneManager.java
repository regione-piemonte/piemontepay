/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;

public interface EsportazioneManager {

    List<String> generaFileReportXlsx (DTOInputWsRicercaFlussoOrigine flussoInput, String nomeFile ) throws IOException;

    void generaFileReportCsv (DTOInputWsRicercaFlussoOrigine flussoInput, OutputStream output ) throws IOException;
    
    void generaFileReportContabileXlsx (List<CblTFlussoOrigine> list, OutputStream output ) throws IOException;

    void generaFileReportContabileCsv (List<CblTFlussoOrigine> list, OutputStream output ) throws IOException;
}
