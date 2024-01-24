/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager;

import it.csi.epay.epaymodricweb.common.exceptions.NotAllowedException;
import it.csi.epay.epaymodricweb.security.EntityAction;

public interface AuthorizationManager {

    boolean authorize(Class<?> entity, EntityAction action);

    boolean authorize(Class<?> entity, Long id, EntityAction action);

    boolean authorize(Object vo, EntityAction action);

    default void require(Class<?> entity, EntityAction action) {
        if (!this.authorize(entity, action)) {
            throw new NotAllowedException();
        }
    }

    default void require(Class<?> entity, Long id, EntityAction action) {
        if (!this.authorize(entity, id, action)) {
            throw new NotAllowedException();
        }
    }

    default void require(Object vo, EntityAction action) {
        if (!this.authorize(vo, action)) {
            throw new NotAllowedException();
        }
    }

}
