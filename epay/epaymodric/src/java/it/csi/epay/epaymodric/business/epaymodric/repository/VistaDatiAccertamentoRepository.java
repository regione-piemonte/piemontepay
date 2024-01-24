/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.csi.epay.epaymodric.business.epaymodric.model.CblVDatiAccertamento;

public interface VistaDatiAccertamentoRepository extends JpaRepository<CblVDatiAccertamento, Long> {

    public List<CblVDatiAccertamento> findByIdEnteAndCodiceVersamento ( String idEnte, String codiceVersamento );

    public List<CblVDatiAccertamento> findByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizio (
        String idEnte, String codiceVersamento, String datiSpecificiRiscossione, Integer annoEsercizio);

    @Query ( "select e from CblVDatiAccertamento e where idEnte = ?1 and codiceVersamento in ?2 and datiSpecificiRiscossione = ?3 and annoEsercizio = ?4 and dataInizioValidita <= ?5 and ( dataFineValidita >= ?6 or dataFineValidita is null )" )
    public List<CblVDatiAccertamento> findByIdEnteAndCodiceVersamentoAndDatiSpecificiRiscossioneAndAnnoEsercizioAndBetweenDataInizioValiditaAndDataFineValidita(
        String idEnte, String codiceVersamento, String datiSpecificiRiscossione, Integer annoEsercizio, Timestamp dataInizioValidita, Timestamp dataFineValidita
                    );

}
