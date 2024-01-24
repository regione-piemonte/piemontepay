/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:24.574+0200")
@StaticMetamodel(AssociazioneUtenteCdu.class)
public class AssociazioneUtenteCdu_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<AssociazioneUtenteCdu, AssociazioneUtenteCduPK> id;
	public static volatile SingularAttribute<AssociazioneUtenteCdu, Cdu> cdu;
}
