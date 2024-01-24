/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import java.math.BigInteger;
import java.util.List;


import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTRr;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTTipoRevoca;

public interface EpaypaTTipoRevocaDao extends EpaypaDaoBase<Long, EpaypaTTipoRevoca>{

    public EpaypaTTipoRevoca findTipoRevocaById(int filter) throws PersistenceException;

}
