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

@Generated(value="Dali", date="2022-10-03T12:46:30.201+0200")
@StaticMetamodel(CblTFlussoDettaglio.class)
public class CblTFlussoDettaglio_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTFlussoDettaglio, Long> id;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> anagraficaPagatore;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> descrizioneCausaleVersamento;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> codiceVersamento;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> codicefiscalePagatore;
	public static volatile SingularAttribute<CblTFlussoDettaglio, Timestamp> dataPagamento;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> datiSpecificiDiRiscossione;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> esitoPagamento;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> identificativoUnicoRiscossione;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> identificativoUnicoVersamento;
	public static volatile SingularAttribute<CblTFlussoDettaglio, BigDecimal> importoSingoloVersamento;
	public static volatile SingularAttribute<CblTFlussoDettaglio, Integer> indiceSingoloVersamento;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> statoInvioFruitore;
	public static volatile SingularAttribute<CblTFlussoDettaglio, String> transactionid;
	public static volatile SingularAttribute<CblTFlussoDettaglio, CblTFlussoSintesi> cblTFlussoSintesi;
	public static volatile SingularAttribute<CblTFlussoDettaglio, CblDCategoriaIuv> cblDCategoriaIuv;
}
