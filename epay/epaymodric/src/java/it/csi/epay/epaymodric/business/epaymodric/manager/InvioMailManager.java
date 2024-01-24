/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.EmailEnum;


/**
 * @author vsgro
 */
public interface InvioMailManager {

    public void emailDequeue ();
    
    public void invioMail ( EmailEnum tipoDestinatario, String codiceErrore, String idEnte, String idFlusso, String messaggio );

}
