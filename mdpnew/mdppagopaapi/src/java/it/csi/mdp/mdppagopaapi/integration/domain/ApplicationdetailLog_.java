/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.760+0200")
@StaticMetamodel(ApplicationdetailLog.class)
public class ApplicationdetailLog_ {
	public static volatile SingularAttribute<ApplicationdetailLog, Long> seq;
	public static volatile SingularAttribute<ApplicationdetailLog, String> applicationid;
	public static volatile SingularAttribute<ApplicationdetailLog, String> applicationurlback;
	public static volatile SingularAttribute<ApplicationdetailLog, String> applicationurlcancel;
	public static volatile SingularAttribute<ApplicationdetailLog, String> applicationurlerror;
	public static volatile SingularAttribute<ApplicationdetailLog, String> applicationurlresponseko;
	public static volatile SingularAttribute<ApplicationdetailLog, String> applicationurlresponseok;
	public static volatile SingularAttribute<ApplicationdetailLog, String> codiceistat;
	public static volatile SingularAttribute<ApplicationdetailLog, String> contocorrenteposte;
	public static volatile SingularAttribute<ApplicationdetailLog, String> enabled;
	public static volatile SingularAttribute<ApplicationdetailLog, String> flagreturlmdp;
	public static volatile SingularAttribute<ApplicationdetailLog, String> gatewayid;
	public static volatile SingularAttribute<ApplicationdetailLog, String> macAvvio;
	public static volatile SingularAttribute<ApplicationdetailLog, String> macEsito;
	public static volatile SingularAttribute<ApplicationdetailLog, String> mail2buyerko;
	public static volatile SingularAttribute<ApplicationdetailLog, String> mail2buyerok;
	public static volatile SingularAttribute<ApplicationdetailLog, String> mail2esercko;
	public static volatile SingularAttribute<ApplicationdetailLog, String> mail2esercok;
	public static volatile SingularAttribute<ApplicationdetailLog, String> mail2sysko;
	public static volatile SingularAttribute<ApplicationdetailLog, String> mail2sysok;
	public static volatile SingularAttribute<ApplicationdetailLog, String> merchantid;
	public static volatile SingularAttribute<ApplicationdetailLog, String> merchantidpassword;
	public static volatile SingularAttribute<ApplicationdetailLog, String> paymentmodeid;
	public static volatile SingularAttribute<ApplicationdetailLog, BigDecimal> pgactioncode;
	public static volatile SingularAttribute<ApplicationdetailLog, BigDecimal> pgcontabcode;
	public static volatile SingularAttribute<ApplicationdetailLog, BigDecimal> sogliaa;
	public static volatile SingularAttribute<ApplicationdetailLog, BigDecimal> sogliada;
	public static volatile SingularAttribute<ApplicationdetailLog, String> tipobollettinoposte;
	public static volatile SingularAttribute<ApplicationdetailLog, String> tipocommissione;
	public static volatile SingularAttribute<ApplicationdetailLog, String> tipodocumentoposte;
	public static volatile SingularAttribute<ApplicationdetailLog, BigDecimal> valorecommissionemax;
	public static volatile SingularAttribute<ApplicationdetailLog, BigDecimal> valorecommissionemin;
}
