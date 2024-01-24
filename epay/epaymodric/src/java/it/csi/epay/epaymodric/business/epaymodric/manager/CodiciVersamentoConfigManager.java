/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.interfacews.epaymodric.exception.RiconciliazioneException;

public interface CodiciVersamentoConfigManager {
    
	/*
	 * Restuisce il flag per ente e per codice versamento */
    public Boolean leggiCodiciVersamentoConfig(String idEnte, String codiceVersamento) throws RiconciliazioneException;
}
