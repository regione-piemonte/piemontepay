/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTTipoPagamento
 */
@Generated( value="EII" )
@StaticMetamodel( EpayTTipoPagamento.class )
public class EpayTTipoPagamento_ {
	public static volatile SingularAttribute<EpayTTipoPagamento, Long> idTipoPagamento; 
    public static volatile SingularAttribute<EpayTTipoPagamento, String> codiceVersamento; 
    public static volatile SingularAttribute<EpayTTipoPagamento, String> descrizionePortale; 
    public static volatile SingularAttribute<EpayTTipoPagamento, String> compilazioneNote; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Date> inizioValidita; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Date> fineValidita; 
    public static volatile SingularAttribute<EpayTTipoPagamento, String> idApplicazione; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Long> contatoreSelezioni; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Long> contatoreCompilazioni; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Long> contatorePagamenti; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Boolean> pagamentoSpontaneo; 
    public static volatile SingularAttribute<EpayTTipoPagamento, String> datiSpecificiRiscossione; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Boolean> flagInvioPagamenti; 
    public static volatile SingularAttribute<EpayTTipoPagamento, String> numeroAccertamento; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Long> annoAccertamento; 
    public static volatile SingularAttribute<EpayTTipoPagamento, String> chiaveIntersistema; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Boolean> flagInvioRt; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Boolean> bollettinoPostale; 
    public static volatile SingularAttribute<EpayTTipoPagamento, Boolean> flagMultibeneficiario; 
    public static volatile ListAttribute<EpayTTipoPagamento, EpayTTipoPagamentoTemp> listOfEpayTTipoPagamentoTemp;
    public static volatile ListAttribute<EpayTTipoPagamento, EpayTPagamento> listOfEpayTPagamento;
    public static volatile ListAttribute<EpayTTipoPagamento, EpayTDatiAvvisoPagamento> listOfEpayTDatiAvvisoPagamento;
	public static volatile SingularAttribute<EpayTTipoPagamento, EpayTEnti> epayTEnti;
}
