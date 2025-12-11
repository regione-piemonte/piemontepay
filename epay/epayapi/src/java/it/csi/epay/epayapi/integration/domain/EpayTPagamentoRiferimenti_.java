/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayTPagamentoRiferimenti
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTPagamentoRiferimenti.class )
public class EpayTPagamentoRiferimenti_ {
	public static volatile SingularAttribute<EpayTPagamentoRiferimenti, Long> idRiferimento; 
    public static volatile SingularAttribute<EpayTPagamentoRiferimenti, String> nome; 
    public static volatile SingularAttribute<EpayTPagamentoRiferimenti, Integer> progressivo; 
    public static volatile SingularAttribute<EpayTPagamentoRiferimenti, String> valore; 
    public static volatile SingularAttribute<EpayTPagamentoRiferimenti, String> utenteUltimaModifica; 
	public static volatile SingularAttribute<EpayTPagamentoRiferimenti, EpayTPagamento> epayTPagamento;
}
