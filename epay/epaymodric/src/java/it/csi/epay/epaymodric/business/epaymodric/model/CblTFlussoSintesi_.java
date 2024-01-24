/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.449+0200")
@StaticMetamodel(CblTFlussoSintesi.class)
public class CblTFlussoSintesi_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTFlussoSintesi, Long> id;
	public static volatile SingularAttribute<CblTFlussoSintesi, String> codiceVersamento;
	public static volatile SingularAttribute<CblTFlussoSintesi, String> datiSpecificiDiRiscossione;
	public static volatile SingularAttribute<CblTFlussoSintesi, BigDecimal> importoQuotaAggregazione;
	public static volatile SingularAttribute<CblTFlussoSintesi, BigDecimal> numeroVersQuotaAggregazione;
	public static volatile SingularAttribute<CblTFlussoSintesi, String> pianoDeiConti;
	public static volatile SingularAttribute<CblTFlussoSintesi, Integer> provvisorioAnno;
	public static volatile SingularAttribute<CblTFlussoSintesi, Integer> provvisorioNro;
	public static volatile ListAttribute<CblTFlussoSintesi, CblTFlussoDettaglio> cblTFlussoDettaglios;
	public static volatile SingularAttribute<CblTFlussoSintesi, CblRStatoFlussoErrore> cblRStatoFlussoErrore;
	public static volatile SingularAttribute<CblTFlussoSintesi, CblTEnte> cblTEnte;
	public static volatile SingularAttribute<CblTFlussoSintesi, CblTFlussoOrigine> cblTFlussoOrigine;
	public static volatile SingularAttribute<CblTFlussoSintesi, Integer> articolo;
	public static volatile SingularAttribute<CblTFlussoSintesi, Integer> accertamentoNumero;
	public static volatile SingularAttribute<CblTFlussoSintesi, Integer> accertamentoAnno;
	public static volatile SingularAttribute<CblTFlussoSintesi, String> descrizioneCodiceVersamento;
	public static volatile SingularAttribute<CblTFlussoSintesi, String> capitolo;
	public static volatile SingularAttribute<CblTFlussoSintesi, Date> dataPagamentoCalcolata;
}
