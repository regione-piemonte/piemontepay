/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.business;

import it.csi.epay.epaybeapi.dto.common.MessaggioDTO;
import it.csi.epay.epaybeapi.util.Messages;


/**
 * Servizio per la gestione dei messaggi dinamici
 */
public interface MessageService {

    MessaggioDTO getMessaggio ( Messages message, Object... args );
}
