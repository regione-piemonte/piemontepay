/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.810+0200")
@StaticMetamodel(Icicodiciimm.class)
public class Icicodiciimm_ {
	public static volatile SingularAttribute<Icicodiciimm, IcicodiciimmPK> id;
	public static volatile SingularAttribute<Icicodiciimm, String> causale;
	public static volatile SingularAttribute<Icicodiciimm, Application> application;
}
