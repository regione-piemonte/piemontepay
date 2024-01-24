/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf.cooppec;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.cooppec.EpaypaDErrore;


public interface EpaypaDErroreDao extends EpaypaDaoBase<Integer, EpaypaDErrore> {

    public EpaypaDErrore findByCodiceErrore ( String codiceErrore ) throws PersistenceException;
}
