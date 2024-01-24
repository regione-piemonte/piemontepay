/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTProvvisorio;


public interface ProvvisoriRepository extends JpaRepository<CblTProvvisorio, Long> {

    /**
     * ricerca una lista di provvisori a partire dal campo id_ente
     *
     * @param idEnte input
     * @return List<CblTProvvisorio>
     */
    List<CblTProvvisorio> findByIdEnte ( String idEnte );

    /**
     * ricerca una lista di provvisori.
     *
     * @param idEnte input
     * @param identificativoFlusso input
     * @param statoFlusso input
     * @return List<CblTProvvisorio>
     */
    /**
     * @param idEnte
     * @param identificativoFlusso
     * @param statoFlusso
     * @return
     */
    List<CblTProvvisorio> findAllByIdEnteAndIdentificativoFlussoAndStato ( String idEnte, String identificativoFlusso, String statoFlusso );

    /**
     * @param idEnte
     * @param identificativoFlusso
     * @param annoEsercizio
     * @return
     */
    List<CblTProvvisorio> findByIdEnteAndIdentificativoFlussoAndAnnoEsercizio ( String idEnte, String identificativoFlusso, Integer annoEsercizio );

    /**
     * @param idEnte
     * @param identificativoFlusso
     * @param stati
     * @param dataInizio
     * @param dataFine
     * @return
     */
    List<CblTProvvisorio> findByIdEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqualAndDataMovimentoLessThanEqual (
        String idEnte,
        String identificativoFlusso,
        Collection<String> stati, Date dataInizio, Date dataFine );

    List<CblTProvvisorio> findByIdEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqual (
        String idEnte,
        String identificativoFlusso,
        Collection<String> stati, Date dataInizio );

    CblTProvvisorio findOneByIdentificativoFlusso ( String identificativoFlusso );

    /**
     * Ricerca un provvisorio a partire dalla causale e stato.
     *
     * @param causale input
     * @param stato input
     * @return provvisori trovati.
     */
    List<CblTProvvisorio> findAllByIdentificativoFlussoAndStato ( String causale, String stato );

    CblTProvvisorio findByIdEnteAndIdentificativoFlussoAndStato ( String idEnte, String identificativoFlusso, String valoreCampoProvvisoriValido );

}
