/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysim.business.epaysim.repository;

import it.csi.epay.epaysim.business.epaysim.model.SimTFlussoOriginePagopa;
import it.csi.epay.epaysim.business.epaysim.repository.custom.SimTFlussoOriginePagopaRepositoryCustom;
import it.csi.epay.epaysim.business.epaysim.repository.util.IRepository;


/**
 *
 */

public interface SimTFlussoOriginePagopaRepository extends IRepository<SimTFlussoOriginePagopa, Long>, SimTFlussoOriginePagopaRepositoryCustom {

    SimTFlussoOriginePagopa findOneByIdentificativoFlusso (String identificativoFlusso);


}
