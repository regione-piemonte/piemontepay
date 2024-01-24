/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-09-23T12:14:16.799+0200")
@StaticMetamodel(FlussoSingoloPagamento.class)
public class FlussoSingoloPagamento_ {
	public static volatile SingularAttribute<FlussoSingoloPagamento, Integer> id;
	public static volatile SingularAttribute<FlussoSingoloPagamento, String> codiceesitosingolopagamento;
	public static volatile SingularAttribute<FlussoSingoloPagamento, Timestamp> dataesitosingolopagamento;
	public static volatile SingularAttribute<FlussoSingoloPagamento, Timestamp> datainserimento;
	public static volatile SingularAttribute<FlussoSingoloPagamento, Timestamp> datamodifica;
	public static volatile SingularAttribute<FlussoSingoloPagamento, Integer> idFlusso;
	public static volatile SingularAttribute<FlussoSingoloPagamento, String> identificativounivocoriscossione;
	public static volatile SingularAttribute<FlussoSingoloPagamento, Integer> indicedatisingolopagamento;
	public static volatile SingularAttribute<FlussoSingoloPagamento, String> iuv;
	public static volatile SingularAttribute<FlussoSingoloPagamento, BigDecimal> singoloimportopagato;
	public static volatile SingularAttribute<FlussoSingoloPagamento, Application> application;
}
