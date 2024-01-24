/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.sql.Timestamp;
import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppTiporicEp;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppTiporicEpPK;

//@formatter:off
public interface EPaywsoRAppTiporicEpDao extends EPaywsoDaoBase<EPaywsoRAppTiporicEpPK, EPaywsoRAppTiporicEp> {

	public List<EPaywsoRAppTiporicEp> findAllByIdApp(Integer idApp, Timestamp timestamp) throws PersistenceException;
	public List<EPaywsoRAppTiporicEp> findAllByIdTipoRichiesta(Integer idTipoRichiesta, Timestamp timestamp) throws PersistenceException;
	public List<EPaywsoRAppTiporicEp> findAllByIdTipoRichiestaAndIdApp(Integer id, Integer idApp, Timestamp timestamp) throws PersistenceException;
	public List<EPaywsoRAppTiporicEp> findAllByIdTipoRichiestaAndIdApps(Integer idTipoRichiesta, List<Integer> idApps, Timestamp timestamp) throws PersistenceException;

}
//@formatter:on
