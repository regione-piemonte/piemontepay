/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLogAudit;


public interface LogAuditManager {

    //	public List<LogAudit> getAllCsiLogAudit();

    public DTOInputWsLogAudit salvaLogAudit ( DTOInputWsLogAudit logAudit );

    //	public List<LogAudit> getCsiLogAudit(String IdAction);

}
