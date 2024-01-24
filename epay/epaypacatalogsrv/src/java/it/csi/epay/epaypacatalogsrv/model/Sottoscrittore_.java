/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-17T12:57:08.396+0200")
@StaticMetamodel(Sottoscrittore.class)
public class Sottoscrittore_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<Sottoscrittore, Long> id;
	public static volatile SingularAttribute<Sottoscrittore, String> codice;
	public static volatile SingularAttribute<Sottoscrittore, String> descrizione;
	public static volatile SingularAttribute<Sottoscrittore, String> indirizzo;
	public static volatile SingularAttribute<Sottoscrittore, Integer> priorita;
}
