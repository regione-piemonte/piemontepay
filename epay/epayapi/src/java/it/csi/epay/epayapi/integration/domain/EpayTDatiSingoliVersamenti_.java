/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
/**
 *	Metamodel per la classe EpayTDatiSingoliVersamenti
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTDatiSingoliVersamenti.class )
public class EpayTDatiSingoliVersamenti_ {
	public static volatile SingularAttribute<EpayTDatiSingoliVersamenti, Long> id; 
    public static volatile SingularAttribute<EpayTDatiSingoliVersamenti, BigDecimal> importo; 
    public static volatile SingularAttribute<EpayTDatiSingoliVersamenti, String> descrizioneCausale; 
    public static volatile SingularAttribute<EpayTDatiSingoliVersamenti, String> datiSpecificiRiscossione; 
	public static volatile SingularAttribute<EpayTDatiSingoliVersamenti, EpayTPagamento> epayTPagamento;
}
