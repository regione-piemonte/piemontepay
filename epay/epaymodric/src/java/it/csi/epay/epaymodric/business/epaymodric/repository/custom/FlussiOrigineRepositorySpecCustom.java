/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository.custom;

import org.springframework.data.domain.Page;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaFlussoOrigine;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsFlussoOrigine;


public interface FlussiOrigineRepositorySpecCustom {

    public DTOOutputWsFlussoOrigine cercaPerFiltro ( DTOInputWsRicercaFlussoOrigine ricercaFlussi );
    public Page<CblTFlussoOrigine> cercaFlussiPerFiltro ( DTOInputWsRicercaFlussoOrigine ricercaFlussi );
    public Integer contaFlussiPerFiltro ( DTOInputWsRicercaFlussoOrigine ricerca );

}
