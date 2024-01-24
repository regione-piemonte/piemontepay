/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-23T15:13:51.150+0200")
@StaticMetamodel(SimTFlussoSintesiPagopa.class)
public class SimTFlussoSintesiPagopa_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, Long> id;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, Integer> accertamentoAnno;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, Integer> accertamentoNumero;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, BigDecimal> articolo;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> capitolo;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> codiceVersamento;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> datiSpecificiDiRiscossione;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> descrizioneCodiceVersamento;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> descrizioneDatiSpecifici;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, BigDecimal> importoQuotaAggregazione;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> macrotipo;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, BigDecimal> numeroPagamentiAggregazione;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> pdc;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, Integer> progressivoFlussoSintetico;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, String> tematica;
	public static volatile ListAttribute<SimTFlussoSintesiPagopa, SimTFlussoDettaglioPagopa> simTFlussoDettaglioPagopas;
	public static volatile SingularAttribute<SimTFlussoSintesiPagopa, SimTFlussoOriginePagopa> simTFlussoOriginePagopa;
}
