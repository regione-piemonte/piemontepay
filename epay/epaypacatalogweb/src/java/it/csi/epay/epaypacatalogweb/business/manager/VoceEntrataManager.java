/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.ppay.RicercaVoceEntrataPPayFiltroVO;
import it.csi.epay.epaypacatalogweb.model.ppay.VoceEntrataPPayVO;

public interface VoceEntrataManager {

    public List<VoceEntrataPPayVO> ricercaVoceEntrata ( RicercaVoceEntrataPPayFiltroVO filter ) throws OperationFailedException;

    public List<VoceEntrataPPayVO> ricercaVoceEntrataDisponibile ( RicercaVoceEntrataPPayFiltroVO filter ) throws OperationFailedException;

    public List<VoceEntrataPPayVO> getOpzioniVoceEntrata ();

    public VoceEntrataPPayVO getVoceEntrataByCodice ( String codice ) throws OperationFailedException;

}
