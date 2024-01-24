/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.756+0200")
@StaticMetamodel(ApplicationcustomfieldsLog.class)
public class ApplicationcustomfieldsLog_ {
	public static volatile SingularAttribute<ApplicationcustomfieldsLog, Long> seq;
	public static volatile SingularAttribute<ApplicationcustomfieldsLog, String> applicationid;
	public static volatile SingularAttribute<ApplicationcustomfieldsLog, String> fielddescription;
	public static volatile SingularAttribute<ApplicationcustomfieldsLog, String> fieldname;
	public static volatile SingularAttribute<ApplicationcustomfieldsLog, String> fieldvalue;
	public static volatile SingularAttribute<ApplicationcustomfieldsLog, String> gatewayId;
	public static volatile SingularAttribute<ApplicationcustomfieldsLog, Long> keyid;
}
