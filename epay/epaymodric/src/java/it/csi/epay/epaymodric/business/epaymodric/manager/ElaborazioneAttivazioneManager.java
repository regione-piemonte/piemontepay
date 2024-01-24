/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;


/**
 * @author vsgro
 */
public interface ElaborazioneAttivazioneManager {

    public void attivaElaborazione (List<String>  statiDaEscludere) throws EpaymodricException;

}
