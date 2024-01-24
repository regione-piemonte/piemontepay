/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-21T10:00:14.485+0200")
@StaticMetamodel(TemplateEmail.class)
public class TemplateEmail_ {
	public static volatile SingularAttribute<TemplateEmail, Integer> id;
	public static volatile SingularAttribute<TemplateEmail, String> codice;
	public static volatile SingularAttribute<TemplateEmail, String> descrizione;
	public static volatile SingularAttribute<TemplateEmail, String> template;
	public static volatile SingularAttribute<TemplateEmail, String> oggetto;
}
