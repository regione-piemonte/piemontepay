/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayDStatoPagamento
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayDStatoPagamento.class )
public class EpayDStatoPagamento_ {
	public static volatile SingularAttribute<EpayDStatoPagamento, Short> idStato; 
    public static volatile SingularAttribute<EpayDStatoPagamento, String> descStato; 
    public static volatile SingularAttribute<EpayDStatoPagamento, Boolean> pagabile; 
    public static volatile SingularAttribute<EpayDStatoPagamento, Boolean> modificabile; 
    public static volatile ListAttribute<EpayDStatoPagamento, EpayTRegistroVersamenti> listOfEpayTRegistroVersamenti;
    public static volatile ListAttribute<EpayDStatoPagamento, EpayTPagamento> listOfEpayTPagamento;
}
