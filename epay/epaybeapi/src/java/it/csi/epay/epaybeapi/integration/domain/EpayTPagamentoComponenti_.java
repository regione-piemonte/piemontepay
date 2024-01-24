/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
/**
 *	Metamodel per la classe EpayTPagamentoComponenti
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTPagamentoComponenti.class )
public class EpayTPagamentoComponenti_ {
	public static volatile SingularAttribute<EpayTPagamentoComponenti, Long> idComponente; 
    public static volatile SingularAttribute<EpayTPagamentoComponenti, Integer> progressivo; 
    public static volatile SingularAttribute<EpayTPagamentoComponenti, BigDecimal> importo; 
    public static volatile SingularAttribute<EpayTPagamentoComponenti, String> causale; 
    public static volatile SingularAttribute<EpayTPagamentoComponenti, String> datiSpecificiRiscossione; 
    public static volatile SingularAttribute<EpayTPagamentoComponenti, String> utenteUltimaModifica; 
    public static volatile SingularAttribute<EpayTPagamentoComponenti, Long> annoAccertamento; 
    public static volatile SingularAttribute<EpayTPagamentoComponenti, String> numeroAccertamento; 
	public static volatile SingularAttribute<EpayTPagamentoComponenti, EpayTPagamento> epayTPagamento;
}
