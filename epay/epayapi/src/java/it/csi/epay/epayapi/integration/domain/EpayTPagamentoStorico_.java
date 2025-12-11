/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
/**
 *	Metamodel per la classe EpayTPagamentoStorico
 */
@Generated( value="fabio.fenoglio" )
@StaticMetamodel( EpayTPagamentoStorico.class )
public class EpayTPagamentoStorico_ {
    public static volatile SingularAttribute<EpayTPagamentoStorico, Long> id;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Date> dataStoricizzazione;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Long> idPagamento;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Long> idTipoPagamento;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Date> dataInserimento;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> causale;
    public static volatile SingularAttribute<EpayTPagamentoStorico, BigDecimal> importo;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> note;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Boolean> consensoPrivacy;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Date> inizioValidita;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Date> fineValidita;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> iuvNumeroAvviso;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> auxDigit;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> applicationCode;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> codicePagamentoRifEnte;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Integer> annoRiferimento;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Date> dataScadenza;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Short> idStatoCorrente;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> numeroRata;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Long> idAnagraficaPagatore;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Boolean> pagamentoSpontaneo;
    public static volatile SingularAttribute<EpayTPagamentoStorico, Boolean> flagInviato;
    public static volatile SingularAttribute<EpayTPagamentoStorico, String> utenteUltimaModifica;

    public static volatile SingularAttribute<EpayTPagamento, BigDecimal> importoPrincipale;

    public static volatile SingularAttribute<EpayTPagamento, String> identificativoDominio;
}
