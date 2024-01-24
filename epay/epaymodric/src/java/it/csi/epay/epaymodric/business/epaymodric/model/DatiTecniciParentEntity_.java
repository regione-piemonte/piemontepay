/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.593+0200")
@StaticMetamodel(DatiTecniciParentEntity.class)
public class DatiTecniciParentEntity_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<DatiTecniciParentEntity, String> utenteInserimento;
	public static volatile SingularAttribute<DatiTecniciParentEntity, Timestamp> dataInserimento;
	public static volatile SingularAttribute<DatiTecniciParentEntity, String> utenteModifica;
	public static volatile SingularAttribute<DatiTecniciParentEntity, Timestamp> dataModifica;
}
