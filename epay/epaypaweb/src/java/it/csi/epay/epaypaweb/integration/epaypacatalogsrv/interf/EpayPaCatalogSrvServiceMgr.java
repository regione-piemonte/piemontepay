/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.epaypacatalogsrv.interf;

import java.util.List;

import it.csi.epay.epaypaweb.dto.EnteDto;
import it.csi.epay.epaypaweb.dto.ProfilazioneEpayPaCatalogSrvDto;
import it.csi.epay.epaypaweb.exception.IntegrationException;


public interface EpayPaCatalogSrvServiceMgr {

    public final static String URL_PROPERTY = "epaypacatalogsrv.url";

    public List<EnteDto> getEntiAssociati ( String codiceFiscale ) throws IntegrationException;

    public ProfilazioneEpayPaCatalogSrvDto getProfilazioneUtente ( String codiceFiscale, String codiceEnte ) throws IntegrationException;
}
