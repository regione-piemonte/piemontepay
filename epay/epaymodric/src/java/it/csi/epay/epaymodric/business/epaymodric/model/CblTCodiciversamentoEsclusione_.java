/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2022-09-19T18:58:20.377+0200")
@StaticMetamodel(CblTCodiciversamentoEsclusione.class)
public class CblTCodiciversamentoEsclusione_ {
	public static volatile SingularAttribute<CblTCodiciversamentoEsclusione, Long> id;
	public static volatile SingularAttribute<CblTCodiciversamentoEsclusione, String> codiceVersamento;
	public static volatile SingularAttribute<CblTCodiciversamentoEsclusione, String> idEnte;
	public static volatile SingularAttribute<CblTCodiciversamentoEsclusione, String> motivazione;
}
