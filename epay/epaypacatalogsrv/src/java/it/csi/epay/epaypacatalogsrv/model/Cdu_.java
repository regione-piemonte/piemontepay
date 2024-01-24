/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.610+0200")
@StaticMetamodel(Cdu.class)
public class Cdu_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<Cdu, Integer> id;
	public static volatile SingularAttribute<Cdu, String> codice;
	public static volatile SingularAttribute<Cdu, String> descrizione;
	public static volatile SingularAttribute<Cdu, CategoriaCdu> categoria;
}
