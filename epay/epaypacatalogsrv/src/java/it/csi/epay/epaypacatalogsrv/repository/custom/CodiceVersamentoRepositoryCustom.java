/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;

import java.util.List;

public interface CodiceVersamentoRepositoryCustom {

    List<CodiceVersamento> ricerca(RicercaCodiceVersamentoInput input, Long idEnte);
    
//    List<CodiceVersamento> ricercaCovSecondariPerEnte( Long idEnte);

}
