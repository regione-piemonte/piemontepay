/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.business.enums.ParametriConfigurazione;
import it.csi.epay.epaypacatalogsrv.vo.ConfigurazioneVO;

public interface ConfigurazioneService {

    ConfigurazioneVO getParametro ( ParametriConfigurazione parametro, Long idEnte );

    ConfigurazioneVO getParametro ( ParametriConfigurazione parametro );

    ConfigurazioneVO richiediParametro ( ParametriConfigurazione parametro, Long idEnte );

    ConfigurazioneVO richiediParametro ( ParametriConfigurazione parametro );

    ConfigurazioneVO getParametroNoCache ( ParametriConfigurazione parametro );

}
