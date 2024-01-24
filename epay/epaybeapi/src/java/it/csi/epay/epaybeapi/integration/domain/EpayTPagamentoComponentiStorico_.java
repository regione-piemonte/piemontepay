/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
import java.util.Date;
/**
 *	Metamodel per la classe EpayTPagamentoComponentiStorico
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTPagamentoComponentiStorico.class )
public class EpayTPagamentoComponentiStorico_ {
	public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, Long> id; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, Date> dataStoricizzazione; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, Long> idComponente; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, Long> idPagamento; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, Integer> progressivo; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, BigDecimal> importo; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, String> causale; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, String> datiSpecificiRiscossione; 
    public static volatile SingularAttribute<EpayTPagamentoComponentiStorico, String> utenteUltimaModifica; 
}
