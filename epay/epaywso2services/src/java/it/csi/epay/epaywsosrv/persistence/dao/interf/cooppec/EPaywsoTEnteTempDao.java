/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf.cooppec;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.cooppec.EPaywsoTEnteTemp;

public interface EPaywsoTEnteTempDao extends EPaywsoDaoBase<Long, EPaywsoTEnteTemp> {

    public EPaywsoTEnteTemp findOneByIdOperazione ( String idOperazione ) throws PersistenceException;
}
