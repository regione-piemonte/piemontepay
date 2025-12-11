/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayDStatoErrore
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayDStatoErrore.class )
public class EpayDStatoErrore_ {
	public static volatile SingularAttribute<EpayDStatoErrore, Integer> idStato; 
    public static volatile SingularAttribute<EpayDStatoErrore, String> descrizione; 
    public static volatile ListAttribute<EpayDStatoErrore, EpayTErrori> listOfEpayTErrori;
}
