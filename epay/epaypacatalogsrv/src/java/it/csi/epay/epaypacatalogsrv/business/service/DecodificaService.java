/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import java.util.Map;

import it.csi.epay.epaypacatalogsrv.dto.MessageDto;


public interface DecodificaService {

	Map<String, String> getMessaggi(String codiceLingua);

	String getMessaggio(String codice);

    MessageDto getMessaggio ( String codice, Object... parametri );
}
