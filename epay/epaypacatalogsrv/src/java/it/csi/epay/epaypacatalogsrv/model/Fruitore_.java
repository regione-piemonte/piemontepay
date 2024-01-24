/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.619+0200")
@StaticMetamodel(Fruitore.class)
public class Fruitore_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<Fruitore, Integer> id;
	public static volatile SingularAttribute<Fruitore, String> codice;
	public static volatile SingularAttribute<Fruitore, Date> dataFineValidita;
	public static volatile SingularAttribute<Fruitore, Date> dataInizioValidita;
	public static volatile SingularAttribute<Fruitore, String> descrizione;
}
