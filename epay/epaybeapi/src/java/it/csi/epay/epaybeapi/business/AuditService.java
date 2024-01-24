/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaybeapi.business;

import it.csi.epay.epaybeapi.dto.audit.AuditOperation;


/**
 *
 */

public interface AuditService {

    void saveAudit ( AuditOperation operation );

    void saveAudit ( AuditOperation operation, String subject );

    void saveAudit ( AuditOperation operation, String id, String subject );

}
