/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.tassonomia.RicercaTassonomiaFiltroVO;
import it.csi.epay.epaypacatalogweb.model.tassonomia.TassonomiaVO;

public interface TassonomiaManager {

	
    List<TassonomiaVO> getElencoTassonomiaPerCodiceTipoEnteCreditore ();
    
    List<TassonomiaVO> ricercaTassonomia (RicercaTassonomiaFiltroVO input)  throws OperationFailedException ;


}
