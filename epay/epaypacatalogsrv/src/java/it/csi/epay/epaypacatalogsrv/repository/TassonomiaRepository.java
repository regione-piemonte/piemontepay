/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository;

import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.custom.TassonomiaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface TassonomiaRepository extends JpaRepository<Tassonomia, Long> ,
											  JpaSpecificationExecutor<Tassonomia>,
                                              TassonomiaRepositoryCustom{

    
	List<Tassonomia> findByCodTipoEnteCreditore ( String codiceEnteCreditore );
	
	List<Tassonomia> findByCodTipologiaServizioAndCodTipoEnteCreditore ( String codTipologiaServizio , String codiceEnteCreditore );

	List<Tassonomia> findByFlagAggiornato ( boolean flagAggiornato );

	Tassonomia findById ( long id );
}
