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

@Generated(value="Dali", date="2022-09-19T18:58:20.462+0200")
@StaticMetamodel(CblTListaDiCarico.class)
public class CblTListaDiCarico_ {
	public static volatile SingularAttribute<CblTListaDiCarico, Long> id;
	public static volatile SingularAttribute<CblTListaDiCarico, Integer> accertamentoAnno;
	public static volatile SingularAttribute<CblTListaDiCarico, Integer> accertamentoNro;
	public static volatile SingularAttribute<CblTListaDiCarico, Integer> annoEsercizio;
	public static volatile SingularAttribute<CblTListaDiCarico, BigDecimal> articolo;
	public static volatile SingularAttribute<CblTListaDiCarico, String> capitolo;
	public static volatile SingularAttribute<CblTListaDiCarico, String> codiceVersamento;
	public static volatile SingularAttribute<CblTListaDiCarico, Timestamp> dataFineValidita;
	public static volatile SingularAttribute<CblTListaDiCarico, Timestamp> dataInizioValidita;
	public static volatile SingularAttribute<CblTListaDiCarico, String> datiSpecificiRiscossione;
	public static volatile SingularAttribute<CblTListaDiCarico, String> descrizioneCausale;
	public static volatile SingularAttribute<CblTListaDiCarico, String> idEnte;
	public static volatile SingularAttribute<CblTListaDiCarico, BigDecimal> importoVersamento;
	public static volatile SingularAttribute<CblTListaDiCarico, String> pianoDeiConti;
	public static volatile SingularAttribute<CblTListaDiCarico, Integer> rifPosizioneDebitoria;
	public static volatile SingularAttribute<CblTListaDiCarico, Boolean> attivo;
}
