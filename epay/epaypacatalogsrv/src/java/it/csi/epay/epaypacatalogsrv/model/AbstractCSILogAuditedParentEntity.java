/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import it.csi.epay.epaypacatalogsrv.business.audit.CSILogAuditEventListener;


@MappedSuperclass
@EntityListeners ( CSILogAuditEventListener.class )
public abstract class AbstractCSILogAuditedParentEntity {

    public abstract String getPrimaryKeyRepresentation ();
}
