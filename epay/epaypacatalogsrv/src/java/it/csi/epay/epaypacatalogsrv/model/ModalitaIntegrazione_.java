/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-30T15:14:04.895+0100")
@StaticMetamodel(ModalitaIntegrazione.class)
public class ModalitaIntegrazione_ extends AbstractCSILogAuditedParentEntity_ {
	public static volatile SingularAttribute<ModalitaIntegrazione, Integer> id;
	public static volatile SingularAttribute<ModalitaIntegrazione, String> codice;
	public static volatile SingularAttribute<ModalitaIntegrazione, String> descrizione;
}
