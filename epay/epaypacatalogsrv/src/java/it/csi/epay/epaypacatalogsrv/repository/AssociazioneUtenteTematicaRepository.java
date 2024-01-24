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

import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematica;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteTematicaPK;


public interface AssociazioneUtenteTematicaRepository extends JpaRepository<AssociazioneUtenteTematica, AssociazioneUtenteTematicaPK> {

    @Query ( "select associazione from AssociazioneUtenteTematica associazione where id.idUtente = :idUtente and id.idEnte = :idEnte" )
    List<AssociazioneUtenteTematica> findByIdUtenteAndIdEnte ( @Param ( "idUtente" ) Long idUtente, @Param ( "idEnte" ) Integer idEnte );

    @Modifying
    @Query ( "delete from AssociazioneUtenteTematica associazione where id.idUtente = :idUtente" )
    void deleteByIdUtente ( @Param ( "idUtente" ) Long idUtente );

    @Query ( "select associazione from AssociazioneUtenteTematica associazione where id.codTematica = :codiceTematica" )
    List<AssociazioneUtenteTematica> findByCodiceTematica ( @Param ( "codiceTematica" ) String codiceTematica );
}
