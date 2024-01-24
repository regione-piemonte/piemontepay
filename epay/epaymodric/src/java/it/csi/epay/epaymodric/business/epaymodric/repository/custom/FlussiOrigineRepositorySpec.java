/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.repository.custom;

import org.springframework.data.domain.Page;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;


public interface FlussiOrigineRepositorySpec extends IRepository<CblTFlussoOrigine, Long>, FlussiOrigineRepositorySpecCustom {

    @Override
    public DTOOutputWsFlussoOrigine cercaPerFiltro ( DTOInputWsRicercaFlussoOrigine ricercaFlussi );
    @Override
    public Page<CblTFlussoOrigine> cercaFlussiPerFiltro ( DTOInputWsRicercaFlussoOrigine ricercaFlussi);
    @Override
    public Integer contaFlussiPerFiltro ( DTOInputWsRicercaFlussoOrigine ricerca );
}
