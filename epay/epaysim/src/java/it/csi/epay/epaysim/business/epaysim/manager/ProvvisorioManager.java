/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.manager;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio;
import it.csi.epay.epaysim.dto.GiornaleDiCassaDTO;
import it.csi.epay.epaysim.dto.InserisciProvvisorioOutputDTO;
import it.csi.epay.epaysim.dto.ProvvisorioDTO;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPARequest;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPAResponse;


public interface ProvvisorioManager {

    /**
     * Metodo per la ricerca dei provvisori.
     *
     * @param RicercaProvvisoriPagoPARequest DTO contenente i dati del filtro di ricerca.
     * @return RicercaProvvisoriPagoPAResponse contiente la lista dei risultati che soddisfano il filtro di ricerca.
     */
    RicercaProvvisoriPagoPAResponse ricercaProvvisoriPagoPA ( RicercaProvvisoriPagoPARequest ricercaProvvisoriPagoPARequest );

    /**
     * Metodo che inserisce un provvisorio.
     *
     * @param provvisorioDTO DTO da inserire.
     * @param idGiornaleDiCassa id del giornale cassa a cui fa riferimento il provvisorio.
     * @param cf_utente codice fiscale dell'utente che ha apportato le modifiche.
     * @return InserisciProvvisorioOutputDTO contiente l'oggetto di controllo e l'entita' appena inserita.
     */
    InserisciProvvisorioOutputDTO inserisciProvvisorio ( ProvvisorioDTO provvisorioDTO, Long idGiornaleDiCassa, String cf_utente );

    /**
     * Metodo per la ricerca il giornale di casa con un dato identificativo flusso BT.
     *
     * @param IdentificativoFlussoBT e' il filtro di ricerca.
     * @return GiornaleDiCassaDTO contiente il risultato che soddisfano il filtro di ricerca.
     */
    GiornaleDiCassaDTO ricercaGiornaleDiCassa ( String identificativoFlussoBT ) throws DatatypeConfigurationException;;

    /**
     * Metodo che inserisce un giornale di cassa.
     *
     * @param xmlFlusso xml da inserire.
     * @param cf_utente codice fiscale dell'utente che effettua la modifica
     * @return InserisciGiornaleDiCassaOutputDTO contiente l'oggetto di controllo e l'entita' appena inserita.
     * @throws DatatypeConfigurationException
     */
    GiornaleDiCassaDTO inserisciGiornaleDiCassa ( String xmlFlusso, String identificativoFlussoBT, String cf_utente ) throws DatatypeConfigurationException;

    SimTProvvisorio find ( Long id );

    List<SimTProvvisorio> find ( String cfEnte, String string, Collection<String> stati, Date fromDate, Date toDate );
}
