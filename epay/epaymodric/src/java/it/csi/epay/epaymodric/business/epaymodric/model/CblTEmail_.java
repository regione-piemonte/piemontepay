/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.406+0200")
@StaticMetamodel(CblTEmail.class)
public class CblTEmail_ {
	public static volatile SingularAttribute<CblTEmail, Long> id;
	public static volatile SingularAttribute<CblTEmail, String> body;
	public static volatile SingularAttribute<CblTEmail, Timestamp> data;
	public static volatile SingularAttribute<CblTEmail, String> error;
	public static volatile SingularAttribute<CblTEmail, String> idEnte;
	public static volatile SingularAttribute<CblTEmail, String> recipient;
	public static volatile SingularAttribute<CblTEmail, Long> retry;
	public static volatile SingularAttribute<CblTEmail, String> stato;
	public static volatile SingularAttribute<CblTEmail, String> subject;
}
