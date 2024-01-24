/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import it.csi.epay.epaywsosrv.exception.PersistenceException;
import it.csi.epay.epaywsosrv.persistence.dao.EPaywsoDaoBase;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppRicEsitoInvio;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoRAppRicEsitoInvioPK;

public interface EPaywsoRAppRicEsitoInvioDao extends EPaywsoDaoBase<EPaywsoRAppRicEsitoInvioPK, EPaywsoRAppRicEsitoInvio> {

	public EPaywsoRAppRicEsitoInvio findOneByIdRichiestaAndIdApp(Long idRichiesta, Integer idApp) throws PersistenceException;
	public EPaywsoRAppRicEsitoInvio findOneByIdRichiestaAndIdEsitoInvio(Long idRichiesta, Long idEsitoInvio) throws PersistenceException;

}
