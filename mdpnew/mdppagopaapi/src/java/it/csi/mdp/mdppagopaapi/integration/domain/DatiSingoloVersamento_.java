/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.786+0200")
@StaticMetamodel(DatiSingoloVersamento.class)
public class DatiSingoloVersamento_ {
	public static volatile SingularAttribute<DatiSingoloVersamento, Integer> id;
	public static volatile SingularAttribute<DatiSingoloVersamento, Integer> annoaccertamento;
	public static volatile SingularAttribute<DatiSingoloVersamento, String> causaleversamento;
	public static volatile SingularAttribute<DatiSingoloVersamento, BigDecimal> commissionecaricopa;
	public static volatile SingularAttribute<DatiSingoloVersamento, String> credenzialipagatore;
	public static volatile SingularAttribute<DatiSingoloVersamento, String> datispecificiriscossione;
	public static volatile SingularAttribute<DatiSingoloVersamento, String> hashdocumento;
	public static volatile SingularAttribute<DatiSingoloVersamento, BigDecimal> importosingoloversamento;
	public static volatile SingularAttribute<DatiSingoloVersamento, Integer> multiId;
	public static volatile SingularAttribute<DatiSingoloVersamento, Integer> numeroaccertamento;
	public static volatile SingularAttribute<DatiSingoloVersamento, Integer> posizione;
	public static volatile SingularAttribute<DatiSingoloVersamento, String> provinciaresidenza;
	public static volatile SingularAttribute<DatiSingoloVersamento, String> tipobollo;
}
