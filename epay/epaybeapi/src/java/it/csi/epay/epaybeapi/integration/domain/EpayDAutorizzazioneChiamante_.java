/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

/**
 *	Metamodel per la classe EpayDAutorizzazioneChiamante
 */
@Generated( value="EII" )
@StaticMetamodel( EpayDAutorizzazioneChiamante.class )
public class EpayDAutorizzazioneChiamante_ {
	public static volatile SingularAttribute<EpayDAutorizzazioneChiamante, String> codice; 
    public static volatile SingularAttribute<EpayDAutorizzazioneChiamante, String> descrizione; 
    public static volatile ListAttribute<EpayDAutorizzazioneChiamante, EpayDChiamanteEsterno> listOfEpayDChiamanteEsterno;
}
