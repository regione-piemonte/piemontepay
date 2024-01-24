/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.business.epaymodric.bo.Errore;

/**
 * @author vsgro
 */
public interface ErroreManager {

    public Errore leggi(String codiceErrore);
    

}
