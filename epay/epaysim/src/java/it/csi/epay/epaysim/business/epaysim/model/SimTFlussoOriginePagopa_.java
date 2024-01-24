/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysim.business.epaysim.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@Generated ( value = "Dali", date = "2018-10-23T15:13:51.088+0200" )
@StaticMetamodel(SimTFlussoOriginePagopa.class)
public class SimTFlussoOriginePagopa_ extends DatiTecniciParentEntity_ {

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Long> id;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> cfEnteCreditore;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Timestamp> dataOraMessaggio;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Date> dataRegolamento;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> denominazioneEnte;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> denominazionePsp;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> idMessaggio;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> identificativoFlusso;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> identificativoPsp;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> identificativoUnivocoRegolamento;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, BigDecimal> importoTotalePagamenti;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, BigDecimal> importoTotalePagamentiIntermediati;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Integer> numeroTotalePagamenti;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Integer> numeroTotalePagamentiIntermediati;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Integer> provvisorioAnno;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Integer> provvisorioNumero;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, Boolean> statoElaborazioneFlusso;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> utenteInserimento;

    public static volatile SingularAttribute<SimTFlussoOriginePagopa, String> utenteModifica;

    public static volatile ListAttribute<SimTFlussoOriginePagopa, SimTFlussoSintesiPagopa> simTFlussoSintesiPagopas;
}
