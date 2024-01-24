/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCodiceVersamentoPK;


public interface AssociazioneUtenteCodiceVersamentoRepository extends JpaRepository<AssociazioneUtenteCodiceVersamento, AssociazioneUtenteCodiceVersamentoPK> {

    @Query ( "select associazione from AssociazioneUtenteCodiceVersamento associazione where " + 
    		"id.idUtente = :idUtente and id.idEnte = :idEnte " + 
    		"AND (codiceVersamento.flagAnnullato IS NULL or codiceVersamento.flagAnnullato = false)" )
    List<AssociazioneUtenteCodiceVersamento> findByIdUtenteAndIdEnte ( @Param ( "idUtente" ) Long idUtente, @Param ( "idEnte" ) Integer idEnte );

    @Modifying
    @Query ( "delete from AssociazioneUtenteCodiceVersamento associazione where id.idUtente = :idUtente" )
    void deleteByIdUtente ( @Param ( "idUtente" ) Long idUtente );

    @Query ( "select associazione from AssociazioneUtenteCodiceVersamento associazione where " +
        "id.idCodiceVersamento = :idCodiceVersamento" )
    List<AssociazioneUtenteCodiceVersamento> findByIdCodiceVersamento ( @Param ( "idCodiceVersamento" ) Integer idCodiceVersamento );

}
