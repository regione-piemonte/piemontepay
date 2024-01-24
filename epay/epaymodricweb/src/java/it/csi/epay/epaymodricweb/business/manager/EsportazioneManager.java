/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.business.manager;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsProvvisori;
import it.csi.epay.epaymodricweb.model.flusso.RicercaFlussoFiltroVO;


/**
 *
 */
public interface EsportazioneManager {

    public void esportaListaFlussiXls ( HttpServletResponse response, RicercaFlussoFiltroVO filtro ) throws IOException, OperationFailedException;

    public void esportaListaFlussiCsv ( HttpServletResponse response, RicercaFlussoFiltroVO filtro ) throws IOException, OperationFailedException;

    void esportaModelloTemplateProvvisoriCsv ( HttpServletResponse response ) throws IOException;

    void esportaModelloTemplateProvvisoriXsl ( HttpServletResponse response ) throws IOException;

    void esportaTemplateProvvisoriXls ( HttpServletResponse response, DtoOutputWsProvvisori flussiDaEsportare ) throws IOException;

    void esportaTemplateProvvisoriCsv ( HttpServletResponse response, DtoOutputWsProvvisori flussiDaEsportare) throws IOException;

}
