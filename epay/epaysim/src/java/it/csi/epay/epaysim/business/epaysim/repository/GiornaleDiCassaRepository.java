/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.repository;

import it.csi.epay.epaysim.business.epaysim.model.SimTGiornaleDiCassa;
import it.csi.epay.epaysim.business.epaysim.repository.util.IRepository;

public interface GiornaleDiCassaRepository extends IRepository<SimTGiornaleDiCassa, Long> {

    SimTGiornaleDiCassa findOneByIdentificativoFlussoBt ( String identificativoFlussoBt );
}
