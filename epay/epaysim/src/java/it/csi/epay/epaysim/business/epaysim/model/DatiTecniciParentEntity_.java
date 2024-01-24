/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-17T12:57:02.761+0200")
@StaticMetamodel(DatiTecniciParentEntity.class)
public class DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<DatiTecniciParentEntity, Date> dataInserimento;
	public static volatile SingularAttribute<DatiTecniciParentEntity, Date> dataModifica;
	public static volatile SingularAttribute<DatiTecniciParentEntity, String> utenteInserimento;
	public static volatile SingularAttribute<DatiTecniciParentEntity, String> utenteModifica;
}
