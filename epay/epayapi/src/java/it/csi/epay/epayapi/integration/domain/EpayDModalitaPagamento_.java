/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayDModalitaPagamento
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayDModalitaPagamento.class )
public class EpayDModalitaPagamento_ {
	public static volatile SingularAttribute<EpayDModalitaPagamento, Integer> idModalitaPagamento; 
    public static volatile SingularAttribute<EpayDModalitaPagamento, String> descrizione; 
    public static volatile ListAttribute<EpayDModalitaPagamento, EpayTEsitiRicevuti> listOfEpayTEsitiRicevuti;
}
