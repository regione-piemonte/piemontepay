/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
/**
 *	Metamodel per la classe EpayTEnti
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTEnti.class )
public class EpayTEnti_ {
	public static volatile SingularAttribute<EpayTEnti, Long> idEnte; 
    public static volatile SingularAttribute<EpayTEnti, String> nome; 
    public static volatile SingularAttribute<EpayTEnti, String> codiceFiscale; 
    public static volatile SingularAttribute<EpayTEnti, byte[]> logo; 
    public static volatile SingularAttribute<EpayTEnti, BigDecimal> codiceGs1Gln; 
    public static volatile SingularAttribute<EpayTEnti, String> orari; 
    public static volatile SingularAttribute<EpayTEnti, Boolean> flagInvioPagamenti; 
    public static volatile SingularAttribute<EpayTEnti, String> codiceInterbancario; 
    public static volatile ListAttribute<EpayTEnti, EpayTEntiTemp> listOfEpayTEntiTemp;
    public static volatile ListAttribute<EpayTEnti, EpayTTipoPagamentoLog> listOfEpayTTipoPagamentoLog;
    public static volatile ListAttribute<EpayTEnti, EpayTEntiLog> listOfEpayTEntiLog;
    public static volatile ListAttribute<EpayTEnti, EpayTConfigurazione> listOfEpayTConfigurazione;
    public static volatile ListAttribute<EpayTEnti, EpayTTipoPagamento> listOfEpayTTipoPagamento;
    public static volatile ListAttribute<EpayTEnti, EpayTTipoPagamentoTemp> listOfEpayTTipoPagamentoTemp;
}
