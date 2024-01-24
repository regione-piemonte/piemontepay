/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.905+0200")
@StaticMetamodel(Rt.class)
public class Rt_ {
	public static volatile SingularAttribute<Rt, Integer> id;
	public static volatile SingularAttribute<Rt, String> applicationId;
	public static volatile SingularAttribute<Rt, BigDecimal> commissioniapplicatepsp;
	public static volatile SingularAttribute<Rt, Date> dataInvioFallito;
	public static volatile SingularAttribute<Rt, Timestamp> dataInvioFruitore;
	public static volatile SingularAttribute<Rt, Timestamp> dataMsgRicevuta;
	public static volatile SingularAttribute<Rt, Integer> idEsitoPagamento;
	public static volatile SingularAttribute<Rt, String> idMsgRicevuta;
	public static volatile SingularAttribute<Rt, String> idMsgRichiesta;
	public static volatile SingularAttribute<Rt, Integer> idRr;
	public static volatile SingularAttribute<Rt, Timestamp> insertDate;
	public static volatile SingularAttribute<Rt, String> iuv;
	public static volatile SingularAttribute<Rt, Timestamp> lastUpdate;
	public static volatile SingularAttribute<Rt, byte[]> rtData;
	public static volatile SingularAttribute<Rt, String> sorgenteInvioFruitore;
	public static volatile SingularAttribute<Rt, BigDecimal> statoInvio;
	public static volatile SingularAttribute<Rt, String> statoInvioFruitore;
	public static volatile SingularAttribute<Rt, String> tipoFirma;
	public static volatile SingularAttribute<Rt, String> transactionId;
}
