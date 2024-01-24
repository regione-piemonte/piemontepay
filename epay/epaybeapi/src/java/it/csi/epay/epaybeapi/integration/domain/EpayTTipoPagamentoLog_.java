/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
/**
 *	Metamodel per la classe EpayTTipoPagamentoLog
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTTipoPagamentoLog.class )
public class EpayTTipoPagamentoLog_ {
	public static volatile SingularAttribute<EpayTTipoPagamentoLog, Long> seq; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Long> idTipoPagamento; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, String> codiceVersamento; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, String> descrizionePortale; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, String> compilazioneNote; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Date> inizioValidita; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Date> fineValidita; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, String> idApplicazione; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Long> contatoreSelezioni; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Long> contatoreCompilazioni; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Long> contatorePagamenti; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Boolean> pagamentoSpontaneo; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, String> datiSpecificiRiscossione; 
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Boolean> flagInvioPagamenti;
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Boolean> flagMultibeneficiario;  
    public static volatile SingularAttribute<EpayTTipoPagamentoLog, Date> dataTrigger; 
	public static volatile SingularAttribute<EpayTTipoPagamentoLog, EpayTEnti> epayTEnti;
}
