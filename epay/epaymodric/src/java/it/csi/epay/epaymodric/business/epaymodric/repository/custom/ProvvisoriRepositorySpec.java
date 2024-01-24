/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository.custom;

import java.util.ArrayList;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTProvvisorio;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOProvvisorio;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaProvvisori;


public interface ProvvisoriRepositorySpec extends IRepository<CblTProvvisorio, Long>, ProvvisoriRepositorySpecCustom {

    @Override
    public ArrayList<DTOProvvisorio> cercaPerFiltro ( DTOInputWsRicercaProvvisori ricerca );

}
