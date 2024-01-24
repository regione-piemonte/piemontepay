/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteOutput;


/**
 * Service per i servizi epyapacatalog
 */
public interface GetProfilazioneUtenteCorrenteService {

    
    GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente (GetProfilazioneUtenteCorrenteInput input );
    


}
