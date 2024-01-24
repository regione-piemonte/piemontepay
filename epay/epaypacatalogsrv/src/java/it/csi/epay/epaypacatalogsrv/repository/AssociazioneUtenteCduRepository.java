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

import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCdu;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneUtenteCduPK;


public interface AssociazioneUtenteCduRepository extends JpaRepository<AssociazioneUtenteCdu, AssociazioneUtenteCduPK>,
	JpaSpecificationExecutor<AssociazioneUtenteCdu> {

    @Query ( "select associazione from AssociazioneUtenteCdu associazione where id.idUtente = :idUtente and id.idEnte = :idEnte" )
    List<AssociazioneUtenteCdu> findByIdUtenteAndIdEnte ( @Param ( "idUtente" ) Long idUtente, @Param ( "idEnte" ) Integer idEnte );

    @Query ( "select associazione from AssociazioneUtenteCdu associazione where id.codCdu = :codCdu and id.idEnte = :idEnte" )
    List<AssociazioneUtenteCdu> findByCodiceCduAndIdEnte ( @Param ( "codCdu" ) String codiceCdu, @Param ( "idEnte" ) Integer idEnte );

    @Query ( "select associazione from AssociazioneUtenteCdu associazione where cdu.categoria.codice = :codCatCdu and id.idEnte = :idEnte" )
    List<AssociazioneUtenteCdu> findByCodiceCategoriaCduAndIdEnte ( @Param ( "codCatCdu" ) String codiceCategoriaCdu, @Param ( "idEnte" ) Integer idEnte );

    @Modifying
    @Query ( "delete from AssociazioneUtenteCdu associazione where id.idUtente = :idUtente" )
    void deleteByIdUtente ( @Param ( "idUtente" ) Long idUtente );
}
