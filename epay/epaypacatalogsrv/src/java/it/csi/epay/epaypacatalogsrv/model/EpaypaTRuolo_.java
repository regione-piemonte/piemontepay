/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-29T09:43:34.938+0100")
@StaticMetamodel(EpaypaTRuolo.class)
public class EpaypaTRuolo_ {
	public static volatile SingularAttribute<EpaypaTRuolo, Integer> idRuolo;
	public static volatile SingularAttribute<EpaypaTRuolo, String> codRuolo;
	public static volatile SingularAttribute<EpaypaTRuolo, String> descrizione;
	public static volatile SingularAttribute<EpaypaTRuolo, Integer> idEnte;
}
