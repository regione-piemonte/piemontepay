/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTListaDiCarico;

public interface ListaDiCaricoRepository extends JpaRepository<CblTListaDiCarico, Long> {

    public List<CblTListaDiCarico> findById(Long id);

    public List<CblTListaDiCarico> findAllByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizioOrderByDataFineValiditaDesc ( String idEnte,
        String codiceVersamento,
        String datiSpecificiRiscossione, Integer annoEsercizio );

}
