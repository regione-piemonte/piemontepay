/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.595+0200")
@StaticMetamodel(AssociazioneUtenteTematica.class)
public class AssociazioneUtenteTematica_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<AssociazioneUtenteTematica, AssociazioneUtenteTematicaPK> id;
	public static volatile SingularAttribute<AssociazioneUtenteTematica, TematicaPpay> tematica;
	public static volatile SingularAttribute<AssociazioneUtenteTematica, Boolean> flagVisibilitaTotale;
}
