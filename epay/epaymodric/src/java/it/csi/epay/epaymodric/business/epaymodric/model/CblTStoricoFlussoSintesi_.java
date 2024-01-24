/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.567+0200")
@StaticMetamodel(CblTStoricoFlussoSintesi.class)
public class CblTStoricoFlussoSintesi_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Long> id;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Integer> accertamentoAnno;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Integer> accertamentoNro;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Integer> articolo;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, String> capitolo;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, String> codiceVersamento;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, String> datiSpecificiRiscossione;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, String> descrizioneCodiceVersamento;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Long> idErrore;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Long> idFlussoOrigine;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Long> idIstitutoRicevente;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, String> identificativoFlusso;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, BigDecimal> importoQuotaAggregazione;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, BigDecimal> numeroVersQuotaAggregazione;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, String> pianoDeiConti;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Integer> provvisorioAnno;
	public static volatile SingularAttribute<CblTStoricoFlussoSintesi, Integer> provvisorioNro;
}
