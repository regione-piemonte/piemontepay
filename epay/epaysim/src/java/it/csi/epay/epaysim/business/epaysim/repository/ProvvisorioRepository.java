/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio;
import it.csi.epay.epaysim.business.epaysim.repository.custom.ProvvisorioRepositoryCustom;
import it.csi.epay.epaysim.business.epaysim.repository.util.IRepository;


public interface ProvvisorioRepository extends IRepository<SimTProvvisorio, Integer>, ProvvisorioRepositoryCustom {

    /**
     * Ricerca un provvisorio a partire dalla causale e stato.
     * 
     * @param causale input
     * @param stato input
     * @return provvisori trovati.
     */
    List<SimTProvvisorio> findAllByCausaleAndStato ( String causale, String stato );

    /**
     * @param idEnte
     * @param identificativoFlusso
     * @param stati
     * @param dataInizio
     * @param dataFine
     * @return
     */
    List<SimTProvvisorio> findByCodiceFiscaleEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqualAndDataMovimentoLessThanEqual (
        String cfEnte,
        String identificativoFlusso,
        Collection<String> stati, Date dataInizio, Date dataFine );

    List<SimTProvvisorio> findByCodiceFiscaleEnteAndIdentificativoFlussoIgnoreCaseContainingAndStatoInAndDataMovimentoGreaterThanEqual (
        String cfEnte,
        String identificativoFlusso,
        Collection<String> stati, Date dataInizio );

}
