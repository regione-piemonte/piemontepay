/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-12-20T11:35:18.341+0100")
@StaticMetamodel(CodiceVersamentoLight.class)
public class CodiceVersamentoLight_ extends AbstractPropagableParentEntity_ {
	public static volatile SingularAttribute<CodiceVersamentoLight, Long> id;
	public static volatile SingularAttribute<CodiceVersamentoLight, String> codice;
	public static volatile SingularAttribute<CodiceVersamentoLight, String> descrizione;
	public static volatile SingularAttribute<CodiceVersamentoLight, Boolean> flagMbSecondario;
	public static volatile SingularAttribute<CodiceVersamentoLight, Boolean> flagMbPrimario;
	public static volatile SingularAttribute<CodiceVersamentoLight, TipoPagamento> tipoPagamento;
	public static volatile SingularAttribute<CodiceVersamentoLight, Ente> ente;
	public static volatile SingularAttribute<CodiceVersamentoLight, StatoMultibeneficiario> statoMultibeneficiario;
}
