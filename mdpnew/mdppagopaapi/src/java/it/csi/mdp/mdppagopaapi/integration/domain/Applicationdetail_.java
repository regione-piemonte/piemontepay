/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.758+0200")
@StaticMetamodel(Applicationdetail.class)
public class Applicationdetail_ {
	public static volatile SingularAttribute<Applicationdetail, ApplicationdetailPK> id;
	public static volatile SingularAttribute<Applicationdetail, String> applicationurlback;
	public static volatile SingularAttribute<Applicationdetail, String> applicationurlcancel;
	public static volatile SingularAttribute<Applicationdetail, String> applicationurlerror;
	public static volatile SingularAttribute<Applicationdetail, String> applicationurlresponseko;
	public static volatile SingularAttribute<Applicationdetail, String> applicationurlresponseok;
	public static volatile SingularAttribute<Applicationdetail, String> codiceistat;
	public static volatile SingularAttribute<Applicationdetail, String> contocorrenteposte;
	public static volatile SingularAttribute<Applicationdetail, String> enabled;
	public static volatile SingularAttribute<Applicationdetail, String> flagreturlmdp;
	public static volatile SingularAttribute<Applicationdetail, String> macAvvio;
	public static volatile SingularAttribute<Applicationdetail, String> macEsito;
	public static volatile SingularAttribute<Applicationdetail, String> mail2buyerko;
	public static volatile SingularAttribute<Applicationdetail, String> mail2buyerok;
	public static volatile SingularAttribute<Applicationdetail, String> mail2esercko;
	public static volatile SingularAttribute<Applicationdetail, String> mail2esercok;
	public static volatile SingularAttribute<Applicationdetail, String> mail2sysko;
	public static volatile SingularAttribute<Applicationdetail, String> mail2sysok;
	public static volatile SingularAttribute<Applicationdetail, String> merchantid;
	public static volatile SingularAttribute<Applicationdetail, String> merchantidpassword;
	public static volatile SingularAttribute<Applicationdetail, BigDecimal> pgactioncode;
	public static volatile SingularAttribute<Applicationdetail, BigDecimal> pgcontabcode;
	public static volatile SingularAttribute<Applicationdetail, BigDecimal> sogliaa;
	public static volatile SingularAttribute<Applicationdetail, BigDecimal> sogliada;
	public static volatile SingularAttribute<Applicationdetail, String> tipobollettinoposte;
	public static volatile SingularAttribute<Applicationdetail, String> tipodocumentoposte;
	public static volatile SingularAttribute<Applicationdetail, BigDecimal> valorecommissionemax;
	public static volatile SingularAttribute<Applicationdetail, BigDecimal> valorecommissionemin;
	public static volatile SingularAttribute<Applicationdetail, Application> application;
	public static volatile SingularAttribute<Applicationdetail, Commission> commission;
	public static volatile SingularAttribute<Applicationdetail, Gateway> gateway;
	public static volatile SingularAttribute<Applicationdetail, Paymentmode> paymentmode;
}
