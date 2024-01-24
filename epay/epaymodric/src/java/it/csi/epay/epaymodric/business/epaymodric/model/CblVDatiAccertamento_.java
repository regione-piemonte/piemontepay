/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.580+0200")
@StaticMetamodel(CblVDatiAccertamento.class)
public class CblVDatiAccertamento_ {
	public static volatile SingularAttribute<CblVDatiAccertamento, Integer> accertamentoAnno;
	public static volatile SingularAttribute<CblVDatiAccertamento, Integer> accertamentoNro;
	public static volatile SingularAttribute<CblVDatiAccertamento, Integer> annoEsercizio;
	public static volatile SingularAttribute<CblVDatiAccertamento, String> codiceVersamento;
	public static volatile SingularAttribute<CblVDatiAccertamento, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<CblVDatiAccertamento, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<CblVDatiAccertamento, String> datiSpecificiRiscossione;
	public static volatile SingularAttribute<CblVDatiAccertamento, Long> id;
	public static volatile SingularAttribute<CblVDatiAccertamento, String> idEnte;
	public static volatile SingularAttribute<CblVDatiAccertamento, String> capitolo;
	public static volatile SingularAttribute<CblVDatiAccertamento, Integer> articolo;
	public static volatile SingularAttribute<CblVDatiAccertamento, String> pianoDeiConti;
	public static volatile SingularAttribute<CblVDatiAccertamento, String> descrizioneCodiceVersamento;
	public static volatile SingularAttribute<CblVDatiAccertamento, Long> idListaDiCarico;
	public static volatile SingularAttribute<CblVDatiAccertamento, String> pdc;
	public static volatile SingularAttribute<CblVDatiAccertamento, Integer> priorita;
}
