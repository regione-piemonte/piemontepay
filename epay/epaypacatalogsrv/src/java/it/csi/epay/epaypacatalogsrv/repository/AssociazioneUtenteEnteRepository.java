/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEnte;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteEntePK;


public interface AssociazioneUtenteEnteRepository extends JpaRepository<AssociazioneUtenteEnte, AssociazioneUtenteEntePK>,
	JpaSpecificationExecutor<AssociazioneUtenteEnte> {

    @Modifying
    @Query ( "delete from AssociazioneUtenteEnte associazione where id.idUtente = :idUtente" )
    void deleteByIdUtente ( @Param ( "idUtente" ) Long idUtente );

    @Query ( "select associazione from AssociazioneUtenteEnte associazione where id.idUtente = :idUtente and id.idEnte = :idEnte" )
    List<AssociazioneUtenteEnte> findByIdUtenteAndIdEnte ( @Param ( "idUtente" ) Long idUtente, @Param ( "idEnte" ) Integer idEnte );

    @Query ( "select associazione from AssociazioneUtenteEnte associazione where id.idEnte = :idEnte and flagAdmin = :flagAdmin" )
    List<AssociazioneUtenteEnte> findByIdEnteAndFlagAdmin ( @Param ( "idEnte" ) Integer idEnte, @Param ( "flagAdmin" ) Boolean flagAdmin );

}
