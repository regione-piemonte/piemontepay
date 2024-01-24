/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-09-17T12:57:02.808+0200")
@StaticMetamodel(SimTFlussoOrigineErrore.class)
public class SimTFlussoOrigineErrore_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Long> id;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> codiceErrore;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> codiceVersamento;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Integer> contatoreTentativi;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Date> dataRegolamento;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Date> dataoraFlusso;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> descrizioneErrore;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> ibanRiversamentoFlusso;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Long> idElaborazione;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> cfEnteRicevente;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> idMessaggio;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Long> idStatoFlusso;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> identificativoFlusso;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> identificativoPsp;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> identificativoUnivocoRegolamento;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, BigDecimal> importoTotalePagamenti;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, BigDecimal> importoTotalePagamentiIntermediati;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Integer> numeroTotalePagamenti;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, Integer> numeroTotalePagamentiIntermediati;
	public static volatile SingularAttribute<SimTFlussoOrigineErrore, String> xmlFlusso;
}
