/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;


public interface AuditManager {

    void prePersist ( Object target );

    void preUpdate ( Object target );

    void preRemove ( Object target );

    void preExport ( String className, List<String> list );

}
