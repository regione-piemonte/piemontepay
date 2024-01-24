/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import org.hibernate.service.spi.ServiceException;

import it.csi.epay.epaymodric.business.epaymodric.bo.StatoFlussoErrore;

/**
 *
 */

public interface StatoFlussoErroreManager {
    
    public StatoFlussoErrore inserisciFlussoErrore(StatoFlussoErrore statoFlussoErrore) throws ServiceException;
    
}
