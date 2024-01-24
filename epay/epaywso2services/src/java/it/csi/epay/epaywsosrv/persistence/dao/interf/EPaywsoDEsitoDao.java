/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoDEsito;

public interface EPaywsoDEsitoDao extends EPaywsoDaoBase<String, EPaywsoDEsito> {

	public EPaywsoDEsito findOneByCod(String cod) throws PersistenceException;

}
