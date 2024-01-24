/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.sql.Timestamp;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEndpoint;

public interface EPaywsoTEndpointDao extends EPaywsoDaoBase<String, EPaywsoTEndpoint> {

	EPaywsoTEndpoint findOneById(Integer id, Timestamp timestamp) throws PersistenceException;

}
