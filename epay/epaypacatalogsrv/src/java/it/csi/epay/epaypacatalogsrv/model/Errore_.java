/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.615+0200")
@StaticMetamodel(Errore.class)
public class Errore_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<Errore, Integer> id;
	public static volatile SingularAttribute<Errore, String> codice;
	public static volatile SingularAttribute<Errore, String> codiceApplicativo;
	public static volatile SingularAttribute<Errore, String> codiceLingua;
	public static volatile SingularAttribute<Errore, String> messaggio;
}
