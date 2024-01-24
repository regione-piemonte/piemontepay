/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import java.io.Serializable;


public interface IEntity<ID extends Serializable> extends Serializable {

    ID getPrimaryKey ();

}
