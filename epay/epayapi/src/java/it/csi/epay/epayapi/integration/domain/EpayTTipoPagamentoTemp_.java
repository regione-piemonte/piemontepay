/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.*;

import java.util.Date;
/**
 *	Metamodel per la classe EpayTTipoPagamentoTemp
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTTipoPagamentoTemp.class )
public class EpayTTipoPagamentoTemp_ {
	public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Long> idTipoPagamentoTemp; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> idOperazione; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> codiceVersamento; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> descrizionePortale; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> compilazioneNote; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Date> inizioValidita; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Date> fineValidita; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> idApplicazione; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Long> contatoreSelezioni; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Long> contatoreCompilazioni; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Long> contatorePagamenti; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Boolean> pagamentoSpontaneo; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> datiSpecificiRiscossione; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Boolean> flagInvioPagamenti;
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Boolean> flagMultibeneficiario;
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> numeroAccertamento; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, Long> annoAccertamento; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> chiaveIntersistema; 
    public static volatile SingularAttribute<EpayTTipoPagamentoTemp, String> tipoOperazione; 
	public static volatile SingularAttribute<EpayTTipoPagamentoTemp, EpayTEnti> epayTEnti;
	public static volatile SingularAttribute<EpayTTipoPagamentoTemp, EpayTTipoPagamento> epayTTipoPagamento;
}
