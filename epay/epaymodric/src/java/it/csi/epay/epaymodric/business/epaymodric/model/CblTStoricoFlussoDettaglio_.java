/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.554+0200")
@StaticMetamodel(CblTStoricoFlussoDettaglio.class)
public class CblTStoricoFlussoDettaglio_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, Long> id;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> anagraficaPagatore;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> codiceVersamento;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> codicefiscalePagatore;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, Timestamp> dataPagamento;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> datiSpecificiDiRiscossione;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> descrizioneCausaleVersamento;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> esitoPagamento;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, Long> idFlussoSintesi;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> identificativoUnicoRiscossione;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> identificativoUnicoVersamento;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, BigDecimal> importoSingoloVersamento;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, Integer> indiceSingoloVersamento;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> statoInvioFruitore;
	public static volatile SingularAttribute<CblTStoricoFlussoDettaglio, String> transactionid;
}
