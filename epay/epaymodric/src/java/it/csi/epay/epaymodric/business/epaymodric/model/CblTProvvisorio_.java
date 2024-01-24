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

@Generated(value="Dali", date="2022-09-19T18:58:20.475+0200")
@StaticMetamodel(CblTProvvisorio.class)
public class CblTProvvisorio_ extends DatiTecniciParentEntity_ {
	public static volatile SingularAttribute<CblTProvvisorio, Long> id;
	public static volatile SingularAttribute<CblTProvvisorio, Integer> annoEsercizio;
	public static volatile SingularAttribute<CblTProvvisorio, Integer> annoProvvisorio;
	public static volatile SingularAttribute<CblTProvvisorio, String> causale;
	public static volatile SingularAttribute<CblTProvvisorio, Timestamp> dataFine;
	public static volatile SingularAttribute<CblTProvvisorio, Timestamp> dataMovimento;
	public static volatile SingularAttribute<CblTProvvisorio, String> descrizione;
	public static volatile SingularAttribute<CblTProvvisorio, String> idEnte;
	public static volatile SingularAttribute<CblTProvvisorio, String> identificativoFlusso;
	public static volatile SingularAttribute<CblTProvvisorio, BigDecimal> importoDisponibilita;
	public static volatile SingularAttribute<CblTProvvisorio, BigDecimal> importoProvvisorio;
	public static volatile SingularAttribute<CblTProvvisorio, Integer> numeroProvvisorio;
	public static volatile SingularAttribute<CblTProvvisorio, String> stato;
	public static volatile SingularAttribute<CblTProvvisorio, String> tipoMovimento;
}
