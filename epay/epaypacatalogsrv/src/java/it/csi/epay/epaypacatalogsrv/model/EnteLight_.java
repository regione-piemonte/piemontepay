/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-05T09:40:12.886+0100")
@StaticMetamodel(EnteLight.class)
public class EnteLight_ extends AbstractChangeTrackedParentEntity_ {
	public static volatile SingularAttribute<EnteLight, Long> id;
	public static volatile SingularAttribute<EnteLight, String> codiceFiscale;
	public static volatile SingularAttribute<EnteLight, String> denominazione;
	public static volatile SingularAttribute<EnteLight, String> email;
}
