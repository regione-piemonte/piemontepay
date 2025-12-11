/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.business;

import it.csi.epay.epayapi.dto.common.MessaggioDTO;
import it.csi.epay.epayapi.util.Messages;


/**
 * Servizio per la gestione dei messaggi dinamici
 */
public interface MessageService {

    MessaggioDTO getMessaggio ( Messages message, Object... args );
}
