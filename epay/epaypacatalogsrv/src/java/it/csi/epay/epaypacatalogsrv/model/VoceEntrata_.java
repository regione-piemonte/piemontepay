/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.698+0200")
@StaticMetamodel(VoceEntrata.class)
public class VoceEntrata_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<VoceEntrata, Long> id;
	public static volatile SingularAttribute<VoceEntrata, String> codice;
	public static volatile SingularAttribute<VoceEntrata, String> descrizione;
	public static volatile SingularAttribute<VoceEntrata, MacrotipoPpay> macrotipo;
	public static volatile SingularAttribute<VoceEntrata, TematicaPpay> tematica;
}
