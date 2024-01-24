/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.dto.common.MessaggioDTO;
import it.csi.mdp.mdppagopaapi.util.Messages;


/**
 * Servizio per la gestione dei messaggi dinamici
 */
public interface MessageService {

    MessaggioDTO getMessaggio ( Messages message, Object... args );
}
