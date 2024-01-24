/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.635+0200")
@StaticMetamodel(Operazione.class)
public class Operazione_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<Operazione, Long> id;
	public static volatile SingularAttribute<Operazione, String> codice;
	public static volatile ListAttribute<Operazione, Sottoscrittore> sottoscrittori;
	public static volatile SingularAttribute<Operazione, Servizio> servizio;
}
