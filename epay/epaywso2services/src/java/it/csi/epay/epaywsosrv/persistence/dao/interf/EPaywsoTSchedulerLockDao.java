/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.persistence.dao.interf;

import it.csi.epay.epaywsosrv.exception.SchedulerException;
import it.csi.epay.epaywsosrv.persistence.entity.EPaywsoTSchedulerLock;

public interface EPaywsoTSchedulerLockDao {

	public EPaywsoTSchedulerLock getLock() throws SchedulerException;

}
