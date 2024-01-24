/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-24T09:27:58.750+0200")
@StaticMetamodel(Email.class)
public class Email_ {
	public static volatile SingularAttribute<Email, Long> id;
	public static volatile SingularAttribute<Email, String> body;
	public static volatile SingularAttribute<Email, Timestamp> data;
	public static volatile SingularAttribute<Email, String> error;
	public static volatile SingularAttribute<Email, Integer> idEnte;
	public static volatile SingularAttribute<Email, String> recipient;
	public static volatile SingularAttribute<Email, Integer> numeroTentativi;
	public static volatile SingularAttribute<Email, String> subject;
	public static volatile SingularAttribute<Email, String> codiceStato;
}
