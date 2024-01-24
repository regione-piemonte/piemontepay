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

import it.csi.epay.epaypacatalogsrv.model.AssociazioneRiferimentoContabilePrimarioSecondario;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneRiferimentoContabilePrimarioSecondarioPK;


public interface AssociazioneRiferimentoContabilePrimarioSecondarioRepository extends 
JpaRepository<AssociazioneRiferimentoContabilePrimarioSecondario, 
AssociazioneRiferimentoContabilePrimarioSecondarioPK> {

	 @Query ( "select associazione from AssociazioneRiferimentoContabilePrimarioSecondario associazione where " +
		        "id.idRiferimentoContabileSecondario = :idRiferimentoContabileSecondario" )
    List<AssociazioneRiferimentoContabilePrimarioSecondario> findByIdRiferimentoContabileSecondario 
    (  @Param ( "idRiferimentoContabileSecondario" ) Integer idRiferimentoContabileSecondario );
    
	 
    @Modifying
    @Query ( "delete from AssociazioneRiferimentoContabilePrimarioSecondario associazione "
        + "where id.idRiferimentoContabileSecondario = :idRiferimentoContabileSecondario" )
    void deleteByIdRiferimentoContabileSecondario 
    ( @Param ( "idRiferimentoContabileSecondario" ) Integer idRiferimentoContabileSecondario );
}
