/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.dto.tassonomia.ElencoTassonomiaPerCodiceTipoEnteCreditoreInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput;


/**
 * Service per i servizi tassonomia
 */
public interface TassonomiaService {

    
    
    ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput getElencoTassonomiaPerCodiceTipoEnteCreditore ( ElencoTassonomiaPerCodiceTipoEnteCreditoreInput input);
    
//    TassonomiaOutput getTassonomiaByCodTipologiaServizioAndCodTipoEnteCreditore (   String codiceTipologiaServizio, String codiceEnte);
    


}
