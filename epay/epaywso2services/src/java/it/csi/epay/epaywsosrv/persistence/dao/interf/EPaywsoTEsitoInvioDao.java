/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import java.util.List;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTEsitoInvio;

public interface EPaywsoTEsitoInvioDao extends EPaywsoDaoBase<Integer, EPaywsoTEsitoInvio> {

	public List<EPaywsoTEsitoInvio> findAllByIdRichiesta(Long idRichiesta) throws PersistenceException;

}
