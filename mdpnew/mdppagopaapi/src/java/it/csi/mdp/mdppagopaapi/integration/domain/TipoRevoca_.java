/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.922+0200")
@StaticMetamodel(TipoRevoca.class)
public class TipoRevoca_ {
	public static volatile SingularAttribute<TipoRevoca, Long> id;
	public static volatile SingularAttribute<TipoRevoca, String> codice;
	public static volatile SingularAttribute<TipoRevoca, String> descrizione;
	public static volatile ListAttribute<TipoRevoca, Rr> rrs;
}
