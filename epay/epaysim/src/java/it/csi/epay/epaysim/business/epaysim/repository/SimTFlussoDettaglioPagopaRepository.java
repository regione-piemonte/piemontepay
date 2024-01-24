/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.repository;

import java.util.List;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoDettaglioPagopa;
import it.csi.epay.epaysim.business.epaysim.repository.util.IRepository;

/**
 *
 */
public interface SimTFlussoDettaglioPagopaRepository extends IRepository<SimTFlussoDettaglioPagopa, Long> {

    List<SimTFlussoDettaglioPagopa> findAllBySimTFlussoSintesiPagopa_Id ( Long id );

}
