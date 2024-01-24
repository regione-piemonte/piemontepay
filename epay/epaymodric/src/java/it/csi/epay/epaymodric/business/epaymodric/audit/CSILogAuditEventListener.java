/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import it.csi.epay.epaymodric.business.epaymodric.manager.AuditManager;


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


    private AuditManager getService () {
        return CSILogAuditApplicationConfiguration.getApplicationContext ().getBean ( AuditManager.class );
    }

}
