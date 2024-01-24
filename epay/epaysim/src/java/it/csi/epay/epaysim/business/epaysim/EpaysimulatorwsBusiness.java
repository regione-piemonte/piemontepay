/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim;

import javax.xml.ws.WebServiceContext;

import it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes.ResponseType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.EsitoFlussiPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPAResponse;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TestataTrasmissioneFlussiType;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiInErrorePagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.TrasmettiFlussiPagoPARequest;
import it.csi.epay.epaysim.exception.epaysim.EpaysimulatorException;
import it.csi.epay.epaysim.exception.epaysim.UnrecoverableException;


/**
 * Interfaccia relativa alla definizione dei metodi di business utilizzati per la trasmissione del flusso da acquisire e la ricerca dei provvisori
 */
public interface EpaysimulatorwsBusiness {

    String testResources ( WebServiceContext wsContext, String dummy )
                    throws EpaysimulatorException, Exception, UnrecoverableException;

    /**
     * Metodo per la ricerca di un provvisorio.
     *
     * @param RicercaProvvisoriPagoPAReques ricercaProvvisoriPagoPA DTO i dati del filtro di ricerca.
     * @return RicercaProvvisoriPagoPAResponse DTO contenente la lista dei provvisori che soddisfano il filtro id ricerca.
     */
    RicercaProvvisoriPagoPAResponse ricercaProvvisoriPagoPA (
        RicercaProvvisoriPagoPARequest ricercaProvvisoriPagoPARequest )
                        throws EpaysimulatorException, Exception, UnrecoverableException;

    /**
     * Metodo per la ricerca degli esiti dei flussi.
     *
     * @param TestataFlussoRiconciliazioneType testataFlussoRiconciliazioneType DTO i dati del filtro di ricerca.
     */
    EsitoFlussiPagoPAResponse esitoFlussiPagoPA ( TestataTrasmissioneFlussiType testataTrasmissioneFlussiType )
                    throws EpaysimulatorException, Exception, UnrecoverableException;

    /**
     * Operazione che permette di inserire un flusso in errore.
     *
     * @param flussoInErroreRequest input parameter
     * @return ResponseType oggetto composto da codice e descrizione
     * @throws EpaysimulatorException exception.
     * @throws UnrecoverableException exception.
     * @throws Exception exception.
     */
    public ResponseType flussoInErrore ( TrasmettiFlussiInErrorePagoPARequest trasmettiFlussiInErrorePagoPARequest )
                    throws EpaysimulatorException, UnrecoverableException, Exception;

    /**
     * Operazione che permette di inserire un flusso.
     *
     * @param trasmettiFlussiPagoPARequest oggetto di input
     * @return ResponseType oggetto composto da codice e descrizione
     * @throws EpaysimulatorException exception.
     * @throws UnrecoverableException exception.
     * @throws Exception exception.
     */
    public ResponseType flussoRiconciliazione ( TrasmettiFlussiPagoPARequest trasmettiFlussiPagoPARequest )
                    throws EpaysimulatorException, UnrecoverableException, Exception;

}
