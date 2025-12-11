/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.math.BigDecimal;
import java.util.Date;
/**
 *	Metamodel per la classe EpayTRegistroElaborazioni
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTRegistroElaborazioni.class )
public class EpayTRegistroElaborazioni_ {
	public static volatile SingularAttribute<EpayTRegistroElaborazioni, Long> id; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, Date> dataInizio; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, Date> dataFine; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, String> operazione; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, String> idMessaggio; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, Long> idEnte; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, Long> idTipoPagamento; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, Boolean> pagamentiSpontanei; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, Integer> numPagamenti; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, BigDecimal> importoTotPagamenti; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, String> esito; 
    public static volatile SingularAttribute<EpayTRegistroElaborazioni, String> messageFault; 
    public static volatile ListAttribute<EpayTRegistroElaborazioni, EpayTPagamento> listOfEpayTPagamento;
    public static volatile ListAttribute<EpayTRegistroElaborazioni, EpayTRegistroElaborazioniFault> listOfEpayTRegistroElaborazioniFault;
}
