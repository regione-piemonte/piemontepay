/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.797+0200")
@StaticMetamodel(FlussoRiversamento.class)
public class FlussoRiversamento_ {
	public static volatile SingularAttribute<FlussoRiversamento, Integer> id;
	public static volatile SingularAttribute<FlussoRiversamento, String> codicebicbancadiriversamento;
	public static volatile SingularAttribute<FlussoRiversamento, Timestamp> datainserimento;
	public static volatile SingularAttribute<FlussoRiversamento, Timestamp> datamodifica;
	public static volatile SingularAttribute<FlussoRiversamento, Timestamp> dataoraflusso;
	public static volatile SingularAttribute<FlussoRiversamento, Timestamp> dataregolamento;
	public static volatile SingularAttribute<FlussoRiversamento, String> denominazionemittente;
	public static volatile SingularAttribute<FlussoRiversamento, String> denominazionericevente;
	public static volatile SingularAttribute<FlussoRiversamento, String> identificativoflusso;
	public static volatile SingularAttribute<FlussoRiversamento, String> identificativoistitutomittente;
	public static volatile SingularAttribute<FlussoRiversamento, String> identificativoistitutoricevente;
	public static volatile SingularAttribute<FlussoRiversamento, String> identificativopsp;
	public static volatile SingularAttribute<FlussoRiversamento, String> identificativounivocoregolamento;
	public static volatile SingularAttribute<FlussoRiversamento, BigDecimal> importototalepagamenti;
	public static volatile SingularAttribute<FlussoRiversamento, Integer> numerototalepagamenti;
	public static volatile SingularAttribute<FlussoRiversamento, String> versioneoggetto;
	public static volatile SingularAttribute<FlussoRiversamento, String> xmlflusso;
	public static volatile SingularAttribute<FlussoRiversamento, StatoInvioFlussoRiversamento> statoInvioFlussoRiversamento1;
	public static volatile SingularAttribute<FlussoRiversamento, StatoInvioFlussoRiversamento> statoInvioFlussoRiversamento2;
}
