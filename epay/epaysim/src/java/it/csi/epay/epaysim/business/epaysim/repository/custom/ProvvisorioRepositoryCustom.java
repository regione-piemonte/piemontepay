/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.repository.custom;

import java.util.List;

import it.csi.epay.epaysim.business.epaysim.model.SimTProvvisorio;
import it.csi.epay.epaysim.dto.ws.flussi.riconciliazioneversamentipsptypes.RicercaProvvisoriPagoPARequest;


public interface ProvvisorioRepositoryCustom {

    List<SimTProvvisorio> search ( RicercaProvvisoriPagoPARequest ricercaProvvisoriPagoPARequest );

}
