/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-06T11:19:49.043+0200")
@StaticMetamodel(AbstractChangeTrackedParentEntity.class)
public class AbstractChangeTrackedParentEntity_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<AbstractChangeTrackedParentEntity, String> utenteInserimento;
	public static volatile SingularAttribute<AbstractChangeTrackedParentEntity, Timestamp> dataInserimento;
	public static volatile SingularAttribute<AbstractChangeTrackedParentEntity, String> utenteModifica;
	public static volatile SingularAttribute<AbstractChangeTrackedParentEntity, Timestamp> dataModifica;
}
