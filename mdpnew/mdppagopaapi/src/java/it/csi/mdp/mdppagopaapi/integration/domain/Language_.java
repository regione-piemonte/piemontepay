/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.830+0200")
@StaticMetamodel(Language.class)
public class Language_ {
	public static volatile SingularAttribute<Language, LanguagePK> id;
	public static volatile SingularAttribute<Language, String> enabled;
	public static volatile SingularAttribute<Language, String> languagedescr;
	public static volatile SingularAttribute<Language, Gateway> gateway;
}
