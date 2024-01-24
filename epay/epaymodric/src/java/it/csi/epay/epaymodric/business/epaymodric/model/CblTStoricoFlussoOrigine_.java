/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.562+0200")
@StaticMetamodel(CblTStoricoFlussoOrigine.class)
public class CblTStoricoFlussoOrigine_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Long> id;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Integer> contatoreTentativi;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Timestamp> dataoraFlusso;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Long> idElaborazione;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Long> idIstitutoRicevente;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Long> idStatoFlusso;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, String> identificativoFlusso;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, String> identificativoIstitutoRicevente;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, String> identificativoPsp;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, BigDecimal> importoTotalePagamenti;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, BigDecimal> importoTotalePagamentiIntermediati;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Integer> numeroTotalePagamenti;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Integer> numeroTotalePagamentiIntermediati;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, String> xmlFlusso;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, Date> dataRegolamento;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, String> identificativoUnivocoRegolamento;
	public static volatile SingularAttribute<CblTStoricoFlussoOrigine, CblDTipoAcquisizione> cblDTipoAcquisizione;
}
