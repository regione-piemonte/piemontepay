/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.924+0200")
@StaticMetamodel(Transazione.class)
public class Transazione_ {
	public static volatile SingularAttribute<Transazione, String> transactionId;
	public static volatile SingularAttribute<Transazione, BigDecimal> amount;
	public static volatile SingularAttribute<Transazione, String> applicationId;
	public static volatile SingularAttribute<Transazione, String> authornumber;
	public static volatile SingularAttribute<Transazione, String> basketId;
	public static volatile SingularAttribute<Transazione, String> buyerEmail;
	public static volatile SingularAttribute<Transazione, String> buyercodfisc;
	public static volatile SingularAttribute<Transazione, String> buyername;
	public static volatile SingularAttribute<Transazione, String> ccy;
	public static volatile SingularAttribute<Transazione, Timestamp> changestatedate;
	public static volatile SingularAttribute<Transazione, String> clientipaddress;
	public static volatile SingularAttribute<Transazione, BigDecimal> commissioniApplicate;
	public static volatile SingularAttribute<Transazione, String> errcode;
	public static volatile SingularAttribute<Transazione, Timestamp> finishDate;
	public static volatile SingularAttribute<Transazione, String> incassokoerrormessage;
	public static volatile SingularAttribute<Transazione, Timestamp> initDate;
	public static volatile SingularAttribute<Transazione, String> intestatariocc;
	public static volatile SingularAttribute<Transazione, String> language;
	public static volatile SingularAttribute<Transazione, String> merchantId;
	public static volatile SingularAttribute<Transazione, BigDecimal> mscsorderid;
	public static volatile SingularAttribute<Transazione, BigDecimal> oldstate;
	public static volatile SingularAttribute<Transazione, String> opernumber;
	public static volatile SingularAttribute<Transazione, String> paymentid;
	public static volatile SingularAttribute<Transazione, String> payurl;
	public static volatile SingularAttribute<Transazione, String> pgresultcode;
	public static volatile SingularAttribute<Transazione, String> providertimestamp;
	public static volatile SingularAttribute<Transazione, String> rispcomp;
	public static volatile SingularAttribute<Transazione, Timestamp> startDate;
	public static volatile SingularAttribute<Transazione, String> userhaschange;
	public static volatile ListAttribute<Transazione, MdpErrori> mdpErroris;
	public static volatile ListAttribute<Transazione, OptAttr> optAttrs;
	public static volatile SingularAttribute<Transazione, Gateway> gateway;
	public static volatile SingularAttribute<Transazione, Paymentmode> paymentmode;
	public static volatile SingularAttribute<Transazione, StatoTransazione> statoTransazione;
	public static volatile ListAttribute<Transazione, TransazioneExtraAttribute> transazioneExtraAttributes;
}
