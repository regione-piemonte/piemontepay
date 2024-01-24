/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandlerRegister;
import it.csi.epay.epaypacatalogsrv.business.service.AuditService;


/**
 *
 */

public class CSILogAuditEventListener {

    @PrePersist
    public void prePersist ( Object target ) {
        getService ().prePersist ( target );
    }

    @PreUpdate
    public void preUpdate ( Object target ) {
        getService ().preUpdate ( target );
    }

    @PreRemove
    public void preRemove ( Object target ) {
        getService ().preRemove ( target );
    }

    private AuditService getService () {
        return OperationHandlerRegister.getApplicationContext ().getBean ( AuditService.class );
    }

}
