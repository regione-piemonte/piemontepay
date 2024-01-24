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

import it.csi.epay.epaypacatalogsrv.model.AssociazioneCodiceVersamentoPrimarioSecondario;
import it.csi.epay.epaypacatalogsrv.model.AssociazioneCodiceVersamentoPrimarioSecondarioPK;


public interface AssociazioneCodiceVersamentoPrimarioSecondarioRepository extends JpaRepository<AssociazioneCodiceVersamentoPrimarioSecondario, AssociazioneCodiceVersamentoPrimarioSecondarioPK> {

	 @Query ( "select associazione from AssociazioneCodiceVersamentoPrimarioSecondario associazione where " +
		        "id.idCodiceVersamentoSecondario = :idCodiceVersamentoSecondario" )
    List<AssociazioneCodiceVersamentoPrimarioSecondario> findByIdCodiceVersamentoSecondario 
    (  @Param ( "idCodiceVersamentoSecondario" ) Integer idCodiceVersamentoSecondario );
    
	 
    @Modifying
    @Query ( "delete from AssociazioneCodiceVersamentoPrimarioSecondario associazione where id.idCodiceVersamentoSecondario = :idCodiceVersamentoSecondario" )
    void deleteByIdCodiceVersamentoSecondario 
    ( @Param ( "idCodiceVersamentoSecondario" ) Integer idCodiceVersamentoSecondario );
}
