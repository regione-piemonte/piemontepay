/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository.custom;

import java.util.List;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOProvvisorio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaProvvisori;


public interface ProvvisoriRepositorySpecCustom {

    public List<DTOProvvisorio> cercaPerFiltro ( DTOInputWsRicercaProvvisori ricercaProvvisori );

}
