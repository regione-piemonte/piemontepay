/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.business.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoOutputWsProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.FlussoOrigineErrorePagopaOutputDTO;
import it.csi.epay.epaysimweb.model.flussi.EsportazioneFlussoVO;


public interface EsportazioneManager {

    void esportaTemplateProvvisoriXls ( HttpServletResponse response, DtoOutputWsProvvisori flussiDaEsportare ) throws IOException;

    void esportaTemplateProvvisoriCsv ( HttpServletResponse response, DtoOutputWsProvvisori flussiDaEsportare ) throws IOException;

    public void esportaListaFlussiXls ( HttpServletResponse response, List<EsportazioneFlussoVO> flussiDaEsportare ) throws IOException;

    public void esportaListaFlussiCsv ( HttpServletResponse response, List<EsportazioneFlussoVO> flussiDaEsportare ) throws IOException;

    public void esportaTemplateErroriCsv ( HttpServletResponse response, List<FlussoOrigineErrorePagopaOutputDTO> items ) throws IOException;

    public void esportaTemplateErroriXls ( HttpServletResponse response, List<FlussoOrigineErrorePagopaOutputDTO> items ) throws IOException;

}
