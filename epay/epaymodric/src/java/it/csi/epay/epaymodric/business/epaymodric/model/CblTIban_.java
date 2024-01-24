/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.456+0200")
@StaticMetamodel(CblTIban.class)
public class CblTIban_ {
	public static volatile SingularAttribute<CblTIban, Long> id;
	public static volatile SingularAttribute<CblTIban, String> iban;
	public static volatile SingularAttribute<CblTIban, String> idApplicativoMdp;
	public static volatile SingularAttribute<CblTIban, String> idEnte;
}
