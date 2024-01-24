/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dao.interf;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dao.EpaypaDaoBase;
import it.csi.epay.epaypaweb.persistence.entity.EpaypaTInvioMail;

public interface EpaypaTInvioMailDao extends EpaypaDaoBase<Long, EpaypaTInvioMail> {

	public EpaypaTInvioMail findNextMailToSend(int seconds) throws PersistenceException;

}
