/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-10-03T12:59:16.002+0200")
@StaticMetamodel(CblTFlussoOrigine.class)
public class CblTFlussoOrigine_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTFlussoOrigine, Long> id;
	public static volatile SingularAttribute<CblTFlussoOrigine, Integer> contatoreTentativi;
	public static volatile SingularAttribute<CblTFlussoOrigine, Timestamp> dataoraFlusso;
	public static volatile SingularAttribute<CblTFlussoOrigine, String> identificativoFlusso;
	public static volatile SingularAttribute<CblTFlussoOrigine, String> identificativoIstitutoRicevente;
	public static volatile SingularAttribute<CblTFlussoOrigine, CblTPsp> cblTPsp;
	public static volatile ListAttribute<CblTFlussoOrigine, CblTFlussoSintesi> cblTFlussoSintesis;
	public static volatile ListAttribute<CblTFlussoOrigine, CblRStatoFlussoErrore> cblRStatoFlussoErrores;
	public static volatile SingularAttribute<CblTFlussoOrigine, BigDecimal> importoTotalePagamenti;
	public static volatile SingularAttribute<CblTFlussoOrigine, BigDecimal> importoTotalePagamentiIntermediati;
	public static volatile SingularAttribute<CblTFlussoOrigine, BigDecimal> importoTotalePagamentiSconosciuti;
	public static volatile SingularAttribute<CblTFlussoOrigine, BigDecimal> importoTotalePagamentiNonIntermediati;
	public static volatile SingularAttribute<CblTFlussoOrigine, Integer> numeroTotalePagamenti;
	public static volatile SingularAttribute<CblTFlussoOrigine, Integer> numeroTotalePagamentiIntermediati;
	public static volatile SingularAttribute<CblTFlussoOrigine, Integer> numeroTotalePagamentiSconosciuti;
	public static volatile SingularAttribute<CblTFlussoOrigine, Integer> numeroTotalePagamentiNonIntermediati;
	public static volatile SingularAttribute<CblTFlussoOrigine, String> xmlFlusso;
	public static volatile SingularAttribute<CblTFlussoOrigine, Date> dataRegolamento;
	public static volatile SingularAttribute<CblTFlussoOrigine, String> identificativoUnivocoRegolamento;
	public static volatile SingularAttribute<CblTFlussoOrigine, Boolean> flagFlussoIntermediato;
	public static volatile SingularAttribute<CblTFlussoOrigine, CblDStatoFlusso> cblDStatoFlusso;
	public static volatile SingularAttribute<CblTFlussoOrigine, CblTElaborazione> cblTElaborazione;
	public static volatile SingularAttribute<CblTFlussoOrigine, CblTEnte> cblTEnte;
	public static volatile SingularAttribute<CblTFlussoOrigine, CblDTipoAcquisizione> cblDTipoAcquisizione;
}
