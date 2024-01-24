/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.repository;

import java.util.List;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoSintesiPagopa;
import it.csi.epay.epaysim.business.epaysim.repository.util.IRepository;


/**
 *
 */
public interface SimTFlussoSintesiPagopaRepository extends IRepository<SimTFlussoSintesiPagopa, Long> {

    List<SimTFlussoSintesiPagopa> findAllBySimTFlussoOriginePagopa_id ( Long id );


}
