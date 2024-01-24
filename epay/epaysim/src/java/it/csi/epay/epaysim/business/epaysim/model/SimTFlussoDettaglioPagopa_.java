/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-23T15:19:03.380+0200")
@StaticMetamodel(SimTFlussoDettaglioPagopa.class)
public class SimTFlussoDettaglioPagopa_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, Long> id;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, String> anagraficaPagatore;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, String> causale;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, Timestamp> dataPagamento;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, String> descrizioneCausaleVersamento;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, String> esitoPagamento;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, String> identificativoUnicoRiscossione;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, String> identificativoUnicoVersamento;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, BigDecimal> importoSingoloVersamento;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, Integer> indiceSingoloVersamento;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, String> transactionid;
	public static volatile SingularAttribute<SimTFlussoDettaglioPagopa, SimTFlussoSintesiPagopa> simTFlussoSintesiPagopa;
}
