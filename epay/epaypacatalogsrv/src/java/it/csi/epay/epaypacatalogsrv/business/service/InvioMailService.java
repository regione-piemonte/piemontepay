/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service;

import java.util.Map;

import it.csi.epay.epaypacatalogsrv.business.enums.EmailEnum;
import it.csi.epay.epaypacatalogsrv.business.enums.EmailStatoEnum;


public interface InvioMailService {

    public Map<EmailStatoEnum, Integer> elaboraCodaEmail ();

    public void inviaMail ( EmailEnum template, String destinatario, Long idEnte, Map<String, String> model );

    public void inviaMail ( EmailEnum template, String destinatario, Long idEnte );

    public void inviaMail ( EmailEnum template, Long idEnte );

}
